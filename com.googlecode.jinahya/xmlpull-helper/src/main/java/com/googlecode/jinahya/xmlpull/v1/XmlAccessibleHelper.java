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
public final class XmlAccessibleHelper {


    /**
     * Parses a new instance of given <code>accessibleType</code> from specified
     * <code>parser</code>.
     *
     * @param <A> accessible type parameter
     * @param parser parser
     * @param accessibleType parsable type
     * @return a new parsed instance
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public static <A extends XmlAccessible> A parse(
        final XmlPullParser parser, final Class<A> accessibleType)
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
            throw new RuntimeException(
                "failed to create instance of " + accessibleType, ie);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(
                "failed to create instance of " + accessibleType, iae);
        }
    }


    /**
     * Serializes an empty instance of given <code>accessibleType</code> to
     * specified <code>serializer</code>.
     *
     * @param <A> accessible type parameter
     * @param serializer serializer
     * @param accessibleType accessible type
     * @throws IOException if an I/O error occurs.
     */
    public static <A extends XmlAccessible> void serialize(
        final XmlSerializer serializer, final Class<A> accessibleType)
        throws IOException {

        if (serializer == null) {
            throw new NullPointerException("null serializer");
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
            accessibleType.newInstance().serialize(serializer);
        } catch (InstantiationException ie) {
            throw new RuntimeException(
                "failed to create instance of " + accessibleType, ie);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(
                "failed to create instance of " + accessibleType, iae);
        }
    }


    /**
     * Creates a new instance.
     */
    private XmlAccessibleHelper() {
        super();
    }


}

