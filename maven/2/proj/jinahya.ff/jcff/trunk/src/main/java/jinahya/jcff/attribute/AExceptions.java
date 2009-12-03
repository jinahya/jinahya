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
public class AExceptions extends Attribute {


    private static final long serialVersionUID = -8830349142226380876L;


    @Override
    protected void readInfo(DataInput in)
        throws IOException {

        int numberOfExceptions = in.readShort() & 0xFFFF;
        for (int i = 0 ; i < numberOfExceptions; i++) {
            getExceptionIndex().add(in.readShort() & 0xFFFF);
        }
    }


    @Override
    protected void writeInfo(DataOutput out) throws IOException {
        out.writeShort(getExceptionIndex().size());
        for (Integer element : getExceptionIndex()) {
            out.writeShort(element);
        }
    }


    public List<Integer> getExceptionIndex() {
        if (exceptionIndex == null) {
            exceptionIndex = new ArrayList<Integer>();
        }
        return exceptionIndex;
    }


    public void setExceptionIndex(List<Integer> exceptionIndex) {
        this.exceptionIndex = exceptionIndex;
    }

    
    private List<Integer> exceptionIndex;
}
