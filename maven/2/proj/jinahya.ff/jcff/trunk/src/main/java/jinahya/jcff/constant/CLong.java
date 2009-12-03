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
public class CLong extends CNumber<Long> {


    private static final long serialVersionUID = -3337125790782060698L;


    public CLong() {
        super(CONSTANT_Long);
    }


    @Override
    public void readInfo(DataInput in)
        throws IOException {

        setValue(in.readLong());
    }


    @Override
    public void writeInfo(DataOutput out) throws IOException {
        out.writeLong(getValue());
    }
}
