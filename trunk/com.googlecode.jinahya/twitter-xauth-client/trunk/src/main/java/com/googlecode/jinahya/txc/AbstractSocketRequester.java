/*
 * Copyright 2011 Jin Kwon.
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


package com.googlecode.jinahya.txc;


import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;
import java.util.Vector;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
abstract class AbstractSocketRequester implements Requester {


    /** buffer. */
    private static final StringBuffer BUFFER = new StringBuffer();


    /**
     * Reads a line.
     *
     * @param input input
     * @return a line
     * @throws IOException if an I/O error occurs.
     */
    static String readLine(final InputStream input) throws IOException {

        synchronized (BUFFER) {
            return readLine(input, BUFFER);
        }
    }


    static String readLine(final InputStream input, final StringBuffer buffer)
        throws IOException {

        buffer.delete(0, buffer.length());

        for (int b = -1; (b = input.read()) != -1;) {
            if (b == '\r') {
                input.read(); // '\n'
                break;
            }
            buffer.append((char) b);
        }

        return buffer.toString();
    }


    /**
     * Checks given <code>statusLine</code> of HTTP response.
     *
     * @param statusLine status line of HTTP response message.
     * @throws IOException if statusLine is not OK
     */
    static void checkStatusLine(final String statusLine) throws IOException {

        final Vector<String> tokenized = tokenizeStatusLine(statusLine);
        checkStatusLine(tokenized.elementAt(1), tokenized.elementAt(2));
    }


    static Vector<String> tokenizeStatusLine(final String statusLine) {

        final Vector<String> tokens = new Vector<String>(3);

        final StringTokenizer tokenizer = new StringTokenizer(statusLine);
        while (tokenizer.hasMoreTokens()) {
            final String token = tokenizer.nextToken().trim();
            if (token.length() > 0) {
                tokens.add(token);
            }
        }

        return tokens;
    }


    /**
     * Returns the <code>HTTP-Version</code> of given <code>statusLine</code>.
     *
     * @param statusLine the Status-Line of a HTTP Response message.
     * @return the <code>HTTP-Version</code>
     */
    static String getHttpVersion(final String statusLine) {

        return tokenizeStatusLine(statusLine).elementAt(0);
    }


    /**
     * Returns the <code>Status-Code</code> of given <code>statusLine</code>.
     *
     * @param statusLine the Status-Line of a HTTP Response message.
     * @return the <code>Status-Code</code>
     */
    static String getStatusCode(final String statusLine) {

        return tokenizeStatusLine(statusLine).elementAt(1);
    }


    /**
     * Returns the <code>Reason-Phrase</code> of given <code>statusLine</code>.
     *
     * @param statusLine the Status-Line of a HTTP Response message.
     * @return The <code>Reason Phrase</code>
     */
    static String getReasonPhrase(final String statusLine) {

        return tokenizeStatusLine(statusLine).elementAt(2);
    }


    /**
     * Checks HTTP response status.
     *
     * @param statusCode status code
     * @param reasonPhrase reason phrase
     * @throws IOException if the status is not OK
     */
    static void checkStatusLine(final String statusCode,
                                final String reasonPhrase)
        throws IOException {

        if (!statusCode.equals("200")) {
            throw new IOException(statusCode + "; " + reasonPhrase);
        }
    }
}
