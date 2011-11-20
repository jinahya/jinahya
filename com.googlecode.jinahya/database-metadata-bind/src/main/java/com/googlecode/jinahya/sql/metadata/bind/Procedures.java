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
public class Procedures extends EntrySetWrapper<Procedure> {


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
    public static Procedures newInstance(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schemaPattern, final String procedureNamePattern)
        throws SQLException {

        final Procedures instnace = new Procedures();
        getProcedures(databaseMetaData, catalog, schemaPattern,
                      procedureNamePattern, instnace.getProcedures());

        return instnace;
    }


    public static void getProcedures(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schemaPattern, final String procedureNamePattern,
        final Collection<Procedure> procedures)
        throws SQLException {

        final ResultSet procedureResultSet = databaseMetaData.getProcedures(
            catalog, schemaPattern, procedureNamePattern);
        try {
            while (procedureResultSet.next()) {
                final Procedure procedure = EntrySet.newInstance(
                    Procedure.class, procedureResultSet);
                procedures.add(procedure);
            }
        } finally {
            procedureResultSet.close();
        }
    }


    /**
     * 
     * @param databaseMetaData
     * @param catalog
     * @throws SQLException 
     */
    public static void getAllProcedures(
        final DatabaseMetaData databaseMetaData, final Catalog catalog)
        throws SQLException {

        getProcedures(databaseMetaData, catalog.getTABLE_CAT(), null, null,
                      catalog.getProcedures());

        for (Procedure procedure : catalog.getProcedures()) {
            procedure.setParent(catalog);
        }
    }


    /**
     * Creates a new instance.
     */
    public Procedures() {
        super(Procedure.class);
    }


    @XmlElement(name = "procedure")
    public Collection<Procedure> getProcedures() {
        return super.getEntrySets();
    }


}

