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


import com.googlecode.jinahya.sql.metadata.Schema.SchemasMapAdapter;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlType(propOrder = {"tableCat", "schemas"})
public class Catalog {


    public static final Catalog UNNAMED = new Catalog();


    static {
        UNNAMED.tableCat = null;
    }


    public String getTableCat() {
        return tableCat;
    }


    public Map<String, Schema> getSchemas() {

        if (schemas == null) {
            schemas = new HashMap<String, Schema>();
        }

        return schemas;
    }


    @Label("TABLE_CAT")
    @XmlElement(nillable = true, required = true)
    private String tableCat;


    @XmlElement(required = true)
    @XmlJavaTypeAdapter(SchemasMapAdapter.class)
    private Map<String, Schema> schemas;


}
