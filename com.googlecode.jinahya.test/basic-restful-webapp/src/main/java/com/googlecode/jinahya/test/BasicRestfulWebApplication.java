

package com.googlecode.jinahya.test;


import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@ApplicationPath("/")
public class BasicRestfulWebApplication extends Application {


//    @Override
//    public Set<Class<?>> getClasses() {
//        final HashSet<Class<?>> classes = new HashSet<>(2);
//        classes.add(MOXyJsonProvider.class);
//        classes.add(ItemsResource.class);
//        return classes;
//    }
//    @Override
//    public Set<Object> getSingletons() {
//        HashSet<Object> singletons = new HashSet<>();
//        singletons.add(
//            new org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider());
//        return singletons;
//    }


}

