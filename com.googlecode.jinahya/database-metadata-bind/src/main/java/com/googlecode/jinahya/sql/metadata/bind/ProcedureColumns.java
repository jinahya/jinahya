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


import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Column wrapper.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class ProcedureColumns extends EntrySetWrapper<ProcedureColumn> {


    /**
     * 
     * @param databaseMetaData
     * @param catalog
     * @param schemaPattern
     * @param tableNamePattern
     * @param columnNamePattern
     * @return
     * @throws SQLException 
     */
    public static ProcedureColumns newInstance(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schemaPattern, final String tableNamePattern,
        final String columnNamePattern)
        throws SQLException {

        final ProcedureColumns procedureColumns = new ProcedureColumns();
        getProcedureColumns(databaseMetaData, catalog, schemaPattern,
                            tableNamePattern, columnNamePattern,
                            procedureColumns.getProcedureColumns());

        return procedureColumns;
    }


    /**
     * 
     * @param databaseMetaData
     * @param catalog
     * @param schemaPattern
     * @param procedureNamePattern
     * @param columnNamePattern
     * @param procedureColumns
     * @throws SQLException 
     */
    public static void getProcedureColumns(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schemaPattern, final String procedureNamePattern,
        final String columnNamePattern,
        final Collection<ProcedureColumn> procedureColumns)
        throws SQLException {

        final ResultSet procedureColumnResultSet =
            databaseMetaData.getProcedureColumns(
            catalog, schemaPattern, procedureNamePattern, columnNamePattern);
        try {
            while (procedureColumnResultSet.next()) {
                final ProcedureColumn procedureColumn = EntrySet.newInstance(
                    ProcedureColumn.class, procedureColumnResultSet);
                procedureColumns.add(procedureColumn);
            }
        } finally {
            procedureColumnResultSet.close();
        }
    }


    /**
     * 
     * @param databaseMetaData
     * @param catalog
     * @throws SQLException 
     */
    public static void getAllProcedureColumns(
        final DatabaseMetaData databaseMetaData, final Catalog catalog)
        throws SQLException {

        getProcedureColumns(
            databaseMetaData, catalog.getTABLE_CAT(), null, null, null,
            catalog.getProcedureColumns());

        for (ProcedureColumn procedureColumn : catalog.getProcedureColumns()) {
            procedureColumn.setParent(catalog);
        }
    }


    /**
     * Creates a new instance.
     */
    public ProcedureColumns() {
        super(ProcedureColumn.class);
    }


    @XmlElement(name = "procedureColumn")
    public Collection<ProcedureColumn> getProcedureColumns() {
        return super.getEntrySets();
    }


}

