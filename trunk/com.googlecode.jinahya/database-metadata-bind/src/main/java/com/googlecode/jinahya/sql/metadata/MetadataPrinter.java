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


import com.googlecode.jinahya.sql.metadata.bind.DatabaseMetadataBindConstants;
import com.googlecode.jinahya.sql.metadata.bind.Metadata;

import java.io.IOException;

import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class MetadataPrinter {


    /**
     * Command line option.
     */
    private static class ConnectionOption {


        public String getDriver() {
            return driver;
        }


        public void setDriver(final String driver) {
            this.driver = driver;
        }


        public String getPassword() {
            return password;
        }


        public void setPassword(final String password) {
            this.password = password;
        }


        public String getUrl() {
            return url;
        }


        public void setUrl(final String url) {
            this.url = url;
        }


        public String getUser() {
            return user;
        }


        public void setUser(final String user) {
            this.user = user;
        }


        public List<String> getMethodNamesToOmit() {

            if (methodNamesToOmit == null) {
                methodNamesToOmit = new ArrayList<String>();
            }

            return methodNamesToOmit;
        }


        /**
         * JDBC driver class name.
         */
        @Option(name = "-driver", usage = "database driver name")
        private String driver;


        /**
         * Database connection URL.
         */
        @Option(name = "-url", required = true,
                usage = "a database url of the form jdbc:subprotocol:subname")
        private String url;


        /**
         * Database user.
         */
        @Option(name = "-user",
                usage = "the database user on whose behalf the connection is"
                        + " being made")
        private String user;


        /**
         * Database password.
         */
        @Option(name = "-password", usage = "the user's password")
        private String password;


        /**
         * Methods to omit.
         */
        @Option(name = "-omit", usage = "method names to omit")
        private List<String> methodNamesToOmit;


    }


    /**
     * Prints database metadata.
     *
     * @param args command line arguments
     * @throws IOException if an I/O error occurs.
     * @throws ClassNotFoundException if JDBC driver class not found
     * @throws SQLException if an SQL error occurs.
     * @throws JAXBException if a JAXB error occurs.
     */
    public static void main(final String[] args)
        throws IOException, ClassNotFoundException, SQLException,
               JAXBException {

        final ConnectionOption option = new ConnectionOption();

        final CmdLineParser parser = new CmdLineParser(option);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException cle) {
            cle.printStackTrace(System.err);
            parser.printUsage(System.out);
            return;
        }

        final Metadata metadata = print(
            option.driver, option.url, option.user, option.password,
            option.getMethodNamesToOmit());

        print(metadata, System.out);
    }


    /**
     * Prints database metadata with given arguments.
     *
     * @param driver driver class name
     * @param url database connection URL
     * @param user database user
     * @param password database password
     * @param methodNamesToOmit method names to omit
     * @throws ClassNotFoundException if driver class not found
     * @throws SQLException if an SQL error occurs.
     * @throws JAXBException if a JAXB error occurs
     *
     * @see Class#forName(String)
     * @see DriverManager#getConnection(String)
     * @see DriverManager#getConnection(String, String, String)
     */
    public static Metadata print(final String driver, final String url,
                                 final String user, final String password,
                                 final List<String> methodNamesToOmit)
        throws ClassNotFoundException, SQLException, JAXBException {

        if (driver == null) {
            throw new NullPointerException("null driver");
        }

        if (url == null) {
            throw new NullPointerException("null url");
        }

        MethodNamesToOmit.getInstance().getNames().addAll(methodNamesToOmit);

        Class.forName(driver);

        final Connection connection;
        if (user != null && password != null) {
            connection = DriverManager.getConnection(url, user, password);
        } else {
            connection = DriverManager.getConnection(url);
        }
        try {
            return Metadata.newInstance(connection.getMetaData());
        } finally {
            connection.close();
        }
    }


    public static void print(final Metadata metadata)
        throws SQLException, JAXBException {

        print(metadata, System.out);
    }


    public static void print(final Metadata metadata, final OutputStream output)
        throws SQLException, JAXBException {

        if (metadata == null) {
            throw new NullPointerException("null metadata");
        }

        if (output == null) {
            throw new NullPointerException("null output");
        }

        final Marshaller marshaller =
            DatabaseMetadataBindConstants.JAXB_CONTEXT.createMarshaller();

        marshaller.setProperty(
            Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(
            Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "");

        marshaller.marshal(metadata, output);

        System.out.flush();
    }


}

