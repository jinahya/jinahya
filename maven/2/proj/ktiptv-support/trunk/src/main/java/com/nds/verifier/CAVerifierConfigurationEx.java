package com.nds.verifier;


import java.util.Hashtable;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class CAVerifierConfigurationEx {


    private static int[] REGION_BLOCK_NUMBER_LENGTH = new int[] { 0, -1};
    static {
        try {
            REGION_BLOCK_NUMBER_LENGTH =
                CAVerifierConfiguration.readRegionBlocksNumberAndLength();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static final Hashtable REGION_BLOCKS = new Hashtable();


    /**
     * Clear cached information.
     */
    public static synchronized void clearRegionBlocks() {
        REGION_BLOCKS.clear();
    }


    /**
     * Checks give region bit is active or not.
     *
     * @param regionBitIndex target region bit index
     * @return true if the region bit is active, false otherwise
     */
    public static synchronized boolean isRegionBitActive(int regionBitIndex) {

        if (regionBitIndex < 0) {
            throw new IllegalArgumentException
                ("Illegal regionBitIndex: " + regionBitIndex);
        }

        if (REGION_BLOCK_NUMBER_LENGTH[0] <= 0 ||
            REGION_BLOCK_NUMBER_LENGTH[1] <= 0) {

            return false;
        }

        int blockLengthInBits = REGION_BLOCK_NUMBER_LENGTH[1] * 8;

        int blockIndex = regionBitIndex / blockLengthInBits;

        if (blockIndex >= REGION_BLOCK_NUMBER_LENGTH[0]) {
            return false;
        }

        byte[] blockBytes = (byte[]) REGION_BLOCKS.get(new Integer(blockIndex));
        if (blockBytes == null) {
            try {
                blockBytes =
                    CAVerifierConfiguration.readRegionBlockData(blockIndex);
                REGION_BLOCKS.put(new Integer(blockIndex), blockBytes);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        int regionBitIndexInBlock = regionBitIndex % blockLengthInBits;

        int byteIndexInBlock = regionBitIndexInBlock / 8;

        int bitIndexInByte = regionBitIndexInBlock % 8;
        bitIndexInByte = 7 - bitIndexInByte // LETTLE ENDIAN

        byte regionByte = blockBytes[byteIndexInBlock];

        return ((regionByte >>> (7 - bitIndexInByte)) & 0x01) != 0x00;
    }
}