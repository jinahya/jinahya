/*
 *  Copyright 2010 Jin Kwon.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package jinahya.rfc2616;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
//import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Set;

import jinahya.rfc2616.message.GenericMessage;
import jinahya.rfc2616.message.GenericMessage.MessageHeaders;
import jinahya.rfc2616.message.MessageBody;
import jinahya.rfc2616.message.ResponseMessage;
import jinahya.rfc2616.message.RequestMessage;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class SingleConnectionClient {


    /**
     *
     */
    private static final int DEFAULT_CONNECT_TIMEOUT = 2000;


    /**
     *
     */
    public static interface SocketConfigurator {

        /**
         *
         * @param socket
         * @return
         * @throws SocketException
         */
        Socket configure(Socket socket) throws SocketException;
    }


    /**
     *
     */
    private static final SocketConfigurator DEFAULT_SOCKET_CONFIGURATOR =
        new SocketConfigurator() {

            @Override
            public Socket configure(final Socket socket)
                throws SocketException {

                return socket;
            }
        };


        /**
     *
     */
    public static class BufferedMessageBody implements MessageBody {


        @Override
        public void read(final GenericMessage message, final InputStream stream)
            throws IOException {

            setContent(stream);
        }


        @Override
        public void write(final GenericMessage message,
                          final OutputStream stream)
            throws IOException {

            stream.write(content);
        }


        /**
         *
         * @return
         */
        public byte[] getContent() {
            return content;
        }


        /**
         *
         * @param content
         */
        public void setContent(final byte[] content) {

            if (content == null) {
                throw new IllegalArgumentException("content is null");
            }

            this.content = content;
        }


        /**
         *
         * @param content
         * @throws IOException
         */
        public void setContent(final InputStream content) throws IOException {

            if (content == null) {
                throw new IllegalArgumentException("content is null");
            }

            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                final byte[] buffer = new byte[1024];
                for (int read = -1; (read = content.read(buffer)) != -1;) {
                    baos.write(buffer, 0, read);
                }
                baos.flush();
                this.content = baos.toByteArray();
            } finally {
                baos.close();
            }
        }


        @Override
        public String toString() {
            return new String(content);
        }


        private byte[] content = new byte[0];
    }


    /**
     *
     * @param address
     * @param request
     * @param response
     * @throws IOException
     */
    public void request(final SocketAddress address,
                        final RequestMessage request,
                        final ResponseMessage response)
        throws IOException {

        final MessageHeaders requestMessageHeaders =
            request.getMessageHeaders();

        /*
        if (!requestMessageHeaders.containsField("Host") &&
            address instanceof InetSocketAddress) {

            final InetSocketAddress addr = (InetSocketAddress) address;
            requestMessageHeaders.getFieldValues("Host").
                add(addr.getHostName() + ":" + addr.getPort());
        }
         */

        final Set connectionFieldValues =
            requestMessageHeaders.getFieldValues("Connection");
        if (connectionFieldValues.isEmpty()) {
            connectionFieldValues.add("close");
        }


        final Socket socket = socketConfigurator.configure(new Socket());

        try {
            socket.connect(address, connectTimeout);

            // ------------------------------------------------------------ SEND
            final OutputStream output = socket.getOutputStream();
            request.write(output);
            output.flush();

            try {
                socket.shutdownOutput();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

            // --------------------------------------------------------- RECEIVE
            final InputStream input = socket.getInputStream();
            response.read(input);

            try {
                socket.shutdownInput();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

        } finally {
            try {
                socket.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }


    /**
     * Returns the current value of <code>connectTimeout</code>.
     *
     * @return connectTimeout
     */
    public int getConnectTimeout() {
        return connectTimeout;
    }


    /**
     * Sets new <code>connectTimeout</code>.
     *
     * @param connectTimeout new connect timeout.
     */
    public void setConnectTimeout(int connectTimeout) {

        if (connectTimeout < 0) {
            throw new IllegalArgumentException(
                "connectTimeout(" + connectTimeout + ") < 0");
        }

        this.connectTimeout = connectTimeout;
    }


    /**
     *
     * @return
     */
    public SocketConfigurator getSocketConfigurator() {
        return socketConfigurator;
    }


    /**
     *
     * @param socketConfigurator
     */
    public void setSocketConfigurator(
        final SocketConfigurator socketConfigurator) {

        if (socketConfigurator == null) {
            throw new IllegalArgumentException("socketConfigurator is null");
        }

        this.socketConfigurator = socketConfigurator;
    }


    private SocketConfigurator socketConfigurator = DEFAULT_SOCKET_CONFIGURATOR;
    private int connectTimeout = DEFAULT_CONNECT_TIMEOUT;
}
