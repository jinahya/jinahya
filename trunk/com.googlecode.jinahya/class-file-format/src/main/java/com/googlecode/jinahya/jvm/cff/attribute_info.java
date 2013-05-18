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


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public abstract class attribute_info {


    public void read(final DataInput input) throws IOException {

        attribute_name_index = input.readUnsignedShort();

        final int attribute_length = input.readInt();

        final byte[] info = new byte[attribute_length];
        input.readFully(info);
        readInfo(new DataInputStream(new ByteArrayInputStream(info)));
    }


    protected abstract void readInfo(DataInput input) throws IOException;


    public void write(final DataOutput output) throws IOException {

        output.writeShort(attribute_name_index);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        writeInfo(new DataOutputStream(baos));
        baos.flush();

        final byte[] info = baos.toByteArray();
        output.write(info);
    }


    protected abstract void writeInfo(DataOutput output) throws IOException;


    @XmlAttribute
    private int attribute_name_index;


    @XmlTransient
    protected byte[] info;


}

