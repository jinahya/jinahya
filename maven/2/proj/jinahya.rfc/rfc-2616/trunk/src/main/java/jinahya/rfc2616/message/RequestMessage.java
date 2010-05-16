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

package jinahya.rfc2616.message;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.StringTokenizer;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class RequestMessage extends GenericMessage {


    //@Override
    public String getStartLine() {
        return method + " " + requestUri + " " + httpVersion;
    }


    //@Override
    protected void setStartLine(final String startLine) {
        final StringTokenizer tokenizer = new StringTokenizer(startLine);
        method = tokenizer.nextToken();
        requestUri = tokenizer.nextToken();
        httpVersion = tokenizer.nextToken();
    }


    //@Override
    public GenericMessage read(final InputStream stream) throws IOException {
        return (RequestMessage) super.read(stream);
    }


    //@Override
    public GenericMessage write(OutputStream stream) throws IOException {
        return (RequestMessage) super.write(stream);
    }


    public final String getMethod() {
        return method;
    }


    public final void setMethod(final String method) {
        this.method = method;
    }


    public final String getRequestUri() {
        return requestUri;
    }


    public final void setRequestUri(final String requestUri) {
        this.requestUri = requestUri;
    }


    public final String getHttpVersion() {
        return httpVersion;
    }


    public final void setHttpVersion(final String httpVersion) {
        this.httpVersion = httpVersion;
    }


    /**
     *
     * @param address
     * @param port
     * @return
     */
    public final boolean setHost(final String address, final int port) {
        return setHost(address + ":" + port);
    }


    /**
     *
     * @param host
     * @return
     */
    public final boolean setHost(final String host) {
        return getMessageHeaders().getFieldValues("Host").add(host);
    }


    private String method;
    private String requestUri;
    private String httpVersion;
}
