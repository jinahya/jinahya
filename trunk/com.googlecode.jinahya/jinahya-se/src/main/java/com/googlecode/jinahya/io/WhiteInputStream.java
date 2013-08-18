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


/**
 * An {@code InputStream} which generates random values.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class WhiteInputStream extends InputStream {


    /**
     * Creates a new instance with given {@code limit}.
     *
     * @param limit the maximum number of bytes for reading. any negative for
     * unlimited.
     */
    public WhiteInputStream(final long limit) {

        super();

        this.limit = limit;
    }


    /**
     * Creates a new instance without a limit.
     */
    public WhiteInputStream() {

        this(-1L);
    }


    @Override
    public int read() throws IOException {

        if (limit >= 0L && count >= limit) {
            return -1;
        }

        count++;

        return (int) (System.currentTimeMillis() & 0xFF);
    }


    /**
     * Returns the number of bytes read so far.
     *
     * @return the number of bytes read so far.
     */
    public long getCount() {
        return count;
    }


    /**
     * count.
     */
    private long count = 0x00L;


    /**
     * limit.
     */
    private final long limit;


}

