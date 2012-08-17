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


package com.googlecode.jinahya.stackoverflow.q11943487;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ItemsTest {


    @Test
    public void testXml() throws JAXBException, IOException {

        final Items marshallable = new Items();
        for (int i = 0; i < 5; i++) {
            marshallable.getItems().add(Item.newInstance(i, "name" + i));
        }
        for (Item item : marshallable.getItems()) {
            System.out.println("marshallable.item: " + item);
        }

        final JAXBContext context = JAXBContext.newInstance(Items.class);

        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        marshaller.marshal(marshallable, baos);
        baos.flush();


        final Unmarshaller unmarshaller = context.createUnmarshaller();
        final Items unmarshalled = (Items) unmarshaller.unmarshal(
            new ByteArrayInputStream(baos.toByteArray()));
        for (Item item : unmarshalled.getItems()) {
            System.out.println("unmarshalled.item: " + item);
        }
    }
}

