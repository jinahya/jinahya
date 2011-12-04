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


package jinahya.io;


import java.io.ByteArrayInputStream;
import java.util.zip.Checksum;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class BufferedChecksumInputStream extends ChecksumInputStream {


    public BufferedChecksumInputStream(
        final BufferedChecksumOutputStream output, final Checksum checksum) {

        this(output.toByteArray(), checksum);
    }


    public BufferedChecksumInputStream(final byte[] bytes,
                                       final Checksum checksum) {

        super(new ByteArrayInputStream(bytes), checksum);
    }


}

