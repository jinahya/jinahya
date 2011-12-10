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


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlTransient
@XmlType(propOrder = {"highBytes", "lowBytes"})
class _number64 extends _number {


    protected _number64(final ConstantTag tag) {
        super(tag, 2);
    }


    @XmlElement(required = true)
    public int getHighBytes() {
        return words[0];
    }


    public void setHighBytes(final int highBytes) {
        words[0] = highBytes;
    }


    @XmlElement(required = true)
    public int getLowBytes() {
        return words[1];
    }


    public void setLowBytes(final int lowBytes) {
        words[1] = lowBytes;
    }


}

