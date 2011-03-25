

package jinahya.twitter.xauth;


import java.util.HashMap;
import java.util.Map;


/**
 * Hello world!
 *
 */
public class Client {


    private static final String TWITTER_ACCESS_TOKEN_URL =
        "https://api.twitter.com/oauth/access_token";


    public Client(final String consumerKey, final String consumerSecret) {
        super();

        if (consumerKey == null) {
            throw new IllegalArgumentException("null consumerKey");
        }

        if (consumerSecret == null) {
            throw new IllegalArgumentException("null consumerSecret");
        }

        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
    }


    public void signIn() {
    }


    public void signOut() {
    }


    public boolean isSignedIn() {
        return !responses.isEmpty();
    }


    private void sign(final Map<String, String> parameters) {

        parameters.put("oauth_consumer_key", consumerKey);

        parameters.put("oauth_token", "");


        final byte[] bytes = new byte[16];

        // ----------------------------------------------------------- TIMESTAMP
        long timestamp = System.currentTimeMillis();
        parameters.put("oauth_timestamp", Long.toString(timestamp / 1000L));
        for (int i = 0; i < 8; i++) {
            bytes[i] = (byte) (timestamp & 0xFFL);
            timestamp >>>= 8;
        }

        // --------------------------------------------------------------- NONCE
        long nonce = Double.doubleToRawLongBits(Math.random());
        for (int i = 8; i < 15; i++) {
            bytes[i] = (byte) (nonce & 0xFFL);
            nonce >>>= 8;
        }

        /*
        final long timestamp = Long.parseLong(
            (String) parameters.get(KEY_OAUTH_TIMESTAMP)) * 1000L;
        putLongToBytes(timestamp, bytes, 0);

        final long nonce = generateNonce(timestamp);
        putLongToBytes(nonce, bytes, 8);
        parameters.put("oauth_nonce", "");
        */

        parameters.put("oauth_signature_method", "HMAC-SHA1");
        parameters.put("oauth_version", "1.0");


    }


    private final String consumerKey;


    private final String consumerSecret;

    private final Map<String, String> responses = new HashMap<String, String>();
}
