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


import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import static org.junit.Assert.*;
import org.junit.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class RFC4648EncoderTest {


    @Test
    public void test() throws IOException {

        byte[] source = new byte[(int) (Math.random() * 65536)];
        //byte[] source = new byte[10];

        for (int i = 0; i < source.length; i++) {
            source[i] = (byte) (Math.random() * 256);
        }

        {
            String actual = encode(RFC4648Constants.BASE64, source);
            System.out.println("actual:   <" + actual + ">");
            String expected = new String(Base64.encodeBase64(source), "US-ASCII");
            System.out.println("expected: <" + expected + ">");
            assertEquals(expected, actual);
        }

        {
            /*
            String actual = encode(RFC4648Constants.BASE64URL, source);
            System.out.println("actual:   <" + actual + ">");
            String expected = new String(Base64.encodeBase64URLSafe(source), "US-ASCII");
            System.out.println("expected: <" + expected + ">");
            assertEquals(expected, actual);
             */
        }

        {
            String actual = encode(RFC4648Constants.BASE16, source);
            System.out.println("actual:   <" + actual + ">");
            String expected = new String(Hex.encodeHex(source, false));
            System.out.println("expected: <" + expected + ">");
            assertEquals(expected, actual);
        }

    }


    private String encode(String alphabet, byte[] source) throws IOException {
        StringWriter writer = new StringWriter();
        try {
            RFC4648Encoder.encode(alphabet, source, writer);
            writer.flush();
            return writer.toString();
        } finally {
            writer.close();
        }
    }
}
