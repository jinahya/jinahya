/*
 *  Copyright 2010 onacit.
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
public class ResponseMessage extends GenericMessage {


    @Override
    public void read(final InputStream stream) throws IOException {
        super.read(stream);

        StringTokenizer tokenizer = new StringTokenizer(getStartLine());
        HTTPVersion = tokenizer.nextToken();
        statusCode = Integer.parseInt(tokenizer.nextToken());
        reasonPhrase = tokenizer.nextToken();
    }


    @Override
    public void write(OutputStream stream) throws IOException {
        setStartLine(HTTPVersion + " " + statusCode + " " + reasonPhrase);

        super.write(stream);
    }


    public final String getHTTPVersion() {
        return HTTPVersion;
    }


    public final void setHTTPVersion(final String HTTPVersion) {
        this.HTTPVersion = HTTPVersion;
    }


    public final int getStatusCode() {
        return statusCode;
    }


    public final void setStatusCode(final int statusCode) {
        this.statusCode = statusCode;
    }


    public final String getRequestURI() {
        return reasonPhrase;
    }


    public final void setRequestURI(final String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }


    private String HTTPVersion;
    private int statusCode;
    private String reasonPhrase;
}
