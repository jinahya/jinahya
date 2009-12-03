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

package jinahya.qtff;


import java.io.IOException;

import jinahya.bitio.BitInput;
import jinahya.bitio.BitOutput;
import jinahya.bitio.Digitizable;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class QTAtomContainer implements Digitizable {


    @Override
    public void readDigits(BitInput in) throws IOException {
        for (int i = 0; i < 10; i++) {
            assert in.readInt(8) == 0x00;
        }
        assert in.readInt(16) == 0x00;
    }


    @Override
    public void writeDigits(BitOutput out) throws IOException {
        for (int i = 0; i < 10; i++) {
            out.writeInt(8, 0x00);
        }
        out.writeInt(16, 0x00);
    }


    private QTAtom root;
}
