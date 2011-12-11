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


package com.googlecode.jinahya.jvms.classfile.attribute.stackmap;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public enum VerificationTypeTag {


    ITEM_Top(0, TopVariable.class),
    ITEM_Integer(1, IntegerVariable.class),
    ITEM_Float(2, FloatVariable.class),
    ITEM_Double(3, DoubleVariable.class),
    ITEM_Long(4, LongVariable.class),
    ITEM_Null(5, NullVariable.class),
    ITEM_UninitializedThis(6, UninitializedThisVariable.class),
    ITEM_Object(7, ObjectVariable.class),
    ITEM_Uninitialized(8, UninitializedVariable.class);


    public static VerificationTypeTag valueOf(final int value) {

        for (VerificationTypeTag tag : values()) {
            if (tag.value == value) {
                return tag;
            }
        }

        throw new IllegalArgumentException("no constant for " + value);
    }


    /**
     * Creates a new instance.
     *
     * @param value value
     * @param type type
     */
    private VerificationTypeTag(final int value,
                                final Class<? extends VerificationType> type) {

        this.value = value;
        this.type = type;
    }


    public VerificationType newType() {
        try {
            return type.newInstance();
        } catch (InstantiationException ie) {
            throw new RuntimeException(
                "failed to create a new instance of " + type, ie);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(
                "failed to create a new instance of " + type, iae);
        }
    }


    public final int getValue() {
        return value;
    }


    private final int value;


    private final Class<? extends VerificationType> type;


}

