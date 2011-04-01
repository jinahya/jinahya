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


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>

 */
public class UtilTest {


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

        Assert.assertEquals(Util.base16("".getBytes()), "");
        Assert.assertEquals(Util.base16("f".getBytes()), "66");
        Assert.assertEquals(Util.base16("fo".getBytes()), "666F");
        Assert.assertEquals(Util.base16("foo".getBytes()), "666F6F");
        Assert.assertEquals(Util.base16("foob".getBytes()), "666F6F62");
        Assert.assertEquals(Util.base16("fooba".getBytes()), "666F6F6261");
        Assert.assertEquals(Util.base16("foobar".getBytes()), "666F6F626172");
    }


    @Test
    public void testSort() {

        final List<Integer> list = new LinkedList<Integer>();

        list.addAll(Arrays.asList(4, 2, 3, 7, 3, 1, 5, 6));

        Util.sort(list);

        Assert.assertEquals(list, Arrays.asList(3, 1, 3, 7, 4, 2, 5, 6));
    }


    /**
     *
     * @see <a href="http://www.apps.ietf.org/rfc/rfc4648.html#sec-10">Test
     *      Vectors (RFC 4648)</a>
     */
    @Test
    public void testBase64() {

        Assert.assertEquals(Util.base64("".getBytes()), "");
        Assert.assertEquals(Util.base64("f".getBytes()), "Zg==");
        Assert.assertEquals(Util.base64("fo".getBytes()), "Zm8=");
        Assert.assertEquals(Util.base64("foo".getBytes()), "Zm9v");
        Assert.assertEquals(Util.base64("foob".getBytes()), "Zm9vYg==");
        Assert.assertEquals(Util.base64("fooba".getBytes()), "Zm9vYmE=");
        Assert.assertEquals(Util.base64("foobar".getBytes()), "Zm9vYmFy");
    }
}
