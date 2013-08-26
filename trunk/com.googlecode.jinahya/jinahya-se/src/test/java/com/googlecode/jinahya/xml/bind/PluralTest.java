/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
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


import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONMarshaller;
import com.sun.jersey.api.json.JSONUnmarshaller;
import com.sun.jersey.json.impl.BaseJSONMarshaller;
import com.sun.jersey.json.impl.BaseJSONUnmarshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class PluralTest<P extends Plural<S>, S> {


    public PluralTest(final Class<P> pluralType, final Class<S> singularType) {
        super();

        this.pluralType = pluralType;
        this.singularType = singularType;
    }


    protected JAXBContext newJAXBContext() throws JAXBException {

        return JAXBContext.newInstance(pluralType);
    }


    protected P newPluralInstance()
        throws InstantiationException, IllegalAccessException {

        return pluralType.newInstance();
    }


    protected S newSingularInstance()
        throws InstantiationException, IllegalAccessException {

        return singularType.newInstance();
    }


    protected P newPlural() {

        try {
            return newPluralInstance();
        } catch (Exception e) {
            throw new RuntimeException("failed to create new plural", e);
        }
    }


    protected S newSingular() {

        try {
            return newSingularInstance();
        } catch (Exception e) {
            throw new RuntimeException("failed to create new plural", e);
        }
    }


    @Test
    public void testXsd() throws JAXBException, IOException {

        final JAXBContext context = newJAXBContext();

        context.generateSchema(new SchemaOutputResolver() {


            @Override
            public Result createOutput(final String namespaceUri,
                                       final String suggestedFileName)
                throws IOException {

                return new StreamResult(System.out) {


                    @Override
                    public String getSystemId() {
                        return suggestedFileName;
                    }

                };
            }

        });
    }


    protected void testXml(final int singularCount)
        throws JAXBException, IOException {

        final JAXBContext context = newJAXBContext();

        final P expected = newPlural();

        for (int i = 0; i < singularCount; i++) {
            expected.getSingulars().add(newSingular());
        }

        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        marshaller.marshal(expected, baos);
        baos.flush();

        System.out.println(new String(baos.toByteArray(), "UTF-8"));

        final Unmarshaller unmarshaller = context.createUnmarshaller();

        final P actual = pluralType.cast(unmarshaller.unmarshal(
            new ByteArrayInputStream(baos.toByteArray())));
    }


    @Test
    public void testXml() throws JAXBException, IOException {

        testXml(0);

        testXml(ThreadLocalRandom.current().nextInt(128) + 1);
    }


    public void testJson(final int singularCount)
        throws JAXBException, IOException {

        final JAXBContext context = newJAXBContext();

        final JSONConfiguration configuration =
            JSONConfiguration.natural().build();

        final P expected = newPlural();

        for (int i = 0; i < singularCount; i++) {
            expected.getSingulars().add(newSingular());
        }

        final JSONMarshaller marshaller =
            new BaseJSONMarshaller(context, configuration);
        marshaller.setProperty(JSONMarshaller.FORMATTED, Boolean.TRUE);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        marshaller.marshallToJSON(expected, baos);
        baos.flush();

        System.out.println(new String(baos.toByteArray(), "UTF-8"));

        final JSONUnmarshaller unmarshaller =
            new BaseJSONUnmarshaller(context, configuration);

        final P actual = unmarshaller.unmarshalFromJSON(
            new ByteArrayInputStream(baos.toByteArray()), pluralType);
    }


    @Test
    public void testJsonEmpty() throws JAXBException, IOException {

        testJson(0);

        testJson(ThreadLocalRandom.current().nextInt(128) + 1);
    }


    protected final Class<P> pluralType;


    protected final Class<S> singularType;


}
