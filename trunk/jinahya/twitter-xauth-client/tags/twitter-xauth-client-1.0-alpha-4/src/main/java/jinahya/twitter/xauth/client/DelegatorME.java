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
import javax.microedition.io.Connector;

import javax.microedition.io.HttpConnection;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class DelegatorME
    implements Delegator<HttpConnection> {


    @Override
    public void close(final HttpConnection connection) throws IOException {
        connection.close();
    }


    @Override
    public void connect(final HttpConnection connection) throws IOException {
    }


    @Override
    public InputStream getInputStream(final HttpConnection connection)
        throws IOException {

        return connection.openInputStream();
    }


    @Override
    public OutputStream getOutputStream(final HttpConnection connection)
        throws IOException {

        return connection.openOutputStream();
    }


    @Override
    public HttpConnection open(final String spec) throws Exception {
        return (HttpConnection) Connector.open(spec);
    }


    @Override
    public void setRequestMethod(final HttpConnection connection,
                                 final String method)
        throws IOException {

        connection.setRequestMethod(method);
    }


    @Override
    public void setRequestProperty(final HttpConnection connection,
                                   final String key, final String value)
        throws IOException {

        connection.setRequestProperty(key, value);
    }
}
