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


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ShaJCA extends Sha {


    /**
     * Hash algorithm.
     */
    public static final String ALGORITHM = "SHA-512";


//    /**
//     * instance holder.
//     */
//    private static class SynchronizedInstanceHolder {
//
//
//        /**
//         * digest.
//         */
//        private static final MessageDigest DIGEST;
//
//
//        static {
//            try {
//                DIGEST = MessageDigest.getInstance(ALGORITHM);
//            } catch (NoSuchAlgorithmException nsae) {
//                throw new InstantiationError(nsae.getMessage());
//            }
//        }
//
//
//        /**
//         * instance.
//         */
//        private static final SHA INSTANCE = new SHAJCA() {
//
//
//            @Override
//            public byte[] hash(final byte[] data) {
//
//                if (data == null) {
//                    throw new IllegalArgumentException("null data");
//                }
//
//                synchronized (DIGEST) {
//                    DIGEST.reset();
//                    return DIGEST.digest(data);
//                }
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
//     * Return the synchronized instance.
//     *
//     * @return the synchronized instance; singleton.
//     */
//    public static SHA getSynchronizedInstance() {
//
//        return SynchronizedInstanceHolder.INSTANCE;
//    }
    /**
     * Creates a new synchronized instance.
     *
     * @return a new synchronized instance
     */
    public static Sha newSynchronizedInstance() {
//        /**
//         * digest.
//         */
//        final MessageDigest DIGEST;
//
//        try {
//            DIGEST = MessageDigest.getInstance(ALGORITHM);
//        } catch (NoSuchAlgorithmException nsae) {
//            throw new InstantiationError(nsae.getMessage());
//        }

        return new ShaJCA() {


            @Override
            public synchronized byte[] hash(final byte[] data) {
                return super.hash(data);
            }


        };
    }


    public ShaJCA() {
        super();

        try {
            messageDigest = MessageDigest.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException("\"" + ALGORITHM + "\" not available?");
        }
    }


    @Override
    public byte[] hash(final byte[] data) {

        if (data == null) {
            throw new IllegalArgumentException("null data");
        }

        return messageDigest.digest(data);
//        try {
//            return MessageDigest.getInstance(ALGORITHM).digest(data);
//        } catch (NoSuchAlgorithmException nsae) {
//            throw new RuntimeException("\"" + ALGORITHM + "\" not available?");
//        }
    }


    private final MessageDigest messageDigest;


}

