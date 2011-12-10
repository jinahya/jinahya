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

import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @See <a href="http://goo.gl/KnMEs">4.7.3 The Code Attribute</a>
 */
@XmlType(name = "Code")
public class Code extends Attribute {


    @Override
    protected void readContent(final DataInput input) throws IOException {

        maxStack = input.readUnsignedShort();

        maxLocals = input.readUnsignedShort();

        code = new byte[input.readInt()];
        input.readFully(code);

        getExceptionTables().clear();
        final int exceptionTableLength = input.readUnsignedShort();
        for (int i = 0; i < exceptionTableLength; i++) {
            final ExceptionTable exceptionTable = new ExceptionTable();
            exceptionTable.read(input);
            getExceptionTables().add(exceptionTable);
        }

        final int attributeCount = input.readUnsignedShort();
        for (int i = 0; i < attributeCount; i++) {
        }

    }


    @Override
    protected void writeContent(DataOutput output) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public int getMaxStack() {
        return maxStack;
    }


    public int getMaxLocals() {
        return maxLocals;
    }


    public List<ExceptionTable> getExceptionTables() {

        if (exceptionTables == null) {
            exceptionTables = new ArrayList<ExceptionTable>();
        }

        return exceptionTables;
    }


    public List<Attribute> getAttributes() {

        if (attributes == null) {
            attributes = new ArrayList<Attribute>();
        }

        return attributes;
    }


    private int maxStack;


    private int maxLocals;


    private byte[] code;


    private List<ExceptionTable> exceptionTables;


    private List<Attribute> attributes;


}

