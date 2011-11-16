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


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


import org.kohsuke.args4j.Option;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class MetadataPrinter {


    /**
     * Command line option.
     */
    public static class ConnectionOption {


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


        @Option(name = "-driver", usage = "database driver name")
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
     * Prints database metadata with given <code>option</code>.
     *
     * @param option database connection option
     * @throws ClassNotFoundException if <code>option.driver</code> not found
     * @throws SQLException if an SQL error occurs
     * @throws JAXBException if an JAXB error occurs.
     */
    protected static void printMetadata(final ConnectionOption option)
        throws ClassNotFoundException, SQLException, JAXBException {

        if (option == null) {
            throw new NullPointerException("null option");
        }

        printMetadata(option.driver, option.url, option.user, option.password);
    }


    /**
     * Prints database metadata with given arguments.
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
    protected static void printMetadata(
        final String driver, final String url, final String user,
        final String password)
        throws ClassNotFoundException, SQLException, JAXBException {

        if (driver == null) {
            throw new NullPointerException("null driver");
        }

        if (url == null) {
            throw new NullPointerException("null url");
        }

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

