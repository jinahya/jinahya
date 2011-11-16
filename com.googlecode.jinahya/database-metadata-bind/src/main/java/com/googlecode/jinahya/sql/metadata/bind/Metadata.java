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
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Root class of database metadata binding.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
@XmlType(propOrder = {"properties", "catalogs"})
public class Metadata {


    /**
     * Creates a new instance.
     *
     * @param connection database connection
     * @return a new instance of Metadata
     * @throws SQLException if an SQL error occurs.
     */
    public static Metadata newInstance(final Connection connection)
        throws SQLException {

        return newInstance(connection.getMetaData());
    }


    /**
     * Creates a new instance.
     *
     * @param databaseMetaData database metadata
     * @return a new instance of Metadata
     * @throws SQLException if an SQL error occurs.
     */
    public static Metadata newInstance(final DatabaseMetaData databaseMetaData)
        throws SQLException {

        final Metadata instance = new Metadata();

        instance.properties = Properties.newInstance(databaseMetaData);

        instance.catalogs = Catalogs.newInstance(databaseMetaData);

        return instance;
    }


    /**
     * Returns catalogs.
     *
     * @return catalogs
     */
    public Catalogs getCatalogs() {
        return catalogs;
    }


    /**
     * Sets catalogs.
     *
     * @param catalogs catalogs.
     */
    public void setCatalogs(final Catalogs catalogs) {
        this.catalogs = catalogs;
    }


    /**
     * Returns properties.
     *
     * @return properties.
     */
    public Properties getProperties() {
        return properties;
    }


    /**
     * Sets properties.
     *
     * @param properties properties.
     */
    public void setProperties(final Properties properties) {
        this.properties = properties;
    }


    @XmlElement(required = true, nillable = true)
    private Properties properties;


    @XmlElement(required = true, nillable = true)
    private Catalogs catalogs;


}

