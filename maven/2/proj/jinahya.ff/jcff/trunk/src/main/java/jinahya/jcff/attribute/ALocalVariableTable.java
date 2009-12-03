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
public class ALocalVariableTable extends Attribute {


    private static final long serialVersionUID = 8515274824038547256L;


    /**
     *
     */
    public static class LocalVariableTableEntry {
        //implements Externalizable {


//        private static final long serialVersionUID = -8422044084903456963L;


        public void readData(DataInput in)
            throws IOException {

            startPC = in.readShort() & 0xFFFF;
            length = in.readShort() & 0xFFFF;
            nameIndex = in.readShort() & 0xFFFF;
            descriptorIndex = in.readShort() & 0xFFFF;
            index = in.readShort() & 0xFFFF;
        }


        public void writeData(DataOutput out) throws IOException {
            out.writeShort(startPC);
            out.writeShort(length);
            out.writeShort(nameIndex);
            out.writeShort(descriptorIndex);
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


        public int getDescriptorIndex() {
            return descriptorIndex;
        }


        public void setDescriptorIndex(int descriptorIndex) {
            this.descriptorIndex = descriptorIndex;
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
        private int descriptorIndex;
        private int index;
    }


    @Override
    protected void readInfo(DataInput in)
        throws IOException {

        int lineNumberTableLength = in.readShort() & 0xFFFF;
        for (int i = 0 ; i < lineNumberTableLength; i++) {
            LocalVariableTableEntry instance = new LocalVariableTableEntry();
            instance.read(in);
            getEntry().add(instance);
        }
    }


    @Override
    protected void writeInfo(DataOutput out) throws IOException {
        out.writeShort(getEntry().size());
        for (LocalVariableTableEntry instance : getEntry()) {
            instance.write(out);
        }
    }


    public List<LocalVariableTableEntry> getEntry() {
        if (entry == null) {
            entry = new ArrayList<LocalVariableTableEntry>();
        }
        return entry;
    }


    public void setEntry(List<LocalVariableTableEntry> entry) {
        this.entry = entry;
    }


    private List<LocalVariableTableEntry> entry;
}
