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


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
abstract class AbstractSocketRequester implements Requester {


    /**
     * Reads a line from <code>input</code> and write to <code>output</code>.
     *
     * @param input input
     * @param output output
     * @throws IOException if an I/O error occurs.
     */
    static void line(final InputStream input, final OutputStream output)
        throws IOException {

        for (int b = -1; (b = input.read()) != -1;) {
            if (b == '\r') {
                input.read(); // '\n'
                return;
            }
            output.write(b);
        }
    }


    /**
     *
     * @param input
     * @param output
     * @throws IOException
     */
    static void line(final InputStream input,
                     final ByteArrayOutputStream output)
        throws IOException {

        output.reset();
        line(input, output);
        output.flush();
    }


    /**
     * Checks given <code>statusLine</code> of HTTP response.
     *
     * @param statusLine status line of HTTP response message.
     * @throws IOException if statusLine is not OK
     */
    protected void checkStatusLine(final String statusLine) throws IOException {

        int spaceIndex = statusLine.indexOf(' ');
        final String httpVersion = statusLine.substring(0, spaceIndex);
        spaceIndex = statusLine.indexOf(' ', spaceIndex + 1);
        final String statusCode = statusLine.substring(
            httpVersion.length() + 2, spaceIndex);
        final String reasonPhrase = statusLine.substring(spaceIndex + 1);

        checkStatus(statusCode, reasonPhrase);
    }


    /**
     * Checks HTTP response status.
     *
     * @param statusCode status code
     * @param reasonPhrase reason phrase
     * @throws IOException if the status is not OK
     */
    protected void checkStatus(final String statusCode,
                               final String reasonPhrase)
        throws IOException {

        if (!statusCode.equals("200")) {
            throw new IOException(statusCode + "; " + reasonPhrase);
        }
    }
}

