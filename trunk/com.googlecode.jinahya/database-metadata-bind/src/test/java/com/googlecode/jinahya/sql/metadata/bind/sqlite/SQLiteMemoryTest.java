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


package com.googlecode.jinahya.sql.metadata.bind.sqlite;


import com.googlecode.jinahya.sql.metadata.MetadataPrinter;
import com.googlecode.jinahya.sql.metadata.MethodNamesToOmit;
import com.googlecode.jinahya.sql.metadata.bind.Metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.xml.bind.JAXBException;

import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class SQLiteMemoryTest extends SQLiteTest {


    /**
     * Database connection URL for memory.
     */
    protected static final String MEMORY_DATABASE_URL = "jdbc:sqlite::memory:";


    @Test
    public void create() throws SQLException {

        final Connection connection =
            DriverManager.getConnection(MEMORY_DATABASE_URL);
        try {
        } finally {
            connection.close();
        }
    }


    @Test
    public void printMetadata() throws SQLException, JAXBException {

        MethodNamesToOmit.addNameToInstance("getClientInfoProperties");
        MethodNamesToOmit.addNameToInstance("getSchemas");

        final Connection connection =
            DriverManager.getConnection(MEMORY_DATABASE_URL);
        try {
            final DatabaseMetaData databaseMetaData = connection.getMetaData();

            final Metadata metadata = Metadata.newInstance(databaseMetaData);

            MetadataPrinter.printMetadata(metadata);

        } finally {
            connection.close();
        }
    }


}

