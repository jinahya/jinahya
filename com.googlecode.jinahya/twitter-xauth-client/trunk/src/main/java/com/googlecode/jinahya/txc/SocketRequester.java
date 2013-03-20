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
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class SocketRequester extends AbstractSocketRequester {


    /**
     * default SSL socket factory.
     *
     * @see javax.net.ssl.SSLSocketFactory#getDefault()
     */
    protected static final SocketFactory DEFAULT_SSL_SOCKET_FACTORY =
        SSLSocketFactory.getDefault();


    @Override
    public InputStream request(final String method, final String url,
                               final String parameters,
                               final String authorization)
        throws IOException, XTweetException {

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

            // ---------------------------------------------------- REQUEST LINE
            writer.write(method + " " + path + " HTTP/1.1\r\n");

            // ------------------------------------------------- REQUEST HEADERS
            writer.write("Host: " + host + ":" + port + "\r\n");
            if (authorization != null) {
                writer.write("Authorization: " + authorization + "\r\n");
            }
            writer.write("Connection: close\r\n");
            final byte[] body = parameters.getBytes("US-ASCII");
            if (doOutput) {
                writer.write("Content-Length: " + body.length + "\r\n");
            }
            writer.write("\r\n");
            writer.flush();

            // ---------------------------------------------------- REQUEST BODY
            if (doOutput) {
                output.write(body);
            }
            output.flush();


            final InputStream input =
                new BufferedInputStream(socket.getInputStream());


            // ----------------------------------------------------- STATUS LINE
            final String statusLine = readLine(input);
            checkStatusLine(statusLine);

            // ------------------------------------------------ RESPONSE HEADERS
            while (readLine(input).length() > 0) {
            }

            // --------------------------------------------------- RESPONSE BODY
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
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
     * Creates a Socket.
     *
     * @param protocol protocol
     * @param host target host
     * @param port target port
     *
     * @return a new Socket
     *
     * @throws IOException if an I/O error occurs.
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
     * Creates a SSLSocket instance.
     *
     * @param host target host
     * @param port target port
     *
     * @return a new SSLSocket
     *
     * @throws IOException if an I/O error occurs.
     */
    protected SSLSocket createSSL(final String host, final int port)
        throws IOException {

        return (SSLSocket) DEFAULT_SSL_SOCKET_FACTORY.createSocket();
    }


    /**
     * Create a Socket.
     *
     * @param host target host
     * @param port target port
     *
     * @return a new Socket
     *
     * @throws IOException if an I/O error occurs.
     */
    protected Socket create(final String host, final int port)
        throws IOException {

        return new Socket();
    }


    /**
     * Binds given
     * <code>socket</code>.
     *
     * @param socket socket to bind
     *
     * @throws IOException if an I/O error occurs.
     */
    protected void bind(final Socket socket) throws IOException {
    }


    /**
     * Connects given
     * <code>socket</code> to specified address.
     *
     * @param socket socket
     * @param host target host
     * @param port target port
     *
     * @throws IOException if an I/O error occurs.
     */
    protected void connect(final Socket socket, final String host,
                           final int port)
        throws IOException {

        socket.connect(new InetSocketAddress(host, port));
    }


}

