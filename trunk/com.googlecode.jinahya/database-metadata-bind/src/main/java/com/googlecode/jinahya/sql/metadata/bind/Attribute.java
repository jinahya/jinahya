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


/**
 * Binding for attributes.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class Attribute extends EntrySet {


    /**
     * Creates a new instance from given <code>resultSet</code>.
     * 
     * @param resultSet resultSet
     * @return a new instance
     * @throws SQLException if an SQL error occurs.
     */
    public static Attribute newInstance(final ResultSet resultSet)
        throws SQLException {

        return newInstance(Attribute.class, resultSet);
    }


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


    public String getTABLE_SCHEM() {
        return getValue("TABLE_SCHEM");
    }


    public void setTABLE_SCHEM(final String TABLE_SCHEM) {
        setValue("TABLE_SCHEM", TABLE_SCHEM);
    }


    public String getATTR_NAME() {
        return getValue("ATTR_NAME");
    }


    public void setATTR_NAME(final String ATTR_NAME) {
        setValue("ATTR_NAME", ATTR_NAME);
    }


    public String getDATA_TYPE() {
        return getValue("DATA_TYPE");
    }


    public void setDATA_TYPE(final String DATA_TYPE) {
        setValue("DATA_TYPE", DATA_TYPE);
    }


    public String getATTR_TYPE_NAME() {
        return getValue("ATTR_TYPE_NAME");
    }


    public void setATTR_TYPE_NAME(final String ATTR_TYPE_NAME) {
        setValue("ATTR_TYPE_NAME", ATTR_TYPE_NAME);
    }


    public String getATTR_SIZE() {
        return getValue("ATTR_SIZE");
    }


    public void setATTR_SIZE(final String ATTR_SIZE) {
        setValue("ATTR_SIZE", ATTR_SIZE);
    }


    public String getDECIMAL_DIGITS() {
        return getValue("DECIMAL_DIGITS");
    }


    public void setDECIMAL_DIGITS(final String DECIMAL_DIGITS) {
        setValue("DECIMAL_DIGITS", DECIMAL_DIGITS);
    }


    public String getNUM_PREC_RADIX() {
        return getValue("NUM_PREC_RADIX");
    }


    public void setNUM_PREC_RADIX(final String NUM_PREC_RADIX) {
        setValue("NUM_PREC_RADIX", NUM_PREC_RADIX);
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


    public String getATTR_DEF() {
        return getValue("ATTR_DEF");
    }


    public void setATTR_DEF(final String ATTR_DEF) {
        setValue("ATTR_DEF", ATTR_DEF);
    }


    public String getSQL_DATA_TYPE() {
        return getValue("SQL_DATA_TYPE");
    }


    public void setSQL_DATA_TYPE(final String SQL_DATA_TYPE) {
        setValue("SQL_DATA_TYPE", SQL_DATA_TYPE);
    }


    public String getSQL_DATETIME_SUB() {
        return getValue("SQL_DATETIME_SUB ");
    }


    public void setSQL_DATETIME_SUB(final String SQL_DATETIME_SUB) {
        setValue("SQL_DATETIME_SUB", SQL_DATETIME_SUB);
    }


    public String getCHAR_OCTET_LENGTH() {
        return getValue("CHAR_OCTET_LENGTH");
    }


    public void setCHAR_OCTET_LENGTH(final String CHAR_OCTET_LENGTH) {
        setValue("CHAR_OCTET_LENGTH", CHAR_OCTET_LENGTH);
    }


    public String getORDINAL_POSITION() {
        return getValue("ORDINAL_POSITION");
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


    public String getSCOPE_CATLOG() {
        return getValue("SCOPE_CATLOG");
    }


    public void setSCOPE_CATLOG(final String SCOPE_CATLOG) {
        setValue("SCOPE_CATLOG", SCOPE_CATLOG);
    }


    public String getSCOPE_SCHEMA() {
        return getValue("SCOPE_SCHEMA");
    }


    public void setSCOPE_SCHEMA(final String SCOPE_SCHEMA) {
        setValue("SCOPE_SCHEMA", SCOPE_SCHEMA);
    }


    public String getSOURCE_DATA_TYPE() {
        return getValue("SOURCE_DATA_TYPE");
    }


    public void setSOURCE_DATA_TYPE(final String SOURCE_DATA_TYPE) {
        setValue("SOURCE_DATA_TYPE", SOURCE_DATA_TYPE);
    }


}

