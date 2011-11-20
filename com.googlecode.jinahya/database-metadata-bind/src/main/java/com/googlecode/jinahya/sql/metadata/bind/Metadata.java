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
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Root class of database metadata binding.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
@XmlType(propOrder = {"catalogs", "dataTypes", "properties", "schemas",
                      "tables", "tableTypes"})
public class Metadata {


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

        Catalogs.getCatalogs(databaseMetaData, instance.getCatalogs());

        DataTypes.getTypeInfo(databaseMetaData, instance.getDataTypes());

        Properties.getClientInfoProperties(
            databaseMetaData, instance.getProperties());

        Schemas.getSchemas(databaseMetaData, null, null, instance.getSchemas());

        Tables.getTables(databaseMetaData, null, null, null, null,
                         instance.getTables());

        TableTypes.getTableTypes(databaseMetaData, instance.getTableTypes());

        return instance;
    }


    public Collection<Catalog> getCatalogs() {

        if (catalogs == null) {
            catalogs = new ArrayList<Catalog>();
        }

        return catalogs;
    }


    /**
     * Returns properties.
     *
     * @return properties.
     */
    public Collection<Property> getProperties() {

        if (properties == null) {
            properties = new ArrayList<Property>();
        }

        return properties;
    }


    public Collection<Schema> getSchemas() {

        if (schemas == null) {
            schemas = new ArrayList<Schema>();
        }

        return schemas;
    }


    public Collection<Table> getTables() {

        if (tables == null) {
            tables = new ArrayList<Table>();
        }

        return tables;
    }


    public Collection<DataType> getDataTypes() {

        if (dataTypes == null) {
            dataTypes = new ArrayList<DataType>();
        }

        return dataTypes;
    }


    public Collection<TableType> getTableTypes() {

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


    @XmlElement(name = "schema")
    @XmlElementWrapper(required = true, nillable = true)
    private Collection<Schema> schemas;


    @XmlElement(name = "table")
    @XmlElementWrapper(required = true, nillable = true)
    private Collection<Table> tables;


    @XmlElement(name = "tableType")
    @XmlElementWrapper(required = true, nillable = true)
    private Collection<TableType> tableTypes;


}

