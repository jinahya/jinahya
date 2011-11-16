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
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
/*
@XmlType(propOrder = {"tableCatalog", "tableSchema", "tableName", "tableType",
                      "remarks", "typeCatalog", "typeSchema", "typeName",
                      "idColumnName", "idColumnGeneration", "columns",
                      "indices"})
 */
public class Function extends Metadata {


    /*
    @XmlElement(required = true, nillable = true)
    public String getFunctionCatalog() {
        return getValue(String.class, "FUNCTION_CAT");
    }


    public void setFunctionCatalog(final String functionCatalog) {
        setValue(String.class, "FUNCTION_CAT", functionCatalog);
    }


    @XmlElement(required = true, nillable = true)
    public String getFunctionSchema() {
        return getValue(String.class, "FUNCTION_SCHEM");
    }


    public void setFunctionSchema(final String functionSchema) {
        setValue(String.class, "FUNCTION_SCHEM", functionSchema);
    }


    @XmlElement(required = true)
    public String getFunctionName() {
        return getValue(String.class, "FUNCTION_NAME");
    }


    public void setFunctionName(final String tableName) {
        setValue(String.class, "FUNCTION_NAME", tableName);
    }


    @XmlElement(required = true)
    public String getRemarks() {
        return getValue(String.class, "REMARKS");
    }


    public void setRemarks(final String remarks) {
        setValue(String.class, "REMARKS", remarks);
    }


    @XmlElement(required = true, nillable = false)
    public Short getFunctionType() {
        return getValue(Short.class, "FUNCTION_TYPE");
    }


    public void setFunctionType(final Short functionType) {
        setValue(Short.class, "FUNCTION_TYPE", functionType);
    }


    @XmlElement(required = true)
    public String getSpecificName() {
        return getValue(String.class, "SPECIIC_CAT");
    }


    public void setSpecificName(final String specificName) {
        setValue(String.class, "SPECIFIC_CAT", specificName);
    }
     */


}

