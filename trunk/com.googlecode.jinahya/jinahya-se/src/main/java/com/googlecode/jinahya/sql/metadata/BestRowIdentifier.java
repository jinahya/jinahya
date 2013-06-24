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
public class BestRowIdentifier {


    @Label("SCOPE")
    private short scope;


    @Label("COLUMN_NAME")
    private String columnName;


    @Label("DATA_TYPE")
    private int dataType;


    @Label("TYPE_NAME")
    private int typeName;


    @Label("COLUMN_SIZE")
    private int columnSize;


    @Label("BUFFER_LENGTH")
    private int bufferLength;


    @Label("DECIMAL_DIGIT")
    private short decimalDigits;


    @Label("PSEUDO_COLUMN")
    private short pseudoColumn;


}
