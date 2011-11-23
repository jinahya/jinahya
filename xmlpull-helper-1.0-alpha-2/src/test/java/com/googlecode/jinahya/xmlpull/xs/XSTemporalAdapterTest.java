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

import java.util.Date;

import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class XSTemporalAdapterTest<T extends XSTemporalAdapter> {


    protected static final XmlPullParserFactory XML_PULL_PARSER_FACTORY;


    static {
        try {
            XML_PULL_PARSER_FACTORY = XmlPullParserFactory.newInstance();
        } catch (XmlPullParserException xppe) {
            throw new InstantiationError(xppe.getMessage());
        }
    }


    protected static <T extends XSTemporalAdapter> void testParse(
        final T adapter, final String[] strings)
        throws XmlPullParserException, IOException {

        for (String string : strings) {
            final Date parsed = adapter.parse(string);
            System.out.printf("%1$30s -> %2$s\n", string, parsed);
        }
    }


}
