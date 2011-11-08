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


import java.io.IOException;

import org.testng.annotations.Test;

import org.xmlpull.v1.XmlPullParserException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class XSDateTimeAdapterTest
extends XSTemporalAdapterTest<XSDateTimeAdapter> {


    private static final String[] XS_DATE_TIME_STRINGS = new String[]{
        "2001-10-26T21:32:52", "2001-10-26T21:32:52+02:00",
        "2001-10-26T19:32:52Z", "2001-10-26T19:32:52+00:00",
        "-2001-10-26T21:32:52", "2001-10-26T21:32:52.12679"
    };


    @Test
    public void testParse() throws XmlPullParserException, IOException {
        testParse(new XSDateTimeAdapter(), XS_DATE_TIME_STRINGS);
    }


}

