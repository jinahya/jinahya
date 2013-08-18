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


    /**
     * Constant for {@link Types#LONGVARCHAR}.
     */
    LONGNVARCHAR(Types.LONGNVARCHAR), //  -16
    /**
     * Constant for {@link Types#NCHAR}.
     */
    NCHAR(Types.NCHAR), //                -15
    // -------------------------------------------------------------------------
    /**
     * Constant for {@link Types#NVARCHAR}.
     */
    NVARCHAR(Types.NVARCHAR), //           -9
    /**
     * Constant for {@link Types#ROWID}.
     */
    ROWID(Types.ROWID), //                 -8
    /**
     * Constant for {@link Types#BIT}.
     */
    BIT(Types.BIT), //                     -7
    /**
     * Constant for {@link Types#TINYINT}.
     */
    TINYINT(Types.TINYINT), //             -6
    /**
     * Constant for {@link Types#BIGINT}.
     */
    BIGINT(Types.BIGINT), //               -5
    /**
     * Constant for {@link Types#LONGVARBINARY}.
     */
    LONGVARBINARY(Types.LONGVARBINARY), // -4
    /**
     * Constant for {@link Types#VARBINARY}.
     */
    VARBINARY(Types.VARBINARY), //         -3
    /**
     * Constant for {@link Types#BINARY}.
     */
    BINARY(Types.BINARY), //               -2
    /**
     * Constant for {@link Types#LONGVARCHAR}.
     */
    LONGVARCHAR(Types.LONGVARCHAR), //     -1
//    /**
//     * Constant for {@link Types#NULL}.
//     */
//    NULL(Types.NULL), //                    0
    /**
     * Constant for {@link Types#CHAR}.
     */
    CHAR(Types.CHAR), //                    1
    /**
     * Constant for {@link Types#NUMERIC}.
     */
    NUMERIC(Types.NUMERIC),//               2
    /**
     * Constant for {@link Types#DECIMAL}.
     */
    DECIMAL(Types.DECIMAL), //              3
    /**
     * Constant for {@link Types#INTEGER}.
     */
    INTEGER(Types.INTEGER), //              4
    /**
     * Constant for {@link Types#SMALLINT}.
     */
    SMALLINT(Types.SMALLINT), //            5
    /**
     * Constant for {@link Types#FLOAT}.
     */
    FLOAT(Types.FLOAT), //                  6
    /**
     * Constant for {@link Types#REAL}.
     */
    REAL(Types.REAL), //                    7
    /**
     * Constant for {@link Types#DOUBLE}.
     */
    DOUBLE(Types.DOUBLE), //                8
    // -------------------------------------------------------------------------
    /**
     * Constant for {@link Types#VARCHAR}.
     */
    VARCHAR(Types.VARCHAR), //             12
    // -------------------------------------------------------------------------
    /**
     * Constant for {@link Types#BOOLEAN}.
     */
    BOOLEAN(Types.BOOLEAN), //             16
    // -------------------------------------------------------------------------
    /**
     * Constant for {@link Types#DATALINK}.
     */
    DATALINK(Types.DATALINK), //           70
    // -------------------------------------------------------------------------
    /**
     * Constant for {@link Types#DATE}.
     */
    DATE(Types.DATE), //                   91
    /**
     * Constant for {@link Types#TIME}.
     */
    TIME(Types.TIME), //                   92
    /**
     * Constant for {@link Types#TIMESTAMP}.
     */
    TIMESTAMP(Types.TIMESTAMP), //         93
    // -------------------------------------------------------------------------
//    /**
//     * Constant for {@link Types#OTHER}.
//     */
//    OTHER(Types.OTHER), //               1111
    // -------------------------------------------------------------------------
    /**
     * Constant for {@link Types#JAVA_OBJECT}.
     */
    JAVA_OBJECT(Types.JAVA_OBJECT), //   2000
    /**
     * Constant for {@link Types#DISTINCT}.
     */
    DISTINCT(Types.DISTINCT), //         2001
    /**
     * Constant for {@link Types#STRUCT}.
     */
    STRUCT(Types.STRUCT), //             2002
    /**
     * Constant for {@link Types#ARRAY}.
     */
    ARRAY(Types.ARRAY), //               2003
    /**
     * Constant for {@link Types#BLOB}
     */
    BLOB(Types.BLOB), //                 2004
    /**
     * Constant for {@link Types#CLOB}.
     */
    CLOB(Types.CLOB), //                 2005
    /**
     * Constant for {@link Types#REF}.
     */
    REF(Types.REF), //                   2006

    /**
     * Constant for {@link Types#SQLXML}.
     */
    SQLXML(Types.SQLXML), //             2009
    // -------------------------------------------------------------------------
    /**
     * Constant for {@link Types#NCLOB}.
     */
    NCLOB(Types.NCLOB); //               2011


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

