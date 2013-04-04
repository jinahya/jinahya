

package com.googlecode.jinahya.test;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ItemTest {


    @Test
    public void testXml() throws JAXBException, IOException {

        final JAXBContext context = JAXBContext.newInstance(Item.class);

        final Item expected =
            Item.putCreatedAtAndId(Item.newInstance("name", 0));

        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        marshaller.marshal(expected, baos);
        baos.flush();

        final byte[] bytes = baos.toByteArray();
        System.out.println(new String(bytes, (String) marshaller.getProperty(
            Marshaller.JAXB_ENCODING)));

        final Unmarshaller unmserahller = context.createUnmarshaller();
        final Item actual = (Item) unmserahller.unmarshal(
            new ByteArrayInputStream(bytes));

        Assert.assertEquals(actual.name, expected.name);
        Assert.assertEquals(actual.stock, expected.stock);
    }


    @Test
    public void readItemXml() throws JAXBException, IOException {

        final JAXBContext context = JAXBContext.newInstance(Item.class);

        final Unmarshaller unmserahller = context.createUnmarshaller();
        final Item item = (Item) unmserahller.unmarshal(
            getClass().getResourceAsStream("/item.xml"));

        System.out.println("item.xml#item/name: " + item.name);
        System.out.println("item.xml#item/stock: " + item.stock);
    }


}

