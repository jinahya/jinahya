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


package com.googlecode.jinahya.xmlpull;


import static com.googlecode.jinahya.xmlpull.XmlPullParserHelper.*;

import java.text.ParseException;

import org.testng.annotations.Test;

import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class XmlPullParserHelperTest {


    private static final XmlPullParserFactory XML_PULL_PARSER_FACTORY;


    static {
        try {
            XML_PULL_PARSER_FACTORY = XmlPullParserFactory.newInstance();
        } catch (XmlPullParserException xppe) {
            throw new InstantiationError(xppe.getMessage());
        }
    }


    private static final String[] XS_DATE_STRINGS = new String[]{
        "2001-10-26", "2001-10-26+02:00", "2001-10-26Z", "2001-10-26+00:00",
        "-2001-10-26", "-20000-04-01"
    };


    @Test
    public void testParseXSDate() throws ParseException {

        String parsed = null;

        for (String xsDateExample : XS_DATE_STRINGS) {
            try {
                parsed = parseXSDate(xsDateExample).toString();
            } catch (ParseException pe) {
                parsed = pe.getMessage();
            }

            System.out.printf("%1$20s -> %2$s\n", xsDateExample, parsed);
        }
    }


    private static final String[] XS_TIME_STRINGS = new String[]{
        "21:32:52", "21:32:52+02:00", "19:32:52Z", "19:32:52+00:00",
        "21:32:52.12679"
    };


    @Test
    public void testParseXSTime() throws ParseException {

        String parsed = null;

        for (String xsTimeString : XS_TIME_STRINGS) {
            try {
                parsed = parseXSTime(xsTimeString).toString();
            } catch (ParseException pe) {
                parsed = pe.getMessage();
            }

            System.out.printf("%1$20s -> %2$s\n", xsTimeString, parsed);
        }
    }


    private static final String[] XS_DATE_TIME_STRINGS = new String[]{
        "2001-10-26T21:32:52", "2001-10-26T21:32:52+02:00",
        "2001-10-26T19:32:52Z", "2001-10-26T19:32:52+00:00",
        "-2001-10-26T21:32:52", "2001-10-26T21:32:52.12679"
    };


    @Test
    public void testParseXSDateTime() throws ParseException {

        String parsed = null;

        for (String xsDateTimeString : XS_DATE_TIME_STRINGS) {
            try {
                parsed = parseXSDateTime(xsDateTimeString).toString();
            } catch (ParseException pe) {
                parsed = pe.getMessage();
            }

            System.out.printf("%1$30s -> %2$s\n", xsDateTimeString, parsed);
        }
    }


}

