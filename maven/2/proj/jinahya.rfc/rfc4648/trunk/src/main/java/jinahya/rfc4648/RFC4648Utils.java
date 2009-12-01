/*
 *  Copyright 2009 Jin Kwon.
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
 *  under the License.
 */

package jinahya.rfc4648;


/**
 *
 *
 * @author <a href="mailto:jinahya@gmail.com>Jin Kwon</a>
 */
final class RFC4648Utils {


    public static final int OCTET_SIZE = 8;


    /**
     *
     *
     * @param a first operand
     * @param b second operand
     * @return the value of least common mulple
     */
    public static int leastCommonMultiple(final int a, final int b) {
        return (a * b) / greatestCommonDivisor(a, b);
    }


    /**
     *
     *
     * @param a first operand
     * @param b second operand
     * @return the value of greatest common divisor
     */
    public static int greatestCommonDivisor(final int a, final int b) {
        if (b == 0) {
            return a;
        } else {
            return greatestCommonDivisor(b, a % b);
        }
    }


    /**
     *
     *
     * @param alphabet
     * @return number of bits per character
     */
    public static int bitsPerChar(final String alphabet) {
        return (int) (Math.log(alphabet.length()) / Math.log(2.0d));
    }


    /**
     *
     *
     * @param bitsPerChar
     * @return number of bytes per word
     */
    public static int bytesPerWord(final int bitsPerChar) {
        return leastCommonMultiple(OCTET_SIZE, bitsPerChar) / OCTET_SIZE;
    }


    /**
     *
     *
     * @param bytesPerWord
     * @param bitsPerChar
     * @return number of characters per word
     */
    public static int charsPerWord(final int bytesPerWord,
                                   final int bitsPerChar) {

        return bytesPerWord * OCTET_SIZE / bitsPerChar;
    }


    private RFC4648Utils() {
        super();
    }
}
