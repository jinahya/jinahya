/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
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


import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class StringPackageSetAdapterTest {


    @Test
    public void marshal() throws JAXBException {

        final StringPackageSetAdapterTest expected =
            new StringPackageSetAdapterTest();

        expected.getPackages().add(Package.getPackage("java.lang"));
        expected.getPackages().add(Package.getPackage("javax.accessibility"));
        expected.getPackages().add(Package.getPackage("javax.swing.border"));

        final JAXBContext context =
            JAXBContext.newInstance(StringPackageSetAdapterTest.class);

        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        marshaller.marshal(expected, System.out);
    }


    public Set<Package> getPackages() {

        if (packages == null) {
            packages = new HashSet<Package>();
        }

        return packages;
    }


    @XmlElement
    @XmlJavaTypeAdapter(StringPackageSetAdapter.class)
    private Set<Package> packages;


}

