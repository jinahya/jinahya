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


package jinahya.twitter.xauth.client.requester;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.StringTokenizer;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class SocketRequester implements Requester {


    protected static final SocketFactory DEFAULT_SSL_SOCKET_FACTORY =
        SSLSocketFactory.getDefault();


    @Override
    public InputStream request(final String method, final String url,
                               final String parameters,
                               final String authorization)
        throws Exception {

        final URL u = new URL(url);
        final String host = u.getHost();
        int port = u.getPort();
        if (port == -1) {
            port = u.getDefaultPort();
        }

        final Socket socket = create(u.getProtocol(), host, port);
        try {

            if (!socket.isBound()) {
                bind(socket);
            }

            if (!socket.isConnected()) {
                connect(socket, host, port);
            }

            final boolean doOutput = method.equals("POST");

            String path = u.getPath();

            if (!doOutput) {
                path += ("?" + parameters);
            }

            final OutputStream output =
                new BufferedOutputStream(socket.getOutputStream());
            final Writer writer = new OutputStreamWriter(output, "US-ASCII");
            writer.write(method + " " + path + " HTTP/1.1\r\n");
            writer.write("Host: " + host + ":" + port + "\r\n");
            if (authorization != null) {
                writer.write("Authorization: " + authorization + "\r\n");
            }
            final byte[] body = parameters.getBytes("US-ASCII");
            if (doOutput) {
                writer.write("Content-Length: " + body.length + "\r\n");
            }
            writer.write("\r\n");
            writer.flush();
            if (doOutput) {
                output.write(body);
            }
            output.flush();

            final InputStream input =
                new BufferedInputStream(socket.getInputStream());

            final ByteArrayOutputStream baos = new ByteArrayOutputStream();

            baos.reset();
            line(input, baos);
            baos.flush();

            final String statusLine = new String(baos.toByteArray());
            final StringTokenizer tokenizer = new StringTokenizer(statusLine);
            final String httpVersion = tokenizer.nextToken();
            final String statusCode = tokenizer.nextToken();
            final String reasonPhrase = tokenizer.nextToken();
            check(statusCode, reasonPhrase);

            while (true) {
                baos.reset();
                line(input, baos);
                baos.flush();
                if (baos.size() == 0) {
                    break;
                }
            }

            baos.reset();
            final byte[] buffer = new byte[1024];
            for (int read = -1; (read = input.read(buffer)) != -1;) {
                baos.write(buffer, 0, read);
            }
            baos.flush();

            return new ByteArrayInputStream(baos.toByteArray());

        } finally {
            socket.close();
        }
    }


    /**
     * Checks http response status.
     *
     * @param statusCode status code
     * @param reasonPhrase reason phrase
     * @throws IOException if the status is not ok
     */
    protected void check(final String statusCode, final String reasonPhrase)
        throws IOException {

        if (!statusCode.equals("200")) {
            throw new IOException(statusCode + "; " + reasonPhrase);
        }
    }


    /**
     * 
     * @param protocol
     * @param host
     * @param port
     * @return
     * @throws IOException 
     */
    protected Socket create(final String protocol, final String host,
                            final int port)
        throws IOException {

        if (protocol.equals("https")) {
            return createSSL(host, port);
        } else {
            return create(host, port);
        }
    }


    /**
     * 
     * @param host
     * @param port
     * @return
     * @throws IOException 
     */
    protected Socket createSSL(final String host, final int port)
        throws IOException {

        return DEFAULT_SSL_SOCKET_FACTORY.createSocket();
    }


    /**
     * 
     * @param host
     * @param port
     * @return
     * @throws IOException 
     */
    protected Socket create(final String host, final int port)
        throws IOException {

        return new Socket();
    }


    /**
     * 
     * @param socket
     * @throws IOException 
     */
    protected void bind(final Socket socket) throws IOException {
    }


    /**
     * 
     * @param socket
     * @param host
     * @param port
     * @throws IOException 
     */
    protected void connect(final Socket socket, final String host,
                           final int port)
        throws IOException {

        socket.connect(new InetSocketAddress(host, port));
    }


    /**
     * 
     * @param input
     * @param output
     * @throws IOException 
     */
    private void line(final InputStream input, final OutputStream output)
        throws IOException {

        for (int b = -1; (b = input.read()) != -1;) {
            if (b == '\r') {
                input.read(); // '\n'
                return;
            }
            output.write(b);
        }
    }
}
