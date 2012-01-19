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
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlType(propOrder = {"entries", "columns"})
public class Function extends ChildEntrySet<Schema> {


    /**
     * Retrieves functions for given
     * <code>schema</code>.
     *
     * @param databaseMetaData database meta data
     * @param schema schema
     * @throws SQLException if a database access error occurs.
     */
    static void getFunctions(final DatabaseMetaData databaseMetaData,
                             final Schema schema)
        throws SQLException {

        if (schema.getMetadata().excludes.contains("getFunctions")) {
            return;
        }

        final ResultSet resultSet = databaseMetaData.getFunctions(
            schema.getValue("TABLE_CATALOG"), schema.getValue("TABLE_SCHEM"),
            null);
        try {
            while (resultSet.next()) {

                final Function function = EntrySet.newInstance(
                    Function.class, resultSet);
                function.setParent(schema);
                schema.getFunctions().add(function);

                FunctionColumn.getFunctionColumns(databaseMetaData, function);
            }
        } finally {
            resultSet.close();
        }
    }


    public String getFUNCTION_CAT() {
        return getValue("FUNCTION_CAT");
    }


    public void setFUNCTION_CAT(final String FUNCTION_CAT) {
        setValue("FUNCTION_CAT", FUNCTION_CAT);
    }


    public String getFUNCTION_SCHEM() {
        return getValue("FUNCTION_SCHEM");
    }


    public void setFUNCTION_SCHEM(final String FUNCTION_SCHEM) {
        setValue("FUNCTION_SCHEM", FUNCTION_SCHEM);
    }


    public String getFUNCTION_NAME() {
        return getValue("FUNCTION_NAME");
    }


    public void setFUNCTION_NAME(final String FUNCTION_NAME) {
        setValue("FUNCTION_NAME", FUNCTION_NAME);
    }


    public String getREMARKS() {
        return getValue("REMARKS");
    }


    public void setREMARKS(final String REMARKS) {
        setValue("REMARKS", REMARKS);
    }


    public String getFUNCTION_TYPE() {
        return getValue("FUNCTION_TYPE");
    }


    public void setFUNCTION_TYPE(final String FUNCTION_TYPE) {
        setValue("FUNCTION_TYPE", FUNCTION_TYPE);
    }


    public String getSPECIFIC_NAME() {
        return getValue("SPECIFIC_NAME");
    }


    public void setSPECIFIC_NAME(final String SPECIFIC_NAME) {
        setValue("SPECIFIC_NAME", SPECIFIC_NAME);
    }


    /**
     * Returns function columns.
     *
     * @return function columns
     */
    public Collection<FunctionColumn> getColumns() {
        if (columns == null) {
            columns = new ArrayList<FunctionColumn>();
        }
        return columns;
    }


    /**
     * function columns.
     */
    @XmlElement(name = "column")
    @XmlElementWrapper(required = true, nillable = true)
    private Collection<FunctionColumn> columns;


}