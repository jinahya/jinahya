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
@XmlType(propOrder={"entries", "privileges"})
public class Column extends EntrySet {


    public String getTABLE_CAT() {
        return getValue("TABLE_CAT");
    }


    public void setTABLE_CAT(final String TABLE_CAT) {
        setValue("TABLE_CAT", TABLE_CAT);
    }


    public String getTABLE_SCHEM() {
        return getValue("TABLE_SCHEM");
    }


    public void setTABLE_SCHEM(final String TABLE_SCHEM) {
        setValue("TABLE_SCHEM", TABLE_SCHEM);
    }


    public String getTABLE_NAME() {
        return getValue("TABLE_NAME");
    }


    public void setTABLE_NAME(final String TABLE_NAME) {
        setValue("TABLE_NAME", TABLE_NAME);
    }


    public String getCOLUMN_NAME() {
        return getValue("COLUMN_NAME");
    }


    public void setCOLUMN_NAME(final String COLUMN_NAME) {
        setValue("COLUMN_NAME", COLUMN_NAME);
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


    public String getCOLUMN_SIZE() {
        return getValue("COLUMN_SIZE");
    }


    public void setCOLUMN_SIZE(final String COLUMN_SIZE) {
        setValue("COLUMN_SIZE", COLUMN_SIZE);
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


    public String getCOLUMN_DEF() {
        return getValue("COLUMN_DEF");
    }


    public void setCOLUMN_DEF(final String COLUMN_DEF) {
        setValue("COLUMN_DEF", COLUMN_DEF);
    }


    public String getCHAR_OCTET_LENGTH() {
        return getValue("CHAR_OCTET_LENGTH");
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


    public String getSCOPE_TABLE() {
        return getValue("SCOPE_TABLE");
    }


    public void setSCOPE_TABLE(final String SCOPE_TABLE) {
        setValue("SCOPE_TABLE", SCOPE_TABLE);
    }


    public String geSOURCE_DATA_TYPE() {
        return getValue("SOURCE_DATA_TYPE");
    }


    public void setSOURCE_DATA_TYPE(final String SOURCE_DATA_TYPE) {
        setValue("SOURCE_DATA_TYPE", SOURCE_DATA_TYPE);
    }


    public String getIS_AUTOINCREMENT() {
        return getValue("IS_AUTOINCREMENT");
    }


    public void setIS_AUTOINCREMENT(final String IS_AUTOINCREMENT) {
        setValue("IS_AUTOINCREMENT", IS_AUTOINCREMENT);
    }


    /**
     * Returns privileges.
     *
     * @return privileges
     */
    public Collection<ColumnPrivilege> getPrivileges() {

        if (privileges == null) {
            privileges = new ArrayList<ColumnPrivilege>();
        }

        return privileges;
    }


    @XmlElement(name = "privilege")
    @XmlElementWrapper(required = true, nillable = true)
    private Collection<ColumnPrivilege> privileges;


}

