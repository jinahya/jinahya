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
package jinahya.io;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitInputFlacTest {


    @Test
    public void readFlac() throws IOException, XMLStreamException {

        final InputStream in =
            BitInputFlacTest.class.getResourceAsStream("sample.flac");
        if (in == null) {
            return;
        }

        try {
            final Writer out = new OutputStreamWriter(System.out);

            readFlac(in, out);

            out.flush();

        } finally {
            in.close();
        }
    }


    private void readFlac(final InputStream in, final Writer out)
        throws IOException, XMLStreamException {

        final BitInput input = new BitInput(in);

        final XMLOutputFactory factory = XMLOutputFactory.newFactory();
        final XMLStreamWriter writer = factory.createXMLStreamWriter(out);
        try {
            writer.writeStartDocument();

            writer.writeStartElement("stream"); // -------------------- <stream>

            writer.writeStartElement("magic"); // ---------------------- <magic>
            final byte[] magic = new byte[4];
            input.readBytes(magic);
            writer.writeCharacters(new String(magic, "US-ASCII"));
            writer.writeEndElement(); // ------------------------------ </magic>


            readFlacMetadatas(input, writer);

            readFlacFrames(input, writer);

            writer.writeEndElement(); // ----------------------------- </stream>

            writer.writeEndDocument();
        } finally {
            writer.close();
        }
    }


    private void readFlacMetadatas(final BitInput input,
                                   final XMLStreamWriter writer)
        throws IOException, XMLStreamException {

        writer.writeStartElement("metadatas"); // ------------------ <metadatas>

        while (true) {
            writer.writeStartElement("metadata"); // ---------------- <metadata>

            writer.writeStartElement("last");
            final boolean last = input.readBoolean();
            writer.writeCharacters(Boolean.toString(last));
            writer.writeEndElement();

            writer.writeStartElement("type");
            final int type = input.readUnsignedInt(7);
            writer.writeCharacters(Integer.toString(type));
            writer.writeEndElement();

            final int size = input.readUnsignedInt(24);
            for (int i = 0; i < size; i++) {
                input.readInt(8);
            }

            writer.writeEndElement(); // --------------------------- </metadata>

            if (last) {
                break;
            }
        }

        writer.writeEndElement(); // ------------------------------ </metadatas>
    }


    private void readFlacFrames(final BitInput input,
                                final XMLStreamWriter writer)
        throws IOException, XMLStreamException {


        writer.writeStartElement("frames"); // ------------------------ <frames>


        writer.writeStartElement("frame"); // -------------------------- <frame>


        writer.writeStartElement("syncCode"); // sync code
        final int syncCode = input.readUnsignedInt(14);
        writer.writeCharacters(Integer.toBinaryString(syncCode));
        writer.writeEndElement();


        final int reserved1 = input.readUnsignedInt(1);


        writer.writeStartElement("blockingStrategy");
        final int blockingStrategy = input.readUnsignedInt(1);
        writer.writeCharacters(Integer.toString(blockingStrategy));
        writer.writeEndElement();


        writer.writeStartElement("blockSizeInInterChannelSamples");
        final int blockSizeInInterChannelSamples = input.readUnsignedInt(4);
        writer.writeCharacters(Integer.toString(blockSizeInInterChannelSamples));
        writer.writeEndElement();


        writer.writeStartElement("sampleRate"); // sample rate
        final int sampleRate = input.readUnsignedInt(4);
        writer.writeCharacters(Integer.toString(sampleRate));
        writer.writeEndElement();


        writer.writeStartElement("channelAssignment"); // channel assignment
        final int channelAssignment = input.readUnsignedInt(4);
        writer.writeCharacters(Integer.toString(channelAssignment));
        writer.writeEndElement();


        writer.writeStartElement("sampleSizeInBits");
        final int sampleSizeInBits = input.readUnsignedInt(4);
        writer.writeCharacters(Integer.toString(sampleSizeInBits));
        writer.writeEndElement();


        final int reserved2 = input.readUnsignedInt(1); // reserved


        long sampleNumber = -1L;
        int frameNumber = -1;
        if (blockingStrategy == 0x01) {
            sampleNumber = input.readUnsignedLong(36);
        } else {
            frameNumber = input.readUnsignedInt(31);
        }

        writer.writeStartElement("sampleNumber");
        writer.writeCharacters(Long.toString(sampleNumber));
        writer.writeEndElement();

        writer.writeStartElement("frameNumber");
        writer.writeCharacters(Integer.toString(frameNumber));
        writer.writeEndElement();


        if (blockSizeInInterChannelSamples >> 1 == 0x11) { // 011x
        }


        if (sampleSizeInBits >> 2 == 0x11) { // 11xx
        }


        writer.writeStartElement("CRC8");
        final int crc8 = input.readUnsignedInt(8);
        writer.writeCharacters(Integer.toBinaryString(crc8));
        writer.writeEndElement();


        writer.writeEndElement(); // ---------------------------------- </frame>


        writer.writeEndElement(); // --------------------------------- </frames>
    }


}
