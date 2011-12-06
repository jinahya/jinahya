/*
 * Copyright 2011 Jin Kwon <jinahya at gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.googlecode.jinahya.util;


import com.googlecode.jinahya.io.BitInput;
import com.googlecode.jinahya.io.BitOutput;

import java.io.IOException;


/**
 * Interface for objects can accessible bits.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public interface BitAccessible extends Accessible {


    /**
     * Reads information from given <code>input</code>.
     *
     * @param input input
     * @throws IOException if an I/O error occurs.
     */
    void read(BitInput input) throws IOException;


    /**
     * Writes information to given <code>output</code>.
     *
     * @param output output
     * @throws IOException if an I/O error occurs.
     */
    void write(BitOutput output) throws IOException;


}

