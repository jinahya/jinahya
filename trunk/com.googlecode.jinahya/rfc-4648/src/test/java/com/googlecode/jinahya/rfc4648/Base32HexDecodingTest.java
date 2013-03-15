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


package com.googlecode.jinahya.rfc4648;


import org.apache.commons.codec.binary.Base32;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class Base32HexDecodingTest
    extends DecodingTest<Base32Hex, org.apache.commons.codec.binary.Base32> {


    public Base32HexDecodingTest() {
        super(Base32Hex.class, org.apache.commons.codec.binary.Base32.class);
    }


    @Override
    protected Base32 newEncoder() {
        return new Base32(-1, null, true);
    }


    @Override
    protected byte[] forBaseDecoding(byte[] commonsEncoded) {
        return commonsEncoded;
    }


}

