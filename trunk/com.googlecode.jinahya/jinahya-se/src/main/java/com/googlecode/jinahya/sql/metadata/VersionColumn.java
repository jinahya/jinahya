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
import com.googlecode.jinahya.xml.bind.ValuesMapAdapter.AbstractValues;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class VersionColumn {


    public static class VersionColumns extends AbstractValues<VersionColumn> {


        @XmlElement
        public List<VersionColumn> getColumn() {

            return getValueList();
        }


    }


    public static class VersionColumnsMapAdapter
        extends ValuesMapAdapter<VersionColumns, String, VersionColumn> {


        public VersionColumnsMapAdapter() {

            super(VersionColumns.class);
        }


        @Override
        protected String getKey(final VersionColumn value) {

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


    @Label("SCOPE")
    private short scope;


    @Label("COLUMN_NAME")
    private String columnName;


    @Label("DATA_TYPE")
    private int dataType;


    @Label("TYPE_NAME")
    private String typeName;


    @Label("COLUMN_SIZE")
    private int columnSize;


    @Label("BUFFER_LENGTH")
    private int bufferLength;


    @Label("DECIMAL_DIGITS")
    private short decimalDigits;


    @Label("PSEUDO_COLUMN")
    private short pseudoColumn;


}
