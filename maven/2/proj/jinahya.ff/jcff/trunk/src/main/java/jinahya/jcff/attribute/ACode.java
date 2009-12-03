/*
 *  Copyright 2009 Jin Kwon.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package jinahya.jcff.attribute;


import java.io.Externalizable;
import java.io.IOException;
import java.io.DataInput;
import java.io.DataOutput;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ACode extends Attribute {


    private static final long serialVersionUID = 7246082004673662129L;


    /**
     *
     */
    public static class ExceptionTable {


//        private static final long serialVersionUID = 156659854929710730L;


        public void readData(DataInput in)
            throws IOException {

            startPC = in.readShort() & 0xFFFF;
            endPC = in.readShort() & 0xFFFF;
            handlerPC = in.readShort() & 0xFFFF;
            catchType = in.readShort() & 0xFFFF;
        }


        public void writeData(DataOutput out) throws IOException {
            out.writeShort(startPC);
            out.writeShort(endPC);
            out.writeShort(handlerPC);
            out.writeShort(catchType);
        }


        private int startPC;
        private int endPC;
        private int handlerPC;
        private int catchType;
    }


    @Override
    protected void readInfo(DataInput in)
        throws IOException {

        maxStack = in.readShort() & 0xFFFF;
        maxLocals = in.readShort() & 0xFFFF;

        code = new byte[in.readShort() & 0xFFFF];
        in.readFully(code);

        int exceptionTableLength = in.readShort() & 0xFFFF;
        for (int i = 0; i < exceptionTableLength; i++) {
            ExceptionTable instance = new ExceptionTable();
            instance.read(in);
            getExceptionTable().add(instance);
        }

        int attributesCount = in.readShort() & 0xFFFF;
        for (int i = 0; i < attributesCount; i++) {
            Attribute attr = new Attribute();
            attr.read(in);
            getAttribute().add(attr);
        }
    }


    @Override
    protected void writeInfo(DataOutput out) throws IOException {
        out.writeShort(maxStack);
        out.writeShort(maxLocals);

        out.writeShort(code.length);
        out.write(code);

        out.writeShort(getExceptionTable().size());
        for (ExceptionTable instance : getExceptionTable()) {
            instance.write(out);
        }

        out.writeShort(getAttribute().size());
        for (Attribute instance : getAttribute()) {
            instance.write(out);
        }
    }
    

    public int getMaxStack() {
        return maxStack;
    }


    public void setMaxStack(int maxStack) {
        this.maxStack = maxStack;
    }


    public int getMaxLocals() {
        return maxLocals;
    }


    public void setMaxLocals(int maxLocals) {
        this.maxLocals = maxLocals;
    }


    public byte[] getCode() {
        return code;
    }


    public void setCode(byte[] code) {
        this.code = code;
    }


    public List<ExceptionTable> getExceptionTable() {
        if (exceptionTable == null) {
            exceptionTable = new ArrayList<ExceptionTable>();
        }
        return exceptionTable;
    }


    public List<Attribute> getAttribute() {
        if (attribute == null) {
            attribute = new ArrayList<Attribute>();
        }
        return attribute;
    }


    private int maxStack;
    private int maxLocals;
    private byte[] code;

    private List<ExceptionTable> exceptionTable;
    private List<Attribute> attribute;
}
