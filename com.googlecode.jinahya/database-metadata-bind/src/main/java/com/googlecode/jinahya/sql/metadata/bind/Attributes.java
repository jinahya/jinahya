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
 * Attributes.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class Attributes extends EntrySets<Attribute> {


    /**
     * Creates a new instance.
     *
     * @param databaseMetaData meta
     * @param catalog catalog
     * @param schemaPattern schemaPattern
     * @param typeNamePattern typeNamePattern
     * @param attributeNamePattern attributeNamePattern
     * @return a new instance.
     * @throws SQLException if a database access error occurs.
     * @see DatabaseMetaData#getAttributes(String, String, String, String)
     */
    public static Attributes newInstance(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schemaPattern, final String typeNamePattern,
        final String attributeNamePattern)
        throws SQLException {

        final Attributes instance = new Attributes();

        getAttributes(databaseMetaData, catalog, schemaPattern, typeNamePattern,
                      attributeNamePattern, instance.getAttributes());

        return instance;
    }


    /**
     * Retrieves attributes.
     *
     * @param databaseMetaData database metadata
     * @param catalog catalog
     * @param schemaPattern schemaPattern
     * @param typeNamePattern typeNamePattern
     * @param attributeNamePattern attributeNamePattern
     * @param attributes attributes
     * @throws SQLException if a database access error occurs
     * @see DatabaseMetaData#getAttributes(String, String, String, String)
     */
    public static void getAttributes(
        final DatabaseMetaData databaseMetaData, final String catalog,
        final String schemaPattern, final String typeNamePattern,
        final String attributeNamePattern,
        final Collection<Attribute> attributes)
        throws SQLException {

        if (MethodNamesToOmit.instanceContainsName("getAttributes")) {
            return;
        }

        final ResultSet resultSet = databaseMetaData.getAttributes(
            catalog, schemaPattern, typeNamePattern, attributeNamePattern);
        try {
            while (resultSet.next()) {
                final Attribute attribute = EntrySet.newInstance(
                    Attribute.class, resultSet);
                attributes.add(attribute);
            }
        } finally {
            resultSet.close();
        }
    }


    /**
     * Retrieves attributes.
     *
     * @param databaseMetaData database meta data
     * @param UDT UDT
     * @throws SQLException if a database access error occurs.
     */
    public static void getAllAttributes(
        final DatabaseMetaData databaseMetaData, final UserDataType UDT)
        throws SQLException {

        getAttributes(databaseMetaData, UDT.getTYPE_CAT(), UDT.getTYPE_SCHEM(),
                      UDT.getTYPE_NAME(), null, UDT.getAttributes());

        for (Attribute attribute : UDT.getAttributes()) {
            attribute.setParent(UDT);
        }
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

