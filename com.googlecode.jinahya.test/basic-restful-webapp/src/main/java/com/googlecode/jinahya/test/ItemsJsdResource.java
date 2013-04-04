

package com.googlecode.jinahya.test;


import java.io.IOException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.schema.JsonSchema;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Path("/items.jsd")
public class ItemsJsdResource {


    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response read() throws JAXBException, IOException {

        final ObjectMapper objectMapper = new ObjectMapper();
        final JsonSchema jsonSchema =
            objectMapper.generateJsonSchema(Items.class);

        final String jsonSchemaString = jsonSchema.toString();

        return Response.ok(jsonSchemaString).build();
    }


    @Context
    private UriInfo uriInfo;


}

