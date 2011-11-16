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
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class Schemas extends MetadataSet<Schema> {


    /**
     * 
     * @param databaseMetaData
     * @param catalog
     * @param schemaPattern
     * @return
     * @throws SQLException 
     * @see DatabaseMetaData#getSchemas(String, String)
     */
    public static Schemas newInstance(final DatabaseMetaData databaseMetaData,
                                      final String catalog,
                                      final String schemaPattern)
        throws SQLException {

        final ResultSet schemaResultSet =
            databaseMetaData.getSchemas(catalog, schemaPattern);
        try {
            final Schemas schemas = new Schemas();
            while (schemaResultSet.next()) {
                final Schema schema = Metadata.newInstance(
                    Schema.class, schemaResultSet);
                schemas.getMetadata().add(schema);

                final Tables tables = Tables.newInstance(
                    databaseMetaData, catalog, schema.getValue("TABLE_SCHEM"),
                    null, null);
                schema.getTables().addAll(tables.getMetadata());
            }

            return schemas;

        } finally {
            schemaResultSet.close();
        }
    }


    /**
     * Creates a new instance.
     */
    public Schemas() {
        super(Schema.class);
    }


    @XmlElement(name = "schema")
    public Collection<Schema> getSchemas() {
        return super.getMetadata();
    }


}

