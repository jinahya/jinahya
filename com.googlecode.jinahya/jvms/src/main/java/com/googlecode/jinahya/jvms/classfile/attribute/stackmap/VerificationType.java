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
public abstract class VerificationType {


    public static VerificationType readInstance(final DataInput input)
        throws IOException {

        final int value = input.readUnsignedByte();
        final VerificationTypeTag tag = VerificationTypeTag.valueOf(value);
        final VerificationType type = tag.newType();
        type.readInfo(input);
        return type;
    }


    public static void writeInstance(final DataOutput output,
                                     final VerificationType type)
        throws IOException {

        output.writeByte(type.getTag().getValue());

        type.writeInfo(output);
    }


    protected abstract void readInfo(final DataInput input) throws IOException;


    protected abstract void writeInfo(final DataOutput output)
        throws IOException;


    public final VerificationTypeTag getTag() {
        return tag;
    }


    public final void setTag(final VerificationTypeTag tag) {
        this.tag = tag;
    }


    private VerificationTypeTag tag;


}

