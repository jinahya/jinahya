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


package com.googlecode.jinahya.nica.client;


import android.content.Context;
import com.googlecode.jinahya.nica.util.Aes;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class AndroidNicaBuilderFactoryImpl extends AndroidNicaBuilderFactory {


    @Override
    protected ClientCredential loadClientCredential(final Context context)
        throws NicaClientException {

        //final Random random = java.util.concurrent.ThreadLocalRandom.current();

        return new ClientCredential() {

            @Override
            public Map getNames() {
                final Map<String, String> names = new HashMap<String, String>();
                names.put("key1", "밸류1");
                names.put("키2", "value2");
                return names;
            }


            @Override
            public byte[] getKey() {
                final byte[] key = new byte[Aes.KEY_SIZE_IN_BYTES];
                new Random().nextBytes(key);
                return key;
            }

        };
    }


}
