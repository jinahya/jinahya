

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
public class EmployeeTest {


    @Test
    public void test() throws JAXBException {

        final JAXBContext context = JAXBContext.newInstance(Employee.class);

        Employee callback = new Employee();

        callback.setId(0L);
        callback.setName("name");

        final StringWriter writer = new StringWriter();

        context.createMarshaller().marshal(callback, writer);

        final String xml = writer.toString();

        System.out.println(xml);

        final StringReader reader = new StringReader(xml);

        callback = (Employee) context.createUnmarshaller().unmarshal(reader);

    }


}

