

package jinahya.twitter.xauth.client;


import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class AuthenticatorBC implements Authenticator {


    @Override
    public byte[] authenticate(final byte[] key, final byte[] input)
        throws Exception {

        final Mac mac = new HMac(new SHA1Digest());
        mac.init(new KeyParameter(key));

        mac.update(input, 0, input.length);

        final byte[] output = new byte[mac.getMacSize()];
        mac.doFinal(output, 0);

        return output;
    }
}
