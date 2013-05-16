/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.jvm.cff;


import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class CONSTANT_Utf8_info extends cp_info {


    public static CONSTANT_Utf8_info newInstance(final byte[] bytes) {

        final CONSTANT_Utf8_info instance = new CONSTANT_Utf8_info();

        instance.bytes = bytes;

        return instance;
    }


    public CONSTANT_Utf8_info() {
        super(TAG_CONSTANT_Utf8);
    }


    @Override
    protected void readInfo(final DataInput input) throws IOException {

        final int length = input.readUnsignedShort();
        input.readFully(bytes = new byte[length]);
    }


    @Override
    protected void writeInfo(final DataOutput output) throws IOException {

        output.writeShort(bytes.length);
        output.write(bytes);
    }


    public byte[] getBytes() {
        return bytes;
    }


    public void setBytes(final byte[] bytes) {
        this.bytes = bytes;
    }


    private int length;


    private byte[] bytes;


}

