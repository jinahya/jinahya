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


import java.sql.ResultSet;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
@XmlType(propOrder = {"grantor", "grantee", "privilege", "isGrantable"})
public class TablePrivilege {


    public static TablePrivilege newInstance(final Table table,
                                             final ResultSet resultSet) {

        final TablePrivilege instance = new TablePrivilege();

        instance.table = table;

        return instance;
    }


    // ------------------------------------------------------------------- table
    public Table getTable() {

        return table;
    }


    public void setTable(Table table) {

        this.table = table;
    }


    // ----------------------------------------------------------------- GRANTOR
    public String getGrantor() {

        return grantor;
    }


    public void setGrantor(String grantor) {

        this.grantor = grantor;
    }


    // ----------------------------------------------------------------- GRANTEE
    public String getGrantee() {

        return grantee;
    }


    public void setGrantee(String grantee) {

        this.grantee = grantee;
    }


    // --------------------------------------------------------------- PRIVILEGE
    public String getPrivilege() {

        return privilege;
    }


    public void setPrivilege(String privilege) {

        this.privilege = privilege;
    }


    // ------------------------------------------------------------ IS_GRANTABLE
    public String getIsGrantable() {

        return isGrantable;
    }


    public void setIsGrantable(String isGrantable) {

        this.isGrantable = isGrantable;
    }


    @ColumnLabel("TABLE_CAT")
    @XmlTransient
    private String tableCat;


    @ColumnLabel("TABLE_SCHEM")
    @XmlTransient
    private String tableSchem;


    @ColumnLabel("TABLE_NAME")
    @XmlTransient
    private String tableName;


    @XmlTransient
    private Table table;


    @ColumnLabel("GRANTOR")
    @XmlElement(nillable = true, required = true)
    private String grantor;


    @ColumnLabel("GRANTEE")
    @XmlElement(required = true)
    private String grantee;


    @ColumnLabel("PRIVILEGE")
    @XmlElement(required = true)
    private String privilege;


    @ColumnLabel("IS_GRANTABLE")
    @XmlElement(nillable = true, required = true)
    private String isGrantable;


}
