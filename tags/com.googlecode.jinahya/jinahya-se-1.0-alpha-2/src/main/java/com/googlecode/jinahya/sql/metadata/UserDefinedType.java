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


package com.googlecode.jinahya.sql.metadata;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class UserDefinedType {


    @ColumnLabel("TYPE_CAT")
    private String typeCat;


    @ColumnLabel("TYPE_SCHEM")
    private String typeSchem;


    @ColumnLabel("TYPE_NAME")
    private String typeName;


    @ColumnLabel("CLASS_NAME")
    private String className;


    @ColumnLabel("DATA_TYPE")
    private int dataType;


    @ColumnLabel("REMARKS")
    private String remarks;


    @ColumnLabel("BASE_TYPE")
    private short baseType;


}
