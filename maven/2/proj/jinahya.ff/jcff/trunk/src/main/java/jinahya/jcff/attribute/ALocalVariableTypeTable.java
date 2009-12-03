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
public class ALocalVariableTypeTable extends Attribute {


    private static final long serialVersionUID = 8704610905503679271L;



    /**
     *
     */
    public static class LocalVariableTypeTableEntry {
        //implements Externalizable {


//        private static final long serialVersionUID = 4283677934437835630L;


        public void readData(DataInput in)
            throws IOException {

            startPC = in.readShort() & 0xFFFF;
            length = in.readShort() & 0xFFFF;
            nameIndex = in.readShort() & 0xFFFF;
            signatureIndex = in.readShort() & 0xFFFF;
            index = in.readShort() & 0xFFFF;
        }


        public void writeData(DataOutput out) throws IOException {
            out.writeShort(startPC);
            out.writeShort(length);
            out.writeShort(nameIndex);
            out.writeShort(signatureIndex);
            out.writeShort(index);
        }


        public int getStartPC() {
            return startPC;
        }


        public void setStartPC(int startPC) {
            this.startPC = startPC;
        }


        public int getLength() {
            return length;
        }


        public void setLength(int length) {
            this.length = length;
        }


        public int getNameIndex() {
            return nameIndex;
        }


        public void setNameIndex(int nameIndex) {
            this.nameIndex = nameIndex;
        }


        public int getSignatureIndex() {
            return signatureIndex;
        }


        public void setSignatureIndex(int signatureIndex) {
            this.signatureIndex = signatureIndex;
        }


        public int getIndex() {
            return index;
        }


        public void setIndex(int index) {
            this.index = index;
        }

        private int startPC;
        private int length;
        private int nameIndex;
        private int signatureIndex;
        private int index;
    }


     @Override
    protected void readInfo(DataInput in)
        throws IOException {

        int lineNumberTableLength = in.readShort() & 0xFFFF;
        for (int i = 0 ; i < lineNumberTableLength; i++) {
            LocalVariableTypeTableEntry instance = new LocalVariableTypeTableEntry();
            instance.read(in);
            getEntry().add(instance);
        }
    }


    @Override
    protected void writeInfo(DataOutput out) throws IOException {
        out.writeShort(getEntry().size());
        for (LocalVariableTypeTableEntry instance : getEntry()) {
            instance.write(out);
        }
    }


    public List<LocalVariableTypeTableEntry> getEntry() {
        if (entry == null) {
            entry = new ArrayList<LocalVariableTypeTableEntry>();
        }
        return entry;
    }


    public void setEntry(List<LocalVariableTypeTableEntry> entry) {
        this.entry = entry;
    }


    private List<LocalVariableTypeTableEntry> entry;
}
