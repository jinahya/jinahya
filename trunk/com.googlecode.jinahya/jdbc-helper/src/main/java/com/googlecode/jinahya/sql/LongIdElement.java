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


/**
 * DBElement with Long id.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class LongIdElement extends AbstractDBElement<Long> {


    /**
     * Creates a new instance.
     *
     * @param tableName table name
     * @param idColumnName id column name
     */
    public LongIdElement(final String tableName,
                           final String idColumnName) {

        super(tableName, idColumnName, Types.BIGINT);
    }


}

