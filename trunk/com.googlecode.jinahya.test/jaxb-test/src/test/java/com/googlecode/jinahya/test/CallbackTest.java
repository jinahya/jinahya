/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.googlecode.jinahya.test;


import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class CallbackTest {


    @Test
    public void test() throws JAXBException {

        final JAXBContext context = JAXBContext.newInstance(Callback.class);

        Callback callback = new Callback();

        callback.setId(0L);
        callback.setName("name");

        final StringWriter writer = new StringWriter();

        context.createMarshaller().marshal(callback, writer);

        final String xml = writer.toString();

        System.out.println(xml);

        final StringReader reader = new StringReader(xml);

        callback = (Callback) context.createUnmarshaller().unmarshal(reader);

    }


}

