

package jinahya.twitter.xauth.client.authenticator;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class JCAAuthenticator implements Authenticator {


    protected static final String ALGORITHM = "HmacSHA1";


    //@Override
    public byte[] authenticate(final byte[] key, final byte[] input)
        throws Exception {

        final Mac mac = Mac.getInstance(ALGORITHM);
        mac.init(new SecretKeySpec(key, ALGORITHM));

        return mac.doFinal(input);
    }
}
