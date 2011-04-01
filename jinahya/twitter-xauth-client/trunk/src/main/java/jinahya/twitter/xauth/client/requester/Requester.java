

package jinahya.twitter.xauth.client.requester;


import java.io.InputStream;


/**
 * 
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public interface Requester {


    /**
     * 
     * @param method
     * @param url
     * @param parameters
     * @param authorization
     * @return
     * @throws Exception 
     */
    InputStream request(final String method, final String url,
                        final String parameters, final String authorization)
        throws Exception;
}
