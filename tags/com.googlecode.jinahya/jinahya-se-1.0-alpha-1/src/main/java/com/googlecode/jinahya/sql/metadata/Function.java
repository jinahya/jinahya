/*
 * Copyright 2013 Jin Kwon <onacit at gmail.com>.
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


package com.googlecode.jinahya.sql.metadata;


import java.sql.DatabaseMetaData;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class Function {


    @XmlEnum
    public static enum Type {


        @XmlEnumValue("0") // DatabaseMetaData.functionResultUnknown
        FUNCTION_RESULT_UNKNOWN(DatabaseMetaData.functionResultUnknown),
        @XmlEnumValue("1") // DatabaseMetaData.functionNoTable
        FUNCTION_NO_TABLE(DatabaseMetaData.functionNoTable),
        @XmlEnumValue("2") // DatabaseMetaData.functionReturnsTable
        FUNCTION_RETURNS_TABLE(DatabaseMetaData.functionReturnsTable);


        public static Type fromValue(final int type) {

            for (Type value : values()) {
                if (value.type == type) {
                    return value;
                }
            }

            throw new IllegalArgumentException("unknown type: " + type);
        }


        private Type(final int type) {
            this.type = type;
        }


        private final int type;


    }


    private Catalog catalog;


    @Label("FUNCTION_CAT")
    private String functionCat;


    @Label("FUNCTION_SCHEM")
    private String functionSchem;


    @Label("FUNCTION_NAME")
    private String functionName;


    @Label("REMARKS")
    private String remarks;


    @Label("FUNCTION_TYPE")
    private Type functionType;


    @Label("SPECIFIC_NAME")
    private String specificName;


}

