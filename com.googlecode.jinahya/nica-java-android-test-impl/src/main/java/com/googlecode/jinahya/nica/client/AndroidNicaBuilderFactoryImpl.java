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
import android.content.res.AssetManager;
import com.googlecode.jinahya.nica.util.Hex;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class AndroidNicaBuilderFactoryImpl extends AndroidNicaBuilderFactory {


    @Override
    protected ClientCredential loadClientCredential(final Context context)
        throws NicaClientException {

        final AssetManager assets = context.getAssets();

        final StringBuilder builder = new StringBuilder();
        try {
            final BufferedReader reader = new BufferedReader(
                new InputStreamReader(assets.open("nica.json"), "UTF-8"));
            try {
                for (String line; (line = reader.readLine()) != null;) {
                    builder.append(line);
                }
            } finally {
                reader.close();
            }
        } catch (IOException ioe) {
            throw new NicaClientException(ioe);
        }

        final String publisherGuid;
        final String gameGuid;
        final String secretGuid;
        final byte[] key;
        try {
            final JSONObject object = new JSONObject(builder.toString());
            publisherGuid = Integer.toString(object.getInt("publisherGuid"));
            gameGuid = Integer.toString(object.getInt("gameGuid"));
            secretGuid = Integer.toString(object.getInt("secretGuid"));
            key = Hex.decode(object.getString("secretCode"));
        } catch (JSONException jsone) {
            throw new NicaClientException(jsone);
        }

        return new ClientCredential() {

            @Override
            public Map getNames() {
                final Map<String, String> names = new HashMap<String, String>();
                names.put("publisherGuid", publisherGuid);
                names.put("gameGuid", gameGuid);
                names.put("secretGuid", secretGuid);
                return names;
            }


            @Override
            public byte[] getKey() {
                return key;
            }

        };
    }


}
