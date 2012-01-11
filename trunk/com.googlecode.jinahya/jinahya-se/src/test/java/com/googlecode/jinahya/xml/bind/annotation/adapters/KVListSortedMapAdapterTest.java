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


package com.googlecode.jinahya.xml.bind.annotation.adapters;


import java.io.IOException;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class KVListSortedMapAdapterTest extends AbstractKVMapAdapterTest {


    @Test
    public void printXml() throws JAXBException, IOException {

        printXml(KVListSortedMapAdapterTest.class, this);
    }


    @Override
    protected Map<Key, Value> getValues() {

        if (values == null) {
            values = new TreeMap<Key, Value>();
        }

        return values;
    }


    @XmlJavaTypeAdapter(KVListSortedMapAdapter.class)
    private SortedMap<Key, Value> values;


}
