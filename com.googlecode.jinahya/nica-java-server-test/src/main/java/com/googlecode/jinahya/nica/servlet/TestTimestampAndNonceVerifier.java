/*
 * Copyright 2013 Jin Kwon <onacit at gmail.com>.
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


package com.googlecode.jinahya.nica.servlet;


import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
@WebListener
public class TestTimestampAndNonceVerifier
    extends NicaCodesAttributeListenerL3 {


    private static final Logger LOGGER =
        Logger.getLogger(TestTimestampAndNonceVerifier.class.getName());


    // a map of <client-id> and a list of <timestamp>, <nonce>
    private static final Map<String, List<Object>> TABLE =
        Collections.synchronizedMap(new HashMap<String, List<Object>>());


    @Override
    protected void nicaCodesAdded(final ServletRequestAttributeEvent event)
        throws Exception {

        final String clientIdentity = getNicaClientIdentifier(event);
        System.out.println("clientIdentity: " + clientIdentity);
        final long requestTimestamp = getRequestTimestamp(event);
        System.out.println("requestTimestamp: " + requestTimestamp + " "
                           + new Date(requestTimestamp));
        final String requestNonce = getRequestNonce(event);
        System.out.println("requestNonce: " + requestNonce);

        if (requestTimestamp < (System.currentTimeMillis() - 10000L)) {
            final String message = "request timestamp is too old: "
                                   + new Date(requestTimestamp) + " now: "
                                   + new Date();
            System.out.println(message);
            setResponseError(event, HttpServletResponse.SC_BAD_REQUEST,
                             message);
            return;
        }

        synchronized (TABLE) {
            if (!TABLE.containsKey(clientIdentity)) {
                TABLE.put(clientIdentity, new LinkedList<Object>());
            }
            final List<Object> list = TABLE.get(clientIdentity);
            // clear old
            final long threshold = System.currentTimeMillis() - 180000L;
            for (final Iterator<Object> i = list.iterator(); i.hasNext();) {
                final long oldTimestamp = (Long) i.next();
                if (oldTimestamp < threshold) {
                    LOGGER.log(Level.INFO, "removing nonce created at {0}",
                               new Date(oldTimestamp));
                    System.out.println("oldTimestamp: " + oldTimestamp);
                    i.remove();
                    final String oldNonce = (String) i.next();
                    System.out.println("oldNonce: " + oldNonce);
                    i.remove();
                } else {
                    i.next();
                }
            }
            if (list.contains(requestNonce)) {
                setResponseError(event, HttpServletResponse.SC_BAD_REQUEST,
                                 "duplicated nonce: " + requestNonce);
                return;
            }
            list.add(Long.valueOf(requestTimestamp));
            list.add(requestNonce);
        }
    }


}
