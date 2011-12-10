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


import com.googlecode.jinahya.jvms.classfile.DataAccessible;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @See <a href="http://goo.gl/KnMEs">4.7.3 The Code Attribute</a>
 */
public class Code extends Attribute {


    public static class ExceptionTable implements DataAccessible {


        @Override
        public void read(final DataInput input) throws IOException {

            startPc = input.readUnsignedShort();

            endPc = input.readUnsignedShort();

            handlerPc = input.readUnsignedShort();

            catchType = input.readUnsignedShort();
        }


        @Override
        public void write(final DataOutput output) throws IOException {

            output.writeShort(startPc);

            output.writeShort(endPc);

            output.writeShort(handlerPc);

            output.writeShort(catchType);
        }


        @XmlAttribute(required = true)
        private int startPc;


        @XmlAttribute(required = true)
        private int endPc;


        @XmlAttribute(required = true)
        private int handlerPc;


        @XmlAttribute(required = true)
        private int catchType;


    }


    @Override
    protected void readInfo(final DataInput input) throws IOException {

        maxStack = input.readUnsignedShort();

        maxLocals = input.readUnsignedShort();

        code = new byte[input.readInt()];
        input.readFully(code);

        final int exceptionTableLength = input.readUnsignedShort();
        getExceptionTables().clear();
        for (int i = 0; i < exceptionTableLength; i++) {
            final ExceptionTable exceptionTable = new ExceptionTable();
            exceptionTable.read(input);
            getExceptionTables().add(exceptionTable);
        }

        final int attributeCount = input.readUnsignedShort();
        getAttributes().clear();
        for (int i = 0; i < attributeCount; i++) {
            getAttributes().add(AttributeName.readAttribute(input, this));
        }

    }


    @Override
    protected void writeInfo(final DataOutput output) throws IOException {
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


    @XmlAttribute(required = true)
    private int maxStack;


    @XmlAttribute(required = true)
    private int maxLocals;


    @XmlElement(required = true)
    private byte[] code;


    @XmlElement(name = "exceptionTable")
    @XmlElementWrapper(required = true)
    private List<ExceptionTable> exceptionTables;


    @XmlElement(name = "attribute")
    @XmlElementWrapper(required = true)
    private List<Attribute> attributes;


}

