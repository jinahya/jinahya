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


import java.sql.ResultSet;
import java.util.Map;
import java.util.TreeMap;
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


    public static Table newInstance(final Schema schema,
                                    final ResultSet resultSet) {

        final Table instance = new Table();

        instance.schema = schema;

        return instance;
    }


    // ------------------------------------------------------------------ schema
    public Schema getSchema() {

        return schema;
    }


    // -------------------------------------------------------------- TABLE_NAME
    public String getTableName() {
        return tableName;
    }


    public void setTableName(String tableName) {
        this.tableName = tableName;
    }


    // -------------------------------------------------------------- TABLE_TYPE
    public String getTableType() {
        return tableType;
    }


    public void setTableType(String tableType) {
        this.tableType = tableType;
    }


    // ----------------------------------------------------------------- REMARKS
    public String getRemarks() {
        return remarks;
    }


    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    // ---------------------------------------------------------------- TYPE_CAT
    public String getTypeCat() {
        return typeCat;
    }


    public void setTypeCat(String typeCat) {
        this.typeCat = typeCat;
    }


    // -------------------------------------------------------------- TYPE_SCHEM
    public String getTypeSchem() {
        return typeSchem;
    }


    public void setTypeSchem(String typeSchem) {
        this.typeSchem = typeSchem;
    }


    // --------------------------------------------------------------- TYPE_NAME
    public String getTypeName() {
        return typeName;
    }


    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }


    // ----------------------------------------------- SELF_REFERENCING_COL_NAME
    public String getSelfReferencingColName() {
        return selfReferencingColName;
    }


    public void setSelfReferencingColName(String selfReferencingColName) {
        this.selfReferencingColName = selfReferencingColName;
    }


    // ---------------------------------------------------------- REF_GENERATION
    public String getRefGeneration() {
        return refGeneration;
    }


    public void setRefGeneration(String refGeneration) {
        this.refGeneration = refGeneration;
    }


    // ----------------------------------------------------------------- columns
    public Map<String, Column> getColumns() {

        if (columns == null) {
            columns = new TreeMap<String, Column>();
        }

        return columns;
    }


    // --------------------------------------------------------- tablePrivileges
    public Map<String, TablePrivilege> getTablePrivileges() {

        if (tablePrivileges == null) {
            tablePrivileges = new TreeMap<String, TablePrivilege>();
        }

        return tablePrivileges;
    }


    // ---------------------------------------------------------- versionColumns
    public Map<String, VersionColumn> getVersionColumns() {

        if (versionColumns == null) {
            versionColumns = new TreeMap<String, VersionColumn>();
        }

        return versionColumns;
    }


    @ColumnLabel("TABLE_CAT")
    @XmlTransient
    private String tableCat;


    @ColumnLabel("TABLE_CAT")
    @XmlTransient
    private String tableSchem;


    @XmlTransient
    private Schema schema; // ------------------------------------------- schema


    @ColumnLabel("TABLE_NAME")
    @XmlElement(required = true)
    private String tableName;


    @ColumnLabel("TABLE_TYPE")
    @XmlElement(required = true)
    private String tableType;


    @ColumnLabel("REMARKS")
    @XmlElement(required = true)
    private String remarks;


    @ColumnLabel("TYPE_CAT")
    @XmlElement(nillable = true, required = true)
    private String typeCat;


    @ColumnLabel("TYPE_SCHEM")
    @XmlElement(nillable = true, required = true)
    private String typeSchem;


    @ColumnLabel("TYPE_NAME")
    @XmlElement(nillable = true, required = true)
    private String typeName;


    @ColumnLabel("SELF_REFERENCING_COL_NAME")
    @XmlElement(nillable = true, required = true)
    private String selfReferencingColName;


    @ColumnLabel("REF_GENERATION")
    @XmlElement(nillable = true, required = true)
    private String refGeneration;


    @XmlElement(required = true)
    @XmlJavaTypeAdapter(ColumnValuesAdapter.class)
    private Map<String, Column> columns;


    @XmlElement(required = true)
    @XmlElementWrapper(nillable = true, required = true)
    private Map<String, TablePrivilege> tablePrivileges;


    @XmlElement(required = true)
    @XmlJavaTypeAdapter(VersionColumnValuesAdapter.class)
    private Map<String, VersionColumn> versionColumns;


}
