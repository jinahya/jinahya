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


package com.googlecode.jinahya.stackoverflow.q11956071;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class InfoTest {


    @Test
    public void testXml() throws JAXBException {

        final JAXBContext context = JAXBContext.newInstance(Info.class);

        final Info marshall = new Info();
        marshall.getMap().put(1, Item.newInstance(1, "value1"));
        marshall.getMap().put(2, Item.newInstance(2, "value2"));

        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        marshaller.marshal(marshall, baos);
        System.out.println(new String(baos.toByteArray()));

        final Unmarshaller unmarshaller = context.createUnmarshaller();

        final Info unmarshal = (Info) unmarshaller.unmarshal(
            new ByteArrayInputStream(baos.toByteArray()));

        for (Item item : unmarshal.getMap().values()) {
            System.out.println(item);
        }
    }


}

