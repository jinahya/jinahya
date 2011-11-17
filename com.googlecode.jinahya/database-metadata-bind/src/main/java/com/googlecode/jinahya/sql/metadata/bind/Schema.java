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
@XmlType(propOrder = {"entries", "attributes", "functions", "procedureColumns",
                      "procedures", "tables"})
public class Schema extends EntrySet {


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
     * Returns attributes.
     *
     * @return attributes
     */
    public Collection<Attribute> getAttributes() {

        if (attributes == null) {
            attributes = new ArrayList<Attribute>();
        }

        return attributes;
    }


    /**
     * Returns functions.
     *
     * @return functions
     */
    public Collection<Function> getFunctions() {

        if (functions == null) {
            functions = new ArrayList<Function>();
        }

        return functions;
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


    public Collection<ProcedureColumn> getProcedureColumns() {

        if (procedureColumns == null) {
            procedureColumns = new ArrayList<ProcedureColumn>();
        }

        return procedureColumns;
    }


    public Collection<Procedure> getProcedures() {

        if (procedures == null) {
            procedures = new ArrayList<Procedure>();
        }

        return procedures;
    }


    /**
     * attributes
     */
    @XmlElement(name = "attribute")
    @XmlElementWrapper(required = true, nillable = false)
    private Collection<Attribute> attributes;


    /**
     * functions.
     */
    @XmlElement(name = "function")
    @XmlElementWrapper(required = true, nillable = false)
    private Collection<Function> functions;


    @XmlElement(name = "procedureColumns")
    @XmlElementWrapper(required = true, nillable = false)
    private Collection<ProcedureColumn> procedureColumns;


    @XmlElement(name = "procedure")
    @XmlElementWrapper(required = true, nillable = true)
    private Collection<Procedure> procedures;


    /** tables. */
    @XmlElement(name = "table")
    @XmlElementWrapper(required = true, nillable = true)
    private Collection<Table> tables;


}

