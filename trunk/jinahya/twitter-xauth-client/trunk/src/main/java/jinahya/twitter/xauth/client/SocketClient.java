/*
 * Copyright 2011 onacit.
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


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;


/**
 *
 * @author onacit
 */
public abstract class SocketClient extends Client {


    public SocketClient(final String consumerKey, final String consumerSecret) {

        this(consumerKey, consumerSecret, SSLSocketFactory.getDefault());
    }


    public SocketClient(final String consumerKey, final String consumerSecret,
                        final SocketFactory factory) {

        super(consumerKey, consumerSecret);

        this.factory = factory;
    }


    @Override
    protected InputStream request(final String method, final String url,
                                  final List<String> parameters,
                                  final String authorization)
        throws Exception {

        final URL u = new URL(url);
        final String host = u.getHost();
        int port = u.getPort();
        if (port == -1) {
            port = u.getDefaultPort();
        }
        System.out.println("host: " + host);
        System.out.println("port: " + port);

        final Socket socket = createSocket(u.getProtocol(), host, port);
        try {
            if (!socket.isConnected()) {
                final SocketAddress address = new InetSocketAddress(host, port);
                socket.connect(address, connectTimeout);
            }

            final StringBuffer buffer = new StringBuffer();
            final Iterator<String> iterator = parameters.iterator();
            if (iterator.hasNext()) {
                buffer.append(URLEncoder.encode(iterator.next(), "UTF-8")).
                    append("=").
                    append(URLEncoder.encode(iterator.next(), "UTF-8"));
            }
            while (iterator.hasNext()) {
                buffer.append("&").
                    append(URLEncoder.encode(iterator.next(), "UTF-8")).
                    append("=").
                    append(URLEncoder.encode(iterator.next(), "UTF-8"));
            }

            final boolean doOutput = method.equals("POST");

            String path = u.getPath();

            if (!doOutput) {
                path += "?" + buffer.toString();
            }
            System.out.println("path: " + path);

            final OutputStream output = socket.getOutputStream();
            final Writer writer = new OutputStreamWriter(output, "US-ASCII");
            writer.write(method + " " + path + " HTTP/1.1\r\n");
            writer.write("Host: " + host + ":" + port + "\r\n");
            if (authorization != null) {
                writer.write("Authorization: " + authorization + "\r\n");
            }
            final byte[] body = buffer.toString().getBytes("US-ASCII");
            if (doOutput) {
                writer.write("Content-Length: " + body.length + "\r\n");
            }
            writer.write("\r\n");
            writer.flush();
            if (doOutput) {
                System.out.println("body: " + new String(body));
                output.write(body);
            }
            output.flush();
            System.out.println("output flushed");

            final InputStream input = socket.getInputStream();
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();

            baos.reset();
            line(input, baos);
            baos.flush();

            final String status = new String(baos.toByteArray());
            final StringTokenizer tokenizer = new StringTokenizer(status);
            tokenizer.nextToken();
            final String code = tokenizer.nextToken();
            System.out.println("code: " + code);
            if (!code.equals("200")) {
                throw new IOException("not ok; " + code);
            }

            while (true) {
                baos.reset();
                line(input, baos);
                baos.flush();
                if (baos.size() == 0) {
                    break;
                }
                System.out.println("header: " + new String(baos.toByteArray()));
            }

            baos.reset();
            for (int b = -1; (b = input.read()) != -1;) {
                baos.write(b);
            }
            baos.flush();
            System.out.println("body: " + new String(baos.toByteArray()));

            return new ByteArrayInputStream(baos.toByteArray());

        } finally {
            socket.close();
        }
    }


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


    protected Socket createSocket(final String protocol, final String host,
                                  final int port)
        throws IOException {

        if (protocol.equals("https")) {
            return factory.createSocket(host, port);
        } else {
            return new Socket(host, port);
        }
    }


    private final SocketFactory factory;


    private int connectTimeout = 0;
}
