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
public class Catalogs extends MetadataCollection<Catalog> {


    public static Catalogs newInstance(final DatabaseMetaData databaseMetaData)
        throws SQLException {

        final ResultSet catalogResultSet = databaseMetaData.getCatalogs();
        try {
            final Catalogs catalogs = new Catalogs();
            while (catalogResultSet.next()) {
                final Catalog catalog = Metadata.newInstance(
                   Catalog.class, catalogResultSet);
                catalogs.getMetadata().add(catalog);

                final Schemas schemas = Schemas.newInstance(
                    databaseMetaData, catalog.getTableCatalog(), null);
                catalog.getSchemas().addAll(schemas.getMetadata());
            }

            return catalogs;

        } finally {
            catalogResultSet.close();
        }
    }


    @XmlElement(name = "catalog")
    @Override
    public Collection<Catalog> getMetadata() {
        return super.getMetadata();
    }


}

