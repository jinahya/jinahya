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
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Random;

import jinahya.util.ModifiedUTF8.Acceptor;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ModifiedUTF8Test {


    /**
     *
     */
    private static final Acceptor NON_ISO_CONTROL = new Acceptor() {


        @Override
        public boolean accept(char ch) {
            return !Character.isISOControl(ch);
        }
    };


    @Test(invocationCount = 1024, enabled = false)
    public void testGenerate() throws IOException {
        final PrintStream out = new PrintStream(System.out, true, "UTF-8");
        final Random random = new Random();
        out.println("GENERATED: " + ModifiedUTF8.generate(
            random.nextInt(76) + 1, random, NON_ISO_CONTROL));
    }


    @Test(invocationCount = 1024, enabled = false)
    public void testEncode() throws IOException {

        final Random random = new Random();

        final int length = random.nextInt(21844) + 1;

        final String expected = ModifiedUTF8.generate(length, random, null);

        final byte[] bytes = ModifiedUTF8.encode(expected);

        final byte[] bytes2 = new byte[bytes.length + 2];

        bytes2[0] = (byte) (bytes.length >> 8);
        bytes2[1] = (byte) (bytes.length & 0xFF);

        System.arraycopy(bytes, 0, bytes2, 2, bytes.length);

        final String actual =
            new DataInputStream(new ByteArrayInputStream(bytes2)).readUTF();

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = 1024, enabled = false)
    public void testDecode() throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final Random random = new Random();

        final int length = random.nextInt(21844);

        final String expected = ModifiedUTF8.generate(length, random, null);

        new DataOutputStream(baos).writeUTF(expected);

        final InputStream in = new ByteArrayInputStream(baos.toByteArray());
        in.read();
        in.read();

        final String actual = ModifiedUTF8.decode(in);

        Assert.assertEquals(actual, expected);
    }
}
