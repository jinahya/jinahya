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


import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;

import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class DatabaseMetadataBindMain {


    private static class DatabaseOption {


        @Option(name = "-driver", required = true,
                usage = "database driver name")
        private String driver;


        @Option(name = "-url", required = true,
                usage = "a database url of the form jdbc:subprotocol:subname")
        private String url;


        @Option(name = "-user",
                usage = "the database user on whose behalf the connection is"
                        + " being made")
        private String user;


        @Option(name = "-password", usage = "the user's password")
        private String password;


    }


    /**
     * 
     * @param args command line arguments
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws JAXBException 
     */
    public static void main(final String[] args)
        throws IOException, ClassNotFoundException, SQLException,
               JAXBException {

        if (args.length == 1 && "--print-schema".equals(args[0])) {
            printSchema();
            return;
        }

        final DatabaseOption option = new DatabaseOption();

        final CmdLineParser parser = new CmdLineParser(option);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException cle) {
            cle.printStackTrace(System.err);
            parser.printUsage(System.out);
            return;
        }

        printMetadata(option.driver, option.url, option.user, option.password);
    }


    /**
     * Prints XMLSchema.
     *
     * @throws IOException if an I/O error occurs.
     *
     * @see JAXBContext#generateSchema(SchemaOutputResolver)
     */
    private static void printSchema() throws IOException {

        SchemaOutputResolver resolver = new SchemaOutputResolver() {


            @Override
            public Result createOutput(final String namespaceUri,
                                       final String suggestedFileName)
                throws IOException {

                return new StreamResult(System.out) {


                    @Override
                    public String getSystemId() {
                        return "System.out";
                    }


                };
            }


        };

        DatabaseMetadataBindConstants.JAXB_CONTEXT.generateSchema(resolver);
    }


    /**
     * Prints metadata.
     *
     * @param driver driver class name
     * @param url database connection url
     * @param user database user
     * @param password database password
     * @throws ClassNotFoundException if driver class not found
     * @throws SQLException if an SQL error occurs.
     * @throws JAXBException if a JAXB error occurs
     *
     * @see Class#forName(String)
     * @see DriverManager#getConnection(String)
     * @see DriverManager#getConnection(String, String, String)
     */
    private static void printMetadata(final String driver, final String url,
                                      final String user, final String password)
        throws ClassNotFoundException, SQLException, JAXBException {

        Class.forName(driver);

        final Connection connection;
        if (user != null && password != null) {
            connection = DriverManager.getConnection(url, user, password);
        } else {
            connection = DriverManager.getConnection(url);
        }
        try {
            final Catalogs catalogs =
                Catalogs.newInstance(connection.getMetaData());
            final Marshaller marshaller =
                DatabaseMetadataBindConstants.JAXB_CONTEXT.createMarshaller();
            marshaller.setProperty(
                Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(catalogs, System.out);
            System.out.flush();
        } finally {
            connection.close();
        }
    }


}

