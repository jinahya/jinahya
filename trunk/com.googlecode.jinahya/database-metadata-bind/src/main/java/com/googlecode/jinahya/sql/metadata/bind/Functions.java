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


import java.lang.reflect.InvocationTargetException;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collection;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class Functions extends MetadataCollectable<Function> {


    /**
     * 
     * @param databaseMetaData
     * @param catalog
     * @param schemaPattern
     * @param functionNamePattern
     * @return
     * @throws SQLException 
     */
    public static Functions newInstance(final DatabaseMetaData databaseMetaData,
                                        final String catalog,
                                        final String schemaPattern,
                                        final String functionNamePattern)
        throws SQLException {

        final ResultSet functionResultSet = databaseMetaData.getFunctions(
            catalog, schemaPattern, functionNamePattern);
        try {
            final Functions functions = new Functions();
            while (functionResultSet.next()) {
                final Function function = MetadataAccessible.newInstance(
                    Function.class, functionResultSet);
                functions.getFunctions().add(function);
            }

            return functions;

        } finally {
            functionResultSet.close();
        }
    }


    /**
     * @param <O> output type parameter
     * @param databaseMetaData
     * @param catalog
     * @param schemaPattern
     * @param tableNamePattern
     * @param properties
     * @param outputType
     * @param output
     * @throws SQLException
     * @throws JAXBException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public static <O> void marshalInstance(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schemaPattern, final String tableNamePattern,
        final Map<String, Object> properties, final Class<O> outputType,
        final O output)
        throws SQLException, JAXBException, NoSuchMethodException,
               IllegalAccessException, InvocationTargetException {

        final Functions instance = newInstance(
            databaseMetaData, catalog, schemaPattern, tableNamePattern);

        marshal(instance, properties, outputType, output);
    }


    /**
     * 
     * @param <I>
     * @param properties
     * @param inputTyep
     * @param input
     * @return
     * @throws SQLException
     * @throws JAXBException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public static <I> Functions unmarshalInstance(
        final Map<String, Object> properties, final Class<I> inputTyep,
        final I input)
        throws SQLException, JAXBException, NoSuchMethodException,
               IllegalAccessException, InvocationTargetException {

        return unmarshal(Functions.class, properties, inputTyep, input);
    }


    @XmlElement(name = "function")
    public Collection<Function> getFunctions() {
        return super.getMetadata();
    }


}

