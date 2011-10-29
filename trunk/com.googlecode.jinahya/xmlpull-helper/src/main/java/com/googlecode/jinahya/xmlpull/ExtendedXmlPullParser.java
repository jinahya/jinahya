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


    public String getAttributeNamespace(final int index) {
        return parser.getAttributeNamespace(index);
    }


    public String getAttributePrefix(final int index) {
        return parser.getAttributePrefix(index);
    }


    public String getAttributeType(final int index) {
        return parser.getAttributeType(index);
    }


    public String getAttributeValue(final int index) {
        return parser.getAttributeValue(index);
    }


    public String getAttributeValue(String namespace, String name) {
        return parser.getAttributeValue(namespace, name);
    }


    public int getColumnNumber() {
        return parser.getColumnNumber();
    }


    public int getDepth() {
        return parser.getDepth();
    }


    public int getEventType() throws XmlPullParserException {
        return parser.getEventType();
    }


    public boolean getFeature(String name) {
        return parser.getFeature(name);
    }


    public String getInputEncoding() {
        return parser.getInputEncoding();
    }


    public int getLineNumber() {
        return parser.getLineNumber();
    }


    public String getName() {
        return parser.getName();
    }


    public String getNamespace(String prefix) {
        return parser.getNamespace(prefix);
    }


    public String getNamespace() {
        return parser.getNamespace();
    }


    public int getNamespaceCount(final int depth)
        throws XmlPullParserException {

        return parser.getNamespaceCount(depth);
    }


    public String getNamespacePrefix(final int pos)
        throws XmlPullParserException {

        return parser.getNamespacePrefix(pos);
    }


    public String getNamespaceUri(final int pos) throws XmlPullParserException {
        return parser.getNamespaceUri(pos);
    }


    public String getPositionDescription() {
        return parser.getPositionDescription();
    }


    public String getPrefix() {
        return parser.getPrefix();
    }


    public Object getProperty(final String name) {
        return parser.getProperty(name);
    }


    public String getText() {
        return parser.getText();
    }


    public char[] getTextCharacters(final int[] holderForStartAndLength) {
        return parser.getTextCharacters(holderForStartAndLength);
    }


    public boolean isAttributeDefault(final int index) {
        return parser.isAttributeDefault(index);
    }


    public boolean isEmptyElementTag() throws XmlPullParserException {
        return parser.isEmptyElementTag();
    }


    public boolean isWhitespace() throws XmlPullParserException {
        return parser.isWhitespace();
    }


    public int next() throws XmlPullParserException, IOException {
        return parser.next();
    }


    public int nextTag() throws XmlPullParserException, IOException {
        return parser.nextTag();
    }


    public String nextText() throws XmlPullParserException, IOException {
        return parser.nextText();
    }


    public int nextToken() throws XmlPullParserException, IOException {
        return parser.nextToken();
    }


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


    public void setInput(final Reader in) throws XmlPullParserException {
        parser.setInput(in);
    }


    public void setInput(final InputStream inputStream,
                         final String inputEncoding)
        throws XmlPullParserException {

        parser.setInput(inputStream, inputEncoding);
    }


    public void setProperty(final String name, final Object value)
        throws XmlPullParserException {

        parser.setProperty(name, value);
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
    public Date getXSDateAttributeValue(final String namespace,
                                        final String name)
        throws XmlPullParserException, IOException, ParseException {

        return XmlPullParserHelper.getXSDateAttributeValue(
            parser, namespace, name);
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
    public Date getXSTimeAttributeValue(final String namespace,
                                        final String name)
        throws XmlPullParserException, IOException, ParseException {

        return XmlPullParserHelper.getXSTimeAttributeValue(
            parser, namespace, name);
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
    public Date getXSDateTimeAttributeValue(final String namespace,
                                            final String name)
        throws XmlPullParserException, IOException, ParseException {

        return XmlPullParserHelper.getXSDateTimeAttributeValue(
            parser, namespace, name);
    }


    /**
     * 
     * @return
     * @throws XmlPullParserException
     * @throws IOException
     * @throws ParseException 
     */
    public Date nextXSDateText()
        throws XmlPullParserException, IOException, ParseException {

        return XmlPullParserHelper.nextXSDateText(parser);
    }


    /**
     * 
     * @return
     * @throws XmlPullParserException
     * @throws IOException
     * @throws ParseException 
     */
    public Date nextXSTimeText()
        throws XmlPullParserException, IOException, ParseException {

        return XmlPullParserHelper.nextXSTimeText(parser);
    }


    /**
     * 
     * @return
     * @throws XmlPullParserException
     * @throws IOException
     * @throws ParseException 
     */
    public Date nextXSDateTimeText()
        throws XmlPullParserException, IOException, ParseException {

        return XmlPullParserHelper.nextXSDateTimeText(parser);
    }


    /**
     * 
     * @param namespace
     * @param name
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public Byte getByteAttributeValue(final String namespace, final String name)
        throws XmlPullParserException, IOException {

        return XmlPullParserHelper.getByteAttributeValue(
            parser, namespace, name);
    }


    /**
     * 
     * @param namespace
     * @param name
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public Short getShortAttributeValue(final String namespace,
                                        final String name)
        throws XmlPullParserException, IOException {

        return XmlPullParserHelper.getShortAttributeValue(
            parser, namespace, name);
    }


    /**
     * 
     * @param namespace
     * @param name
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public Integer getIntAttributeValue(final String namespace,
                                        final String name)
        throws XmlPullParserException, IOException {

        return XmlPullParserHelper.getIntAttributeValue(
            parser, namespace, name);
    }


    /**
     * 
     * @param namespace
     * @param name
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public Long getLongAttributeValue(final String namespace, final String name)
        throws XmlPullParserException, IOException {

        return XmlPullParserHelper.getLongAttributeValue(
            parser, namespace, name);
    }


    /**
     * 
     * @param namespace
     * @param name
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public Float getFloatAttributeValue(final String namespace,
                                        final String name)
        throws XmlPullParserException, IOException {

        return XmlPullParserHelper.getFloatAttributeValue(
            parser, namespace, name);
    }


    /**
     * 
     * @param namespace
     * @param name
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public Double getDoubleAttributeValue(final String namespace,
                                          final String name)
        throws XmlPullParserException, IOException {

        return XmlPullParserHelper.getDoubleAttributeValue(
            parser, namespace, name);
    }


    /**
     * 
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public String nextNillableText()
        throws XmlPullParserException, IOException {

        return XmlPullParserHelper.nextNillableText(parser);
    }


    /**
     * 
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public Byte nextByteText()
        throws XmlPullParserException, IOException {

        return XmlPullParserHelper.nextByteText(parser);
    }


    /**
     * 
     * @return
     * @throws XmlPullParserException
     * @throws IOException 
     */
    public Short nextShortText()
        throws XmlPullParserException, IOException {

        return XmlPullParserHelper.nextShortText(parser);
    }


    /**
     * Parses <code>nextText</code> to an Integer.
     *
     * @return parsed Integer value or null it empty tag.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public Integer nextIntText()
        throws XmlPullParserException, IOException {

        return XmlPullParserHelper.nextIntText(parser);
    }


    /**
     * Parses <code>nextText</code> to a Long.
     *
     * @return parsed Long value or null if empty tag.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public Long nextLongText()
        throws XmlPullParserException, IOException {

        return XmlPullParserHelper.nextLongText(parser);
    }


    /**
     * Parses <code>nextText</code> to Float.
     *
     * @return parsed Float value or null if empty tag.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public Float nextFloatText()
        throws XmlPullParserException, IOException {

        return XmlPullParserHelper.nextFloatText(parser);
    }


    /**
     * Parses <code>nextText</code> to Double.
     *
     * @return parsed Long value or null if empty tag.
     * @throws XmlPullParserException if an XML error occurs.
     * @throws IOException if an I/O error occurs.
     */
    public Double nextDoubleText()
        throws XmlPullParserException, IOException {

        return XmlPullParserHelper.nextDoubleText(parser);
    }


    /**
     * parser implementation.
     */
    private final XmlPullParser parser;


}

