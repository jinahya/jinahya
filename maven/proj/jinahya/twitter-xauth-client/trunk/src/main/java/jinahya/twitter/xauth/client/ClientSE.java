

package jinahya.twitter.xauth.client;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ClientSE extends Client {


    /**
     * 
     * @param consumerKey consumer key
     * @param consumerSecret consumer secret
     */
    public ClientSE(final String consumerKey, final String consumerSecret) {
        super(consumerKey, consumerSecret);
    }


    @Override
    protected byte[] HmacSHA1(final byte[] key, final byte[] input)
        throws Exception {

        final Mac mac = Mac.getInstance(ALGORITHM);
        mac.init(new SecretKeySpec(key, ALGORITHM));

        return mac.doFinal(input);
    }
}
