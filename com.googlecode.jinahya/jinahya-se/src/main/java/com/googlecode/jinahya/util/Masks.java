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


package com.googlecode.jinahya.util;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public final class Masks {


    /**
     * Puts specified {@code mask} on to given {@code face}.
     *
     * @param face
     * @param mask
     *
     * @return
     */
    public static int putOn(final int face, final int mask) {

        return face | mask;
    }


    /**
     * Puts all specified {@code masks} on to given {@code face}.
     *
     * @param face current face
     * @param mask masks to put on
     *
     * @return a new face with all given {@code masks} on.
     */
    public static int putOn(int face, final int... masks) {

        if (masks == null) {
            throw new NullPointerException("null masks");
        }

        if (masks.length == 0) {
            throw new IllegalArgumentException(
                "masks.length(" + masks.length + ") == 0");
        }

        for (int mask : masks) {
            face = putOn(face, mask);
        }

        return face;
    }


    /**
     * Takes specified {@code mask} off from given {@code face}.
     *
     * @param face current face
     * @param mask the mask to take off
     *
     * @return a new face with given {@code mask} off.
     */
    public static int takeOff(final int face, final int mask) {
        return face & ~mask;
    }


    /**
     * Takes all specified {@code masks} off from given {@code face}.
     *
     * @param face current face
     * @param mask masks to take off
     *
     * @return a new face with all given {@code masks} off.
     */
    public static int takeOff(int face, final int... masks) {

        if (masks == null) {
            throw new NullPointerException("null masks");
        }

        if (masks.length == 0) {
            throw new IllegalArgumentException(
                "masks.length(" + masks.length + ") == 0");
        }

        for (int mask : masks) {
            face = takeOff(face, mask);
        }

        return face;
    }


    /**
     * Creates a new instance.
     */
    private Masks() {
        super();
    }


}

