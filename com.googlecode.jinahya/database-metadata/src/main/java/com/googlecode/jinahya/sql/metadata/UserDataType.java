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

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;


/**
 * Binding for UDTs of a {@link Catalog}.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlType(propOrder = {"pairs", "attributes"})
public class UserDataType extends ChildPairSet<Schema> {


    /**
     * Retrieves all UDTs mapped to given
     * <code>schema</code>.
     *
     * @param databaseMetaData database meta data
     * @param schema schema
     * @throws SQLException if a database access error occurs.
     * @see DatabaseMetaData#getUDTs(String, String, String, int[])
     */
    static void getUserDataTypes(final DatabaseMetaData databaseMetaData,
                                 final Schema schema)
        throws SQLException {

        if (schema.getMetadata().excludes.contains("getUDTs")) {
            return;
        }

        final ResultSet resultSet = databaseMetaData.getUDTs(
            schema.getValue("TABLE_CATALOG"), schema.getValue(("TABLE_SCHEM")),
            null, null);
        try {
            while (resultSet.next()) {
                final UserDataType userDataType =
                    PairSet.newInstance(UserDataType.class, resultSet);
                userDataType.setParent(schema);
                schema.getUserDataTypes().add(userDataType);
            }
        } finally {
            resultSet.close();
        }
    }


    public String getTYPE_CAT() {
        return getValue("TYPE_CAT");
    }


    public void setTYPE_CAT(final String TYPE_CAT) {
        setValue("TYPE_CAT", TYPE_CAT);
    }


    public String getTYPE_SCHEM() {
        return getValue("TYPE_SCHEM");
    }


    public void setTYPE_SCHEM(final String TYPE_SCHEM) {
        setValue("TYPE_SCHEM", TYPE_SCHEM);
    }


    public String getTYPE_NAME() {
        return getValue("TYPE_NAME");
    }


    public void setTYPE_NAME(final String TYPE_NAME) {
        setValue("TYPE_NAME", TYPE_NAME);
    }


    public String getCLASS_NAME() {
        return getValue("CLASS_NAME");
    }


    public void setCLASS_NAME(final String CLASS_NAME) {
        setValue("CLASS_NAME", CLASS_NAME);
    }


    public String getDATA_TYPE() {
        return getValue("DATA_TYPE");
    }


    public void setDATA_TYPE(final String DATA_TYPE) {
        setValue("DATA_TYPE", DATA_TYPE);
    }


    public String getREMARKS() {
        return getValue("REMARKS");
    }


    public void setREMARKS(final String REMARKS) {
        setValue("REMARKS", REMARKS);
    }


    public String getBASE_TYPE() {
        return getValue("BASE_TYPE");
    }


    public void setBASE_TYPE(final String BASE_TYPE) {
        setValue("BASE_TYPE", BASE_TYPE);
    }


    /**
     * Returns attributes.
     *
     * @return attributes.
     */
    public Collection<Attribute> getAttributes() {

        if (attributes == null) {
            attributes = new ArrayList<Attribute>();
        }

        return attributes;
    }


    /**
     * attributes.
     */
    @XmlElement(name = "attribute")
    @XmlElementWrapper(required = true, nillable = true)
    private Collection<Attribute> attributes;


}

