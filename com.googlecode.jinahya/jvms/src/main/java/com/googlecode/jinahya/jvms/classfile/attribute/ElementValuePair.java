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


package com.googlecode.jinahya.jvms.classfile.attribute;


import com.googlecode.jinahya.jvms.classfile.DataAccessible;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlElement;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ElementValuePair implements DataAccessible {


    @Override
    public void read(final DataInput input) throws IOException {

        elementNameIndex = input.readUnsignedShort();

        value = new ElementValue();
        value.read(input);
    }


    @Override
    public void write(final DataOutput output) throws IOException {
    }


    private int elementNameIndex;


    private ElementValue value;


}

