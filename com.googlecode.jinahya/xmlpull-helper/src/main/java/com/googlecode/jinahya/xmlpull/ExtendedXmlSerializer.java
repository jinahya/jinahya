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


package com.googlecode.jinahya.xmlpull;


import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import org.xmlpull.v1.XmlSerializer;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ExtendedXmlSerializer implements XmlSerializer {


    public ExtendedXmlSerializer(final XmlSerializer serializer) {
        super();

        if (serializer == null) {
            throw new NullPointerException("null serializer");
        }

        this.serializer = serializer;
    }


    /** {@inheritDoc} */
    @Override
    public ExtendedXmlSerializer attribute(final String namespace,
                                           final String name,
                                           final String value)
        throws IOException, IllegalArgumentException, IllegalStateException {

        assert serializer.equals(serializer.attribute(namespace, name, value));

        return this;
    }


    /** {@inheritDoc} */
    @Override
    public void cdsect(final String text)
        throws IOException, IllegalArgumentException, IllegalStateException {

        serializer.cdsect(text);
    }


    /** {@inheritDoc} */
    @Override
    public void comment(final String text)
        throws IOException, IllegalArgumentException, IllegalStateException {

        serializer.comment(text);
    }


    /** {@inheritDoc} */
    @Override
    public void docdecl(final String text)
        throws IOException, IllegalArgumentException, IllegalStateException {

        serializer.docdecl(text);
    }


    /** {@inheritDoc} */
    @Override
    public void endDocument()
        throws IOException, IllegalArgumentException, IllegalStateException {

        serializer.endDocument();
    }


    /** {@inheritDoc} */
    @Override
    public ExtendedXmlSerializer endTag(final String namespace,
                                        final String name)
        throws IOException, IllegalArgumentException, IllegalStateException {

        assert serializer.equals(serializer.endTag(namespace, name));

        return this;
    }


    /** {@inheritDoc} */
    @Override
    public void entityRef(final String text)
        throws IOException, IllegalArgumentException, IllegalStateException {

        serializer.entityRef(text);
    }


    /** {@inheritDoc} */
    @Override
    public void flush() throws IOException {
        serializer.flush();
    }


    /** {@inheritDoc} */
    @Override
    public int getDepth() {
        return serializer.getDepth();
    }


    /** {@inheritDoc} */
    @Override
    public boolean getFeature(String name) {
        return serializer.getFeature(name);
    }


    /** {@inheritDoc} */
    @Override
    public String getName() {
        return serializer.getName();
    }


    /** {@inheritDoc} */
    @Override
    public String getNamespace() {
        return serializer.getNamespace();
    }


    /** {@inheritDoc} */
    @Override
    public String getPrefix(final String namespace,
                            final boolean generatePrefix)
        throws IllegalArgumentException {

        return serializer.getPrefix(namespace, generatePrefix);
    }


    /** {@inheritDoc} */
    @Override
    public Object getProperty(final String name) {
        return serializer.getProperty(name);
    }


    /** {@inheritDoc} */
    @Override
    public void ignorableWhitespace(final String text)
        throws IOException, IllegalArgumentException, IllegalStateException {

        serializer.ignorableWhitespace(text);
    }


    /** {@inheritDoc} */
    @Override
    public void processingInstruction(final String text)
        throws IOException, IllegalArgumentException, IllegalStateException {

        serializer.processingInstruction(text);
    }


    /** {@inheritDoc} */
    @Override
    public void setFeature(final String name, final boolean state)
        throws IllegalArgumentException, IllegalStateException {

        serializer.setFeature(name, state);
    }


    /** {@inheritDoc} */
    @Override
    public void setOutput(final OutputStream os, final String encoding)
        throws IOException, IllegalArgumentException, IllegalStateException {

        serializer.setOutput(os, encoding);
    }


    /** {@inheritDoc} */
    @Override
    public void setOutput(final Writer writer)
        throws IOException, IllegalArgumentException, IllegalStateException {

        serializer.setOutput(writer);
    }


    /** {@inheritDoc} */
    @Override
    public void setPrefix(String prefix, String namespace)
        throws IOException, IllegalArgumentException, IllegalStateException {

        serializer.setPrefix(prefix, namespace);
    }


    /** {@inheritDoc} */
    @Override
    public void setProperty(String name, Object value)
        throws IllegalArgumentException, IllegalStateException {

        serializer.setProperty(name, value);
    }


    /** {@inheritDoc} */
    @Override
    public void startDocument(final String encoding, final Boolean standalone)
        throws IOException, IllegalArgumentException, IllegalStateException {

        serializer.startDocument(encoding, standalone);
    }


    /** {@inheritDoc} */
    @Override
    public ExtendedXmlSerializer startTag(final String namespace,
                                          final String name)
        throws IOException, IllegalArgumentException, IllegalStateException {

        assert serializer.equals(serializer.startTag(namespace, name));
        return this;
    }


    /** {@inheritDoc} */
    @Override
    public ExtendedXmlSerializer text(final String text)
        throws IOException, IllegalArgumentException, IllegalStateException {

        assert serializer.equals(serializer.text(text));
        return this;
    }


    /** {@inheritDoc} */
    @Override
    public ExtendedXmlSerializer text(final char[] buf, final int start,
                                      final int len)
        throws IOException, IllegalArgumentException, IllegalStateException {

        assert serializer.equals(serializer.text(buf, start, len));
        return this;
    }


    /** impl. */
    private final XmlSerializer serializer;


}

