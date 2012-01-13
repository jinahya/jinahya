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


import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlAdapter;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlTransient
class EntriesAdapter extends XmlAdapter<Entries, Map<String, Entry>> {


    @Override
    public Entries marshal(final Map<String, Entry> boundType)
        throws Exception {

        final Entries valueType = new Entries();

        valueType.getEntries().addAll(boundType.values());

        return valueType;
    }


    @Override
    public Map<String, Entry> unmarshal(final Entries valueType)
        throws Exception {

        final Map<String, Entry> boundType = new LinkedHashMap<String, Entry>();

        for (Entry entry : valueType.getEntries()) {
            boundType.put(entry.getKey(), entry);
        }

        return boundType;
    }


}

