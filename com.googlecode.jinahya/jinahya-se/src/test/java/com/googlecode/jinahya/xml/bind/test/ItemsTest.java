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


package com.googlecode.jinahya.xml.bind.test;


import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONMarshaller;
import com.sun.jersey.api.json.JSONUnmarshaller;
import com.sun.jersey.json.impl.BaseJSONMarshaller;
import com.sun.jersey.json.impl.BaseJSONUnmarshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
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
 */
public class ItemsTest {


    @Test
    public void testEmtpyItemsXml() throws JAXBException, IOException {

        final JAXBContext context =
            JAXBContext.newInstance(Items.class, Item.class);

        final Items expected = new Items();

        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        marshaller.marshal(expected, baos);
        baos.flush();

        System.out.println(new String(baos.toByteArray(), "UTF-8"));

        final Unmarshaller unmarshaller = context.createUnmarshaller();

        final Items actual = (Items) unmarshaller.unmarshal(
            new ByteArrayInputStream(baos.toByteArray()));
    }


    @Test
    public void testEmtpyItemsJson() throws JAXBException, IOException {

        final JAXBContext context =
            JAXBContext.newInstance(Items.class, Item.class);

        final JSONConfiguration configuration =
            JSONConfiguration.natural().build();

        final Items expected = new Items();

        final JSONMarshaller marshaller =
            new BaseJSONMarshaller(context, configuration);
        marshaller.setProperty(JSONMarshaller.FORMATTED, Boolean.TRUE);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        marshaller.marshallToJSON(expected, baos);
        baos.flush();

        System.out.println(new String(baos.toByteArray(), "UTF-8"));

        final JSONUnmarshaller unmarshaller =
            new BaseJSONUnmarshaller(context, configuration);

        final Items actual = (Items) unmarshaller.unmarshalFromJSON(
            new ByteArrayInputStream(baos.toByteArray()), Items.class);
    }


    @Test
    public void testXml() throws JAXBException, IOException {

        final JAXBContext context =
            JAXBContext.newInstance(Items.class, Item.class);

        final Items expected = new Items();
        for (int i = 0; i < 5; i++) {
            expected.getSingulars().add(Item.newInstance());
        }
        for (Item item : expected.getSingulars()) {
            System.out.println("expected.item: " + item);
        }

        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        marshaller.marshal(expected, baos);
        baos.flush();

        System.out.println(new String(baos.toByteArray(), "UTF-8"));

        final Unmarshaller unmarshaller = context.createUnmarshaller();

        final Items actual = (Items) unmarshaller.unmarshal(
            new ByteArrayInputStream(baos.toByteArray()));

        for (Item item : actual.getSingulars()) {
            System.out.println("actual.item: " + item);
        }

        final Iterator<Item> actualItems = actual.getSingulars().iterator();
        final Iterator<Item> expectedItems = expected.getSingulars().iterator();

        while (actualItems.hasNext()) {
            Assert.assertEquals(actualItems.next(), expectedItems.next());
        }
    }


    @Test(enabled = false)
    public void testJson() throws JAXBException, IOException {

//        final JAXBContext context =
//            JAXBContext.newInstance(Items.class, Item.class);
        final JAXBContext context =
            JAXBContext.newInstance("com.googlecode.jinahya.xml.bind.test");

        final JSONConfiguration configuration =
            JSONConfiguration.natural().build();

        final Items expected = new Items();
        for (int i = 0; i < 5; i++) {
            expected.getSingulars().add(Item.newInstance());
        }
        for (Item item : expected.getSingulars()) {
            System.out.println("expected.item: " + item);
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

//        final Items actual = unmarshaller.unmarshalFromJSON(
//            new ByteArrayInputStream(baos.toByteArray()), Items.class);
        final Items actual = unmarshaller.unmarshalJAXBElementFromJSON(
            new ByteArrayInputStream(baos.toByteArray()), Items.class)
            .getValue();

        for (Item item : actual.getSingulars()) {
            System.out.println("actual.item: " + item);
        }

        final Iterator<Item> actualItems = actual.getSingulars().iterator();
        final Iterator<Item> expectedItems = expected.getSingulars().iterator();

        while (actualItems.hasNext()) {
            Assert.assertEquals(actualItems.next(), expectedItems.next());
        }
    }


    @Test
    public void testXsd() throws JAXBException, IOException {

        final JAXBContext context =
            JAXBContext.newInstance(Items.class);

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


}

