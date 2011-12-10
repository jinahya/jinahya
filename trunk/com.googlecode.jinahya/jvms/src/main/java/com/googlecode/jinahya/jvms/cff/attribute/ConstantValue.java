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


package com.googlecode.jinahya.jvms.cff.attribute;


import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @see <a href="http://goo.gl/TVYP9">4.7.2 The ConstantValue Attribute</a>
 */
@XmlType(name = "ConstantValue")
public class ConstantValue extends Attribute {


    @Override
    protected void readContent(final DataInput input) throws IOException {

        constantValueIndex = input.readUnsignedShort();
    }


    @Override
    protected void writeContent(DataOutput output) throws IOException {

        output.writeShort(constantValueIndex);
    }


    @XmlAttribute(required = true)
    private int constantValueIndex;


}
