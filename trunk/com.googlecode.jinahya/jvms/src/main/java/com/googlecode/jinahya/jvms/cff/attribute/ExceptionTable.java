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


import com.googlecode.jinahya.jvms.cff.DataAccessible;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import javax.xml.bind.annotation.XmlAttribute;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ExceptionTable implements DataAccessible {


    @Override
    public void read(final DataInput input) throws IOException {

        startPc = input.readUnsignedShort();

        endPc = input.readUnsignedShort();

        handlerPc = input.readUnsignedShort();

        catchType = input.readUnsignedShort();
    }


    @Override
    public void write(final DataOutput output) throws IOException {

        output.writeShort(startPc);

        output.writeShort(endPc);

        output.writeShort(handlerPc);

        output.writeShort(catchType);
    }


    @XmlAttribute(required = true)
    private int startPc;


    @XmlAttribute(required = true)
    private int endPc;


    @XmlAttribute(required = true)
    private int handlerPc;


    @XmlAttribute(required = true)
    private int catchType;


}

