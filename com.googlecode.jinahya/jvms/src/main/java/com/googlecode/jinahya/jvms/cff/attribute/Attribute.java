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


package com.googlecode.jinahya.jvms.cff.attribute;


import com.googlecode.jinahya.jvms.cff.Classfilea;
import com.googlecode.jinahya.jvms.cff.DataAccessible;

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
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class Attribute implements DataAccessible {


    @Override
    public void read(final DataInput input) throws IOException {

        nameIndex = input.readUnsignedShort();

        final byte[] bytes = new byte[input.readInt()];
        input.readFully(bytes);

        final DataInputStream dis =
            new DataInputStream(new ByteArrayInputStream(bytes));
        readContent(dis);
    }


    protected abstract void readContent(DataInput input) throws IOException;


    @Override
    public void write(final DataOutput output) throws IOException {

        output.writeShort(nameIndex);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final DataOutputStream dos = new DataOutputStream(baos);
        writeContent(dos);
        dos.flush();

        final byte[] bytes = baos.toByteArray();
        output.writeInt(bytes.length);
        output.write(bytes);
    }


    protected abstract void writeContent(DataOutput output) throws IOException;


    // -------------------------------------------------------- parent ClassFile
    /**
     * Returns the parent ClassFile of this attribute.
     *
     * @return the parent ClassFile
     */
    public Classfilea getClassFile() {
        return classFile;
    }


    /**
     * Sets the parent ClassFile of this attribute.
     *
     * @param classFile the parent ClassFile
     */
    public void setClassFile(final Classfilea classFile) {
        this.classFile = classFile;
    }


    /** parent ClassFile. */
    @XmlTransient
    private Classfilea classFile;


    @XmlAttribute(required = true)
    private int nameIndex;


}

