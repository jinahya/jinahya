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


package com.googlecode.jinahya.sql.metadata;


import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;


/**
 * Catalog binding.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlType(propOrder = {"pairs", "schemas"})
public class Catalog extends PairSet {


    /**
     * Creates a new null instance.
     *
     * @return a new null instance.
     */
    private static Catalog newNullInstance() {
        final Catalog catalog = new Catalog();
        catalog.setValue("TABLE_CAT", null);
        return catalog;
    }


    private static void putNullInstance(final Metadata metadata) {

        if (metadata == null) {
            throw new NullPointerException("null metadata");
        }

        if (!metadata.getCatalogs().isEmpty()) {
            throw new IllegalArgumentException(
                "metadata.catalogs is not empty");
        }

        final Catalog catalog = new Catalog();
        catalog.setValue("TABLE_CAT", null);
        catalog.setMetadata(metadata);
        metadata.getCatalogs().add(catalog);
    }


    /**
     * Retrieves all categories.
     *
     * @param databaseMetaData database metadata
     * @param metadata metadata
     * @throws SQLException if a database access error occurs.
     */
    static void getCatalogs(final DatabaseMetaData databaseMetaData,
                            final Metadata metadata)
        throws SQLException {

        if (metadata.excludes.contains("getCatalogs")) {
            putNullInstance(metadata);
            return;
        }

        final ResultSet resultSet = databaseMetaData.getCatalogs();
        try {
            while (resultSet.next()) {
                final Catalog catalog = PairSet.newInstance(
                    Catalog.class, resultSet);
                catalog.setMetadata(metadata);
                metadata.getCatalogs().add(catalog);
            }

            if (metadata.getCatalogs().isEmpty()) {
                putNullInstance(metadata);
            }

            for (Catalog catalog : metadata.getCatalogs()) {
                Schema.getSchemas(databaseMetaData, catalog);
            }
        } finally {
            resultSet.close();
        }
    }


    /**
     * Returns the value of 'TABLE_CAT' entry.
     *
     * @return 'TABLE_CAT' entry value.
     */
    public String getTABLE_CAT() {
        return getValue("TABLE_CAT");
    }


    /**
     * Sets the value of 'TABLE_CAT' entry.
     *
     * @param TABLE_CAT 'TABLE_CAT' entry value.
     */
    public void setTABLE_CAT(final String TABLE_CAT) {
        setValue("TABLE_CAT", TABLE_CAT);
    }


    /**
     * Returns schemas.
     *
     * @return schemas
     */
    public final Collection<Schema> getSchemas() {

        if (schemas == null) {
            schemas = new ArrayList<Schema>();
        }

        return schemas;
    }


    @Override
    public String toString() {
        return super.toString() + "/" + getTABLE_CAT();
    }


    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Catalog)) {
            return false;
        }

        final Catalog catalog = (Catalog) obj;

        if (!(getTABLE_CAT() == catalog.getTABLE_CAT())
            || (getTABLE_CAT() != null
                && getTABLE_CAT().equals(catalog.getTABLE_CAT()))) {
            return false;
        }

        return true;
    }


    @Override
    public int hashCode() {

        int hashCode = 17;

        hashCode = 37 * hashCode
                   + (getTABLE_CAT() == null ? 0 : getTABLE_CAT().hashCode());

        return hashCode;
    }


    /**
     * schemas.
     */
    @XmlElement(name = "schema")
    @XmlElementWrapper(required = true, nillable = true)
    private Collection<Schema> schemas;


}

