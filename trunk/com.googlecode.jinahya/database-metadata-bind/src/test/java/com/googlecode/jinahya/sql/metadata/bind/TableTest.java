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


package com.googlecode.jinahya.sql.metadata.bind;


import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class TableTest {


    @Test
    public void testXmlOutput() throws JAXBException {

        final Marshaller marshaller =
            DatabaseMetadataBindConstants.JAXB_CONTEXT.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        final Table table = new Table();
        for (int i = 0; i < 5; i++) {
            table.addEntry(Integer.toString(i), Integer.toString(i));
        }

        marshaller.marshal(table, System.out);
    }


}

