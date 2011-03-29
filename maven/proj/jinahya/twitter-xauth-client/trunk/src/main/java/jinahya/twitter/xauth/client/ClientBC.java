

package jinahya.twitter.xauth.client;


import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ClientBC extends Client {


    public static void main(final String[] args) throws Exception {
        main(ClientBC.class, args);
    }


    /**
     * 
     * @param consumerKey consumer key
     * @param consumerSecret consumer secret
     */
    public ClientBC(final String consumerKey, final String consumerSecret) {
        super(consumerKey, consumerSecret);
    }


    @Override
    protected byte[] HmacSHA1(final byte[] key, final byte[] input)
        throws Exception {

        final Mac mac = new HMac(new SHA1Digest());
        mac.init(new KeyParameter(key));

        mac.update(input, 0, input.length);

        final byte[] output = new byte[mac.getMacSize()];
        mac.doFinal(output, 0);

        return output;
    }
}
