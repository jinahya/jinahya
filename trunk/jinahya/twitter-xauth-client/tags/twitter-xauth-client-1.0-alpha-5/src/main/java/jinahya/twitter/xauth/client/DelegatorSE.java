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
    public void close(final HttpURLConnection connection) throws IOException {
    }


    @Override
    public void connect(final HttpURLConnection connection) throws IOException {
        connection.connect();
    }


    @Override
    public InputStream getInputStream(final HttpURLConnection connection)
        throws IOException {

        return connection.getInputStream();
    }


    @Override
    public OutputStream getOutputStream(final HttpURLConnection connection)
        throws IOException {

        return connection.getOutputStream();
    }


    @Override
    public HttpURLConnection open(final String spec) throws Exception {
        return (HttpURLConnection) new URL(spec).openConnection();
    }


    @Override
    public void setRequestMethod(final HttpURLConnection connection,
                                 final String method)
        throws IOException {

        connection.setRequestMethod(method);
    }


    @Override
    public void setRequestProperty(final HttpURLConnection connection,
                                   final String key, final String value)
        throws IOException {

        connection.setRequestProperty(key, value);
    }
}
