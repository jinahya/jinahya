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


/**
 * Binding for data types.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
//@XmlType(propOrder = {"pairs"})
public class DataType extends PairSet {


    /**
     * Retrieves data types.
     *
     * @param databaseMetaData database metadata
     * @param metadata metadata
     * @throws SQLException if a database access error occurs.
     * @see DatabaseMetaData#getTypeInfo()
     */
    static void getTypeInfo(final DatabaseMetaData databaseMetaData,
                            final Metadata metadata)
        throws SQLException {

        if (metadata.excludes.contains("getTypeInfo")) {
            return;
        }

        final ResultSet resultSet = databaseMetaData.getTypeInfo();
        try {
            while (resultSet.next()) {
                final DataType dataType =
                    PairSet.newInstance(DataType.class, resultSet);
                dataType.setMetadata(metadata);
                metadata.getDataTypes().add(dataType);
            }
        } finally {
            resultSet.close();
        }
    }


    public String getTYPE_NAME() {
        return getValue("TYPE_NAME");
    }


    public void setTYPE_NAME(final String TYPE_NAME) {
        setValue("TYPE_NAME", TYPE_NAME);
    }


    public String getDATA_TYPE() {
        return getValue("DATA_TYPE");
    }


    public void setDATA_TYPE(final String DATA_TYPE) {
        setValue("DATA_TYPE", DATA_TYPE);
    }


    public String getPRECISION() {
        return getValue("PRECISION");
    }


    public void setPRECISION(final String PRECISION) {
        setValue("PRECISION", PRECISION);
    }


    public String getLITERAL_PREFIX() {
        return getValue("LITERAL_PREFIX");
    }


    public void setLITERAL_PREFIX(final String LITERAL_PREFIX) {
        setValue("LITERAL_PREFIX", LITERAL_PREFIX);
    }


    public String getLITERAL_SUFFIX() {
        return getValue("LITERAL_SUFFIX");
    }


    public void setLITERAL_SUFFIX(final String LITERAL_SUFFIX) {
        setValue("LITERAL_SUFFIX", LITERAL_SUFFIX);
    }


    public String getCREATE_PARAMS() {
        return getValue("CREATE_PARAMS");
    }


    public void setCREATE_PARAMS(final String CREATE_PARAMS) {
        setValue("CREATE_PARAMS", CREATE_PARAMS);
    }


    public String getNULLABLE() {
        return getValue("NULLABLE");
    }


    public void setNULLABLE(final String NULLABLE) {
        setValue("NULLABLE", NULLABLE);
    }


    public String getCASE_SENSITIVE() {
        return getValue("CASE_SENSITIVE");
    }


    public void setCASE_SENSITIVE(final String CASE_SENSITIVE) {
        setValue("CASE_SENSITIVE", CASE_SENSITIVE);
    }


    public String getSEARCHABLE() {
        return getValue("SEARCHABLE");
    }


    public void setSEARCHABLE(final String SEARCHABLE) {
        setValue("SEARCHABLE", SEARCHABLE);
    }


    public String getUNSIGNED_ATTRIBUTE() {
        return getValue("UNSIGNED_ATTRIBUTE");
    }


    public void setUNSIGNED_ATTRIBUTE(final String UNSIGNED_ATTRIBUTE) {
        setValue("UNSIGNED_ATTRIBUTE", UNSIGNED_ATTRIBUTE);
    }


    public String getFIXED_PREC_SCALE() {
        return getValue("FIXED_PREC_SCALE");
    }


    public void setFIXED_PREC_SCALE(final String FIXED_PREC_SCALE) {
        setValue("FIXED_PREC_SCALE", FIXED_PREC_SCALE);
    }


    public String getAUTO_INCREMENT() {
        return getValue("AUTO_INCREMENT");
    }


    public void setAUTO_INCREMENT(final String AUTO_INCREMENT) {
        setValue("AUTO_INCREMENT", AUTO_INCREMENT);
    }


    public String getLOCAL_TYPE_NAME() {
        return getValue("LOCAL_TYPE_NAME");
    }


    public void setLOCAL_TYPE_NAME(final String LOCAL_TYPE_NAME) {
        setValue("LOCAL_TYPE_NAME", LOCAL_TYPE_NAME);
    }


    public String getMINIMUM_SCALE() {
        return getValue("MINIMUM_SCALE");
    }


    public void setMINIMUM_SCALE(final String MINIMUM_SCALE) {
        setValue("MINIMUM_SCALE", MINIMUM_SCALE);
    }


    public String getMAXIMUM_SCALE() {
        return getValue("MAXIMUM_SCALE");
    }


    public void setMAXIMUM_SCALE(final String MAXIMUM_SCALE) {
        setValue("MAXIMUM_SCALE", MAXIMUM_SCALE);
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


    public String getNUM_PREC_RADIX() {
        return getValue("NUM_PREC_RADIX");
    }


    public void setNUM_PREC_RADIX(final String NUM_PREC_RADIX) {
        setValue("NUM_PREC_RADIX", NUM_PREC_RADIX);
    }


}

