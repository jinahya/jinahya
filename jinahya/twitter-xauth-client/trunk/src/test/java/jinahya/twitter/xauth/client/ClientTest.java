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


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import jinahya.twitter.xauth.client.authenticator.Authenticator;
import jinahya.twitter.xauth.client.requester.Requester;

import org.testng.Assert;
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


    private static final Properties PROPERTIES = new Properties();


    static {
        final InputStream stream =
            ClientTest.class.getResourceAsStream("properties.xml");
        if (stream != null) {
            try {
                try {
                    PROPERTIES.loadFromXML(stream);
                } finally {
                    stream.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace(System.err);
            }
        }
    }


    protected static boolean isPropertiesEmpty() {
        return PROPERTIES.isEmpty();
    }


    protected static String getProperty(final String key) {
        return PROPERTIES.getProperty(key);
    }


    protected static Client signIn(final Client client) throws Exception {

        if (client == null) {
            throw new IllegalArgumentException("null client");
        }

        if (PROPERTIES.isEmpty()) {
            throw new IllegalStateException("PROPERTIES is empty");
        }

        client.signIn(getProperty("username"), getProperty("password"));

        return client;
    }


    protected static void print(final InputStream stream) throws IOException {
        final BufferedReader reader =
            new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        try {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } finally {
            reader.close();
        }
    }


    protected static void request(final Client client, final String method,
                                  final String url,
                                  final Map<String, String> parameters,
                                  final boolean authorize) {
        try {
            final InputStream response =
                client.request(method, url, parameters, authorize);
            try {
                print(response);
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }


    public ClientTest(final Requester requester,
                      final Authenticator authenticator) {
        super();

        this.requester = requester;
        this.authenticator = authenticator;
    }


    private Client create() {
        return create(null, null);
    }


    private Client create(String consumerKey, String consumerSecret) {

        if (PROPERTIES.isEmpty()) {
            throw new IllegalStateException("PROPERTIES is empty");
        }

        if (consumerKey == null) {
            consumerKey = PROPERTIES.getProperty("consumerKey");
        }

        if (consumerSecret == null) {
            consumerSecret = PROPERTIES.getProperty("consumerSecret");
        }

        return new Client(consumerKey, consumerSecret) {


            @Override
            public InputStream request(final String method, final String url,
                                       final String parameters,
                                       final String authorization)
                throws Exception {

                return ClientTest.this.requester.request(
                    method, url, parameters, authorization);
            }


            @Override
            public byte[] authenticate(final byte[] key, final byte[] input)
                throws Exception {

                return ClientTest.this.authenticator.authenticate(key, input);
            }
        };
    }


    //@Test
    public void testAuthorize() throws Exception {

        if (PROPERTIES.isEmpty()) {
            return;
        }

        final Client client = create(CONSUMER_KEY, CONSUMER_SECRET);

        final List<String> parameters = new LinkedList<String>(PARAMETERS);
        parameters.add("oauth_timestamp");
        parameters.add(TIMESTAMP);
        parameters.add("oauth_nonce");
        parameters.add(NONCE);

        final String authorization = client.authorize(
            METHOD, URL, parameters, TOKEN, TOKEN_SECRET);

        System.out.println("AUTHORIZATION.ACTUAL  : " + authorization);

        System.out.println("AUTHORIZATION.EXPECTED: " + AUTHORIZATION);
    }


    @Test(enabled = false)
    public void testStatusesPublicTimeline() throws Exception {

        if (PROPERTIES.isEmpty()) {
            return;
        }

        final Client client = create();

        final Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("include_entities", "true");

        request(client, "GET",
                "http://api.twitter.com/1/statuses/public_timeline.xml",
                parameters, false);
    }


    @Test(enabled = false)
    public void testSignIn() throws Exception {

        if (PROPERTIES.isEmpty()) {
            return;
        }

        final Client client = create();

        final long start = System.currentTimeMillis();
        signIn(client);
        final long finish = System.currentTimeMillis();
        System.out.println(getClass() + "#testSignin -> " + (finish - start)
                           + " ms");
        Assert.assertTrue(client.isSignedIn());

        client.signOut();
        Assert.assertFalse(client.isSignedIn());
    }


    @Test(enabled = false)
    public void testStatusesUpdate() throws Exception {

        if (PROPERTIES.isEmpty()) {
            return;
        }

        final Client client = signIn(create());

        final Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("status", "xAuth; " + getClass().getSimpleName());

        request(client, "POST", "http://api.twitter.com/1/statuses/update.xml",
                parameters, true);
    }


    @Test(enabled = false)
    public void testStatusesHomeTimeline() throws Exception {

        if (PROPERTIES.isEmpty()) {
            return;
        }

        final Client client = signIn(create());

        final Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("include_entities", "true");

        request(client, "GET",
                "http://api.twitter.com/1/statuses/home_timeline.xml",
                parameters, true);
    }


    private final Requester requester;


    private final Authenticator authenticator;
}
