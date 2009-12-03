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


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import jinahya.jcff.DataContainer;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
@XmlTransient
public class Attribute implements Serializable, DataContainer {


    private static final long serialVersionUID = -661773358587485680L;


    @Override
    public void readData(DataInput in) throws IOException {

        attributeNameIndex = in.readShort() & 0xFFFF;

        byte[] info = new byte[in.readInt()];
        in.readFully(info);

        ByteArrayInputStream bais = new ByteArrayInputStream(info);
        try {
            DataInputStream ois = new DataInputStream(bais);
            try {
                readInfo(ois);
            } finally {
                ois.close();
            }
        } finally {
            bais.close();
        }
    }


    protected void readInfo(DataInput in) throws IOException {
        // empty
    }


    @Override
    public void writeData(DataOutput out) throws IOException {

        out.writeShort(attributeNameIndex);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            DataOutputStream oos = new DataOutputStream(baos);
            try {
                writeInfo(oos);
                oos.flush();

                byte[] info = baos.toByteArray();

                out.writeShort(info.length);
                out.write(info);

            } finally {
                oos.close();
            }
        } finally {
            baos.close();
        }
    }


    protected void writeInfo(DataOutput out) throws IOException {
        // empty
    }


    @XmlElement(required = true)
    public int getAttributeNameIndex() {
        return attributeNameIndex;
    }


    public void setAttributeNameIndex(int attributeNameIndex) {
        this.attributeNameIndex = attributeNameIndex;
    }


    /*
    public byte[] getInfo() {
        return info;
    }


    public void setInfo(byte[] info) {
        this.info = info;
    }
     */


    private int attributeNameIndex;
}
