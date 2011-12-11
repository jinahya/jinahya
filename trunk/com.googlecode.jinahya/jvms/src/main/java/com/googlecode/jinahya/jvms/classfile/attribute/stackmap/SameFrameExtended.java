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


package com.googlecode.jinahya.jvms.classfile.attribute.stackmap;


import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class SameFrameExtended extends SameFrame {


    @Override
    protected void readInfo(final DataInput input) throws IOException {

        super.readInfo(input);

        offsetDelta = input.readUnsignedShort();
    }


    @Override
    protected void writeInfo(final DataOutput output) throws IOException {

        super.writeInfo(output);

        output.writeShort(offsetDelta);
    }


    private int offsetDelta;


}

