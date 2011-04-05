/*
 * Copyright 2011 Jin Kwon.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package jinahya.twitter.xauth.client;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;
import java.util.Vector;

import jinahya.twitter.xauth.client.authenticator.Authenticator;
import jinahya.twitter.xauth.client.requester.Requester;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class Client implements Authenticator, Requester {


    /** access token url. */
    private static final String ACCESS_TOKEN_URL =
        "https://api.twitter.com/oauth/access_token";


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

        final Vector parameters = new Vector(6);

        // ----------------------------------------------------- x_auth_username
        parameters.addElement("x_auth_username");
        parameters.addElement(username);

        // ----------------------------------------------------- x_auth_password
        parameters.addElement("x_auth_password");
        parameters.addElement(password);

        // --------------------------------------------------------- x_auth_mode
        parameters.addElement("x_auth_mode");
        parameters.addElement("client_auth");

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
                               final Hashtable parameters,
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

        final Vector vector = new Vector(parameters.size() * 2);
        Object key;
        for (Enumeration keys = parameters.keys(); keys.hasMoreElements();) {
            key = keys.nextElement();
            vector.addElement(key);
            vector.addElement(parameters.get(key));
        }

        return request(method, url, vector, authorize);
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
                               final Vector parameters, final boolean authorize)
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
            token = (String) responses.get("oauth_token");
            tokenSecret = (String) responses.get("oauth_token_secret");
        }

        return request(method, url, parameters, authorize, token,
                       tokenSecret);
    }


    /**
     *
     * @param method request method
     * @param url nomalized request URL
     * @param parameters parameters
     * @param authorize flag authenticate requirement
     * @param token token
     * @param tokenSecret token secret
     * @return resource stream
     * @throws Exception if an error occurs
     */
    private InputStream request(final String method, final String url,
                                final Vector parameters,
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
        final Enumeration e = parameters.elements();
        if (e.hasMoreElements()) {
            buffer.append(Encode.url((String) e.nextElement())).append("=").
                append(Encode.url((String) e.nextElement()));
        }
        while (e.hasMoreElements()) {
            buffer.append("&").append(Encode.url((String) e.nextElement())).
                append("=").append(Encode.url((String) e.nextElement()));
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
     * Returns current user's id. An IllegalStateException will be thrown if
     * this client is not signed in.
     *
     * @return current user's id
     */
    public String getUserId() {

        if (!isSignedIn()) {
            throw new IllegalStateException("not signed in");
        }

        return (String) responses.get("user_id");
    }


    /**
     * Returns current user's screen name. An IllegalStateException will be
     * thrown if this client is not signed in.
     *
     * @return current user's screen name
     */
    public String getScreenName() {

        if (!isSignedIn()) {
            throw new IllegalStateException("not signed in");
        }

        return (String) responses.get("screen_name");
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

        return responses.containsKey("oauth_token")
               && responses.containsKey("oauth_token_secret");
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
                               final Vector parameters, final String token,
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

        final Vector vector = new Vector(parameters.size() + 14);
        for (Enumeration e = parameters.elements(); e.hasMoreElements();) {
            vector.addElement(e.nextElement());
        }

        // -------------------------------------------------- oauth_consumer_key
        vector.addElement("oauth_consumer_key");
        vector.addElement(consumerKey);

        // --------------------------------------------------------- oauth_token
        vector.addElement("oauth_token");
        vector.addElement(token);

        // ----------------------------------------------------- oauth_timestamp
        long timestamp = System.currentTimeMillis();
        vector.addElement("oauth_timestamp");
        vector.addElement(Long.toString(timestamp / 1000L));

        // --------------------------------------------------------- oauth_nonce
        final byte[] nonce = new byte[16];
        for (int i = 7; i >= 0; i--) {
            nonce[i] = (byte) (timestamp & 0xFFL);
            timestamp >>>= 8;
        }
        long l = random.nextLong();
        for (int i = 15; i >= 8; i--) {
            nonce[i] = (byte) (l & 0xFFL);
            l >>>= 8;
        }
        vector.addElement("oauth_nonce");
        vector.addElement(Encode.base16(nonce));

        // ---------------------------------------------- oauth_signature_method
        vector.addElement("oauth_signature_method");
        vector.addElement("HMAC-SHA1");

        // ------------------------------------------------------- oauth_version
        vector.addElement("oauth_version");
        vector.addElement("1.0");

        // ----------------------------------------------------- oauth_signature
        final String signature = sign(method, url, vector, tokenSecret);
        vector.addElement("oauth_signature");
        vector.addElement(signature);

        final StringBuffer buffer =
            new StringBuffer("OAuth realm=\"" + url + "\"");
        for (Enumeration e = vector.elements(); e.hasMoreElements();) {
            final String key = (String) e.nextElement();
            final String value = (String) e.nextElement();
            if (parameters.contains(key)) {
                continue;
            }
            buffer.append(", ").append(Encode.percent(key)).append("=\"").
                append(Encode.percent(value)).append("\"");
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
                          final Vector parameters, final String tokenSecret)
        throws Exception {

        final String[] array = new String[parameters.size()];

        // ------------------------------------------------------ PERCENT ENCODE
        int k = 0;
        for (Enumeration e = parameters.elements(); e.hasMoreElements();) {
            array[k++] = Encode.percent((String) e.nextElement());
        }

        // ---------------------------------------------------------------- SORT
        boolean swapped;
        String tmp;
        for (int i = array.length - 1; i >= 3;) {
            final String source = array[i];
            swapped = false;
            for (int j = 1; j <= i - 2; j += 2) {
                final String target = array[j];
                if (target.compareTo(source) > 0) {
                    tmp = array[j - 1];
                    array[j - 1] = array[i - 1];
                    array[i - 1] = tmp;
                    tmp = array[j];
                    array[j] = array[i];
                    array[i] = tmp;
                    swapped = true;
                    break;
                }
            }
            if (!swapped) {
                i -= 2;
            }
        }
        for (int i = array.length - 2; i >= 2;) {
            final String source = array[i];
            swapped = false;
            for (int j = 0; j <= i - 2; j += 2) {
                final String target = array[j];
                if (target.compareTo(source) > 0) {
                    tmp = array[j];
                    array[j] = array[i];
                    array[i] = tmp;
                    tmp = array[j + 1];
                    array[j + 1] = array[i + 1];
                    array[i + 1] = tmp;
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
        if (array.length > 0) {
            buffer.append(array[0]).append("=").append(array[1]);
            for (int i = 2; i < array.length - 1; i += 2) {
                buffer.append("&").append(array[i]).append("=").
                    append(array[i + 1]);
            }
        }

        // ----------------------------------------------- SIGNATURE BASE STRING
        final byte[] input = (Encode.percent(method) + "&"
                              + Encode.percent(url) + "&"
                              + Encode.percent(buffer.toString())).getBytes();

        // ----------------------------------------------------------------- KEY
        final byte[] key = (Encode.percent(consumerSecret) + "&"
                            + Encode.percent(tokenSecret)).getBytes();

        // ----------------------------------------------------------- SIGNATURE
        final byte[] authentication = authenticate(key, input);

        return Encode.base64(authentication);
    }


    /** consumer key. */
    private final String consumerKey;


    /** consumer secret. */
    private final String consumerSecret;


    /** random. */
    private final Random random = new Random();


    /** responses */
    private final Hashtable responses = new Hashtable();
}
