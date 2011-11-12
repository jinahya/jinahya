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

import org.testng.Assert;
import org.testng.annotations.Test;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class XmlAccessibleTest extends XmlPullHelperTest {


    @Test
    public static void testParseAccessible()
        throws XmlPullParserException, IOException {

        {
            final XmlPullParser parser = newPullParser();

            final InputStream stream = getResourceAsStream("sample.xml");
            try {
                parser.setInput(stream, null);

                parser.nextTag();

                final Sample sample =
                    XmlPullAccessibleHelper.parse(parser, Sample.class);

                Assert.assertEquals(sample.getId(), Long.valueOf(0L));
                Assert.assertEquals(sample.getName(), "name");
                Assert.assertEquals(sample.getAge(), Integer.valueOf(19));

            } finally {
                stream.close();
            }
        }


        {
            final XmlPullParser parser = newPullParser();

            final InputStream stream = getResourceAsStream("sampleNS.xml");
            try {
                parser.setInput(stream, null);

                parser.nextTag();

                final Sample sample =
                    XmlPullAccessibleHelper.parse(parser, SampleNS.class);

                Assert.assertEquals(sample.getId(), Long.valueOf(0L));
                Assert.assertEquals(sample.getName(), "name");
                Assert.assertEquals(sample.getAge(), Integer.valueOf(19));

            } finally {
                stream.close();
            }
        }
    }


}

