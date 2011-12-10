

package com.googlecode.jinahya.jvms.cff;


import java.io.DataInputStream;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.testng.annotations.Test;



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
/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ClassFileTest {


    private static void print(final Classfilea classFile)
        throws JAXBException {

        final JAXBContext context = JAXBContext.newInstance(
            "com.googlecode.jinahya.jvms.cff"
            + ":com.googlecode.jinahya.jvms.cff.constant");

        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        marshaller.marshal(classFile, System.out);
    }


    @Test
    public static void testNewInstance() throws IOException, JAXBException {

        final DataInputStream dis = new DataInputStream(
            ClassFileTest.class.getResourceAsStream(
            ClassFileTest.class.getSimpleName() + ".class"));
        try {
            final Classfilea instance = Classfilea.readInstance(dis);
            print(instance);
        } finally {
            dis.close();
        }
    }


}

