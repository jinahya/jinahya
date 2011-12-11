/*
 * Copyright 2011 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.jvms.classfile.attribute;


import com.googlecode.jinahya.jvms.classfile.DataAccessible;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class AttributeInfo implements DataAccessible {


    @Override
    public void read(final DataInput input) throws IOException {

        attributeNameIndex = input.readUnsignedShort();

        info = new byte[input.readInt()];
        input.readFully(info);
        for (byte b : info) {
            final int i = b & 0xFF;
            if (i < 0x0A) {
                System.out.print("0");
            }
            System.out.print(Integer.toHexString(i) + " ");
        }
        System.out.println();
    }


    @Override
    public void write(final DataOutput output) throws IOException {

        output.writeShort(attributeNameIndex);

        output.writeInt(info.length);
        output.write(info);
    }


    public void parse(final Attribute attribute) throws IOException {

        attributeNameIndex = attribute.nameIndex;

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final DataOutputStream dos = new DataOutputStream(baos);
        attribute.writeInfo(this, dos);
        dos.flush();
        baos.flush();
        info = baos.toByteArray();
    }


    public void print(final Attribute attribute) throws IOException {

        attribute.nameIndex = attributeNameIndex;

        final ByteArrayInputStream bais = new ByteArrayInputStream(info);
        final DataInputStream dis = new DataInputStream(bais);

        attribute.readInfo(this, dis);
    }


    protected int attributeNameIndex;


    protected byte[] info;


}

