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


import java.io.IOException;
import java.io.OutputStream;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;


/**
 * Root class of database metadata.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
@XmlType(propOrder = {"catalogs", "dataTypes", "properties", "tableTypes"})
public class Metadata {


    /**
     * Command line option.
     */
    private static class ConnectionOption {


        /*
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


        public List<String> getExcludes() {

            if (excludes == null) {
                excludes = new ArrayList<String>();
            }

            return excludes;
        }
        */


        /** the JDBC driver class name. */
        @Option(name = "-driver", usage = "database driver name")
        private String driver;


        /** the connection URL. */
        @Option(name = "-url", required = true,
                usage = "a database url of the form jdbc:subprotocol:subname")
        private String url;


        /** the database user. */
        @Option(name = "-user", usage = "the database user")
        private String user;


        /** the user's password. */
        @Option(name = "-password", usage = "the user's password")
        private String password;


        /** method names to exclude. */
        @Option(name = "-exclude", usage = "method name to exclude")
        private final List<String> excludes = new ArrayList<String>();


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

        final Metadata metadata = bind(
            option.driver, option.url, option.user, option.password,
            option.excludes);

        print(metadata, System.out);
    }


    /**
     * Binds database meta data with given arguments.
     *
     * @param driver driver class name
     * @param url database connection URL
     * @param user database user
     * @param password database password
     * @param excludes method names to omit
     * @return a new instance.
     * @throws ClassNotFoundException if driver class not found
     * @throws SQLException if a database access error occurs.
     * @throws JAXBException if a JAXB error occurs
     * @see Class#forName(String)
     * @see DriverManager#getConnection(String)
     * @see DriverManager#getConnection(String, String, String)
     */
    public static Metadata bind(final String driver, final String url,
                                final String user, final String password,
                                final List<String> excludes)
        throws ClassNotFoundException, SQLException, JAXBException {

        if (driver == null) {
            throw new NullPointerException("null driver");
        }

        if (url == null) {
            throw new NullPointerException("null url");
        }

        Class.forName(driver);

        Connection connection = null;
        try {
            if (user != null && password != null) {
                connection = DriverManager.getConnection(url, user, password);
            } else {
                connection = DriverManager.getConnection(url);
            }
            return Metadata.newInstance(connection.getMetaData(), excludes);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }


    /**
     * Prints given <code>metadata</code> to specified <code>output</code>.
     *
     * @param metadata metadata
     * @param output output
     * @throws SQLException if a database access error occurs.
     * @throws JAXBException if a JAXB error occurs.
     */
    public static void print(final Metadata metadata, final OutputStream output)
        throws SQLException, JAXBException {

        if (metadata == null) {
            throw new NullPointerException("null metadata");
        }

        if (output == null) {
            throw new NullPointerException("null output");
        }

        final JAXBContext context =
            JAXBContext.newInstance("com.googlecode.jinahya.sql.metadata");

        final Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty(
            Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        /*
        marshaller.setProperty(
        Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "");
         */

        marshaller.marshal(metadata, output);
    }


    /**
     * Creates a new instance.
     *
     * @param databaseMetaData database meta data
     * @param excludes method names to excludes
     * @return a new instance
     * @throws SQLException if an SQL error occurs.
     */
    public static Metadata newInstance(final DatabaseMetaData databaseMetaData,
                                       final Collection<String> excludes)
        throws SQLException {

        final Metadata metadata = new Metadata();
        metadata.excludes.addAll(excludes);

        Catalog.getCatalogs(databaseMetaData, metadata);
        DataType.getTypeInfo(databaseMetaData, metadata);
        Property.getClientInfoProperties(databaseMetaData, metadata);
        TableType.getTableTypes(databaseMetaData, metadata);

        return metadata;
    }


    /**
     * Returns catalogs.
     *
     * @return catalogs.
     */
    public final Collection<Catalog> getCatalogs() {

        if (catalogs == null) {
            catalogs = new ArrayList<Catalog>();
        }

        return catalogs;
    }


    /**
     * Returns dataTypes.
     *
     * @return dataTypes.
     */
    public final Collection<DataType> getDataTypes() {

        if (dataTypes == null) {
            dataTypes = new ArrayList<DataType>();
        }

        return dataTypes;
    }


    /**
     * Returns properties.
     *
     * @return properties.
     */
    public final Collection<Property> getProperties() {

        if (properties == null) {
            properties = new ArrayList<Property>();
        }

        return properties;
    }


    /**
     * Returns tableTypes.
     *
     * @return tableTypes.
     */
    public final Collection<TableType> getTableTypes() {

        if (tableTypes == null) {
            tableTypes = new ArrayList<TableType>();
        }

        return tableTypes;
    }


    @XmlElement(name = "catalog")
    @XmlElementWrapper(required = true, nillable = true)
    private Collection<Catalog> catalogs;


    @XmlElement(name = "dataType")
    @XmlElementWrapper(required = true, nillable = true)
    private Collection<DataType> dataTypes;


    @XmlElement(name = "property")
    @XmlElementWrapper(required = true, nillable = true)
    private Collection<Property> properties;


    @XmlElement(name = "tableType")
    @XmlElementWrapper(required = true, nillable = true)
    private Collection<TableType> tableTypes;


    @XmlTransient
    final Set<String> excludes = new HashSet<String>();


}

