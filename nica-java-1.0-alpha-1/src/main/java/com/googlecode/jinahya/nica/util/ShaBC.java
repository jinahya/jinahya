/*
 * Copyright 2012 Jin Kwon <jinahya at gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.googlecode.jinahya.nica.util;


import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ShaBC extends Sha {


//    /**
//     * The instance for synchronized singleton.
//     */
//    private static class SynchronizedInstanceHolder {
//
//
//        /**
//         * digest.
//         */
//        private static final Digest DIGEST = new SHA512Digest();
//
//
//        /**
//         * instance.
//         */
//        private static final SHA INSTANCE = new SHABC() {
//
//
//            //@Override
//            public byte[] hash(final byte[] data) {
//
//                if (data == null) {
//                    throw new IllegalArgumentException("null data");
//                }
//
//                synchronized (DIGEST) {
//                    DIGEST.reset();
//                    DIGEST.update(data, 0, data.length);
//                    final byte[] hashed = new byte[DIGEST.getDigestSize()];
//                    DIGEST.doFinal(hashed, 0);
//                    return hashed;
//                }
//
//            }
//
//
//        };
//
//
//    }
//
//
//    /**
//     * Returns the synchronized singleton instance.
//     *
//     * @return the synchronized singleton instance.
//     */
//    public static SHA getSynchronizedInstance() {
//
//        return SynchronizedInstanceHolder.INSTANCE;
//    }
    /**
     * Returns a new synchronized instance.
     *
     * @return a new synchronized instance
     */
    public static Sha newSynchronizedInstance() {

        final Digest DIGEST = new SHA512Digest();

        return new ShaBC() {


            //@Override
            public byte[] hash(final byte[] data) {

                if (data == null) {
                    throw new IllegalArgumentException("null data");
                }

                synchronized (DIGEST) {
                    DIGEST.update(data, 0, data.length);
                    final byte[] hashed = new byte[DIGEST.getDigestSize()];
                    DIGEST.doFinal(hashed, 0);
                    return hashed;
                }
            }


        };
    }


    //@Override
    public byte[] hash(final byte[] data) {

        if (data == null) {
            throw new IllegalArgumentException("null data");
        }

        final Digest digest = new SHA512Digest();
        digest.update(data, 0, data.length);
        final byte[] hashed = new byte[digest.getDigestSize()];
        digest.doFinal(hashed, 0);
        return hashed;
    }


}

