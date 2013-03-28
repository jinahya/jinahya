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


package com.googlecode.jinahya.io;


import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Random;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class SampleEntity {


    public static SampleEntity newInstance(final Random random) {

        return new SampleEntity(
            random.nextBoolean(),
            random.nextInt() >>> 0x0F,
            random.nextInt() >> 0x10,
            random.nextLong() >>> 0x1F,
            random.nextLong() >> 0x10);
    }


    protected SampleEntity(final boolean b, final int ui0x11, final int si0x18,
                           final long ul0x21, final long sl0x30) {
        super();

        if (ui0x11 >> 0x11 != 0x00) { // unsigned 17 bit
            throw new IllegalArgumentException(
                "ui0x11(" + ui0x11 + ") >> 0x11 != 0x00");
        }

        if (si0x18 < 0) {
            if (si0x18 >> 0x18 != -1) { // signed 24 bit
                throw new IllegalArgumentException(
                    "si0x18(" + si0x18 + ") >> 0x18 != -1");
            }
        } else {
            if (si0x18 >> 0x18 != 0x00) { // signed 24 bit
                throw new IllegalArgumentException(
                    "si0x18(" + si0x18 + ") >> 0x18 != 0x00");
            }
        }

        if (ul0x21 >> 0x21 != 0x00L) {
            throw new IllegalArgumentException(
                "ul0x21(" + ul0x21 + ") >> 0x21 != 0x00L");
        }

        if (sl0x30 < 0x00L) {
            if (sl0x30 >> 0x30 != -1L) {
                throw new IllegalArgumentException(
                    "sl0x30(" + sl0x30 + ") >> 0x30 != -1L");
            }
        } else {
            if (sl0x30 >> 0x30 != 0x00L) {
                throw new IllegalArgumentException(
                    "sl0x30(" + sl0x30 + ") >> 0x30 != 0x00L");
            }

        }

        this.b = b;
        this.ui0x11 = ui0x11;
        this.si0x18 = si0x18;
        this.ul0x21 = ul0x21;
        this.sl0x30 = sl0x30;
    }


    public SampleEntity read(final BitInput input) throws IOException {

        b = input.readBoolean();

        ui0x11 = input.readUnsignedInt(0x11);

        si0x18 = input.readInt(0x18);

        ul0x21 = input.readUnsignedLong(0x21);

        sl0x30 = input.readLong(0x30);

        return this;
    }


    public SampleEntity write(final BitOutput output) throws IOException {

        output.writeBoolean(b);

        output.writeUnsignedInt(0x11, ui0x11);

        output.writeInt(0x18, si0x18);

        output.writeUnsignedLong(0x21, ul0x21);

        output.writeLong(0x30, sl0x30);

        return this;
    }


    public SampleEntity read(final DataInput input) throws IOException {

        b = input.readBoolean();

        ui0x11 = input.readInt();

        si0x18 = input.readInt();

        ul0x21 = input.readLong();

        sl0x30 = input.readLong();

        return this;
    }


    public SampleEntity write(final DataOutput output) throws IOException {

        output.writeBoolean(b);

        output.writeInt(ui0x11);

        output.writeInt(si0x18);

        output.writeLong(ul0x21);

        output.writeLong(sl0x30);

        return this;
    }


    private boolean b;


    private int ui0x11;


    private int si0x18;


    private long ul0x21;


    private long sl0x30;


}

