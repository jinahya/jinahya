

package com.googlecode.jinahya.test;


import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.schema.JsonSchema;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Path("/imageSuffix.jsd")
public class ImageSuffixJsdResource {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(ImageSuffixJsdResource.class.getName());


    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String read() throws JsonMappingException {

        LOGGER.info("read()");

        final ObjectMapper objectMapper = new ObjectMapper();

        final JsonSchema jsonSchema =
            objectMapper.generateJsonSchema(ImageSuffix.class);

        final String jsonSchemaString = jsonSchema.toString();

        return jsonSchemaString;
    }


}

