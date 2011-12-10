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


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public enum FieldAccessFlag {


    ACC_PUBLIC(0x0001),
    ACC_FINAL(0x0010),
    ACC_SUPER(0x0020),
    ACC_INTERFACE(0x0200),
    ACC_ABSTRACT(0x0400);


    public static FieldAccessFlag valueOf(final int accessFlagValue) {

        for (FieldAccessFlag accessFlag : values()) {
            if (accessFlag.value == accessFlagValue) {
                return accessFlag;
            }
        }

        throw new IllegalArgumentException(
            "no constant for " + accessFlagValue);
    }


    private FieldAccessFlag(final int value) {
        this.value = value;
    }


    public int getValue() {
        return value;
    }


    /** acces flag. */
    private final int value;


}

