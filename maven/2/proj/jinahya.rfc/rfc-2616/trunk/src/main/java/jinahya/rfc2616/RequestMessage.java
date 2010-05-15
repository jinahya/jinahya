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


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.StringTokenizer;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class RequestMessage extends GenericMessage {


    @Override
    public void read(final InputStream stream) throws IOException {
        super.read(stream);

        StringTokenizer tokenizer = new StringTokenizer(getStartLine());
        if (tokenizer.countTokens() != 3) {
            throw new IOException("Illegal requestLine: " + getStartLine());
        }
        method = tokenizer.nextToken();
        reasonPhrase = tokenizer.nextToken();
        HTTPVersion = tokenizer.nextToken();
    }


    @Override
    public void write(OutputStream stream) throws IOException {
        setStartLine(method + " " + reasonPhrase + " " + HTTPVersion);

        super.write(stream);
    }


    public final String getMethod() {
        return method;
    }


    public final void setMethod(final String method) {
        this.method = method;
    }


    public final String getRequestURI() {
        return reasonPhrase;
    }


    public final void setRequestURI(final String requestURI) {
        this.reasonPhrase = requestURI;
    }


    public final String getHTTPVersion() {
        return HTTPVersion;
    }


    public final void setHTTPVersion(final String HTTPVersion) {
        this.HTTPVersion = HTTPVersion;
    }


    private String method;
    private String reasonPhrase;
    private String HTTPVersion;
}
