/*
 *  Copyright 2010 Jin Kwon.
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
 */

package jinahya.io;


import java.io.IOException;

import org.junit.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitOutputTest extends AbstractTest {


    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testWriteUnsignedIntIllegalLength() throws IOException {
        output.writeUnsignedInt(invalidLengthForUnsignedInt(), 0x00);
    }


    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testWriteIntWithIllegalLength() throws IOException {
        output.writeInt(super.invalidLengthForInt(), 0x00);
    }


    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testWriteUnsignedLongWithIllegalLength() throws IOException {
        output.writeUnsignedLong(invalidLengthForUnsignedLong(), 0x00);
    }


    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testWriteLongWithIllegalLength() throws IOException {
        output.writeLong(invalidLengthForLong(), 0x00);
    }


    @Test(expected = java.lang.NullPointerException.class)
    public void testWriteBytesWithNullValue() throws IOException {
        output.writeBytes(null);
    }


    @Test(expected = java.lang.NullPointerException.class)
    public void testWriteModifedUTF8StringWithNullValue() throws IOException {
        output.writeModifiedUTF8String(null);
    }


    @Test(expected = java.lang.NullPointerException.class)
    public void testWriteUSASCIIBytesWithNullValue() throws IOException {
        output.writeUSASCIIBytes(null);
    }


    @Test(expected = java.lang.NullPointerException.class)
    public void testWriteUSASCIIStringWithNullValue() throws IOException {
        output.writeUSASCIIString(null);
    }
}
