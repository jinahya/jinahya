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


package com.googlecode.jinahya.twitter.xauth;


import com.googlecode.jinahya.txc.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class Client {


    /**
     * Twitter Access Token URL.
     */
    private static final String ACCESS_TOKEN_URL =
        "https://api.twitter.com/oauth/access_token";


    /**
     * Creates a new instance.
     *
     * @param consumerKey consumer key
     * @param consumerSecret consumer secret
     * @param authenticator authenticator
     * @param requester requester
     */
    public Client(final String consumerKey, final String consumerSecret,
                  final Signer authenticator,
                  final Requester requester) {
        super();

        if (consumerKey == null) {
            throw new NullPointerException("null consumerKey");
        }

        if (consumerSecret == null) {
            throw new NullPointerException("null consumerSecret");
        }

        if (authenticator == null) {
            throw new NullPointerException("null authenticator");
        }

        if (requester == null) {
            throw new NullPointerException("null requester");
        }

        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.authenticator = authenticator;
        this.requester = requester;
    }


    /**
     * Signs in with given user credentials.
     *
     * @param username username
     * @param password password
     * @throws IOException if an I/O error occurs.
     * @throws XTweetException if an error occurs.
     */
    public final void signIn(final String username, final String password)
        throws IOException, XTweetException {

        if (username == null) {
            throw new NullPointerException("null username");
        }

        if (username.length() == 0) {
            throw new IllegalArgumentException("empty username");
        }

        if (password == null) {
            throw new NullPointerException("null password");
        }

        if (password.length() == 0) {
            throw new IllegalArgumentException("empty password");
        }

        signOut();

        final Vector<String> parameters = new Vector<String>(6);

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
     * Requests HTTP GET resource.
     *
     * @param url normalized request URL
     * @param parameters request parameters
     * @param requiresAuthentication authorize flag
     * @return resource stream
     * @throws IOException if an I/O error occurs.
     * @throws XTweetException if an error occurs.
     */
    public final InputStream GET(final String url,
                                 final Hashtable<String, String> parameters,
                                 final boolean requiresAuthentication)
        throws IOException, XTweetException {

        return request("GET", url, parameters, requiresAuthentication);
    }


    /**
     * Requests HTTP POST resource.
     *
     * @param url normalized request URL
     * @param parameters request parameters
     * @param requiresAuthentication authorize flag
     * @return resource stream
     * @throws IOException if an I/O error occurs.
     * @throws XTweetException if any error occurs.
     */
    public final InputStream POST(final String url,
                                  final Hashtable<String, String> parameters,
                                  final boolean requiresAuthentication)
        throws IOException, XTweetException {

        return request("POST", url, parameters, requiresAuthentication);
    }


    /**
     * Requests a resource.
     *
     * @param method request method
     * @param url normalized resource URL
     * @param parameters
     * @param requiresAuthentication flag for authentication requirement
     * @return resource stream
     * @thrwos IOException if an I/O error occurs.
     * @throws XTweetException if an error occurs.
     */
    public InputStream request(final String method, final String url,
                               final Hashtable<String, String> parameters,
                               final boolean requiresAuthentication)
        throws IOException, XTweetException {

        if (method == null) {
            throw new NullPointerException("null method");
        }

        if (url == null) {
            throw new NullPointerException("null url");
        }

        if (parameters == null) {
            throw new NullPointerException("null parameters");
        }

        if (requiresAuthentication && !isSignedIn()) {
            throw new IllegalStateException("not signed in");
        }

        final Vector<String> vector = new Vector<String>(parameters.size() * 2);
        for (Enumeration<String> keys = parameters.keys();
             keys.hasMoreElements();) {
            final String key = keys.nextElement();
            vector.addElement(key);
            vector.addElement(parameters.get(key));
        }

        return request(method, url, vector, requiresAuthentication);
    }


    /**
     * Requests resource.
     *
     * @param method request method
     * @param url normalized resource URL
     * @param parameters parameters
     * @param requiresAuthentication flag for authentication requirement
     * @return resource stream
     * @throws IOException if an I/O error occurs.
     * @throws XTweetException if an error occurs
     */
    private InputStream request(final String method, final String url,
                                final Vector<String> parameters,
                                final boolean requiresAuthentication)
        throws IOException, XTweetException {

        if (method == null) {
            throw new NullPointerException("null method");
        }

        if (url == null) {
            throw new NullPointerException("null url");
        }

        if (parameters == null) {
            throw new NullPointerException("null parameters");
        }

        if (requiresAuthentication && !isSignedIn()) {
            throw new IllegalStateException("not signed in");
        }

        String token = "";
        String tokenSecret = "";
        if (requiresAuthentication) {
            token = responses.get("oauth_token");
            tokenSecret = responses.get("oauth_token_secret");
        }

        return request(method, url, parameters, requiresAuthentication, token,
                       tokenSecret);
    }


    /**
     *
     * @param method request method
     * @param url normalized request URL
     * @param parameters parameters
     * @param authorize flag authenticate requirement
     * @param token token
     * @param tokenSecret token secret
     * @return resource stream
     * @throws IOException if an I/O error occurs.
     * @throws XTweetException if an error occurs
     */
    private InputStream request(final String method, final String url,
                                final Vector<String> parameters,
                                final boolean authorize, final String token,
                                final String tokenSecret)
        throws IOException, XTweetException {

        if (method == null) {
            throw new NullPointerException("null method");
        }

        if (url == null) {
            throw new NullPointerException("null url");
        }

        if (parameters == null) {
            throw new NullPointerException("null parameters");
        }

        if (token == null) {
            throw new NullPointerException("null token");
        }

        if (tokenSecret == null) {
            throw new NullPointerException("null tokenSecret");
        }

        String authorization = null;
        if (authorize) {
            authorization = authorize(
                method, url, parameters, token, tokenSecret);
        }

        final StringBuffer buffer = new StringBuffer();
        final Enumeration<String> e = parameters.elements();
        if (e.hasMoreElements()) {
            buffer.append(Encode.url(e.nextElement())).append("=").
                append(Encode.url(e.nextElement()));
        }
        while (e.hasMoreElements()) {
            buffer.append("&").append(Encode.url(e.nextElement())).
                append("=").append(Encode.url(e.nextElement()));
        }

        return requester.request(method, url, buffer.toString(), authorization);
    }


    /**
     * Signs out.
     */
    public final void signOut() {
        responses.clear();
    }


    /**
     * Returns current user's id. An IllegalStateException will be thrown if
     * this client is not signed in.
     *
     * @return current user's id
     */
    public final String getUserId() {

        if (!isSignedIn()) {
            throw new IllegalStateException("not signed in");
        }

        return responses.get("user_id");
    }


    /**
     * Returns current user's screen name. An IllegalStateException will be
     * thrown if this client is not signed in.
     *
     * @return current user's screen name
     */
    public final String getScreenName() {

        if (!isSignedIn()) {
            throw new IllegalStateException("not signed in");
        }

        return responses.get("screen_name");
    }


    /**
     * Checks whether currently signed in or not.
     *
     * @return signed in status
     */
    public final boolean isSignedIn() {

        if (responses.isEmpty()) {
            return false;
        }

        return responses.containsKey("oauth_token")
               && responses.containsKey("oauth_token_secret");
    }


    /**
     * Generates the authorization string.
     *
     * @param method request method
     * @param url normalized resource URL
     * @param parameters parameters
     * @param token access token
     * @param tokenSecret token secret
     * @return OAuth Authorization header value
     * @throws IOException if an I/O error occurs.
     * @throws XTweetException if an error occurs
     */
    private String authorize(final String method, final String url,
                             final Vector<String> parameters,
                             final String token, final String tokenSecret)
        throws IOException, XTweetException {

        if (method == null) {
            throw new NullPointerException("null method");
        }

        if (url == null) {
            throw new NullPointerException("null url");
        }

        if (parameters == null) {
            throw new NullPointerException("null parameters");
        }

        if (token == null) {
            throw new NullPointerException("null token");
        }

        if (tokenSecret == null) {
            throw new NullPointerException("null tokenSecret");
        }

        final Vector<String> vector =
            new Vector<String>(parameters.size() + 14);
        for (Enumeration<String> e = parameters.elements();
             e.hasMoreElements();) {
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
        long l = Double.doubleToLongBits(Math.random());
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
        for (Enumeration<String> e = vector.elements(); e.hasMoreElements();) {
            final String key = e.nextElement();
            final String value = e.nextElement();
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
     * @throws IOException if an I/O error occurs.
     * @throws XTweetException if an error occurs
     */
    private String sign(final String method, final String url,
                        final Vector<String> parameters,
                        final String tokenSecret)
        throws IOException, XTweetException {

        final String[] array = new String[parameters.size()];

        // ------------------------------------------------------ PERCENT ENCODE
        int k = 0;
        for (Enumeration<String> e = parameters.elements();
             e.hasMoreElements();) {
            array[k++] = Encode.percent(e.nextElement());
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
        final byte[] authentication = authenticator.sign(key, input);

        return Encode.base64(authentication);
    }


    /** consumer key. */
    private final String consumerKey;


    /** consumer secret. */
    private final String consumerSecret;


    /** authenticator. */
    private final Signer authenticator;


    /** requester. */
    private final Requester requester;


    /** responses. */
    private final Hashtable<String, String> responses =
        new Hashtable<String, String>();
}
