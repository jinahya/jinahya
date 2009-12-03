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
public class ALineNumberTable extends Attribute {


    private static final long serialVersionUID = -3071918590371135703L;


    /**
     * 
     */
    public static class LineNumberTableEntry {
        //implements Externalizable {


//        private static final long serialVersionUID = -4534643738119980070L;


        public void readData(DataInput in) throws IOException {

            startPC = in.readShort() & 0xFFFF;
            lineNumber = in.readShort() & 0xFFFF;
        }


        public void writeData(DataOutput out) throws IOException {
            out.writeShort(startPC);
            out.writeShort(lineNumber);
        }


        public int getStartPC() {
            return startPC;
        }


        public void setStartPC(int startPC) {
            this.startPC = startPC;
        }


        public int getLineNumber() {
            return lineNumber;
        }


        public void setLineNumber(int lineNumber) {
            this.lineNumber = lineNumber;
        }


        private int startPC;
        private int lineNumber;
    }


    @Override
    protected void readInfo(DataInput in)
        throws IOException {

        int lineNumberTableLength = in.readShort() & 0xFFFF;
        for (int i = 0 ; i < lineNumberTableLength; i++) {
            LineNumberTableEntry instance = new LineNumberTableEntry();
            instance.read(in);
            getEntry().add(instance);
        }
    }


    @Override
    protected void writeInfo(DataOutput out) throws IOException {
        out.writeShort(getEntry().size());
        for (LineNumberTableEntry instance : getEntry()) {
            instance.write(out);
        }
    }


    public List<LineNumberTableEntry> getEntry() {
        if (entry == null) {
            entry = new ArrayList<LineNumberTableEntry>();
        }
        return entry;
    }


    public void setEntry(List<LineNumberTableEntry> entry) {
        this.entry = entry;
    }


    private List<LineNumberTableEntry> entry;
}
