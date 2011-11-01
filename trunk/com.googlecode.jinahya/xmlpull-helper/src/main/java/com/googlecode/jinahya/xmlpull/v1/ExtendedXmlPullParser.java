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

import java.text.ParseException;

import java.util.Date;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public final class ExtendedXmlPullParser implements XmlPullParser {


    /**
     * Creates a new instance.
     *
     * @param parser parser implementation
     */
    public ExtendedXmlPullParser(final XmlPullParser parser) {
        super();

        if (parser == null) {
            throw new NullPointerException("null parser");
        }

        this.parser = parser;
    }


    /** {@inheritDoc}. */
    public void defineEntityReplacementText(final String entityName,
                                            final String replacementText)
        throws XmlPullParserException {

        parser.defineEntityReplacementText(entityName, replacementText);
    }


    /** {@inheritDoc}. */
    public int getAttributeCount() {
        return parser.getAttributeCount();
    }


    /** {@inheritDoc}. */
    public String getAttributeName(final int index) {
        return parser.getAttributeName(index);
    }


    /** {@inheritDoc}. */
    public String getAttributeNamespace(final int index) {
        return parser.getAttributeNamespace(index);
    }


    /** {@inheritDoc}. */
    public String getAttributePrefix(final int index) {
        return parser.getAttributePrefix(index);
    }


    /** {@inheritDoc}. */
    public String getAttributeType(final int index) {
        return parser.getAttributeType(index);
    }


    /** {@inheritDoc}. */
    public String getAttributeValue(final int index) {
        return parser.getAttributeValue(index);
    }


    /** {@inheritDoc}. */
    public String getAttributeValue(String namespace, String name) {
        return parser.getAttributeValue(namespace, name);
    }


    /** {@inheritDoc}. */
    public int getColumnNumber() {
        return parser.getColumnNumber();
    }


    /** {@inheritDoc}. */
    public int getDepth() {
        return parser.getDepth();
    }


    /** {@inheritDoc}. */
    public int getEventType() throws XmlPullParserException {
        return parser.getEventType();
    }


    /** {@inheritDoc}. */
    public boolean getFeature(String name) {
        return parser.getFeature(name);
    }


    /** {@inheritDoc}. */
    public String getInputEncoding() {
        return parser.getInputEncoding();
    }


    /** {@inheritDoc}. */
    public int getLineNumber() {
        return parser.getLineNumber();
    }


    /** {@inheritDoc}. */
    public String getName() {
        return parser.getName();
    }


    /** {@inheritDoc}. */
    public String getNamespace(String prefix) {
        return parser.getNamespace(prefix);
    }


    /** {@inheritDoc}. */
    public String getNamespace() {
        return parser.getNamespace();
    }


    /** {@inheritDoc}. */
    public int getNamespaceCount(final int depth)
        throws XmlPullParserException {

        return parser.getNamespaceCount(depth);
    }


    /** {@inheritDoc}. */
    public String getNamespacePrefix(final int pos)
        throws XmlPullParserException {

        return parser.getNamespacePrefix(pos);
    }


    /** {@inheritDoc}. */
    public String getNamespaceUri(final int pos) throws XmlPullParserException {
        return parser.getNamespaceUri(pos);
    }


    /** {@inheritDoc}. */
    public String getPositionDescription() {
        return parser.getPositionDescription();
    }


    /** {@inheritDoc}. */
    public String getPrefix() {
        return parser.getPrefix();
    }


    /** {@inheritDoc}. */
    public Object getProperty(final String name) {
        return parser.getProperty(name);
    }


    /** {@inheritDoc}. */
    public String getText() {
        return parser.getText();
    }


    /** {@inheritDoc}. */
    public char[] getTextCharacters(final int[] holderForStartAndLength) {
        return parser.getTextCharacters(holderForStartAndLength);
    }


    /** {@inheritDoc}. */
    public boolean isAttributeDefault(final int index) {
        return parser.isAttributeDefault(index);
    }


    /** {@inheritDoc}. */
    public boolean isEmptyElementTag() throws XmlPullParserException {
        return parser.isEmptyElementTag();
    }


    /** {@inheritDoc}. */
    public boolean isWhitespace() throws XmlPullParserException {
        return parser.isWhitespace();
    }


    /** {@inheritDoc}. */
    public int next() throws XmlPullParserException, IOException {
        return parser.next();
    }


    /** {@inheritDoc}. */
    public int nextTag() throws XmlPullParserException, IOException {
        return parser.nextTag();
    }


    /** {@inheritDoc}. */
    public String nextText() throws XmlPullParserException, IOException {
        return parser.nextText();
    }


    /** {@inheritDoc}. */
    public int nextToken() throws XmlPullParserException, IOException {
        return parser.nextToken();
    }


    /** {@inheritDoc}. */
    public void require(final int tyep, final String namespace,
                        final String name)
        throws XmlPullParserException, IOException {

        parser.require(tyep, namespace, name);
    }


    /** {@inheritDoc}. */
    public void setFeature(final String name, final boolean state)
        throws XmlPullParserException {

        parser.setFeature(name, state);
    }


    /** {@inheritDoc}. */
    public void setInput(final Reader in) throws XmlPullParserException {
        parser.setInput(in);
    }


    /** {@inheritDoc}. */
    public void setInput(final InputStream inputStream,
                         final String inputEncoding)
        throws XmlPullParserException {

        parser.setInput(inputStream, inputEncoding);
    }


    /** {@inheritDoc}. */
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
     * @throws ParseException if the attribute value couldn't be parsed.
     */
    public Date getXSDateAttribute(final String namespace, final String name)
        throws XmlPullParserException, IOException, ParseException {

        return XmlPullParserHelper.getXSDateAttribute(parser, namespace, name);
    }


    /**
     * 
     * @param namespace
     * @param name
     * @return
     * @throws XmlPullParserException
     * @throws IOException
     * @throws ParseException 
     */
    public Date getXSTimeAttribute(final String namespace, final String name)
        throws XmlPullParserException, IOException, ParseException {

        return XmlPullParserHelper.getXSTimeAttribute(parser, namespace, name);
    }


    /**
     * 
     * @param namespace
     * @param name
     * @return
     * @throws XmlPullParserException
     * @throws IOException
     * @throws ParseException 
     */
    public Date getXSDateTimeAttribute(final String namespace,
                                       final String name)
        throws XmlPullParserException, IOException, ParseException {

        return XmlPullParserHelper.getXSDateTimeAttribute(
            parser, namespace, name);
    }


    /**
     * 
     * @return
     * @throws XmlPullParserException
     * @throws IOException
     * @throws ParseException 
     */
    public Date nextXSDate()
        throws XmlPullParserException, IOException, ParseException {

        return XmlPullParserHelper.nextXSDate(parser);
    }


    /**
     * 
     * @return
     * @throws XmlPullParserException
     * @throws IOException
     * @throws ParseException 
     */
    public Date nextXSTime()
        throws XmlPullParserException, IOException, ParseException {

        return XmlPullParserHelper.nextXSTime(parser);
    }


    /**
     * 
     * @return
     * @throws XmlPullParserException
     * @throws IOException
     * @throws ParseException 
     */
    public Date nextXSDateTime()
        throws XmlPullParserException, IOException, ParseException {

        return XmlPullParserHelper.nextXSDateTime(parser);
    }


    /**
     * 
     * @param namespace
     * @param name
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public Integer getIntAttribute(final String namespace, final String name)
        throws XmlPullParserException, IOException {

        return XmlPullParserHelper.getIntAttribute(parser, namespace, name);
    }


    /**
     * 
     * @param namespace
     * @param name
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public Long getLongAttribute(final String namespace, final String name)
        throws XmlPullParserException, IOException {

        return XmlPullParserHelper.getLongAttribute(parser, namespace, name);
    }


    /**
     * 
     * @param namespace
     * @param name
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public Float getFloatAttribute(final String namespace, final String name)
        throws XmlPullParserException, IOException {

        return XmlPullParserHelper.getFloatAttribute(parser, namespace, name);
    }


    /**
     * 
     * @param namespace
     * @param name
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public Double getDoubleAttribute(final String namespace, final String name)
        throws XmlPullParserException, IOException {

        return XmlPullParserHelper.getDoubleAttribute(parser, namespace, name);
    }


    /**
     * 
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public String nextNillableText()
        throws XmlPullParserException, IOException {

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
     * Parses <code>nextText</code> to Float.
     *
     * @return parsed Float value or null if empty tag.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public Float nextFloat() throws XmlPullParserException, IOException {

        return XmlPullParserHelper.nextFloat(parser);
    }


    /**
     * Parses <code>nextText</code> to Double.
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

