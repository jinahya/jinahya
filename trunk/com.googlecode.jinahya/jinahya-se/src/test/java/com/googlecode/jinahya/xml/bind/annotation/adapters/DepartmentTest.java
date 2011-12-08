/*
 * Copyright 2011 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.xml.bind.annotation.adapters;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javax.xml.transform.stream.StreamSource;

import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class DepartmentTest {


    /** the JAXBContext. */
    private static final JAXBContext JAXB_CONTEXT;


    static {
        try {
            JAXB_CONTEXT = JAXBContext.newInstance(
                Department.class, Crowd.class);
        } catch (JAXBException jaxbe) {
            throw new InstantiationError(jaxbe.getMessage());
        }
    }


    @Test(enabled = false)
    public byte[] marshal() throws JAXBException, IOException {

        final Department department = Department.newInstance(
            "IT",
            Staff.newInstance(0, "Roy Trenneman"),
            Staff.newInstance(1, "Maurice Moss"),
            Staff.newInstance(2, "Jen Barber"));

        final Marshaller marshaller = JAXB_CONTEXT.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        marshaller.marshal(department, baos);
        baos.flush();

        final byte[] bytes = baos.toByteArray();

        final String charsetName =
            (String) marshaller.getProperty(Marshaller.JAXB_ENCODING);
        System.out.println(new String(bytes, charsetName));

        return bytes;
    }


    @Test
    public void unmarshal() throws JAXBException, IOException {

        final ByteArrayInputStream bais = new ByteArrayInputStream(marshal());

        final Unmarshaller unmarshaller = JAXB_CONTEXT.createUnmarshaller();
        final Department department = unmarshaller.unmarshal(
            new StreamSource(bais), Department.class).getValue();

        System.out.println("department.name: " + department.getName());
        for (Staff person : department.getCrowd().values()) {
            System.out.println("department.staff: " + person);
        }
    }


}

