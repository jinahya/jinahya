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


package com.googlecode.jinahya.imageio;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 * @param <T>
 * @param <U>
 */
public abstract class ImageFeaturesTest<T extends ImageFeatures<U>, U extends ImageFeature> {


    public ImageFeaturesTest(final Class<T> imageDescriptorsType) {

        super();

        this.imageDescriptorsType = imageDescriptorsType;
    }


    @Test
    public void marshalUnmarshal() throws JAXBException {

        final T expected = newInstance();

//        final JAXBContext context =
//            JAXBContext.newInstance(imageDescriptorsType);
        final JAXBContext context =
            JAXBContext.newInstance(imageDescriptorsType.getPackage().getName());

        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        final String jaxbEncoding =
            (String) marshaller.getProperty(Marshaller.JAXB_ENCODING);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        marshaller.marshal(expected, baos);

        System.out.println(new String(
            baos.toByteArray(), Charset.forName(jaxbEncoding)));

        final Unmarshaller unmarshaller = context.createUnmarshaller();

        final Source source =
            new StreamSource(new ByteArrayInputStream(baos.toByteArray()));
        final T actual = unmarshaller.unmarshal(source, imageDescriptorsType)
            .getValue();

        for (U imageDescriptor : actual.getImageFeatureList()) {
            System.out.println("unmarshalled: " + imageDescriptor);
        }
    }


    protected abstract T newInstance();


    private final Class<T> imageDescriptorsType;


}
