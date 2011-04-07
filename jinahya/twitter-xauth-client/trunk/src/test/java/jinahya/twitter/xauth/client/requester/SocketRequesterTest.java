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


import jinahya.twitter.xauth.client.authenticator.BouncyCastleAuthenticator;
import jinahya.twitter.xauth.client.authenticator.JCEAuthenticator;

import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:support@minigate.net">Minigate Co., Ltd.</a>
 */
public class SocketRequesterTest extends RequesterTest<SocketRequester> {


    public SocketRequesterTest() {
        super(new SocketRequester());
    }


    @Test(enabled = true)
    public void testStatusesPublicTimeline() throws Exception {

        super.testStatusesPublicTimeline(new BouncyCastleAuthenticator());
        super.testStatusesPublicTimeline(new JCEAuthenticator());
    }
}
