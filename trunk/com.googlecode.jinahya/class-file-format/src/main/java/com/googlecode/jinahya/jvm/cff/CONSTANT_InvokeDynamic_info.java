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
public class CONSTANT_InvokeDynamic_info extends cp_info {


    public CONSTANT_InvokeDynamic_info() {
        super(TAG_CONSTANT_InvokeDynamic);
    }


    @Override
    protected void readInfo(final DataInput input) throws IOException {

        bootstrap_method_attr_index = input.readUnsignedShort();

        name_and_type_index = input.readUnsignedShort();
    }


    @Override
    protected void writeInfo(final DataOutput output) throws IOException {

        output.writeShort(bootstrap_method_attr_index);

        output.writeShort(name_and_type_index);
    }


    public int getBootstrap_method_attr_index() {

        return bootstrap_method_attr_index;
    }


    public void setBootstrap_method_attr_index(
        final int bootstrap_method_attr_index) {

        this.bootstrap_method_attr_index = bootstrap_method_attr_index;
    }


    public int getName_and_type_index() {

        return name_and_type_index;
    }


    public void setName_and_type_index(final int name_and_type_index) {

        this.name_and_type_index = name_and_type_index;
    }


    private int bootstrap_method_attr_index;


    private int name_and_type_index;


}

