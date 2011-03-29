

package jinahya.twitter.xauth.client;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
@Test(singleThreaded = true)
public abstract class ClientTest<T extends Client> {


    protected static final String METHOD = "GET";


    protected static final String URL = "http://photos.example.net/photos";


    protected static final List<String> PARAMETERS = Arrays.asList(
        "size", "original", "file", "vacation.jpg", "가나", "다라");


    protected static final String CONSUMER_KEY = "dpf43f3p2l4k3l03";


    protected static final String CONSUMER_SECRET = "kd94hf93k423kf44";


    protected static final String TOKEN = "nnch734d00sl2jdk";


    protected static final String TOKEN_SECRET = "pfkkdhi9sl3r4s00";


    protected static final String TIMESTAMP = "1191242096";


    protected static final String NONCE = "kllo9940pd9333jh";


    protected static final String PROTOCOL_VERSION = "1.0";


    protected static final String SIGNATURE_BASE_STRING =
        "GET&http%3A%2F%2Fphotos.example.net%2Fphotos&%25EA%25B0%2580%25EB%2582"
        + "%2598%3D%25EB%258B%25A4%25EB%259D%25BC%26file%3Dvacation.jpg%26oauth"
        + "_consumer_key%3Ddpf43f3p2l4k3l03%26oauth_nonce%3Dkllo9940pd9333jh%26"
        + "oauth_signature_method%3DHMAC-SHA1%26oauth_timestamp%3D1191242096%26"
        + "oauth_token%3Dnnch734d00sl2jdk%26oauth_version%3D1.0%26size%3Dorigin"
        + "al";


    protected static final String KEY = "kd94hf93k423kf44&pfkkdhi9sl3r4s00";


    protected static final String OAUTH_SIGNATURE =
        "GUUqqzmvcZ6ZJNNTIc/xZghY1Uw=";


    protected static final String AUTHORIZATION =
        "OAuth realm=\"http://photos.example.net/photos\""
        + ", oauth_consumer_key=\"dpf43f3p2l4k3l03\""
        + ", oauth_token=\"nnch734d00sl2jdk\""
        + ", oauth_nonce=\"kllo9940pd9333jh\""
        + ", oauth_timestamp=\"1191242096\""
        + ", oauth_signature_method=\"HMAC-SHA1\""
        + ", oauth_version=\"1.0\""
        + ", oauth_signature=\"GUUqqzmvcZ6ZJNNTIc%2FxZghY1Uw%3D\"";


    public ClientTest(final T client) {
        super();

        this.client = client;
    }


    @Test
    public void testAuthorize() throws Exception {

        final List<String> parameters = new LinkedList<String>(PARAMETERS);
        parameters.add("oauth_timestamp");
        parameters.add(TIMESTAMP);
        parameters.add("oauth_nonce");
        parameters.add(NONCE);

        final String authorization = client.authorize(
            METHOD, URL, parameters, TOKEN, TOKEN_SECRET);

        System.out.println("AUTHORIZATION.ACTUAL  : " + authorization);

        System.out.println("AUTHORIZATION.EXPECTED: " + AUTHORIZATION);
    }


    protected final T client;
}
