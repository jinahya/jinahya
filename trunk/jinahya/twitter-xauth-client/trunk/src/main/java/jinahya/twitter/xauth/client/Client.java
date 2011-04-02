

package jinahya.twitter.xauth.client;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jinahya.twitter.xauth.client.authenticator.Authenticator;
import jinahya.twitter.xauth.client.requester.Requester;


/**
 * 
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class Client implements Authenticator, Requester {


    private static final String ACCESS_TOKEN_URL =
        "https://api.twitter.com/oauth/access_token";


    //protected static final String ALGORITHM = "HmacSHA1";
    protected static final String KEY_OAUTH_TOKEN = "oauth_token";


    protected static final String KEY_OAUTH_TOKEN_SECRET = "oauth_token_secret";


    /**
     * Creates a new instance.
     *
     * @param consumerKey consumer key
     * @param consumerSecret consumer secret
     */
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


    /**
     * Sign in.
     *
     * @param username username
     * @param password password
     * @throws Exception if an error occurs.
     */
    public void signIn(final String username, final String password)
        throws Exception {

        if (username == null) {
            throw new IllegalArgumentException("null username");
        }

        if (password == null) {
            throw new IllegalArgumentException("null password");
        }

        signOut();

        final List<String> parameters = new LinkedList<String>();

        // ----------------------------------------------------- x_auth_username
        parameters.add("x_auth_username");
        parameters.add(username);

        // ----------------------------------------------------- x_auth_password
        parameters.add("x_auth_password");
        parameters.add(password);

        // --------------------------------------------------------- x_auth_mode
        parameters.add("x_auth_mode");
        parameters.add("client_auth");

        final InputStream stream = request(
            "POST", ACCESS_TOKEN_URL, parameters, true, "", "");
        try {
            final InputStreamReader reader =
                new InputStreamReader(stream, "UTF-8");
            try {
                final StringBuffer buffer = new StringBuffer();
                String key = null;
                String value = null;
                for (int c = 0; (c = reader.read()) != -1;) {
                    switch (c) {
                        case '=':
                            key = buffer.toString();
                            buffer.delete(0, buffer.length());
                            break;
                        case '&':
                            value = buffer.toString();
                            buffer.delete(0, buffer.length());
                            responses.put(key, value);

                            break;
                        default:
                            buffer.append((char) c);
                            break;
                    }
                }
            } finally {
                reader.close();
            }
        } finally {
            stream.close();
        }
    }


    /**
     * 
     * @param method request method
     * @param url normalized resource url
     * @param parameters
     * @param authorize flag for authentication requirement
     * @return resource stream
     * @throws Exception if an error occurs.
     */
    public InputStream request(final String method, final String url,
                               final Map<String, String> parameters,
                               final boolean authorize)
        throws Exception {

        if (method == null) {
            throw new IllegalArgumentException("null method");
        }

        if (url == null) {
            throw new IllegalArgumentException("null url");
        }

        if (parameters == null) {
            throw new IllegalArgumentException("null parameters");
        }

        if (authorize && !isSignedIn()) {
            throw new IllegalStateException("not signed in");
        }

        final List<String> list = new LinkedList<String>();
        for (Entry<String, String> entry : parameters.entrySet()) {
            list.add(entry.getKey());
            list.add(entry.getValue());
        }

        return request(method, url, list, authorize);
    }


    /**
     * 
     * @param method request method
     * @param url normalized resource url
     * @param parameters parameters
     * @param authorize flag for authentication requirement
     * @return resource stream
     * @throws Exception if an error occurs
     */
    public InputStream request(final String method, final String url,
                               final List<String> parameters,
                               final boolean authorize)
        throws Exception {

        if (method == null) {
            throw new IllegalArgumentException("null method");
        }

        if (url == null) {
            throw new IllegalArgumentException("null url");
        }

        if (parameters == null) {
            throw new IllegalArgumentException("null parameters");
        }

        if (authorize && !isSignedIn()) {
            throw new IllegalStateException("not signed in");
        }

        String token = "";
        String tokenSecret = "";
        if (authorize) {
            token = responses.get("oauth_token");
            tokenSecret = responses.get("oauth_token_secret");
        }

        return request(method, url, parameters, authorize, token,
                       tokenSecret);
    }


    /**
     * 
     * @param method HTTP method
     * @param url nomalized request URL
     * @param parameters parameters
     * @param authorize flag authenticate requirement
     * @param token token
     * @param tokenSecret token secret
     * @return resource stream
     * @throws Exception if an error occurs
     */
    private InputStream request(final String method, final String url,
                                final List<String> parameters,
                                final boolean authorize, final String token,
                                final String tokenSecret)
        throws Exception {

        if (method == null) {
            throw new IllegalArgumentException("null method");
        }

        if (url == null) {
            throw new IllegalArgumentException("null url");
        }

        if (parameters == null) {
            throw new IllegalArgumentException("null parameters");
        }

        if (token == null) {
            throw new IllegalArgumentException("null token");
        }

        if (tokenSecret == null) {
            throw new IllegalArgumentException("null tokenSecret");
        }

        String authorization = null;
        if (authorize) {
            authorization = authorize(
                method, url, parameters, token, tokenSecret);
        }

        final StringBuffer buffer = new StringBuffer();
        final Iterator<String> iterator = parameters.iterator();
        if (iterator.hasNext()) {
            buffer.append(Util.url(iterator.next())).
                append("=").
                append(Util.url(iterator.next()));
        }
        while (iterator.hasNext()) {
            buffer.append("&").
                append(Util.url(iterator.next())).
                append("=").
                append(Util.url(iterator.next()));
        }

        return request(method, url, buffer.toString(), authorization);
    }


    /**
     * Signs out.
     */
    public void signOut() {
        responses.clear();
    }


    /**
     * Checks whether currently signed in or not.
     *
     * @return signed in status
     */
    public boolean isSignedIn() {

        if (responses.isEmpty()) {
            return false;
        }

        return responses.containsKey(KEY_OAUTH_TOKEN)
               && responses.containsKey(KEY_OAUTH_TOKEN_SECRET);
    }


    /**
     * 
     * @param method request method
     * @param url normalized resource url
     * @param parameters parameters
     * @param token access token
     * @param tokenSecret token secret
     * @return OAuth Authorization header value
     * @throws Exception if an error occurs
     */
    protected String authorize(final String method, final String url,
                               final List<String> parameters,
                               final String token, final String tokenSecret)
        throws Exception {

        if (method == null) {
            throw new IllegalArgumentException("null method");
        }

        if (url == null) {
            throw new IllegalArgumentException("null url");
        }

        if (parameters == null) {
            throw new IllegalArgumentException("null parameters");
        }

        if (token == null) {
            throw new IllegalArgumentException("null token");
        }

        if (tokenSecret == null) {
            throw new IllegalArgumentException("null tokenSecret");
        }

        final List<String> list = new LinkedList<String>(parameters);

        // -------------------------------------------------- oauth_consumer_key
        list.add("oauth_consumer_key");
        list.add(consumerKey);

        // --------------------------------------------------------- oauth_token
        list.add(KEY_OAUTH_TOKEN);
        list.add(token);

        // ----------------------------------------------------- oauth_timestamp
        long timestamp = System.currentTimeMillis();
        if (list.contains("oauth_timestamp")) {
            final int index = parameters.indexOf("oauth_timestamp");
            parameters.remove(index + 1);
            parameters.remove(index);
        } else {
            list.add("oauth_timestamp");
            list.add(Long.toString(timestamp / 1000L));
        }

        // --------------------------------------------------------- oauth_nonce
        final byte[] nonce = new byte[16];
        for (int i = 7; i >= 0; i--) {
            nonce[i] = (byte) (timestamp & 0xFFL);
            timestamp >>>= 8;
        }
        long random = Double.doubleToRawLongBits(Math.random());
        for (int i = 15; i >= 8; i--) {
            nonce[i] = (byte) (random & 0xFFL);
            random >>>= 8;
        }
        if (list.contains("oauth_nonce")) {
            final int index = parameters.indexOf("oauth_nonce");
            parameters.remove(index + 1);
            parameters.remove(index);
        } else {
            list.add("oauth_nonce");
            list.add(Util.base16(nonce));
        }

        // ---------------------------------------------- oauth_signature_method
        list.add("oauth_signature_method");
        list.add("HMAC-SHA1");

        // ------------------------------------------------------- oauth_version
        list.add("oauth_version");
        list.add("1.0");

        // ----------------------------------------------------- oauth_signature
        final String signature = sign(method, url, list, tokenSecret);
        list.add("oauth_signature");
        list.add(signature);

        final StringBuffer buffer =
            new StringBuffer("OAuth realm=\"" + url + "\"");
        for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
            final String key = iterator.next();
            final String value = iterator.next();
            if (parameters.contains(key)) {
                continue;
            }
            buffer.append(", ").
                append(Util.percent(key)).
                append("=\"").
                append(Util.percent(value)).
                append("\"");
        }
        return buffer.toString();
    }


    /**
     * 
     * @param method HTTP method
     * @param url normalized request URL
     * @param parameters OAuth parameters
     * @param tokenSecret token secret
     * @return signature
     * @throws Exception if an error occurs
     */
    protected String sign(final String method, final String url,
                          final List<String> parameters,
                          final String tokenSecret)
        throws Exception {

        final List<String> list = new LinkedList<String>(parameters);

        // ------------------------------------------------------ PERCENT ENCODE
        for (int i = 0; i < list.size(); i++) {
            list.add(i, Util.percent(list.remove(i)));
        }

        // ---------------------------------------------------------------- SORT
         boolean swapped;
        for (int i = list.size() - 1; i >= 3;) {
            final String source = list.get(i);
            swapped = false;
            for (int j = 1; j <= i - 2; j += 2) {
                final String target = list.get(j);
                if (target.compareTo(source) > 0) {
                    Collections.swap(list, i - 1, j - 1); // key
                    Collections.swap(list, i, j); // value
                    swapped = true;
                    break;
                }
            }
            if (!swapped) {
                i -= 2;
            }
        }
        for (int i = list.size() - 2; i >= 2;) {
            final String source = list.get(i);
            swapped = false;
            for (int j = 0; j <= i - 2; j += 2) {
                final String target = list.get(j);
                if (target.compareTo(source) > 0) {
                    Collections.swap(list, i, j); // key
                    Collections.swap(list, i + 1, j + 1); // value
                    swapped = true;
                    break;
                }
            }
            if (!swapped) {
                i -= 2;
            }
        }

        // --------------------------------------------------------- CONCATENATE
        final StringBuffer buffer = new StringBuffer();
        final Iterator<String> iterator = list.iterator();
        if (iterator.hasNext()) {
            buffer.append(iterator.next()).append("=").append(iterator.next());
            while (iterator.hasNext()) {
                buffer.append("&").append(iterator.next()).append("=").
                    append(iterator.next());
            }
        }

        // ----------------------------------------------- SIGNATURE BASE STRING
        final byte[] input = (Util.percent(method) + "&" + Util.percent(url)
                              + "&"
                              + Util.percent(buffer.toString())).getBytes();

        // ----------------------------------------------------------------- KEY
        final byte[] key = (Util.percent(consumerSecret) + "&"
                            + Util.percent(tokenSecret)).getBytes();

        // ----------------------------------------------------------- SIGNATURE
        final byte[] authentication = authenticate(key, input);

        return Util.base64(authentication);
    }


    private final String consumerKey;


    private final String consumerSecret;


    private final Map<String, String> responses =
        Collections.synchronizedMap(new HashMap<String, String>());
}
