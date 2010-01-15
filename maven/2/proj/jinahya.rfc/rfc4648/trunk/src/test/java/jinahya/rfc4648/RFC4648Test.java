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
import java.util.*;

import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;


/**
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @param <T>
 */
public abstract class RFC4648Test<T extends RFC4648> {


    protected final byte[] getBytes() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int length = (int) (Math.random() * 1024.d);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
           baos.write(random.nextInt());
        }
        return baos.toByteArray();
    }


    protected abstract T getCodec();


    @Test
    public void test() throws IOException {

        T t = getCodec();

        byte[] expected = getBytes();
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


        assertArrayEquals(expected, actual);
    }


    protected BinaryEncoder getCommonsCodecBinaryEncoder() {
        return null;
    }


    @Test
    public final void testWithCommonsCodecBinaryEncoder()
        throws IOException, EncoderException {

        BinaryEncoder binaryEncoder = getCommonsCodecBinaryEncoder();
        if (binaryEncoder == null) {
            return;
        }

        byte[] expecteds = getBytes();

        // -------------------------------------------------------------- ENCODE
        byte[] encoded = encodeWithCommonsCodecBinaryEncoder(
            binaryEncoder, expecteds);


        // -------------------------------------------------------------- DECODE
        T t = getCodec();

        ByteArrayOutputStream decodeOutput = new ByteArrayOutputStream();
        t.decode(new InputStreamReader(
            new ByteArrayInputStream(encoded), "US-ASCII"), decodeOutput);

        byte[] actuals = decodeOutput.toByteArray();


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

        BinaryDecoder binaryDecoder = getCommonsCodecBinaryDecoder();
        if (binaryDecoder == null) {
            return;
        }

        byte[] expecteds = getBytes();


        // -------------------------------------------------------------- ENCODE
        T t = getCodec();

        ByteArrayOutputStream encodeOutput = new ByteArrayOutputStream();
        t.encode(new ByteArrayInputStream(expecteds),
                 new OutputStreamWriter(encodeOutput, "US-ASCII"));


        // -------------------------------------------------------------- DECODE
        byte[] actuals = decodeWithCommonsCodecBinaryDecoder(
            binaryDecoder, encodeOutput.toByteArray());


        // ------------------------------------------------------- ASSERT EQUALS
        Assert.assertArrayEquals(expecteds, actuals);
    }


    protected byte[] decodeWithCommonsCodecBinaryDecoder(BinaryDecoder decoder,
                                                         byte[] encoded)
        throws DecoderException {

        return decoder.decode(encoded);
    }
}
