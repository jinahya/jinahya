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


import jinahya.rfc2616.message.ResponseMessage;
import jinahya.rfc2616.message.RequestMessage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class SingleConnectionClient {


    private static final int DEFAULT_TIMEOUT = 5000;


    public static interface SocketOptionEnabler {

        /**
         *
         * @param socket
         * @return
         * @throws SocketException
         */
        public Socket enableOptions(Socket socket) throws SocketException;
    }


    /**
     *
     */
    private static final SocketOptionEnabler DEFAULT_ENABLER =
        new SocketOptionEnabler() {
            public Socket enableOptions(final Socket socket)
                throws SocketException {

                socket.setSoTimeout(5000);

                return socket;
            }
        };


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

        final Socket socket = enabler.enableOptions(new Socket());
        try {

            if (!request.getMessageHeaders().containsMessageHeader("Host") &&
                address instanceof InetSocketAddress) {

                final InetSocketAddress addr = (InetSocketAddress) address;
                request.getMessageHeaders().getFieldValues("Host").
                    add(addr.getHostName() + ":" + addr.getPort());
            }

            socket.connect(address, timeout);

            final OutputStream output = socket.getOutputStream();
            request.write(output);
            output.flush();
            socket.shutdownOutput();

            final InputStream input = socket.getInputStream();
            response.read(input);
            socket.shutdownInput();

        } finally {
            socket.close();
        }
    }


    public final long getTimeout() {
        return timeout;
    }


    public final void setTimeout(int timeout) {
        if (timeout <= 0L) {
            throw new IllegalArgumentException("timeout(" + timeout + ") <= 0");
        }
        this.timeout = timeout;
    }


    public SocketOptionEnabler getEnabler() {
        return enabler;
    }


    public void setEnabler(final SocketOptionEnabler enabler) {
        if (enabler == null) {
            throw new NullPointerException("enabler");
        }
        this.enabler = enabler;
    }


    private SocketOptionEnabler enabler = DEFAULT_ENABLER;
    private int timeout = DEFAULT_TIMEOUT;
}
