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
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public final class XmlCollectableHelper {


    /**
     * Parses a collectable instance.
     *
     * @param <C> collectable type parameter
     * @param parser parser
     * @param collectableType collectable type
     * @param namespaceURI XML namespace URI
     * @param localName XML local name
     * @return collectable instance
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public static <C extends XmlCollectable<?>> C parse(
        final XmlPullParser parser, final Class<C> collectableType,
        final String namespaceURI, final String localName)
        throws XmlPullParserException, IOException {

        if (parser == null) {
            throw new NullPointerException("null parser");
        }

        if (collectableType == null) {
            throw new NullPointerException("null collectableType");
        }

        if (!XmlCollectable.class.isAssignableFrom(collectableType)) {
            throw new IllegalArgumentException(
                collectableType + " is not assignable to "
                + XmlCollectable.class);
        }

        parser.require(XmlPullParser.START_TAG, namespaceURI, localName);

        final C collectable;
        try {
            collectable = collectableType.newInstance();
        } catch (InstantiationException ie) {
            throw new RuntimeException(
                "failed to create instance of " + collectableType, ie);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(
                "failed to create instance of " + collectableType, iae);
        }

        return parse(parser, collectable, namespaceURI, localName);
    }


    /**
     * 
     * @param <C>
     * @param parser
     * @param collectable
     * @param namespaceURI
     * @param localName
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public static <C extends XmlCollectable<?>> C parse(
        final XmlPullParser parser, final C collectable,
        final String namespaceURI, final String localName)
        throws XmlPullParserException, IOException {

        if (parser == null) {
            throw new NullPointerException("null parser");
        }

        if (collectable == null) {
            throw new NullPointerException("null collectable");
        }

        final Collection<XmlAccessible> accessibles =
            (Collection<XmlAccessible>) collectable.getAccessibles();

        parser.require(XmlPullParser.START_TAG, namespaceURI, localName);

        while (parser.nextTag() != XmlPullParser.END_TAG) {
            final XmlAccessible accessible = XmlAccessibleHelper.parse(
                parser, collectable.getAccessibleType());
            accessibles.add(accessible);
        }

        parser.require(XmlPullParser.END_TAG, namespaceURI, localName);

        return collectable;
    }


    /**
     * 
     * @param <C>
     * @param serializer
     * @param collectableType
     * @param namespaceURI
     * @param localName
     * @throws IOException 
     */
    public static <C extends XmlCollectable<?>> void serialize(
        final XmlSerializer serializer, final Class<C> collectableType,
        final String namespaceURI, final String localName)
        throws IOException {

        if (serializer == null) {
            throw new NullPointerException("null serializer");
        }

        if (collectableType == null) {
            throw new NullPointerException("null collectableType");
        }

        final C collectable;
        try {
            collectable = collectableType.newInstance();
        } catch (InstantiationException ie) {
            throw new RuntimeException(
                "failed to create instance of " + collectableType, ie);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(
                "failed to create instance of " + collectableType, iae);
        }

        serialize(serializer, collectable, namespaceURI, localName);
    }


    /**
     * 
     * @param <C>
     * @param serializer
     * @param collectable
     * @param namespaceURI
     * @param localName
     * @throws IOException 
     */
    public static <C extends XmlCollectable<?>> void serialize(
        final XmlSerializer serializer, final C collectable,
        final String namespaceURI, final String localName)
        throws IOException {

        if (serializer == null) {
            throw new NullPointerException("null serializer");
        }

        if (collectable == null) {
            throw new NullPointerException("null collectable");
        }

        serializer.startTag(namespaceURI, localName);

        final Collection<? extends XmlAccessible> accessibles =
            collectable.getAccessibles();
        final Iterator<? extends XmlAccessible> iterator =
            accessibles.iterator();
        while (iterator.hasNext()) {
            final XmlAccessible accessible = iterator.next();
            accessible.serialize(serializer);
        }

        serializer.endTag(namespaceURI, localName);
    }


    /**
     * Creates a new instance.
     */
    private XmlCollectableHelper() {
        super();
    }


}

