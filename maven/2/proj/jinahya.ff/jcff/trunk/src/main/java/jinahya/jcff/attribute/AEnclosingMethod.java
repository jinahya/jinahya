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


import java.io.IOException;
import java.io.DataInput;
import java.io.DataOutput;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class AEnclosingMethod extends Attribute {


    private static final long serialVersionUID = -286113225854564195L;


    @Override
    protected void readInfo(DataInput in)
        throws IOException {

        classIndex = in.readShort() & 0xFFFF;
        methodIndex = in.readShort() & 0xFFFF;
    }


    @Override
    protected void writeInfo(DataOutput out) throws IOException {
        out.writeShort(classIndex);
        out.writeShort(methodIndex);
    }


    public int getClassIndex() {
        return classIndex;
    }


    public void setClassIndex(int classIndex) {
        this.classIndex = classIndex;
    }


    public int getMethodIndex() {
        return methodIndex;
    }


    public void setMethodIndex(int methodIndex) {
        this.methodIndex = methodIndex;
    }


    private int classIndex;
    private int methodIndex;
}
