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

import javax.xml.bind.annotation.XmlElement;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class AConstantValue extends Attribute {


    private static final long serialVersionUID = 7064127556041287681L;


    @Override
    protected void readInfo(DataInput in)
        throws IOException {

        constantValueIndex = in.readShort() & 0xFF;
    }


    @Override
    protected void writeInfo(DataOutput out) throws IOException {
        out.writeShort(constantValueIndex);
    }


    @XmlElement(required = true)
    public int getConstantValueIndex() {
        return constantValueIndex;
    }


    public void setConstantValueIndex(int constantValueIndex) {
        this.constantValueIndex = constantValueIndex;
    }


    private int constantValueIndex;
}
