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
 * FunctionColumn wrapper.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class FunctionColumns extends EntrySetWrapper<FunctionColumn> {


    public static FunctionColumns newInstance(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schemaPattern, final String functionNamePattern,
        final String columnNamePattern)
        throws SQLException {

        final FunctionColumns functionColumns = new FunctionColumns();
        getFunctionColumns(databaseMetaData, catalog, schemaPattern,
                           functionNamePattern, columnNamePattern,
                           functionColumns.getFunctionColumns());

        return functionColumns;
    }


    public static void getFunctionColumns(
        final DatabaseMetaData databaseMetaData, String catalog,
        final String schemaPattern, final String functionNamePattern,
        final String columnNamePattern,
        final Collection<FunctionColumn> functionColumns)
        throws SQLException {

        try {
            final ResultSet functionColumnResultSet =
                databaseMetaData.getFunctionColumns(
                catalog, schemaPattern, functionNamePattern, columnNamePattern);
            try {
                while (functionColumnResultSet.next()) {
                    final FunctionColumn functionColumn = EntrySet.newInstance(
                        FunctionColumn.class, functionColumnResultSet);
                    functionColumns.add(functionColumn);
                }
            } finally {
                functionColumnResultSet.close();
            }
        } catch (AbstractMethodError ame) {
        }
    }


    public static void getFunctionColumns(
        final DatabaseMetaData databaseMetaData, final Catalog catalog,
        final String schemaNamePattern, final String functionNamePattern,
        final String columnNamePattern)
        throws SQLException {

        getFunctionColumns(databaseMetaData, catalog.getTABLE_CAT(),
                           schemaNamePattern, functionNamePattern,
                           columnNamePattern, catalog.getFunctionColumns());
    }


    public static void getAllFunctionColumns(
        final DatabaseMetaData databaseMetaData, final Catalog catalog)
        throws SQLException {

        getFunctionColumns(databaseMetaData, catalog, null, null, null);
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
