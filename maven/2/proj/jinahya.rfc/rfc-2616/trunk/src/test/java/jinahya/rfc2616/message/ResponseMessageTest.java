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

//import org.testng.Assert;
//import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ResponseMessageTest extends GenericMessageTest {


    private ResponseMessage read() throws IOException {
        InputStream stream = getClass().getResourceAsStream("/response.bin");
        return (ResponseMessage) new ResponseMessage().read(stream);
    }


    /**
     *
     * @throws IOException
     * @testng.test
     */
    //@Test
    public void readSample() throws IOException {
        ResponseMessage message = read();

        System.out.println("-------------------------------------------------");
        print(message);
    }
}