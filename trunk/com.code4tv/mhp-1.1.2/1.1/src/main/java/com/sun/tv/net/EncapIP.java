/*
 * @(#)EncapIP.java	1.4 99/11/23
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
package com.sun.tv.net;

import java.net.*;
import java.lang.*;

/* Parser for IP encapsulated in MPEG2 transport stream datagram_sections, 
 * with possible LLC_SNAP
 */
public class EncapIP {
    //----------------------------------------------------------------------

    // some useful IP constants

    private static final int IPVERSION = 4;

    // we don't handle options for now so limit the header size.
    // (header length is in bytes)
    private static final int MIN_HEADER_LEN = 20;
    private static final int MAX_HEADER_LEN = 20;

    // IP protocol type constants
    protected static final int IPPROTO_ICMP = 1;
    protected static final int IPPROTO_IGMP = 2;
    protected static final int IPPROTO_TCP  = 6;
    protected static final int IPPROTO_UDP  = 17;

    // IP address constants
    protected static final int IP_CLASSD_ADDR = 0xE0000000;
    protected static final int IP_CLASSD_MASK = 0xF0000000;

    //----------------------------------------------------------------------

    // Offsets for various IP header fields.
    private static final int VERS_OFFSET  = 0;
    private static final int TOS_OFFSET   = 1;
    private static final int LEN_OFFSET   = 2;
    private static final int IDENT_OFFSET = 4;
    private static final int FRAG_OFFSET  = 6;
    private static final int TTL_OFFSET   = 8;
    private static final int PROT_OFFSET  = 9;
    private static final int CKSUM_OFFSET = 10;
    static final int SRCIP_OFFSET = 12;
    static final int DSTIP_OFFSET = 16;

    // IP fragment control bits
    private static final int IP_MF = 0x2000;   // more fragments
    private static final int IP_DF = 0x4000;   // don't fragment

    // offsets for a couple of UDP header fields, in bytes.
    private static final int UDP_SRCPORT_OFFSET = 0;
    private static final int UDP_DSTPORT_OFFSET = 2;
    //----------------------------------------------------------------------
    // Encapsulated datagram section header definitions

    private static final int SECTION_SYNTAX_INDICATOR_BIT = 9;
    private static final int PAYLOAD_SCRAMBLING_START_BIT = 43;
    private static final int PAYLOAD_SCRAMBLING_NO_BITS = 2;
    private static final int ADDRESS_SCRAMBLING_START_BIT = 45;
    private static final int ADDRESS_SCRAMBLING_NO_BITS = 2;
    private static final int LLC_SNAP_FLAG_BIT = 47;
    private static final int LLC_SNAP_HDR_BYTE_OFFSET = 12; //13TH byte
    private static final int LLC_SNAP_HDR_BYTE_LEN = 4; //DVB=4, ATSC=6
    private static final int IP_DATAGRAM_BYTE_OFFSET_LLC = 16; //17TH byte
    private static final int IP_DATAGRAM_BYTE_OFFSET_NO_LLC = 12; //13TH byte
    private static final int CKSUM_OR_CRC_BYTE_LEN = 4;

    private static final int ENCAP_HEADER_LEN = 12;

    private int section_syntax_indicator;
    private int LLC_SNAP;

    /* Get either 1 or 2 bits that's within the same byte.
    * Say I want to get from the 9th bit to the 10th bit, I'll call
    * getBits(9, 1, pkt)
    *
    * Will return -1 if the above criteria is not satisfied.
    */
    private static int getBits(int bit_offset, int no_bits, Packet pkt) {

	if ( (no_bits > 2) || (no_bits < 1) ) {
	    return -1;
	}

	int byte_offset = bit_offset / 8;

	int leftover_bit_offset = bit_offset % 8;

	if (leftover_bit_offset > 0) {
	    byte_offset++; // starting byte
	}

	int starting_byte = pkt.getByte(byte_offset-1); // since in byte buf,
                                                 	// we count from 0
	if ( no_bits == 1 ) {
	    starting_byte = starting_byte >> (8-leftover_bit_offset); // shift right to the last bit
	    starting_byte &= 0x01; // zero the front bits
	} else { // no_bits == 2
	    starting_byte = starting_byte >> (8-leftover_bit_offset-1); // shift right to the last two bits
	    starting_byte &= 0x03; // zero the front bits
	}
	return starting_byte;
    }
	
    //----------------------------------------------------------------------

    public static boolean inputIP(Packet pkt, boolean mcast, int mcastAddr, 
				  int mcastPort, DatagramPacket dPkt) 
	throws PacketDiscardedException {

	
	// parse encap header

	// we get this bit, but we'll ignore the checksum for now. XXX
	int section_syntax_indicator = getBits(SECTION_SYNTAX_INDICATOR_BIT, 1, pkt); 
	int LLC_SNAP = getBits(LLC_SNAP_FLAG_BIT, 1, pkt);

	if ( LLC_SNAP == 1 ) {
	    pkt.shiftHeader(ENCAP_HEADER_LEN + LLC_SNAP_HDR_BYTE_LEN);
	    err("llc bit is 1, correct, pkt hdrlen="+pkt.getHeaderOffset());
	}
	else if (LLC_SNAP == 0) {
	    err("llc bit is 0, wrong!!");
	    pkt.shiftHeader(ENCAP_HEADER_LEN);
	} else {
	    err("wrong llc snap bit");
	}

	// parse IP header
	//printPacket(pkt);

	// check for an Packet too small to contain an IP header
	int len = pkt.dataLength();
	if (len < MIN_HEADER_LEN) {
	    err("Packet length too small: " + len);
	    throw new PacketDiscardedException("Packet length too small: "+len);
	}

	// get the IP version number
	int b = pkt.getByte(VERS_OFFSET);
	int version = (b >>> 4);

	if (version != IPVERSION) {
	    throw new PacketDiscardedException("bad header version: " + version);
	}

	// check whether the header length is acceptable
	int headerLength = (b & 0x0f) * 4;
	if (headerLength != MIN_HEADER_LEN) {
	    throw new PacketDiscardedException("bad header length: " + headerLength);
	}

	// do the header checksum
	if (pkt.cksum(0, headerLength) != 0) {
	    throw new PacketDiscardedException("header checksum error");
	}

	// check if the Packet is large enough for given packet length
	int packetLength = pkt.getShort(LEN_OFFSET);
	err("packetLength="+packetLength+"len="+len);
	if (packetLength > len) {
	    throw new PacketDiscardedException("Packet too short");
	} else if (packetLength < len) {

	    // set the true size of the data for this packet
	    pkt.setDataLength(packetLength);
	}

	int dst_ip = pkt.getInt(DSTIP_OFFSET);

	// Here we try to filter out packets with destination addresses
	// not intended for us.  Check for localAddr, broadcast and loopback.
/* This filter is not really needed -- experimenting without it for now.
	if (dst_ip != 0x7f000001 &&
	    (dst_ip != localAddr && localAddr != 0) &&
	    (dst_ip & netbits) != 0x0 &&
	    (dst_ip & netbits) != 0xff) {
	    // this packet is not intended for us.
	    dprint("rejected dst_ip: " + addrToString(dst_ip));
	    return false;
	}
	*/

	// While previous test may not be needed, now that we support
	// multicast, we have to filter since the underlying driver
	// may deliver packets to us for multicast groups we don't
	// belong to.
	if ((dst_ip & IP_CLASSD_MASK) == IP_CLASSD_ADDR) {
	    //	    if (Igmp.activeGroup(dst_ip) != true) { --tye
	    if ( mcast && (dst_ip != mcastAddr) ) {
		// This group not active
		throw new PacketDiscardedException("multicast address do not match");
	    }
	}

	int src_ip = pkt.getInt(SRCIP_OFFSET);
	int prot =   pkt.getByte(PROT_OFFSET) & 0xff;
	int offset = pkt.getShort(FRAG_OFFSET);

	// The multicast RFC (1112) says that if the source IP address
	// is a group address (type D), then we should quietly discard it.
	if ((src_ip & IP_CLASSD_MASK) == IP_CLASSD_ADDR) {
	    throw new PacketDiscardedException("src addr = multicast addr");
	}

	// TYE -- Check if protocol is UDP, and if port matches multicast port

	if ( prot != IPPROTO_UDP ) {
	    throw new PacketDiscardedException("protocol is not UDP");
	}

	// advance header offset beyond IP header.
	pkt.shiftHeader(headerLength);

	// TYE -- Check if port matches multicast port

	int src_port = pkt.getShort(UDP_SRCPORT_OFFSET);
	int dst_port = pkt.getShort(UDP_DSTPORT_OFFSET);

	if ( (mcast) && (dst_port != mcastPort)) {
	    throw new PacketDiscardedException("multcast port does not match dst_port="+dst_port+"mcastPort supposed to be="+mcastPort);
	}

	pkt.src_port = src_port;
	pkt.src_ip = src_ip;

	// Check if this packet is an IP fragment.
	if ((offset & 0x3fff) != 0) {

	    // get the IP identification for reassembly
	    int id = pkt.getShort(IDENT_OFFSET-headerLength);

	    // give this IP fragment to the reassembler
	    pkt = IPReass.insertFragment(pkt, id, src_ip, dst_ip, prot,offset);
	    if (pkt == null) {
		// undo the inc for this datagram as just a fragment
		throw new PacketDiscardedException("in the process of de-frag");
	    }
	    dprint("fragmented packets reassembled");
	}
	try {
	    dPkt.setAddress( InetAddress.getByName(addrToString(pkt.src_ip)) );
	    dPkt.setPort(pkt.src_port);
	    dPkt.setLength(packetLength);
	    byte newBuf[] = new byte[packetLength];
	    pkt.getBytes(pkt.getHeaderOffset(), newBuf, 0, packetLength);
	    dPkt.setData(newBuf);
	    // don't know the port number from IP XXX
	} catch (UnknownHostException uhe) {
	    throw new PacketDiscardedException("src_ip address is bad");
	}
	return true;
    }


    /**
     * Utility routine to convert an IP address to a dotted
     * decimal string
     *
     * @param	The IP address
     * @return	The Sring a.b.c.d
     */
    public static String addrToString(int addr) {
	return Integer.toString((addr >> 24) & 0xff) + "." +
	    Integer.toString((addr >> 16) & 0xff) + "." +
	    Integer.toString((addr >> 8) & 0xff) + "." +
	    Integer.toString(addr & 0xff);
    }

    //----------------------------------------------------------------------

    private static boolean debug = false;

    private static void dprint(String mess) {
	if (debug) {
	    err(mess);
	}
    }

    private static void err(String mess) {
	System.err.println("EncapIP: " + mess);
    }
}
