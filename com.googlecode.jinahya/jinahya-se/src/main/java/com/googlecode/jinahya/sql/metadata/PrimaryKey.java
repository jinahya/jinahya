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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class PrimaryKey {


    public static PrimaryKey newInstance(final Table table,
                                         final ResultSet resultSet) {

        final PrimaryKey instance = new PrimaryKey();

        instance.table = table;

        return instance;
    }


    // --------------------------------------------------------------- TABLE_CAT
    // ------------------------------------------------------------- TABLE_SCHEM
    // -------------------------------------------------------------- TABLE_NAME
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


    // ----------------------------------------------------------------- KEY_SEQ
    public short getKeySeq() {
        return keySeq;
    }


    public void setKeySeq(short keySeq) {
        this.keySeq = keySeq;
    }


    // ----------------------------------------------------------------- PK_NAME
    public String getPkName() {
        return pkName;
    }


    public void setPkName(String pkName) {
        this.pkName = pkName;
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
    @XmlElement(required = true)
    private String columnName;


    @ColumnLabel("KEY_SEQ")
    @XmlElement(required = true)
    private short keySeq;


    @ColumnLabel("PK_NAME")
    @XmlElement(nillable = true, required = true)
    private String pkName;


}
