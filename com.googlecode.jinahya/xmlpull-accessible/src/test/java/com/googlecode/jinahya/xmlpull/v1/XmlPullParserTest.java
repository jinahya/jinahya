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

import org.testng.annotations.Test;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class XmlPullParserTest extends XmlPullHelperTest {


    @Test
    public void testNextText() throws XmlPullParserException, IOException {

        {
            final XmlPullParser parser = newPullParser();

            parser.setInput(
                new ByteArrayInputStream("<test></test>".getBytes()), null);

            parser.nextTag();

            System.out.println("[" + parser.nextText() + "]");
        }

        {
            final XmlPullParser parser = newPullParser();

            parser.setInput(
                new ByteArrayInputStream("<test/>".getBytes()), null);

            parser.nextTag();

            System.out.println("[" + parser.nextText() + "]");
        }
        
        {
            final XmlPullParser parser = newPullParser();

            parser.setInput(
                new ByteArrayInputStream("<test>  </test>".getBytes()), null);

            parser.nextTag();

            System.out.println("[" + parser.nextText() + "]");
        }
    }


}

