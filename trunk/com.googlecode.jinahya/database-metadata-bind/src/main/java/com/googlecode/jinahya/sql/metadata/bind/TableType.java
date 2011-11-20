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
 * Binding for TableType.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class TableType extends EntrySet {


    /**
     * Returns the value of <code>TABLE_TYPE</code> entry.
     *
     * @return TABLE_TYPE
     */
    public String getTABLE_TYPE() {
        return getValue("TABLE_TYPE");
    }


    /**
     * Sets the value of 'TABLE_TYPE' entry.
     *
     * @param TABLE_TYPE TABLE_TYPE
     */
    public void setTABLE_TYPE(final String TABLE_TYPE) {
        setValue("TABLE_TYPE", TABLE_TYPE);
    }


}

