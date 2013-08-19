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
public class ExportedKey {


    public static class ImportedKeys extends AbstractValues<ExportedKey> {


        @XmlElement
        public List<ExportedKey> getColumn() {

            return getValueList();
        }


    }


    public static class ImportedKeysMapAdapter
        extends ValuesMapAdapter<ImportedKeys, String, ExportedKey> {


        public ImportedKeysMapAdapter() {

            super(ImportedKeys.class);
        }


        @Override
        protected String getKey(final ExportedKey value) {

            return value.getColumnName();
        }


    }


    public String getColumnName() {

        return pkcolumnName;
    }


    public Table getTable() {

        return table;
    }


    public void setTable(final Table table) {

        this.table = table;
    }


    @ColumnLabel("PKTABLE_CAT")
    private String pktableCat;


    @ColumnLabel("PKTABLE_SCHEM")
    private String pktableSchem;


    @ColumnLabel("PKTABLE_NAME")
    private String pktableName;


    @ColumnLabel("PKCOLUMN_NAME")
    private String pkcolumnName;


    @ColumnLabel("FKTABLE_CAT")
    private String fktableCat;


    @ColumnLabel("FKTABLE_SCHEM")
    private String fktableSchem;


    @ColumnLabel("FKTABLE_NAME")
    private String fktableName;


    @ColumnLabel("FKCOLUMN_NAME")
    private String fkcolumnName;


    @ColumnLabel("KEY_SEQ")
    private short keySeq;


    @ColumnLabel("UPDATE_RULE")
    private short updateRule;


    @ColumnLabel("DELETE_RULE")
    private short deleteRule;


    @ColumnLabel("FK_NAME")
    private String fkName;


    @ColumnLabel("PK_NAME")
    private String pkName;


    @ColumnLabel("DEFERRABILITY")
    private short deferrability;


    @XmlTransient
    private transient Table table;


}
