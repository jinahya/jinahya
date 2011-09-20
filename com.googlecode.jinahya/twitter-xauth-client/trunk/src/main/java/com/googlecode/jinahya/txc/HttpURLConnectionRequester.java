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


import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class HttpURLConnectionRequester implements Requester {


    //@Override
    public InputStream request(final String method, String url,
                               final String parameters,
                               final String authorization)
        throws Exception {


        final boolean doOutput = method.equals("POST");
        if (!doOutput) {
            url += ("?" + parameters);
        }

        final HttpURLConnection connection =
            (HttpURLConnection) new URL(url).openConnection();

        connection.setRequestMethod(method);

        if (authorization != null) {
            connection.setRequestProperty("Authorization", authorization);
        }

        if (doOutput) {
            final byte[] body = parameters.getBytes();
            connection.setRequestProperty(
                "Content-Length", Integer.toString(body.length));
            connection.setDoOutput(true);
            final OutputStream output = connection.getOutputStream();
            output.write(body);
            output.flush();
        }

        return connection.getInputStream();
    }
}
