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


import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class MetadataEntriesAdapter
    extends XmlAdapter<MetadataEntries, Map<String, MetadataEntry>> {


    @Override
    public MetadataEntries marshal(final Map<String, MetadataEntry> bound)
        throws Exception {

        final MetadataEntries value = new MetadataEntries();

        value.getEntries().addAll(bound.values());

        return value;
    }


    @Override
    public Map<String, MetadataEntry> unmarshal(final MetadataEntries value)
        throws Exception {

        final Map<String, MetadataEntry> bound =
            new LinkedHashMap<String, MetadataEntry>();

        for (MetadataEntry entry : value.getEntries()) {
            bound.put(entry.getLabel(), entry);
        }

        return bound;
    }


}

