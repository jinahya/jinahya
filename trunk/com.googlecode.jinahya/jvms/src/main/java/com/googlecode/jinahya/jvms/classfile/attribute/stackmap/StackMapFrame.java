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


import com.googlecode.jinahya.jvms.classfile.DataAccessible;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class StackMapFrame implements DataAccessible {


    @Override
    public void read(final DataInput input) throws IOException {

        frameType = input.readUnsignedByte();
    }


    @Override
    public void write(final DataOutput output) throws IOException {

        output.writeByte(frameType);
    }


    // ----------------------------------------------------------- previousFrame
    public StackMapFrame getPreviousFrame() {
        return previousFrame;
    }


    public void setPreviousFrame(final StackMapFrame previousFrame) {
        this.previousFrame = previousFrame;
    }


    // --------------------------------------------------------------- frameType
    public int getFrameType() {
        return frameType;
    }


    public void setFrameType(final int frameType) {
        this.frameType = frameType;
    }


    public StackMapFrame previousFrame;


    private int frameType;


}

