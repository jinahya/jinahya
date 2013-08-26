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


package com.googlecode.jinahya.xml.bind;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public class StringPackageSetAdapterTest {


    private static final Logger LOGGER =
        Logger.getLogger(StringPackageSetAdapter.class.getName());


    static {
        LOGGER.setLevel(Level.WARNING);
    }


    @Test
    public void testEmpty() throws JAXBException {

        final StringPackageListAdapterTest expected =
            new StringPackageListAdapterTest();

        final JAXBContext context =
            JAXBContext.newInstance(StringPackageListAdapterTest.class);

        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        marshaller.marshal(expected, baos);

        final Unmarshaller unmarshaller = context.createUnmarshaller();

        final StringPackageListAdapterTest actual =
            (StringPackageListAdapterTest) unmarshaller.unmarshal(
            new ByteArrayInputStream(baos.toByteArray()));

        Assert.assertEquals(actual, expected);
    }


    @Test
    public void testNonEmpty()
        throws JAXBException, UnsupportedEncodingException {

        final StringPackageListAdapterTest expected =
            new StringPackageListAdapterTest();

        expected.getPackages().addAll(Arrays.asList(Package.getPackages()));

        final JAXBContext context =
            JAXBContext.newInstance(StringPackageListAdapterTest.class);

        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        marshaller.marshal(expected, baos);

        final String charsetName =
            (String) marshaller.getProperty(Marshaller.JAXB_ENCODING);
        System.out.println(new String(baos.toByteArray(), charsetName));

        final Unmarshaller unmarshaller = context.createUnmarshaller();

        final StringPackageListAdapterTest actual =
            (StringPackageListAdapterTest) unmarshaller.unmarshal(
            new ByteArrayInputStream(baos.toByteArray()));

        Assert.assertEquals(actual, expected);
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.packages != null
                            ? this.packages.hashCode() : 0);
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
        final StringPackageSetAdapterTest other =
            (StringPackageSetAdapterTest) obj;
        if (this.packages != other.packages
            && (this.packages == null
                || !this.packages.equals(other.packages))) {
            return false;
        }
        return true;
    }


    public Set<Package> getPackages() {

        LOGGER.info("getPackages()");

        if (packages == null) {
            packages = new HashSet<Package>();
        }

        return packages;
    }


    @XmlElement
    @XmlJavaTypeAdapter(StringPackageSetAdapter.class)
    private Set<Package> packages;


}
