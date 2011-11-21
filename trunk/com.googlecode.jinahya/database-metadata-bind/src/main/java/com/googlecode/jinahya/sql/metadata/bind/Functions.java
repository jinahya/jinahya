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
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
//@XmlRootElement
public class Functions extends EntrySetWrapper<Function> {


    /**
     * Creates a new instance from given <code>databaseMetaData</code>.
     *
     * @param databaseMetaData database meta data
     * @param catalog catalog
     * @param schemaPattern schema pattern
     * @param functionNamePattern function name pattern
     * @return a new instance
     * @throws SQLException if an SQL error occurs.
     *
     * @see DatabaseMetaData#getFunctions(String, String, String)
     */
    public static Functions newInstance(final DatabaseMetaData databaseMetaData,
                                        final String catalog,
                                        final String schemaPattern,
                                        final String functionNamePattern)
        throws SQLException {

        final Functions functions = new Functions();
        getFunctions(databaseMetaData, catalog, schemaPattern,
                     functionNamePattern, functions.getFunctions());

        return functions;
    }


    /**
     * Gets functions from given <code>databaseMetaData</code> and adds to
     * specified <code>functions</code>.
     *
     * @param databaseMetaData database meta data
     * @param catalog catalog
     * @param schemaPattern schema patterns
     * @param functionNamePattern function name pattern
     * @param functions collection to be filled
     * @throws SQLException if an SQL error occurs.
     *
     * @see DatabaseMetaData#getFunctions(String, String, String)
     */
    public static void getFunctions(final DatabaseMetaData databaseMetaData,
                                    final String catalog,
                                    final String schemaPattern,
                                    final String functionNamePattern,
                                    final Collection<Function> functions)
        throws SQLException {

        if (MethodNamesToOmit.instanceContainsName("getFunctions")) {
            return;
        }

        final ResultSet resultSet = databaseMetaData.getFunctions(
            catalog, schemaPattern, functionNamePattern);
        try {
            while (resultSet.next()) {
                final Function function = EntrySet.newInstance(
                    Function.class, resultSet);
                functions.add(function);
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
    public static void getAllFunctions(final DatabaseMetaData databaseMetaData,
                                       final Catalog catalog)
        throws SQLException {

        getFunctions(databaseMetaData, catalog.getTABLE_CAT(), null, null,
                     catalog.getFunctions());

        for (Function function : catalog.getFunctions()) {
            function.setParent(catalog);
        }
    }


    /**
     * Creates a new instance.
     */
    public Functions() {
        super(Function.class);
    }


    @XmlElement(name = "function")
    public Collection<Function> getFunctions() {
        return super.getEntrySets();
    }


}

