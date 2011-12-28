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


import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Schema binding.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
@XmlType(propOrder = {"entries", "tables"})
public class Schema extends ChildEntrySet<Catalog> {


    public static Schema newInstance(final ResultSet resultSet)
        throws SQLException {

        return newInstance(Schema.class, resultSet);
    }


    public String getTABLE_SCHEM() {
        return getValue("TABLE_SCHEM");
    }


    public void setTABLE_SCHEM(final String TABLE_SCHEM) {
        setValue("TABLE_SCHEM", TABLE_SCHEM);
    }


    public String getTABLE_CATALOG() {
        return getValue("TABLE_CATALOG");
    }


    public void setTABLE_CATALOG(final String TABLE_CATALOG) {
        setValue("TABLE_CATALOG", TABLE_CATALOG);
    }


    /**
     * Returns tables.
     *
     * @return tables
     */
    public Collection<Table> getTables() {

        if (tables == null) {
            tables = new ArrayList<Table>();
        }

        return tables;
    }


    @Override
    public String toString() {
        return super.toString() + "/" + getTABLE_CATALOG() + "/"
               + getTABLE_SCHEM();
    }


    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Schema)) {
            return false;
        }

        final Schema schema = (Schema) obj;

        if (!(getTABLE_CATALOG() == schema.getTABLE_CATALOG())
            || (getTABLE_CATALOG() != null
                && getTABLE_CATALOG().equals(schema.getTABLE_CATALOG()))) {

            return false;
        }

        if (!(getTABLE_SCHEM() == schema.getTABLE_SCHEM())
            || (getTABLE_SCHEM() != null
                && getTABLE_SCHEM().equals(schema.getTABLE_SCHEM()))) {

            return false;
        }

        return true;
    }


    @Override
    public int hashCode() {

        int hashCode = 17;

        hashCode = 37 * hashCode
                   + (getTABLE_CATALOG() == null
                      ? 0 : getTABLE_CATALOG().hashCode());

        hashCode = 37 * hashCode
                   + (getTABLE_SCHEM() == null
                      ? 0 : getTABLE_SCHEM().hashCode());

        return hashCode;
    }


    /**
     * tables.
     */
    @XmlElement(name = "table")
    @XmlElementWrapper(required = true, nillable = true)
    private Collection<Table> tables;


}

