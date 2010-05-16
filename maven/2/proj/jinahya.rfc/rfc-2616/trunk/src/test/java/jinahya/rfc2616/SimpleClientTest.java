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
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import jinahya.rfc2616.message.RequestMessage;
import jinahya.rfc2616.message.ResponseMessage;

import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class SimpleClientTest {


    @Test
    public void testDaum() throws IOException {

        final RequestMessage request = new RequestMessage();
        request.setMethod("GET");
        request.setRequestURI("/index.html");
        request.setHTTPVersion("HTTP/1.1");
        System.out.println("-------------------------------------------------");
        System.out.println(request);

        final ResponseMessage response = new ResponseMessage();

        final SocketAddress address = new InetSocketAddress("www.daum.net", 80);
        new SimpleClient().request(address, request, response);
        System.out.println("-------------------------------------------------");
        System.out.println(response.toString());
    }
}
