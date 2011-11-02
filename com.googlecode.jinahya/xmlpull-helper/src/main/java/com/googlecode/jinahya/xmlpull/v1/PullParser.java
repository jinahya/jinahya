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
import java.io.InputStream;
import java.io.Reader;

import java.util.Date;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public final class PullParser implements XmlPullParser {


    /**
     * Creates a new instance.
     *
     * @param parser parser implementation
     */
    public PullParser(final XmlPullParser parser) {
        super();

        if (parser == null) {
            throw new NullPointerException("null parser");
        }

        this.parser = parser;
    }


    /** {@inheritDoc}. */
    @Override
    public void defineEntityReplacementText(final String entityName,
                                            final String replacementText)
        throws XmlPullParserException {

        parser.defineEntityReplacementText(entityName, replacementText);
    }


    /** {@inheritDoc}. */
    @Override
    public int getAttributeCount() {
        return parser.getAttributeCount();
    }


    /** {@inheritDoc}. */
    @Override
    public String getAttributeName(final int index) {
        return parser.getAttributeName(index);
    }


    /** {@inheritDoc}. */
    @Override
    public String getAttributeNamespace(final int index) {
        return parser.getAttributeNamespace(index);
    }


    /** {@inheritDoc}. */
    @Override
    public String getAttributePrefix(final int index) {
        return parser.getAttributePrefix(index);
    }


    /** {@inheritDoc}. */
    @Override
    public String getAttributeType(final int index) {
        return parser.getAttributeType(index);
    }


    /** {@inheritDoc}. */
    @Override
    public String getAttributeValue(final int index) {
        return parser.getAttributeValue(index);
    }


    /** {@inheritDoc}. */
    @Override
    public String getAttributeValue(String namespace, String name) {
        return parser.getAttributeValue(namespace, name);
    }


    /** {@inheritDoc}. */
    @Override
    public int getColumnNumber() {
        return parser.getColumnNumber();
    }


    /** {@inheritDoc}. */
    @Override
    public int getDepth() {
        return parser.getDepth();
    }


    /** {@inheritDoc}. */
    @Override
    public int getEventType() throws XmlPullParserException {
        return parser.getEventType();
    }


    /** {@inheritDoc}. */
    @Override
    public boolean getFeature(String name) {
        return parser.getFeature(name);
    }


    /** {@inheritDoc}. */
    @Override
    public String getInputEncoding() {
        return parser.getInputEncoding();
    }


    /** {@inheritDoc}. */
    @Override
    public int getLineNumber() {
        return parser.getLineNumber();
    }


    /** {@inheritDoc}. */
    @Override
    public String getName() {
        return parser.getName();
    }


    /** {@inheritDoc}. */
    @Override
    public String getNamespace(String prefix) {
        return parser.getNamespace(prefix);
    }


    /** {@inheritDoc}. */
    @Override
    public String getNamespace() {
        return parser.getNamespace();
    }


    /** {@inheritDoc}. */
    @Override
    public int getNamespaceCount(final int depth)
        throws XmlPullParserException {

        return parser.getNamespaceCount(depth);
    }


    /** {@inheritDoc}. */
    @Override
    public String getNamespacePrefix(final int pos)
        throws XmlPullParserException {

        return parser.getNamespacePrefix(pos);
    }


    /** {@inheritDoc}. */
    @Override
    public String getNamespaceUri(final int pos) throws XmlPullParserException {
        return parser.getNamespaceUri(pos);
    }


    /** {@inheritDoc}. */
    @Override
    public String getPositionDescription() {
        return parser.getPositionDescription();
    }


    /** {@inheritDoc}. */
    @Override
    public String getPrefix() {
        return parser.getPrefix();
    }


    /** {@inheritDoc}. */
    @Override
    public Object getProperty(final String name) {
        return parser.getProperty(name);
    }


    /** {@inheritDoc}. */
    @Override
    public String getText() {
        return parser.getText();
    }


    /** {@inheritDoc}. */
    @Override
    public char[] getTextCharacters(final int[] holderForStartAndLength) {
        return parser.getTextCharacters(holderForStartAndLength);
    }


    /** {@inheritDoc}. */
    @Override
    public boolean isAttributeDefault(final int index) {
        return parser.isAttributeDefault(index);
    }


    /** {@inheritDoc}. */
    @Override
    public boolean isEmptyElementTag() throws XmlPullParserException {
        return parser.isEmptyElementTag();
    }


    /** {@inheritDoc}. */
    @Override
    public boolean isWhitespace() throws XmlPullParserException {
        return parser.isWhitespace();
    }


    /** {@inheritDoc}. */
    @Override
    public int next() throws XmlPullParserException, IOException {
        return parser.next();
    }


    /** {@inheritDoc}. */
    @Override
    public int nextTag() throws XmlPullParserException, IOException {
        return parser.nextTag();
    }


    /** {@inheritDoc}. */
    @Override
    public String nextText() throws XmlPullParserException, IOException {
        return parser.nextText();
    }


    /** {@inheritDoc}. */
    @Override
    public int nextToken() throws XmlPullParserException, IOException {
        return parser.nextToken();
    }


    /** {@inheritDoc}. */
    @Override
    public void require(final int tyep, final String namespace,
                        final String name)
        throws XmlPullParserException, IOException {

        parser.require(tyep, namespace, name);
    }


    /** {@inheritDoc}. */
    @Override
    public void setFeature(final String name, final boolean state)
        throws XmlPullParserException {

        parser.setFeature(name, state);
    }


    /** {@inheritDoc}. */
    @Override
    public void setInput(final Reader in) throws XmlPullParserException {
        parser.setInput(in);
    }


    /** {@inheritDoc}. */
    @Override
    public void setInput(final InputStream inputStream,
                         final String inputEncoding)
        throws XmlPullParserException {

        parser.setInput(inputStream, inputEncoding);
    }


    /** {@inheritDoc}. */
    @Override
    public void setProperty(final String name, final Object value)
        throws XmlPullParserException {

        parser.setProperty(name, value);
    }


    /**
     * Parses an attribute and returns as a Date.
     *
     * @param namespace namespace
     * @param name name
     * @return the parsed Date or null it attribute not found
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs
     */
    public Date getXSDateAttribute(final String namespace, final String name)
        throws XmlPullParserException, IOException {

        return XmlPullParserHelper.getXSDateAttribute(parser, namespace, name);
    }


    /**
     * Parses an <code>xs:time</code> attribute and returns as a Date.
     *
     * @param namespace namespace
     * @param name name
     * @return the parsed Date or null if no attribute found.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public Date getXSTimeAttribute(final String namespace, final String name)
        throws XmlPullParserException, IOException {

        return XmlPullParserHelper.getXSTimeAttribute(parser, namespace, name);
    }


    /**
     * Parses an <code>xs:dateTime</code> attribute and returns as Date.
     *
     * @param namespace namespace
     * @param name name
     * @return the parsed Date or null if no attribute found
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public Date getXSDateTimeAttribute(final String namespace,
                                       final String name)
        throws XmlPullParserException, IOException {

        return XmlPullParserHelper.getXSDateTimeAttribute(
            parser, namespace, name);
    }


    /**
     * Parsed the <code>nextText()</code> as an <code>xs:date</code>.
     *
     * @return parsed Date or null if the tag is empty.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public Date nextXSDate() throws XmlPullParserException, IOException {

        return XmlPullParserHelper.nextXSDate(parser);
    }


    /**
     * Parses the <code>nextText()</code> as an <code>xs:time</code>.
     *
     * @return the parsed Date or null if the tag is empty.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public Date nextXSTime() throws XmlPullParserException, IOException {

        return XmlPullParserHelper.nextXSTime(parser);
    }


    /**
     * Parses the <code>nextText</code> as an <code>xs:dateTime</code>.
     *
     * @return the parsed Date or null if the tag is empty
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public Date nextXSDateTime() throws XmlPullParserException, IOException {

        return XmlPullParserHelper.nextXSDateTime(parser);
    }


    /**
     * Returns an attribute as an Integer.
     *
     * @param namespace namespace
     * @param name name
     * @return the parsed Integer or null if attribute not found.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public Integer getIntAttribute(final String namespace, final String name)
        throws XmlPullParserException, IOException {

        return XmlPullParserHelper.getIntAttribute(parser, namespace, name);
    }


    /**
     * Parses an attribute as a Long.
     *
     * @param namespace namespace
     * @param name name
     * @return the parsed Long value or null if no attribute found
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public Long getLongAttribute(final String namespace, final String name)
        throws XmlPullParserException, IOException {

        return XmlPullParserHelper.getLongAttribute(parser, namespace, name);
    }


    /**
     * Parses an attribute as a Float.
     *
     * @param namespace namespace
     * @param name name
     * @return the parsed Float value or null if no attribute found
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public Float getFloatAttribute(final String namespace, final String name)
        throws XmlPullParserException, IOException {

        return XmlPullParserHelper.getFloatAttribute(parser, namespace, name);
    }


    /**
     * Parses an attribute as a Double.
     *
     * @param namespace namespace
     * @param name name
     * @return the parsed Double value or null if no attribute found
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public Double getDoubleAttribute(final String namespace, final String name)
        throws XmlPullParserException, IOException {

        return XmlPullParserHelper.getDoubleAttribute(parser, namespace, name);
    }


    /**
     * Parses <code>nextText</code>.
     *
     * @return <code>nextText</code> value or null if the tag is empty.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public String nextNillable() throws XmlPullParserException, IOException {

        return XmlPullParserHelper.nextNillable(parser);
    }


    /**
     * Parses <code>nextText</code> to an Integer.
     *
     * @return parsed Integer value or null it empty tag.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public Integer nextInt() throws XmlPullParserException, IOException {

        return XmlPullParserHelper.nextInt(parser);
    }


    /**
     * Parses <code>nextText</code> to a Long.
     *
     * @return parsed Long value or null if empty tag.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public Long nextLong() throws XmlPullParserException, IOException {

        return XmlPullParserHelper.nextLong(parser);
    }


    /**
     * Parses <code>nextText</code> to a Float.
     *
     * @return parsed Float value or null if empty tag.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public Float nextFloat() throws XmlPullParserException, IOException {

        return XmlPullParserHelper.nextFloat(parser);
    }


    /**
     * Parses <code>nextText</code> to a Double.
     *
     * @return parsed Long value or null if empty tag.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public Double nextDouble() throws XmlPullParserException, IOException {

        return XmlPullParserHelper.nextDouble(parser);
    }


    /**
     * parser implementation.
     */
    private final XmlPullParser parser;


}

