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


package com.googlecode.jinahya.jvms.classfile;


import com.googlecode.jinahya.jvms.classfile.attribute.Attribute;
import com.googlecode.jinahya.jvms.classfile.attribute.AttributeName;
import com.googlecode.jinahya.jvms.classfile.constant.Constant;
import com.googlecode.jinahya.jvms.classfile.constant.ConstantTag;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
@XmlType(propOrder = {"constants", "interfaces", "fields", "methods",
                      "attributes"})
public class Classfile implements DataAccessible {


    public static Classfile readInstance(final DataInput input)
        throws IOException {

        final Classfile instance = new Classfile();
        instance.read(input);

        return instance;
    }


    /**
     * The magic number identifying the class file format; it has the value
     * <code>0xCAFEBABE</code>.
     */
    public static final int MAGIC = 0xCAFEBABE;


    @Override
    public void read(final DataInput input) throws IOException {

        assert input.readInt() == MAGIC;

        //minorVersion = input.readUnsignedShort();

        //majorVersion = input.readUnsignedShort();

        version = Version.valueOf(input.readUnsignedShort(),
                                     input.readUnsignedShort());

        // -------------------------------------------------------- constantPool
        final int constantPoolCount = input.readUnsignedShort();
        for (int i = 0; i < constantPoolCount - 1; i++) {
            final int tag = input.readUnsignedByte();
            final Constant constant = ConstantTag.valueOf(tag).newInfo();
            constant.setIndex(i);
            constant.read(input);
            getConstants().add(constant);
        }

        accessFlags = input.readUnsignedShort();

        thisClass = input.readUnsignedShort();

        superClass = input.readUnsignedShort();

        // ----------------------------------------------------------- interface
        final int interfacesCount = input.readUnsignedShort();
        for (int i = 0; i < interfacesCount; i++) {
            getInterfaces().add(input.readUnsignedShort());
        }

        // -------------------------------------------------------------- fields
        final int fieldsCount = input.readUnsignedShort();
        for (int i = 0; i < fieldsCount; i++) {
            getFields().add(input.readUnsignedShort());
        }

        // ------------------------------------------------------------- methods
        final int methodsCount = input.readUnsignedShort();
        for (int i = 0; i < methodsCount; i++) {
            getMethods().add(input.readUnsignedShort());
        }

        // ---------------------------------------------------------- attributes
        getAttributes().clear();
        final int attributesCount = input.readUnsignedShort();
        for (int i = 0; i < attributesCount; i++) {
            final Attribute attribute =
                AttributeName.readAttribute(input, getConstants());
            attribute.setClassfile(this);
            getAttributes().add(attribute);
        }
    }


    @Override
    public void write(final DataOutput output) throws IOException {

        output.writeInt(MAGIC);

        //output.writeShort(minorVersion);

        //output.writeShort(majorVersion);

        output.writeShort(version.minor);

        output.writeShort(version.major);

        // -------------------------------------------------------- constantPool
        output.writeShort(getConstants().size() + 1);
        for (Constant constant : getConstants()) {
            output.write(constant.getTag().getValue());
            constant.write(output);
        }

        output.writeShort(accessFlags);

        output.writeShort(thisClass);

        output.writeShort(superClass);

        // ---------------------------------------------------------- interfaces
        output.writeShort(getInterfaces().size());
        for (Iterator<Integer> i = getInterfaces().iterator(); i.hasNext();) {
            output.writeShort(i.next());
        }

        // -------------------------------------------------------------- fields
        output.writeShort(getFields().size());
        for (Iterator<Integer> i = getFields().iterator(); i.hasNext();) {
            output.writeShort(i.next());
        }

        // ------------------------------------------------------------- methods
        output.writeShort(getMethods().size());
        for (Iterator<Integer> i = getMethods().iterator(); i.hasNext();) {
            output.writeShort(i.next());
        }

        // ---------------------------------------------------------- attributes
        output.writeShort(getAttributes().size());
        for (Attribute attribute : getAttributes()) {
            attribute.write(output);
        }
    }


    @XmlAttribute
    public int getMinorVersion() {
        return getVersion().minor;
    }


    @XmlAttribute
    public int getMajorVersion() {
        return getVersion().major;
    }


    public Version getVersion() {
        return version;
    }


    public List<Constant> getConstants() {

        if (constants == null) {
            constants = new ArrayList<Constant>();
        }

        return constants;
    }


    public int getAccessFlags() {
        return accessFlags;
    }


    public int getThisClass() {
        return thisClass;
    }


    public int getSuperClass() {
        return superClass;
    }


    public List<Integer> getInterfaces() {

        if (interfaces == null) {
            interfaces = new ArrayList<Integer>();
        }

        return interfaces;
    }


    public List<Integer> getFields() {

        if (fields == null) {
            fields = new ArrayList<Integer>();
        }

        return fields;
    }


    public List<Integer> getMethods() {

        if (methods == null) {
            methods = new ArrayList<Integer>();
        }

        return methods;
    }


    public List<Attribute> getAttributes() {

        if (attributes == null) {
            attributes = new ArrayList<Attribute>();
        }

        return attributes;
    }


    /*
    @XmlAttribute(required = true)
    private int minorVersion;
     */
    /*
    @XmlAttribute(required = true)
    private int majorVersion;
     */
    /** version. */
    @XmlAttribute(required = true)
    private Version version;


    /** constants. */
    @XmlElement(name = "constant")
    @XmlElementWrapper(required = true)
    private List<Constant> constants;


    /** accessFlags. */
    @XmlAttribute(required = true)
    private int accessFlags;


    @XmlAttribute(required = true)
    private int thisClass;


    @XmlAttribute(required = true)
    private int superClass;


    @XmlElement(name = "interface")
    @XmlElementWrapper(required = true)
    private List<Integer> interfaces;


    @XmlElement(name = "field")
    @XmlElementWrapper(required = true)
    private List<Integer> fields;


    @XmlElement(name = "method")
    @XmlElementWrapper(required = true)
    private List<Integer> methods;


    @XmlElement(name = "attribute")
    @XmlElementWrapper(required = true)
    private List<Attribute> attributes;


}

