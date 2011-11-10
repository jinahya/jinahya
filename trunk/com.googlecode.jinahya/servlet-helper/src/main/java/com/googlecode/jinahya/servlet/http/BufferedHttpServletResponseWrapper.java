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
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class BufferedHttpServletResponseWrapper
    extends HttpServletResponseWrapper {


    public BufferedHttpServletResponseWrapper(
        final HttpServletResponse response) {

        this(response, new BufferedHttpServletOutputStream());
    }


    public BufferedHttpServletResponseWrapper(
        final HttpServletResponse response,
        final BufferedHttpServletOutputStream outputStream) {

        super(response);

        this.outputStream = outputStream;
    }


    @Override
    public void flushBuffer() throws IOException {
        super.flushBuffer();

        if (writer != null) {
            writer.flush();
        }

        getOutputStream().flush();
    }


    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return outputStream;
    }


    @Override
    public PrintWriter getWriter() throws IOException {

        if (writer == null) {
            writer = new PrintWriter(getOutputStream());
        }

        return writer;
    }


    @Override
    public void reset() {
        super.reset();

        outputStream.reset();
    }


    @Override
    public void resetBuffer() {
        super.resetBuffer();
        outputStream.reset();
    }


    private final BufferedHttpServletOutputStream outputStream;


    private PrintWriter writer;


}

