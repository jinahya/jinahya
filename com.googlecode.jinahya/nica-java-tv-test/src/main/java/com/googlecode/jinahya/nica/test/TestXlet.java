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


import com.googlecode.jinahya.nica.CodeKeys;
import com.googlecode.jinahya.nica.Headers;
import com.googlecode.jinahya.nica.JavaTVHeaders;
import com.googlecode.jinahya.nica.util.Aes;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;
import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class TestXlet implements Xlet {


    private static final Random RANDOM = new SecureRandom();


    public void initXlet(final XletContext ctx)
        throws XletStateChangeException {

        final Hashtable names = new Hashtable();
        names.put("NAME_en", "Kwon, Jin");
        names.put("NAME_ko", "권 진");
        names.put("NAME_zh", "權 辰");

        final byte[] key = new byte[Aes.KEY_SIZE_IN_BYTES];
        RANDOM.nextBytes(key);

        headers = JavaTVHeaders.newInstance(names, ctx, key);
    }


    public void startXlet() throws XletStateChangeException {

        headers.putConstantEntry(CodeKeys.DEVICE_ID, "DEVICE_ID");
        headers.putConstantEntry(CodeKeys.SYSTEM_ID, "SYSTEM_ID");

        for (int i = 0; i < 10; i++) {

            headers.putVariableEntry("ROUND", Integer.toString(i));

            headers.putVariableEntry("SOCIAL_SECURITY_NUMBER", "000-00-0000");
            headers.putVariableEntry("CREDIT_CARD_NUMBER",
                                     "0000-0000-0000-0000");
            headers.putVariableEntry("ADDRESS",
                                     "46 Linden Street, Riverdale, New York");

            headers.putVolatileEntry(CodeKeys.USER_USERNAME, "USERNAME");
            headers.putVolatileEntry(CodeKeys.USER_PASSWORD, "PASSWORD");

            final Hashtable entries = ((JavaTVHeaders) headers).getEntries();

            final Enumeration keys = entries.keys();
            while (keys.hasMoreElements()) {
                final String fieldName = (String) keys.nextElement();
                final String fieldValue = (String) entries.get(fieldName);
                System.out.println(fieldName + "[" + i + "]: " + fieldValue);
            }
        }
    }


    public void pauseXlet() {
    }


    public void destroyXlet(final boolean unconditional)
        throws XletStateChangeException {
    }


    private Headers headers;


}

