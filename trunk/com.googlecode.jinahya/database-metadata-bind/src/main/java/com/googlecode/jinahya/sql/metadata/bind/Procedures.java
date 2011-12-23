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


import com.googlecode.jinahya.sql.metadata.MethodNamesToOmit;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Procedures.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class Procedures extends EntrySetWrapper<Procedure> {


    /**
     * Retrieves procedures.
     *
     * @param databaseMetaData database metadata
     * @param catalog catalog
     * @param schemaPattern schemaPattern
     * @param procedureNamePattern procedureNamePattern
     * @return an instance of Procedures
     * @throws SQLException if a database access error occurs.
     * @see #getProcedures(java.sql.DatabaseMetaData, java.lang.String,
     * java.lang.String, java.lang.String, java.util.Collection)
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


    /**
     * Retrieves procedures.
     *
     * @param databaseMetaData database metadata
     * @param catalog catalog
     * @param schemaPattern schemaPattern
     * @param procedureNamePattern procedureNamePattern
     * @param procedures procedures
     * @throws SQLException if a database access error occurs.
     * @see java.sql.DatabaseMetaData#getProcedures(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    public static void getProcedures(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schemaPattern, final String procedureNamePattern,
        final Collection<Procedure> procedures)
        throws SQLException {

        if (MethodNamesToOmit.instanceContainsName("getProcedures")) {
            return;
        }

        final ResultSet resultSet = databaseMetaData.getProcedures(
            catalog, schemaPattern, procedureNamePattern);
        try {
            while (resultSet.next()) {
                final Procedure procedure = EntrySet.newInstance(
                    Procedure.class, resultSet);
                procedures.add(procedure);
            }
        } finally {
            resultSet.close();
        }
    }


    /**
     * Retrieves procedures.
     *
     * @param databaseMetaData database metadata
     * @param catalog catalog
     * @throws SQLException if a database access error occurs.
     */
    public static void getAllProcedures(
        final DatabaseMetaData databaseMetaData, final Catalog catalog)
        throws SQLException {

        getProcedures(databaseMetaData, catalog.getTABLE_CAT(), null, null,
                      catalog.getProcedures());

        for (Procedure procedure : catalog.getProcedures()) {
            procedure.setCatalog(catalog);
        }
    }


    /**
     * Creates a new instance.
     */
    public Procedures() {
        super(Procedure.class);
    }


    /**
     * Returns procedures.
     *
     * @return procedures.
     */
    @XmlElement(name = "procedure")
    public Collection<Procedure> getProcedures() {
        return super.getEntrySets();
    }


}

