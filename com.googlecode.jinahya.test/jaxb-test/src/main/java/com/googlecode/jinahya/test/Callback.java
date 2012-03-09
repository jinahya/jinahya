

package com.googlecode.jinahya.test;


import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Callback {


    public static void main(final String[] args) throws Exception {

        final JAXBContext context = JAXBContext.newInstance(Callback.class);

        final Callback before = new Callback();
        before.id = 0L;
        before.name = "name";

        final StringWriter writer = new StringWriter();
        context.createMarshaller().marshal(before, writer);
        writer.flush();

        final String xml = writer.toString();
        System.out.println(xml);

        final StringReader reader = new StringReader(xml);
        final Callback after =
            (Callback) context.createUnmarshaller().unmarshal(reader);
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    private void beforeMarshal(Marshaller marshaller) {
        System.out.println("beforeMarshal(" + marshaller + ")");
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
    }


    @XmlAttribute
    private Long id;


    @XmlElement
    private String name;


}

