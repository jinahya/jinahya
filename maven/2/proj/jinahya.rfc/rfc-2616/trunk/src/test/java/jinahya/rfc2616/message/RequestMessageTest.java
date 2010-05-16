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


import jinahya.rfc2616.message.RequestMessage;
import java.io.IOException;
import java.io.InputStream;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class RequestMessageTest {


    private RequestMessage read() throws IOException {
        InputStream stream = getClass().getResourceAsStream("/request.bin");
        return new RequestMessage().read(stream);
    }


    @Test
    public void readSample() throws IOException {
        RequestMessage message = read();

        System.out.println("-------------------------------------------------");
        System.out.println(message);
        System.out.println("method: " + message.getMethod());
        System.out.println("requestURI: " + message.getRequestURI());
        System.out.println("HTTPVersion: " + message.getHTTPVersion());
    }
}
