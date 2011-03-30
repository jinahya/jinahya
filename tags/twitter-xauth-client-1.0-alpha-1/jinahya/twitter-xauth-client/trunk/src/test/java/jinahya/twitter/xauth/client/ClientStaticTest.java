

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
public class ClientStaticTest {


    @Test
    public void testPercent() {
    }


    @Test
    public void testBase16() {

        Assert.assertEquals(Client.base16("".getBytes()), "");
        Assert.assertEquals(Client.base16("f".getBytes()), "66");
        Assert.assertEquals(Client.base16("fo".getBytes()), "666F");
        Assert.assertEquals(Client.base16("foo".getBytes()), "666F6F");
        Assert.assertEquals(Client.base16("foob".getBytes()), "666F6F62");
        Assert.assertEquals(Client.base16("fooba".getBytes()), "666F6F6261");
        Assert.assertEquals(Client.base16("foobar".getBytes()), "666F6F626172");
    }


    @Test
    public void testSort() {

        final List<Integer> list = new LinkedList<Integer>();

        list.addAll(Arrays.asList(4, 2, 3, 7, 3, 1, 5, 6));

        Client.sort(list);

        Assert.assertEquals(list, Arrays.asList(3, 1, 3, 7, 4, 2, 5, 6));
    }


    @Test
    public void testBase64() {

        Assert.assertEquals(Client.base64("".getBytes()), "");
        Assert.assertEquals(Client.base64("f".getBytes()), "Zg==");
        Assert.assertEquals(Client.base64("fo".getBytes()), "Zm8=");
        Assert.assertEquals(Client.base64("foo".getBytes()), "Zm9v");
        Assert.assertEquals(Client.base64("foob".getBytes()), "Zm9vYg==");
        Assert.assertEquals(Client.base64("fooba".getBytes()), "Zm9vYmE=");
        Assert.assertEquals(Client.base64("foobar".getBytes()), "Zm9vYmFy");
    }
}
