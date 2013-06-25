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
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ClassFile {


//    private static final Logger LOGGER =
//        Logger.getLogger(ClassFile.class.getName());
//        Logger.getLogger(MethodHandles.lookup().getClass().getName());
    /**
     * The magic.
     */
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

            for (ACCESS_FLAG constant : values()) {
                if (constant.value == value) {
                    return constant;
                }
            }

            throw new IllegalArgumentException("Unknown value(" + value + ")");
        }


        ACCESS_FLAG(final int value) {
            this.value = value;
        }


        public int getValue() {
            return value;
        }


        private final int value;


    }


    public void read(final DataInput input) throws IOException {

        final int magic = input.readInt();

        minor_version = input.readUnsignedShort();

        major_version = input.readUnsignedShort();

        final int constant_pool_count = input.readUnsignedShort();
        System.out.println("constant_pool_count: " + constant_pool_count);
        constant_pool.clear();
        ((ArrayList) constant_pool).ensureCapacity(constant_pool_count - 1);
        for (int i = 1; i < constant_pool_count; i++) {
            final int tag = input.readUnsignedByte();
            final cp_info constant = cp_info.TAG.newConstant(tag);
            constant.classFile = this;
            constant.readInfo(input);
            System.out.println("tag[" + i + "]: " + tag + " " + constant);
            constant_pool.add(constant);
        }

        access_flags = input.readUnsignedShort();

        this_class = input.readUnsignedShort();
        final CONSTANT_Class_info cpi = getCp_info(CONSTANT_Class_info.class, this_class);
        final CONSTANT_Utf8_info cui = getCp_info(CONSTANT_Utf8_info.class, cpi.getName_index());
        System.out.println(cui.getInfo());

        super_class = input.readUnsignedShort();

        final int interfaces_count = input.readUnsignedShort();
        System.out.println("interfaces_count: " + interfaces_count);
        interfaces.clear();
        ((ArrayList) interfaces).ensureCapacity(interfaces_count);
        for (int i = 0; i < interfaces_count; i++) {
            interfaces.add(input.readUnsignedShort());
        }

        final int fields_count = input.readUnsignedShort();
        System.out.println("fields_count: " + fields_count);
        fields.clear();
        ((ArrayList) fields).ensureCapacity(fields_count);
        for (int i = 0; i < fields_count; i++) {
            final field_info field = new field_info();
            field.classFile = this;
            fields.add(field);
            field.read(input);
        }


//        final int fields_count = input.readUnsignedShort();
//        System.out.println("fields_count: " + fields_count);
//        fields.clear();
//        ((ArrayList) fields).ensureCapacity(fields_count);
//        for (int i = 0; i < fields_count; i++) {
//            final field_info field = new field_info();
//            field.classFile = this;
//            fields.add(field);
//            field.read(input);
//        }
    }


    public void write(final DataOutput output) throws IOException {

        output.writeInt(MAGIC);

        output.writeShort(minor_version);
        output.writeShort(major_version);

        output.writeShort(constant_pool.size() + 1);
        for (cp_info info : constant_pool) {
            info.writeInfo(output);
        }

        output.writeShort(access_flags);

        output.writeShort(this_class);

        output.writeShort(super_class);

        output.writeShort(interfaces.size()); // -------------- interfaces_count
        for (int interface_ : interfaces) {
            output.writeShort(interface_);
        }
    }


    public int getMinor_version() {
        return minor_version;
    }


    public int getMajor_version() {
        return major_version;
    }


    public cp_info getCp_info(final int index) {
        return constant_pool.get(index - 1);
    }


    public <T extends cp_info> T getCp_info(final Class<T> type,
                                            final int index) {
        return type.cast(getCp_info(index));
    }


    public int getAccess_flags() {
        return access_flags;
    }


    public int getThis_class() {
        return this_class;
    }


    public int getSuper_class() {
        return super_class;
    }


    private int magic;


    private int minor_version;


    private int major_version;


    private final List<cp_info> constant_pool = new ArrayList<>();


    private int access_flags;


    private int this_class;


    private int super_class;


    private final List<Integer> interfaces = new ArrayList<>();


    private final List<field_info> fields = new ArrayList<>();

private final List<method_info> methods = new ArrayList<>();
}

