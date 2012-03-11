

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
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder = {"token", "norma", "strin"})
public class XsString {


    public static void main(final String[] args)
        throws JAXBException, IOException {

        final JAXBContext context = JAXBContext.newInstance(XsString.class);

        // XML Schema ----------------------------------------------------------
        final StringWriter schemaWriter = new StringWriter();
        context.generateSchema(new SchemaOutputResolver() {


            @Override
            public Result createOutput(final String namespaceUri,
                                       final String suggestedFileName)
                throws IOException {

                return new StreamResult(schemaWriter) {


                    @Override
                    public String getSystemId() {
                        return suggestedFileName;
                    }


                };
            }


        });
        schemaWriter.flush();
        System.out.println("-------------------------------------- XML Schema");
        System.out.println(schemaWriter.toString());

        final String unprocessed = "\t ab\r\n   c\t \r \n";

        // marshal -------------------------------------------------------------
        final XsString marshalling = new XsString();
        marshalling.token = unprocessed;
        marshalling.norma = unprocessed;
        marshalling.strin = unprocessed;
        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        final StringWriter writer = new StringWriter();
        marshaller.marshal(marshalling, writer);
        writer.flush();
        System.out.println("-------------------------------------- marshalled");
        System.out.println(writer.toString());

        // unmarshal -----------------------------------------------------------
        final Unmarshaller unmarshaller = context.createUnmarshaller();
        final XsString unmarshalled = (XsString) unmarshaller.unmarshal(
            new StringReader(writer.toString()));
        System.out.println("------------------------------------ unmarshalled");
        System.out.println(unmarshalled.toString());
    }


    @Override
    public String toString() {
        return "token: " + token + "\nnorma: " + norma + "\nstrin: " + strin;
    }


    @XmlElement
    @XmlSchemaType(name = "token")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    private String token;


    @XmlElement
    @XmlSchemaType(name = "normalizedString")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    private String norma;


    @XmlElement
    private String strin;


}

