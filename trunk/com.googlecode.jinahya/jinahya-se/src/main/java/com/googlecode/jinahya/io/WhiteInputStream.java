/*
 * Copyright 2012 Jin Kwon <jinahya at gmail.com>.
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


import java.io.IOException;
import java.io.InputStream;
import java.util.Random;


/**
 * An {@code InputStream} which generates random values.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class WhiteInputStream extends InputStream {


    /**
     * Creates a new instance.
     *
     * @param random the random for generating values.
     */
    protected WhiteInputStream(final Random random) {
        super();

        if (random == null) {
            throw new NullPointerException("null random");
        }

        this.random = random;
    }


    /**
     * Creates a new instance.
     */
    public WhiteInputStream() {
        this(new Random());
    }


    @Override
    public int read() throws IOException {

        count++;

        return random.nextInt(0x0100);
    }


    public long getCount() {
        return count;
    }


    /**
     * random.
     */
    private final Random random;


    /**
     * count.
     */
    private long count = 0x00L;


}

