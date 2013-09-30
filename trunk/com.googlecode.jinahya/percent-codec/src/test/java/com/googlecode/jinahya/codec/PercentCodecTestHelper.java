/*
 * Copyright 2011 <a href="mailto:jinahya@gmail.com">Jin Kwon</a>.
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


package com.googlecode.jinahya.codec;


import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import net.oauth.OAuth;
import org.apache.commons.lang3.RandomStringUtils;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class PercentCodecTestHelper {


    private static Random random() {

        return ThreadLocalRandom.current();
    }


    /**
     * Returns an {@code UTF-8} encoded string.
     *
     * @param maxlen maximum string length
     *
     * @return a random string
     */
    public static String decodedString(final int maxlen) {

        return RandomStringUtils.random(random().nextInt(maxlen));
    }


    public static byte[] decodedBytes(final int maxlen) {

        return decodedString(maxlen).getBytes(StandardCharsets.UTF_8);
    }


    public static String encodedString(final int maxlen) {

        return OAuth.percentEncode(decodedString(maxlen));
    }


    public static byte[] encodedBytes(final int maxlen) {

        return encodedString(maxlen).getBytes(StandardCharsets.US_ASCII);
    }


}
