/*
 * Copyright 2013 onacit.
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


package com.googlecode.jinahya.jvm.cff;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ConstantValue_attribute extends attribute_info {


    public int getConstantValueIndex() {

        if (info == null) {
            info = new byte[2];
        }

        return (info[0] & 0xFF) << 8 | (info[1] & 0xFF);
    }


    public void setConstantValueIndex(final int constantValueIndex) {
        info = new byte[2];
        info[0] = (byte) (constantValueIndex >> 8);
        info[1] = (byte) (constantValueIndex & 0xFF);
    }


    private int constant_value_index;


}

