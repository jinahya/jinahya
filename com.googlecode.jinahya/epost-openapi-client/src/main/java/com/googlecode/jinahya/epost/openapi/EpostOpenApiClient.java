/*
 * Copyright 2012 Jin Kwon <jinahya at gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.googlecode.jinahya.epost.openapi;


import java.io.IOException;
import java.lang.Class;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class EpostOpenApiClient {


    private static final SAXParserFactory SAX_PARSER_FACTORY =
        SAXParserFactory.newInstance();


    private static final String REQUEST_URI =
        "http://biz.epost.go.kr/KpostPortal/openapi";


    /**
     * Creates a new instance.
     *
     * @param regkey regkey
     */
    public EpostOpenApiClient(final String regkey) {
        super();

        if (regkey == null) {
            throw new IllegalArgumentException("null regkey");
        }

        if (regkey.trim().isEmpty()) {
            throw new IllegalArgumentException("empty regkey");
        }

        this.regkey = regkey;
    }


    /**
     * Queries.
     *
     * @param query query
     * @param results results map from <code>address</code>
     * to <code>postcd</code>.
     *
     * @return true if succeeded(200); false otherwise
     *
     * @throws IOException if an I/O error occurs.
     * @throws ParserConfigurationException See <a
     * href="http://docs.oracle.com/javase/7/docs/api/javax/xml/parsers/SAXParserFactory.html#newInstance()">SAXParserFactory.html#newInstance()</a>
     * @throws SAXException See <a
     * href="http://docs.oracle.com/javase/7/docs/api/javax/xml/parsers/SAXParser.html#parse(java.io.InputStream,
     * org.xml.sax.helpers.DefaultHandler)">SAXParser.html#parse(java.io.InputStream,
     * org.xml.sax.helpers.DefaultHandler)</a>
     */
    public boolean query(final String query, final Map<String, String> results)
        throws IOException, ParserConfigurationException, SAXException {

        if (query == null) {
            throw new IllegalArgumentException("null query");
        }

        if (query.trim().isEmpty()) {
            throw new IllegalArgumentException("empty query");
        }

        if (results == null) {
            throw new IllegalArgumentException("null results");
        }

        final URL url = new URL(
            REQUEST_URI + "?regkey=" + regkey + "&target=post&query="
            + URLEncoder.encode(query, "euc-kr"));
//        final URL url = new URL(
//            REQUEST_URI + "?regkey=" + authKey + "&target=post&query="
//            + query);

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();
        //connection.setRequestMethod("POST");
        connection.setRequestProperty("Accept-Language", "ko"); // what the fu..

        setTimeout(connection);

        connection.connect();

        setResponse(connection);

        if (responseCode == 200) {
            final SAXParser parser = SAX_PARSER_FACTORY.newSAXParser();
            parser.parse(connection.getInputStream(),
                         new EpostDefaultHandler(results));
            return true;
        }

        connection.disconnect();

        return false;
    }


    private void setTimeout(final URLConnection connection) {

        if (connection == null) {
            throw new IllegalArgumentException("null connection");
        }

        if (connectTimeout != null) {
            try {
                final Method method = URLConnection.class.getMethod(
                    "setConnectTimeout", new Class<?>[]{Integer.TYPE});
                try {
                    method.invoke(
                        connection, new Object[]{connectTimeout.intValue()});
                } catch (IllegalAccessException iae) {
                    iae.printStackTrace(System.err);
                } catch (InvocationTargetException ite) {
                    ite.printStackTrace(System.err);
                }
            } catch (NoSuchMethodException nsme) {
                nsme.printStackTrace(System.err);
            }
        }

        if (readTimeout != null) {
            try {
                final Method method = URLConnection.class.getMethod(
                    "setReadTimeout", new Class<?>[]{Integer.TYPE});
                try {
                    method.invoke(
                        connection, new Object[]{readTimeout.intValue()});
                } catch (IllegalAccessException iae) {
                    iae.printStackTrace(System.err);
                } catch (InvocationTargetException ite) {
                    ite.printStackTrace(System.err);
                }
            } catch (NoSuchMethodException nsme) {
                nsme.printStackTrace(System.err);
            }
        }
    }


    /**
     * Returns connect timeout.
     *
     * @return connect timeout
     */
    public Integer getConnectTimeout() {
        return connectTimeout;
    }


    /**
     * Sets connect timeout.
     *
     * @param connectTimeout connect timeout
     */
    public void setConnectTimeout(final Integer connectTimeout) {

        if (connectTimeout != null && connectTimeout.intValue() < 0) {
            throw new IllegalArgumentException(
                "connectTimeout.intValue(" + connectTimeout.intValue()
                + ") < 0");
        }

        this.connectTimeout = connectTimeout;
    }


    /**
     * Return read timeout.
     *
     * @return read timeout.
     */
    public Integer getReadTimeout() {
        return readTimeout;
    }


    /**
     * Sets read timeout.
     *
     * @param readTimeout read timeout
     */
    public void setReadTimeout(final Integer readTimeout) {

        if (readTimeout != null && readTimeout.intValue() < 0) {
            throw new IllegalArgumentException(
                "readTimeout.intValue(" + readTimeout.intValue() + ") < 0");
        }

        this.readTimeout = readTimeout;
    }


    private void setResponse(final HttpURLConnection connection)
        throws IOException {

        if (connection == null) {
            throw new IllegalArgumentException("null connection");
        }

        responseCode = connection.getResponseCode();
        responseMessage = connection.getResponseMessage();
        System.out.println("response: " + responseCode + " " + responseMessage);
    }


    /**
     * Returns last HTTP status code.
     *
     * @return status code
     */
    public int getResponseCode() {
        return responseCode;
    }


    /**
     * Returns last HTTP reason phrase.
     *
     * @return reason phrase
     */
    public String getResponseMessage() {
        return responseMessage;
    }


    private final String regkey;


    private transient Integer connectTimeout;


    private transient Integer readTimeout;


    private transient int responseCode;


    private transient String responseMessage;


}

