/*
 * Copyright 2013 onacit.
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
import java.io.IOException;
import java.util.List;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ClassFile {


    public static final int MAGIC = 0xCAFEBABE;


    public static enum ACCESS_FLAG {


        ACC_PUBLIC(0x0001),
        ACC_FINAL(0x0010),
        ACC_SUPER(0x0020),
        ACC_INTERFACE(0x0200),
        ACC_ABSTRACT(0x0400),
        ACC_SYNTHETIC(0x1000),
        ACC_ANNOTATION(0x2000),
        ACC_ENUM(0x4000);


        public static ACCESS_FLAG fromValue(final int value) {

            for (ACCESS_FLAG flag : values()) {
                if (flag.value == value) {
                    return flag;
                }
            }

            throw new IllegalArgumentException("Unknown value(" + value + ")");
        }


        ACCESS_FLAG(int value) {
            this.value = value;
        }


        public int getValue() {
            return value;
        }


        int value;


    }


    public void read(final DataInput input) throws IOException {

        magic = input.readInt();

        minor_version = input.readUnsignedShort();

        major_version = input.readUnsignedShort();

        final int constant_pull_count = input.readUnsignedShort();

        for (int i = 0; i <= constant_pull_count; i++) {
        }
    }


    private int magic;


    private int minor_version;


    private int major_version;


    private int access_flags;


    private int this_class;


    private int super_class;


    private List<Integer> interfaces;


}

