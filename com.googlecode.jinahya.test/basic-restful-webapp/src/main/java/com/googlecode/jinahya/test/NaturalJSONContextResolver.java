

package com.googlecode.jinahya.test;


import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONJAXBContext;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Provider
public class NaturalJSONContextResolver
    implements ContextResolver<JAXBContext> {


    public NaturalJSONContextResolver() throws JAXBException {
        super();

        this.context = new JSONJAXBContext(
            JSONConfiguration.natural().build(), targetTypes);
    }


    @Override
    public JAXBContext getContext(final Class<?> type) {

        for (Class targetType : targetTypes) {
            if (targetType.equals(type)) {
                return context;
            }
        }

        return null;
    }


    private final Class[] targetTypes = new Class<?>[]{Items.class, Item.class};


    private final JAXBContext context;


}

