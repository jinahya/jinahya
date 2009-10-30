/*
 *  Copyright 2009 onacit.
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

package jinahya.io.bitio;


import java.io.IOException;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class BitInput {


    /**
     *
     * @param input
     */
    public BitInput(ByteInput input) {
        super();

        this.input = input;

        count = 0L;
    }


    public boolean readBoolean() throws IOException {
        return readByte(1) == 0x01;
    }


    private int readByte(int length) throws IOException {
        return -1;
    }


    private int readShort(int length) throws IOException {
        return -1;
    }


    public int readInt(int length) throws IOException {
        int value = readBoolean() ? (-1 << (length - 1)) : 0x00;
        return (value | readUnsignedInt(length - 1));
    }


    public int readUnsignedInt(int length) throws IOException {
        return -1;
    }


    public long readLong(int length) throws IOException {
        long value = readBoolean() ? (-1L << (length - 1)) : 0x00L;
        return (value | readUnsignedLong(length - 1));
    }


    public long readUnsignedLong(int length) throws IOException {
        return -1L;
    }


    /**
     *
     * @return
     */
    public long getCount() {
        return count;
    }


    /**
     *
     * @return
     */
    public boolean isDebugMode() {
        return debugMode;
    }


    /**
     *
     * @param debugMode
     */
    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }


    private ByteInput input;

    private long count;
    private boolean debugMode = false;
}
