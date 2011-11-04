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


import java.io.IOException;
import java.io.InputStream;

import org.testng.annotations.Test;

import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class PullParsableCollectionImpl1Test {


    @Test
    public void testParse() throws XmlPullParserException, IOException {

        final XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

        final PullParser parser = new PullParser(factory.newPullParser());

        final InputStream input =
            PullParsableCollectionImpl1Test.class.getResourceAsStream(
            "collection1.xml");
        try {
            parser.setInput(input, null);
            parser.nextTag();

            final PullParsableCollectionImpl1 collection =
                new PullParsableCollectionImpl1();

            collection.parse(parser);

            for (PullParsableImpl1 parsable : collection.getParsable()) {
                System.out.println(parsable);
            }
        } finally {
            input.close();
        }
    }


}

