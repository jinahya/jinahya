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


package com.googlecode.jinahya.txc;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Logger;

import junit.framework.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class ClientTest {


    protected static final String METHOD = "GET";


    protected static final String URL = "http://photos.example.net/photos";


    protected static final List<String> PARAMETERS = Arrays.asList(
        "size", "original", "file", "vacation.jpg", "가나", "다라");


    protected static final String CONSUMER_KEY = "dpf43f3p2l4k3l03";


    protected static final String CONSUMER_SECRET = "kd94hf93k423kf44";


    protected static final String TOKEN = "nnch734d00sl2jdk";


    protected static final String TOKEN_SECRET = "pfkkdhi9sl3r4s00";


    protected static final String TIMESTAMP = "1191242096";


    protected static final String NONCE = "kllo9940pd9333jh";


    protected static final String PROTOCOL_VERSION = "1.0";


    protected static final String SIGNATURE_BASE_STRING =
        "GET&http%3A%2F%2Fphotos.example.net%2Fphotos&%25EA%25B0%2580%25EB%2582"
        + "%2598%3D%25EB%258B%25A4%25EB%259D%25BC%26file%3Dvacation.jpg%26oauth"
        + "_consumer_key%3Ddpf43f3p2l4k3l03%26oauth_nonce%3Dkllo9940pd9333jh%26"
        + "oauth_signature_method%3DHMAC-SHA1%26oauth_timestamp%3D1191242096%26"
        + "oauth_token%3Dnnch734d00sl2jdk%26oauth_version%3D1.0%26size%3Dorigin"
        + "al";


    protected static final String KEY = "kd94hf93k423kf44&pfkkdhi9sl3r4s00";


    protected static final String OAUTH_SIGNATURE =
        "GUUqqzmvcZ6ZJNNTIc/xZghY1Uw=";


    protected static final String AUTHORIZATION =
        "OAuth realm=\"http://photos.example.net/photos\""
        + ", oauth_consumer_key=\"dpf43f3p2l4k3l03\""
        + ", oauth_token=\"nnch734d00sl2jdk\""
        + ", oauth_nonce=\"kllo9940pd9333jh\""
        + ", oauth_timestamp=\"1191242096\""
        + ", oauth_signature_method=\"HMAC-SHA1\""
        + ", oauth_version=\"1.0\""
        + ", oauth_signature=\"GUUqqzmvcZ6ZJNNTIc%2FxZghY1Uw%3D\"";


    private static final Logger LOGGER =
        Logger.getLogger("com.googlecode.jinahya.txc");


    public ClientTest(final Authenticator authenticator,
                      final Requester requester) {
        super();

        this.authenticator = authenticator;
        this.requester = requester;
    }


    /**
     * Creates a client.
     *
     * @return a new client.
     */
    protected final Client createClient() {

        final String consumerKey = System.getProperty("consumerKey");
        if (consumerKey == null) {
            throw new SkipException("no consumerKey");
        }

        final String consumerSecret = System.getProperty("consumerSecret");
        if (consumerSecret == null) {
            throw new SkipException("no consumerSecret");
        }

        return new Client(consumerKey, consumerSecret, authenticator,
                          requester);
    }


    protected final void signIn(final Client client)
        throws IOException, TXCException {

        final String username = System.getProperty("username");
        if (username == null) {
            throw new SkipException("no username");
        }

        final String password = System.getProperty("password");
        if (password == null) {
            throw new SkipException("no password");
        }

        client.signIn(username, password);
    }


    protected static void print(final InputStream response) throws IOException {
        final BufferedReader reader =
            new BufferedReader(new InputStreamReader(response, "UTF-8"));
        try {
            for (String line = null; (line = reader.readLine()) != null;) {
                System.out.println(line);
            }
        } finally {
            reader.close();
        }
    }


    @Test
    public void testSignIn() throws IOException, TXCException {

        final Client client = createClient();
        signIn(client);
        LOGGER.info("signed in");
        LOGGER.info("userId: " + client.getUserId());
        LOGGER.info("screenName: " + client.getScreenName());

        Assert.assertTrue(client.isSignedIn());

        final Hashtable<String, String> parameters =
            new Hashtable<String, String>();

        {
            final InputStream response = client.GET(
                "https://api.twitter.com/1/statuses/home_timeline.json",
                parameters, true);
            try {
                print(response);
            } finally {
                response.close();
            }
        }

        {
            parameters.clear();
            parameters.put("status", "hello");
            final InputStream response = client.POST(
                "https://api.twitter.com/1/statuses/update.xml", parameters,
                true);
            try {
                print(response);
            } finally {
                response.close();
            }
        }

        
        client.signOut();
        Assert.assertFalse(client.isSignedIn());
    }


    /** authenticator. */
    private final Authenticator authenticator;


    /** requester. */
    private final Requester requester;
}
