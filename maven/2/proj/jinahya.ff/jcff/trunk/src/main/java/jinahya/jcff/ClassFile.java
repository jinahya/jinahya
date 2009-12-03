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

package jinahya.jcff;


import java.io.IOException;
import java.io.DataInput;
import java.io.DataOutput;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import jinahya.jcff.attribute.Attribute;
import jinahya.jcff.constant.Constant;
import jinahya.jcff.field.Field;
import jinahya.jcff.method.Method;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ClassFile {


    //private static final long serialVersionUID = -2500092885408220481L;


    private static final int MAGIC = 0xCAFEBABE;


    /**
     * Declared <code>public</code>; may be accessed from outside its package.
     */
    public static final int ACC_PUBLIC = 0x0001;


    /**
     * Declared <code>final</code>; no subclasses allowed.
     */
    public static final int ACC_FINAL  = 0x0010;


    /**
     * Treat superclass methods specially when invoked by the
     * <i>invokespecial</i> instruction.
     */
    public static final int ACC_SUPER = 0x0020;


    /**
     * Is an interface, not a class.
     */
    public static final int ACC_INTERFACE = 0x0200;


    /**
     * Declared <code>abstract</code>; must not be instantiated.
     */
    public static final int ACC_ABSTRACT = 0x0400;


    /**
     * Declared <code>synthetic</code>; Not present in the source code.
     */
    public static final int ACC_SYNTHETIC = 0x1000;


    /**
     * Declared as an annotation type.
     */
    public static final int ACC_ANNOTATION = 0x2000;


    /**
     * Declared as an <code>enum<code> type.
     */
    public static final int ACC_ENUM = 0x4000;



    /**
     *
     * @param FQCN Fully Qualified Class Name
     * @return
     */
    public static ClassFile newClassInstance(String FQCN) {
        return null;
    }


    /**
     *
     * @param FQIN Fully Qualified Interface Name
     * @return
     */
    public static ClassFile newInterfaceInstance(String FQIN) {
        return null;
    }


    /*
    private ClassFile() {
        super();
    }
     */


    public void readData(DataInput in) throws IOException {

        if (in.readInt() != MAGIC) {
            throw new IOException("illegal magic");
        }

        minorVersion = in.readShort() & 0xFFFF;
        System.out.println("mijorVersion: " + minorVersion);
        majorVersion = in.readShort() & 0xFFFF;
        System.out.println("majorVersion: " + majorVersion);

        int constantPoolCount = in.readShort() & 0xFFFF;
        System.out.println("constantPoolCount: " + constantPoolCount);
        for (int i = 1; i < constantPoolCount; i++) {
            Constant instance = Constant.readInstance(in);
            System.out.printf("\tconstant[%1$5d]: %2$s\n", i, instance);
            getConstant().add(instance);
        }

        accessFlags = in.readShort() & 0xFFFF;
        System.out.printf("accessFlags: %1$5d\n", accessFlags);
        thisClass = in.readShort() & 0xFFFF;
        System.out.println("thisClass: " + thisClass);
        superClass = in.readShort() & 0xFFFF;
        System.out.println("superClass: " + superClass);


        int interfacesCount = in.readShort() & 0xFFFF;
        System.out.println("interfacesCount: " + interfacesCount);
        for (int i = 0; i < interfacesCount; i++) {
            int element = in.readShort() & 0xFFFF;
            System.out.printf("\tinterface[%1$5d]: %2$d\n", i, element);
            getInterfaceIndex().add(element);
        }

        int fieldsCount = in.readShort() & 0xFFFF;
        System.out.println("fieldsCount: " + fieldsCount);
        for (int i = 0; i < fieldsCount; i++) {
            Field instance = new Field();
            instance.read(in);
            System.out.printf("\tfield[%1$5d]: %2$s\n", i, instance);
            getField().add(instance);

            for (int j = 0; j < instance.getAttribute().size(); j++) {
                System.out.printf("\t\tattribute[%1$5d]: %2$s\n", j, instance.getAttribute().get(j));
            }
        }

        int methodsCount = in.readShort() & 0xFFFF;
        System.out.println("methodsCount: " + methodsCount);
        for (int i = 0; i < methodsCount; i++) {
            Method instance = new Method();
            instance.readData(in);
            System.out.printf("\tmethod[%1$5d]: %2$s\n", i, instance);
            getMethod().add(instance);

            for (int j = 0; j < instance.getAttribute().size(); j++) {
                System.out.printf("\t\tattribute[%1$5d]: %2$s\n", j, instance.getAttribute().get(j));
            }
        }
    }


    public void writeData(DataOutput out) throws IOException {

        out.writeInt(MAGIC);

        out.writeShort(minorVersion);
        out.writeShort(majorVersion);

        out.writeShort(getConstant().size() + 1);
        for (Constant instance : getConstant()) {
            instance.write(out);
        }

        out.writeShort(accessFlags);
        out.writeShort(thisClass);
        out.writeShort(superClass);

        out.writeShort(getInterfaceIndex().size());
        for (int element : getInterfaceIndex()) {
            out.writeShort(element);
        }

        out.writeShort(getField().size());
        for (Field element : getField()) {
            element.write(out);
        }

        out.writeShort(getMethod().size());
        for (Method element : getMethod()) {
            element.writeData(out);
        }
    }


    @XmlElement(required = true)
    public int getMagic() {
        return MAGIC;
    }


    @XmlElement(required = true)
    public int getMinorVersion() {
        return minorVersion;
    }


    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion & 0xFF;
    }


    @XmlElement(required = true)
    public int getMajorVersion() {
        return majorVersion;
    }


    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion & 0xFF;
    }


    @XmlElementWrapper(name = "constants")
    @XmlElement
    public List<Constant> getConstant() {
        if (constant == null) {
            constant = new ArrayList<Constant>();
        }
        return constant;
    }


    public void setConstant(List<Constant> constant) {
        this.constant = constant;
    }


    @XmlElement(required = true)
    public int getAccessFlags() {
        return accessFlags;
    }


    public int getThisClass() {
        return thisClass;
    }


    @XmlElement(required = true)
    public int getSuperClass() {
        return superClass;
    }


    public void setSuperClass(int superClass) {
        this.superClass = superClass;
    }


    @XmlElementWrapper(name = "interfaceIndices")
    @XmlElement
    public List<Integer> getInterfaceIndex() {
        if (interfaceIndex == null) {
            interfaceIndex = new ArrayList<Integer>();
        }
        return interfaceIndex;
    }


    public void setInterfaceIndex(List<Integer> interfaceIndex) {
        this.interfaceIndex = interfaceIndex;
    }


    @XmlElementWrapper(name = "fields")
    @XmlElement
    public List<Field> getField() {
        if (field == null) {
            field = new ArrayList<Field>();
        }
        return field;
    }


    public void setField(List<Field> field) {
        this.field = field;
    }


    @XmlElementWrapper(name = "methods")
    @XmlElement
    public List<Method> getMethod() {
        if (method == null) {
            method = new ArrayList<Method>();
        }
        return method;
    }


    public void setMethod(List<Method> method) {
        this.method = method;
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


    private int minorVersion;
    private int majorVersion;

    private List<Constant> constant;

    private int accessFlags;
    private int thisClass;
    private int superClass = 0x00;

    private List<Integer> interfaceIndex;

    private List<Field> field;

    private List<Method> method;

    private List<Attribute> attribute;
}
