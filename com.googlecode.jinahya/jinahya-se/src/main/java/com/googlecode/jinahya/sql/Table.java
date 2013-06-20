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


package com.googlecode.jinahya.sql;


import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class Table {


    @XmlEnum
    public static enum Type {


        @XmlEnumValue(TableType.TABLE)
        TABLE(TableType.TABLE),
        @XmlEnumValue(TableType.VIEW)
        VIEW(TableType.VIEW),
        @XmlEnumValue(TableType.SYSTEM_TABLE)
        SYSTEM_TABLE(TableType.SYSTEM_TABLE),
        @XmlEnumValue(TableType.GLOBAL_TEMPORARY)
        GLOBAL_TEMPORARY(TableType.GLOBAL_TEMPORARY),
        @XmlEnumValue(TableType.LOCAL_TEMPORARY)
        LOCAL_TEMPORARY(TableType.LOCAL_TEMPORARY),
        @XmlEnumValue(TableType.ALIAS)
        ALIAS(TableType.ALIAS),
        @XmlEnumValue(TableType.SYNONYM)
        SYNONYM(TableType.SYNONYM);


        public static Type fromValue(final String type) {

            for (Type value : values()) {
                if (value.type.equals(type)) {
                    return value;
                }
            }

            throw new IllegalArgumentException("unknown type: " + type);
        }


        private Type(final String type) {
            this.type = type;
        }


        private final String type;


    }


    @XmlTransient
    private Schema schema;


    @Label("TABLE_NAME")
    private String tableName;


    private Type tableType;


    @Label("REMARKS")
    private String remarks;


    @Label("TYPE_CAT")
    private String typeCat;


    @Label("TYPE_SCHEM")
    private String typeSchem;


    @Label("TYPE_NAME")
    private String typeName;


    @Label("SELF_REFERENCING_COL_NAME")
    private String selfReferencingColName;


    @Label("REF_GENERATION")
    private String refGeneration;


}

