

package com.googlecode.jinahya.test;


import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
@WebFilter(urlPatterns = "/*",
           initParams = {@WebInitParam(name = ".xml",
                                       value = "application/xml"),
                         @WebInitParam(name = ".json",
                                       value = "application/json")})
public class ApplicationTypeSuffixFilter extends TypeSuffixFilter {
}
