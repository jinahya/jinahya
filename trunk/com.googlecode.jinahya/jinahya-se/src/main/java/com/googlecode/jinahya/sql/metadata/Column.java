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


import com.googlecode.jinahya.xml.bind.ValuesMapAdapter;
import com.googlecode.jinahya.xml.bind.ValuesMapAdapter.Values;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class Column {


    public static class Columns extends Values<Column> {


        @XmlElement
        public List<Column> getColumn() {

            return getValue();
        }


    }


    public static class ColumnsMapAdapter
        extends ValuesMapAdapter<Columns, String, Column> {


        public ColumnsMapAdapter() {

            super(Columns.class);
        }


        @Override
        protected String getKey(final Column value) {

            return value.getColumnName();
        }


    }


    public Table getTable() {

        return table;
    }


    public void setTable(final Table table) {

        this.table = table;
    }


    public String getColumnName() {

        return columnName;
    }


    @XmlTransient
    private Table table;


    @Label("TABLE_CAT")
    private String tableCat;


    @Label("TABLE_SCHEM")
    private String tableSchem;


    @Label("TABLE_NAME")
    private String tableName;


    @Label("COLUMN_NAME")
    private String columnName;


    @Label("DATA_TYPE")
    private int dataType;


    @Label("TYPE_NAME")
    private int typeName;


    @Label("COLUMN_SIZE")
    private int columnSize;


    @Unused
    @XmlTransient
    private Object bufferLength;


    @Label("DECIMAL_DIGITS")
    private int decimalDigits;


    @Label("NUM_PREC_RADIX")
    private int numPrecRadix;


    @Label("NULLABLE")
    private int nullable;


    @Label("REMARKS")
    private String remarks;


    @Label("COLUMN_DEF")
    private String columnDef;


    @Label("SQL_DATA_TYPE")
    @Unused
    private int sqlDataType;


    @Label("SQL_DATETIME_SUB")
    @Unused
    private int sqlDatetimeSub;


    @Label("CHAR_OCTET_LENGTH")
    private int charOctetLength;


    @Label("ORDINAL_POSITION")
    private int ordinalPosition;


    @Label("IS_NULLABLE")
    private String isNullable;


    @Label("SCOPE_CATALOG")
    private String scopeCatalog;


    @Label("SCOPE_SCHEMA")
    private String scopeSchema;


    @Label("SCOPE_TABLE")
    private String scopeTable;


    @Label("SOURCE_DATA_TYPE")
    private short sourceDataType;


    @Label("IS_AUTOINCREMENT")
    private String isAutoincrement;


    @Label("IS_GENERATEDCOLUMN")
    private String isGeneratedcolumn;


}
