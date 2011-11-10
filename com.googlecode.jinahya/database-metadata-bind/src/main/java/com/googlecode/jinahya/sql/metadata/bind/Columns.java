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
public class Columns extends MetadataCollectable<Column> {


    public static Columns newInstance(final DatabaseMetaData databaseMetaData,
                                      final String catalog,
                                      final String schemaPattern,
                                      final String tableNamePattern,
                                      final String columnNamePattern)
        throws SQLException {

        final ResultSet columnResultSet = databaseMetaData.getColumns(
            catalog, schemaPattern, tableNamePattern, columnNamePattern);
        try {
            final Columns columns = new Columns();
            while (columnResultSet.next()) {
                final Column column = MetadataAccessible.newInstance(
                    Column.class, columnResultSet);
                columns.getMetadata().add(column);
            }

            return columns;

        } finally {
            columnResultSet.close();
        }
    }


    @XmlElement(name = "column")
    public Collection<Column> getColumns() {
        return super.getMetadata();
    }


}

