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


import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;


/**
 * Constants for <code>cp_info.tag</code>.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlEnum(Integer.class)
public enum Tag {


    @XmlEnumValue("7")
    _Class(7) {


        @Override
        public Constant newConstant() {
            return new _Class();
        }


    },
    @XmlEnumValue("9")
    _Fieldref(9) {


        @Override
        public Constant newConstant() {
            return new _Fieldref();
        }


    },
    @XmlEnumValue("10")
    _Methodref(10) {


        @Override
        public Constant newConstant() {
            return new _Methodref();
        }


    },
    @XmlEnumValue("11")
    InterfaceMethodref(11) {


        @Override
        public Constant newConstant() {
            return new _InterfaceMethodref();
        }


    },
    @XmlEnumValue("8")
    _String(8) {


        @Override
        public Constant newConstant() {
            return new _String();
        }


    },
    @XmlEnumValue("3")
    _Integer(3) {


        @Override
        public Constant newConstant() {
            return new _Integer();
        }


    },
    @XmlEnumValue("4")
    _Float(4) {


        @Override
        public Constant newConstant() {
            return new _Float();
        }


    },
    @XmlEnumValue("5")
    _Long(5) {


        @Override
        public Constant newConstant() {
            return new _Long();
        }


    },
    @XmlEnumValue("6")
    _Double(6) {


        @Override
        public Constant newConstant() {
            return new _Double();
        }


    },
    @XmlEnumValue("12")
    _NameAndType(12) {


        @Override
        public Constant newConstant() {
            return new _NameAndType();
        }


    },
    @XmlEnumValue("1")
    _Utf8(1) {


        @Override
        public Constant newConstant() {
            return new _Utf8();
        }


    };


    public static Tag valueOf(final int tagValue) {

        for (Tag tag : values()) {
            if (tag.value == tagValue) {
                return tag;
            }
        }

        throw new IllegalArgumentException("no constant for " + tagValue);
    }


    private Tag(final int value) {
        this.value = value;
    }


    /**
     * Creates a new <code>cp_info</code> instance.
     *
     * @return a new <code>cp_info</code>.
     */
    public abstract Constant newConstant();


    /**
     * Returns value.
     *
     * @return value
     */
    public int getValue() {
        return value;
    }


    /** value. */
    private final int value;


}

