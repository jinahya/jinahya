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
public class LocalVariableTable extends Attribute {


    @Override
    protected void readContent(final DataInput input) throws IOException {

        getLocalVariables().clear();
        final int localVariableTableLength = input.readUnsignedShort();
        for (int i = 0; i < localVariableTableLength; i++) {
            final LocalVariable localVariable = new LocalVariable();
            localVariable.read(input);
            getLocalVariables().add(localVariable);
        }
    }


    @Override
    protected void writeContent(final DataOutput output) throws IOException {

        output.writeShort(getLocalVariables().size());
        for (LocalVariable localVariable : getLocalVariables()) {
            localVariable.write(output);
        }
    }


    private List<LocalVariable> getLocalVariables() {

        if (localVariables == null) {
            localVariables = new ArrayList<LocalVariable>();
        }

        return localVariables;
    }


    @XmlElement(name = "localVariable")
    private List<LocalVariable> localVariables;


}

