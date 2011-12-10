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


package com.googlecode.jinahya.jvms.classfile;


import com.googlecode.jinahya.jvms.classfile.attribute.Attribute;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlType(propOrder = {"accessFlags", "nameIndex", "descriptorIndex",
                      "attributes"})
public class Method implements DataAccessible {


    @Override
    public void read(final DataInput input) throws IOException {

        accessFlags = input.readUnsignedShort();

        nameIndex = input.readUnsignedShort();

        descriptorIndex = input.readUnsignedShort();

        final int attributesCount = input.readUnsignedShort();
        getAttributes().clear();
        for (int i = 0; i < attributesCount; i++) {
            getAttributes().add(Attribute.readInstance(input, classfile));
        }
    }


    @Override
    public void write(final DataOutput output) throws IOException {
    }


    // --------------------------------------------------------------- classfile
    public final ClassFile getClassfile() {
        return classfile;
    }


    public final void setClassfile(final ClassFile classfile) {
        this.classfile = classfile;
    }


    // ------------------------------------------------------------- accessFlags
    public int getAccessFlags() {
        return accessFlags;
    }


    // --------------------------------------------------------------- nameIndex
    public int getNameIndex() {
        return nameIndex;
    }


    // --------------------------------------------------------- descriptorIndex
    public int getDescriptorIndex() {
        return descriptorIndex;
    }


    // -------------------------------------------------------------- attributes
    private List<Attribute> getAttributes() {

        if (attributes == null) {
            attributes = new ArrayList<Attribute>();
        }

        return attributes;
    }


    /** parent classfile. */
    @XmlTransient
    private ClassFile classfile;


    @XmlElement(required = true)
    private int accessFlags;


    @XmlElement(required = true)
    private int nameIndex;


    @XmlElement(required = true)
    private int descriptorIndex;


    @XmlElement(name = "attribute")
    @XmlElementWrapper(required = true)
    private List<Attribute> attributes;


}

