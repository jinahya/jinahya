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
import java.io.IOException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public enum AttributeName {


    SourceFile(SourceFile.class),
    ConstantValue(ConstantValue.class),
    Code(Code.class),
    Exceptions(Exceptions.class),
    InnerClasses(InnerClasses.class),
    Synthetic(Synthetic.class),
    LineNumberTable(LineNumberTable.class),
    LocalVariableTable(LocalVariableTable.class),
    Deprecated(Deprecated.class);


    public static Attribute readAttribute(final DataInput input,
                                          final ClassFile parent)
        throws IOException {

        final AttributeInfo info = new AttributeInfo();
        info.read(input);

        final _Utf8 utf8 = parent.getConstant(
            info.getAttributeNameIndex(), _Utf8.class);
        final String name = utf8.getValue();
        final Attribute attribute = valueOf(name).newAttribute();
        info.print(attribute);
        attribute.setClassfile(parent);
        return attribute;
    }


    public static Attribute readAttribute(final DataInput input,
                                          final Attribute parent)
        throws IOException {

        final Attribute attribute = readAttribute(input, parent.getClassfile());
        attribute.setAttribute(attribute);

        return attribute;
    }


    /**
     * Creates a new instance.
     *
     * @param attributeType target attribute type
     */
    private AttributeName(final Class<? extends Attribute> attributeType) {
        this.attributeType = attributeType;
    }


    /**
     * Creates a new target attribute.
     *
     * @return new attribute
     */
    public Attribute newAttribute() {
        try {
            return attributeType.newInstance();
        } catch (InstantiationException ie) {
            throw new RuntimeException(
                "failed to create a new instance of " + attributeType, ie);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(
                "failed to create a new instance of " + attributeType, iae);
        }
    }


    private final Class<? extends Attribute> attributeType;


}

