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

import javax.xml.bind.annotation.XmlElement;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class AInnerClasses extends Attribute {


    private static final long serialVersionUID = -8259860572904531927L;


    /**
     *
     */
    public static class InnerClass {
        //implements Externalizable {


//        private static final long serialVersionUID = 6939566840697187125L;


        public void readData(DataInput in)
            throws IOException {

            innerClassInfoIndex = in.readShort() & 0xFFFF;
            outerClassInfoIndex = in.readShort() & 0xFFFF;
            innerNameIndex = in.readShort() & 0xFFFF;
            innerClassAccessFlags = in.readShort() & 0xFFFF;
        }


        public void writeData(DataOutput out) throws IOException {
            out.writeShort(innerClassInfoIndex);
            out.writeShort(outerClassInfoIndex);
            out.writeShort(innerNameIndex);
            out.writeShort(innerClassAccessFlags);
        }


        public int getInnerClassInfoIndex() {
            return innerClassInfoIndex;
        }


        public void setInnerClassInfoIndex(int innerClassInfoIndex) {
            this.innerClassInfoIndex = innerClassInfoIndex;
        }


        public int getOuterClassInfoIndex() {
            return outerClassInfoIndex;
        }


        public void setOuterClassInfoIndex(int outerClassInfoIndex) {
            this.outerClassInfoIndex = outerClassInfoIndex;
        }


        public int getInnerNameIndex() {
            return innerNameIndex;
        }


        public void setInnerNameIndex(int innerNameIndex) {
            this.innerNameIndex = innerNameIndex;
        }


        public int getInnerClassAccessFlags() {
            return innerClassAccessFlags;
        }


        public void setInnerClassAccessFlags(int innerClassAccessFlags) {
            this.innerClassAccessFlags = innerClassAccessFlags;
        }


        private int innerClassInfoIndex;
        private int outerClassInfoIndex;
        private int innerNameIndex;
        private int innerClassAccessFlags;
    }


    @Override
    protected void readInfo(DataInput in)
        throws IOException {

        int lineNumberTableLength = in.readShort() & 0xFFFF;
        for (int i = 0 ; i < lineNumberTableLength; i++) {
            InnerClass instance = new InnerClass();
            instance.read(in);
            getInnerClass().add(instance);
        }
    }


    @Override
    protected void writeInfo(DataOutput out) throws IOException {
        out.writeShort(getInnerClass().size());
        for (InnerClass instance : getInnerClass()) {
            instance.write(out);
        }
    }


    @XmlElement
    public List<InnerClass> getInnerClass() {
        if (innerClass == null) {
            innerClass = new ArrayList<InnerClass>();
        }
        return innerClass;
    }


    public void setInnerClass(List<InnerClass> innerClass) {
        this.innerClass = innerClass;
    }


    private List<InnerClass> innerClass;
}
