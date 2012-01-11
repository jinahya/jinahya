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


package com.googlecode.jinahya.io;


import java.io.ByteArrayInputStream;


/**
 * A BitInput for a buffered bytes.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class BufferedBitInput extends BitInput {


    /**
     * Creates a new instance buffering given <code>output</code>'s buffered
     * bytes.
     *
     * @param output output
     */
    public BufferedBitInput(final BufferedBitOutput output) {
        this(output.toByteArray());
    }


    /**
     * Creates a new instance with given <code>bytes</code>.
     *
     * @param bytes bytes
     */
    public BufferedBitInput(final byte[] bytes) {
        super(new ByteArrayInputStream(bytes));
    }


}
