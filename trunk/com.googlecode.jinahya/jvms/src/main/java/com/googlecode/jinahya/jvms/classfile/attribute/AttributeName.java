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


import com.googlecode.jinahya.jvms.classfile.constant.Constant;
import com.googlecode.jinahya.jvms.classfile.constant._Utf8;

import java.io.DataInput;
import java.io.IOException;

import java.util.List;


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
                                          final List<Constant> constants)
        throws IOException {

        final int attributeNameIndex = input.readUnsignedShort();

        final _Utf8 utf8 = (_Utf8) constants.get(attributeNameIndex);

        for (AttributeName value : values()) {
            if (value.name().equals(utf8.getValue())) {
                return value.newAttribute();
            }
        }

        throw new IllegalArgumentException(
            "no constant for " + utf8.getValue());
    }


    private AttributeName(final Class<? extends Attribute> attributeClass) {
        this.attributeClass = attributeClass;
    }


    public Attribute newAttribute() {
        try {
            return attributeClass.newInstance();
        } catch (InstantiationException ie) {
            throw new RuntimeException(
                "failed to create a new instance of " + attributeClass, ie);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(
                "failed to create a new instance of " + attributeClass, iae);
        }
    }


    private final Class<? extends Attribute> attributeClass;


}

