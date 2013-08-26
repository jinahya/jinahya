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
public enum DatabaseMetaDataTypeNullability
    implements FieldEnum<DatabaseMetaDataTypeNullability, Integer> {


    /**
     * A constant for {@link DatabaseMetaData#typeNoNulls}.
     */
    typeNoNulls(DatabaseMetaData.typeNoNulls), // 0
    /**
     * A constant for {@link DatabaseMetaData#typeNullable}.
     */
    typeNullable(DatabaseMetaData.typeNullable), // 1
    /**
     * A constant for {@link DatabaseMetaData#typeNullableUnknown}.
     */
    typeNullableUnknown(DatabaseMetaData.typeNullableUnknown); // 2


    private DatabaseMetaDataTypeNullability(final int fieldValue) {

        this.fieldValue = fieldValue;
    }


    @Override
    public Integer getFieldValue() {

        return fieldValue;
    }


    private final int fieldValue;


}
