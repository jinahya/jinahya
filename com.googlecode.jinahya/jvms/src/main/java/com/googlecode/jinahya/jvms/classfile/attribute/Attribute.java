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
import com.googlecode.jinahya.jvms.classfile.constant._Utf8;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class Attribute {


    public static void readInstances(final int attributesCount,
                                     final DataInput input,
                                     final ClassFile classFile,
                                     final Collection<Attribute> attributes)
        throws IOException {

        final AttributeInfo info = new AttributeInfo();
        for (int i = 0; i < attributesCount; i++) {
            info.read(input);
            attributes.add(newInstance(info, classFile));
        }
    }


    public static Attribute newInstance(final AttributeInfo info,
                                        final ClassFile classFile)
        throws IOException {

        final _Utf8 utf8 = classFile.getConstant(
            info.attributeNameIndex, _Utf8.class);
        final AttributeName name = AttributeName.valueOf(utf8.getValue());
        final Attribute attribute = name.newAttribute();
        attribute.setClassFile(classFile);
        info.print(attribute);

        return attribute;
    }


    protected abstract void readInfo(AttributeInfo info, DataInput input)
        throws IOException;


    protected abstract void writeInfo(AttributeInfo info, DataOutput output)
        throws IOException;


    // -------------------------------------------------------- parent classfile
    /**
     * Returns the parent classfile of this attribute.
     *
     * @return the parent classfile
     */
    public final ClassFile getClassFile() {
        return classFile;
    }


    /**
     * Sets the parent classfile of this attribute.
     *
     * @param classFile the parent classfile
     */
    public final void setClassFile(final ClassFile classFile) {
        this.classFile = classFile;
    }


    /** parent classfile. */
    @XmlTransient
    protected ClassFile classFile;


    @XmlAttribute(required = true)
    protected int nameIndex;


}

