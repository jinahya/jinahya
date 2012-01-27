/*
 * @(#)IPReass.java	1.1 99/11/04
 *
 * Copyright (c) 1996-1999 Sun Microsystems, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Sun
 * Microsystems, Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Sun.
 *
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 */

//
// IPReass.java
//
// IP packet reassembler.
//
// sritchie -- Apr 96
//
// notes
//
// The algorithm to add fragments to the fragment list is very simple minded
// and doesn't prune overlaps.  It rejects any packets that overlap.
// XXX may want to revisit this with a smarter algorithm.
//
// Once initialized, there is no dynamic allocation in this code.  All
// fragment and reassembler descriptors are preallocated in free lists
// and are recycled after use.
//
// When a new fragment comes in, and we don't have a free Fragment descriptor
// or Reassembler descriptor, the oldest reassembler in the active
// reassembly list is recycled.
//

/* adapted for javatv 
 * tye -- 11/2/99
 */

package com.sun.tv.net;
import com.sun.tv.net.util.*;


// A fragment is a container for a single IP packet fragment that
// is waiting to be reassembled.
class Fragment {

    int start_offset;    // IP fragment offset
    int end_offset;

    Packet pkt;

    Fragment next;

    //------------------------------------------------------------------------

    private static final int numFragments = 32;

    private static Fragment fragments[];
    private static Fragment freeFragments;

    //------------------------------------------------------------------------

    static {

	fragments = new Fragment[numFragments];

	// build a linked list of free fragment descriptors.
	fragments[0] = new Fragment();

	int i;
	for (i = 0; i < numFragments-1; i++) {
	    fragments[i+1] = new Fragment();
	    fragments[i].next = fragments[i+1];
	}
	fragments[i].next = null;

	freeFragments = fragments[0];
    }

    //------------------------------------------------------------------------

    // Return a free fragment to the caller after initializing its fields.
    // 
    // This will return null if there isn't enough fragments (buffer), which 
    // is signaled by IPReass.recycleOldest return false.
    static Fragment get(Packet pkt) {
      boolean enough_buffer = true;

	// Recycle the oldest reassembler to free up a new Fragment
	// if necessary.
	while (freeFragments == null) {
	    enough_buffer = IPReass.recycleOldest();

	    if ( enough_buffer == false ) {
	      return null;
	    }
	}

	Fragment frag = freeFragments;
	freeFragments = frag.next;

	frag.pkt = pkt;
	frag.next = null;

	return frag;
    }

    // Recycle this fragment back onto the free list.
    void recycle() {

	if (pkt != null) {
	    pkt.recycle();
	    pkt = null;
	}

	// insert this fragment onto the head of the free list
	next = freeFragments;
	freeFragments = this;
    }
}
 

// The reassembler manages a list of packet fragments.  When all
// fragments have arrives, the reassembler combines them into a
// single packet and cleans up.
class Reassembler {

    int src_ip;   // source IP address
    int dst_ip;   // dest IP address
    int ident;    // packet identification
    int prot;     // protocol number

    int bytesQueued;  // number of bytes queued in fragment list
    int totalBytes;   // total size of resulting packet

    long timeout;     // the time in the future when this reassembler expires.

    // used for queueing reassemblers together
    Reassembler next;
    Reassembler prev;

    // A list of fragments which make up the reassembled packet.  This
    // list is sorted in descending order of IP offset.
    Fragment fragments;

    //------------------------------------------------------------------------

    static final int numReassemblers = 5;

    private static Reassembler reassemblers[];
    private static Reassembler freeReassemblers;

    //------------------------------------------------------------------------

    static {

	reassemblers = new Reassembler[numReassemblers];

	// build a linked list of free reassembler descriptors.
	reassemblers[0] = new Reassembler();

	int i;
	for (i = 0; i < numReassemblers-1; i++) {
	    reassemblers[i+1] = new Reassembler();
	    reassemblers[i].next = reassemblers[i+1];
	}
	reassemblers[i].next = null;

	freeReassemblers = reassemblers[0];
    }

    //------------------------------------------------------------------------

    static Reassembler get(int ident, int src_ip, int dst_ip, int prot) {

	// Recycle the oldest reassembler to free up a new Reassembler
	// if necessary.
        boolean enough_buffer = true;

	while (freeReassemblers == null) {
	    enough_buffer = IPReass.recycleOldest();

            if (enough_buffer == false) // recycleOldest only returns false
	      return null;              // when there is one reassembler active
	                                // so this shouldn't happen
	}

	Reassembler reass = freeReassemblers;
	freeReassemblers = reass.next;

	reass.ident = ident;
	reass.src_ip = src_ip;
	reass.dst_ip = dst_ip;
	reass.prot = prot;
	reass.next = null;
	reass.prev = null;

	return reass;
    }

  // Reassembler recycle

    void recycle() {
      //int FragCounts = 0;
      //dprint("Reassembler recycle number" + this.ident);
	// recycle the list of fragments
	while (fragments != null) {
	    Fragment frag = fragments.next;
	    fragments.recycle();
	    fragments = frag;
	    //FragCounts++;
	    //dprint("frag counts in reassembler recycle " + FragCounts);
	}

	totalBytes = 0;
	bytesQueued = 0;
	ident = 0;

	// insert this reassembler onto the head of the free list
	next = freeReassemblers;
	freeReassemblers = this;
	//dprint("Reassembler: end of recycle number" + this.ident);
    }

    //------------------------------------------------------------------------

    // Combine all the packets in the fragment list into one large Packet.
    private Packet combine() {

	// allocate a packet large enough for the IP pseudo header and
	// total data bytes.
	Packet pkt = GenericPacket.get(12, totalBytes);

	// Insert IP source and dst addresses for upper layers' pseudo header.
	pkt.putInt(src_ip, -8);
	pkt.putInt(dst_ip, -4);

	// iterate over all the fragments and copy each of them into
	// the large packet.
	while (fragments != null) {
	    //dprint("combine offset:" + fragments.start_offset + " len:" +
	    //	   fragments.pkt.dataLength());

	    pkt.putBytes(fragments.pkt, 0, fragments.start_offset*8,
			 fragments.pkt.dataLength());

	    Fragment frag = fragments.next;
	    fragments.recycle();
	    fragments = frag;
	}

	return pkt;
    }
    
    //------------------------------------------------------------------------

    private static final int IP_MF = 0x2000;   // more fragments bit

    // Add a fragment to the list of fragments.  If the addition of this
    // fragment results in a completed packet, the fragments are combined
    // and the completed packet is returned.  Otherwise we return null.
    Packet add(Fragment new_frag, int start_offset) {

        // check for the Last Fragment bit in the offset
        boolean moreFragments = true;
	if ((start_offset & IP_MF) == 0) {
	    moreFragments = false;
	}

	// get the total number of bytes in this fragment
	int len = new_frag.pkt.dataLength();

        // mask out the fragment start offset and compute the end offset
	new_frag.start_offset = start_offset & 0x1fff;
	new_frag.end_offset = new_frag.start_offset + (((len+7)/8) - 1);

	//dprint("add start:" + start_offset + " end:" + end_offset +
	//       " len:" + len + " more:" + moreFragments);

	if (fragments == null) {
	    fragments = new_frag;
	} else if (new_frag.start_offset > fragments.start_offset) {

	    // common case: the new fragment inserts at beginning of list.
	    new_frag.next = fragments;
	    fragments = new_frag;

	} else {

	    // Iterate through the list of descending fragments to find the
	    // insertion point.
	    // XXX We do overlap detection, but we don't trim.  Any packet
	    // that overlaps is thrown away, including any new data in
	    // the packet.  For now we keep it simple.
	    Fragment frag, prev = null;
	    for (frag = fragments; frag != null; frag = frag.next) {

		// does the new fragment go before this fragment?
		if (new_frag.end_offset < frag.start_offset) {
		    // yes, go to the next fragment
		    prev = frag;
		    continue;

		} else if (new_frag.start_offset > frag.end_offset) {
		    // insert the fragment
		    //dprint("inserting new_frag after end:" + frag.end_offset +
		    //	   " but before " + prev.start_offset);
		    prev.next = new_frag;
		    new_frag.next = frag;
		    break;
		} else {
		    // This fragment steps on the current fragment.
		    // XXX might want to trim the overlap here.
		    //dprint("new frag steps on current");
		    new_frag.recycle();
		    return null;
		}
	    }
	}

	if (moreFragments == false) {
	    // this is the last fragment, now we know how large the
	    // reassembled packet is.
	    totalBytes = (new_frag.start_offset * 8) + len;
	    //dprint("got last fragment, totalBytes:" + totalBytes);
	}

	bytesQueued += len;
	if (bytesQueued == totalBytes) {
	    return combine();
	}

	return null;
    }

    //-------------------------------------------------------------------------

    private static boolean debug = false;

    private static void dprint(String s) {
	if (debug == true) {
	    err(s);
	}
    }

    private static void err(String s) {
	System.err.println("Reassembler: " + s);
    }
}


// This class manages a list of reassemblers.  When a new fragment arrives,
// a reassembler is allocated and queued.  Subsequent fragments belonging
// to the same reassembler are added to the reassembler.  
class IPReass {

    // reassembly timeout in msec.
    private static final long REASSEMBLY_TIMEOUT = 30000;

    private static Reassembler reassemblers;   // list of active reassemblers
    private static ReassemblerTimer timer;     // reassembly timer


    //-------------------------------------------------------------------------

    static {
	timer = new ReassemblerTimer();
    }

    //-------------------------------------------------------------------------

    private static int ipReasmFails;

    static int getipReasmFails(){
	return ipReasmFails;
    }

    static void timeout() {

	long currentTime = System.currentTimeMillis();

	// Iterate from the oldest reassembler to the newest.  Recycle
	// the reassemblers whose timeout has expired.  When we run
	// into a reassembler that hasn't expired, stop iterating and
	// reset the timer.
	while (reassemblers != null) {

	    // get the oldest reassembler
	    Reassembler reass = reassemblers.prev;

	    if (reass.timeout <= currentTime) {
		//dprint("timeout id:" + reass.ident);
		ipReasmFails++;
		remove(reass);
	    } else {
		// wake up again when the oldest reassembler times out
		timer.start(reass.timeout - currentTime);
		break;
	    }
	}
    }

    //-------------------------------------------------------------------------
    // Some operations on the reassembler list.

    private static void remove(Reassembler reass) {

	// Remove this reassembler from the list.

	if (reass.next == reass) {
	    reassemblers = null;
	    // the reassembler queue is now empty, so stop the timer.
	    timer.stop();
	} else {

	    if (reassemblers == reass) {
		reassemblers = reass.next;
	    }

	    reass.prev.next = reass.next;
	    reass.next.prev = reass.prev;
	}

	// recycle the reassembler
	reass.recycle();

    }

    private static void insert(Reassembler reass) {

	// Add this new reassembler to our list.
	if (reassemblers == null) {

	    // since this is the first and only reassembler in the
	    // list, we need to start the timer.
	    timer.start(REASSEMBLY_TIMEOUT);

	    reass.next = reass;
	    reass.prev = reass;
	} else {
	    reass.next = reassemblers;
	    reass.prev = reassemblers.prev;
	    reassemblers.prev.next = reass;
	    reassemblers.prev = reass;
	}

	reassemblers = reass;

	// set the time in the future that this reassembler expires.
	reass.timeout = System.currentTimeMillis() + REASSEMBLY_TIMEOUT;
    }

    private static Reassembler find(int id, int src_ip, int dst_ip, int prot) {

	Reassembler reass = reassemblers;

	if (reass != null) {
	    do {
		if (reass.ident == id && reass.src_ip == src_ip &&
		    reass.dst_ip == dst_ip && reass.prot == prot) {
		    //dprint("found ident:" + id);
		    return reass;
		}

		reass = reass.next;
	    } while (reass != reassemblers);
	}

	//dprint("can't find ident:" + id);
	return null;
    }

    // Recycle the oldest reassembler to free up some Fragment and
    // Reassembler descriptors.
    // Will return false if there is one reassembler active
    static boolean recycleOldest() {

	Reassembler reass = reassemblers.prev;

      // Don't have enough space to process the packet
      if ( reassemblers == reass ) {
        //dprint("only one reassembler, can not recycle it");
	return false;
      }
      else {
	remove(reass);
        return true;
      }
    }


    //-------------------------------------------------------------------------

    // This method is the main entry point for reassembling a packet.
    // If the insertion of this fragment results in a completed IP packet,
    // the reassembled packet is returned.  Otherwise null is returned.
    static Packet insertFragment(Packet pkt, int id, int src_ip, int dst_ip,
				 int prot, int offset) {

        // Allocate a new Fragment descriptor for this packet.  We need
	// to copy() the packet because it will be held in the fragment list.
	Fragment frag = Fragment.get(pkt.copy());

	if ( frag == null ) {
  	  // can't insert more fragment -- not enough buffer 
	  // return a pkt with length 0, will be discarded by upper layer
	  // Maybe should return an ICMP of some sort
  
	  // try {
	    // dprint("no more fragments. ");
	  //Icmp.sendIcmpDstUnreachable(src_ip, Icmp.FRAG_REQUIRED, 
	  //			pkt);
	//} catch (NetworkException ex) {
	//    return pkt;
	//  }
	  pkt.setLength(0);
	  return pkt;
	}

        Reassembler reass = find(id, src_ip, dst_ip, prot);

	if (reass != null) {
	    pkt = reass.add(frag, offset);
	    if (pkt != null) {
		// the IP packet is successfully reassembled!
	        //dprint("reassemble done id:" + id);
		remove(reass);
	    }
	    
	    return pkt;
	}
	
	// We couldn't find a reassembler for this fragment, so let's
	// start a new one.
	reass = Reassembler.get(id, src_ip, dst_ip, prot);

	if ( reass == null ) { //theoretically this shouldn't happen
	  pkt.setLength(0);    
	  return pkt;
	}
	else {
	  pkt = reass.add(frag, offset);

	  if (pkt == null) {
	    insert(reass);
	  } else {
	    reass.recycle();
	  }
	
	  return pkt;
	}

    }

    //-------------------------------------------------------------------------

    private static boolean debug = true;

    private static void dprint(String s) {
	if (debug == true) {
	    err(s);
	}
    }

    private static void err(String s) {
	System.err.println("IPReass: " + s);
    }

}

//------------------------------------------------------------------------------

// This class implements the reassembly timeout timer.  When we receive
// a timeout callback from the Timer, all we need to do is call the
// IPReass timeout() routine.
class ReassemblerTimer extends Timer {

    public void callback(long delta) {

	//	ProtocolStack.lock.lock();
	IPReass.timeout();
	//ProtocolStack.lock.unlock();
    }
}
