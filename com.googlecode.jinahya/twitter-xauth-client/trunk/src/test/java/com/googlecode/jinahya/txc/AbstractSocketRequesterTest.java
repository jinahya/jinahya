/*
 * Copyright 2011 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.txc;


import java.util.Vector;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class AbstractSocketRequesterTest {


    @Test
    public static void testTokenizeStatusLine() {

        final String statusLine = "HTTP/1.1 200 OK";
        final Vector<String> tokenized =
            AbstractSocketRequester.tokenizeStatusLine(statusLine);
        Assert.assertEquals(tokenized.elementAt(0), "HTTP/1.1");
        Assert.assertEquals(tokenized.elementAt(1), "200");
        Assert.assertEquals(tokenized.elementAt(2), "OK");
    }
}
