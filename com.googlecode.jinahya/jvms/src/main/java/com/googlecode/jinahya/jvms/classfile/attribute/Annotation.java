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

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class Annotation implements DataAccessible {


    @Override
    public void read(final DataInput input) throws IOException {

        typeIndex = input.readUnsignedShort();

        final int numElementValuePairs = input.readUnsignedShort();
        getElementValuePairs().clear();
        for (int i = 0; i < numElementValuePairs; i++) {
            final ElementValuePair elementValuePair =new ElementValuePair();
            elementValuePair.read(input);
            getElementValuePairs().add(elementValuePair);
        }
    }


    @Override
    public void write(final DataOutput output) throws IOException {
    }


    public int getTypeIndex() {
        return typeIndex;
    }


    private List<ElementValuePair> getElementValuePairs() {

        if (elementValuePairs == null) {
            elementValuePairs = new ArrayList<ElementValuePair>();
        }

        return elementValuePairs;
    }


    @XmlElement(required = true)
    private int typeIndex;


    @XmlElement(name = "elementValuePair")
    @XmlElementWrapper(required = true)
    private List<ElementValuePair> elementValuePairs;


}

