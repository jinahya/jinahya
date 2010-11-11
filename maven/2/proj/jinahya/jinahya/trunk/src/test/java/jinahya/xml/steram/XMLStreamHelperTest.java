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

package jinahya.xml.steram;


import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import jinahya.xml.stream.XMLStreamHelper;
import org.testng.Assert;

import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class XMLStreamHelperTest {


    /**
     *
     */
    private static final XMLInputFactory XML_INPUT_FACTORY =
        XMLInputFactory.newInstance();


    /**
     * 
     */
    private static final XMLOutputFactory XML_OUTPUT_FACTORY =
        XMLOutputFactory.newInstance();


    @Test
    public void testReadTextOnlyElement() throws XMLStreamException {

        final String expected = "   abcde   ";

        final XMLStreamReader reader = XML_INPUT_FACTORY.createXMLStreamReader(
            new StringReader("<a>" + expected + "</a>"));
        try {
            reader.nextTag();
            Assert.assertEquals(XMLStreamHelper.readTextOnlyElement(reader),
                                expected);
        } finally {
            reader.close();
        }
    }


    @Test
    public void testReadTextOnlyElementForEmptyElement()
        throws XMLStreamException {

        final XMLStreamReader reader = XML_INPUT_FACTORY.createXMLStreamReader(
            new StringReader("<a/>"));
        try {
            reader.nextTag();
            Assert.assertNull(XMLStreamHelper.readTextOnlyElement(reader));
        } finally {
            reader.close();
        }
    }


    @Test(expectedExceptions = {XMLStreamException.class})
    public void testReadTextOnlyElementForMixedElement()
        throws XMLStreamException {

        final XMLStreamReader reader = XML_INPUT_FACTORY.createXMLStreamReader(
            new StringReader("<a>b<c/>d</a>"));
        try {
            reader.nextTag();
            XMLStreamHelper.readTextOnlyElement(reader);
        } finally {
            reader.close();
        }
    }


    @Test
    public void testWriteTextOnlyElement() throws XMLStreamException {

        final StringWriter stream = new StringWriter();

        final XMLStreamWriter writer =
            XML_OUTPUT_FACTORY.createXMLStreamWriter(stream);
        try {
            XMLStreamHelper.writeTextOnlyElement(writer, "a", "b");
            writer.flush();
            stream.flush();

            Assert.assertEquals(stream.toString(), "<a>b</a>");
        } finally {
            writer.close();
        }
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testWriteTextOnlyElementWithNullLocalName()
        throws XMLStreamException {

        final XMLStreamWriter writer =
            XML_OUTPUT_FACTORY.createXMLStreamWriter(System.out);
        try {
            XMLStreamHelper.writeTextOnlyElement(writer, null, null);
            writer.flush();
        } finally {
            writer.close();
        }
    }


    @Test
    public void testWriteTextOnlyElementWithNullText()
        throws XMLStreamException {

        final StringWriter stream = new StringWriter();

        final XMLStreamWriter writer =
            XML_OUTPUT_FACTORY.createXMLStreamWriter(stream);
        try {
            XMLStreamHelper.writeTextOnlyElement(writer, "a", null);
            writer.flush();
            stream.flush();
        } finally {
            writer.close();
        }

        Assert.assertEquals(stream.toString(), "<a/>");
    }


    @Test
    public void testWriteTextOnlyElementWithAttributes()
        throws XMLStreamException {

        final StringWriter stream = new StringWriter();

        final XMLStreamWriter writer =
            XML_OUTPUT_FACTORY.createXMLStreamWriter(stream);
        try {
            final Map<String, String> attributes =
                new HashMap<String, String>();
            attributes.put("b", "c");

            XMLStreamHelper.writeTextOnlyElement(writer, "a", attributes, "d");
            writer.flush();
            stream.flush();

            Assert.assertEquals(stream.toString(), "<a b=\"c\">d</a>");
        } finally {
            writer.close();
        }
    }
}
