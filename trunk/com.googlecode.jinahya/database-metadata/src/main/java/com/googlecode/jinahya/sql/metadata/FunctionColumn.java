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
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
//@XmlRootElement
public class FunctionColumn extends ChildEntrySet<Catalog> {


    /**
     * 
     * @param databaseMetaData database metadata
     * @param catalog catalog
     * @throws SQLException if a database access error occurs.
     */
    static void getFunctionColumns(final DatabaseMetaData databaseMetaData,
                                   final Catalog catalog)
        throws SQLException {

        if (catalog.getMetadata().excludes.contains("getFunctionColumns")) {
            return;
        }

        final ResultSet resultSet = databaseMetaData.getFunctionColumns(
            catalog.getValue("TABLE_CAT"), null, null, null);
        try {
            while (resultSet.next()) {
                final FunctionColumn functionColumn =
                    EntrySet.newInstance(FunctionColumn.class, resultSet);
                functionColumn.setParent(catalog);
                catalog.getFunctionColumns().add(functionColumn);
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


    public String getSPECIFIC_NAME() {
        return getValue("SPECIFIC_NAME");
    }


    public void setSPECIFIC_NAME(final String SPECIFIC_NAME) {
        setValue("SPECIFIC_NAME", SPECIFIC_NAME);
    }


}