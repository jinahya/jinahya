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


import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class TablePrivilege {


    @XmlTransient
    private Table table;


    @Label("TABLE_CAT")
    private String tableCat;


    @Label("TABLE_SCHEM")
    private String tableSchem;


    @Label("TABLE_NAME")
    private String tableName;


    @Label("GRANTOR")
    private String grantor;


    @Label("GRANTEE")
    private String grantee;


    @Label("PRIVILEGE")
    private String privilege;


    @Label("IS_GRANTABLE")
    private String isGrantable;


}
