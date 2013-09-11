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
import java.security.MessageDigest;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class DigestWritableByteChannel extends FilterWritableByteChannel {


    /**
     * Creates a new instance.
     *
     * @param channel the output channel
     * @param digest the message digest to associate with this channel
     */
    public DigestWritableByteChannel(final WritableByteChannel channel,
                                     final MessageDigest digest) {

        super(channel);

        if (digest == null) {
            throw new NullPointerException("digest");
        }

        this.digest = digest;
    }


    @Override
    public int write(final ByteBuffer src) throws IOException {

        final int position = src.position();

        final int written = super.write(src);

        src.position(position);
        for (int i = 0; i < written; i++) {
            digest.update(src.get());
        }

        return written;
    }


    private MessageDigest digest;


}
