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
import java.net.Socket;
import java.net.URL;
import java.util.List;
import java.util.Map;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class SocketDelegator implements Delegator<Socket> {


    private static final SocketFactory DEFAULT_SSL_SOCKET_FACTORY =
        SSLSocketFactory.getDefault();


    public static class Cache {


        private String host;


        private int port;


        private String uri;


        private String method;


        private Map<String, List<String>> properties;


        private byte[] body;
    }


    public SocketDelegator() {
        this(DEFAULT_SSL_SOCKET_FACTORY);
    }


    public SocketDelegator(final SocketFactory factory) {
        super();

        if (factory == null) {
            throw new IllegalArgumentException("null factory");
        }

        this.factory = factory;
    }


    @Override
    public Socket open(final String spec) throws Exception {

        final URL url = new URL(spec);

        final String host = url.getHost();
        final int port = url.getPort();

        final Socket connection = factory.createSocket(host, port);

        return connection;
    }


    //@Override
    public void setRequestMethod(final Socket connection,
                                 final String method)
        throws IOException {
    }


    //@Override
    public void setRequestProperty(final Socket connection,
                                   final String key, final String value)
        throws IOException {
    }


    @Override
    public void connect(final Socket connection, final String method, final String uri, final String authorization, final boolean output) throws IOException {

    }


    @Override
    public InputStream openInputStream(final Socket connection)
        throws IOException {

        return connection.getInputStream();
    }


    @Override
    public void closeInputStream(final Socket connection, final InputStream inputStream) throws IOException {
    }


    @Override
    public OutputStream openOutputStream(final Socket connection)
        throws IOException {

        return connection.getOutputStream();
    }


    @Override
    public void closeOutputStream(final Socket connection, final OutputStream outputStream) throws IOException {
    }


    @Override
    public void close(final Socket connection) throws IOException {
    }


    private SocketFactory factory;
}
