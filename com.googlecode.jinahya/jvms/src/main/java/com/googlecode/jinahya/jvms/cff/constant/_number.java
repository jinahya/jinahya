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


package com.googlecode.jinahya.jvms.cff.constant;


import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlTransient
abstract class _number extends AbstractConstant {


    public _number(final ConstantTag tag, final int count) {
        super(tag);

        words = new int[count];
    }


    @Override
    public void read(final DataInput input) throws IOException {

        for (int i = 0; i < words.length; i++) {
            words[i] = input.readInt();
        }
    }


    @Override
    public void write(final DataOutput output) throws IOException {

        for (int i = 0; i < words.length; i++) {
            output.writeInt(words[i]);
        }
    }


    protected final int[] words;


}

