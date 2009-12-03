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

package jinahya.jcff.constant;


import java.io.IOException;
import java.io.DataInput;
import java.io.DataOutput;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
@XmlType(propOrder = {"classIndex", "nameAndTypeIndex"})
public abstract class Cref extends Constant {


    private static final long serialVersionUID = -1624284999324243153L;


    protected Cref(int tag) {
        super(tag);
    }


    @Override
    public void readInfo(DataInput in)
        throws IOException {

        classIndex = in.readShort() & 0xFFFF;
        nameAndTypeIndex = in.readShort() & 0xFFFF;
    }


    @Override
    public void writeInfo(DataOutput out) throws IOException {
        out.writeShort(classIndex);
        out.writeShort(nameAndTypeIndex);
    }


    @XmlElement(required = true)
    public int getClassIndex() {
        return classIndex;
    }


    public void setClassIndex(int classIndex) {
        this.classIndex = classIndex;
    }


    @XmlElement(required = true)
    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }


    public void setNameAndTypeIndex(int nameAndTypeIndex) {
        this.nameAndTypeIndex = nameAndTypeIndex;
    }


    private int classIndex;
    private int nameAndTypeIndex;
}
