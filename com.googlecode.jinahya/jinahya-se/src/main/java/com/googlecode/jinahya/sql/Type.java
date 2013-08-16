/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
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


import com.googlecode.jinahya.lang.FieldEnum;
import com.googlecode.jinahya.lang.FieldEnumHelper;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;


/**
 * Enum constants for {@link java.sql.Types}.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public enum Type implements FieldEnum<Type, Integer> {


    ARRAY(Types.ARRAY),
    BIGINT(Types.BIGINT),
    BINARY(Types.BINARY),
    BIT(Types.BIT),
    BLOB(Types.BLOB),
    BOOLEAN(Types.BOOLEAN),
    CHAR(Types.CHAR),
    CLOB(Types.CLOB),
    DATALINK(Types.DATALINK),
    DATE(Types.DATE),
    DECIMAL(Types.DECIMAL),
    DISTINCT(Types.DISTINCT),
    DOUBLE(Types.DOUBLE),
    FLOAT(Types.FLOAT),
    INTEGER(Types.INTEGER),
    JAVA_OBJECT(Types.JAVA_OBJECT),
    LONGNVARCHAR(Types.LONGNVARCHAR),
    LONGVARBINARY(Types.LONGVARBINARY),
    LONGVARCHAR(Types.LONGVARCHAR),
    NCHAR(Types.NCHAR),
    NCLOB(Types.NCLOB),
    NULL(Types.NULL),
    NUMERIC(Types.NUMERIC),
    NVARCHAR(Types.NVARCHAR),
    OTHER(Types.OTHER),
    REAL(Types.REAL),
    REF(Types.REF),
    ROWID(Types.ROWID),
    SMALLINT(Types.SMALLINT),
    SQLXML(Types.SQLXML),
    STRUCT(Types.STRUCT),
    TIME(Types.TIME),
    TIMESTAMP(Types.TIMESTAMP),
    TINYINT(Types.TINYINT),
    VARBINARY(Types.VARBINARY),
    VARCHAR(Types.VARCHAR);


    /**
     * Returns the enum constant mapped to specified {@code fieldValue}.
     *
     * @param fieldValue the field value to map.
     *
     * @return the enum constant for specified {@code fieldValue}.
     */
    public static Type fromFieldValue(final int fieldValue) {

        return FieldEnumHelper.fromFieldValue(Type.class, fieldValue);
    }


    /**
     * Returns all field values.
     *
     * @return all field values.
     */
    public static Integer[] fieldValues() {

        return FieldEnumHelper.fieldValues(Type.class, int.class);
    }


    /**
     * Creates a new instance.
     *
     * @param fieldValue field value.
     */
    private Type(final int fieldValue) {

        this.fieldValue = fieldValue;
    }


    @Override
    public Integer getFieldValue() {

        return fieldValue;
    }


    /**
     * Invokes {@link PreparedStatement#setNull(int, int)} on given
     * {@code preparedStatement} with {@code parameterIndex} and
     * {@code fieldValue}.
     *
     * @param preparedStatement prepared statement
     * @param parameterIndex parameter index
     *
     * @throws SQLException see {@link PreparedStatement#setNull(int, int)}
     */
    public void setNull(final PreparedStatement preparedStatement,
                        final int parameterIndex)
        throws SQLException {

        if (preparedStatement == null) {
            throw new NullPointerException("preparedStatement");
        }

        if (parameterIndex <= 0) {
            throw new IllegalArgumentException(
                "parameterIndex(" + parameterIndex + ") <= 0");
        }

        preparedStatement.setNull(parameterIndex, fieldValue);
    }


    /**
     * Invokes {@link PreparedStatement#setObject(int, java.lang.Object, int)}
     * on given {@code preparedStatement} with
     * {@code parameterIndex}, {@code x}, and {@code fieldValue}.
     *
     * @param preparedStatement prepared statement
     * @param parameterIndex parameter index
     * @param x the object to set
     *
     * @throws SQLException see
     * {@link PreparedStatement#setObject(int, java.lang.Object, int)}
     */
    public void setObject(final PreparedStatement preparedStatement,
                          final int parameterIndex, final Object x)
        throws SQLException {

        if (preparedStatement == null) {
            throw new NullPointerException("preparedStatement");
        }

        if (parameterIndex <= 0) {
            throw new IllegalArgumentException(
                "parameterIndex(" + parameterIndex + ") <= 0");
        }

        preparedStatement.setObject(parameterIndex, x, fieldValue);
    }


    /**
     * Invokes
     * {@link PreparedStatement#setObject(int, java.lang.Object, int, int)} on
     * given {@code preparedStatement} with {@code parameterIndex}, {@code x},
     * and {@code fieldValue}, and {@code scaleOfLength}.
     *
     * @param preparedStatement prepared statement
     * @param parameterIndex parameter index
     * @param x the object to set
     * @param scaleOrLength scale or length
     *
     * @throws SQLException see
     * {@link PreparedStatement#setObject(int, java.lang.Object, int, int)}
     */
    public void setObject(final PreparedStatement preparedStatement,
                          final int parameterIndex, final Object x,
                          final int scaleOrLength)
        throws SQLException {

        if (preparedStatement == null) {
            throw new NullPointerException("preparedStatement");
        }

        if (parameterIndex <= 0) {
            throw new IllegalArgumentException(
                "parameterIndex(" + parameterIndex + ") <= 0");
        }

        preparedStatement.setObject(parameterIndex, x, fieldValue);
    }


    /**
     * field value.
     */
    private final int fieldValue;


}
