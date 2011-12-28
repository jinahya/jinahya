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
public class ProcedureColumn extends ChildEntrySet<Catalog> {


    public static ProcedureColumn newInstance(final ResultSet resultSet)
        throws SQLException {

        return newInstance(ProcedureColumn.class, resultSet);
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


    public String getCOLUMN_NAME() {
        return getValue("COLUMN_NAME");
    }


    public void setCOLUMN_NAME(final String COLUMN_NAME) {
        setValue("COLUMN_NAME", COLUMN_NAME);
    }


    public String getCOLUMN_TYPE() {
        return getValue("COLUMN_TYPE");
    }


    public void setCOLUMN_TYPE(final String COLUMN_TYPE) {
        setValue("COLUMN_TYPE", COLUMN_TYPE);
    }


    public String getDATA_TYPE() {
        return getValue("DATA_TYPE");
    }


    public void setDATA_TYPE(final String DATA_TYPE) {
        setValue("DATA_TYPE", DATA_TYPE);
    }


    public String getTYPE_NAME() {
        return getValue("TYPE_NAME");
    }


    public void setTYPE_NAME(final String TYPE_NAME) {
        setValue("TYPE_NAME", TYPE_NAME);
    }


    public String getPRECISION() {
        return getValue("PRECISION");
    }


    public void setPRECISION(final String PRECISION) {
        setValue("PRECISION", PRECISION);
    }


    public String getLENGTH() {
        return getValue("LENGTH");
    }


    public void setLENGTH(final String LENGTH) {
        setValue("LENGTH", LENGTH);
    }


    public String getSCALE() {
        return getValue("SCALE");
    }


    public void setSCALE(final String SCALE) {
        setValue("SCALE", SCALE);
    }


    public String getRADIX() {
        return getValue("RADIX");
    }


    public void setRADIX(final String RADIX) {
        setValue("RADIX", RADIX);
    }


    public String getNULLABLE() {
        return getValue("NULLABLE");
    }


    public void setNULLABLE(final String NULLABLE) {
        setValue("NULLABLE", NULLABLE);
    }


    public String getREMARKS() {
        return getValue("REMARKS");
    }


    public void setREMARKS(final String REMARKS) {
        setValue("REMARKS", REMARKS);
    }


    public String getCOLUMN_DEF() {
        return getValue("COLUMN_DEF");
    }


    public void setCOLUMN_DEF(final String COLUMN_DEF) {
        setValue("COLUMN_DEF", COLUMN_DEF);
    }


    public String getSQL_DATA_TYPE() {
        return getValue("SQL_DATA_TYPE");
    }


    public void setSQL_DATA_TYPE(final String SQL_DATA_TYPE) {
        setValue("SQL_DATA_TYPE", SQL_DATA_TYPE);
    }


    public String getSQL_DATETIME_SUB() {
        return getValue("SQL_DATETIME_SUB");
    }


    public void setSQL_DATETIME_SUB(final String SQL_DATETIME_SUB) {
        setValue("SQL_DATETIME_SUB", SQL_DATETIME_SUB);
    }


    public String getCHAR_OCTET_LENGTH() {
        return getValue("CHAR_OCTET_LENGTH ");
    }


    public void setCHAR_OCTET_LENGTH(final String CHAR_OCTET_LENGTH) {
        setValue("CHAR_OCTET_LENGTH", CHAR_OCTET_LENGTH);
    }


    public String getORDINAL_POSITION() {
        return getValue("ORDINAL_POSITION ");
    }


    public void setORDINAL_POSITION(final String ORDINAL_POSITION) {
        setValue("ORDINAL_POSITION", ORDINAL_POSITION);
    }


    public String getIS_NULLABLE() {
        return getValue("IS_NULLABLE");
    }


    public void setIS_NULLABLE(final String IS_NULLABLE) {
        setValue("IS_NULLABLE", IS_NULLABLE);
    }


    public String getSPECIFIC_NAME() {
        return getValue("SPECIFIC_NAME");
    }


    public void setSPECIFIC_NAME(final String SPECIFIC_NAME) {
        setValue("SPECIFIC_NAME", SPECIFIC_NAME);
    }


}

