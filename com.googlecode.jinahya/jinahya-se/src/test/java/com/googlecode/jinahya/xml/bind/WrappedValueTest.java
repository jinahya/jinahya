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
import java.util.Objects;
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
 * @param <W> wrapped value type parameter
 * @param <R> raw value type parameter
 */
public abstract class WrappedValueTest<W extends WrappedValue<R>, R> {


    public WrappedValueTest(final Class<W> valueType) {

        super();

        this.valueType = Objects.requireNonNull(valueType, "valueType");
    }


    protected abstract R generateRawValue();


    @Test
    public void testSetRawValue()
        throws InstantiationException, IllegalAccessException {

        final W value = valueType.newInstance();

        value.setRawValue(generateRawValue());
    }


    @Test(invocationCount = 1)
    public void testXml()
        throws JAXBException, InstantiationException, IllegalAccessException,
               IOException {

        final JAXBContext context = JAXBContext.newInstance(valueType);

        final W expected = valueType.newInstance();
        expected.setRawValue(generateRawValue());

        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        marshaller.marshal(expected, baos);
        baos.flush();

        final byte[] bytes = baos.toByteArray();
        System.out.println(new String(
            bytes, (String) marshaller.getProperty(Marshaller.JAXB_ENCODING)));

        final ByteArrayInputStream bais = new ByteArrayInputStream(bytes);

        final Unmarshaller unmarshaller = context.createUnmarshaller();

        final W actual = valueType.cast(unmarshaller.unmarshal(bais));

        Assert.assertEquals(actual, expected);
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


    protected final Class<W> valueType;


}
