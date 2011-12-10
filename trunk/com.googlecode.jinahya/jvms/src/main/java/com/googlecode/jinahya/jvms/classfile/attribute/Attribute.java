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


import com.googlecode.jinahya.jvms.classfile.ClassFile;
import com.googlecode.jinahya.jvms.classfile.DataAccessible;

import com.googlecode.jinahya.jvms.classfile.constant._Utf8;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class Attribute implements DataAccessible {


    public static Attribute readInstance(final DataInput input,
                                         final ClassFile classfile)
        throws IOException {

        final AttributeInfo info = new AttributeInfo();
        info.read(input);

        final _Utf8 utf8 = classfile.getConstant(
            info.getAttributeNameIndex(), _Utf8.class);
        final String name = utf8.getValue();
        final Attribute attribute = AttributeName.valueOf(name).newAttribute();
        info.print(attribute);
        attribute.setClassfile(classfile);
        return attribute;
    }


    @Override
    public final void read(final DataInput input) throws IOException {

        nameIndex = input.readUnsignedShort();

        readInfo(input);
    }


    protected abstract void readInfo(DataInput input) throws IOException;


    @Override
    public final void write(final DataOutput output) throws IOException {

        output.writeShort(nameIndex);

        writeInfo(output);
    }


    protected abstract void writeInfo(DataOutput output) throws IOException;


    // -------------------------------------------------------- parent classfile
    /**
     * Returns the parent classfile of this attribute.
     *
     * @return the parent classfile
     */
    public final ClassFile getClassfile() {

        if (attribute != null) {
            return attribute.getClassfile();
        }

        return classfile;
    }


    /**
     * Sets the parent classfile of this attribute.
     *
     * @param classfile the parent classfile
     */
    public final void setClassfile(final ClassFile classfile) {
        this.classfile = classfile;
    }


    // -------------------------------------------------------- parent attribute
    /**
     * Returns the parent attribute of this attribute.
     *
     * @return the parent attribute
     */
    public final Attribute getAttribute() {
        return attribute;
    }


    /**
     * Sets the parent attribute of this attribute.
     *
     * @param attribute the parent attribute
     */
    public final void setAttribute(final Attribute attribute) {
        this.attribute = attribute;
    }


    /** parent classfile. */
    @XmlTransient
    protected ClassFile classfile;


    /** parent attribute. */
    @XmlTransient
    protected Attribute attribute;


    @XmlAttribute(required = true)
    private int nameIndex;


}

