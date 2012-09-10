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
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
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


    /**
     * UTC TimeZone.
     */
    private static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("UTC");


    /**
     * logger.
     */
    private static final Logger LOGGER =
        Logger.getLogger(UcloudStorageClient.class.getPackage().getName());


    static {
        //LOGGER.setLevel(Level.OFF);
    }


    private static final int RESPONSE_CODE_200_OK = 200;


    private static final int RESPONSE_CODE_201_CREATED = 201;


    private static final int RESPONSE_CODE_202_ACCEPTED = 202;


    private static final int RESPONSE_CODE_204_NO_CONTENT = 204;


    private static final int RESPONSE_CODE_403_FORBIDDEN = 403;


    private static final int RESPONSE_CODE_404_NOT_FOUND = 404;


    private static final int CHUNK_LENGTH = 4096;


    private static final int BUFFER_LENGTH = 4096;


    private static final String PATH_SEPARATOR = "/";


    private static final int MAXIMUM_ENCODED_CONTAINER_NAME_LENGTH = 256;


    private static final int MAXIMUM_ENCODED_OBJECT_NAME_LENGTH = 1024;


    /**
     * Returns 'Content-Length' header value from given
     * <code>connection</code>.
     *
     * @param connection connection
     * @return parsed value.
     */
    private static long getContentLength(final URLConnection connection) {

        if (connection == null) {
            throw new IllegalArgumentException("null connection");
        }

        long contentLength = connection.getContentLength();

        try {
            // getContentLengthLong(); Since 7.0
            contentLength =
                ((Long) URLConnection.class.
                 getMethod("getContentLengthLong").invoke(connection)).
                longValue();
        } catch (NoSuchMethodException nsme) {
        } catch (IllegalAccessException iae) {
        } catch (InvocationTargetException ite) {
        }

        return contentLength;
    }


    /**
     * URL-encodes given
     * <code>containerName</code>.
     *
     * @param containerName container name
     * @return the URL-encoded container name
     * @throws UnsupportedEncodingException if 'UTF-8' is not supported
     */
    private static String encodeContainerName(final String containerName)
        throws UnsupportedEncodingException {

        if (containerName == null) {
            throw new IllegalArgumentException("null containerName");
        }

        final String trimmedContainerName = containerName.trim();

        if (trimmedContainerName.isEmpty()) {
            throw new IllegalArgumentException("empty containerName");
        }

        if (!containerName.equals(trimmedContainerName)) {
            throw new IllegalArgumentException("trimmable containerName");
        }

        if (trimmedContainerName.contains(PATH_SEPARATOR)) {
            throw new IllegalArgumentException(
                "containerName contains PATH_SEPARATOR(\"" + PATH_SEPARATOR
                + "\")");
        }

        final String encodedContainerName =
            URLEncoder.encode(containerName, "UTF-8");
        if (encodedContainerName.length()
            > MAXIMUM_ENCODED_CONTAINER_NAME_LENGTH) {
            throw new IllegalArgumentException(
                "containerName's URL-encoded length("
                + encodedContainerName.length()
                + " > MAXIMUM_ENCODED_CONTAINER_NAME_LENGTH("
                + MAXIMUM_ENCODED_CONTAINER_NAME_LENGTH + ")");
        }

        return encodedContainerName;
    }


    /**
     * URL-encodes given
     * <code>obectName</code>.
     *
     * @param objectName object name
     * @return the URL-encoded object name
     * @throws UnsupportedEncodingException if 'UTF-8' is not supported
     */
    private static String encodeObjectName(final String objectName)
        throws UnsupportedEncodingException {

        if (objectName == null) {
            throw new IllegalArgumentException("null objectName");
        }

        final String trimmedObjectName = objectName.trim();

        if (trimmedObjectName.isEmpty()) {
            throw new IllegalArgumentException("empty objectName");
        }

        if (!objectName.equals(trimmedObjectName)) {
            throw new IllegalArgumentException("trimmable objectName");
        }

        final String encodedObjectName = URLEncoder.encode(objectName, "UTF-8");
        if (encodedObjectName.length() > MAXIMUM_ENCODED_OBJECT_NAME_LENGTH) {
            throw new IllegalArgumentException(
                "objectName's URL-encoded length(" + encodedObjectName.length()
                + "> MAXIMUM_ENCODED_OBJECT_NAME_LENGTH("
                + MAXIMUM_ENCODED_OBJECT_NAME_LENGTH + ")");
        }

        return encodedObjectName;
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
     * @throws UnsupportedEncodingException 'UTF-8' not recognized?
     */
    static String append(final String baseUrl,
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
                builder.append(URLEncoder.encode(key, "UTF-8")).append("=").
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
            builder.append(URLEncoder.encode(key, "UTF-8")).append("=").
                append(URLEncoder.encode(value.toString(), "UTF-8"));
        }

        return builder.toString();
    }


    /**
     * Copy bytes from given
     * <code>input</code> to specified
     * <code>output</code>.
     *
     * @param input input
     * @param output output
     * @throws IOException if an I/O error occurs
     */
    static void copy(final InputStream input, final OutputStream output)
        throws IOException {

        copy(input, output, new byte[BUFFER_LENGTH]);
    }


    /**
     * Copy bytes from given
     * <code>input</code> to specified
     * <code>output</code>.
     *
     * @param input input
     * @param output output
     * @param buffer buffer
     * @throws IOException if an I/O error occurs
     */
    static void copy(final InputStream input, final OutputStream output,
                     final byte[] buffer)
        throws IOException {

        if (input == null) {
            throw new IllegalArgumentException("null input");
        }

        if (output == null) {
            throw new IllegalArgumentException("null output");
        }

        if (buffer == null) {
            throw new IllegalArgumentException("null buffer");
        }

        if (buffer.length == 0) {
            throw new IllegalArgumentException("zero-length buffer");
        }

        for (int read = -1; (read = input.read(buffer)) != -1;) {
            output.write(buffer, 0, read);
        }
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

        if (storageUser.trim().isEmpty()) {
            throw new IllegalArgumentException("empty storageUser");
        }

        if (storagePass == null) {
            throw new IllegalArgumentException("null storagePass");
        }

        if (storagePass.isEmpty()) {
            throw new IllegalArgumentException("empty storagePass");
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
        try {
            setResponses(connection);
            if (responseCode == RESPONSE_CODE_200_OK) {
                storageUrl = connection.getHeaderField("X-Storage-URL");
                authToken = connection.getHeaderField("X-Auth-Token");
                return true;
            }
        } finally {
            connection.disconnect();
        }

        return false;
    }


    /**
     *
     * @param containerName container name
     * @param storageContainer storage container
     * @return true if succeeded; false otherwise
     * @throws IOException if an I/O error occurs
     */
    public boolean readStorageContainer(final String containerName,
                                        final StorageContainer storageContainer)
        throws IOException {

//        LOGGER.log(Level.INFO, "readStorageContainers({0}, {1})",
//                   new Object[]{containerName, storageContainer});

        final String encodedContainerName = encodeContainerName(containerName);

        if (storageContainer == null) {
            throw new IllegalArgumentException("null storageContainer");
        }

        if (!authenticate()) {
            return false;
        }

        final String baseUrl = storageUrl + PATH_SEPARATOR
                               + encodedContainerName;

        final URL url = new URL(baseUrl);

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("HEAD");
        connection.setRequestProperty("X-Auth-Token", authToken);

        setTimeouts(connection);

        connection.connect();
        try {
            setResponses(connection);
            if (responseCode == RESPONSE_CODE_204_NO_CONTENT) {
                storageContainer.setName(containerName);
                storageContainer.setCount(Long.parseLong(
                    connection.getHeaderField("X-Container-Object-Count")));
                storageContainer.setBytes(Long.parseLong(
                    connection.getHeaderField("X-Container-Bytes-Used")));
            }
            if (responseCode == RESPONSE_CODE_204_NO_CONTENT) {
                return true;
            }
        } finally {
            connection.disconnect();
        }

        return false;
    }


    /**
     * Reads information of containers.
     *
     * @param queryParameters query parameters; <code>null</code> is allowed
     * @param storageContainers the collecion to which results are added
     *
     * @return true if succeeded; false otherwise
     *
     * @throws IOException if an I/O error occurs.
     */
    public boolean readStorageContainers(
        final Map<String, Object> queryParameters,
        final Collection<StorageContainer> storageContainers)
        throws IOException {

//        LOGGER.log(Level.INFO, "readStorageContainers({0}, {1})",
//                   new Object[]{storageContainers, queryParameters});

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

        connection.connect();
        try {
            setResponses(connection);
            if (responseCode == RESPONSE_CODE_200_OK) {
                final InputStream input = connection.getInputStream();
                try {
                    try {
                        final Collection<Map<String, String>> results =
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
            if (responseCode == RESPONSE_CODE_200_OK
                || responseCode == RESPONSE_CODE_204_NO_CONTENT) {
                return true;
            }
        } finally {
            connection.disconnect();
        }

        return false;
    }


    /**
     * Creates a container named as given
     * <code>containerName</code>.
     *
     * @param containerName container name
     *
     * @return true if succeeded; false otherwise
     *
     * @throws IOException if an I/O error occurs.
     */
    public boolean createContainer(final String containerName)
        throws IOException {

//        LOGGER.log(Level.INFO, "createContainer({0})", containerName);

        final String encodedContainerName = encodeContainerName(containerName);

        if (!authenticate()) {
            return false;
        }

        final String baseUrl = storageUrl + PATH_SEPARATOR
                               + encodedContainerName;

        final URL url = new URL(baseUrl);

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("PUT");
        connection.setRequestProperty("X-Auth-Token", authToken);

        setTimeouts(connection);

        connection.connect();
        try {
            setResponses(connection);

            if (responseCode == RESPONSE_CODE_201_CREATED
                || responseCode == RESPONSE_CODE_202_ACCEPTED) {
                return true;
            }
        } finally {
            connection.disconnect();
        }

        return false;
    }


    /**
     * Deletes a container.
     *
     * @param containerName container name
     *
     * @return true if succeeded; false otherwise
     *
     * @throws IOException if an I/O error occurs.
     */
    public boolean deleteContainer(final String containerName)
        throws IOException {

//        LOGGER.log(Level.INFO, "deleteContainer({0})", containerName);

        final String encodedContainerName = encodeContainerName(containerName);

        if (!authenticate()) {
            return false;
        }

        final String baseUrl = storageUrl + PATH_SEPARATOR
                               + encodedContainerName;

        final URL url = new URL(baseUrl);

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("DELETE");
        connection.setRequestProperty("X-Auth-Token", authToken);

        setTimeouts(connection);

        connection.connect();
        try {
            setResponses(connection);

            if (responseCode == RESPONSE_CODE_204_NO_CONTENT
                || responseCode == RESPONSE_CODE_404_NOT_FOUND) {
                return true;
            }
        } finally {
            connection.disconnect();
        }

        return false;
    }


    /**
     *
     * @param containerName
     * @param objectName
     * @param storageObject
     * @return
     * @throws IOException
     */
    public boolean readStorageObject(final String containerName,
                                     final String objectName,
                                     final StorageObject storageObject)
        throws IOException {

//        LOGGER.log(Level.INFO, "readStorageObject({0}, {1}, {2})",
//                   new Object[]{containerName, objectName,
//                                storageObject});

        final String encodedContainerName = encodeContainerName(containerName);

        final String encodedObjectName = encodeObjectName(objectName);

        if (storageObject == null) {
            throw new IllegalArgumentException("null storageObjects");
        }

        if (!authenticate()) {
            return false;
        }

        final String baseUrl = storageUrl + PATH_SEPARATOR
                               + encodedContainerName + PATH_SEPARATOR
                               + encodedObjectName;

        final URL url = new URL(baseUrl);

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("HEAD");
        connection.setRequestProperty("X-Auth-Token", authToken);

        setTimeouts(connection);

        connection.connect();
        try {
            setResponses(connection);
            if (responseCode == RESPONSE_CODE_200_OK
                || responseCode == RESPONSE_CODE_204_NO_CONTENT) {
                storageObject.setName(connection.getHeaderField(objectName));
                storageObject.setHash(connection.getHeaderField("Etag"));
                storageObject.setBytes(getContentLength(connection));
                storageObject.setContentType(
                    connection.getHeaderField("Content-Type"));
                storageObject.setLastModified(connection.getLastModified());
            }
            if (responseCode == RESPONSE_CODE_200_OK
                || responseCode == RESPONSE_CODE_204_NO_CONTENT) {
                return true;
            }
        } finally {
            connection.disconnect();
        }

        return false;
    }


    /**
     * Reads objects information of a container.
     *
     * @param containerName container name
     * @param queryParameters query parameters; <code>null</code> is allowed
     * @param storageObjects the collection to which all results are added
     *
     * @return true if succeeded; false otherwise
     *
     * @throws IOException if an I/O error occurs
     */
    public boolean readStorageObjects(
        final String containerName, final Map<String, Object> queryParameters,
        final Collection<StorageObject> storageObjects)
        throws IOException {

//        LOGGER.log(Level.INFO, "readStorageObjects({0}, {1}, {2})",
//                   new Object[]{containerName, queryParameters,
//                                storageObjects});

        final String encodedContainerName = encodeContainerName(containerName);

        if (storageObjects == null) {
            throw new IllegalArgumentException("null storageObjects");
        }

        if (!authenticate()) {
            return false;
        }

        final String baseUrl = storageUrl + PATH_SEPARATOR
                               + encodedContainerName;

        final URL url = new URL(append(baseUrl, queryParameters));

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setDoOutput(true);
        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-Auth-Token", authToken);
        connection.setRequestProperty("Accept", "application/xml");

        setTimeouts(connection);

        connection.connect();
        try {
            setResponses(connection);
            if (responseCode == RESPONSE_CODE_200_OK) { // NOT 204
                final InputStream input = connection.getInputStream();
                try {
                    try {
                        final Collection<Map<String, String>> results =
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
                                lastModified.getTime().getTime());
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
            if (responseCode == RESPONSE_CODE_200_OK
                || responseCode == RESPONSE_CODE_204_NO_CONTENT) {
                return true;
            }
        } finally {
            connection.disconnect();
        }

        return false;
    }


    /**
     * Updates an object's content.
     *
     * @param containerName container name
     * @param objectName object name
     * @param contentType content type; <code>null</code> for unknown
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

//        LOGGER.log(Level.INFO, "updateObject({0}, {1}, {2}, {3})",
//                   new Object[]{containerName, objectName, contentType,
//                                contentData});

        if (contentData == null) {
            throw new IllegalArgumentException("null contentData");
        }

        return updateObject(containerName, objectName, contentType,
                            contentData.length,
                            new ByteArrayInputStream(contentData));
    }


    /**
     * Updates an object's content.
     *
     * @param containerName container name
     * @param objectName object name
     * @param contentType content type; <code>null</code> for unknown
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

//        LOGGER.log(Level.INFO, "updateObject({0}, {1}, {2}, {3}, {4})",
//                   new Object[]{containerName, objectName, contentType,
//                                contentLength, contentData});

        if (contentData == null) {
            throw new IllegalArgumentException("null contentData");
        }

        final ContentDataProducer contentDataProducer =
            new DefaultContentDataProducer(contentData);

        return updateObject(containerName, objectName, contentType,
                            contentLength, contentDataProducer);

    }


    /**
     * Updates an object's content.
     *
     * @param containerName container name
     * @param objectName object name
     * @param contentType content type; <code>null</code> for unknown
     * @param contentLength content length; <code>-1L</code> for unknown
     * @param contentDataProducer content data producer
     *
     * @return true if succeeded; false otherwise
     *
     * @throws IOException if an I/O error occurs.
     */
    public boolean updateObject(final String containerName,
                                final String objectName,
                                final String contentType,
                                final long contentLength,
                                final ContentDataProducer contentDataProducer)
        throws IOException {

//        LOGGER.log(Level.INFO, "updateObject({0}, {1}, {2}, {3}, {4})",
//                   new Object[]{containerName, objectName, contentType,
//                                contentLength, contentDataProducer});

        if (contentDataProducer == null) {
            throw new IllegalArgumentException("null contentDataProducer");
        }

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
     * Updates an object's content.
     *
     * @param containerName container name
     * @param objectName object name
     * @param contentProducer content producer
     *
     * @return true if succeeded; false otherwise
     *
     * @throws IOException if an I/O error occurs
     */
    public boolean updateObject(final String containerName,
                                final String objectName,
                                final ContentProducer contentProducer)
        throws IOException {

//        LOGGER.log(Level.INFO, "updateObject({0}, {1}, {2})",
//                   new Object[]{containerName, objectName, contentProducer});

        final String encodedContainerName = encodeContainerName(containerName);

        final String encodedObjectName = encodeObjectName(objectName);

        if (!createContainer(containerName)) {
            return false;
        }

        final String baseUrl = storageUrl + PATH_SEPARATOR
                               + encodedContainerName + PATH_SEPARATOR
                               + encodedObjectName;

        final URL url = new URL(baseUrl);

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setDoOutput(true);
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("X-Auth-Token", authToken);

        String contentType = contentProducer.getContentType();
        if (contentType == null || contentType.trim().isEmpty()) {
            contentType = UNKNOWN_CONTENT_TYPE;
        }
        connection.setRequestProperty("Content-Type", contentType);

        long contentLength = contentProducer.getContentLength();
        if (contentLength < UNKNOWN_CONTENT_LENGTH) {
            contentLength = UNKNOWN_CONTENT_LENGTH;
        }
        if (contentLength == UNKNOWN_CONTENT_LENGTH) {
            connection.setChunkedStreamingMode(CHUNK_LENGTH);
        } else {
            connection.setRequestProperty(
                "Content-Length", Long.toString(contentLength));
        }

        setTimeouts(connection);

        connection.connect();
        try {
            final OutputStream output = connection.getOutputStream();
            try {
                final InputStream input = contentProducer.getContentData();
                copy(input, output);
                output.flush();
            } finally {
                output.close();
            }
            setResponses(connection);
            if (responseCode == RESPONSE_CODE_201_CREATED) {
                return true;
            }
        } finally {
            connection.disconnect();
        }

        return false;
    }


    /**
     * Reads an object's content.
     *
     * @param containerName container name
     * @param objectName object name
     * @param contentDataOutput the output stream to which content is written
     * @return true if succeeded; false otherwise
     * @throws IOException if an I/O error occurs
     */
    public boolean readObject(final String containerName,
                              final String objectName,
                              final OutputStream contentDataOutput)
        throws IOException {

        if (contentDataOutput == null) {
            throw new IllegalArgumentException("null contentDataOutput");
        }

        final ContentDataConsumer contentDataConsumer =
            new ContentDataConsumer() {


                @Override
                public void setContentData(final InputStream contentData)
                    throws IOException {

                    copy(contentData, contentDataOutput);
                }


            };

        return readObject(containerName, objectName, contentDataConsumer);
    }


    /**
     * Reads object content.
     *
     * @param containerName container name
     * @param objectName object name
     * @param contentDataConsumer content data consumer
     *
     * @return true if succeeded; false otherwise
     *
     * @throws IOException if an I/O error occurs.
     */
    public boolean readObject(final String containerName,
                              final String objectName,
                              final ContentDataConsumer contentDataConsumer)
        throws IOException {

//        LOGGER.log(Level.INFO, "readObject({0}, {1}, {2})",
//                   new Object[]{containerName, objectName,
//                                contentDataConsumer});

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
     * Reads object content.
     *
     * @param containerName container name
     * @param objectName object name
     * @param contentConsumer content consumer
     *
     * @return true if succeeded; false otherwise
     *
     * @throws IOException if an I/O error occurs
     */
    public boolean readObject(final String containerName,
                              final String objectName,
                              final ContentConsumer contentConsumer)
        throws IOException {

//        LOGGER.log(Level.INFO, "readObject({0}, {1}, {2})",
//                   new Object[]{containerName, objectName, contentConsumer});

        final String encodedContainerName = encodeContainerName(containerName);

        final String encodedObjectName = encodeObjectName(objectName);

        if (contentConsumer == null) {
            throw new IllegalArgumentException("null contentConsumer");
        }

        if (!authenticate()) {
            return false;
        }

        final String baseUrl = storageUrl + PATH_SEPARATOR
                               + encodedContainerName + PATH_SEPARATOR
                               + encodedObjectName;

        final URL url = new URL(baseUrl);

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-Auth-Token", authToken);

        setTimeouts(connection);

        connection.connect();
        try {
            setResponses(connection);
            if (responseCode == RESPONSE_CODE_200_OK) {
                contentConsumer.setContentType(connection.getContentType());
                contentConsumer.setContentLength(getContentLength(connection));
                contentConsumer.setContentData(connection.getInputStream());
            }
            if (responseCode == RESPONSE_CODE_200_OK) {
                return true;
            }
        } finally {
            connection.disconnect();
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

//        LOGGER.log(Level.INFO, "deleteObject({0}, {1})",
//                   new Object[]{containerName, objectName});

        final String encodedContainerName = encodeContainerName(containerName);

        final String encodedObjectName = encodeObjectName(objectName);

        if (!authenticate()) {
            return false;
        }

        final String baseUrl = storageUrl + PATH_SEPARATOR
                               + encodedContainerName + PATH_SEPARATOR
                               + encodedObjectName;

        final URL url = new URL(baseUrl);

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("DELETE");
        connection.setRequestProperty("X-Auth-Token", authToken);

        setTimeouts(connection);

        connection.connect();
        try {
            setResponses(connection);
            if (responseCode == RESPONSE_CODE_204_NO_CONTENT
                || responseCode == RESPONSE_CODE_404_NOT_FOUND) {
                return true;
            }
        } finally {
            connection.disconnect();
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


    /**
     * Sets response code and message from given
     * <code>connection</code>.
     *
     * @param connection connection
     *
     * @throws IOException if an I/O error occurs.
     */
    private void setResponses(final HttpURLConnection connection)
        throws IOException {

        if (connection == null) {
            throw new IllegalArgumentException("null connection");
        }

        responseCode = connection.getResponseCode();
        responseMessage = connection.getResponseMessage();

        headerFields = connection.getHeaderFields();

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


    /**
     * Returns last HTTP response header fields.
     *
     * @return response header fields
     */
    public Map<String, List<String>> getHeaderFields() {
        return headerFields;
    }


    /**
     * storage user.
     */
    private final String storageUser;


    /**
     * storage pass.
     */
    private final String storagePass;


    /**
     * storage url.
     */
    private transient String storageUrl;


    /**
     * auth token.
     */
    private transient String authToken;


    /**
     * Connect timeout.
     */
    private transient Integer connectTimeout;


    /**
     * Read timeout.
     */
    private transient Integer readTimeout;


    /**
     * HTTP response code.
     */
    private transient int responseCode;


    /**
     * HTTP response message.
     */
    private transient String responseMessage;


    /**
     * HTTP response header fields.
     */
    private transient Map<String, List<String>> headerFields;


}

