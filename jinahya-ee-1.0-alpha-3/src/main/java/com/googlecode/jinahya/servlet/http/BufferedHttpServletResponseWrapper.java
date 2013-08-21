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


import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import com.googlecode.jinahya.servlet.BufferedServletOutputStream;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class BufferedHttpServletResponseWrapper
    extends HttpServletResponseWrapper {


    /**
     * Creates a new instance.
     *
     * @param response response
     */
    public BufferedHttpServletResponseWrapper(
        final HttpServletResponse response) {

        super(response);

        outputStream = new BufferedServletOutputStream();
    }


    @Override
    public final void flushBuffer() throws IOException {

        if (writer != null) {
            writer.flush();
        }

        getOutputStream().flush();
    }


    @Override
    public final ServletOutputStream getOutputStream() throws IOException {
        return outputStream;
    }


    @Override
    public final PrintWriter getWriter() throws IOException {

        if (writer == null) {
            writer = new PrintWriter(new OutputStreamWriter(
                getOutputStream(), getResponse().getCharacterEncoding()));
        }

        return writer;
    }


    @Override
    public final void reset() {
        outputStream.reset();
    }


    @Override
    public final void resetBuffer() {
        outputStream.reset();
    }


    /**
     * Returns buffered bytes.
     *
     * @return buffered bytes
     */
    public final byte[] getBytes() {
        return outputStream.getBytes();
    }


    /**
     * output stream.
     */
    private final BufferedServletOutputStream outputStream;


    /**
     * writer.
     */
    private PrintWriter writer;


}

