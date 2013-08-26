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


package com.googlecode.jinahya.sql;


import com.googlecode.jinahya.lang.FieldEnum;
import java.sql.DatabaseMetaData;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public enum DatabaseMetaDataTableIndexType
    implements FieldEnum<DatabaseMetaDataTableIndexType, Short> {


    /**
     * A constant for {@link DatabaseMetaData#tableIndexStatistic}.
     */
    tableIndexStatistic(DatabaseMetaData.tableIndexStatistic), // 0
    /**
     * A constant for {@link DatabaseMetaData#tableIndexClustered}.
     */
    tableIndexClustered(DatabaseMetaData.tableIndexClustered), // 1
    /**
     * A constant for {@link DatabaseMetaData#tableIndexHashed}.
     */
    tableIndexHashed(DatabaseMetaData.tableIndexHashed), // 2
    /**
     * A constant for {@link DatabaseMetaData#tableIndexOther}.
     */
    tableIndexOther(DatabaseMetaData.tableIndexOther); // 3


    private DatabaseMetaDataTableIndexType(final short fieldValue) {

        this.fieldValue = fieldValue;
    }


    @Override
    public Short getFieldValue() {

        return fieldValue;
    }


    private final short fieldValue;


}
