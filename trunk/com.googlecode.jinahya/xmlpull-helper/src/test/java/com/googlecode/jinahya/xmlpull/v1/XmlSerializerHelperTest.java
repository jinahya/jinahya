/*
 * Copyright 2011 Jin Kwon <jinahya at gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.googlecode.jinahya.xmlpull.v1;


import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.testng.annotations.Test;

import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class XmlSerializerHelperTest extends XmlPullHelperTest {


    @Test
    public void testAttribute()
        throws XmlPullParserException, IOException {

        final XmlSerializer serializer = newSerializer();

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        serializer.setOutput(baos, ENCODING);

        serializer.startDocument(ENCODING, Boolean.TRUE);

        serializer.startTag(null, "optional");
        XmlSerializerHelper.optionalAttribute(serializer, null, "test", null);
        XmlSerializerHelper.optionalAttribute(serializer, null, "test", "test");
        serializer.endTag(null, "optional");

        serializer.startTag(null, "byte");
        XmlSerializerHelper.byteAttribute(serializer, null, "byte0", null);
        XmlSerializerHelper.byteAttribute(
            serializer, null, "Byte",
            Byte.valueOf((byte) (RANDOM.nextInt(256) - 128)));
        XmlSerializerHelper.byteAttribute(
            serializer, null, "byte", (byte) (RANDOM.nextInt(256) - 128));
        serializer.endTag(null, "byte");

        serializer.startTag(null, "short");
        XmlSerializerHelper.shortAttribute(serializer, null, "short0", null);
        XmlSerializerHelper.shortAttribute(
            serializer, null, "Short",
            Short.valueOf((short) (RANDOM.nextInt(65536) - 32768)));
        XmlSerializerHelper.shortAttribute(
            serializer, null, "short",
            (short) (RANDOM.nextInt(65536) - 32768));
        serializer.endTag(null, "short");

        serializer.startTag(null, "int");
        XmlSerializerHelper.intAttribute(serializer, null, "int0", null);
        XmlSerializerHelper.intAttribute(
            serializer, null, "Integer", Integer.valueOf(RANDOM.nextInt()));
        XmlSerializerHelper.intAttribute(
            serializer, null, "int", RANDOM.nextInt());
        serializer.endTag(null, "int");

        serializer.startTag(null, "long");
        XmlSerializerHelper.longAttribute(serializer, null, "Long0", null);
        XmlSerializerHelper.longAttribute(
            serializer, null, "Long", Long.valueOf(RANDOM.nextLong()));
        XmlSerializerHelper.longAttribute(
            serializer, null, "long", RANDOM.nextLong());
        serializer.endTag(null, "long");

        serializer.startTag(null, "float");
        XmlSerializerHelper.floatAttribute(serializer, null, "Float0", null);
        XmlSerializerHelper.floatAttribute(
            serializer, null, "Float", Float.valueOf(RANDOM.nextFloat()));
        XmlSerializerHelper.floatAttribute(
            serializer, null, "float", RANDOM.nextFloat());
        serializer.endTag(null, "float");

        serializer.startTag(null, "double");
        XmlSerializerHelper.doubleAttribute(serializer, null, "Double0", null);
        XmlSerializerHelper.doubleAttribute(
            serializer, null, "Double", Double.valueOf(RANDOM.nextDouble()));
        XmlSerializerHelper.doubleAttribute(
            serializer, null, "double", RANDOM.nextDouble());
        serializer.endTag(null, "double");

        serializer.endDocument();

        System.out.println(new String(baos.toByteArray(), ENCODING));
    }


    @Test
    public void testTag()
        throws XmlPullParserException, IOException {

        final XmlSerializer serializer = newSerializer();

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        serializer.setOutput(baos, ENCODING);

        serializer.startDocument(ENCODING, Boolean.TRUE);
        serializer.setPrefix(
            "xsi", XmlPullConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI);

        serializer.startTag(null, "root");

        XmlSerializerHelper.nillableTag(serializer, null, "String0", null);
        XmlSerializerHelper.nillableTag(serializer, null, "String1", "String1");

        serializer.endTag(null, "root");

        serializer.endDocument();

        System.out.println(new String(baos.toByteArray(), ENCODING));
    }


}

