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
public class Indices extends MetadataCollection<Index> {


    public static Indices newInstance(final DatabaseMetaData databaseMetaData,
                                      final String catalog, final String schema,
                                      final String table, final boolean unique,
                                      final boolean approximate)
        throws SQLException {

        final ResultSet indexResultSet = databaseMetaData.getIndexInfo(
            catalog, schema, table, unique, approximate);
        try {
            final Indices indices = new Indices();
            while (indexResultSet.next()) {
                final Index index = Metadata.newInstance(
                    Index.class, indexResultSet);
                indices.getMetadata().add(index);
            }

            return indices;

        } finally {
            indexResultSet.close();
        }
    }


    @XmlElement(name = "index")
    @Override
    public Collection<Index> getMetadata() {
        return super.getMetadata();
    }


}

