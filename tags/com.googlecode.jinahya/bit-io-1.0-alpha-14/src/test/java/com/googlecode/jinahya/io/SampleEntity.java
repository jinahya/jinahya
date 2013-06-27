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
import java.util.concurrent.ThreadLocalRandom;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class SampleEntity {


    public SampleEntity() {
        super();

        b01 = ThreadLocalRandom.current().nextBoolean();
        ui17 = ThreadLocalRandom.current().nextInt() >>> 15;
        si24 = ThreadLocalRandom.current().nextInt() >> 16;
        ul33 = ThreadLocalRandom.current().nextLong() >>> 31;
        sl48 = ThreadLocalRandom.current().nextLong() >> 16;
    }


    public SampleEntity read(final BitInput input) throws IOException {

        b01 = input.readBoolean();

        ui17 = input.readUnsignedInt(17);

        si24 = input.readInt(24);

        ul33 = input.readUnsignedLong(33);

        sl48 = input.readLong(48);

        return this;
    }


    public SampleEntity write(final BitOutput output) throws IOException {

        output.writeBoolean(b01);

        output.writeUnsignedInt(17, ui17);

        output.writeInt(24, si24);

        output.writeUnsignedLong(33, ul33);

        output.writeLong(48, sl48);

        return this;
    }


    public SampleEntity read(final DataInput input) throws IOException {

        b01 = input.readBoolean();

        ui17 = input.readInt();

        si24 = input.readInt();

        ul33 = input.readLong();

        sl48 = input.readLong();

        return this;
    }


    public SampleEntity write(final DataOutput output) throws IOException {

        output.writeBoolean(b01);

        output.writeInt(ui17);

        output.writeInt(si24);

        output.writeLong(ul33);

        output.writeLong(sl48);

        return this;
    }


    private boolean b01 = ThreadLocalRandom.current().nextBoolean();


    private int ui17 = ThreadLocalRandom.current().nextInt() >>> 15;


    private int si24 = ThreadLocalRandom.current().nextInt() >> 16;


    private long ul33 = ThreadLocalRandom.current().nextLong() >>> 31;


    private long sl48 = ThreadLocalRandom.current().nextLong() >> 16;


}

