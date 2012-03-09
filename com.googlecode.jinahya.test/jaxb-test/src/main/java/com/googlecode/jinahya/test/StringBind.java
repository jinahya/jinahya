

package com.googlecode.jinahya.test;


import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement(name = "bind")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = {"token", "normalized", "string"})
public class StringBind {


    public static void main(final String[] args)
        throws JAXBException, IOException {

        final JAXBContext context = JAXBContext.newInstance(StringBind.class);

        // print XML Schema

        final StringWriter schemaWriter = new StringWriter();
        context.generateSchema(new SchemaOutputResolver() {


            @Override
            public Result createOutput(final String namespaceUri,
                                       final String suggestedFileName)
                throws IOException {

                final StreamResult result = new StreamResult(schemaWriter);
                result.setSystemId(suggestedFileName);
                return result;
            }


        });
        schemaWriter.flush();
        System.out.println("-------------------------------------- XML Schema");
        System.out.println(schemaWriter.toString());

        final String unprocessed = "\t ab\r\n   c\t \r \n";

        // marshal
        final StringBind marshal = new StringBind();
        marshal.token = marshal.normalized = marshal.string = unprocessed;
        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        final StringWriter marshalWriter = new StringWriter();
        marshaller.marshal(marshal, marshalWriter);
        marshalWriter.flush();
        System.out.println("-------------------------------------- marshalled");
        System.out.println(marshalWriter.toString());

        // unmarshal
        final String xml =
            "<bind>"
            + "<token>" + unprocessed + "</token>"
            + "<normalized>" + unprocessed + "</normalized>"
            + "<string>" + unprocessed + "</string>"
            + "</bind>";
        final Unmarshaller unmarshaller = context.createUnmarshaller();
        final StringBind unmarshalled =
            (StringBind) unmarshaller.unmarshal(new StringReader(xml));
        System.out.println("------------------------------------ unmarshalled");
        System.out.println(unmarshalled);
    }


    @Override
    public String toString() {
        return "token: " + token
               + "\nnormalized: " + normalized
               + "\nstring: " + string;
    }


    @XmlElement
    @XmlSchemaType(name = "token")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    private String token;


    @XmlElement
    @XmlSchemaType(name = "normalizedString")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    private String normalized;


    @XmlElement
    private String string;


}

