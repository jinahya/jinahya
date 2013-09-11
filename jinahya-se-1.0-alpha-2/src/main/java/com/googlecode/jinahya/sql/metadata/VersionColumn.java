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


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class VersionColumn {


    // ------------------------------------------------------------------- SCOPE
    public short getScope() {
        return scope;
    }


    public void setScope(short scope) {
        this.scope = scope;
    }


    // ------------------------------------------------------------- COLUMN_NAME
    public String getColumnName() {

        return columnName;
    }


    public void setColumnName(final String columnName) {

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
    public String getTypeName() {
        return typeName;
    }


    public void setTypeName(String typeName) {
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
    public int getBufferLength() {
        return bufferLength;
    }


    public void setBufferLength(int bufferLength) {
        this.bufferLength = bufferLength;
    }


    // ---------------------------------------------------------- DECIMAL_DIGITS
    public Short getDecimalDigits() {

        return decimalDigits;
    }


    public void setDecimalDigits(final Short decimalDigits) {

        this.decimalDigits = decimalDigits;
    }


    // ----------------------------------------------------------- PSEUDO_COLUMN
    public short getPseudoColumn() {
        return pseudoColumn;
    }


    public void setPseudoColumn(short pseudoColumn) {
        this.pseudoColumn = pseudoColumn;
    }


    @XmlTransient
    private Table table;


    @ColumnLabel("SCOPE")
    @XmlTransient
    private short scope;


    @ColumnLabel("COLUMN_NAME")
    @XmlElement(required = true)
    private String columnName;


    @ColumnLabel("DATA_TYPE")
    @XmlElement(required = true)
    private int dataType;


    @ColumnLabel("TYPE_NAME")
    @XmlElement(required = true)
    private String typeName;


    @ColumnLabel("COLUMN_SIZE")
    @XmlElement(required = true)
    private int columnSize;


    @ColumnLabel("BUFFER_LENGTH")
    @XmlElement(required = true)
    private int bufferLength;


    @ColumnLabel("DECIMAL_DIGITS")
    @XmlElement(nillable = true, required = true)
    private Short decimalDigits;


    @ColumnLabel("PSEUDO_COLUMN")
    @XmlElement(required = true)
    private short pseudoColumn;


}
