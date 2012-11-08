/*
 * Copyright 2012 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.nica.test;


import com.googlecode.jinahya.nica.util.AES;
import com.googlecode.jinahya.nica.util.AESBC;
import com.googlecode.jinahya.nica.util.HEX;
import com.googlecode.jinahya.nica.util.KVPME;
import com.googlecode.jinahya.nica.util.MACBC;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Hashtable;
import java.util.Random;
import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class MyXlet implements Xlet {


    private static final Random RANDOM = new SecureRandom();


    public void initXlet(final XletContext ctx)
        throws XletStateChangeException {
    }


    public void startXlet() throws XletStateChangeException {

        // ----------------------------------------------------------- Nica-Name
        final Hashtable names = new Hashtable();
        names.put("USER_ID", "000000");
        final String nicaName = KVPME.encode(names);
        System.out.println("[NICA] Nica-Name: " + nicaName);

        // ----------------------------------------------------------- Nica-Init
        final byte[] iv = new byte[AES.KEY_SIZE_IN_BYTES];
        RANDOM.nextBytes(iv);
        final String nicaInit = HEX.encodeToString(iv);
        System.out.println("[NICA] Nica-Init: " + nicaInit);

        // ----------------------------------------------------------- Nica-Code
        final Hashtable codes = new Hashtable();
        codes.put("NAME_en", "Kwon, Jin");
        codes.put("NAME_ko", "권 진");
        codes.put("NAME_zh", "權 辰");
        codes.put("SOCIAL_SECURITY_NUMBER", "000-00-0000");
        codes.put("CREDIT_CARD_NUMBER", "0000-0000-0000-0000");
        codes.put("ADDRESS", "46 Linden Street, Riverdale, New York");
        final String nicaBase = KVPME.encode(codes);
        System.out.println("[NICA] Nica-Base: " + nicaBase);
        final byte[] base;
        try {
            base = nicaBase.getBytes("US-ASCII");
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace(System.err);
            throw new XletStateChangeException(uee.getMessage());
        }
        final byte[] key = new byte[AES.KEY_SIZE_IN_BYTES];
        RANDOM.nextBytes(key);
        final byte[] code = new AESBC(key).encrypt(iv, base);
        final String nicaCodes = HEX.encodeToString(code);
        System.out.println("[NICA] Nica-Code: " + nicaCodes);

        // ----------------------------------------------------------- Nica-Auth
        final byte[] auth = new MACBC(key).authenticate(base);
        final String nicaAuth = HEX.encodeToString(auth);
        System.out.println("[NICA] Nica-Auth: " + nicaAuth);

        System.out.println("[NICA] I'm so happy!");
    }


    public void pauseXlet() {
    }


    public void destroyXlet(final boolean unconditional)
        throws XletStateChangeException {
    }


}

