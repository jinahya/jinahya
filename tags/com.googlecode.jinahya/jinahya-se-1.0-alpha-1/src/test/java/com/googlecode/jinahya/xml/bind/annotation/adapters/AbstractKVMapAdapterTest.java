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


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;

import javax.xml.transform.stream.StreamSource;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public abstract class AbstractKVMapAdapterTest {


    protected static <T extends AbstractKVMapAdapterTest> void printXml(
        final Class<T> type, final T toBeMarshalled)
        throws JAXBException, IOException {

        for (int i = 0; i < 10; i++) {
            final Key key = new Key();
            key.setId(i);
            final Value value = new Value();
            value.setKey(key);
            value.setName(Integer.toString(i));
            value.setAge(i);
            toBeMarshalled.getValues().put(key, value);
        }

        final JAXBContext context =
            JAXBContext.newInstance(AbstractKVMapAdapterTest.class, type);
        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        marshaller.marshal(toBeMarshalled, baos);
        baos.flush();

        final byte[] bytes = baos.toByteArray();
        System.out.write(bytes);
        System.out.println();

        final Unmarshaller unmarshaller = context.createUnmarshaller();
        final T unmarshalled = unmarshaller.unmarshal(
            new StreamSource(new ByteArrayInputStream(bytes)), type).getValue();
        for (Value value : unmarshalled.getValues().values()) {
            System.out.println(value);
        }
    }


    protected abstract Map<Key, Value> getValues();


}

