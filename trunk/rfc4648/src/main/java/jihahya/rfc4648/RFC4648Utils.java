package jinahya.rfc4648;



public class RFC4648Utils {


    public static int leastCommonMultiple(int a, int b) {
        return (a * b) / greatestCommonDivisor(a, b);
    }


    public static int greatestCommonDivisor(int a, int b) {
        return b == 0 ? a : greatestCommonDivisor(b, a % b);
    }


    public static int bitsPerChar(String alphabet) {
        return (int)(Math.log(alphabet.length())/Math.log(2.0d));
    }


    public static int bytesPerWord(int bitsPerChar) {
        return leastCommonMultiple(8, bitsPerChar) / 8;
    }


    public static int charsPerWord(int bytesPerWord, int bitsPerChar) {
        return bytesPerWord * 8 / bitsPerChar;
    }
}