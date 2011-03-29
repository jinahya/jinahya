

package jinahya.twitter.xauth.client;

/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ClientSETest extends ClientTest<ClientSE> {


    public ClientSETest() {
        super(new ClientSE(CONSUMER_KEY, CONSUMER_SECRET));
    }
}
