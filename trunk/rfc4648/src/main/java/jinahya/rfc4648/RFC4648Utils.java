package jinahya.rfc4648;


/**
 *
 *
 * @author Jin Kwon
 */
class RFC4648Utils {


    /**
     *
     *
     * @param a
     * @param b
     * @return the least common mulple
     */
    public static int leastCommonMultiple(int a, int b) {
        return (a * b) / greatestCommonDivisor(a, b);
    }


    /**
     *
     *
     * @param a
     * @param b
     * @return the greatest common divisor
     */
    public static int greatestCommonDivisor(int a, int b) {
        return b == 0 ? a : greatestCommonDivisor(b, a % b);
    }


    /**
     *
     *
     * @param alphabet
     * @return number of bits per character
     */
    public static int bitsPerChar(String alphabet) {
        return (int)(Math.log(alphabet.length())/Math.log(2.0d));
    }


    /**
     *
     *
     * @param bitsPerChar
     * @return number of bytes per word
     */
    public static int bytesPerWord(int bitsPerChar) {
        return leastCommonMultiple(8, bitsPerChar) / 8;
    }


    /**
     *
     *
     * @param bytesPerWord
     * @param bitsPerChar
     * @return number of characters per word
     */
    public static int charsPerWord(int bytesPerWord, int bitsPerChar) {
        return bytesPerWord * 8 / bitsPerChar;
    }
}