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


import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class Column {


    // ------------------------------------------------------------------- table
    public Table getTable() {
        return table;
    }


    // ------------------------------------------------------------- COLUMN_NAME
    public String getColumnName() {
        return columnName;
    }


    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }


    // --------------------------------------------------------------- DATA_TYPE
    public int getDataType() {
        return dataType;
    }


    public void setDataType(int dataType) {
        this.dataType = dataType;
    }


    // --------------------------------------------------------------- TYPE_NAME
    public int getTypeName() {
        return typeName;
    }


    public void setTypeName(int typeName) {
        this.typeName = typeName;
    }


    // ------------------------------------------------------------- COLUMN_SIZE
    public int getColumnSize() {
        return columnSize;
    }


    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }


    // ----------------------------------------------------------- BUFFER_LENGTH
    public Object getBufferLength() {
        return bufferLength;
    }


    public void setBufferLength(Object bufferLength) {
        this.bufferLength = bufferLength;
    }


    // ---------------------------------------------------------- DECIMAL_GIGITS
    public int getDecimalDigits() {
        return decimalDigits;
    }


    public void setDecimalDigits(final int decimalDigits) {
        this.decimalDigits = decimalDigits;
    }


    // ---------------------------------------------------------- NUM_PREC_RADIX
    public int getNumPrecRadix() {
        return numPrecRadix;
    }


    public void setNumPrecRadix(final int numPrecRadix) {
        this.numPrecRadix = numPrecRadix;
    }


    // ---------------------------------------------------------------- NULLABLE
    public int getNullable() {
        return nullable;
    }


    public void setNullable(int nullable) {
        this.nullable = nullable;
    }


    // ----------------------------------------------------------------- REMARKS
    public String getRemarks() {
        return remarks;
    }


    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    // -------------------------------------------------------------- COLUMN_DEF
    public String getColumnDef() {
        return columnDef;
    }


    public void setColumnDef(String columnDef) {
        this.columnDef = columnDef;
    }


    // ----------------------------------------------------------- SQL_DATA_TYPE
    public int getSqlDataType() {
        return sqlDataType;
    }


    public void setSqlDataType(final int sqlDataType) {
        this.sqlDataType = sqlDataType;
    }


    // -------------------------------------------------------- SQL_DATETIME_SUB
    public int getSqlDatetimeSub() {
        return sqlDatetimeSub;
    }


    public void setSqlDatetimeSub(final int sqlDatetimeSub) {
        this.sqlDatetimeSub = sqlDatetimeSub;
    }


    // ------------------------------------------------------- CHAR_OCTET_LENGTH
    public int getCharOctetLength() {
        return charOctetLength;
    }


    public void setCharOctetLength(final int charOctetLength) {
        this.charOctetLength = charOctetLength;
    }


    // -------------------------------------------------------- ORDINAL_POSITION
    public int getOrdinalPosition() {
        return ordinalPosition;
    }


    public void setOrdinalPosition(int ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }


    // ------------------------------------------------------------- IS_NULLABLE
    public String getIsNullable() {
        return isNullable;
    }


    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }


    // ----------------------------------------------------------- SCOPE_CATALOG
    public String getScopeCatalog() {
        return scopeCatalog;
    }


    public void setScopeCatalog(String scopeCatalog) {
        this.scopeCatalog = scopeCatalog;
    }


    // ------------------------------------------------------------ SCOPE_SCHEMA
    public String getScopeSchema() {
        return scopeSchema;
    }


    public void setScopeSchema(String scopeSchema) {
        this.scopeSchema = scopeSchema;
    }


    // ------------------------------------------------------------- SCOPE_TABLE
    public String getScopeTable() {
        return scopeTable;
    }


    public void setScopeTable(String scopeTable) {
        this.scopeTable = scopeTable;
    }


    // -------------------------------------------------------- SOURCE_DATA_TYPE
    public short getSourceDataType() {
        return sourceDataType;
    }


    public void setSourceDataType(short sourceDataType) {
        this.sourceDataType = sourceDataType;
    }


    // -------------------------------------------------------- IS_AUTOINCREMENT
    public String getIsAutoincrement() {
        return isAutoincrement;
    }


    public void setIsAutoincrement(String isAutoincrement) {
        this.isAutoincrement = isAutoincrement;
    }


    // ------------------------------------------------------ IS_GENERATEDCOLUMN
    public String getIsGeneratedcolumn() {
        return isGeneratedcolumn;
    }


    public void setIsGeneratedcolumn(String isGeneratedcolumn) {
        this.isGeneratedcolumn = isGeneratedcolumn;
    }


    @ColumnLabel("TABLE_CAT")
    @XmlTransient
    private String tableCat;


    @ColumnLabel("TABLE_SCHEM")
    @XmlTransient
    private String tableSchem;


    @ColumnLabel("TABLE_NAME")
    @XmlTransient
    private String tableName;


    @XmlTransient
    private Table table;


    @ColumnLabel("COLUMN_NAME")
    private String columnName;


    @ColumnLabel("DATA_TYPE")
    private int dataType;


    @ColumnLabel("TYPE_NAME")
    private int typeName;


    @ColumnLabel("COLUMN_SIZE")
    private int columnSize;


    @Unused
    @XmlTransient
    private Object bufferLength;


    @ColumnLabel("DECIMAL_DIGITS")
    private int decimalDigits;


    @ColumnLabel("NUM_PREC_RADIX")
    private int numPrecRadix;


    @ColumnLabel("NULLABLE")
    private int nullable;


    @ColumnLabel("REMARKS")
    private String remarks;


    @ColumnLabel("COLUMN_DEF")
    private String columnDef;


    @ColumnLabel("SQL_DATA_TYPE")
    @Unused
    private int sqlDataType;


    @ColumnLabel("SQL_DATETIME_SUB")
    @Unused
    private int sqlDatetimeSub;


    @ColumnLabel("CHAR_OCTET_LENGTH")
    private int charOctetLength;


    @ColumnLabel("ORDINAL_POSITION")
    private int ordinalPosition;


    @ColumnLabel("IS_NULLABLE")
    private String isNullable;


    @ColumnLabel("SCOPE_CATALOG")
    private String scopeCatalog;


    @ColumnLabel("SCOPE_SCHEMA")
    private String scopeSchema;


    @ColumnLabel("SCOPE_TABLE")
    private String scopeTable;


    @ColumnLabel("SOURCE_DATA_TYPE")
    private short sourceDataType;


    @ColumnLabel("IS_AUTOINCREMENT")
    private String isAutoincrement;


    @ColumnLabel("IS_GENERATEDCOLUMN")
    private String isGeneratedcolumn;


}
