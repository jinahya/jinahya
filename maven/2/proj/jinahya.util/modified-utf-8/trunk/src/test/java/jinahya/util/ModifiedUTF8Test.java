/*
 *  Copyright 2010 Jin Kwon.
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

package jinahya.util;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import jinahya.util.ModifiedUTF8.Acceptor;

import static org.testng.Assert.*;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ModifiedUTF8Test {


    private static final Acceptor NON_ISO_CONTROL =
        new Acceptor() {

            @Override
            public boolean accept(char ch) {
                return !Character.isISOControl(ch);
            }

            @Override
            public char generate() {
                return 126;
            }
        };


    @org.testng.annotations.Test(enabled = false)
    public void testGenerate() throws IOException {
        new PrintStream(System.out, true, "UTF-8").
            println(ModifiedUTF8.generateString(NON_ISO_CONTROL));
    }


    @org.testng.annotations.Test(invocationCount = 1024)
    public void testEncodeRawDecodeRaw() throws IOException {

        final String generated = ModifiedUTF8.generateString(NON_ISO_CONTROL);

        final byte[] encoded = ModifiedUTF8.encodeRaw(generated.toCharArray());

        final char[] decoded = ModifiedUTF8.decodeRaw(encoded);

        assertEquals(generated, new String(decoded));
    }


    @org.testng.annotations.Test(expectedExceptions = {
        NullPointerException.class})
    public void testEncodeRawWithNullBytes() throws IOException {
        ModifiedUTF8.decodeRaw(null);
    }


    @org.testng.annotations.Test(expectedExceptions = {
        NullPointerException.class})
    public void testDecodeRawWithNullBytes() throws IOException {
        ModifiedUTF8.decodeRaw(null);
    }


    @org.testng.annotations.Test(expectedExceptions = {
        IllegalArgumentException.class})
    public void testDecodeRawWithBigBytes() throws IOException {
        ModifiedUTF8.decodeRaw(new byte[65536]);
    }


    @org.testng.annotations.Test(invocationCount = 1024)
    public void testReadString() throws IOException {

        final String expected = ModifiedUTF8.generateString(NON_ISO_CONTROL);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeUTF(expected);
        dos.flush();

        final byte[] encoded = baos.toByteArray();

        ByteArrayInputStream bais = new ByteArrayInputStream(encoded);

        final String actual = ModifiedUTF8.readString(bais);

        assertEquals(expected, actual);
    }


    @org.testng.annotations.Test(invocationCount = 1024)
    public void testWriteString() throws IOException {
        final String expected = ModifiedUTF8.generateString(NON_ISO_CONTROL);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ModifiedUTF8.writeString(expected, baos);
        baos.flush();

        final byte[] encoded = baos.toByteArray();

        ByteArrayInputStream bais = new ByteArrayInputStream(encoded);
        DataInputStream dis = new DataInputStream(bais);

        final String actual = dis.readUTF();

        assertEquals(expected, actual);
    }

}
