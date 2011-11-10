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


package com.googlecode.jinahya.sql.metadata.bind;


import javax.xml.bind.annotation.XmlElement;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class Privilege {


    public String getGrantee() {
        return grantee;
    }


    public void setGrantee(String grantee) {
        this.grantee = grantee;
    }


    public String getGrantor() {
        return grantor;
    }


    public void setGrantor(String grantor) {
        this.grantor = grantor;
    }


    public String getIsGrantable() {
        return isGrantable;
    }


    public void setIsGrantable(String isGrantable) {
        this.isGrantable = isGrantable;
    }


    public String getPrivilege() {
        return privilege;
    }


    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }


    public String getTableCategory() {
        return tableCategory;
    }


    public void setTableCategory(String tableCategory) {
        this.tableCategory = tableCategory;
    }


    public String getTableName() {
        return tableName;
    }


    public void setTableName(String tableName) {
        this.tableName = tableName;
    }


    public String getTableSchema() {
        return tableSchema;
    }


    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }


    @XmlElement(required = true, nillable = true)
    private String tableCategory;


    @XmlElement(required = true, nillable = true)
    private String tableSchema;


    @XmlElement(required = true)
    private String tableName;


    @XmlElement(required = true, nillable = true)
    private String grantor;


    @XmlElement(required = true)
    private String grantee;


    @XmlElement(required = true)
    private String privilege;


    @XmlElement(required = true, nillable = true)
    private String isGrantable;


}

