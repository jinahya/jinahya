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


import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class XmlPullParserHelperTest extends XmlPullHelperTest {


    @Test
    public static void testNextNillableText()
        throws XmlPullParserException, IOException {

        {
            final XmlPullParser parser = newPullParser();

            final String xml =
                "<test"
                + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
                + " xsi:nil=\"true\"></test>";

            parser.setInput(new ByteArrayInputStream(xml.getBytes()), null);

            parser.nextTag();

            Assert.assertNull(XmlPullParserHelper.nextNillableText(parser));
        }

        {
            final XmlPullParser parser = newPullParser();

            final String xml =
                "<test"
                + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
                + " xsi:nil=\"true\"/>";

            parser.setInput(new ByteArrayInputStream(xml.getBytes()), null);

            parser.nextTag();

            Assert.assertNull(XmlPullParserHelper.nextNillableText(parser));
        }

        {
            final XmlPullParser parser = newPullParser();
            final String xml = "<test/>";
            parser.setInput(new ByteArrayInputStream(xml.getBytes()), null);
            parser.nextTag();
            Assert.assertEquals(
                XmlPullParserHelper.nextNillableText(parser), "");
        }

        {
            final XmlPullParser parser = newPullParser();
            final String xml = "<test></test>";
            parser.setInput(new ByteArrayInputStream(xml.getBytes()), null);
            parser.nextTag();
            Assert.assertEquals(
                XmlPullParserHelper.nextNillableText(parser), "");
        }
    }


    @Test
    public static void testNextInt()
        throws XmlPullParserException, IOException {

        final XmlPullParser parser = newPullParser();

        final String xml =
            "<test"
            + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
            + " xsi:nil=\"true\">1000</test>";

        parser.setInput(new ByteArrayInputStream(xml.getBytes()), null);

        parser.nextTag();

        Assert.assertEquals(
            XmlPullParserHelper.nextInt(parser), Integer.valueOf(1000));
    }


    @Test
    public static void testNextIntWithDefaultValue()
        throws XmlPullParserException, IOException {

        {
            final XmlPullParser parser = newPullParser();
            final String xml =
                "<test"
                + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
                + " xsi:nil=\"true\"></test>";
            parser.setInput(new ByteArrayInputStream(xml.getBytes()), null);
            parser.nextTag();
            final int defaultValue = 100;
            Assert.assertEquals(
                XmlPullParserHelper.nextInt(parser, defaultValue),
                defaultValue);
        }

        {
            final XmlPullParser parser = newPullParser();
            final String xml =
                "<test"
                + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
                + " xsi:nil=\"true\"/>";
            parser.setInput(new ByteArrayInputStream(xml.getBytes()), null);
            parser.nextTag();
            final int defaultValue = 100;
            Assert.assertEquals(
                XmlPullParserHelper.nextInt(parser, defaultValue),
                defaultValue);
        }
    }


    @Test
    public static void testNextIntWithIllegalValue()
        throws XmlPullParserException, IOException {

        try {
            final XmlPullParser parser = newPullParser();
            final String xml = "<test></test>";
            parser.setInput(new ByteArrayInputStream(xml.getBytes()), null);
            parser.nextTag();
            XmlPullParserHelper.nextInt(parser);
            Assert.fail("passed: nextInt with empty string");
        } catch (NumberFormatException nfe) {
            // expected
        }

        try {
            final XmlPullParser parser = newPullParser();
            final String xml = "<test>number</test>";
            parser.setInput(new ByteArrayInputStream(xml.getBytes()), null);
            parser.nextTag();
            XmlPullParserHelper.nextInt(parser);
            Assert.fail("passed: nextInt with empty string");
        } catch (NumberFormatException nfe) {
            // expected
        }

        try {
            final XmlPullParser parser = newPullParser();
            final String xml =
                "<test"
                + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
                + " xsi:nil=\"true\"/>";
            parser.setInput(new ByteArrayInputStream(xml.getBytes()), null);
            parser.nextTag();
            Assert.assertNull(XmlPullParserHelper.nextInt(parser));
        } catch (NumberFormatException nfe) {
            // expected
        }

        try {
            final XmlPullParser parser = newPullParser();
            final String xml =
                "<test"
                + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
                + " xsi:nil=\"true\"></test>";
            parser.setInput(new ByteArrayInputStream(xml.getBytes()), null);
            parser.nextTag();
            Assert.assertNull(XmlPullParserHelper.nextInt(parser));
        } catch (NumberFormatException nfe) {
            // expected
        }

    }


}

