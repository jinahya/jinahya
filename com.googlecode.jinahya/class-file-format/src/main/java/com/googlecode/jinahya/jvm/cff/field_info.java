/*
 * Copyright 2013 Jin Kwon <onacit at gmail.com>.
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
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class field_info {


    public void read(final DataInput input) throws IOException {

        access_flags = input.readUnsignedShort();

        name_index = input.readUnsignedShort();

        descriptor_index = input.readUnsignedShort();

        final int attributes_count = input.readUnsignedShort();

        attributes.clear();
        ((ArrayList) attributes).ensureCapacity(attributes_count);
        for (int i = 0; i < attributes_count; i++) {
            final attribute_info info = new attribute_info();
            info.read(input);
            attributes.add(info);
        }
    }


    public void write(final DataOutput output) throws IOException {

        output.writeShort(access_flags);

        output.writeShort(name_index);

        output.writeShort(descriptor_index);

        output.writeShort(attributes.size());
        
        for (attribute_info info : attributes) {
            info.write(output);
        }
    }


    private int access_flags;


    private int name_index;


    private int descriptor_index;


    private int attributes_count;


    private final List<attribute_info> attributes = new ArrayList<>();


}

