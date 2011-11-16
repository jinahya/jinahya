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


import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class Property extends Metadata {


    public String getNAME() {
        return getValue("NAME");
    }


    public void setNAME(final String NAME) {
        setValue("NAME", NAME);
    }


    public String getMAX_LEN() {
        return getValue("MAX_LEN");
    }


    public void setMAX_LEN(final String MAX_LEN) {
        setValue("MAX_LEN", MAX_LEN);
    }


    public String getDEFAULT_VALUE() {
        return getValue("DEFAULT_VALUE");
    }


    public void setDEFAULT_VALUE(final String DEFAULT_VALUE) {
        setValue("DEFAULT_VALUE", DEFAULT_VALUE);
    }


    public String getDESCRIPTION() {
        return getValue("DESCRIPTION");
    }


    public void setDESCRIPTION(final String DESCRIPTION) {
        setValue("DESCRIPTION", DESCRIPTION);
    }


}

