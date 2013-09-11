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


import com.googlecode.jinahya.xml.bind.MapValuesAdapter;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class PrimaryKeyValuesAdapter
    extends MapValuesAdapter<PrimaryKeyValues, String, PrimaryKey> {


    public PrimaryKeyValuesAdapter() {

        super(PrimaryKeyValues.class);
    }


    @Override
    protected String getKey(final PrimaryKey value) {

        return value.getColumnName();
    }


}
