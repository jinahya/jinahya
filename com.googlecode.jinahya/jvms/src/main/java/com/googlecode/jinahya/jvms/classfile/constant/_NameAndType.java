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


package com.googlecode.jinahya.jvms.classfile.constant;


import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlType(propOrder = {"nameIndex", "descriptorIndex"})
public class _NameAndType extends AbstractConstant {


    protected _NameAndType() {
        super(Tag._NameAndType);
    }


    @Override
    public void read(final DataInput input) throws IOException {

        nameIndex = input.readUnsignedShort();
        descriptorIndex = input.readUnsignedShort();
    }


    @Override
    public void write(final DataOutput output) throws IOException {

        output.writeShort(nameIndex);
        output.writeShort(descriptorIndex);
    }


    @XmlAttribute(required = true)
    private int nameIndex;


    @XmlAttribute(required = true)
    private int descriptorIndex;


}

