/*
 * Copyright 2013 Jin Kwon <onacit at gmail.com>.
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


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class Retrievables {


    public static <R extends Retrievable> List<R> retrieve(
        final Class<R> type, final DatabaseMetaData databaseMetaData,
        final Method method, final Object... args)
        throws SQLException {

        try {
            final ResultSet resultSet =
                (ResultSet) method.invoke(databaseMetaData, args);
            try {
                return retrieve(type, resultSet);
            } finally {
                resultSet.close();
            }
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(iae);
        } catch (InvocationTargetException ite) {
            throw new RuntimeException(ite);
        }
    }


    public static <R extends Retrievable> List<R> retrieve(
        final Class<R> type, final ResultSet resultSet)
        throws SQLException {

        final List<R> list = new ArrayList<R>();

        while (resultSet.next()) {
            try {
                list.add(retrieve(type, type.newInstance(), resultSet));
            } catch (InstantiationException ie) {
                throw new RuntimeException(ie);
            } catch (IllegalAccessException iae) {
                throw new RuntimeException(iae);
            }
        }

        return list;
    }


    public static <R extends Retrievable> R retrieve(
        final Class<R> type, final R retrievable, final ResultSet resultSet)
        throws SQLException {

        return retrievable;
    }


    private Retrievables() {
        super();
    }


}
