

package jinahya.twitter.xauth.client.requester;


import java.io.InputStream;


/**
 * 
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public interface Requester {


    /**
     * Requests with given arguments.
     *
     * @param method request method
     * @param url request url
     * @param parameters parameters (already url encoded)
     * @param authorization authorization header value or null.
     * @return resource stream
     * @throws Exception if any error occurs
     */
    InputStream request(final String method, final String url,
                        final String parameters, final String authorization)
        throws Exception;
}
