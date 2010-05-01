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


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;

import static org.testng.Assert.*;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class OAuthSignatureGeneratorTest {


    private Hashtable<String, String> getParameters() {
        final Hashtable<String, String> parameters =
            new Hashtable<String, String>();
        parameters.put("file", "vacation.jpg");
        parameters.put("oauth_consumer_key", "dpf43f3p2l4k3l03");
        parameters.put("oauth_nonce", "kllo9940pd9333jh");
        parameters.put("oauth_signature_method", "HMAC-SHA1");
        parameters.put("oauth_timestamp", "1191242096");
        parameters.put("oauth_token", "nnch734d00sl2jdk");
        parameters.put("oauth_version", "1.0");
        parameters.put("size", "original");
        parameters.put("가나", "다라");
        return parameters;
    }


    @Test
    public void testGenerateSignatureBaseString() throws UnsupportedEncodingException {
        final String expected = "GET&http%3A%2F%2Fphotos.example.net%2Fphotos&%25EA%25B0%2580%25EB%2582%2598%3D%25EB%258B%25A4%25EB%259D%25BC%26file%3Dvacation.jpg%26oauth_consumer_key%3Ddpf43f3p2l4k3l03%26oauth_nonce%3Dkllo9940pd9333jh%26oauth_signature_method%3DHMAC-SHA1%26oauth_timestamp%3D1191242096%26oauth_token%3Dnnch734d00sl2jdk%26oauth_version%3D1.0%26size%3Doriginal";
        final String actual = OAuthSignatureGenerator.generateSignatureBaseString("GET", "http://photos.example.net/photos", getParameters());
        assertEquals(actual, expected);
    }


    @Test
    public void testGenerateSignatureString() throws UnsupportedEncodingException, IOException {
        final String consumerSecret = "kd94hf93k423kf44";
        final String tokenSecret = "pfkkdhi9sl3r4s00";
        final String signatureBaseString = OAuthSignatureGenerator.generateSignatureBaseString("GET", "http://photos.example.net/photos", getParameters());

        final String expected = "GUUqqzmvcZ6ZJNNTIc/xZghY1Uw=";
        final String actual = OAuthSignatureGenerator.generateSignatureString(consumerSecret, tokenSecret, signatureBaseString);
        assertEquals(actual, expected);
    }


    @Test
    public void testEncode()
        throws NoSuchMethodException, IllegalAccessException,
               InvocationTargetException {

        Method method = OAuthSignatureGenerator.class.getDeclaredMethod(
            "encode", new Class[] {String.class});
        method.setAccessible(true);

        final String[] strings = new String[] {
            "abc", "abc",
            "가나", "%EA%B0%80%EB%82%98",
            "日本の食べ物はおいしいですが？", "%E6%97%A5%E6%9C%AC%E3%81%AE%E9%A3%9F%E3%81%B9%E7%89%A9%E3%81%AF%E3%81%8A%E3%81%84%E3%81%97%E3%81%84%E3%81%A7%E3%81%99%E3%81%8C%EF%BC%9F",
            "Honra siempre a tus padres, y verás muchas navidades.", "Honra%20siempre%20a%20tus%20padres%2C%20y%20ver%C3%A1s%20muchas%20navidades."
        };

        for (int i = 0; i < strings.length; i += 2) {
            final String expected = strings[i + 1];
            final String actual =
                (String) method.invoke(null, new Object[] {strings[i]});
            assertEquals(actual, expected);
        }
    }
}
