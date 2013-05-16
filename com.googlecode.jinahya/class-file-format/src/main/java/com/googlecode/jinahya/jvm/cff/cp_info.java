/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.jvm.cff;


import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class cp_info {


    public static final int TAG_CONSTANT_Class = 7;


    public static final int TAG_CONSTANT_Fieldref = 9;


    public static final int TAG_CONSTANT_Methodref = 10;


    public static final int TAG_CONSTANT_InterfaceMethodref = 11;


    public static final int TAG_CONSTANT_String = 8;


    public static final int TAG_CONSTANT_Integer = 3;


    public static final int TAG_CONSTANT_Float = 4;


    public static final int TAG_CONSTANT_Long = 5;


    public static final int TAG_CONSTANT_Double = 6;


    public static final int TAG_CONSTANT_NameAndType = 12;


    public static final int TAG_CONSTANT_Utf8 = 1;


    public static final int TAG_CONSTANT_MethodHandle = 15;


    public static final int TAG_CONSTANT_MethodType = 16;


    public static final int TAG_CONSTANT_InvokeDynamic = 18;


    public static enum TAG {


        CONSTANT_Class(TAG_CONSTANT_Class, CONSTANT_Class_info.class),
        CONSTANT_Fieldref(TAG_CONSTANT_Fieldref, CONSTANT_Fieldref_info.class),
        CONSTANT_Methodref(TAG_CONSTANT_Methodref,
                           CONSTANT_Methodref_info.class),
        CONSTANT_InterfaceMethodref(TAG_CONSTANT_InterfaceMethodref,
                                    CONSTANT_Integer_info.class),
        CONSTANT_String(TAG_CONSTANT_String, CONSTANT_String_info.class),
        CONSTANT_Integer(TAG_CONSTANT_Integer, CONSTANT_Integer_info.class),
        CONSTANT_Float(TAG_CONSTANT_Float, CONSTANT_Float_info.class),
        CONSTANT_Long(TAG_CONSTANT_Long, CONSTANT_Long_info.class),
        CONSTANT_Double(TAG_CONSTANT_Double, CONSTANT_Double_info.class),
        CONSTANT_NameAndType(TAG_CONSTANT_NameAndType,
                             CONSTANT_NameAndType_info.class),
        CONSTANT_Utf8(TAG_CONSTANT_Utf8, CONSTANT_Utf8_info.class),
        CONSTANT_MethodHandle(TAG_CONSTANT_MethodHandle,
                              CONSTANT_MethodHandle_info.class),
        CONSTANT_MethodType(TAG_CONSTANT_MethodType,
                            CONSTANT_MethodType_info.class),
        CONSTANT_InvokeDynamic(TAG_CONSTANT_InvokeDynamic,
                               CONSTANT_InvokeDynamic_info.class);


        public static TAG fromValue(final int value) {

            for (TAG tag : values()) {
                if (tag.value == value) {
                    return tag;
                }
            }

            throw new IllegalArgumentException("Unknown value: " + value);
        }


        public static cp_info newCp_info(final int value) {

            try {
                return fromValue(value)._info.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }


        private TAG(final int value, final Class<? extends cp_info> _info) {
            this.value = value;
            this._info = _info;
        }


        public int getValue() {
            return value;
        }


        private final int value;


        private final Class<? extends cp_info> _info;


    }


    public cp_info(final int tag) {
        super();

        this.tag = tag;
    }


    protected abstract void readInfo(DataInput input) throws IOException;


    protected abstract void writeInfo(DataOutput output) throws IOException;


    private final int tag;


}

