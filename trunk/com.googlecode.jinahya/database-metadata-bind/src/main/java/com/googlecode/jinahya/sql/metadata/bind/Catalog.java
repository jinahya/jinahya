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
 * Catalog binding.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
@XmlType(propOrder = {"entries", "functionColumns", "functions",
                      "procedureColumns", "procedures", "schemas",
                      "tablePrivileges", "UDTs"})
public class Catalog extends EntrySet {


    /**
     * Returns the value of 'TABLE_CAT' entry.
     *
     * @return 'TABLE_CAT' entry value.
     */
    public String getTABLE_CAT() {
        return getValue("TABLE_CAT");
    }


    /**
     * Sets the value of 'TABLE_CAT' entry.
     *
     * @param TABLE_CAT 'TABLE_CAT' entry value.
     */
    public void setTABLE_CAT(final String TABLE_CAT) {
        setValue("TABLE_CAT", TABLE_CAT);
    }


    /**
     * Returns schemas.
     *
     * @return schemas
     */
    public Collection<Schema> getSchemas() {

        if (schemas == null) {
            schemas = new ArrayList<Schema>();
        }

        return schemas;
    }


    /**
     * Returns functions.
     *
     * @return functions.
     */
    public Collection<Function> getFunctions() {

        if (functions == null) {
            functions = new ArrayList<Function>();
        }

        return functions;
    }


    /**
     * Returns functionColumns.
     *
     * @return functionColumns.
     */
    public Collection<FunctionColumn> getFunctionColumns() {

        if (functionColumns == null) {
            functionColumns = new ArrayList<FunctionColumn>();
        }

        return functionColumns;
    }


    /**
     * Returns procedureColumns.
     *
     * @return procedureColumns.
     */
    public Collection<ProcedureColumn> getProcedureColumns() {

        if (procedureColumns == null) {
            procedureColumns = new ArrayList<ProcedureColumn>();
        }

        return procedureColumns;
    }


    /**
     * Procedures.
     *
     * @return procedures.
     */
    public Collection<Procedure> getProcedures() {

        if (procedures == null) {
            procedures = new ArrayList<Procedure>();
        }

        return procedures;
    }


    /**
     * Returns UDTs.
     *
     * @return UDTs.
     */
    public Collection<UDT> getUDTs() {

        if (UDTs == null) {
            UDTs = new ArrayList<UDT>();
        }

        return UDTs;
    }


    public Collection<TablePrivilege> getTablePrivileges() {

        if (tablePrivileges == null) {
            tablePrivileges = new ArrayList<TablePrivilege>();
        }

        return tablePrivileges;
    }


    /**
     * functions.
     */
    @XmlElement(name = "function")
    @XmlElementWrapper(required = true, nillable = true)
    private Collection<Function> functions;


    /**
     * function columns.
     */
    @XmlElement(name = "functionColumn")
    @XmlElementWrapper(required = true, nillable = true)
    private Collection<FunctionColumn> functionColumns;


    /**
     * procedure columns.
     */
    @XmlElement(name = "procedureColumn")
    @XmlElementWrapper(required = true, nillable = true)
    private Collection<ProcedureColumn> procedureColumns;


    /**
     * procedures.
     */
    @XmlElement(name = "procedure")
    @XmlElementWrapper(required = true, nillable = true)
    private Collection<Procedure> procedures;


    /**
     * schemas.
     */
    @XmlElement(name = "schema")
    @XmlElementWrapper(required = true, nillable = true)
    private Collection<Schema> schemas;


    @XmlElement(name = "tablePrivilege")
    @XmlElementWrapper(required = true, nillable = true)
    private Collection<TablePrivilege> tablePrivileges;


    /**
     * UDTs.
     */
    @XmlElement(name = "UDT")
    @XmlElementWrapper(required = true, nillable = true)
    private Collection<UDT> UDTs;


}

