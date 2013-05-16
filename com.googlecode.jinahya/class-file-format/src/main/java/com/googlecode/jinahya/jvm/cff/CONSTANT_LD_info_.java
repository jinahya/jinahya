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
abstract class CONSTANT_LD_info_ extends cp_info {


    public CONSTANT_LD_info_(final int tag) {
        super(tag);
    }


    @Override
    protected void readInfo(final DataInput input) throws IOException {

        high_bytes = input.readInt();
        low_bytes = input.readInt();
    }


    @Override
    protected void writeInfo(final DataOutput output) throws IOException {

        output.writeInt(high_bytes);
        output.writeInt(low_bytes);
    }


    private int high_bytes;


    private int low_bytes;


}

