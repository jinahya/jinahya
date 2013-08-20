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
public enum DatabaseMetaDataDeferrability
    implements FieldEnum<DatabaseMetaDataDeferrability, Integer> {


    /**
     * A constant for {@link DatabaseMetaData#importedKeyInitiallyDeferred}.
     */
    importedKeyInitiallyDeferred(
    DatabaseMetaData.importedKeyInitiallyDeferred), //                      5
    /**
     * A constant for {@link DatabaseMetaData#importedKeyInitiallyImmediate}.
     */
    importedKeyInitiallyImmediate(
    DatabaseMetaData.importedKeyInitiallyImmediate), //                     6
    /**
     * A constant for {@link DatabaseMetaData#importedKeyNotDeferrable}.
     */
    importedKeyNotDeferrable(DatabaseMetaData.importedKeyNotDeferrable); // 7


    private DatabaseMetaDataDeferrability(final int fieldValue) {

        this.fieldValue = fieldValue;
    }


    @Override
    public Integer getFieldValue() {

        return fieldValue;
    }


    private final int fieldValue;


}
