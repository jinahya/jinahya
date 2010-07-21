/*
 *  Copyright 2010 Jin Kwon.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package jinahya.xml.kxml2.kdom;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;

import java.net.URL;

import org.kxml2.io.KXmlParser;
import org.kxml2.io.KXmlSerializer;
import org.kxml2.kdom.Document;
import org.kxml2.kdom.Element;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;
import org.xmlpull.v1.XmlPullParserException;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ElementLocator {


    /**
     * Creates a new instance.
     *
     * @param file xml file
     * @return a new instance
     */
    public static ElementLocator newInstance(final File file)
        throws XmlPullParserException, IOException {

        if (file == null) {
            throw new IllegalArgumentException(
                "param:0:" + File.class + ": is null");
        }

        final InputStream in = new FileInputStream(file);
        try {
            return newInstance(in, null);
        } finally {
            in.close();
        }
    }


    /**
     * Creates a new instance.
     *
     * @param url xml url
     * @return a new instance.
     */
    public static ElementLocator newInstance(final URL url)
        throws XmlPullParserException, IOException {

        if (url == null) {
            throw new IllegalArgumentException(
                "param:0:" + URL.class + ": is null");
        }

        return newInstance(url.openStream(), null);
    }


    /**
     *
     * @param in
     * @param enc
     * @return
     * @throws XmlPullParserException
     * @throws IOException
     * @see org.xmlpull.v1.XmlPullParser#setInput(java.io.InputStream, String)
     */
    public static ElementLocator newInstance(final InputStream in,
                                             final String enc)
        throws XmlPullParserException, IOException {

        if (in == null) {
            throw new IllegalArgumentException(
                "param:0:" + InputStream.class + ": is null");
        }

        if (enc != null) {
            return newInstance(new InputStreamReader(in, enc));
        }

        final XmlPullParser parser = new KXmlParser();
        parser.setInput(in, enc);

        final Document document = new Document();
        document.parse(parser);

        return new ElementLocator(document);
    }


    /**
     *
     * @param in
     * @return
     * @throws XmlPullParserException
     * @throws IOException
     * @see org.xmlpull.v1.XmlPullParser#setInput(java.io.Reader)
     */
    public static ElementLocator newInstance(final Reader in)
        throws XmlPullParserException, IOException {

        if (in == null) {
            throw new IllegalArgumentException(
                "param:0:" + Reader.class + ": is null");
        }

        final XmlPullParser parser = new KXmlParser();
        parser.setInput(in);

        final Document document = new Document();
        document.parse(parser);

        return new ElementLocator(document);
    }


    /**
     * Creates a new instance. An <code>IllegalArgumentException</code> will be
     * thrown if specified <code>document</code> is null.
     *
     * @param document document instance
     */
    public ElementLocator(final Document document) {

        super();

        if (document == null) {
            throw new IllegalArgumentException(
                "param:0:" + Document.class + ": is null");
        }

        current = LinkedElement.newInstance(document);
    }


    /**
     * Adds a new child element with no namespace.
     *
     * @param name new element's name
     * @return self
     * @see #child(String, String)
     */
    public ElementLocator child(final String name) {
        return childNS(XmlPullParser.NO_NAMESPACE, name);
    }


    /**
     * Adds a new child element and locate it.
     *
     * @param namespace new element's namespace
     * @param name new element's name
     * @return self
     */
    public ElementLocator childNS(final String namespace, final String name) {
        current = current.childNS(namespace, name);

        return this;
    }


    /**
     * Locates a child element with no namespace.
     *
     * @param name child element's name
     * @param index child element's index
     * @return self
     * @see #child(String, String, int)
     */
    public ElementLocator child(final String name, final int index) {
        return childNS(XmlPullParser.NO_NAMESPACE, name, index);
    }


    /**
     * Locates child element by given conditions.
     *
     * @param namespace child element's namespace
     * @param name child element's name
     * @param index child element's index
     * @return self
     * @throws ArrayIndexOutOfBoundsException
     * @see LinkedElement#child(String, String, int)
     */
    public ElementLocator childNS(final String namespace, final String name,
                                  final int index) {

        current = current.childNS(namespace, name, index);

        return this;
    }


    /**
     *
     * @param name
     * @return
     * @throws ArrayIndexOutOfBoundsException if there is not child elements.
     */
    public ElementLocator first(final String name) {
        return firstNS(XmlPullParser.NO_NAMESPACE, name);
    }


    /**
     *
     * @param namespace
     * @param name
     * @return
     * @throws ArrayIndexOutOfBoundsException if there is not child elements.
     */
    public ElementLocator firstNS(final String namespace, final String name) {

        current = current.first(namespace, name);

        return this;
    }


    /**
     *
     * @param name
     * @return
     * @throws ArrayIndexOutOfBoundsException if there is not child elements.
     */
    public ElementLocator last(final String name) {
        return lastNS(XmlPullParser.NO_NAMESPACE, name);
    }


    /**
     *
     * @param namespace
     * @param name
     * @return
     * @throws ArrayIndexOutOfBoundsException if there is not child elements.
     */
    public ElementLocator lastNS(final String namespace, final String name) {

        current = current.last(namespace, name);

        return this;
    }


    /**
     *
     * @return self
     */
    public ElementLocator root() {

        current = current.root();

        return this;
    }


    /**
     *
     * @return
     * @throws IllegalStateException if there is no parent
     */
    public ElementLocator parent() {

        current = current.parent();

        return this;
    }


    /**
     * 
     * @param namespace
     * @param name
     * @return
     * @see #count(String, String)
     */
    public int count(final String name) {
        return countNS(XmlPullParser.NO_NAMESPACE, name);
    }


    /**
     * 
     * @param namespace
     * @param name
     * @return
     */
    public int countNS(final String namespace, final String name) {

        if (namespace == null) {
            throw new IllegalArgumentException(
                "param:0:" + String.class + ": is null");
        }

        if (name == null) {
            throw new IllegalArgumentException(
                "param:1:" + String.class + ": is null");
        }

        return current.count(namespace, name);
    }


    /**
     * Returns an attribute's value.
     *
     * @param namespace attribute's namespace
     * @param name attribute's name
     * @return attribute's value
     * @see Element#getAttributeValue(String, String)
     */
    public String attribute(final String name) {
        return attributeNS(XmlPullParser.NO_NAMESPACE, name);
    }


    /**
     * Returns an attribute's value.
     *
     * @param namespace attribute's namespace
     * @param name attribute's name
     * @return attribute's value
     * @see Element#getAttributeValue(String, String)
     */
    public String attributeNS(final String namespace, final String name) {

        if (namespace == null) {
            throw new IllegalArgumentException(
                "param:0:" + String.class + ": is null");
        }

        if (name == null) {
            throw new IllegalArgumentException(
                "param:1:" + String.class + ": is null");
        }

        return current.attributeNS(namespace, name);
    }


    public ElementLocator attribute(final String name, final String value) {

        return attributeNS(XmlPullParser.NO_NAMESPACE, name, value);
    }


    /**
     * Sets new attribute value.
     *
     * @param namespace attribute's namespace
     * @param name attribute's name
     * @param value attribute's new value or null.
     * @return self
     * @see org.kxml2.kdom.Element#setAttribute(String, String, String)
     */
    public ElementLocator attributeNS(final String namespace, final String name,
                                      final String value) {

        if (namespace == null) {
            throw new IllegalArgumentException(
                "param:0:" + String.class + ": is null");
        }

        if (name == null) {
            throw new IllegalArgumentException(
                "param:1:" + String.class + ": is null");
        }

        current.attributeNS(namespace, name, value);

        return this;
    }


    /**
     * Returns text value.
     *
     * @param appendIgnorableWhiteSpaces
     * @return current element's text value or null if there is no text nodes.
     */
    public String text() {
        return current.text();
    }


    /**
     *
     * @param buffer
     * @return
     */
    public ElementLocator text(final StringBuffer buffer) {
        current.text(buffer);

        return this;
    }


    /**
     * Removes all child elements and set text. Calling this method with null
     * value will just make the current element empty.
     *
     * @return self
     * @param text text value
     */
    public ElementLocator text(final String text) {
        current.text(text);

        return this;
    }


    /**
     * Removes current element from its parent and locate to the parent.
     *
     * @return self
     * @throws XmlPullParserException if currently at root.
     */
    public ElementLocator remove() {

        current = current.remove();

        return this;
    }


    /**
     * Returns the document instance.
     *
     * @return the document instance or null
     */
    public Document document() {
        return current.document();
    }


    public String namespace() {
        return current.namespace();
    }

    public String name() {
        return current.name();
    }


    public void require(final String name) {

        if (name == null) {
            throw new IllegalArgumentException(
                "param:0:" + String.class + ": is null");
        }

        requireNS(XmlPullParser.NO_NAMESPACE, name);
    }


    /**
     * Checks if current location matches with specified URI and local name.
     *
     * @param namespace namespace
     * @param name name
     * @throws IllegalArgumentException either one of arguments is null
     * @throws RuntimeException if not matches
     */
    public void requireNS(final String namespace, final String name) {

        if (namespace == null) {
            throw new IllegalArgumentException(
                "param:0:" + String.class + ": is null");
        }

        if (name == null) {
            throw new IllegalArgumentException(
                "param:1:" + String.class + ": is null");
        }

        current.requireNS(namespace, name);
    }


    /**
     *
     * @param out
     * @throws IOException
     */
    public void print(final Writer out) throws IOException {

        if (out == null) {
            throw new IllegalArgumentException(
                "param:0:" + Writer.class + ": is null");
        }

        final XmlSerializer serializer = new KXmlSerializer();
        serializer.setOutput(out);

        document().write(serializer);
    }


    private LinkedElement current;
}
