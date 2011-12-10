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


import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;


/**
 * Constants for <code>cp_info.tag</code>.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlEnum(Integer.class)
public enum ConstantTag {


    @XmlEnumValue("7")
    _Class(7) {


        @Override
        public Constant newInfo() {
            return new _Class();
        }


    },
    @XmlEnumValue("9")
    _Fieldref(9) {


        @Override
        public Constant newInfo() {
            return new _Fieldref();
        }


    },
    @XmlEnumValue("10")
    _Methodref(10) {


        @Override
        public Constant newInfo() {
            return new _Methodref();
        }


    },
    @XmlEnumValue("11")
    InterfaceMethodref(11) {


        @Override
        public Constant newInfo() {
            return new _InterfaceMethodref();
        }


    },
    @XmlEnumValue("8")
    _String(8) {


        @Override
        public Constant newInfo() {
            return new _String();
        }


    },
    @XmlEnumValue("3")
    _Integer(3) {


        @Override
        public Constant newInfo() {
            return new _Integer();
        }


    },
    @XmlEnumValue("4")
    _Float(4) {


        @Override
        public Constant newInfo() {
            return new _Float();
        }


    },
    @XmlEnumValue("5")
    _Long(5) {


        @Override
        public Constant newInfo() {
            return new _Long();
        }


    },
    @XmlEnumValue("6")
    _Double(6) {


        @Override
        public Constant newInfo() {
            return new _Double();
        }


    },
    @XmlEnumValue("12")
    _NameAndType(12) {


        @Override
        public Constant newInfo() {
            return new _NameAndType();
        }


    },
    @XmlEnumValue("1")
    _Utf8(1) {


        @Override
        public Constant newInfo() {
            return new _Utf8();
        }


    };


    public static ConstantTag valueOf(final int tagValue) {

        for (ConstantTag tag : values()) {
            if (tag.getValue() == tagValue) {
                return tag;
            }
        }

        throw new IllegalArgumentException("no constant for " + tagValue);
    }


    private ConstantTag(final int value) {
        this.value = value;
    }


    /**
     * Creates a new <code>cp_info</code> instance.
     *
     * @return a new <code>cp_info</code>.
     */
    public abstract Constant newInfo();


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
