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


package com.googlecode.jinahya.codec;


import java.util.HashSet;
import java.util.Set;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class HexCodecTestUtilTest {


    @Test
    public static void testNewEncodedBytes() {

        final Set<Integer> limits = new HashSet<Integer>();

        limits.add(0x30); // '0'
        limits.add(0x39); // '9'
        limits.add(0x41); // 'A'
        limits.add(0x46); // 'F;
        limits.add(0x61); // 'a'
        limits.add(0x66); // 'f'

        while (!limits.isEmpty()) {
            for (byte b : HexCodecTestUtil.newEncodedBytes()) {
                limits.remove(b & 0xFF);
            }
        }
    }


}

