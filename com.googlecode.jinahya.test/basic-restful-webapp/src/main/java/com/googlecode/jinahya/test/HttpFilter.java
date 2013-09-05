

package com.googlecode.jinahya.test;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public abstract class HttpFilter implements Filter {


    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

        this.filterConfig = filterConfig;
    }


    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain chain)
        throws IOException, ServletException {

        if (!(request instanceof HttpServletRequest)
            || !(response instanceof HttpServletResponse)) {
            chain.doFilter(request, response);
            return;
        }

        doFilter((HttpServletRequest) request, (HttpServletResponse) response,
                 chain);
    }


    protected abstract void doFilter(HttpServletRequest request,
                                     HttpServletResponse response,
                                     FilterChain chain)
        throws IOException, ServletException;


    @Override
    public void destroy() {

        filterConfig = null;
    }


    protected FilterConfig filterConfig;


}
