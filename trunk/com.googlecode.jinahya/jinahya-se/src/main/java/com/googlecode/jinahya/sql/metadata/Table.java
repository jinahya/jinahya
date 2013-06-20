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


import com.googlecode.jinahya.sql.metadata.Column.ColumnsMapAdapter;
import com.googlecode.jinahya.xml.bind.ValuesMapAdapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class Table {


    public static class Tables extends ValuesMapAdapter.Values<Table> {


        @XmlElement
        public List<Table> getTable() {

            return getValue();
        }


    }


    public static class TablesMapAdapter
        extends ValuesMapAdapter<Table.Tables, String, Table> {


        public TablesMapAdapter() {

            super(Table.Tables.class);
        }


        @Override
        protected String getKey(final Table value) {

            return value.getTableName();
        }


    }


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


    public String getTableName() {

        return tableName;
    }


    public Map<String, Column> getColumns() {

        if (columns == null) {
            columns = new HashMap<String, Column>();
        }

        return columns;
    }


    public Collection<TablePrivilege> getTablePrivileges() {

        if (tablePrivileges == null) {
            tablePrivileges = new ArrayList<TablePrivilege>();
        }

        return tablePrivileges;
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


    @XmlElement(name = "column")
    @XmlElementWrapper(nillable = true, required = true)
    @XmlJavaTypeAdapter(ColumnsMapAdapter.class)
    private Map<String, Column> columns;


    @XmlElement(name = "tablePrivilege")
    @XmlElementWrapper(nillable = true, required = true)
    private Collection<TablePrivilege> tablePrivileges;


}
