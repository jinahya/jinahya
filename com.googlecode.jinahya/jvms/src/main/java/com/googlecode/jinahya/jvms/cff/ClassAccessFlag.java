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


package com.googlecode.jinahya.jvms.cff;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public enum ClassAccessFlag {


    ACC_PUBLIC(0x0001),
    ACC_FINAL(0x0010),
    ACC_SUPER(0x0020),
    ACC_INTERFACE(0x0200),
    ACC_ABSTRACT(0x0400);


    public static ClassAccessFlag valueOf(final int accessFlag) {

        for (ClassAccessFlag value : values()) {
            if (value.getAccessFlag() == accessFlag) {
                return value;
            }
        }

        throw new IllegalArgumentException("no constant for " + accessFlag);
    }


    private ClassAccessFlag(final int accessFlag) {
        this.accessFlag = accessFlag;
    }


    //public abstract Constant newInfo();
    public int getAccessFlag() {
        return accessFlag;
    }


    protected final int accessFlag;


}

