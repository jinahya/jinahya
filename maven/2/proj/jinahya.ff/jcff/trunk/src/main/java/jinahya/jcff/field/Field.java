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

package jinahya.jcff.field;


import java.io.IOException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import jinahya.jcff.DataContainer;
import jinahya.jcff.attribute.Attribute;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
@XmlType(propOrder = {"accessFlags", "nameIndex", "descriptorIndex",
                      "attribute"})
public class Field implements Serializable, DataContainer {


    private static final long serialVersionUID = -6564135796665444026L;


    /**
     * Declared <code>public</code>; may be accessed from outside its package.
     */
    public static final int ACC_PUBLIC = 0x0001;


    /**
     * Declared <code>private</code>; usable only within the defining class.
     */
    public static final int ACC_PRIVATE = 0x0002;


    /**
     * Declared <code>protected</code>; may be accessed within subclasses.
     */
    public static final int ACC_PROTECTED = 0x0004;


    /**
     * Declared <code>static</code>.
     */
    public static final int ACC_STATIC = 0x0008;


    /**
     * Declared <code>final</code>; no further assignment after initialization.
     */
    public static final int ACC_FINAL = 0x0010;


    /**
     * Declared <code>volatile</code>; cannot be cached.
     */
    public static final int ACC_VOLATILE = 0x0040;


    /**
     * Declared <code>transient</code>; not written or read by a persistent
     * object manager.
     */
    public static final int ACC_TRANSIENT = 0x0080;


    /**
     * Declared <code>synthetic<code>; Not present in the source code.
     */
    public static final int ACC_SYNTHETIC = 0x1000;


    /**
     * Declared as an element of an <code>enum</code>.
     */
    public static final int ACC_ENUM = 0x4000;


    @Override
    public void readData(DataInput in) throws IOException {
        accessFlags = in.readShort() & 0xFFFF;
        nameIndex = in.readShort() & 0xFFFF;
        descriptorIndex = in.readShort() & 0xFFFF;

        int attributesCount = in.readShort() & 0xFFFF;
        for (int i = 0; i < attributesCount; i++) {
            Attribute instance = new Attribute();
            instance.readData(in);
            getAttribute().add(instance);
        }
    }


    @Override
    public void writeData(DataOutput out) throws IOException {

        if (getAttribute().size() > 65535) {
            throw new IllegalStateException
                ("too many attributes: " + getAttribute().size());
        }

        out.writeShort(accessFlags);
        out.writeShort(nameIndex);
        out.writeShort(descriptorIndex);

        out.writeShort(getAttribute().size());
        for (Attribute instance : getAttribute()) {
            instance.writeData(out);
        }
    }


    @XmlElement(required = true)
    public int getAccessFlags() {
        return accessFlags;
    }


    public void setAccessFlags(int accessFlags) {
        this.accessFlags = accessFlags;
    }


    @XmlElement(required = true)
    public int getNameIndex() {
        return nameIndex;
    }


    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }


    @XmlElement(required = true)
    public int getDescriptorIndex() {
        return descriptorIndex;
    }


    public void setDescriptorIndex(int descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }


    @XmlElementWrapper(name = "attributes")
    @XmlElement
    public List<Attribute> getAttribute() {
        if (attribute == null) {
            attribute = new ArrayList<Attribute>();
        }
        return attribute;
    }


    public void setAttribute(List<Attribute> attribute) {
        this.attribute = attribute;
    }


    @Override
    public String toString() {
        return "FIELD: accessFlags(" + accessFlags + "), nameIndex(" +
            nameIndex + "), descriptorIndex(" + descriptorIndex +
            "), attributes(" + getAttribute().size() + ")";
    }


    private int accessFlags;
    private int nameIndex;
    private int descriptorIndex;

    private List<Attribute> attribute;
}
