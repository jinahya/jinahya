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


import org.xmlpull.v1.XmlPullParserException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public final class PullParsableHelper {


    /**
     * Parses a new instance.
     *
     * @param <T> parsable type parameter
     * @param parser parser
     * @param parsableType parsable type
     * @return a new parsed instance
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public static <T extends PullParsable> T parseInstance(
        final PullParser parser, final Class<T> parsableType)
        throws XmlPullParserException, IOException {

        if (parser == null) {
            throw new NullPointerException("null parser");
        }

        if (parsableType == null) {
            throw new NullPointerException("null parsableType");
        }

        if (!PullParsable.class.isAssignableFrom(parsableType)) {
            throw new IllegalArgumentException(
                parsableType + " is not assignable to " + PullParsable.class);
        }

        try {
            final T parsable = parsableType.newInstance();
            parsable.parse(parser);
            return parsable;
        } catch (InstantiationException ie) {
            throw new XmlPullParserException(
                "failed to create instance of " + parsableType, parser, ie);
        } catch (IllegalAccessException iae) {
            throw new XmlPullParserException(
                "failed to create instance of " + parsableType, parser, iae);
        }
    }


    /**
     * Creates a new instance.
     */
    private PullParsableHelper() {
        super();
    }


}

