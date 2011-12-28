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


/**
 * Binding for column privileges.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ColumnPrivilege extends Privilege<Table> {


    public static ColumnPrivilege newInstance(final ResultSet resultSet)
        throws SQLException {

        return newInstance(ColumnPrivilege.class, resultSet);
    }


    /**
     * Returns the value of 'COLUMN_NAME' entry.
     *
     * @return 'COLUMN_NAME' entry value
     */
    public String getCOLUMN_NAME() {
        return getValue("COLUMN_NAME");
    }


    /**
     * Sets the value of 'COLUMN_NAME' entry.
     *
     * @param COLUMN_NAME 'COLUMN_NAME' entry value
     */
    public void setCOLUMN_NAME(final String COLUMN_NAME) {
        setValue("COLUMN_NAME", COLUMN_NAME);
    }


    public Table getTable() {
        return super.getParent();
    }


    public void setTable(final Table table) {
        setParent(table);
    }


}

