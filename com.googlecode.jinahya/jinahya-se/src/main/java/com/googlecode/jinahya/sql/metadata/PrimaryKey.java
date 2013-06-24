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
public class PrimaryKey {


    public static class PrimaryKeys extends Values<PrimaryKey> {


        @XmlElement
        public List<PrimaryKey> getColumn() {

            return getValue();
        }


    }


    public static class PrimaryKeysMapAdapter
        extends ValuesMapAdapter<PrimaryKeys, String, PrimaryKey> {


        public PrimaryKeysMapAdapter() {

            super(PrimaryKeys.class);
        }


        @Override
        protected String getKey(final PrimaryKey value) {

            return value.getColumnName();
        }


    }


    public String getColumnName() {

        return columnName;
    }


    public Table getTable() {

        return table;
    }


    public void setTable(final Table table) {

        this.table = table;
    }


    @Label("TABLE_CAT")
    private String tableCat;


    @Label("TABLE_SCHEM")
    private String tableSchem;


    @Label("TABLE_NAME")
    private String tableName;


    @Label("COLUMN_NAME")
    private String columnName;


    @Label("KEY_SEQ")
    private short keySeq;


    @Label("PK_NAME")
    private String pkName;


    @XmlTransient
    private transient Table table;


}
