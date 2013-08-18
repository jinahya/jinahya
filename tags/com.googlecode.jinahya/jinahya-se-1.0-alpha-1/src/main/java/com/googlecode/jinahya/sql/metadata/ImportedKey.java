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
public class ImportedKey {


    public static class ImportedKeys extends AbstractValues<ImportedKey> {


        @XmlElement
        public List<ImportedKey> getColumn() {

            return getValueList();
        }


    }


    public static class ImportedKeysMapAdapter
        extends ValuesMapAdapter<ImportedKeys, String, ImportedKey> {


        public ImportedKeysMapAdapter() {

            super(ImportedKeys.class);
        }


        @Override
        protected String getKey(final ImportedKey value) {

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


    @Label("PKTABLE_CAT")
    private String pktableCat;


    @Label("PKTABLE_SCHEM")
    private String pktableSchem;


    @Label("PKTABLE_NAME")
    private String pktableName;


    @Label("PKCOLUMN_NAME")
    private String pkcolumnName;


    @Label("FKTABLE_CAT")
    private String fktableCat;


    @Label("FKTABLE_SCHEM")
    private String fktableSchem;


    @Label("FKTABLE_NAME")
    private String fktableName;


    @Label("FKCOLUMN_NAME")
    private String fkcolumnName;


    @Label("KEY_SEQ")
    private short keySeq;


    @Label("UPDATE_RULE")
    private short updateRule;


    @Label("DELETE_RULE")
    private short deleteRule;


    @Label("FK_NAME")
    private String fkName;


    @Label("PK_NAME")
    private String pkName;


    @Label("DEFERRABILITY")
    private short deferrability;


    @XmlTransient
    private transient Table table;


}
