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

import java.util.Collection;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class Samples extends AbstractXmlCollectable<Sample> {


    public static final String LOCAL_NAME = "samples";


    /**
     * Creates a new instance.
     */
    public Samples() {
        super(Sample.class);
    }


    @Override
    public void parse(final PullParser parser)
        throws XmlPullParserException, IOException {

        parser.require(XmlPullParser.START_TAG, null, LOCAL_NAME);

        getParsable().clear();
        while (parser.nextTag() == XmlPullParser.START_TAG) {
            final Sample parsable = new Sample();
            parsable.parse(parser);
            getParsable().add(parsable);
        }

        parser.require(XmlPullParser.END_TAG, null, LOCAL_NAME);
    }


    @Override
    public void serialize(final PushSerializer serializer)
        throws XmlPullParserException, IOException {

        throw new UnsupportedOperationException("Not supported yet.");
    }


    public Collection<Sample> getParsable() {
        return getAccessibles();
    }


}

