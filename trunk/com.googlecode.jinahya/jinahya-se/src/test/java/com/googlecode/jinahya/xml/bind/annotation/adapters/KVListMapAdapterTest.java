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

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class KVListMapAdapterTest extends AbstractKVMapAdapterTest {


    @Test
    public void printXml() throws JAXBException, IOException {

        printXml(KVListMapAdapterTest.class, this);
    }


    @Override
    protected Map<Key, Value> getValues() {

        if (values == null) {
            values = new HashMap<Key, Value>();
        }

        return values;
    }


    @XmlJavaTypeAdapter(KVListMapAdapter.class)
    private Map<Key, Value> values;


}

