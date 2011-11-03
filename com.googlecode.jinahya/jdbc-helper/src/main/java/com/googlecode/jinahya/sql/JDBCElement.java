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
     * 
     * @param connection
     * @return
     * @throws SQLException 
     */
    boolean delete(Connection connection) throws SQLException;


    /**
     * 
     * @param connection
     * @return
     * @throws SQLException 
     */
    boolean insert(Connection connection) throws SQLException;


    /**
     * 
     * @param connection
     * @return
     * @throws SQLException 
     */
    boolean select(Connection connection) throws SQLException;


    /**
     * 
     * @param connection
     * @return
     * @throws SQLException 
     */
    boolean update(Connection connection) throws SQLException;


    /**
     * 
     * @param resultSet
     * @param prefix
     * @throws SQLException 
     */
    void read(ResultSet resultSet, String prefix) throws SQLException;


}

