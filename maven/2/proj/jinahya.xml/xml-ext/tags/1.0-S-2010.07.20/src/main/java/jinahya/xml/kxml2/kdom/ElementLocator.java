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
     *
     * @param file
     * @return
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
     *
     * @param url
     * @return
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
        return child(XmlPullParser.NO_NAMESPACE, name);
    }


    /**
     * Adds a new child element and locate it.
     *
     * @param namespace new element's namespace
     * @param name new element's name
     * @return self
     */
    public ElementLocator child(final String namespace, final String name) {
        current = current.child(namespace, name);

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
        return child(XmlPullParser.NO_NAMESPACE, name, index);
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
    public ElementLocator child(final String namespace, final String name,
                                final int index) {

        current = current.child(namespace, name, index);

        return this;
    }


    /**
     *
     * @param name
     * @return
     */
    public ElementLocator first(final String name) {
        return first(XmlPullParser.NO_NAMESPACE, name);
    }


    /**
     *
     * @param namespace
     * @param name
     * @return
     */
    public ElementLocator first(final String namespace, final String name) {

        current = current.first(namespace, name);

        return this;
    }


    /**
     *
     * @param name
     * @return
     */
    public ElementLocator last(final String name) {
        return last(XmlPullParser.NO_NAMESPACE, name);
    }


    /**
     *
     * @param namespace
     * @param name
     * @return
     * @throws XmlPullParserException
     */
    public ElementLocator last(final String namespace, final String name) {

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
        return count(XmlPullParser.NO_NAMESPACE, name);
    }


    /**
     * 
     * @param namespace
     * @param name
     * @return
     */
    public int count(final String namespace, final String name) {

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
        return attribute(XmlPullParser.NO_NAMESPACE, name);
    }


    /**
     * Returns an attribute's value.
     *
     * @param namespace attribute's namespace
     * @param name attribute's name
     * @return attribute's value
     * @see Element#getAttributeValue(String, String)
     */
    public String attribute(final String namespace, final String name) {

        if (namespace == null) {
            throw new IllegalArgumentException(
                "param:0:" + String.class + ": is null");
        }

        if (name == null) {
            throw new IllegalArgumentException(
                "param:1:" + String.class + ": is null");
        }

        return current.attribute(namespace, name);
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
    public ElementLocator attribute(final String namespace, final String name,
                                    final String value) {

        if (namespace == null) {
            throw new IllegalArgumentException(
                "param:0:" + String.class + ": is null");
        }

        if (name == null) {
            throw new IllegalArgumentException(
                "param:1:" + String.class + ": is null");
        }

        current.attribute(namespace, name, value);

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
