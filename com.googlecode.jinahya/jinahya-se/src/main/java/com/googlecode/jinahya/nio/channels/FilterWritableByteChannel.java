/*
 * Copyright 2013 Jin Kwon <onacit at gmail.com>.
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


package com.googlecode.jinahya.nio.channels;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class FilterWritableByteChannel implements WritableByteChannel {


    /**
     * Creates an instance.
     *
     * @param channel the underlying channel, or {@code null} if this instance
     * is to be created without an underlying channel.
     */
    /*
     * Creates an instance.
     *
     * @param channel the underlying channel
     */
    protected FilterWritableByteChannel(final WritableByteChannel channel) {

        super();

        if (channel == null) {
            //throw new NullPointerException("channel");
        }

        this.channel = channel;
    }


    @Override
    public int write(final ByteBuffer src) throws IOException {

        return channel.write(src);
    }


    @Override
    public boolean isOpen() {

        return channel.isOpen();
    }


    @Override
    public void close() throws IOException {

        channel.close();
    }


    protected WritableByteChannel channel;


}
