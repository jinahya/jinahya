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


package com.googlecode.jinahya.rfc3986;


import java.util.HashSet;
import java.util.Set;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public final class PercentEncodingConstants {


    private static final byte[] RESERVED_CHARS = new byte[]{};


    private static int[] codePoints(final char[] a, final int offset,
                                    final int count) {

        final int[] codePoints =
            new int[Character.codePointCount(a, offset, count)];

        int index = offset;
        for (int i = 0; i < codePoints.length; i++) {
            codePoints[i] = Character.codePointAt(a, index);
            index += Character.charCount(codePoints[i]);
        }

        return codePoints;
    }


    private static int[] codePoints(final String s) {

        final char[] a = s.toCharArray();
        return codePoints(a, 0, a.length);
    }


    public static final String GEN_DELIMS = "#/:?@[]";


    private static final int[] GEN_DELIMS_CODE_POINTS = codePoints(GEN_DELIMS);


    public static final String SUB_DELIMS = "!$&'()*+,;=";


    private static final int[] SUB_DELIMS_CODE_POINTS = codePoints(SUB_DELIMS);


    public static final String RESERVED = GEN_DELIMS + SUB_DELIMS;


    public static final int[] RESERVED_CODE_POINTS = codePoints(RESERVED);


    public static final String UNRESERVED =
        "123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-._~";


    private static final int[] UNRESERVED_CODE_POINTS = codePoints(UNRESERVED);


    public static final String UNRESERVED_REGEX = "[1-9a-zA-Z-._~]";


    public boolean isReservedCharacter(final char character) {
        final CharSequence sequence = new String(new char[]{character});
        return GEN_DELIMS.contains(sequence) || SUB_DELIMS.contains(sequence);
    }


    public boolean isReservedCharacter(final int codePoint) {
        for (int i = 0; i < RESERVED_CODE_POINTS.length; i++) {
            if (RESERVED_CODE_POINTS[i] == codePoint) {
                return true;
            }
        }
        return false;

//        final CharSequence sequence = new String(new char[]{codePoint});
//        return GEN_DELIMS.contains(sequence) || SUB_DELIMS.contains(sequence);
    }


    public boolean isUnreservedCharacter(final int codePoint) {
        for (int i = 0; i < UNRESERVED_CODE_POINTS.length; i++) {
            if (UNRESERVED_CODE_POINTS[i] == codePoint) {
                return true;
            }
        }
        return false;
//        return Character.isDigit(codePoint)
//               || Character.isAlphabetic(codePoint)
//               || Character.codePointAt(new char[]{'-'}, 0) == codePoint
//               || Character.codePointAt(new char[]{'.'}, 0) == codePoint
//               || Character.codePointAt(new char[]{'_'}, 0) == codePoint
//               || Character.codePointAt(new char[]{'~'}, 0) == codePoint;
    }


    private PercentEncodingConstants() {
        super();
    }


}

