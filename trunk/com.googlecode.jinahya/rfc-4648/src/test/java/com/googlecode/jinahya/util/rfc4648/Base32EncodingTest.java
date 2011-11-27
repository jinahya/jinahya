/*
 * Copyright 2011 Jin Kwon.
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


package com.googlecode.jinahya.util.rfc4648;



/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class Base32EncodingTest
    extends EncodingTest<Base32, org.apache.commons.codec.binary.Base32> {


    private static org.apache.commons.codec.binary.Base32 getCommons() {

        return new org.apache.commons.codec.binary.Base32(-1, null, false);
    }


    public Base32EncodingTest() {
        super(new Base32(), getCommons(), Modifier.TO_SAME);
    }
}
