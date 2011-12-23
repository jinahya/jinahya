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
 * Binding for ExportedKey.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class PrimaryKey extends TableChild {
//extends ChildEntrySet<Table> {


    public static PrimaryKey newInstance(final ResultSet resultSet)
        throws SQLException {

        return newInstance(PrimaryKey.class, resultSet);
    }


    /**
     * Returns the value of 'PKTABLE_CAT' entry.
     *
     * @return 'PKTABLE_CAT' entry value.
     */
    public String getPKTABLE_CAT() {
        return getValue("PKTABLE_CAT");
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


    public String getKEY_SEQ() {
        return getValue("KEY_SEQ");
    }


    public void setKEY_SEQ(final String KEY_SEQ) {
        setValue("KEY_SEQ", KEY_SEQ);
    }


    public String getPK_NAME() {
        return getValue("PK_NAME");
    }


    public void setPK_NAME(final String PK_NAME) {
        setValue("PK_NAME", PK_NAME);
    }


}

