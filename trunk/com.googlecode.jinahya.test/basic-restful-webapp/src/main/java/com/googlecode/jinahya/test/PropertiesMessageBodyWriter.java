

package com.googlecode.jinahya.test;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Provider
public class PropertiesMessageBodyWriter
    implements MessageBodyWriter<Properties> {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(PropertiesMessageBodyWriter.class.getName());


    private static final JsonFactory JSON_FACTORY = new JsonFactory();


    static {
        // configure JSON_FACTORY
    }


    @Override
    public long getSize(final Properties t, final Class<?> type,
                        final Type genericType, final Annotation[] annotations,
                        final MediaType mediaType) {

        LOGGER.log(Level.INFO, "getSize({0}, {1}, {2}, {3}, {4})",
                   new Object[]{t, type, genericType, annotations, mediaType});

        return -1L;
    }


    @Override
    public boolean isWriteable(final Class<?> type, final Type genericType,
                               final Annotation[] annotations,
                               final MediaType mediaType) {

        LOGGER.log(Level.INFO, "isWritable({0}, {1}, {2}, {3})",
                   new Object[]{type, genericType, annotations, mediaType});

        if (!Properties.class.isAssignableFrom(type)) {
            return false;
        }

        if (!MediaType.TEXT_PLAIN_TYPE.isCompatible(mediaType)
            && !MediaType.APPLICATION_XML_TYPE.isCompatible(mediaType)
            && !MediaType.APPLICATION_JSON_TYPE.isCompatible(mediaType)) {
            return false;
        }

        return true;
    }


    @Override
    public void writeTo(final Properties t, final Class<?> type,
                        final Type genericType, final Annotation[] annotations,
                        final MediaType mediaType,
                        final MultivaluedMap<String, Object> httpHeaders,
                        final OutputStream entityStream)
        throws IOException, WebApplicationException {

        LOGGER.log(Level.INFO, "writeTo({0}, {1}, {2}, {3}, {4}, {5}, {6})",
                   new Object[]{t, type, genericType, annotations, mediaType,
                                httpHeaders, entityStream});

        if (MediaType.TEXT_PLAIN_TYPE.isCompatible(mediaType)) {
            t.store(entityStream, null);
            return;
        }

        if (MediaType.APPLICATION_XML_TYPE.isCompatible(mediaType)) {
            t.storeToXML(entityStream, null);
            return;
        }

        if (MediaType.APPLICATION_JSON_TYPE.isCompatible(mediaType)) {

            final JsonGenerator generator =
                JSON_FACTORY.createGenerator(entityStream);

            generator.writeStartObject();

            for (String stringPropertyName : t.stringPropertyNames()) {
                generator.writeStringField(stringPropertyName,
                                           t.getProperty(stringPropertyName));
            }

            generator.writeEndObject();

            generator.flush();

            generator.close();

            return;
        }

        throw new WebApplicationException(
            Response.Status.UNSUPPORTED_MEDIA_TYPE);

    }


}

