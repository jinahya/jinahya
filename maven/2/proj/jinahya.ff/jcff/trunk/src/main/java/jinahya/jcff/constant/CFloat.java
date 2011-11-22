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


import java.io.IOException;
import java.io.DataInput;
import java.io.DataOutput;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class CFloat extends CNumber<Float> {


    private static final long serialVersionUID = 8766559122536494099L;


    public CFloat() {
        super(CONSTANT_Float);
    }


    @Override
    public void readInfo(DataInput in)
        throws IOException {

        setValue(in.readFloat());
    }


    @Override
    public void writeInfo(DataOutput out) throws IOException {
        out.writeFloat(getValue());
    }
}