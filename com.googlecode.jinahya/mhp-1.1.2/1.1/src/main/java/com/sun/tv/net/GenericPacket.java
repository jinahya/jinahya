/*
 * @(#)GenericPacket.java	1.13 97/09/30 SMI
 *
 * Copyright (c) 1996-1997 Sun Microsystems, Inc. All Rights Reserved.
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

 /**
 * This class implements a general purpose network packet buffer
 * mechanism.
 *
 * GenericPackets can be used for any packet processing at any
 * protocol layer, for input or output.  They are not tied to any
 * particular hardware device.
 *
 * This makes GenericPackets suitable for protocols that require
 * packets to exist beyond the scope of the ProtocolStack.lock.
 * This is opposed to LancePackets, which are one-shot only and
 * must not be held between context switches.
 *
 * GenericPackets do pay a price for their flexibility, however.
 * There is usually one or two more copies involved and perhaps
 * a little bit more bookkeeping in terms of internal data structures.
 *
 * sritchie -- Apr 96
 *
 * notes
 * 
 * The implementation is as simple as possible right now.  All
 * packet buffers are allocated fresh from the heap by new() -- there
 * is no free list of buffers as in the old MBuf scheme.  This may
 * change as the users of GenericPacket increase and performance
 * becomes a concern.
 */

/*
 * Trying to adapt this code to do use for basic IP packet 
 * tye -- 11/2/99
 */
public class GenericPacket extends Packet {

    // The packet buffer.
    private byte buf[];

    GenericPacket(int hlen, int dlen) {

	super();

	length = hlen + dlen;
	hdr_offset = hlen;
	buf = new byte[length];
    }

    //-------------------------------------------------------------------------

    // Get an empty packet with enough room for the specified bytes.
    public static GenericPacket get(int hlen, int dlen) {

	GenericPacket sp = new GenericPacket(hlen, dlen);

	return sp;
    }

    // Return this packet back to its pool.
    public void recycle() {
	if (buf == null) {
	    throw new Error("GenericPacket already recycled!");
	}
	buf = null;
    }

    // Return the Byte Array for this Packet
    public byte[] getByteArray() {
	return buf;
    }


    // Make a physical, device independent copy of this packet.
    public Packet copy() {

	GenericPacket sp = get(hdr_offset, dataLength());

	sp.putBytes(buf, 0, -hdr_offset, length);

	sp.src_ip = src_ip;
	sp.src_port = src_port;
	sp.dst_ip = dst_ip;

	return (Packet) sp;
    }

    // Perform an Internet checksum on the packet data and return it.
    public int cksum(int offset, int len) {
	//return (~cksum(buf, hdr_offset+offset, len)) & 0xffff;
	return 0; // XXX ignore cksum for now. I have to learn how to do it.
    }


    //-------------------------------------------------------------------------
    // Methods to marshall primitive data types in and out of packet headers.

    public long getEthAddr(int off) {

	off += hdr_offset;
	return ((long) (buf[off]   & 0xff) << 40) |
	       ((long) (buf[off+1] & 0xff) << 32) |
	       ((long) (buf[off+2] & 0xff) << 24) |
	       ((long) (buf[off+3] & 0xff) << 16) |
	       ((long) (buf[off+4] & 0xff) << 8) |
	        (long) (buf[off+5] & 0xff);
    }

    public int getInt(int off) {

	off += hdr_offset;
	return ((buf[off]         ) << 24) |
	       ((buf[off+1] & 0xff) << 16) |
	       ((buf[off+2] & 0xff) << 8) |
	        (buf[off+3] & 0xff);
    }

    public int getShort(int off) {

	off += hdr_offset;
	return ((buf[off] & 0xff) << 8) |
                (buf[off+1] & 0xff);
    }

    public int getByte(int off) {
	return (buf[hdr_offset + off] & 0xff);
    }

    // Copy data from packet to supplied buffer.
    public void getBytes(int src_offset, byte dst[], int dst_offset, int len) {

	System.arraycopy(buf, hdr_offset+src_offset, dst, dst_offset, len);
    }

    public void putEthAddr(long d, int off) {

	off += hdr_offset;
        buf[off] =   (byte)  (d >>> 40);
        buf[off+1] = (byte) ((d >> 32) & 0xff);
        buf[off+2] = (byte) ((d >> 24) & 0xff);
        buf[off+3] = (byte) ((d >> 16) & 0xff);
        buf[off+4] = (byte) ((d >> 8) & 0xff);
        buf[off+5] = (byte) (d & 0xff);
    }

    public void putInt(int d, int off) {

	off += hdr_offset;
        buf[off] =   (byte)  (d >>> 24);
        buf[off+1] = (byte) ((d >> 16) & 0xff);
        buf[off+2] = (byte) ((d >> 8) & 0xff);
        buf[off+3] = (byte) (d & 0xff);
    }

    public void putShort(int d, int off) {

	off += hdr_offset;
        buf[off] =   (byte) ((d >> 8) & 0xff);
        buf[off+1] = (byte) (d & 0xff);
    }

    public void putByte(int d, int off) {
	buf[hdr_offset + off] = (byte) (d & 0xff);
    }

    // Copy data from supplied buffer to packet.
    public void putBytes(byte src[], int src_offset, int dst_offset, int len) {

	System.arraycopy(src, src_offset, buf, dst_offset+hdr_offset, len);
    }

    // Copy the specified range from the given packet into this packet.
    public void putBytes(Packet pkt, int src_offset, int dst_offset, int len) {

	pkt.getBytes(src_offset, buf, dst_offset+hdr_offset, len);
    }
}
