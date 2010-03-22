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


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitInputTest extends AbstractTest {


    @org.junit.Test(expected = IllegalArgumentException.class)
    @org.testng.annotations.Test(expectedExceptions = {IllegalArgumentException.class})
    public void testReadUnsignedIntIllegalLength() throws IOException {
        input.readUnsignedInt(invalidLengthForUnsignedInt());
    }


    @org.junit.Test(expected = IllegalArgumentException.class)
    @org.testng.annotations.Test(expectedExceptions = {IllegalArgumentException.class})
    public void testReadIntWithIllegalLength() throws IOException {
        input.readInt(invalidLengthForInt());
    }


    @org.junit.Test(expected = IllegalArgumentException.class)
    @org.testng.annotations.Test(expectedExceptions = {IllegalArgumentException.class})
    public void testReadUnsignedLongWithIllegalLength() throws IOException {
        input.readUnsignedLong(invalidLengthForUnsignedLong());
    }


    @org.junit.Test(expected = IllegalArgumentException.class)
    @org.testng.annotations.Test(expectedExceptions = {IllegalArgumentException.class})
    public void testReadLongWithIllegalLength() throws IOException {
        input.readLong(invalidLengthForLong());
    }
}
