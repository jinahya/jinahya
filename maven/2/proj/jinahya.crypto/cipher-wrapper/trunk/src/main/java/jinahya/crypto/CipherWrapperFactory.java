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

package jinahya.crypto;


import java.security.Key;
import javax.crypto.Cipher;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class CipherWrapperFactory {


    /** PRIVATE. */
    private static final int DEFAULT_INPUT_BUFFER_SIZE = 256;


    /** PRIVATE. */
    private static final int DEFAULT_INITIAL_OUTPUT_BUFFER_SIZE = 256;


    /** PRIVATE. */
    private static final int DEFAULT_OUTPUT_BUFFER_INCREMENT_SIZE = 128;


    /**
     *
     * @return
     */
    public static CipherWrapperFactory newInstance() {
        return newInstance(DEFAULT_INPUT_BUFFER_SIZE,
                           DEFAULT_INITIAL_OUTPUT_BUFFER_SIZE,
                           DEFAULT_OUTPUT_BUFFER_INCREMENT_SIZE);
    }


    /**
     *
     * @param inputBufferSize
     * @param initialOutputBufferSize
     * @param outputBufferIncrementSize
     * @return
     */
    public static CipherWrapperFactory newInstance(
        final int inputBufferSize,
        final int initialOutputBufferSize,
        final int outputBufferIncrementSize) {


        if (inputBufferSize <= 0) {
            throw new IllegalArgumentException("inputBufferSize <= 0");
        }

        if (initialOutputBufferSize <= 0) {
            throw new IllegalArgumentException("initialOutputBufferSize <= 0");
        }

        if (outputBufferIncrementSize <= 0) {
            throw new IllegalArgumentException(
                "outputBufferIncrementSize <= 0");
        }

        final CipherWrapperFactory instance = new CipherWrapperFactory();

        instance.inputBufferSize = inputBufferSize;
        instance.initialOutputBufferSize = initialOutputBufferSize;
        instance.outputBufferIncrementSize = outputBufferIncrementSize;

        return instance;
    }


    /**
     *
     * @param key
     * @param cipher
     * @return
     */
    public CipherWrapper newCipherWrapper(final Key key, final Cipher cipher) {

        if (key == null) {
            throw new NullPointerException("key");
        }

        if (cipher == null) {
            throw new NullPointerException("cipher");
        }

        final CipherWrapper wrapper = new CipherWrapper();

        wrapper.key = key;
        wrapper.cipher = cipher;

        wrapper.inputBufferSize = inputBufferSize;
        wrapper.initialOutputBufferSize = initialOutputBufferSize;
        wrapper.outputBufferIncrementSize = outputBufferIncrementSize;

        return wrapper;
    }


    /**
     *
     * @return
     */
    public int getInputBufferSize() {
        return inputBufferSize;
    }


    /**
     * 
     * @return
     */
    public int getInitialOutputBufferSize() {
        return initialOutputBufferSize;
    }


    /**
     *
     * @return
     */
    public int getOutputBufferIncrementSize() {
        return outputBufferIncrementSize;
    }


    private int inputBufferSize;
    private int initialOutputBufferSize;
    private int outputBufferIncrementSize;
}
