/*
 * Copyright 2013 Jin Kwon <onacit at gmail.com>.
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


package com.googlecode.jinahya.xml.bind.test.map;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class Department0Test {


    @Test
    public void printXml()
        throws InstantiationException, IllegalAccessException, JAXBException {

        final Department0 expected = new Department0();

        for (int i = 2; i >= 0; i--) {
            final long id = i;
            final String name = "name" + i;
            final int age = 20 + i;
            final Employee employee = Employee.newInstance(id, name, age);
            expected.getEmployees().put(id, employee);
        }

        final JAXBContext context = JAXBContext.newInstance(Department0.class);

        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        //marshaller.marshal(department, System.out);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        marshaller.marshal(expected, baos);

        final Unmarshaller unmarshaller = context.createUnmarshaller();

        final Department0 actual = (Department0) unmarshaller.unmarshal(
            new ByteArrayInputStream(baos.toByteArray()));

        Assert.assertEquals(actual, expected);
    }


}

