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


import com.googlecode.jinahya.sql.metadata.MethodNamesToOmit;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;


/**
 * DataType wrapper.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class DataTypes extends EntrySets<DataType> {


    /**
     * Retrieved a new instance.
     *
     * @param databaseMetaData database metadata
     * @return a new instance
     * @throws SQLException if a database access error occurs.
     */
    public static DataTypes newInstance(final DatabaseMetaData databaseMetaData)
        throws SQLException {

        final DataTypes instance = new DataTypes();
        getTypeInfo(databaseMetaData, instance.getDataTypes());

        return instance;
    }


    /**
     * Retrieves data types.
     *
     * @param databaseMetaData database metadata
     * @param dataTypes the collection to filled
     * @throws SQLException if a database access error occurs.
     * @see DatabaseMetaData#getTypeInfo()
     */
    public static void getTypeInfo(final DatabaseMetaData databaseMetaData,
                                   final Collection<DataType> dataTypes)
        throws SQLException {

        if (MethodNamesToOmit.instanceContainsName("getTypeInfo")) {
            return;
        }

        final ResultSet resultSet = databaseMetaData.getTypeInfo();
        try {
            while (resultSet.next()) {
                final DataType instance =
                    EntrySet.newInstance(DataType.class, resultSet);
                dataTypes.add(instance);
            }
        } finally {
            resultSet.close();
        }
    }


    /**
     * Creates a new instance.
     */
    public DataTypes() {
        super(DataType.class);
    }


    @XmlElement(name = "dataType")
    public Collection<DataType> getDataTypes() {
        return super.getEntrySets();
    }


}
