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
public class ElementValue {
    //implements Externalizable {


    private static final long serialVersionUID = -8791214393278459507L;


    public static ElementValue newInstanceForValue(int tag,
                                                   int constValueIndex) {
        ElementValue instance = new ElementValue();
        instance.tag = tag;
        instance.constValueIndex = constValueIndex;
        return instance;
    }


    public static ElementValue newInstanceForEnum(int typeNameIndex,
                                                  int constNameIndex) {
        ElementValue instance = new ElementValue();
        instance.tag = 'e';
        instance.typeNameIndex = typeNameIndex;
        instance.constNameIndex = constNameIndex;
        return instance;
    }


    public static ElementValue newInstanceForClass(int classInfoIndex) {
        ElementValue instance = new ElementValue();
        instance.tag = 'c';
        instance.classInfoIndex = classInfoIndex;
        return instance;
    }


    public static ElementValue newInstanceForAnnotation
        (Annotation annotationValue) {

        ElementValue instance = new ElementValue();
        instance.tag = '@';
        instance.annotationValue = annotationValue;
        return instance;
    }


    public static ElementValue newInstanceForArray(ElementValue... values) {
        ElementValue instance = new ElementValue();
        instance.tag = '[';
        instance.getValue().addAll(java.util.Arrays.asList(values));
        return instance;
    }


    public void readData(DataInput in) throws IOException {

        tag = in.readByte() & 0xFF;

        switch (tag) {
            case 'B':
            case 'C':
            case 'D':
            case 'F':
            case 'I':
            case 'J':
            case 'S':
            case 'Z':
            case 's':
                constValueIndex = in.readShort() & 0xFF;
                break;
            case 'e':
                typeNameIndex = in.readShort() & 0xFF;
                constNameIndex = in.readShort() & 0xFF;
                break;
            case 'c':
                classInfoIndex = in.readShort() & 0xFF;
                break;
            case '@':
                annotationValue = new Annotation();
                annotationValue.read(in);
                break;
            case '[':
                int numValues = in.readShort() & 0xFFFF;
                for (int i = 0; i < numValues; i++) {
                    ElementValue instance = new ElementValue();
                    instance.read(in);
                    getValue().add(instance);
                }
                break;
            default:
                throw new IOException("unknown tag: " + (char) tag);
        }
    }


    public void writeData(DataOutput out) throws IOException {

        out.writeByte(tag);

        switch (tag) {
            case 'B':
            case 'C':
            case 'D':
            case 'F':
            case 'I':
            case 'J':
            case 'S':
            case 'Z':
            case 's':
                out.writeShort(constValueIndex);
                break;
            case 'e':
                out.writeShort(typeNameIndex);
                out.writeShort(constNameIndex);
                break;
            case 'c':
                out.writeShort(classInfoIndex);
                break;
            case '@':
                annotationValue.write(out);
                break;
            case '[':
                out.writeShort(getValue().size());
                for (ElementValue instance : getValue()) {
                    instance.write(out);
                }
                break;
            default:
                throw new IOException("unknown tag: " + (char) tag);
        }
    }


    public int getTag() {
        return tag;
    }


    public void setTag(int tag) {
        this.tag = tag;
    }



    public List<ElementValue> getValue() {
        if (value == null) {
            value = new ArrayList<ElementValue>();
        }
        return value;
    }


    public void setValues(List<ElementValue> value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return "ElementValue: tag(" + (char) tag + "), constValueIndex(" +
            constValueIndex + "), typeNameIndex(" + typeNameIndex +
            "), constNameIndex(" + constNameIndex + "), classInfoIndex(" +
            classInfoIndex + "), annotationValue(" + annotationValue +
            "), values(" + getValue().size() + ")";
    }


    private int tag;

    private int constValueIndex;

    private int typeNameIndex;
    private int constNameIndex;

    private int classInfoIndex;

    private Annotation annotationValue;

    private List<ElementValue> value;
}
