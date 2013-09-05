

package com.googlecode.jinahya.test;


import com.googlecode.jinahya.servlet.http.HeadersRequestWrapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public abstract class TypeSuffixFilter extends HttpFilter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        super.init(filterConfig);

        for (final Enumeration<String> names =
            filterConfig.getInitParameterNames(); names.hasMoreElements();) {
            final String name = names.nextElement();
            if (!name.startsWith(".") || name.length() == 1) {
                continue;
            }
            final String value = filterConfig.getInitParameter(name);
            if (suffixes == null) {
                suffixes = new HashMap<>();
            }
            suffixes.put(name, value);
        }
    }


    @Override
    protected void doFilter(final HttpServletRequest request,
                            final HttpServletResponse response,
                            final FilterChain chain)
        throws IOException, ServletException {

        final String pathInfo = request.getPathInfo();
        final String pathTranslated = request.getPathTranslated();
        final String servletPath = request.getServletPath();
        final String requestUri = request.getRequestURI();
        final StringBuffer requestUrl = request.getRequestURL();

        System.out.println("-------------------------------------------------");
        System.out.println("pathInfo: " + pathInfo);
        System.out.println("pathTranslated: " + pathTranslated);
        System.out.println("servletPath: " + servletPath);
        System.out.println("requestUri: " + requestUri);
        System.out.println("requestUrl: " + requestUrl);
        System.out.println("-------------------------------------------------");

        if (pathInfo == null) {
            System.out.println("null pathInfo");
            chain.doFilter(request, response);
            return;
        }

        if (suffixes == null) {
            System.out.println("null suffixes");
            chain.doFilter(request, response);
            return;
        }

        for (Entry<String, String> entry : suffixes.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
            if (!pathInfo.endsWith(entry.getKey())) {
                continue;
            }
            System.out.println("pathInfo endsWith " + entry.getKey());
            final String accept = request.getHeader("Accept");
            if (accept != null) {
                System.out.println("Accept: " + accept);
                continue;
            }
            final Map<String, List<String>> headers = new HashMap<>();
            headers.put("Accept", Arrays.asList(entry.getValue()));
            final HttpServletRequest wequest =
                new HeadersRequestWrapper(request, headers);
//            request.set
            System.out.println(".xml");
            final String path = pathInfo.substring(0, pathInfo.length() - entry.getKey().length());
            System.out.println("dispatcher.path: " + path);
            final RequestDispatcher dispatcher =
                wequest.getRequestDispatcher(path);
            dispatcher.forward(wequest, response);
            System.out.println("fowareded");
            response.flushBuffer();
            return;
        }

        System.out.println("normal chain");
        chain.doFilter(request, response);
    }


    private transient Map<String, String> suffixes;


}
