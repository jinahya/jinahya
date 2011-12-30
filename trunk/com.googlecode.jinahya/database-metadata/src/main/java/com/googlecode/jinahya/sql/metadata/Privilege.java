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
 * @param <P>  parent type parameter
 */
public abstract class Privilege<P extends EntrySet> extends ChildEntrySet<P> {


    public String getTABLE_CAT() {
        return getValue("TABLE_CAT");
    }


    public void setTABLE_CAT(final String TABLE_CAT) {
        setValue("TABLE_CAT", TABLE_CAT);
    }


    public String getTABLE_SCHEM() {
        return getValue("TABLE_SCHEM");
    }


    public void setTABLE_SCHEM(final String TABLE_SCHEM) {
        setValue("TABLE_SCHEM", TABLE_SCHEM);
    }


    public String getTABLE_NAME() {
        return getValue("TABLE_NAME");
    }


    public void setTABLE_NAME(final String TABLE_NAME) {
        setValue("TABLE_NAME", TABLE_NAME);
    }


    public String getGRANTOR() {
        return getValue("GRANTOR");
    }


    public void setGRANTOR(final String GRANTOR) {
        setValue("GRANTOR", GRANTOR);
    }


    public String getGRANTEE() {
        return getValue("GRANTEE");
    }


    public void setGRANTEE(final String GRANTEE) {
        setValue("GRANTEE", GRANTEE);
    }


    public String getPRIVILEGE() {
        return getValue("PRIVILEGE");
    }


    public void setPRIVILEGE(final String PRIVILEGE) {
        setValue("PRIVILEGE", PRIVILEGE);
    }


    public String getIS_GRANTABLE() {
        return getValue("IS_GRANTABLE");
    }


    public void setIS_GRANTABLE(final String IS_GRANTABLE) {
        setValue("IS_GRANTABLE", IS_GRANTABLE);
    }


}

