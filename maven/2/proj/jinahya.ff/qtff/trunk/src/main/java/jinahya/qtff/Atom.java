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
public class Atom implements Digitizable {


    @Override
    public void readDigits(BitInput in) throws IOException {
        size = in.readInt(32);
        type = in.readInt(32);
        if (size == 0x01L) {
            size = in.readLong(64);
        }
    }


    @Override
    public void writeDigits(BitOutput out) throws IOException {
        boolean sizeExtended = isSizeExtended();
        out.writeLong(32, sizeExtended ? size : 0x01L);
        out.writeInt(32, type);
        if (sizeExtended) {
            out.writeLong(64, size);
        }
    }


    public boolean isSizeExtended() {
        return size > Math.pow(2, 32);
    }


    private long size;
    private int type;
}
