

package jinahya.twitter.xauth.client;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ClientBCTest extends ClientTest<ClientSE> {


    public ClientBCTest() {
        super(new ClientSE(CONSUMER_KEY, CONSUMER_SECRET));
    }
}
