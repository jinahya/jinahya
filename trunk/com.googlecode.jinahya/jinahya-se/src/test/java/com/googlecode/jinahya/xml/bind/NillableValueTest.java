/*
 * Copyright 2012 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.xml.bind;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <V> value type
 * @param <R> raw type
 */
public abstract class NillableValueTest<V extends NillableValue<R>, R> {


    public NillableValueTest(final Class<V> valueType) {
        super();

        if (valueType == null) {
            throw new IllegalArgumentException("null valueType");
        }

        this.valueType = valueType;
    }


    protected abstract R generateRaw();


    @Test
    public void testSetRaw()
        throws InstantiationException, IllegalAccessException {

        valueType.newInstance().setRaw(generateRaw());
    }


    @Test
    public void testXml()
        throws JAXBException, InstantiationException, IllegalAccessException,
               IOException {

        final JAXBContext context = JAXBContext.newInstance(valueType);


        final V marshallable = valueType.newInstance();
        marshallable.setRaw(generateRaw());

        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        marshaller.marshal(marshallable, baos);
        baos.flush();

        final byte[] bytes = baos.toByteArray();
        System.out.println(new String(bytes, "UTF-8"));

        final ByteArrayInputStream bais = new ByteArrayInputStream(bytes);

        final Unmarshaller unmarshaller = context.createUnmarshaller();

        final V unmarshalled = valueType.cast(unmarshaller.unmarshal(bais));

        Assert.assertEquals(unmarshalled, marshallable);
    }


    @Test
    public void testXsd() throws JAXBException, IOException {


        final JAXBContext context = JAXBContext.newInstance(valueType);

        context.generateSchema(new SchemaOutputResolver() {
            @Override
            public Result createOutput(final String namespaceUri,
                                       final String suggestedFileName)
                throws IOException {

                return new StreamResult(System.out) {
                    @Override
                    public String getSystemId() {
                        return "noid";
                    }


                };
            }


        });
    }


    protected final Class<V> valueType;


}

