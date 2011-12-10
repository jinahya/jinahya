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


package com.googlecode.jinahya.jvms.classfile.constant;


import com.googlecode.jinahya.jvms.classfile.Classfile;
import com.googlecode.jinahya.jvms.classfile.DataAccessible;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class Constant implements DataAccessible {


    public abstract ConstantTag getTag();


    // -------------------------------------------------------- parent Classfile
    /**
     * Returns the parent Classfile of this attribute.
     *
     * @return the parent Classfile
     */
    public final Classfile getClassfile() {
        return classfile;
    }


    /**
     * Sets the parent Classfile of this attribute.
     *
     * @param classfile the parent Classfile
     */
    public final void setClassfile(final Classfile classfile) {
        this.classfile = classfile;
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
    private Classfile classfile;


    @XmlAttribute
    private Integer index;


}

