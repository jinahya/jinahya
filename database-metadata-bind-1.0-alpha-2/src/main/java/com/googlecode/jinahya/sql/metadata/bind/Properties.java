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
 * Identifier collection.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class Properties extends EntrySetWrapper<Property> {


    /**
     * 
     * @param databaseMetaData
     * @return
     * @throws SQLException 
     *
     * @see DatabaseMetaData#getClientInfoProperties() 
     */
    public static Properties newInstance(
        final DatabaseMetaData databaseMetaData)
        throws SQLException {

        final Properties properties = new Properties();

        getClientInfoProperties(databaseMetaData, properties.getProperties());

        return properties;
    }


    /**
     * 
     * @param databaseMetaData
     * @param properties
     * @throws SQLException 
     *
     * @see DatabaseMetaData#getClientInfoProperties()
     */
    public static void getClientInfoProperties(
        final DatabaseMetaData databaseMetaData,
        final Collection<Property> properties)
        throws SQLException {

        try {
            final ResultSet propertyResultSet =
                databaseMetaData.getClientInfoProperties();
            try {
                while (propertyResultSet.next()) {
                    final Property property = EntrySet.newInstance(
                        Property.class, propertyResultSet);
                    properties.add(property);
                }
            } finally {
                propertyResultSet.close();
            }
        } catch (AbstractMethodError ame) {
            //ame.printStackTrace(System.err);
        }
    }


    /**
     * Creates a new instance.
     */
    public Properties() {
        super(Property.class);
    }


    /**
     * Returns properties.
     *
     * @return properties
     */
    @XmlElement(name = "property")
    public Collection<Property> getProperties() {
        return super.getEntrySets();
    }


}

