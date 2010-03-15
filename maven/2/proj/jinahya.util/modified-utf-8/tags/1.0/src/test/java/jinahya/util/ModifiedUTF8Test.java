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
import java.io.CharArrayWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ModifiedUTF8Test {


    @Ignore
    @Test
    public void testGenerate() throws IOException {
        new PrintStream(System.out, true, "UTF-8").
            println(ModifiedUTF8.generateString(65535));
    }


    @Test
    public void testEncodeDecode() throws IOException {

        for (int i = 0; i < 1024; i++) {
            final String generated = ModifiedUTF8.generateString(65535);

            final byte[] encoded = ModifiedUTF8.encode(generated.toCharArray());
            final char[] decoded = ModifiedUTF8.decode(encoded);

            assertEquals(generated, new String(decoded));
        }
    }


    @Test
    public void testReadString() throws IOException {

        CharArrayWriter caw = new CharArrayWriter();

        for (int i = 0; i < 1024; i++) {

            caw.reset();
            ModifiedUTF8.generateString(((int) (Math.random() * 65536)), caw);
            caw.flush();
            final String expected = caw.toString();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            dos.writeUTF(expected);
            dos.flush();

            ByteArrayInputStream bais =
                new ByteArrayInputStream(baos.toByteArray());

            final String actual = ModifiedUTF8.readString(bais);

            assertEquals(expected, actual);
        }
    }


    @Test
    public void readWriteString() throws IOException {
        CharArrayWriter caw = new CharArrayWriter();

        for (int i = 0; i < 1024; i++) {

            caw.reset();
            ModifiedUTF8.generateString(((int) (Math.random() * 65536)), caw);
            caw.flush();
            final String expected = caw.toString();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ModifiedUTF8.writeString(expected, baos);
            baos.flush();

            ByteArrayInputStream bais =
                new ByteArrayInputStream(baos.toByteArray());
            DataInputStream dis = new DataInputStream(bais);

            final String actual = dis.readUTF();

            assertEquals(expected, actual);
        }
    }
}
