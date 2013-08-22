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


import com.googlecode.jinahya.nica.util.Hex;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.annotation.WebListener;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
@WebListener
public class TestKeyLocator extends AbstractNicaKeyLocator {


    private static final Logger LOGGER =
        Logger.getLogger(TestKeyLocator.class.getName());


    public static final Map<String, String> NAMES;


    static {
        final Map<String, String> map = new HashMap<String, String>();
        map.put("publisherGuid", "1");
        map.put("gameGuid", "1");
        map.put("secretGuid", "1");

        NAMES = Collections.unmodifiableMap(map);
    }


    private static final byte[] KEY =
        Hex.decode("07C6FD4358244AFE0F27D254ADAB9163");

    //    private static final byte[] KEY;
//
//
//    static {
//        try {
//            final KeyGenerator keyGenerator =
//                KeyGenerator.getInstance(AesJCE.ALGORITHM);
//            keyGenerator.init(Aes.KEY_SIZE);
//            final SecretKey secretKey = keyGenerator.generateKey();
//            final byte[] encoded = secretKey.getEncoded();
//            KEY = encoded;
//        } catch (NoSuchAlgorithmException nsae) {
//            throw new InstantiationError(nsae.getMessage());
//        }
//    }

//    public static byte[] getKey() {
//
//        return Arrays.copyOf(KEY, KEY.length);
//    }
    @Override
    protected byte[] locateNicaKey(final ServletRequestAttributeEvent event)
        throws Exception {

        final Map<String, String> names = getNicaNames(event);

        for (Entry<String, String> entry : NAMES.entrySet()) {
            if (!entry.getValue().equals(names.get(entry.getKey()))) {
                LOGGER.info("names not matched!");
                return null;
            }
        }

        LOGGER.info("names matched!");

        return Arrays.copyOf(KEY, KEY.length);
    }


}
