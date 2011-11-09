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

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class SamplesNS extends AbstractXmlCollectable<SampleNS> {


    public static final String LOCAL_NAME = "samples";


    public SamplesNS() {
        super(SampleNS.class);
    }


    @Override
    public void parse(final XmlPullParser parser)
        throws XmlPullParserException, IOException {

        parser.require(XmlPullParser.START_TAG, SampleNS.NAMESPACE_URI,
                       Samples.LOCAL_NAME);

        getAccessibles().clear();
        while (parser.nextTag() == XmlPullParser.START_TAG) {
            final SampleNS sample = new SampleNS();
            sample.parse(parser);
            getAccessibles().add(sample);
        }

        parser.require(XmlPullParser.END_TAG, SampleNS.NAMESPACE_URI,
                       Samples.LOCAL_NAME);
    }


    @Override
    public void serialize(final XmlSerializer serializer) throws IOException {

        throw new UnsupportedOperationException("Not supported yet.");
    }


}

