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


package jinahya.twitter.xauth.client;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class DelegatorSE
    implements Delegator<HttpURLConnection> {


    @Override
    public HttpURLConnection open(final String spec) throws Exception {

        return (HttpURLConnection) new URL(spec).openConnection();
    }


    @Override
    public void connect(final HttpURLConnection connection, final String method, final String uri, final String authorization, final boolean output) throws IOException {

        connection.setRequestMethod(method);
        connection.setRequestProperty("Authorization", authorization);

        if (output) {
            connection.setDoOutput(true);
        }

        connection.connect();
    }


    @Override
    public InputStream openInputStream(final HttpURLConnection connection)
        throws IOException {

        return connection.getInputStream();
    }


    @Override
    public void closeInputStream(final HttpURLConnection connection, final InputStream inputStream) throws IOException {
    }


    @Override
    public OutputStream openOutputStream(final HttpURLConnection connection)
        throws IOException {

        return connection.getOutputStream();
    }


    @Override
    public void closeOutputStream(final HttpURLConnection connection, final OutputStream outputStream) throws IOException {
    }


    //@Override
    public void setRequestMethod(final HttpURLConnection connection,
                                 final String method)
        throws IOException {

        connection.setRequestMethod(method);
    }


    //@Override
    public void setRequestProperty(final HttpURLConnection connection,
                                   final String key, final String value)
        throws IOException {

        connection.setRequestProperty(key, value);
    }


    @Override
    public void close(final HttpURLConnection connection) throws IOException {
    }
}
