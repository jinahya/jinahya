/*
 *  Copyright 2009 Jin Kwon.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package jinahya.jcff.attribute;


import java.io.Externalizable;
import java.io.IOException;
import java.io.DataInput;
import java.io.DataOutput;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class Annotation {
//    implements Externalizable {


//    private static final long serialVersionUID = -8396109628080450247L;


    /**
     *
     */
    public static class ElementValuePair {
        //implements Externalizable {


//        private static final long serialVersionUID = -3802495050840005004L;


        public void readData(DataInput in)
            throws IOException {

            elementNameIndex = in.readShort() & 0xFF;

            value = new ElementValue();
            value.read(in);
        }


        public void writeData(DataOutput out) throws IOException {
            out.writeShort(elementNameIndex);
            value.write(out);
        }


        public int getElementNameIndex() {
            return elementNameIndex;
        }


        public void setElementNameIndex(int elementNameIndex) {
            this.elementNameIndex = elementNameIndex;
        }


        public ElementValue getValue() {
            return value;
        }


        public void setElementValue(ElementValue value) {
            this.value = value;
        }


        private int elementNameIndex;
        private ElementValue value;
    }


    public void readData(DataInput in)
        throws IOException {

        typeIndex = in.readShort() & 0xFF;

        int numElementValuePairs = in.readShort() & 0xFF;
        for (int i = 0; i < numElementValuePairs; i++) {
            ElementValuePair instance = new ElementValuePair();
            instance.read(in);
            getElementValuePair().add(instance);
        }
    }


    public void writeData(DataOutput out) throws IOException {

        out.writeShort(typeIndex);

        out.writeShort(getElementValuePair().size());
        for (ElementValuePair instance : getElementValuePair()) {
            instance.write(out);
        }
    }


    public int getTypeIndex() {
        return typeIndex;
    }


    public void setTypeIndex(int typeIndex) {
        this.typeIndex = typeIndex;
    }


    public List<ElementValuePair> getElementValuePair() {
        if (elementValuePair == null) {
            elementValuePair = new ArrayList<ElementValuePair>();
        }
        return elementValuePair;
    }


    public void setElementValuePair(List<ElementValuePair> elementValuePair) {
        this.elementValuePair = elementValuePair;
    }


    @Override
    public String toString() {
        return "ANNOTATION: typeIndex( " + typeIndex + "), elementValuePairs(" +
            getElementValuePair().size() + ")";
    }


    private int typeIndex;
    private List<ElementValuePair> elementValuePair;
}
