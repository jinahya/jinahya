/*
 *  Copyright 2010 Jin Kwon.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */


package com.googlecode.jinahya.io;


import java.io.IOException;

import java.security.MessageDigest;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.zip.Checksum;


/**
 * Base class for Bit I/O.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
abstract class BitIOBase {


    /**
     * constant for 0x00.
     */
    static final int ZERO = 0;


    /**
     * constant for 0x01.
     */
    static final int ONE = 1;


    /**
     * Aligns to given
     * <code>length</code> as bytes.
     *
     * @param length the number of bytes to align
     * @return the number of bits padded/discarded for alignment
     * @throws IOException if an I/O error occurs.
     */
    public abstract int align(final int length) throws IOException;


    /**
     * Returns the number of octets written/read so far.
     *
     * @return the number of octets written/read so far.
     */
    public final int getCount() {
        return count;
    }


    /**
     * Adds specified
     * <code>checksum</code>.
     *
     * @param checksum checksum to add
     */
    public final void addChecksum(final Checksum checksum) {

        if (checksum == null) {
            throw new NullPointerException("null checksum");
        }

        checksums.add(checksum);
    }


    /**
     * Removes specified
     * <code>checksum</code>.
     *
     * @param checksum checksum to remove
     * @return true if removed; false otherwise
     */
    public final boolean removeChecksum(final Checksum checksum) {

        return checksums.remove(checksum);
    }


    /**
     * Adds specified
     * <code>digest</code>.
     *
     * @param digest digest to add
     */
    public final void addDigest(final MessageDigest digest) {

        if (digest == null) {
            throw new NullPointerException("null digest");
        }

        digests.add(digest);
    }


    /**
     * Removes specified
     * <code>digest</code>.
     *
     * @param digest digest to remove
     * @return true if removed; false otherwise
     */
    public final boolean removeDigest(final MessageDigest digest) {
        return digests.remove(digest);
    }


    /**
     * Returns bit value.
     *
     * @param bitIndex bit index
     * @return bit value
     * @see BitSet#get(int)
     */
    final boolean getBit(final int bitIndex) {
        return set.get(bitIndex);
    }


    /**
     * Sets bit value.
     *
     * @param bitIndex bit index
     * @param value bit value
     * @see BitSet#set(int, boolean)
     */
    final void setBit(final int bitIndex, final boolean value) {

        if (bitIndex < ZERO) {
            throw new IllegalArgumentException(
                "bitIndex(" + bitIndex + ") < " + ZERO);
        }

        if (bitIndex >= Byte.SIZE) {
            throw new IllegalArgumentException(
                "bitIndex(" + bitIndex + ") >= " + Byte.SIZE);
        }

        set.set(bitIndex, value);
    }


    /**
     * Returns octet.
     *
     * @return octet
     */
    final int getOctet() {

        int octet = ZERO;
        for (int i = 0; i < Byte.SIZE; i++) {
            octet <<= 1;
            octet |= (getBit(i) ? 0x01 : 0x00);
        }

        update(octet);

        return octet;
    }


    /**
     * Sets octet.
     *
     * @param octet octet.
     */
    final void setOctet(int octet) {

        update(octet);

        for (int i = Byte.SIZE - 1; i >= 0; i--) {
            setBit(i, (octet & 0x01) == 0x01);
            octet >>= 1;
        }
    }


    /**
     * Increases count.
     */
    final void increaseCount() {
        count++;
    }


    /**
     * Update given byte to checksums and digests.
     *
     * @param b byte to be updated
     */
    private void update(final int b) {

        for (Checksum checksum : checksums) {
            checksum.update(b);
        }

        for (MessageDigest digest : digests) {
            digest.update((byte) b);
        }
    }


    /**
     * bit set.
     */
    private final BitSet set = new BitSet(Byte.SIZE);


    /**
     * number of octets written/read so far.
     */
    private int count = 0x00;


    /**
     * checksums.
     */
    private final List<Checksum> checksums = new ArrayList<Checksum>();


    /**
     * digests.
     */
    private final List<MessageDigest> digests = new ArrayList<MessageDigest>();


}

