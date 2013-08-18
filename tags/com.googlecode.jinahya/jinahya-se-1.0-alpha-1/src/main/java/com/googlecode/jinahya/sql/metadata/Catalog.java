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
import com.googlecode.jinahya.xml.bind.ValuesMapAdapter;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlType(propOrder = {"tableCat", "schemas"})
public class Catalog implements Retrievable {


    public static final Catalog UNNAMED = new Catalog();


    static {
        UNNAMED.tableCat = null;
    }


    public static class Catalogs extends ValuesMapAdapter.AbstractValues<Catalog> {


        @XmlElement
        public List<Catalog> getCatalog() {

            return getValueList();
        }


    }


    public static class CatalogsMapAdapter
        extends ValuesMapAdapter<Catalog.Catalogs, String, Catalog> {


        public CatalogsMapAdapter() {

            super(Catalog.Catalogs.class);
        }


        @Override
        protected String getKey(final Catalog value) {

            return value.getTableCat();
        }


    }


    @Override
    public void retrieve(final DatabaseMetaData databaseMetaData)
        throws SQLException {


        
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
