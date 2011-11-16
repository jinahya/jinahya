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
public class Function extends EntrySet {


    public String getFUNCTION_CAT() {
        return getValue("FUNCTION_CAT");
    }


    public void setFUNCTION_CAT(final String FUNCTION_CAT) {
        setValue("FUNCTION_CAT", FUNCTION_CAT);
    }


    public String getFUNCTION_SCHEM() {
        return getValue("FUNCTION_SCHEM");
    }


    public void setFUNCTION_SCHEM(final String FUNCTION_SCHEM) {
        setValue("FUNCTION_SCHEM", FUNCTION_SCHEM);
    }


    public String getFUNCTION_NAME() {
        return getValue("FUNCTION_NAME");
    }


    public void setFUNCTION_NAME(final String FUNCTION_NAME) {
        setValue("FUNCTION_NAME", FUNCTION_NAME);
    }


    public String getREMARKS() {
        return getValue("REMARKS");
    }


    public void setValue(final String REMARKS) {
        setValue("REMARKS", REMARKS);
    }


    public String getFUNCTION_TYPE() {
        return getValue("FUNCTION_TYPE");
    }


    public void setFUNCTION_TYPE(final String FUNCTION_TYPE) {
        setValue("FUNCTION_TYPE", FUNCTION_TYPE);
    }


    public String getSPECIFIC_NAME() {
        return getValue("SPECIFIC_NAME");
    }


    public void setSPECIFIC_NAME(final String SPECIFIC_NAME) {
        setValue("SPECIFIC_NAME", SPECIFIC_NAME);
    }


}

