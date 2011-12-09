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


package com.googlecode.jinahya.servlet;


import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;


/**
 * Buffered implementation of ServletOutputStream.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class BufferedServletOutputStream extends ServletOutputStream {


    /**
     * Creates a new instance.
     */
    public BufferedServletOutputStream() {
        super();

        outputStream = new ByteArrayOutputStream();
    }


    @Override
    public void write(final int b) throws IOException {
        outputStream.write(b);
    }


    /**
     * Reset this OutputStream.
     */
    public final void reset() {
        outputStream.reset();
    }


    /**
     * Returns buffered bytes.
     *
     * @return buffered bytes
     */
    public final byte[] getBytes() {
        return outputStream.toByteArray();
    }


    /**
     * buffer stream.
     */
    private final ByteArrayOutputStream outputStream;


}

