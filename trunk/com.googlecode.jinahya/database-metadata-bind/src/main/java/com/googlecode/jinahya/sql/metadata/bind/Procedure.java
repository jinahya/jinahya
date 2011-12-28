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

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
@XmlType(propOrder = {"entries"})
public class Procedure extends ChildEntrySet<Catalog> {


    public static Procedure newInstance(final ResultSet resultSet)
        throws SQLException {

        return newInstance(Procedure.class, resultSet);
    }


    public String getPROCEDURE_CAT() {
        return getValue("PROCEDURE_CAT");
    }


    public void setPROCEDURE_CAT(final String PROCEDURE_CAT) {
        setValue("PROCEDURE_CAT", PROCEDURE_CAT);
    }


    public String getPROCEDURE_SCHEM() {
        return getValue("PROCEDURE_SCHEM");
    }


    public void setPROCEDURE_SCHEM(final String PROCEDURE_SCHEM) {
        setValue("PROCEDURE_SCHEM", PROCEDURE_SCHEM);
    }


    public String getPROCEDURE_NAME() {
        return getValue("PROCEDURE_NAME");
    }


    public void setPROCEDURE_NAME(final String PROCEDURE_NAME) {
        setValue("PROCEDURE_NAME", PROCEDURE_NAME);
    }


    public String getREMARKS() {
        return getValue("REMARKS");
    }


    public void setREMARKS(final String REMARKS) {
        setValue("REMARKS", REMARKS);
    }


    public String getPROCEDURE_TYPE() {
        return getValue("PROCEDURE_TYPE");
    }


    public void setPROCEDURE_TYPE(final String PROCEDURE_TYPE) {
        setValue("PROCEDURE_TYPE", PROCEDURE_TYPE);
    }


    public String getSPECIFIC_NAME() {
        return getValue("SPECIFIC_NAME");
    }


    public void setSPECIFIC_NAME(final String SPECIFIC_NAME) {
        setValue("SPECIFIC_NAME", SPECIFIC_NAME);
    }


}

