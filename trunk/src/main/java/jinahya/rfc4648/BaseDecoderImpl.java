package jinahya.rfc4648;


public class BaseDecoderImpl {


    public BaseDecoderImpl(char[] alphabet) {
        super();

        code = new byte[94]; // 94: number of visible characters in ASCII
        for (int i = 0; i < code.length; i++) {
            code[i] = -1;
        }
        for (byte i = 0; i < alphabet.length; i++) {
            code[alphabet[i]] = i;
        }

        bits = (int)(Math.log(alphabet.length)/Math.log(2.0d));
        chars = new char[lcm(8, bits) / bits];
        count = 0;
        bytes = new byte[chars.length * bits / 8];

        finalized = false;
    }


    public static class IllegalAlphabetException extends Exception {
        public IllegalAlphabetException() {
            super();
        }
        public IllegalAlphabetException(char ch) {
            this("Illegal alphabet: " + ch);
        }
        public IllegalAlphabetException(String message) {
            super(message);
        }
    }


    public static class BadPaddingException extends Exception {
        public BadPaddingException() {
            super("Bad padding");
        }
        public BadPaddingException(String message) {
            super(message);
        }
    }


    public BaseDecoderImpl reset() {
        count = 0;
        finalized = false;
        return this;
    }


    public byte[] decode(char[] in) throws IllegalAlphabetException {
        return decode(in, 0, in.length);
    }


    public byte[] decode(char[] in, int off, int len)
        throws IllegalAlphabetException {

        //return _decode(in, off, len, false);
        return _decode(in, off, len);
    }


    public byte[] decodeFinal()
        throws IllegalAlphabetException, BadPaddingException {

        return decodeFinal(new char[0]);
    }


    public byte[] decodeFinal(char[] in)
        throws IllegalAlphabetException, BadPaddingException {

        return decodeFinal(in, 0, in.length);
    }


    public byte[] decodeFinal(char[] in, int off, int len)
        throws IllegalAlphabetException, BadPaddingException {

        return _decodeFinal(in, off, len);
    }


//     private byte[] _decode(char[] in, int off, int len, boolean finalize)
//         throws IllegalAlphabetException, BadPaddingException {

//         if (_finalized) {
//             throw new IllegalStateException("Hasn't been reset");
//         }

//         if (finalize) {
//             _finalized = true;
//         }

//         int all = remainedChars.length + len;
//         int rem = all % charsPerWord;

//         if (finalize && rem > 0) {
//             throw new BadPaddingException("Not aligned to " + charsPerWord);
//         }

//         char[] abuf = new char[all];
//         System.arraycopy(remainedChars, 0, abuf, 0, remainedChars.length);
//         System.arraycopy(in, off, abuf, remainedChars.length, len);

//         if (!finalize) {
//             if ((all / charsPerWord) > 0) {
//                 rem += charsPerWord;
//                 all -= charsPerWord;
//             }
//         }

//         remainedChars = new char[rem];
//         System.arraycopy(abuf, abuf.length - remainedChars.length,
//                          remainedChars, 0, remainedChars.length);

//         char[] cbuf = new char[all];
//         System.arraycopy(abuf, 0, cbuf, 0, cbuf.length);


//         int words = cbuf.length / charsPerWord;
//         if (words == 0) { return new byte[0]; };

//         byte[] bbuf = new byte[words * bytesPerWord];
//         if (finalize) { words--; }
//         for (int i = 0; i < words; i++) {
//             int coff = i * charsPerWord;
//             int boff = i * bytesPerWord;
//             _decode(cbuf, coff, bbuf, boff, false);
//         }

//         if (finalize) {
//             int coff = words * charsPerWord;
//             int boff = words * bytesPerWord;
//             _decode(cbuf, coff, bbuf, boff, true);

//             int ipad = -1;
//             for (int i = 0; i < charsPerWord; i++) {
//                 if (cbuf[cbuf.length - charsPerWord + i] == '=') {
//                     ipad = i;
//                     break;
//                 }
//             }

//             if (ipad != -1) {
//                 int min = 8 / bitsPerChar; // minimum non-pad index
//                 if (ipad <= min) {
//                     throw new BadPaddingException();
//                 }

//                 if ((ipad * bitsPerChar) / 8 ==
//                     ((ipad - 1) * bitsPerChar) / 8) {
//                     throw new BadPaddingException();
//                 }

//                 int vali = (ipad * bitsPerChar) / 8;
//                 int inva = bytesPerWord - vali;
//                 byte[] bbuf2 = new byte[bbuf.length - inva];
//                 System.arraycopy(bbuf, 0, bbuf2, 0, bbuf2.length);

//                 return bbuf2;
//             }
//         }

//         return bbuf;
//     }


    private byte[] _decode(char[] in, int off, int len)
        throws IllegalAlphabetException {

        int all = remainedChars.length + len;
        int rem = all % charsPerWord;

        char[] abuf = new char[all];
        System.arraycopy(remainedChars, 0, abuf, 0, remainedChars.length);
        System.arraycopy(in, off, abuf, remainedChars.length, len);

        if ((all / charsPerWord) > 0) {
            rem += charsPerWord;
            all -= charsPerWord;
        }

        remainedChars = new char[rem];
        System.arraycopy(abuf, abuf.length - remainedChars.length,
                         remainedChars, 0, remainedChars.length);

        char[] cbuf = new char[all];
        System.arraycopy(abuf, 0, cbuf, 0, cbuf.length);


        int words = cbuf.length / charsPerWord;
        if (words == 0) { return new byte[0]; };

        byte[] bbuf = new byte[words * bytesPerWord];
        for (int i = 0; i < words; i++) {
            int coff = i * charsPerWord;
            int boff = i * bytesPerWord;
            _decode(cbuf, coff, bbuf, boff, false);
        }

        return bbuf;
    }


    /*
    private byte[] _decodeFinal(char[] in, int off, int len)
        throws IllegalAlphabetException, BadPaddingException {

        if (_finalized) {
            throw new IllegalStateException("Hasn't been reset");
        }

        _decode(in, off, len);

        _finalized = true;

         int all = remainedChars.length + len;
         int rem = all % charsPerWord;

         if (rem > 0) {
             throw new BadPaddingException("Not aligned to " + charsPerWord);
         }

         char[] abuf = new char[all];

        System.arraycopy(remainedChars, 0, abuf, 0, remainedChars.length);
        System.arraycopy(in, off, abuf, remainedChars.length, len);

//         remainedChars = new char[rem];
//         System.arraycopy(abuf, abuf.length - remainedChars.length,
//                          remainedChars, 0, remainedChars.length);

        char[] cbuf = new char[all];
        System.arraycopy(abuf, 0, cbuf, 0, cbuf.length);

        int words = cbuf.length / charsPerWord;
        if (words == 0) { return new byte[0]; };

        byte[] bbuf = new byte[words * bytesPerWord];
        //if (finalize) { words--; }
        words--;
        for (int i = 0; i < words; i++) {
            int coff = i * charsPerWord;
            int boff = i * bytesPerWord;
            _decode(cbuf, coff, bbuf, boff, false);
        }

        int coff = words * charsPerWord;
        int boff = words * bytesPerWord;
        _decode(cbuf, coff, bbuf, boff, true);

        int ipad = -1;
        for (int i = 0; i < charsPerWord; i++) {
            if (cbuf[cbuf.length - charsPerWord + i] == '=') {
                ipad = i;
                break;
            }
        }

        if (ipad != -1) {
            int min = 8 / bitsPerChar; // minimum non-pad index
            if (ipad <= min) {
                throw new BadPaddingException();
            }

            if ((ipad * bitsPerChar) / 8 ==
                ((ipad - 1) * bitsPerChar) / 8) {
                throw new BadPaddingException();
            }

            int vali = (ipad * bitsPerChar) / 8;
            int inva = bytesPerWord - vali;
            byte[] bbuf2 = new byte[bbuf.length - inva];
            System.arraycopy(bbuf, 0, bbuf2, 0, bbuf2.length);

            return bbuf2;
        }

        return bbuf;
    }
    */


    private byte[] _decodeFinal(char[] in, int off, int len)
        throws IllegalAlphabetException, BadPaddingException {

        if (_finalized) {
            throw new IllegalStateException("Hasn't been reset");
        }

        _decode(in, off, len);

        _finalized = true;

        if (remainedChars.length == 0) {
            return new byte[0];
        }

        if ((remainedChars.length % charsPerWord) > 0) {
            throw new BadPaddingException("Not aligned to " + charsPerWord);
        }

        char[] cbuf = remainedChars;

        int words = cbuf.length / charsPerWord;

        byte[] bbuf = new byte[words * bytesPerWord];
        //if (finalize) { words--; }
        words--;
        for (int i = 0; i < words; i++) {
            int coff = i * charsPerWord;
            int boff = i * bytesPerWord;
            _decode(cbuf, coff, bbuf, boff, false);
        }

        int coff = words * charsPerWord;
        int boff = words * bytesPerWord;
        _decode(cbuf, coff, bbuf, boff, true);

        int ipad = -1;
        for (int i = 0; i < charsPerWord; i++) {
            if (cbuf[cbuf.length - charsPerWord + i] == '=') {
                ipad = i;
                break;
            }
        }

        if (ipad != -1) {
            int min = 8 / bitsPerChar; // minimum non-pad index
            if (ipad <= min) {
                throw new BadPaddingException();
            }

            if ((ipad * bitsPerChar) / 8 ==
                ((ipad - 1) * bitsPerChar) / 8) {
                throw new BadPaddingException();
            }

            int vali = (ipad * bitsPerChar) / 8;
            int inva = bytesPerWord - vali;
            byte[] bbuf2 = new byte[bbuf.length - inva];
            System.arraycopy(bbuf, 0, bbuf2, 0, bbuf2.length);

            return bbuf2;
        }

        return bbuf;
    }


    private void _decode(char[] in, int inoff, byte[] out, int outoff,
                         boolean padAllowed)
        throws IllegalAlphabetException {

        int intValue = 0;
        int bitsInInt = 0;
        int byteIndex = bytesPerWord - 1;
        for (int i = charsPerWord - 1; i >= 0; i--) {
            char ch = in[inoff + i];
            intValue |= (getByte(ch, padAllowed) << bitsInInt);
            bitsInInt += bitsPerChar;
            int byteCount = bitsInInt / 8;
            for (int j = 0; j < byteCount; j++) {
                out[outoff + byteIndex--] = (byte)(intValue & 0xff);
                intValue >>= 8;
                bitsInInt -= 8;
            }
        }
    }


    private void decode(boolean finalize) throws IllegalAlphabetException, BadPaddingException {
        for (int i = count; i < bytes.length; i++) {
            bytes[i] = 0x00; // zero padding
        }
        int pad = ((bytes.length - count) * 8) / bits;
        int index = 0;
        int available = 8;
        for (int i = 0; i < (chars.length - pad); i++) {
            int discarded = 32 - available;
            int base = ((bytes[index] & 0xFF) << discarded) >>> discarded;
            if (available >= bits) {
                base >>>= (available - bits);
                available -= bits;
            } else {
                int needed = bits - available;
                base <<= needed;
                base |= ((bytes[++index] & 0xFF) >>> (available = 8 - needed));
            }
            chars[i] = alphabet[base];
        }
        for (int i = (chars.length - pad); i < chars.length; i++) {
            chars[i] = '=';
        }
    }


    private byte getByte(char ch, boolean padAllowed)
        throws IllegalAlphabetException {

        if (ch == '=') {
            if (padAllowed) {
                return 0;
            } else {
                throw new IllegalAlphabetException(ch);
            }
        }

        int index = (int)ch - 33;
        if (index < 0 || index >= bytes.length) {
            throw new IllegalArgumentException("Unknown char: " + ch);
        }
        byte b = bytes[index];
        if (b == -1) {
            throw new IllegalArgumentException("Unknown char?: " + ch);
        }
        return b;
    }


    public boolean isValidCharacter(char ch) {
        try {
            return (bytes[(int)ch - 33] != -1);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            return false;
        }
    }


    private int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }


    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }


    private byte[] code;

    private int bits;
    private char[] chars;
    private int count;
    private byte[] bytes;

    private boolean finalized;
}