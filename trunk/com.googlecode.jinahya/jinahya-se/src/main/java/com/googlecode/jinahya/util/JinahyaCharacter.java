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
public final class JinahyaCharacter {


    /**
     * Returns the array of Unicode code points in a subarray of the
     * <code>char</code> array argument. The
     * <code>offset</code> argument is the index of the first
     * <code>char</code> of the subarray and the
     * <code>count</code> argument specifies the length of the subarray in
     * <code>char</code>s. Unpaired surrogates within the subarray count as one
     * code point each.
     *
     * @param a the
     * <code>char</code> array
     * @param offset the index of first
     * <code>char</code> in the given
     * <code>char</code> array
     * @param count the length of the subarray in
     * <code>chars</code>
     * @return the array of Unicode code points in the specified subarray
     */
    public static int[] codePoints(final char[] a, final int offset,
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


    /**
     * Returns the array of Unicode code points in the text range of the
     * specified char sequence. The text range begins at the specified
     * <code>beginIndex</code> and extends to the
     * <code>char</code> at index
     * <code>endIndex</code> - 1. Thus the length (in
     * <code>char</code>s) of the text range is
     * <code>endIndex-beginIndex</code>. Unpaired surrogates within the text
     * range count as one code point each.
     *
     * @param seq the char sequence
     * @param beginIndex the index to the first char of the text range
     * @param endIndex the index after the last
     * <code>char</code> of the text range
     * @return the array of Unicode code points in the specified text range
     */
    public static int[] codePoints(final CharSequence seq, final int beginIndex,
                                   final int endIndex) {

        final int[] codePoints =
            new int[Character.codePointCount(seq, beginIndex, endIndex)];
        int index = beginIndex;
        for (int i = 0; i < codePoints.length; i++) {
            codePoints[i] = Character.codePointAt(seq, index);
            index += Character.charCount(codePoints[i]);
        }

        return codePoints;
    }


    /**
     * PRIVATE.
     */
    private JinahyaCharacter() {
        super();
    }


}

