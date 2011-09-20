/*
 * Copyright 2011 Jin Kwon.
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


package com.googlecode.jinahya.txc;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class HttpConnectionRequester implements Requester {


    @Override
    public InputStream request(final String method, String url,
                               final String parameters,
                               final String authorization)
        throws IOException, TXCException {


        final boolean doOutput = method.equals(HttpConnection.POST);
        if (!doOutput) {
            url += ("?" + parameters);
        }

        final HttpConnection connection = open(url);
        try {
            connection.setRequestMethod(method);

            if (authorization != null) {
                connection.setRequestProperty("Authorization", authorization);
            }

            if (doOutput) {
                final byte[] body = parameters.getBytes();
                connection.setRequestProperty(
                    "Content-Length", Integer.toString(body.length));
                final OutputStream output = connection.openOutputStream();
                output.write(body);
                output.flush();
            }

            final int responseCode = connection.getResponseCode();
            if (responseCode != HttpConnection.HTTP_OK) {
                throw new IOException(
                    responseCode + "; " + connection.getResponseMessage());
            }

            final InputStream input = connection.openInputStream();
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final byte[] buffer = new byte[1024];
            for (int read = 1; (read = input.read(buffer)) != -1;) {
                baos.write(buffer, 0, read);
            }
            baos.flush();

            return new ByteArrayInputStream(baos.toByteArray());

        } finally {
            connection.close();
        }
    }


    /**
     * 
     * @param url
     * @return
     * @throws IOException 
     */
    protected HttpConnection open(final String url) throws IOException {
        return (HttpConnection) Connector.open(url);
    }
}
