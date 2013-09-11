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


import com.googlecode.jinahya.xml.bind.test.Item;
import com.googlecode.jinahya.xml.bind.test.Items;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class MarshallersTest {


    @Test
    public static void printMarshalMethods()
        throws NoSuchFieldException, IllegalAccessException {

        final Field field =
            Marshallers.class.getDeclaredField("MARSHAL_METHODS");
        field.setAccessible(true);
        @SuppressWarnings("unchecked")
        final Map<?, ?> value = (Map<?, ?>) field.get(null);
        for (Entry<?, ?> entry : value.entrySet()) {
            final Class<?> outputType = (Class<?>) entry.getKey();
            final Method method = (Method) entry.getValue();
            System.out.println("output type: " + outputType);
            System.out.println("method: " + method);
        }
    }


    @Test
    public static void testMarshal() throws JAXBException {

        final Items items = new Items();
        items.getItems().add(Item.newInstance());

        Marshallers.marshal(items, System.out);
        Marshallers.marshal(items, OutputStream.class, System.out);

        final JAXBContext context = JAXBContext.newInstance(Items.class);
        Marshallers.marshal(context, items, System.out);
        Marshallers.marshal(context, items, OutputStream.class, System.out);

        final Marshaller marshaller = context.createMarshaller();
        Marshallers.marshal(marshaller, items, System.out);
        Marshallers.marshal(marshaller, items, OutputStream.class, System.out);
    }


}

