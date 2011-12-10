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


package com.googlecode.jinahya.jvms.cff.constant;


import com.googlecode.jinahya.jvms.cff.Classfile;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class Constant {


    public abstract ConstantTag getTag();


    public abstract void read(final DataInput input) throws IOException;


    public abstract void write(final DataOutput output) throws IOException;


    // -------------------------------------------------------- parent ClassFile
    /**
     * Returns the parent ClassFile of this attribute.
     *
     * @return the parent ClassFile
     */
    public Classfile getClassFile() {
        return classFile;
    }


    /**
     * Sets the parent ClassFile of this attribute.
     *
     * @param classFile the parent ClassFile
     */
    public void setClassFile(final Classfile classFile) {
        this.classFile = classFile;
    }


    // ------------------------------------------------------------------- index
    public Integer getIndex() {
        return index;
    }


    public void setIndex(final Integer index) {
        this.index = index;
    }


    /** parent ClassFile. */
    @XmlTransient
    private Classfile classFile;


    @XmlAttribute
    private Integer index;


}

