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


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.ws.rs.core.StreamingOutput;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ContentDataStreamingOutput implements StreamingOutput {


    /**
     * Creates a new instance.
     *
     * @param storageUser user id
     * @param storagePass api key
     * @param containerName container name
     * @param objectName object name
     */
    public ContentDataStreamingOutput(final String storageUser,
                                      final String storagePass,
                                      final String containerName,
                                      final String objectName) {
        super();

        this.storageUser = storageUser;
        this.storagePass = storagePass;
        this.containerName = containerName;
        this.objectName = objectName;
    }


    @Override
    public void write(final OutputStream output) throws IOException {

        final UcloudStorageClient client =
            new UcloudStorageClient(storageUser, storagePass);

        final ContentConsumer contentConsumer = new ContentConsumer() {


            @Override
            public void setContentType(final String contentType) {
            }


            @Override
            public void setContentLength(final long contentLength) {
            }


            @Override
            public void setContentData(final InputStream contentData)
                throws IOException {
                final byte[] b = new byte[4096];
                for (int r = -1; (r = contentData.read(b)) != -1;) {
                    output.write(b, 0, r);
                }
                output.flush();
            }

        };

        client.readStorageContent(containerName, objectName, contentConsumer);
    }


    private final String storageUser;


    private final String storagePass;


    private final String containerName;


    private final String objectName;


}
