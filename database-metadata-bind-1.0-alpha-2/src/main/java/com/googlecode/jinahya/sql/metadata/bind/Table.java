/*
 * Copyright 2011 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.sql.metadata.bind;


import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
@XmlType(propOrder = {"entries", "columns", "exportedKeys", "identifiers",
                      "importedKeys", "indices", "primaryKeys", "privileges",
                      "versionColumns"})
public class Table extends EntrySet {


    public String getTABLE_CAT() {
        return getValue("TABLE_CAT");
    }


    public void setTABLE_CAT(final String TABLE_CAT) {
        setValue("TABLE_CAT", TABLE_CAT);
    }


    public String getTABLE_SCHEM() {
        return getValue("TABLE_SCHEM");
    }


    public void setTABLE_SCHEM(final String TABLE_SCHEM) {
        setValue("TABLE_SCHEM", TABLE_SCHEM);
    }


    public String getTABLE_NAME() {
        return getValue("TABLE_NAME");
    }


    public void setTABLE_NAME(final String TABLE_NAME) {
        setValue("TABLE_NAME", TABLE_NAME);
    }


    public String getTABLE_TYPE() {
        return getValue("TABLE_TYPE");
    }


    public void setTABLE_TYPE(final String TABLE_TYPE) {
        setValue("TABLE_TYPE", TABLE_TYPE);
    }


    public String getREMARKS() {
        return getValue("REMARKS");
    }


    public void setREMARKS(final String REMARKS) {
        setValue("REMARKS", REMARKS);
    }


    public String getTYPE_CAT() {
        return getValue("TYPE_CAT");
    }


    public void setTYPE_CAT(final String TYPE_CAT) {
        setValue("TYPE_CAT", TYPE_CAT);
    }


    public String getTYPE_SCHEM() {
        return getValue("TYPE_SCHEM");
    }


    public void setTYPE_SCHEM(final String TYPE_SCHEM) {
        setValue("TYPE_SCHEM", TYPE_SCHEM);
    }


    public String getTYPE_NAME() {
        return getValue("TYPE_NAME");
    }


    public void setTYPE_NAME(final String TYPE_NAME) {
        setValue("TYPE_NAME", TYPE_NAME);
    }


    public String getSELF_REFERENCING_COL_NAME() {
        return getValue("SELF_REFERENCING_COL_NAME");
    }


    public void setSELF_REFERENCING_COL_NAME(
        final String SELF_REFERENCING_COL_NAME) {

        setValue("SELF_REFERENCING_COL_NAME", SELF_REFERENCING_COL_NAME);
    }


    public String getREF_GENERATION() {
        return getValue("REF_GENERATION");
    }


    public void setREF_GENERATION(final String REF_GENERATION) {
        setValue("REF_GENERATION", REF_GENERATION);
    }


    /**
     * Returns columns.
     *
     * @return columns
     */
    public Collection<Column> getColumns() {

        if (columns == null) {
            columns = new ArrayList<Column>();
        }

        return columns;
    }


    /**
     * Returns exportedKeys.
     *
     * @return exportedKeys.
     */
    public Collection<ExportedKey> getExportedKeys() {

        if (exportedKeys == null) {
            exportedKeys = new ArrayList<ExportedKey>();
        }

        return exportedKeys;
    }


    /**
     * Returns indices.
     *
     * @return indices
     */
    public Collection<Index> getIndices() {

        if (indices == null) {
            indices = new ArrayList<Index>();
        }

        return indices;
    }


    /**
     * Returns identifiers.
     *
     * @return identifiers
     */
    public Collection<Identifier> getIdentifiers() {

        if (identifiers == null) {
            identifiers = new ArrayList<Identifier>();
        }

        return identifiers;
    }


    /**
     * Returns privileges.
     *
     * @return privileges
     */
    public Collection<TablePrivilege> getPrivileges() {

        if (privileges == null) {
            privileges = new ArrayList<TablePrivilege>();
        }

        return privileges;
    }


    public Collection<ImportedKey> getImportedKeys() {

        if (importedKeys == null) {
            importedKeys = new ArrayList<ImportedKey>();
        }

        return importedKeys;
    }


    public Collection<PrimaryKey> getPrimaryKeys() {

        if (primaryKeys == null) {
            primaryKeys = new ArrayList<PrimaryKey>();
        }

        return primaryKeys;
    }


    public Collection<VersionColumn> getVersionColumns() {

        if (versionColumns == null) {
            versionColumns = new ArrayList<VersionColumn>();
        }

        return versionColumns;
    }


    @XmlElement(name = "column")
    @XmlElementWrapper(required = true, nillable = true)
    private Collection<Column> columns;


    @XmlElement(name = "exportedKey")
    @XmlElementWrapper(required = true, nillable = true)
    private Collection<ExportedKey> exportedKeys;


    @XmlElement(name = "identifier")
    @XmlElementWrapper(required = true, nillable = true)
    private Collection<Identifier> identifiers;


    @XmlElement(name = "importedKey")
    @XmlElementWrapper(required = true, nillable = true)
    private Collection<ImportedKey> importedKeys;


    @XmlElement(name = "index")
    @XmlElementWrapper(required = true, nillable = true)
    private Collection<Index> indices;


    @XmlElement(name = "primaryKey")
    @XmlElementWrapper(required = true, nillable = true)
    private Collection<PrimaryKey> primaryKeys;


    @XmlElement(name = "privilege")
    @XmlElementWrapper(required = true, nillable = true)
    private Collection<TablePrivilege> privileges;


    @XmlElement(name = "versionColumn")
    @XmlElementWrapper(required = true, nillable = true)
    private Collection<VersionColumn> versionColumns;


}

