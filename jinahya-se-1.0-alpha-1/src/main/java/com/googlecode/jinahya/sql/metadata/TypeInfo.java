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
public class TypeInfo {


    @Label("TYPE_NAME")
    private String typeName;


    @Label("DATA_TYPE")
    private int dataType;


    @Label("PRECISION")
    private int precision;


    @Label("LITERAL_PREFIX")
    private String leteralPrefix;


    @Label("LITERAL_SUFFIX")
    private String leteralSuffix;


    @Label("CREATE_PARAMS")
    private String createParams;


    @Label("NULLABLE")
    private short nullable;


    @Label("CASE_SENSITIVE")
    private boolean caseSensitive;


    @Label("SEARCHABLE")
    private short searchable;


    @Label("UNSIGNED_ATTRIBUTE")
    private boolean unsignedAttribute;


    @Label("FIXED_PREC_SCALE")
    private boolean fixedPrecScale;


    @Label("AUTO_INCREMENT")
    private boolean autoIncrement;


    @Label("LOCAL_TYPE_NAME")
    private String localTypeName;


    @Label("MINIMUM_SCALE")
    private short minimumScale;


    @Label("MAXIMUM_SCALE")
    private short maximumScale;


    @Label("SQL_DATA_TYPE")
    private int sqlDataType;


    @Label("SQL_DATETIME_SUB")
    private int sqlDatetimeSub;


    @Label("NUM_PREC_RADIX")
    private int numPrecRadix;


}
