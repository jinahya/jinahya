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


import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Binding of client property.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class Property extends PairSet {


    /**
     * Retrieves client info properties.
     *
     * @param databaseMetaData database meta data
     * @param metadata metadata
     * @throws SQLException if a database access error occurs.
     * @see DatabaseMetaData#getClientInfoProperties()
     */
    static void getClientInfoProperties(final DatabaseMetaData databaseMetaData,
                                        final Metadata metadata)
        throws SQLException {

        if (metadata.excludes.contains("getClientInfoProperties")) {
            return;
        }

        final ResultSet resultSet = databaseMetaData.getClientInfoProperties();
        try {
            while (resultSet.next()) {
                final Property property =
                    PairSet.newInstance(Property.class, resultSet);
                property.setMetadata(metadata);
                metadata.getProperties().add(property);
            }
        } finally {
            resultSet.close();
        }
    }


    /**
     * Returns the value of 'NAME' entry.
     *
     * @return 'NAME' entry value
     */
    public String getNAME() {
        return getValue("NAME");
    }


    /**
     * Sets the value of 'NAME' entry.
     *
     * @param NAME 'NAME' entry value
     */
    public void setNAME(final String NAME) {
        setValue("NAME", NAME);
    }


    /**
     * Returns the value of 'MAX_LEN' entry.
     *
     * @return 'MAX_LEN' entry value.
     */
    public String getMAX_LEN() {
        return getValue("MAX_LEN");
    }


    /**
     * Sets the value of 'MAX_LEN' entry.
     *
     * @param MAX_LEN 'MAX_LEN' entry value
     */
    public void setMAX_LEN(final String MAX_LEN) {
        setValue("MAX_LEN", MAX_LEN);
    }


    /**
     * Returns the value of 'DEFAULT_VALUE' entry.
     *
     * @return 'DEFAULT_VALUE' entry value.
     */
    public String getDEFAULT_VALUE() {
        return getValue("DEFAULT_VALUE");
    }


    /**
     * Sets the value of 'DEFAULT_VALUE' entry.
     *
     * @param DEFAULT_VALUE 'DEFAULT_VALUE' entry value.
     */
    public void setDEFAULT_VALUE(final String DEFAULT_VALUE) {
        setValue("DEFAULT_VALUE", DEFAULT_VALUE);
    }


    /**
     * Returns the value of 'DESCRIPTION' entry.
     *
     * @return 'DESCRIPTION' entry value.
     */
    public String getDESCRIPTION() {
        return getValue("DESCRIPTION");
    }


    /**
     * Sets the value of 'DESCRIPTION' entry.
     *
     * @param DESCRIPTION 'DESCRIPTION' entry value
     */
    public void setDESCRIPTION(final String DESCRIPTION) {
        setValue("DESCRIPTION", DESCRIPTION);
    }


}

