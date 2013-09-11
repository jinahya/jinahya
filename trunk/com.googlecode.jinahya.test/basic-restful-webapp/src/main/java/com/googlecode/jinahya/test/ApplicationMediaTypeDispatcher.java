

package com.googlecode.jinahya.test;


import com.googlecode.jinahya.servlet.http.FileSuffixToMediaTypeDispatcher;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
@WebFilter(urlPatterns = "/rest/*",
           initParams = {@WebInitParam(name = "file.suffix.xml",
                                       value = "media/type/application/xml"),
                         @WebInitParam(name = "file.suffix.json",
                                       value = "media/type/application/json")})
public class ApplicationMediaTypeDispatcher
    extends FileSuffixToMediaTypeDispatcher {


    private static final Logger LOGGER =
        LoggerFactory.getLogger(ApplicationMediaTypeDispatcher.class);


}
