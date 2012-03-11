

package com.googlecode.jinahya.test;


import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Company1 {


    public static void main(final String[] args) throws Exception {

        final JAXBContext context = JAXBContext.newInstance(Company1.class);

        final Company1 company = new Company1();

        final Employee1 employee1 = new Employee1();
        employee1.setId(1L);
        employee1.setName("Jane Doe");
        company.getEmployees().add(employee1);

        final Employee1 employee2 = new Employee1();
        employee2.setId(2L);
        employee2.setName("John Smith");
        employee2.setManager(employee1);
        employee1.getSubordinates().add(employee2);
        company.getEmployees().add(employee2);

        final Employee1 employee3 = new Employee1();
        employee3.setId(3L);
        employee3.setName("Anne Jones");
        employee3.setManager(employee1);
        employee1.getSubordinates().add(employee3);
        company.getEmployees().add(employee3);

        final StringWriter writer = new StringWriter();
        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(company, writer);
        writer.flush();

        final String xml = writer.toString();
        System.out.println(xml);

        final StringReader reader = new StringReader(xml);
        final Company1 after =
            (Company1) context.createUnmarshaller().unmarshal(reader);
    }


    public Collection<Employee1> getEmployees() {
        if (employees == null) {
            employees = new ArrayList<Employee1>();
        }
        return employees;
    }


    @XmlElement(name = "employee")
    private Collection<Employee1> employees;


}

