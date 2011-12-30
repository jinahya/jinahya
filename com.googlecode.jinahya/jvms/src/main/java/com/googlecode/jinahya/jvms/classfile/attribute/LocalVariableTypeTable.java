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


import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @see <a href="http://goo.gl/I6Fqj">4.7.9 The LocalVariableTable Attribute</a>
 */
public class LocalVariableTypeTable extends Attribute {


    @Override
    protected void readInfo(final AttributeInfo info, final DataInput input)
        throws IOException {

        getLocalVariableTypes().clear();
        final int localVariableTableLength = input.readUnsignedShort();
        for (int i = 0; i < localVariableTableLength; i++) {
            final LocalVariableType localVariableType = new LocalVariableType();
            localVariableType.read(input);
            getLocalVariableTypes().add(localVariableType);
        }
    }


    @Override
    protected void writeInfo(final AttributeInfo info, final DataOutput output)
        throws IOException {

        output.writeShort(getLocalVariableTypes().size());
        for (LocalVariableType localVariableType : getLocalVariableTypes()) {
            localVariableType.write(output);
        }
    }


    private List<LocalVariableType> getLocalVariableTypes() {

        if (localVariableTypes == null) {
            localVariableTypes = new ArrayList<LocalVariableType>();
        }

        return localVariableTypes;
    }


    @XmlElement(name = "localVariableType")
    private List<LocalVariableType> localVariableTypes;


}
