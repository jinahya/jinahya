/*
 * Copyright 2013 onacit.
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
public class StackMapTable_attribute extends attribute_info {


    @Override
    protected void readInfo(final DataInput input) throws IOException {

        constant_value_index = input.readUnsignedShort();
    }


    @Override
    protected void writeInfo(final DataOutput output) throws IOException {

        output.writeShort(constant_value_index);
    }


    public int getConstantValueIndex() {

        return constant_value_index;
    }


    public void setConstantValueIndex(final int constantValueIndex) {

        this.constant_value_index = constantValueIndex;
    }


    private int constant_value_index;


}

