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


package com.googlecode.jinahya.xmlpull.xs;


import org.testng.Assert;
import org.testng.annotations.Test;

import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class XSBooleanAdapterTest {


    private static final XmlPullParserFactory XML_PULL_PARSER_FACTORY;


    static {
        try {
            XML_PULL_PARSER_FACTORY = XmlPullParserFactory.newInstance();
        } catch (XmlPullParserException xppe) {
            throw new InstantiationError(xppe.getMessage());
        }
    }


    @Test
    public void testParseXSBoolean() throws XmlPullParserException {

        try {
            XSBooleanAdapter.parseXSBoolean(null);
            Assert.fail("passed: parseXSBoolean(null)");
        } catch (NullPointerException npe) {
            // expected
        }

        Assert.assertTrue(XSBooleanAdapter.parseXSBoolean("true"));
        Assert.assertTrue(XSBooleanAdapter.parseXSBoolean("1"));

        Assert.assertFalse(XSBooleanAdapter.parseXSBoolean("false"));
        Assert.assertFalse(XSBooleanAdapter.parseXSBoolean("0"));

        testXSBooleanWithIllegalValues("True");

        testXSBooleanWithIllegalValues("TRUE");

        testXSBooleanWithIllegalValues("False");
        testXSBooleanWithIllegalValues("FALSE");

        testXSBooleanWithIllegalValues("-1");
        testXSBooleanWithIllegalValues("2");
        testXSBooleanWithIllegalValues("3");
    }


    private void testXSBooleanWithIllegalValues(final String illegalValue) {
        try {
            XSBooleanAdapter.parseXSBoolean(illegalValue);
            Assert.fail("passed: parseXSBoolean(\""
                        + String.valueOf(illegalValue) + "\"");
        } catch (XmlPullParserException xppe) {
            // expected
        }
    }


}

