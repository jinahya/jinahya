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
    public void testTag()
        throws XmlPullParserException, IOException {

        final XmlSerializer serializer = newSerializer();

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        serializer.setOutput(baos, ENCODING);

        serializer.startDocument(ENCODING, Boolean.TRUE);
        serializer.setPrefix(
            "xsi", XmlConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI);

        serializer.startTag(null, "root");

        XmlSerializerHelper.nillableSimpleTag(serializer, null, "String0",
                                              null);
        XmlSerializerHelper.simpleTag(serializer, null, "String1", "String1",
                                      false);

        XmlSerializerHelper.nillableSimpleTag(serializer, null, "Byte0", null);
        XmlSerializerHelper.simpleTag(serializer, null, "Byte1",
                                      Byte.valueOf((byte) 0), false);

        serializer.endTag(null, "root");

        serializer.endDocument();

        System.out.println(new String(baos.toByteArray(), ENCODING));
    }


}

