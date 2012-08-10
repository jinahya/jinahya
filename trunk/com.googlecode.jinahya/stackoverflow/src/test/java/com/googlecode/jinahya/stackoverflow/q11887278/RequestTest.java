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


package com.googlecode.jinahya.stackoverflow.q11887278;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Random;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestTest {


    @Test(invocationCount = 10)
    public void test() throws JAXBException {

        final Random random = new Random();

        final Request marshallable = new Request();
        if (random.nextBoolean()) {
            marshallable.user = "user";
        }
        if (random.nextBoolean()) {
            marshallable.account = "account";
        }
        if (random.nextBoolean()) {
            marshallable.specifiers = new ArrayList<Specifier>();
            final int count = random.nextInt(3) + 1;
            for (int i = 0; i < count; i++) {
                marshallable.specifiers.add(Specifier.newInstance(random));
            }
        }

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final JAXBContext context = JAXBContext.newInstance(Request.class);

        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(marshallable, baos);

        System.out.println(new String(baos.toByteArray()));

        final Unmarshaller unmarshaller = context.createUnmarshaller();
        final Request unmarshalled = (Request) unmarshaller.unmarshal(
            new ByteArrayInputStream(baos.toByteArray()));

        Assert.assertEquals(unmarshalled.user, marshallable.user);
        Assert.assertEquals(unmarshalled.account, marshallable.account);
        Assert.assertEquals(unmarshalled.specifiers, marshallable.specifiers);
    }


}

