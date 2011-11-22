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


import java.sql.Types;

import javax.xml.bind.annotation.XmlTransient;


/**
 * Abstract implementation of DatabaseAccessible with <code>idType</code> of
 * <code>String</code>.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlTransient
public abstract class DatabaseAccessibleWithString
    extends AbstractDatabaseAccessible<String> {


    /**
     * Creates a new instance.
     *
     * @param tableName table name
     * @param idColumnName id column name
     */
    public DatabaseAccessibleWithString(final String tableName,
                                        final String idColumnName) {

        this(tableName, idColumnName, Types.VARCHAR);
    }


    /**
     * Creates a new instance.
     *
     * @param tableName table name
     * @param idColumnName id column name
     * @param idType id type
     */
    protected DatabaseAccessibleWithString(final String tableName,
                                           final String idColumnName,
                                           final int idType) {

        super(tableName, idColumnName, idType);
    }


}

