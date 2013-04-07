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
import java.util.Iterator;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class PluralTest {


    @XmlRootElement
    private static class Item {


        public static Item newInstance() {

            return newInstance(ThreadLocalRandom.current());
        }


        public static Item newInstance(final Random random) {

            final Item instance = new Item();

            if (random.nextBoolean()) {
                instance.id = random.nextLong();
            }

            if (random.nextBoolean()) {
                instance.name = "name";
            }

            return instance;
        }


        @Override
        public String toString() {
            return getClass().getSimpleName() + "@" + hashCode()
                   + "?id=" + id
                   + "&name=" + name;
        }


        @Override
        public int hashCode() {
            int hash = 7;
            hash = 97 * hash + Objects.hashCode(this.id);
            hash = 97 * hash + Objects.hashCode(this.name);
            return hash;
        }


        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Item other = (Item) obj;
            if (!Objects.equals(this.id, other.id)) {
                return false;
            }
            if (!Objects.equals(this.name, other.name)) {
                return false;
            }
            return true;
        }


        @XmlAttribute
        private Long id;


        @XmlElement(nillable = true)
        //@XmlValue
        private String name;


    }


    @XmlRootElement
    private static class Items extends Plural<Item> {
    }


    @Test
    public void testEmtpyItems() throws JAXBException, IOException {

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
    public void testXml() throws JAXBException, IOException {

        final JAXBContext context =
            JAXBContext.newInstance(Items.class, Item.class);

        final Items expected = new Items();
        for (int i = 0; i < 5; i++) {
            expected.getSingulars().add(Item.newInstance());
        }
        for (Item item : expected.getSingulars()) {
            System.out.println("marshallable.item: " + item);
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
            System.out.println("unmarshalled.item: " + item);
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
            JAXBContext.newInstance(Items.class, Item.class);

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

