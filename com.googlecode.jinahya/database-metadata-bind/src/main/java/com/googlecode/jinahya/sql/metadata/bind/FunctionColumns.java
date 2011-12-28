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


/**
 * FunctionColumn wrapper.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
//@XmlRootElement
public class FunctionColumns extends EntrySets<FunctionColumn> {


    /**
     * 
     * @param databaseMetaData meta
     * @param catalog catalog
     * @param schemaPattern schemaPattern
     * @param functionNamePattern functionNamePattern
     * @param columnNamePattern columnNamePattern
     * @return
     * @throws SQLException if a database access error occurs.
     */
    public static FunctionColumns newInstance(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schemaPattern, final String functionNamePattern,
        final String columnNamePattern)
        throws SQLException {

        final FunctionColumns instance = new FunctionColumns();

        getFunctionColumns(databaseMetaData, catalog, schemaPattern,
                           functionNamePattern, columnNamePattern,
                           instance.getFunctionColumns());

        return instance;
    }


    /**
     * 
     * @param databaseMetaData
     * @param catalog
     * @param schemaPattern
     * @param functionNamePattern
     * @param columnNamePattern
     * @param functionColumns
     * @throws SQLException if a database access error occurs.
     * @see DatabaseMetaData#getFunctionColumns(String, String, String, String)
     */
    public static void getFunctionColumns(
        final DatabaseMetaData databaseMetaData, String catalog,
        final String schemaPattern, final String functionNamePattern,
        final String columnNamePattern,
        final Collection<FunctionColumn> functionColumns)
        throws SQLException {

        if (MethodNamesToOmit.instanceContainsName("getFunctionColumns")) {
            return;
        }

        final ResultSet resultSet = databaseMetaData.getFunctionColumns(
            catalog, schemaPattern, functionNamePattern, columnNamePattern);
        try {
            while (resultSet.next()) {
                final FunctionColumn functionColumn =
                    FunctionColumn.newInstance(resultSet);
                functionColumns.add(functionColumn);
            }
        } finally {
            resultSet.close();
        }
    }


    /**
     * 
     * @param databaseMetaData
     * @param catalog
     * @throws SQLException if a database access error occurs.
     */
    public static void getAllFunctionColumns(
        final DatabaseMetaData databaseMetaData, final Catalog catalog)
        throws SQLException {

        getFunctionColumns(databaseMetaData, catalog.getTABLE_CAT(), null, null,
                           null, catalog.getFunctionColumns());

        for (FunctionColumn functionColumn : catalog.getFunctionColumns()) {
            functionColumn.setParent(catalog);
        }
    }


    /**
     * Creates a new instance.
     */
    public FunctionColumns() {
        super(FunctionColumn.class);
    }


    @XmlElement(name = "functionColumn")
    public Collection<FunctionColumn> getFunctionColumns() {
        return super.getEntrySets();
    }


}

