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


package com.googlecode.jinahya.sql.metadata;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class TableType {


    public static final String TABLE = "TABLE";


    public static final String VIEW = "VIEW";


    public static final String SYSTEM_TABLE = "SYSTEM TABLE";


    public static final String GLOBAL_TEMPORARY = "GLOBAL TEMPORARY";


    public static final String LOCAL_TEMPORARY = "LOCAL TEMPORARY";


    public static final String ALIAS = "ALIAS";


    public static final String SYNONYM = "SYNONYM";


    public String getTableType() {
        return tableType;
    }


    @Label("TABLE_TYPE")
    private String tableType;


}

