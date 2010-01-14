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
 */

package jinahya.rfc4648;


import java.io.*;
import java.util.*;

import org.junit.*;


/**
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @param <T>
 */
public abstract class RFC4648Test<T extends RFC4648> {


    private static final ByteArrayOutputStream BAOS =
        new ByteArrayOutputStream();

    private static final Random RANDOM = new Random();


    protected byte[] newBytes() {
        synchronized (BAOS) {
            BAOS.reset();
            int length = (int) (Math.random() * 1024.d);
            //int length = (int) (Math.random() * 10.d);
            //int length = 9;
            for (int i = 0; i < length; i++) {
                BAOS.write(RANDOM.nextInt());
            }
            return BAOS.toByteArray();
        }
    }


    protected abstract T newCodec();


    @Test
    public void test() throws IOException {

        T t = newCodec();

        byte[] expected = newBytes();
        /*
        for (byte b : expected) {
            System.out.printf(" %1$02x", b);
        }
        System.out.println();
         */

        ByteArrayInputStream encodeInput = new ByteArrayInputStream(expected);
        CharArrayWriter encodeOutput = new CharArrayWriter();

        t.encode(encodeInput, encodeOutput);

        //System.out.println(new String(encodeOutput.toCharArray()));

        CharArrayReader decodeReader =
            new CharArrayReader(encodeOutput.toCharArray());
        ByteArrayOutputStream decodeOutput = new ByteArrayOutputStream();

        t.decode(decodeReader, decodeOutput);

        byte[] actual = decodeOutput.toByteArray();
        /*
        for (byte b : actual) {
            System.out.printf(" %1$02x", b);
        }
        System.out.println();
         */


        Assert.assertArrayEquals(expected, actual);
    }
}
