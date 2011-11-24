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


package com.googlecode.jinahya.servlet.http;


/**
 * Filter for transforming XML to (X)HTML.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class XML2HTMLFilter extends XSLTFilter {


    /**
     * Preferred Media Type for HTML.
     *
     * @see <a href="http://tools.ietf.org/html/rfc2854">
     *      The 'text/html' Media Type (RFC2854)</a>
     */
    protected static final String MEDIA_TYPE_TEXT_HTML = "text/html";


    /**
     * Preferred Media Type for XHTML.
     *
     * @see <a href="http://www.w3.org/TR/xhtml-media-types/">
     *      XHTML Media Types - Second Edition (W3C)</a>
     */
    protected static final String MEDIA_TYPE_APPLICATION_XHTML_XML =
        "application/xhtml+xml";


    /**
     * {@inheritDoc}
     *
     * @return {@link XSLTFilter#PREFERRED_CHARACTER_ENCODING}
     */
    @Override
    protected String getOutputCharacterEncoding() {
        return PREFERRED_CHARACTER_ENCODING;
    }


    /**
     * {@inheritDoc}
     *
     * @return {@link #MEDIA_TYPE_APPLICATION_XHTML_XML}
     */
    @Override
    protected String getOutputContentType() {
        return MEDIA_TYPE_APPLICATION_XHTML_XML;
    }


}

