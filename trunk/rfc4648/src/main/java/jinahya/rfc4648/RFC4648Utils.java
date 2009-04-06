package jinahya.rfc4648;


/**
 *
 *
 * @author Jin Kwon
 */
public class RFC4648Utils {


    /**
     *
     *
     * @param a
     * @param b
     * @return
     */
    public static int leastCommonMultiple(int a, int b) {
        return (a * b) / greatestCommonDivisor(a, b);
    }


    /**
     *
     *
     * @param a
     * @param b
     * @return
     */
    public static int greatestCommonDivisor(int a, int b) {
        return b == 0 ? a : greatestCommonDivisor(b, a % b);
    }


    /**
     *
     *
     * @param alphabet
     * @return
     */
    public static int bitsPerChar(String alphabet) {
        return (int)(Math.log(alphabet.length())/Math.log(2.0d));
    }


    /**
     *
     *
     * @param bitsPerChar
     * @return
     */
    public static int bytesPerWord(int bitsPerChar) {
        return leastCommonMultiple(8, bitsPerChar) / 8;
    }


    /**
     *
     *
     * @param bytesPerWord
     * @param bitsPerChar
     * @return
     */
    public static int charsPerWord(int bytesPerWord, int bitsPerChar) {
        return bytesPerWord * 8 / bitsPerChar;
    }
}