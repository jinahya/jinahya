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
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class PluralTest {


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


        @XmlAttribute
        private Long id;


        @XmlValue
        private String name;


    }


    @XmlRootElement
    private static class Items extends Plural<Item> {


        @XmlElement(name = "item", nillable = true)
        public Collection<Item> getItems() {
            return getSingulars();
        }


    }


    @Test
    public void testXml() throws JAXBException, IOException {

        final JAXBContext context = JAXBContext.newInstance(Items.class);

        final Items marshallable = new Items();
        for (int i = 0; i < 5; i++) {
            marshallable.getItems().add(Item.newInstance());
        }
        for (Item item : marshallable.getItems()) {
            System.out.println("marshallable.item: " + item);
        }

        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        marshaller.marshal(marshallable, baos);
        baos.flush();

        System.out.println(new String(baos.toByteArray(), "UTF-8"));

        final Unmarshaller unmarshaller = context.createUnmarshaller();

        final Items unmarshalled = (Items) unmarshaller.unmarshal(
            new ByteArrayInputStream(baos.toByteArray()));

        for (Item item : unmarshalled.getItems()) {
            System.out.println("unmarshalled.item: " + item);
        }

    }


}

