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


package com.googlecode.jinahya.ucloud.storage;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class UcloudStorageClient {


    /**
     * auth url.
     */
    private static final String AUTH_URL =
        "https://api.ucloudbiz.olleh.com/storage/v1/auth";


    /**
     * Content type for unknown.
     */
    protected static final String UNKNOWN_CONTENT_TYPE =
        "application/octet-stream";


    /**
     * Content length for unknown.
     */
    protected static final long UNKNOWN_CONTENT_LENGTH = -1L;


    private static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("UTC");


    /**
     * logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(UcloudStorageClient.class.getPackage().getName());


    static {
        //LOGGER.setLevel(Level.OFF);
    }


    /**
     * Appends given
     * <code>queryParams</code> to specified
     * <code>baseUrl</code>.
     *
     * @param baseUrl base url
     * @param queryParameters query parameters
     *
     * @return append url
     *
     * @throws UnsupportedEncodingException
     */
    private static String append(final String baseUrl,
                                 final Map<String, Object> queryParameters)
        throws UnsupportedEncodingException {

        if (baseUrl == null) {
            throw new IllegalArgumentException("null baseUrl");
        }

        if (queryParameters == null || queryParameters.isEmpty()) {
            return baseUrl;
        }

        final StringBuilder builder = new StringBuilder(baseUrl);

        final Iterator<Entry<String, Object>> entries =
            queryParameters.entrySet().iterator();
        if (entries.hasNext()) {
            builder.append("?");
            final Entry<String, Object> entry = entries.next();
            final String key = entry.getKey();
            final Object value = entry.getValue();
            if (key != null && value != null) {
                builder.append(URLEncoder.encode(key, "UTF-8")).
                    append("=").
                    append(URLEncoder.encode(value.toString(), "UTF-8"));
            }
        }
        while (entries.hasNext()) {
            builder.append("&");
            final Entry<String, Object> entry = entries.next();
            final String key = entry.getKey();
            final Object value = entry.getValue();
            if (key == null || value == null) {
                continue;
            }
            builder.append(URLEncoder.encode(key, "UTF-8")).
                append("=").
                append(URLEncoder.encode(value.toString(), "UTF-8"));
        }

        return builder.toString();
    }


    /**
     * Creates a new instance.
     *
     * @param storageUser user id
     * @param storagePass api key
     */
    public UcloudStorageClient(final String storageUser,
                               final String storagePass) {
        super();

        if (storageUser == null) {
            throw new IllegalArgumentException("null storageUser");
        }

        if (storagePass == null) {
            throw new IllegalArgumentException("null storagePass");
        }

        this.storageUser = storageUser;
        this.storagePass = storagePass;
    }


    /**
     * Authenticates.
     *
     * @return true if succeeded; false otherwise
     *
     * @throws IOException if an I/O error occurs.
     */
    private boolean authenticate() throws IOException {

        //LOGGER.info("authenticate()");

        final URL url = new URL(AUTH_URL);

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setRequestProperty("X-Storage-User", storageUser);
        connection.setRequestProperty("X-Storage-Pass", storagePass);

        setTimeouts(connection);

        connection.connect();

        setResponse(connection);

        if (responseCode == 200) {
            storageUrl = connection.getHeaderField("X-Storage-URL");
            authToken = connection.getHeaderField("X-Auth-Token");
            return true;
        }

        connection.disconnect();

        return false;
    }


    /**
     * Reads container information.
     *
     * @param storageContainers the collecion to which informations are added
     * @param queryParameters query parameters
     * @return true if succeeded; false otherwise
     * @throws IOException if an I/O error occurs.
     */
    public boolean readStorageContainers(
        final Collection<StorageContainer> storageContainers,
        final Map<String, Object> queryParameters)
        throws IOException {

        LOGGER.log(Level.INFO, "readStorageContainers({0}, {1})",
                   new Object[]{storageContainers, queryParameters});

        if (storageContainers == null) {
            throw new IllegalArgumentException("null storageContainers");
        }

        if (!authenticate()) {
            return false;
        }

        final URL url = new URL(append(storageUrl, queryParameters));

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-Auth-Token", authToken);
        connection.setRequestProperty("Accept", "application/xml");

        setTimeouts(connection);

        try {
            connection.connect();
            setResponse(connection);
            if (responseCode == 200) {
                final InputStream input = connection.getInputStream();
                try {
                    try {
                        final List<Map<String, String>> results =
                            SimpleHandler.parse(input, "container");
                        for (Map<String, String> result : results) {
                            final StorageContainer storageContainer =
                                new StorageContainer();
                            storageContainer.setName(result.get("name"));
                            storageContainer.setCount(
                                Long.parseLong(result.get("count")));
                            storageContainer.setBytes(
                                Long.parseLong(result.get("bytes")));
                            storageContainers.add(storageContainer);
                        }
                    } catch (ParserConfigurationException pce) {
                        pce.printStackTrace(System.err);
                        return false;
                    } catch (SAXException saxe) {
                        saxe.printStackTrace(System.err);
                        return false;
                    }
                } finally {
                    input.close();
                }
            }
        } finally {
            connection.disconnect();
        }

        if (responseCode == 200 || responseCode == 204) {
            return true;
        }

        return false;
    }


    /**
     * Creates a container.
     *
     * @param containerName container name
     *
     * @return true if succeeded; false otherwise
     *
     * @throws IOException if an I/O error occurs.
     */
    public boolean createContainer(final String containerName)
        throws IOException {

        LOGGER.log(Level.INFO, "createContainer({0})", containerName);

        if (containerName == null) {
            throw new IllegalArgumentException("null containerName");
        }

        if (!authenticate()) {
            return false;
        }

        final String baseUrl = storageUrl + "/"
                               + URLEncoder.encode(containerName, "UTF-8");

        final URL url = new URL(baseUrl);

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("PUT");
        connection.setRequestProperty("X-Auth-Token", authToken);

        setTimeouts(connection);

        connection.connect();

        setResponse(connection);

        connection.disconnect();

        if (responseCode == 201 || responseCode == 202) {
            return true;
        }

        return false;
    }


    /**
     * Deletes a container named as given
     * <code>containerName</code>.
     *
     * @param containerName container name
     *
     * @return true if succeeded; false otherwise
     *
     * @throws IOException if an I/O error occurs.
     */
    public boolean deleteContainer(final String containerName)
        throws IOException {

        LOGGER.log(Level.INFO, "deleteContainer({0})", containerName);

        if (containerName == null) {
            throw new IllegalArgumentException("null containerName");
        }

        if (!authenticate()) {
            return false;
        }

        final String baseUrl = storageUrl + "/"
                               + URLEncoder.encode(containerName, "UTF-8");

        final URL url = new URL(baseUrl);

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("DELETE");
        connection.setRequestProperty("X-Auth-Token", authToken);

        setTimeouts(connection);

        connection.connect();

        setResponse(connection);

        connection.disconnect();

        if (responseCode == 204 || responseCode == 404) {
            return true;
        }

        return false;
    }


    /**
     *
     * @param containerName
     * @param storageObjects
     * @param queryParameters
     *
     * @return true if succeeded false otherwise
     *
     * @throws IOException if an I/O error occurs
     */
    public boolean readStorageObjects(
        final String containerName,
        final Collection<StorageObject> storageObjects,
        final Map<String, Object> queryParameters)
        throws IOException {

        LOGGER.log(Level.INFO, "readStorageObjects({0}, {1}, {2})",
                   new Object[]{containerName, storageObjects,
                                queryParameters});

        if (containerName == null) {
            throw new IllegalArgumentException("null containerName");
        }

        if (storageObjects == null) {
            throw new IllegalArgumentException("null storageObjects");
        }

        if (!authenticate()) {
            return false;
        }

        final String baseUrl =
            storageUrl + "/" + URLEncoder.encode(containerName, "UTF-8");

        final URL url = new URL(append(baseUrl, queryParameters));

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setRequestProperty("X-Auth-Token", authToken);
        connection.setRequestProperty("Accept", "application/xml");

        setTimeouts(connection);

        connection.connect();
        try {
            setResponse(connection);
            if (responseCode == 200) {
                final InputStream input = connection.getInputStream();
                try {
                    try {
                        final List<Map<String, String>> results =
                            SimpleHandler.parse(input, "object");
                        for (Map<String, String> result : results) {
                            final StorageObject storageObject =
                                new StorageObject();
                            storageObject.setName(result.get("name"));
                            storageObject.setHash(result.get("hash"));
                            storageObject.setBytes(
                                Long.parseLong(result.get("bytes")));
                            storageObject.setContentType(
                                result.get("content_type"));
                            final Calendar lastModified =
                                DatatypeConverter.parseDateTime(
                                result.get("last_modified"));
                            lastModified.setTimeZone(UTC_TIME_ZONE);
                            storageObject.setLastModified(
                                lastModified.getTime());
                            storageObjects.add(storageObject);
                        }
                    } catch (ParserConfigurationException pce) {
                        pce.printStackTrace(System.err);
                        return false;
                    } catch (SAXException saxe) {
                        saxe.printStackTrace(System.err);
                        return false;
                    }
                } finally {
                    input.close();
                }
            }
        } finally {
            connection.disconnect();
        }

        if (responseCode == 200 || responseCode == 204) {
            return true;
        }

        return false;
    }


    /**
     *
     * @param containerName container name
     * @param objectName object name
     * @param contentType content type
     * @param contentData content data
     *
     * @return true if succeeded; false otherwise
     *
     * @throws IOException if an I/O error occurs
     */
    public boolean updateObject(final String containerName,
                                final String objectName,
                                final String contentType,
                                final byte[] contentData)
        throws IOException {

        LOGGER.log(Level.INFO, "updateObject({0}, {1}, {2}, {3})",
                   new Object[]{containerName, objectName, contentType,
                                contentData});

        if (contentData == null) {
            throw new IllegalArgumentException("null contentData");
        }

        return updateObject(containerName, objectName, contentType,
                            contentData.length,
                            new ByteArrayInputStream(contentData));
    }


    /**
     * Updates object.
     *
     * @param containerName container name
     * @param objectName object name
     * @param contentType content type
     * @param contentLength content length; <code>-1L</code> for unknown
     * @param contentData content data
     *
     * @return true if succeeded; false otherwise
     *
     * @throws IOException if an I/O error occurs
     */
    public boolean updateObject(final String containerName,
                                final String objectName,
                                final String contentType,
                                final long contentLength,
                                final InputStream contentData)
        throws IOException {

        LOGGER.log(Level.INFO, "updateObject({0}, {1}, {2}, {3}, {4})",
                   new Object[]{containerName, objectName, contentType,
                                contentLength, contentData});

        if (contentData == null) {
            throw new IllegalArgumentException("null contentData");
        }

        final ContentDataProducer contentDataProducer =
            new ContentDataProducer() {


                @Override
                public InputStream getContentData() throws IOException {
                    return contentData;
                }


            };

        return updateObject(containerName, objectName, contentType,
                            contentLength, contentDataProducer);

    }


    /**
     *
     * @param containerName
     * @param objectName
     * @param contentType
     * @param contentLength
     * @param contentDataProducer
     *
     * @return
     *
     * @throws IOException
     */
    public boolean updateObject(final String containerName,
                                final String objectName,
                                final String contentType,
                                final long contentLength,
                                final ContentDataProducer contentDataProducer)
        throws IOException {

        LOGGER.log(Level.INFO, "updateObject({0}, {1}, {2}, {3}, {4})",
                   new Object[]{containerName, objectName, contentType,
                                contentLength, contentDataProducer});

        final ContentProducer contentProducer = new ContentProducer() {


            @Override
            public String getContentType() {
                return contentType;
            }


            @Override
            public long getContentLength() {
                return contentLength;
            }


            @Override
            public InputStream getContentData() throws IOException {
                return contentDataProducer.getContentData();
            }


        };

        return updateObject(containerName, objectName, contentProducer);
    }


    /**
     *
     * @param containerName
     * @param objectName
     * @param contentProducer
     *
     * @return
     *
     * @throws IOException
     */
    public boolean updateObject(final String containerName,
                                final String objectName,
                                final ContentProducer contentProducer)
        throws IOException {

        LOGGER.log(Level.INFO, "updateObject({0}, {1}, {2})",
                   new Object[]{containerName, objectName, contentProducer});

        if (containerName == null) {
            throw new IllegalArgumentException("null containerName");
        }

        if (objectName == null) {
            throw new IllegalArgumentException("null objectName");
        }

        if (contentProducer == null) {
            throw new IllegalArgumentException("null contentProducer");
        }

        if (!createContainer(containerName)) {
            return false;
        }

        final URL url = new URL(
            storageUrl + "/" + URLEncoder.encode(containerName, "UTF-8") + "/"
            + URLEncoder.encode(objectName, "UTF-8"));

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("PUT");
        connection.setDoOutput(true);
        connection.setRequestProperty("X-Auth-Token", authToken);

        String contentType = contentProducer.getContentType();
        if (contentType == null) {
            contentType = UNKNOWN_CONTENT_TYPE;
        }
        connection.setRequestProperty("Content-Type", contentType);

        long contentLength = contentProducer.getContentLength();
        if (contentLength < UNKNOWN_CONTENT_LENGTH) {
            contentLength = UNKNOWN_CONTENT_LENGTH;
        }
        if (contentLength == UNKNOWN_CONTENT_LENGTH) {
            connection.setChunkedStreamingMode(4096);
        } else {
            connection.setRequestProperty(
                "Content-Length", Long.toString(contentLength));
        }

        setTimeouts(connection);

        connection.connect();

        final OutputStream output = connection.getOutputStream();

        final InputStream contentData = contentProducer.getContentData();

        final byte[] buffer = new byte[8192];
        for (int read = -1; (read = contentData.read(buffer)) != -1;) {
            output.write(buffer, 0, read);
        }
        output.flush();

        setResponse(connection);

        connection.disconnect();

        if (responseCode == 201) {
            return true;
        }

        return false;
    }


    /**
     *
     * @param containerName
     * @param objectName
     * @param contentDataConsumer
     *
     * @return
     *
     * @throws IOException
     */
    public boolean readObject(final String containerName,
                              final String objectName,
                              final ContentDataConsumer contentDataConsumer)
        throws IOException {

        LOGGER.log(Level.INFO, "readObject({0}, {1}, {2})",
                   new Object[]{containerName, objectName,
                                contentDataConsumer});

        if (containerName == null) {
            throw new IllegalArgumentException("null containerName");
        }

        if (objectName == null) {
            throw new IllegalArgumentException("null objectName");
        }

        if (contentDataConsumer == null) {
            throw new IllegalArgumentException("null contentDataConsumer");
        }

        final ContentConsumer contentConsumer = new ContentConsumer() {


            @Override
            public void setContentType(final String contentType) {
                // empty
            }


            @Override
            public void setContentLength(final long contentLength) {
                // empty
            }


            @Override
            public void setContentData(final InputStream contentData)
                throws IOException {

                contentDataConsumer.setContentData(contentData);
            }


        };

        return readObject(containerName, objectName, contentConsumer);
    }


    /**
     *
     * @param containerName
     * @param objectName
     * @param contentConsumer
     *
     * @return
     *
     * @throws IOException
     */
    public boolean readObject(final String containerName,
                              final String objectName,
                              final ContentConsumer contentConsumer)
        throws IOException {

        LOGGER.log(Level.INFO, "readObject({0}, {1}, {2})",
                   new Object[]{containerName, objectName, contentConsumer});

        if (containerName == null) {
            throw new IllegalArgumentException("null containerName");
        }

        if (objectName == null) {
            throw new IllegalArgumentException("null objectName");
        }

        if (contentConsumer == null) {
            throw new IllegalArgumentException("null contentConsumer");
        }

        if (!authenticate()) {
            return false;
        }

        final String baseUrl = storageUrl + "/"
                               + URLEncoder.encode(containerName, "UTF-8") + "/"
                               + URLEncoder.encode(objectName, "UTF-8");

        final URL url = new URL(baseUrl);

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-Auth-Token", authToken);

        setTimeouts(connection);

        connection.connect();

        setResponse(connection);

        if (responseCode == 200) {

            contentConsumer.setContentType(connection.getContentType());

            contentConsumer.setContentLength(connection.getContentLength());
            try {
                // getContentLengthLong(); Since 7.0
                contentConsumer.setContentLength(
                    (Long) URLConnection.class.getMethod(
                    "getContentLengthLong").invoke(connection));
            } catch (NoSuchMethodException nsme) {
            } catch (IllegalAccessException iae) {
            } catch (InvocationTargetException ite) {
            }

            contentConsumer.setContentData(connection.getInputStream());
        }

        connection.disconnect();

        if (responseCode == 200) {
            return true;
        }

        return false;
    }


    /**
     * Deletes an object.
     *
     * @param containerName container name
     * @param objectName object name
     *
     * @return true if succeeded; false otherwise
     *
     * @throws IOException if an I/O error occurs.
     */
    public boolean deleteObject(final String containerName,
                                final String objectName)
        throws IOException {

        LOGGER.log(Level.INFO, "deleteObject({0}, {1})",
                   new Object[]{containerName, objectName});

        if (containerName == null) {
            throw new IllegalArgumentException("null containerName");
        }

        if (objectName == null) {
            throw new IllegalArgumentException("null objectName");
        }

        if (!authenticate()) {
            return false;
        }

        final String baseUrl = storageUrl + "/"
                               + URLEncoder.encode(containerName, "UTF-8") + "/"
                               + URLEncoder.encode(objectName, "UTF-8");

        final URL url = new URL(baseUrl);

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("DELETE");
        connection.setRequestProperty("X-Auth-Token", authToken);

        setTimeouts(connection);

        connection.connect();

        setResponse(connection);

        connection.disconnect();

        if (responseCode == 204 || responseCode == 404) {
            return true;
        }

        return false;
    }


    /**
     * Sets timeouts to given
     * <code>connection</code>.
     *
     * @param connection connection
     */
    private void setTimeouts(final URLConnection connection) {

        if (connection == null) {
            throw new IllegalArgumentException("null connection");
        }

        if (connectTimeout != null) {
            connection.setConnectTimeout(connectTimeout.intValue());
        }

        if (readTimeout != null) {
            connection.setReadTimeout(readTimeout.intValue());
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

//        LOGGER.log(Level.INFO, "response: {0} {1}",
//                   new Object[]{responseCode, responseMessage});
    }


    /**
     * Returns last HTTP response status code.
     *
     * @return status code
     */
    public int getResponseCode() {
        return responseCode;
    }


    /**
     * Returns last HTTP response reason phrase.
     *
     * @return reason phrase
     */
    public String getResponseMessage() {
        return responseMessage;
    }


    private final String storageUser;


    private final String storagePass;


    private transient String storageUrl;


    private transient String authToken;


    private transient Integer connectTimeout;


    private transient Integer readTimeout;


    private transient int responseCode;


    private transient String responseMessage;


}

