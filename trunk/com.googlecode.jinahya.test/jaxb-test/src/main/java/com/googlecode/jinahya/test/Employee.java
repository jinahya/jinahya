

package com.googlecode.jinahya.test;


import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {


    public static void main(final String[] args) throws Exception {

        final JAXBContext context = JAXBContext.newInstance(Employee.class);

        final Employee before = new Employee();
        before.id = 0L;
        before.name = "name";
        for (long childId = 0L; childId <= 10L; childId++) {
            final Employee child = new Employee();
            child.id = childId;
            child.name = Long.toString(childId);
            child.manager = before;
            before.getSubordinates().add(child);
        }

        final StringWriter writer = new StringWriter();
        context.createMarshaller().marshal(before, writer);
        writer.flush();

        final String xml = writer.toString();
        System.out.println(xml);

        final StringReader reader = new StringReader(xml);
        final Employee after =
            (Employee) context.createUnmarshaller().unmarshal(reader);
    }


    public Long getId() {
        return id;
    }


    public void setId(final Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(final String name) {
        this.name = name;
    }


    public Employee getManager() {
        return manager;
    }


    public void setManager(final Employee manager) {
        this.manager = manager;
    }


    public Collection<Employee> getSubordinates() {
        if (subordinates == null) {
            subordinates = new ArrayList<Employee>();
        }
        return subordinates;
    }


    private void beforeMarshal(final Marshaller marshaller) {
        System.out.println("beforeMarshal(" + marshaller + ")");
        System.out.println("id: " + id);
        employeeId = Long.toString(id);
    }


    private void afterMarshal(final Marshaller marshaller) {
        System.out.println("afterMarshal(" + marshaller + ")");
    }


    private void beforeUnmarshal(final Unmarshaller unmarshaller,
                                 final Object parent) {
        System.out.println(
            "beforeUnmarshal(" + unmarshaller + ", " + parent + ")");
    }


    private void afterUnmarshal(final Unmarshaller unmarshaller,
                                final Object parent) {
        System.out.println(
            "afterUnmarshal(" + unmarshaller + ", " + parent + ")");

        //System.out.println(employeeId);
        id = Long.parseLong(employeeId);

        //this.parent = (Callback) parent;
    }


    @XmlTransient
    private Long id;


    @XmlAttribute
    @XmlID
    private String employeeId;


    @XmlElement
    private String name;


    @XmlIDREF
    private Employee manager;


    @XmlElement(name = "subordinate")
    @XmlIDREF
    private Collection<Employee> subordinates;


}

