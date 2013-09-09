

package com.googlecode.jinahya.test;


import com.googlecode.jinahya.servlet.http.FileSuffixToMediaTypeDispatcher;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
@WebFilter(urlPatterns = "/*",
           initParams = {@WebInitParam(name = "file.suffix.xml",
                                       value = "media/type/application/xml"),
                         @WebInitParam(name = "file.suffix.json",
                                       value = "media/type/application/json")})
public class ApplicationMediaTypeDispatcher
    extends FileSuffixToMediaTypeDispatcher {
}
