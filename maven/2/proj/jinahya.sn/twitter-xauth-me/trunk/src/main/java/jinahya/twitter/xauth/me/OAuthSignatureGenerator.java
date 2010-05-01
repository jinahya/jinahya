/*
 *  Copyright 2010 Jin Kwon.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package jinahya.twitter.xauth.me;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import jinahya.rfc4648.Base64;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.macs.HMac;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class OAuthSignatureGenerator {


    private static final String OAUTH_SIGNATURE_METHOD = "HMAC-SHA1";


    /**
     * Generates the signature base string.
     *
     * @param method HTTP method (UPPER CASE)
     * @param url the normalized url
     * @param parameters parameter
     * @return signature base string
     * @throws UnsupportedEncodingException if "UTF-8" is not recognized
     */
    public static String generateSignatureBaseString(final String method,
                                                     final String url,
                                                     final Hashtable parameters)
        throws UnsupportedEncodingException {

        final Vector vector = new Vector();

        synchronized (parameters) {

            final Enumeration keys = parameters.keys();
            while (keys.hasMoreElements()) {
                final String key = (String) keys.nextElement();
                final String name = encode(key);
                final String value = encode((String) parameters.get(key));
                for (int i = 0; i < vector.size(); i += 2) {
                    if (((String) vector.elementAt(i)).compareTo(name) > 0) {
                        vector.insertElementAt(name, i);
                        vector.insertElementAt(value, i + 1);
                        break;
                    }
                }
                if (vector.indexOf(name) == -1) {
                    vector.addElement(name);
                    vector.addElement(value);
                }
            }
        }

        final StringBuffer buffer = new StringBuffer();
        if (!vector.isEmpty()) {
            buffer.append(vector.elementAt(0) + "=" + vector.elementAt(1));
        }
        for (int i = 2; i < vector.size(); i += 2) {
            buffer.append("&" + vector.elementAt(i) + "="
                          + vector.elementAt(i + 1));
        }

        return encode(method) + "&" + encode(url) + "&" +
               encode(buffer.toString());
    }



    public static String generateSignatureString(final String consumerSecret,
                                                 final String tokenSecret,
                                                 final String signatureBaseString)
        throws UnsupportedEncodingException, IOException {

        final Digest digest = new SHA1Digest();

        final Mac mac = new HMac(digest);

        final String key= encode(consumerSecret) + "&" + encode(tokenSecret);
        final CipherParameters parameter =
            new KeyParameter(key.getBytes("US-ASCII"));

        mac.init(parameter);

        final byte[] input = signatureBaseString.getBytes("US-ASCII");
        mac.update(input, 0, input.length);

        final byte[] output = new byte[mac.getMacSize()];
        mac.doFinal(output, 0);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        new Base64().encode(new ByteArrayInputStream(output),
                            new OutputStreamWriter(baos, "US-ASCII"));
        baos.flush();
        return new String(baos.toByteArray(), "US-ASCII");
    }


    /**
     *
     * @param string
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String encode(final String string)
        throws UnsupportedEncodingException {

        if (string == null) {
            throw new NullPointerException("string");
        }

        final byte[] bytes = string.getBytes("UTF-8");

        final StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {

            final char ch = (char) (bytes[i] & 0xFF);

            // UPPER CASE ALPHA
            if (ch >= 0x41 && ch <= 0x5A) {
                buffer.append(ch);
                continue;
            }

            // LOWER CASE ALPHA
            if (ch >= 0x61 && ch <= 0x7A) {
                buffer.append(ch);
                continue;
            }

            // DIGIT
            if (ch >= 0x30 && ch <= 0x39) {
                buffer.append(ch);
                continue;
            }

            // RESERVED
            if (ch == 0x2D       // '-'
                || ch == 0x2E    // '.'
                || ch == 0x5F    // '_'
                || ch == 0x7E) { // '~'
                buffer.append(ch);
                continue;
            }

            buffer.append('%');
            if (ch <= 0x0F) {
                buffer.append('0');
            }
            buffer.append(Integer.toHexString(ch).toUpperCase());
        }

        return buffer.toString();
    }


    /** PRIVTE. */
    private OAuthSignatureGenerator() {
        super();
    }
}
