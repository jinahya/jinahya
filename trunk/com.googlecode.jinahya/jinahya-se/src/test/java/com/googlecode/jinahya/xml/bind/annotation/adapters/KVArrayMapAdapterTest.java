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


import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class KVArrayMapAdapterTest {


    @Test
    public void printXml() throws JAXBException {

        final KVArrayMapAdapterTest type = new KVArrayMapAdapterTest();
        for (int i = 0; i < 10; i++) {
            final Key key = new Key();
            key.setId(i);
            final Value value = new Value();
            value.setKey(key);
            value.setName(Integer.toString(i));
            value.setAge(i);
            type.getValues().put(key, value);
        }

        final JAXBContext context =
            JAXBContext.newInstance(KVArrayMapAdapterTest.class);
        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(type, System.out);
    }


    public Map<Key, Value> getValues() {

        if (values == null) {
            values = new HashMap<Key, Value>();
        }

        return values;
    }


    @XmlJavaTypeAdapter(KVArrayMapAdapter.class)
    private Map<Key, Value> values;


}

