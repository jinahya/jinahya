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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Schema binding.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
@XmlType(propOrder = {"entries", "tables"})
public class Schema extends ChildEntrySet<Catalog> {


    /**
     * Creates a new null instance.
     *
     * @param catalog catalog name.
     * @return a new null instance.
     */
    private static Schema newNullInstance(final String catalog) {
        final Schema schema = new Schema();
        schema.setValue("TABLE_CATALOG", catalog);
        schema.setValue("TABLE_SCHEM", null);
        return schema;
    }


    /**
     * Retrieves all <code>Schema</code> mapped to given <code>catalog</code>.
     *
     * @param databaseMetaData meta
     * @param catalog catalog
     * @throws SQLException if a database access error occurs.
     */
    static void getSchemas(final DatabaseMetaData databaseMetaData,
                           final Catalog catalog)
        throws SQLException {

        if (catalog.getMetadata().excludes.contains("getSchemas")) {
            final Schema schema = Schema.newNullInstance(
                catalog.getValue("TABLE_CAT"));
            schema.setParent(catalog);
            catalog.getSchemas().add(schema);
            return;
        }

        final ResultSet resultSet = databaseMetaData.getSchemas(
            catalog.getValue("TABLE_CAT"), null);
        try {
            while (resultSet.next()) {
                final Schema schema =
                    EntrySet.newInstance(Schema.class, resultSet);
                schema.setParent(catalog);
                catalog.getSchemas().add(schema);
            }
            if (catalog.getSchemas().isEmpty()) {
                final Schema schema = Schema.newNullInstance(
                    catalog.getValue("TABLE_CAT"));
                schema.setParent(catalog);
                catalog.getSchemas().add(schema);
            }
            for (Schema schema : catalog.getSchemas()) {
                Table.getTables(databaseMetaData, schema);
            }
        } finally {
            resultSet.close();
        }
    }


    public String getTABLE_SCHEM() {
        return getValue("TABLE_SCHEM");
    }


    public void setTABLE_SCHEM(final String TABLE_SCHEM) {
        setValue("TABLE_SCHEM", TABLE_SCHEM);
    }


    public String getTABLE_CATALOG() {
        return getValue("TABLE_CATALOG");
    }


    public void setTABLE_CATALOG(final String TABLE_CATALOG) {
        setValue("TABLE_CATALOG", TABLE_CATALOG);
    }


    /**
     * Returns tables.
     *
     * @return tables
     */
    public final Collection<Table> getTables() {

        if (tables == null) {
            tables = new ArrayList<Table>();
        }

        return tables;
    }


    @Override
    public String toString() {
        return super.toString() + "/" + getTABLE_CATALOG() + "/"
               + getTABLE_SCHEM();
    }


    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Schema)) {
            return false;
        }

        final Schema schema = (Schema) obj;

        if (!(getTABLE_CATALOG() == schema.getTABLE_CATALOG())
            || (getTABLE_CATALOG() != null
                && getTABLE_CATALOG().equals(schema.getTABLE_CATALOG()))) {

            return false;
        }

        if (!(getTABLE_SCHEM() == schema.getTABLE_SCHEM())
            || (getTABLE_SCHEM() != null
                && getTABLE_SCHEM().equals(schema.getTABLE_SCHEM()))) {

            return false;
        }

        return true;
    }


    @Override
    public int hashCode() {

        int hashCode = 17;

        hashCode = 37 * hashCode
                   + (getTABLE_CATALOG() == null
                      ? 0 : getTABLE_CATALOG().hashCode());

        hashCode = 37 * hashCode
                   + (getTABLE_SCHEM() == null
                      ? 0 : getTABLE_SCHEM().hashCode());

        return hashCode;
    }


    /**
     * tables.
     */
    @XmlElement(name = "table")
    @XmlElementWrapper(required = true, nillable = true)
    private Collection<Table> tables;


}

