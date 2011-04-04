/*
 * Copyright 2011 Jin Kwon.
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


package jinahya.twitter.xauth.client;


import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>

 */
public class EncodersTest {


    @Test
    public void testPercent() {
        // i need test vectors
    }


    /**
     *
     * @see <a href="http://www.apps.ietf.org/rfc/rfc4648.html#sec-10">Test
     *      Vectors (RFC 4648)</a>
     */
    @Test
    public void testBase16() {

        Assert.assertEquals(Encoders.base16("".getBytes()), "");
        Assert.assertEquals(Encoders.base16("f".getBytes()), "66");
        Assert.assertEquals(Encoders.base16("fo".getBytes()), "666F");
        Assert.assertEquals(Encoders.base16("foo".getBytes()), "666F6F");
        Assert.assertEquals(Encoders.base16("foob".getBytes()), "666F6F62");
        Assert.assertEquals(Encoders.base16("fooba".getBytes()), "666F6F6261");
        Assert.assertEquals(Encoders.base16("foobar".getBytes()), "666F6F626172");
    }


    /**
     *
     * @see <a href="http://www.apps.ietf.org/rfc/rfc4648.html#sec-10">Test
     *      Vectors (RFC 4648)</a>
     */
    @Test
    public void testBase64() {

        Assert.assertEquals(Encoders.base64("".getBytes()), "");
        Assert.assertEquals(Encoders.base64("f".getBytes()), "Zg==");
        Assert.assertEquals(Encoders.base64("fo".getBytes()), "Zm8=");
        Assert.assertEquals(Encoders.base64("foo".getBytes()), "Zm9v");
        Assert.assertEquals(Encoders.base64("foob".getBytes()), "Zm9vYg==");
        Assert.assertEquals(Encoders.base64("fooba".getBytes()), "Zm9vYmE=");
        Assert.assertEquals(Encoders.base64("foobar".getBytes()), "Zm9vYmFy");
    }
}
