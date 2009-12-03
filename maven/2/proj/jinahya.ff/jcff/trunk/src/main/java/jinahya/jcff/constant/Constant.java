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

package jinahya.jcff.constant;


import java.io.Externalizable;
import java.io.IOException;
import java.io.DataInput;
import java.io.DataOutput;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class Constant {
    //implements Externalizable {


//    private static final long serialVersionUID = -3212104781590944873L;


    public static final int CONSTANT_Class =              0x07;

    public static final int CONSTANT_Fieldref =           0x09;

    public static final int CONSTANT_Methodref =          0x0A; // 10

    public static final int CONSTANT_InterfaceMethodref = 0x0B; // 11

    public static final int CONSTANT_String =             0x08;

    public static final int CONSTANT_Integer =            0x03;

    public static final int CONSTANT_Float =              0x04;

    public static final int CONSTANT_Long =               0x05;

    public static final int CONSTANT_Double =             0x06;

    public static final int CONSTANT_NameAndType =        0x0C; // 12

    public static final int CONSTANT_Utf8 =               0x01;


    public static Constant readInstance(DataInput in)
        throws IOException {

        int tag = in.readByte() & 0xFF;

        Constant instance;

        switch (tag) {
            case CONSTANT_Class:
                instance = new CClass();
                break;
            case CONSTANT_Fieldref:
                instance = new CFieldref();
                break;
            case CONSTANT_Methodref:
                instance = new CMethodref();
                break;
            case CONSTANT_InterfaceMethodref:
                instance = new CInterfaceMethodref();
                break;
            case CONSTANT_String:
                instance = new CString();
                break;
            case CONSTANT_Integer:
                instance = new CInteger();
                break;
            case CONSTANT_Float:
                instance = new CFloat();
                break;
            case CONSTANT_Long:
                instance = new CLong();
                break;
            case CONSTANT_Double:
                instance = new CDouble();
                break;
            case CONSTANT_NameAndType:
                instance = new CNameAndType();
                break;
            case CONSTANT_Utf8:
                instance = new CUtf8();
                break;
            default:
                throw new IOException("unknown tag: " + tag);
                //break;
        }

        instance.readInfo(in);

        return instance;
    }


    protected Constant(int tag) {
        super();

        this.tag = tag;
    }


    public final void readData(DataInput in) throws IOException {
        tag = in.readByte() & 0xFF;
        readInfo(in);
    }


    /**
     *
     * @param in
     * @throws IOException
     */
    protected abstract void readInfo(DataInput in) throws IOException;


    public final void writeData(DataOutput out) throws IOException {
        out.writeShort(tag);
        writeInfo(out);
    }


    /**
     * 
     * @param out
     * @throws IOException
     */
    protected abstract void writeInfo(DataOutput out) throws IOException;


    public int getTag() {
        return tag;
    }


    public void setTag(int tag) {
        this.tag = tag;
    }


    private int tag;
}
