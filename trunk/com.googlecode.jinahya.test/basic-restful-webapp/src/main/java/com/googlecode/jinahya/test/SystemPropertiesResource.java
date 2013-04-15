

package com.googlecode.jinahya.test;


import java.util.Properties;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Path("/systemProperties")
public class SystemPropertiesResource {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(SystemPropertiesResource.class.getName());


    private static final Properties PROPERTIES = System.getProperties();


    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Properties read() {

        LOGGER.info("read()");

        return PROPERTIES;
    }


}

