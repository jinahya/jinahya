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
 * Attributes collection.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class Attributes extends EntrySetWrapper<Attribute> {


    /**
     * 
     * @param databaseMetaData
     * @param catalog
     * @param schemaPattern
     * @param typeNamePattern
     * @param attributeNamePattern
     * @return
     * @throws SQLException 
     * 
     * @see DatabaseMetaData#getAttributes(String, String, String, String) 
     */
    public static Attributes newInstance(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schemaPattern, final String typeNamePattern,
        final String attributeNamePattern)
        throws SQLException {

        final Attributes attributes = new Attributes();
        getAttributes(databaseMetaData, catalog, schemaPattern, typeNamePattern,
                      attributeNamePattern, attributes.getAttributes());

        return attributes;
    }


    /**
     * 
     * @param databaseMetaData
     * @param catalog
     * @param schemaPattern
     * @param typeNamePattern
     * @param attributeNamePattern
     * @param attributes
     * @throws SQLException 
     * 
     * @see DatabaseMetaData#getAttributes(String, String, String, String) 
     */
    public static void getAttributes(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schemaPattern, final String typeNamePattern,
        final String attributeNamePattern,
        final Collection<Attribute> attributes)
        throws SQLException {

        final ResultSet attributeResultSet = databaseMetaData.getAttributes(
            catalog, schemaPattern, typeNamePattern, attributeNamePattern);
        try {
            while (attributeResultSet.next()) {
                final Attribute attribute = EntrySet.newInstance(
                    Attribute.class, attributeResultSet);
                attributes.add(attribute);
            }
        } finally {
            attributeResultSet.close();
        }
    }


    /**
     * 
     * @param databaseMetaData
     * @param schema
     * @param typeNamePattern
     * @param attributeNamePattern
     * @throws SQLException 
     *
     * @see DatabaseMetaData#getAttributes(String, String, String, String) 
     */
    public static void getAttributes(
        final DatabaseMetaData databaseMetaData, final Schema schema,
        final String typeNamePattern, final String attributeNamePattern)
        throws SQLException {

        getAttributes(databaseMetaData, schema.getTABLE_CATALOG(),
                      schema.getTABLE_SCHEM(), typeNamePattern,
                      attributeNamePattern, schema.getAttributes());
    }


    /**
     * 
     * @param databaseMetaData database meta data
     * @param schema schema
     * @throws SQLException if an SQL error occurs
     */
    public static void getAllAttributes(
        final DatabaseMetaData databaseMetaData, final Schema schema)
        throws SQLException {

        getAttributes(databaseMetaData, schema, null, null);
    }


    /**
     * Creates a new instance.
     */
    public Attributes() {
        super(Attribute.class);
    }


    /**
     * Returns attributes.
     *
     * @return attributes
     */
    @XmlElement(name = "attribute")
    public Collection<Attribute> getAttributes() {
        return super.getEntrySets();
    }


}

