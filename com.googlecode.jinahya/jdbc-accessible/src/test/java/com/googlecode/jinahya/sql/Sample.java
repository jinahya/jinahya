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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Entity()
@Table(name = Sample.TABLE_NAME)//"SAMPLE")
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "sample")
@XmlType(propOrder = {"name", "age"})
public class Sample extends DbAccessibleWithLong {


    public static final String TABLE_NAME = "SAMPLE";


    public static final String ID_COLUMN_NAME = "ID";


    public static final String QUALIIED_ID_COLUMN_NAME = "SAMPLE_ID";


    public static final String ID_SEQUENCE_NAME =
        QUALIIED_ID_COLUMN_NAME + "_SEQ";


    /**
     * Creates a new instance.
     */
    public Sample() {
        super(TABLE_NAME, ID_COLUMN_NAME);
    }


    @Override
    public boolean insert(final Connection connection) throws SQLException {
        final PreparedStatement preparedStatement = connection.prepareStatement(
            "INSERT INO " + TABLE_NAME + " (" + ID_COLUMN_NAME + ", NAME, AGE)"
            + " VALUES (?, ?, ?)");
        try {
            int parameterIndex = 0;

            preparedStatement.setLong(++parameterIndex, getId());

            preparedStatement.setString(++parameterIndex, name);
            preparedStatement.setInt(++parameterIndex, age);

            return preparedStatement.executeUpdate() == 1;

        } finally {
            preparedStatement.close();
        }
    }


    @Override
    public boolean update(final Connection connection) throws SQLException {
        final PreparedStatement preparedStatement = connection.prepareStatement(
            "UPDATE " + TABLE_NAME + " SET NAME = ?, AGE = ?"
            + " WHERE " + ID_COLUMN_NAME + " = ?");
        try {
            int parameterIndex = 0;

            preparedStatement.setString(++parameterIndex, name);
            preparedStatement.setInt(++parameterIndex, age);

            preparedStatement.setLong(++parameterIndex, getId());

            return preparedStatement.executeUpdate() == 1;

        } finally {
            preparedStatement.close();
        }
    }


    @Override
    public void read(final ResultSet resultSet, final String prefix)
        throws SQLException {

        setId(resultSet.getLong(prefix + idColumnName));

        name = resultSet.getString(prefix + "NAME");
        age = getInt(resultSet, prefix + "AGE");
    }


    @Id
    @Column(name = ID_COLUMN_NAME)
    @XmlAttribute(required = true)
    @Override
    public Long getId() {
        return super.getId();
    }


    public String getName() {
        return name;
    }


    public void setName(final String name) {
        this.name = name;
    }


    public Integer getAge() {
        return age;
    }


    public void setAge(final Integer age) {
        this.age = age;
    }


    @Basic(optional = false)
    @Column(nullable = false)
    @XmlElement(required = true, nillable = false)
    @XmlSchemaType(name = "token")
    private String name;


    @Basic()
    @Column()
    @XmlElement()
    @XmlSchemaType(name = "nonNegativeInteger")
    private Integer age;


}

