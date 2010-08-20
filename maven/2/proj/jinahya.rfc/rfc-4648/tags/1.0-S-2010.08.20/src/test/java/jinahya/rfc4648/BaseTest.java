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


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.util.Random;

import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.Test;


/**
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @param <T>
 */
@RunWith(BaseTestClassRunner.class)
public abstract class BaseTest<T extends Base> {


    protected final byte[] getBytes() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int length = (int) (Math.random() * 256.d);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
           baos.write(random.nextInt());
        }
        return baos.toByteArray();
    }


    /**
     *
     * @return an instance of typed codec.
     */
    protected abstract T getCodec();


    @Test
    public void test() throws IOException {

        T t = getCodec();

        byte[] expecteds = getBytes();
        print("original", expecteds);


        // -------------------------------------------------------------- ENCODE
        ByteArrayInputStream encodeInput = new ByteArrayInputStream(expecteds);
        CharArrayWriter encodeOutput = new CharArrayWriter();

        t.encode(encodeInput, encodeOutput);
        char[] encoded = encodeOutput.toCharArray();
        print("encoded by " + t.getClass(), encoded);


        // -------------------------------------------------------------- DECODE
        CharArrayReader decodeReader = new CharArrayReader(encoded);
        ByteArrayOutputStream decodeOutput = new ByteArrayOutputStream();

        t.decode(decodeReader, decodeOutput);

        byte[] actuals = decodeOutput.toByteArray();
        print("decoded by " + t.getClass(), actuals);


        // ------------------------------------------------------- ASSERT EQUALS
        Assert.assertArrayEquals(expecteds, actuals);
    }


    protected BinaryEncoder getCommonsCodecBinaryEncoder() {
        return null;
    }


    @Test
    public final void testWithCommonsCodecBinaryEncoder()
        throws IOException, EncoderException {

        BinaryEncoder encoder = getCommonsCodecBinaryEncoder();
        if (encoder == null) {
            return;
        }

        byte[] expecteds = getBytes();
        print("original", expecteds);

        // -------------------------------------------------------------- ENCODE
        byte[] encoded = encodeWithCommonsCodecBinaryEncoder(
            encoder, expecteds);
        print("encoded by " + encoder.getClass(), encoded);


        // -------------------------------------------------------------- DECODE
        T t = getCodec();

        ByteArrayOutputStream decodeOutput = new ByteArrayOutputStream();
        t.decode(new InputStreamReader(
            new ByteArrayInputStream(encoded), "US-ASCII"), decodeOutput);

        byte[] actuals = decodeOutput.toByteArray();
        print("decoded by " + t.getClass(), actuals);


        // ------------------------------------------------------- ASSERT EQUALS
        Assert.assertArrayEquals(expecteds, actuals);
    }


    protected byte[] encodeWithCommonsCodecBinaryEncoder(BinaryEncoder encoder,
                                                         byte[] original)
        throws EncoderException {

        return encoder.encode(original);
    }


    protected BinaryDecoder getCommonsCodecBinaryDecoder() {
        return null;
    }


    @Test
    public void testWithCommonsCodecBinaryDecoder()
        throws IOException, DecoderException {

        BinaryDecoder decoder = getCommonsCodecBinaryDecoder();
        if (decoder == null) {
            return;
        }

        byte[] expecteds = getBytes();
        print("original", expecteds);


        // -------------------------------------------------------------- ENCODE
        T t = getCodec();

        ByteArrayOutputStream encodeOutput = new ByteArrayOutputStream();
        t.encode(new ByteArrayInputStream(expecteds),
                 new OutputStreamWriter(encodeOutput, "US-ASCII"));
        byte[] encoded = encodeOutput.toByteArray();
        print("encoded by " + t.getClass(), encoded);


        // -------------------------------------------------------------- DECODE
        byte[] actuals = decodeWithCommonsCodecBinaryDecoder(decoder, encoded);
        print("decoded by " + decoder.getClass(), actuals);


        // ------------------------------------------------------- ASSERT EQUALS
        Assert.assertArrayEquals(expecteds, actuals);
    }


    protected byte[] decodeWithCommonsCodecBinaryDecoder(BinaryDecoder decoder,
                                                         byte[] encoded)
        throws DecoderException {

        return decoder.decode(encoded);
    }


    private void print(String head, byte[] bytes) {
        System.out.println("\n" + head + " (" + bytes.length + ")");
        System.out.println("----------------------------------------");
        for (byte b : bytes) {
            System.out.printf("%02x", b);
        }
        System.out.println("\n----------------------------------------");
    }


    private void print(String head, char[] chars) {
        System.out.println("\n" + head + " (" + chars.length + ")");
        System.out.println("----------------------------------------");
        for (char c : chars) {
            System.out.printf("%c", c);
        }
        System.out.println("\n----------------------------------------");
    }
}
