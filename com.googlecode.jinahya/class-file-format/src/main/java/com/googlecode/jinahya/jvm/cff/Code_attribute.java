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
public class Code_attribute extends attribute_info {


    public static class exception_table {


        protected void read(final DataInput input) throws IOException {
            start_pc = input.readUnsignedShort();
            end_pc = input.readUnsignedShort();
            handler_pc = input.readUnsignedShort();
            catch_type = input.readUnsignedShort();
        }


        private int start_pc;


        private int end_pc;


        private int handler_pc;


        private int catch_type;


    }


    @Override
    protected void readInfo(final DataInput input) throws IOException {

        max_stack = input.readUnsignedShort();

        max_locals = input.readUnsignedShort();

        final int code_length = input.readInt();
        code = new byte[code_length];
        input.readFully(code);

        final int exception_table_length = input.readUnsignedShort();
        exception_table = new exception_table[exception_table_length];
        for (int i = 0; i < exception_table.length; i++) {
            exception_table[i] = new exception_table();
            exception_table[i].read(input);
        }
    }


    @Override
    protected void writeInfo(final DataOutput output) throws IOException {
    }


    private int max_stack;


    private int max_locals;


    private byte[] code;


    private exception_table[] exception_table;


    private final List<attribute_info> attributes = new ArrayList<>();


}

