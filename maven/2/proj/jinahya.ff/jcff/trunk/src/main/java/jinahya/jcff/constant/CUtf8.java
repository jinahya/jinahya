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

package jinahya.jcff.constant;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UTFDataFormatException;
import java.io.Writer;

import javax.xml.bind.annotation.XmlElement;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class CUtf8 extends Constant {


    private static final long serialVersionUID = -4132889133325049361L;


    public static String modifiedUtf8(byte[] source) throws IOException {
        StringWriter sw = new StringWriter();
        try {
            modifiedUtf8(source, sw);
            sw.flush();
            return sw.toString();
        } finally {
            sw.close();
        }
    }


    public static void modifiedUtf8(byte[] source, Writer target)
        throws IOException {

        ByteArrayInputStream bais = new ByteArrayInputStream(source);
        try {
            modifiedUtf8(bais, target);
        } finally {
            bais.close();
        }
    }


    public static String modifiedUtf8(InputStream source) throws IOException {
        StringWriter sw = new StringWriter();
        try {
            modifiedUtf8(source, sw);
            sw.flush();
            return sw.toString();
        } finally {
            sw.close();
        }
    }


    public static void modifiedUtf8(InputStream source, Writer target)
        throws IOException {

        /*
        byte[] bytes = new byte[((source.read() & 0xFF) << 8) |
                               (source.read() & 0xFF)];

        // read fully
        for (int index = 0; index < bytes.length; ) {
            index += source.read(bytes, index, bytes.length - index);
        }
         */

        int length = ((source.read() & 0xFF) << 8) | (source.read() & 0xFF);

        for (int i = 0; i < length; i++) {

            byte a = (byte) source.read();
            i++;

            if (a >>> 4 == 0x0F || a >>> 6 == 0x02) {
                throw new UTFDataFormatException(":(");
            }

            if ((a >>> 8 == 0x00)) { // 0xxxxxxx
                target.write(a);

            } else if (a >>> 5 == 0x06) { // 110xxxxx
                if (i == length - 1) {
                    throw new UTFDataFormatException(":(");
                }

                byte b = (byte) source.read();
                i++;

                if (b >>> 6 != 0x02) { // !(10xxxxxx)
                    throw new UTFDataFormatException(":(");
                }

                target.write((char) (((a & 0x1F) << 6) | (b & 0x3F)));

            } else if (a >>> 4 == 0x0E) { // 1110xxxxx
                if (i == length - 2) {
                    throw new UTFDataFormatException(":(");
                }

                byte b = (byte) source.read();
                i++;

                byte c = (byte) source.read();
                i++;

                if (b >>> 6 != 0x02 || c >>> 6 != 0x02) { // !(10xxxxxx)
                    throw new UTFDataFormatException(":(");
                }

                target.write((char) (((a & 0x0F) << 12) |
                                    ((b & 0x3F) << 6) |
                                    (c & 0x3F)));
            }
        }
    }


    public static byte[] modifiedUtf8(String source) throws IOException {
        StringReader sr = new StringReader(source);
        try {
            return modifiedUtf8(sr);
        } finally {
            sr.close();
        }
    }


    public static void modifiedUtf8(String source, OutputStream target)
        throws IOException {

        StringReader sr = new StringReader(source);
        try {
            modifiedUtf8(sr, target);
        } finally {
            sr.close();
        }
    }


    public static byte[] modifiedUtf8(Reader source) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            modifiedUtf8(source, baos);
            baos.flush();
            return baos.toByteArray();
        } finally {
            baos.close();
        }
    }


    public static void modifiedUtf8(Reader source, OutputStream target)
        throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            for (int c = -1; (c = source.read()) != -1; ) {
                if (c == '\u0000') {
                    baos.write((byte) (0xC0 | (0x1F & (c >> 6))));
                    baos.write((byte) (0x80 | (0x3F & c)));
                    if (baos.size() > 65535) {
                        throw new UTFDataFormatException
                            ("too long: " + baos.size());
                    }
                } else if (c <= '\u007F') {
                    baos.write((byte) c);
                    if (baos.size() > 65535) {
                        throw new UTFDataFormatException
                            ("too long: " + baos.size());
                    }
                } else if (c <= '\u07FF') {
                    baos.write((byte) (0xC0 | (0x1F & (c >> 6))));
                    baos.write((byte) (0x80 | (0x3F & c)));
                    if (baos.size() > 65535) {
                        throw new UTFDataFormatException
                            ("too long: " + baos.size());
                    }
                } else if (c <= '\uFFFF') {
                    baos.write((byte) (0xE0 | (0x0F & (c >> 12))));
                    baos.write((byte) (0x80 | (0x3F & (c >> 6))));
                    baos.write((byte) (0x80 | (0x3F & c)));
                    if (baos.size() > 65535) {
                        throw new UTFDataFormatException
                            ("too long: " + baos.size());
                    }
                }
            }

            baos.flush();

            int length = baos.size();
            target.write((length >> 8) & 0xFF);
            target.write(length & 0xFF);

            target.write(baos.toByteArray());

        } finally {
            baos.close();
        }
    }


    public CUtf8() {
        super(CONSTANT_Utf8);
    }


    @Override
    protected void readInfo(DataInput in)
        throws IOException {

        bytes = new byte[in.readShort() & 0xFF];
        in.readFully(bytes);
    }


    @Override
    protected void writeInfo(DataOutput out) throws IOException {
        out.writeShort(bytes.length);
        out.write(bytes);
    }


    public byte[] getBytes() {
        return bytes;
    }


    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }


    @XmlElement(required=true)
    public String getValue() throws IOException {

        /*
        int length = ((bytes[0] & 0xFF) << 8) | (bytes[1] & 0xFF);
        System.out.println("length: " + length);
         */

        StringWriter writer = new StringWriter();
        try {
            for (int i = 0; i < bytes.length; i++) {

                byte a = bytes[i];

                if (a >>> 4 == 0x0F || a >>> 6 == 0x02) {
                    throw new UTFDataFormatException(":(");
                }

                if ((a >>> 8 == 0x00)) { // 0xxxxxxx
                    writer.write(a);

                } else if (a >>> 5 == 0x06) { // 110xxxxx
                    if (i == bytes.length - 1) {
                        throw new UTFDataFormatException(":(");
                    }

                    byte b = bytes[++i];

                    if (b >>> 6 != 0x02) { // !(10xxxxxx)
                        throw new UTFDataFormatException(":(");
                    }

                    writer.write((char) (((a & 0x1F) << 6) | (b & 0x3F)));

                } else if (a >>> 4 == 0x0E) { // 1110xxxxx
                    if (i == bytes.length - 2) {
                        throw new UTFDataFormatException(":(");
                    }

                    byte b = bytes[++i];

                    byte c = bytes[++i];

                    if (b >>> 6 != 0x02 || c >>> 6 != 0x02) { // !(10xxxxxx)
                        throw new UTFDataFormatException(":(");
                    }

                    writer.write((char) (((a & 0x0F) << 12) |
                                        ((b & 0x3F) << 6) |
                                        (c & 0x3F)));
                }
            }

            return writer.toString();

        } finally {
            writer.close();
        }
    }


    public void setValue(String value) throws IOException {
        bytes = modifiedUtf8(value);
    }


    @Override
    public String toString() {
        try {
            return "CONSTANT_Utf8: " + getValue();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }

    private byte[] bytes;
}
