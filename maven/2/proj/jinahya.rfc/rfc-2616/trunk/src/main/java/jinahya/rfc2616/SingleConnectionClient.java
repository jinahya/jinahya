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


    public static interface SocketOptionHandler {

        /**
         *
         * @param socket
         * @return
         * @throws SocketException
         */
        public Socket handleOptions(Socket socket) throws SocketException;
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

        if (!request.getMessageHeaders().containsField("Host") &&
            address instanceof InetSocketAddress) {

            final InetSocketAddress addr = (InetSocketAddress) address;
            request.getMessageHeaders().getFieldValues("Host").
                add(addr.getHostName() + ":" + addr.getPort());
        }

        Socket socket = new Socket();
        if (handler != null) {
            socket = handler.handleOptions(socket);
        }
        try {
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


    public final int getTimeout() {
        return timeout;
    }


    public final void setTimeout(int timeout) {
        if (timeout < 0) {
            throw new IllegalArgumentException("timeout(" + timeout + ") < 0");
        }
        this.timeout = timeout;
    }


    public final SocketOptionHandler getHandler() {
        return handler;
    }


    public final void setHandler(final SocketOptionHandler handler) {
        this.handler = handler;
    }


    private SocketOptionHandler handler = null;
    private int timeout = DEFAULT_TIMEOUT;
}
