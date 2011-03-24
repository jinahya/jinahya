/*
 * Copyright 2011 Jin Kwon.
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


package jinahya.rfc4648;


import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class BaseTest<T extends Base> {


    protected static final Random RANDOM = new Random();


    protected static final byte[] generate(final int length) {

        synchronized (RANDOM) {
            final byte[] bytes = new byte[RANDOM.nextInt(length)];
            RANDOM.nextBytes(bytes);
            return bytes;
        }
    }


    private static final PrintStream UTF8;


    static {
        try {
            UTF8 = new PrintStream(System.out, true, "UTF-8");
        } catch (UnsupportedEncodingException uee) {
            throw new InstantiationError("What can I do to make you love me?");
        }
    }


    private static final String[] WELCOMES = {
        "Salaam", "Dobrodošli", "歡迎", "欢迎", "歡迎",
        "Vítáme tĕ", "Velkommen", "Welkom", "Bienvenue", "Wolkom",
        "Willkommen", "Καλώς ορίσατε", "Aloha", "Shalom", "Benvenuto",
        "ようこそ", "환영합니다", "Тавтай морилогтун", "Bem-vindo",
        "Bem-vinda", "Bienvenido", "Välkommen", "Mabuhay", "Swaagatham",
        "Suswaagatham", "Merhaba"};


    public static void main(final String[] args) throws IOException {

        if (args.length == 0) {
            test(WELCOMES);
        } else {
            test(args);
        }
    }


    private static void test(final String[] strings) throws IOException {
        for (String string : strings) {

            System.out.printf("%1$5s: %2$s\n", "input", string);

            final byte[] original = string.getBytes("UTF-8");

            System.out.printf("%1$5s: ", "UTF-8");
            for (byte b : original) {
                System.out.printf("%1$s", Integer.toHexString(b & 0xFF));
            }
            System.out.printf("\n");

            test("base64", new Base64(), original);
            test("base64url", new Base64URL(), original);
            test("base32", new Base32(), original);
            test("base32hex", new Base32HEX(), original);
            test("base16", new Base16(), original);
        }
    }


    private static void test(final String name, final Base base,
                             final byte[] original)
        throws IOException {

        final char[] encoded = base.encode(original);

        System.out.printf("\t%1$10s: %2$s\n", name, new String(encoded));

        final byte[] decoded = base.decode(encoded);
        assert Arrays.equals(decoded, original) : "fail";
    }


    public BaseTest(final T base) {
        super();

        this.base = base;
    }


    @Test(invocationCount = 128)
    public void testEncodingDecoding() throws IOException {

        final byte[] original = generate(1024);

        final char[] encoded = base.encode(original);

        final byte[] decoded = base.decode(encoded);

        Assert.assertEquals(decoded, original, "fail");
    }


    protected final T base;
}
