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


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ElementValue implements DataAccessible {


    public void read(final DataInput input) throws IOException {

        tag = input.readUnsignedByte();

        switch (tag) {
            case 0x42: // 66  'B'
            case 0x43: // 67  'C'
            case 0x44: // 68  'D'
            case 0x46: // 70  'F'
            case 0x49: // 73  'I'
            case 0x4A: // 74  'J'
            case 0x53: // 83  'S'
            case 0x5A: // 90  'Z'
            case 0x73: // 115 's' String
                constValueIndex = input.readUnsignedShort();
                break;
            case 0x65: // 101 'e' enum constant
                typeNameIndex = input.readUnsignedShort();
                constNameIndex = input.readUnsignedShort();
                break;
            case 0x63: // 99  'c' class
                classInfoIndex = input.readUnsignedShort();
                break;
            case 0x40: // 64  '@' annotation type
                annotationValue = new Annotation();
                annotationValue.read(input);
                break;
            case 0x5B: // 91  '[' array
                final int numValues = input.readUnsignedShort();
                getArrayValue().clear();
                for (int i = 0; i < numValues; i++) {
                    final ElementValue elementValue = new ElementValue();
                    elementValue.read(input);
                    getArrayValue().add(elementValue);
                }
                break;
            default:
                break;
        }
    }


    public void write(DataOutput output) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    private List<ElementValue> getArrayValue() {

        if (arrayValue == null) {
            arrayValue = new ArrayList<ElementValue>();
        }

        return arrayValue;
    }


    private int tag;


    private int constValueIndex;


    private int typeNameIndex;


    private int constNameIndex;


    private int classInfoIndex;


    private Annotation annotationValue;


    private List<ElementValue> arrayValue;


}

