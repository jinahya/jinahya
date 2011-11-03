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


package com.googlecode.jinahya.sql;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Interface JDBC mapped element.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <I> id type parameter
 */
public interface JDBCElement<I> {


    /**
     * Returns id.
     *
     * @return id
     */
    I getId();


    /**
     * Sets id.
     *
     * @param id id
     */
    void setId(I id);


    /**
     * Returns id column type.
     *
     * @return 
     */
    int getIdColumnType();


    /**
     * Returns table name.
     *
     * @return table name
     */
    String getTableName();


    /**
     * Return id column name.
     *
     * @return id column name.
     */
    String getIdColumnName();


    /**
     * Deletes this element.
     *
     * @param connection connection
     * @return true if successfully deleted; false otherwise.
     * @throws SQLException if an SQL error occurs.
     */
    boolean delete(Connection connection) throws SQLException;


    /**
     * Inserts this element.
     *
     * @param connection connection
     * @return true if successfully inserted; false otherwise.
     * @throws SQLException 
     */
    boolean insert(Connection connection) throws SQLException;


    /**
     * Selects this instance.
     *
     * @param connection connection
     * @return true if successfully selected; false otherwise.
     * @throws SQLException if an SQL error occurs.
     */
    boolean select(Connection connection) throws SQLException;


    /**
     * Updates this instance.
     *
     * @param connection connection
     * @return true if successfully updated; false otherwise.
     * @throws SQLException if an SQL error occurs.
     */
    boolean update(Connection connection) throws SQLException;


    /**
     * Reads information from given <code>resultSet</code>.
     *
     * @param resultSet result set
     * @param prefix column prefix
     * @throws SQLException if an SQL error occurs.
     */
    void read(ResultSet resultSet, String prefix) throws SQLException;


}

