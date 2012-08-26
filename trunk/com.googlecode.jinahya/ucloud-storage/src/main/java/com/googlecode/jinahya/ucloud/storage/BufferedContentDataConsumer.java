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


package com.googlecode.jinahya.ucloud.storage;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class BufferedContentDataConsumer implements ContentDataConsumer {


    @Override
    public void setContentData(final InputStream contentData)
        throws IOException {

        if (contentData == null) {
            throw new IllegalArgumentException("null contentData");
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final byte[] buffer = new byte[8192];
        for (int read = -1; (read = contentData.read(buffer)) != -1;) {
            baos.write(buffer, 0, read);
        }
        baos.flush();

        this.contentData = baos.toByteArray();
    }


    /**
     * Returns buffered content data.
     *
     * @return content data
     */
    public byte[] getContentData() {
        return contentData;
    }


    /**
     * content data.
     */
    private byte[] contentData;


}

