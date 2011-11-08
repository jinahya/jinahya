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
public final class XmlAccessibleHelper {


    /**
     * Parses a new instance.
     *
     * @param <A> accessible type parameter
     * @param parser parser
     * @param accessibleType parsable type
     * @return a new parsed instance
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public static <A extends XmlAccessible> A parse(
        final PullParser parser, final Class<A> accessibleType)
        throws XmlPullParserException, IOException {

        if (parser == null) {
            throw new NullPointerException("null parser");
        }

        if (accessibleType == null) {
            throw new NullPointerException("null elementType");
        }

        if (!XmlAccessible.class.isAssignableFrom(accessibleType)) {
            throw new IllegalArgumentException(
                accessibleType + " is not assignable to "
                + XmlAccessible.class);
        }

        try {
            final A accessible = accessibleType.newInstance();
            accessible.parse(parser);
            return accessible;
        } catch (InstantiationException ie) {
            throw new XmlPullParserException(
                "failed to create instance of " + accessibleType, parser, ie);
        } catch (IllegalAccessException iae) {
            throw new XmlPullParserException(
                "failed to create instance of " + accessibleType, parser, iae);
        }
    }


    /**
     * Creates a new instance.
     */
    private XmlAccessibleHelper() {
        super();
    }


}

