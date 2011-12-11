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


import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @see <a href="http://goo.gl/aA7dn">4.7.4 The Exceptions Attribute</a>
 */
public class Exceptions extends Attribute {


    @Override
    protected void readInfo(final AttributeInfo info, final DataInput input) throws IOException {

        getIndices().clear();
        final int numberOfExceptions = input.readUnsignedShort();
        for (int i = 0; i < numberOfExceptions; i++) {
            getIndices().add(input.readUnsignedShort());
        }
    }


    @Override
    protected void writeInfo(final AttributeInfo info, final DataOutput output) throws IOException {

        output.writeShort(getIndices().size());
        for (int indiex : getIndices()) {
            output.writeShort(indiex);
        }
    }


    private List<Integer> getIndices() {

        if (indices == null) {
            indices = new ArrayList<Integer>();
        }

        return indices;
    }


    @XmlElement(name = "index")
    private List<Integer> indices;


}

