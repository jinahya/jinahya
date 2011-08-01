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


package jinahya.twitter.xauth.client.requester;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;

import jinahya.twitter.xauth.client.TestProperties;
import jinahya.twitter.xauth.client.Client;
import jinahya.twitter.xauth.client.ClientFactory;
import jinahya.twitter.xauth.client.authenticator.Authenticator;


/**
 *
 * @author <a href="mailto:support@minigate.net">Minigate Co., Ltd.</a>
 */
public class RequesterTest<R extends Requester> {


    /**
     * 
     * @param stream
     * @throws IOException 
     */
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


    public RequesterTest(final R requester) {
        super();

        if (requester == null) {
            throw new IllegalArgumentException("null requester");
        }

        this.requester = requester;
    }


    /**
     * 
     * @param authenticator
     * @throws Exception 
     */
    protected void testStatusesPublicTimeline(final Authenticator authenticator)
        throws Exception {

        if (TestProperties.isEmpty()) {
            return;
        }

        final Client client = ClientFactory.newClient(authenticator, requester);


        final Hashtable<String, String> parameters =
            new Hashtable<String, String>();
        parameters.put("trim_user", "true");

        final InputStream response = client.request(
            "GET", "http://api.twitter.com/1/statuses/public_timeline.xml",
            parameters, false);
        try {
            print(response);
        } finally {
            response.close();
        }
    }


    /** requester. */
    private final Requester requester;
}
