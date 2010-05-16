/*
 *  Copyright 2010 Jin Kwon.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package jinahya.rfc2616.message;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BufferedMessageBody implements MessageBody {


    /**
     *
     */
    public BufferedMessageBody() {
        this(1024);
    }


    /**
     *
     * @param bufferSize
     */
    public BufferedMessageBody(final int bufferSize) {
        super();

        if (bufferSize <= 0) {
            throw new IllegalArgumentException(
                "bufferSize(" + bufferSize + ") <= 0");
        }

        buffer = new byte[bufferSize];
    }


    //@Override
    public final void read(final GenericMessage message,
                           final InputStream stream)
        throws IOException {

        content.reset();
        int read = -1;
        while ((read = stream.read(buffer)) != -1) {
            content.write(buffer, 0, read);
        }
    }


    //@Override
    public final void write(final GenericMessage message,
                            final OutputStream stream)
        throws IOException {

        stream.write(content.toByteArray());
    }


    /**
     *
     * @return
     */
    public final byte[] getContent() {
        return content.toByteArray();
    }


    /**
     *
     * @param content
     */
    public final void setContent(final byte[] content) {
        if (content == null) {
            throw new NullPointerException("content");
        }

        this.content.reset();
        try {
            this.content.write(content);
        } catch (IOException ioe) {
            // no gonna happen
        }
    }


    private final byte[] buffer;
    private final ByteArrayOutputStream content = new ByteArrayOutputStream();
}
