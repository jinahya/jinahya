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


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class cp_info {


    public static final int TAG_CONSTANT_Class = 7;


    public static final int TAG_CONSTANT_Fieldref = 9;


    public static final int TAG_CONSTANT_Methodref = 10;


    public static enum TAG {


        CONSTANT_Class(TAG_CONSTANT_Class),
        CONSTANT_Fieldref(TAG_CONSTANT_Fieldref),
        CONSTANT_Methodref(TAG_CONSTANT_Methodref),
        CONSTANT_InterfaceMethodref(11),
        CONSTANT_String(8),
        CONSTANT_Integer(3),
        CONSTANT_Float(4),
        CONSTANT_Long(5),
        CONSTANT_Double(6),
        CONSTANT_NameAndType(12),
        CONSTANT_Utf8(1),
        CONSTANT_MethodHandle(15),
        CONSTANT_MethodType(16),
        CONSTANT_InvokeDynamic(18);


        public static TAG fromValue(final int value) {
            for (TAG tag : values()) {
                if (tag.value == value) {
                    return tag;
                }
            }
            throw new IllegalArgumentException("Unknown value: " + value);
        }


        private TAG(final int value) {
            this.value = value;
        }


        private int value;


    }


    public cp_info(final TAG tag) {
        super();

        this.tag = tag;
    }


    private final TAG tag;


    protected byte[] info;


}

