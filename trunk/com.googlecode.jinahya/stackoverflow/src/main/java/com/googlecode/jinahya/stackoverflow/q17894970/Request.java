/*
 * Copyright Error: on line 4, column 29 in Templates/Licenses/license-apache20.txt
 Expecting a date here, found: 2013. 7. 27 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.stackoverflow.q17894970;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "request")
public class Request {


    private static class EmtpyStringAdapter
        extends XmlAdapter<String, Integer> {


        @Override
        public Integer unmarshal(final String value) throws Exception {

            if (value == null || value.isEmpty()) {
                return null;
            }

            return Integer.valueOf(value);
        }


        @Override
        public String marshal(final Integer bound) throws Exception {

            if (bound == null) {
                return null;
            }

            return bound.toString();
        }


    }


    public static void main(final String[] args) throws JAXBException {

        final JAXBContext context = JAXBContext.newInstance(Request.class);

        {
            final Request request1 = new Request();
            request1.setIntTag(0);

            final ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            final Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                                   Boolean.TRUE);
            marshaller.marshal(request1, System.out);
            marshaller.marshal(request1, buffer);

            final Unmarshaller unamrshaller = context.createUnmarshaller();
            final Request request2 = (Request) unamrshaller.unmarshal(
                new ByteArrayInputStream(buffer.toByteArray()));
            System.out.println(request2.getIntTag());
        }

        {
            final Request request1 = new Request();
            request1.setIntTag(null);

            final ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            final Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                                   Boolean.TRUE);
            marshaller.marshal(request1, System.out);
            marshaller.marshal(request1, buffer);

            final Unmarshaller unamrshaller = context.createUnmarshaller();
            final Request request2 = (Request) unamrshaller.unmarshal(
                new ByteArrayInputStream(buffer.toByteArray()));
            System.out.println(request2.getIntTag());
        }

        {
            final String xml = "<request><intTag></intTag></request>";
            System.out.println(xml);
            final Unmarshaller unamrshaller = context.createUnmarshaller();
            final Request request2 =
                (Request) unamrshaller.unmarshal(new StringReader(xml));
            System.out.println(request2.getIntTag());
        }
    }


    @XmlElement(nillable = true, required = true)
    @XmlJavaTypeAdapter(EmtpyStringAdapter.class)
    public Integer getIntTag() {
        return intTag;
    }


    public void setIntTag(Integer intTag) {
        this.intTag = intTag;
    }


    private Integer intTag;


}

