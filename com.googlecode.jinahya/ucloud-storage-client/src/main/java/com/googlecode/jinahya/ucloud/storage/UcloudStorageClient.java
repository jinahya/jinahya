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
import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.ParserConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    static final String UNKNOWN_CONTENT_TYPE = "application/octet-stream";


    /**
     * Content length for unknown.
     */
    static final long UNKNOWN_CONTENT_LENGTH = -1L;


    /**
     * UTC TimeZone.
     */
    private static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("UTC");


    /**
     * logger.
     */
    private static final Logger LOGGER =
        LoggerFactory.getLogger(UcloudStorageClient.class);


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
     * Returns 'Content-Length' header value from given connection.
     *
     * @param connection the connection
     *
     * @return parsed value.
     */
    private static long getContentLength(final URLConnection connection) {

        if (connection == null) {
            throw new NullPointerException("connection");
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


    private static String utf8(final Object string) {

        try {
            return URLEncoder.encode(String.valueOf(string), "UTF-8");
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("\"UTF-8\" is not supported?");
        }
    }


    /**
     * URL-encodes given container name.
     *
     * @param containerName the container name
     *
     * @return a URL-encoded value of given container name
     */
    private static String encodeContainerName(String containerName) {

        if (containerName == null) {
            throw new NullPointerException("containerName");
        }

        containerName = containerName.trim();
        if (containerName.isEmpty()) {
            throw new IllegalArgumentException("empty containerName");
        }

        if (containerName.contains(PATH_SEPARATOR)) {
            throw new IllegalArgumentException(
                "containerName contains PATH_SEPARATOR(\"" + PATH_SEPARATOR
                + "\")");
        }

        containerName = utf8(containerName);
        if (containerName.length() > MAXIMUM_ENCODED_CONTAINER_NAME_LENGTH) {
            throw new IllegalArgumentException(
                "encoded containerName's length(" + containerName.length()
                + " > (" + MAXIMUM_ENCODED_CONTAINER_NAME_LENGTH + ")");
        }

        return containerName;
    }


    /**
     * URL-encodes given object name.
     *
     * @param objectName the object name
     *
     * @return the URL-encoded object name
     */
    private static String encodeObjectName(String objectName) {

        if (objectName == null) {
            throw new NullPointerException("objectName");
        }

        objectName = objectName.trim();
        if (objectName.isEmpty()) {
            throw new IllegalArgumentException("empty objectName");
        }

        objectName = utf8(objectName);
        if (objectName.length() > MAXIMUM_ENCODED_OBJECT_NAME_LENGTH) {
            throw new IllegalArgumentException(
                "encoded objectName's length(" + objectName.length()
                + "> (" + MAXIMUM_ENCODED_OBJECT_NAME_LENGTH + ")");
        }

        return objectName;
    }


    /**
     * Appends given query parameters onto specified url.
     *
     * @param base the base url
     * @param params the query parameters
     *
     * @return append url
     */
    private static String append(final String base,
                                 final Map<String, Object> params) {

        if (base == null) {
            throw new NullPointerException("base");
        }

        if (params == null || params.isEmpty()) {
            return base;
        }

        final StringBuilder builder = new StringBuilder(base);

        final Iterator<Entry<String, Object>> i = params.entrySet().iterator();
        if (i.hasNext()) {
            final Entry<String, Object> entry = i.next();
            builder.append("?").append(utf8(entry.getKey())).append("=")
                .append(utf8(entry.getValue()));
        }
        while (i.hasNext()) {
            final Entry<String, Object> entry = i.next();
            builder.append("&").append(utf8(entry.getKey())).append("=")
                .append(utf8(entry.getValue()));
        }

        return builder.toString();
    }


    /**
     * Copy bytes from given input stream to specified output stream using given
     * buffer.
     *
     * @param input the input stream
     * @param output the output stream
     * @param buffer the byte buffer
     *
     * @throws IOException if an I/O error occurs
     */
    private static void copy(final InputStream input, final OutputStream output,
                             final byte[] buffer)
        throws IOException {

        if (input == null) {
            throw new NullPointerException("null input");
        }

        if (output == null) {
            throw new NullPointerException("null output");
        }

        if (buffer == null) {
            throw new NullPointerException("null buffer");
        }

        if (buffer.length == 0) {
            throw new IllegalArgumentException("zero-length buffer");
        }

        for (int read = -1; (read = input.read(buffer)) != -1;) {
            output.write(buffer, 0, read);
        }
    }


    /**
     * Copy bytes from given input stream to specified output stream.
     *
     * @param input the input stream
     * @param output the output stream
     *
     * @throws IOException if an I/O error occurs
     */
    private static void copy(final InputStream input, final OutputStream output)
        throws IOException {

        copy(input, output, new byte[BUFFER_LENGTH]);
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
            throw new NullPointerException("storageUser");
        }

        if (storagePass == null) {
            throw new NullPointerException("storagePass");
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

        LOGGER.debug("authenticate()");

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
     * Retrieves a storage account.
     *
     * @return a storage account or {@code null} if failed.
     *
     * @throws IOException if an I/O error occurs.
     */
    public StorageAccount readStorageAccount() throws IOException {

        LOGGER.debug("readStorageAccount()");

        if (!authenticate()) {
            return null;
        }

        final String base = storageUrl;

        final URL url = new URL(base);

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("HEAD");
        connection.setRequestProperty("X-Auth-Token", authToken);

        // 406 Not Acceptable without this header, damn.
        connection.setRequestProperty("Accept", "application/xml"); // @@?

        setTimeouts(connection);

        connection.connect();
        try {
            setResponses(connection);
            if (responseCode == RESPONSE_CODE_204_NO_CONTENT) {
                final StorageAccount storageAccount = new StorageAccount();
                storageAccount.setContainerCount(Long.parseLong(
                    getFirstHeaderValue("X-Account-Container-Count")));
                storageAccount.setObjectCount(Long.parseLong(
                    getFirstHeaderValue("X-Account-Object-Count")));
                storageAccount.setBytesUsed(Long.parseLong(
                    getFirstHeaderValue("X-Account-Bytes-Used")));
                return storageAccount;
            }
        } finally {
            connection.disconnect();
        }

        return null;
    }


    /**
     * Retrieves storage containers.
     *
     * @param queryParameters query parameters; {@code null} is allowed
     * @param storageContainers the collection to which results are added
     *
     * @return {@code true} if succeeded; {@code false} otherwise
     *
     * @throws IOException if an I/O error occurs.
     */
    public boolean readStorageContainers(
        final Map<String, Object> queryParameters,
        final Collection<? super StorageContainer> storageContainers)
        throws IOException {

        LOGGER.debug("readStorageContainers({}, {})", queryParameters,
                     storageContainers);

        if (queryParameters == null) {
            // ok
        }

        if (storageContainers == null) {
            throw new NullPointerException("storageContainers");
        }

        if (!authenticate()) {
            return false;
        }

        if (queryParameters != null) {
            queryParameters.remove("format");
        }

        final String spec = append(storageUrl, queryParameters);

        final URL url = new URL(spec);

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        //connection.setDoInput(true);
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
                        for (final Map<String, String> result : results) {
                            final StorageContainer storageContainer =
                                new StorageContainer();
                            storageContainer.setContainerName(
                                result.get("name"));
                            storageContainer.setObjectCount(
                                Long.parseLong(result.get("count")));
                            storageContainer.setBytesUsed(
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


//    /**
//     * Requests the storage container whose name is given container name.
//     *
//     * @param containerName the container name
//     * @param storageContainer storage container
//     *
//     * @return true if succeeded; false otherwise
//     *
//     * @throws IOException if an I/O error occurs
//     */
//    public boolean readStorageContainer(final String containerName,
//                                        final StorageContainer storageContainer)
//        throws IOException {
//
//        LOGGER.debug("readStorageContainer({}, {})", containerName,
//                     storageContainer);
//
//        // check
//        final String encodedContainerName = encodeContainerName(containerName);
//
//        if (storageContainer == null) {
//            throw new NullPointerException("storageContainer");
//        }
//
//        if (!authenticate()) {
//            return false;
//        }
//
//        final String base = storageUrl + PATH_SEPARATOR + encodedContainerName;
//
//        final URL url = new URL(base);
//
//        final HttpURLConnection connection =
//            (HttpURLConnection) url.openConnection();
//
//        connection.setRequestMethod("HEAD");
//        connection.setRequestProperty("X-Auth-Token", authToken);
//
//        setTimeouts(connection);
//
//        connection.connect();
//        try {
//            setResponses(connection);
//            if (responseCode == RESPONSE_CODE_204_NO_CONTENT) {
//                storageContainer.setContainerName(containerName);
//                storageContainer.setObjectCount(Long.parseLong(
//                    getFirstHeaderValue("X-Container-Object-Count")));
//                storageContainer.setBytesUsed(Long.parseLong(
//                    getFirstHeaderValue("X-Container-Bytes-Used")));
//            }
//            if (responseCode == RESPONSE_CODE_204_NO_CONTENT) {
//                return true;
//            }
//        } finally {
//            connection.disconnect();
//        }
//
//        return false;
//    }
//
//
//    /**
//     *
//     * @param storageContainer the storage container
//     *
//     * @return {@code true} if succeeded; {@code false} if failed.
//     *
//     * @throws IOException if an I/O error occurs.
//     */
//    public boolean readStorageContainer(final StorageContainer storageContainer)
//        throws IOException {
//
//        LOGGER.debug("readStorageContainer({})", storageContainer);
//
//        if (storageContainer == null) {
//            throw new NullPointerException("storageContainer");
//        }
//
//        return readStorageContainer(storageContainer.getContainerName(),
//                                    storageContainer);
//    }
//    /**
//     * Retrieves a storage container whose name is mapped to given container
//     * name.
//     *
//     * @param containerName the container name
//     *
//     * @return the mapped storage container or {@code null} if not found or
//     * failed
//     *
//     * @throws IOException if an I/O error occurs.
//     */
//    public StorageContainer readStorageContainer(final String containerName)
//        throws IOException {
//
//        LOGGER.debug("readStorageContainer({})", containerName);
//
//        final StorageContainer storageContainer = new StorageContainer();
//        storageContainer.setContainerName(containerName);
//
//        if (!readStorageContainer(storageContainer)) {
//            return null;
//        }
//
//        return storageContainer;
//    }
    /**
     * Creates a storage container named as given name.
     *
     * @param containerName the container name
     *
     * @return true if succeeded; false otherwise
     *
     * @throws IOException if an I/O error occurs.
     */
    public boolean createStorageContainer(final String containerName)
        throws IOException {

        LOGGER.debug("createStorageContainer({})", containerName);

        // check
        final String encodedContainerName = encodeContainerName(containerName);

        if (!authenticate()) {
            return false;
        }

        final String spec = storageUrl + PATH_SEPARATOR + encodedContainerName;

        final URL url = new URL(spec);

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
     * Retrieves a storage container whose name is mapped to given container
     * name.
     *
     * @param containerName the container name
     *
     * @return the mapped storage container or {@code null} if not found or
     * failed
     *
     * @throws IOException if an I/O error occurs.
     */
    public StorageContainer readStorageContainer(final String containerName)
        throws IOException {

        LOGGER.debug("readStorageContainer({})", containerName);

        // check
        final String encodedContainerName = encodeContainerName(containerName);

        if (!authenticate()) {
            return null;
        }

        final String base = storageUrl + PATH_SEPARATOR + encodedContainerName;

        final URL url = new URL(base);

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("HEAD");
        connection.setRequestProperty("X-Auth-Token", authToken);

        setTimeouts(connection);

        connection.connect();
        try {
            setResponses(connection);
            if (responseCode == RESPONSE_CODE_204_NO_CONTENT) {
                final StorageContainer storageContainer =
                    new StorageContainer();
                storageContainer.setContainerName(containerName);
                storageContainer.setContainerName(containerName);
                storageContainer.setObjectCount(Long.parseLong(
                    getFirstHeaderValue("X-Container-Object-Count")));
                storageContainer.setBytesUsed(Long.parseLong(
                    getFirstHeaderValue("X-Container-Bytes-Used")));
                return storageContainer;
            }
        } finally {
            connection.disconnect();
        }

        return null;
    }


    /**
     * Deletes a storage container named as given container name.
     *
     * @param containerName the name of the storage container to delete
     *
     * @return {@code true} if succeeded; {@code false} otherwise
     *
     * @throws IOException if an I/O error occurs.
     */
    public boolean deleteStorageContainer(final String containerName)
        throws IOException {

        LOGGER.debug("deleteStorageContainer({})", containerName);

        // check
        final String encodedContainerName = encodeContainerName(containerName);

        if (!authenticate()) {
            return false;
        }

        final String spec = storageUrl + PATH_SEPARATOR + encodedContainerName;

        final URL url = new URL(spec);

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
     * Reads objects information of a container.
     *
     * @param containerName the container name
     * @param queryParameters query parameters; {@code null} is allowed
     * @param storageObjects the collection to which all results are added
     *
     * @return {@code true} if succeeded; {@code false} otherwise
     *
     * @throws IOException if an I/O error occurs
     */
    public boolean readStorageObjects(
        final String containerName, final Map<String, Object> queryParameters,
        final Collection<? super StorageObject> storageObjects)
        throws IOException {

        LOGGER.debug("readStorageObjects({}, {}, {})", containerName,
                     queryParameters, storageObjects);

        final String encodedContainerName = encodeContainerName(containerName);

        if (storageObjects == null) {
            throw new NullPointerException("storageObjects");
        }

        if (!authenticate()) {
            return false;
        }

        final String base = storageUrl + PATH_SEPARATOR + encodedContainerName;

        if (queryParameters != null) {
            queryParameters.remove("format");
        }

        final URL url = new URL(append(base, queryParameters));

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
                            storageObject.setObjectName(result.get("name"));
                            storageObject.setEntityTag(result.get("hash"));
                            storageObject.setContentLength(
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


//    /**
//     * Reads a storage object.
//     *
//     * @param containerName the container name
//     * @param objectName the object name
//     * @param storageObject the storage object
//     *
//     * @return true if succeeded; false otherwise
//     *
//     * @throws IOException if an I/O error occurs
//     */
//    public boolean readStorageObject(final String containerName,
//                                     final String objectName,
//                                     final StorageObject storageObject)
//        throws IOException {
//
//        LOGGER.debug("readStorageObject({}, {}, {})", containerName, objectName,
//                     storageObject);
//
//        final String encodedContainerName = encodeContainerName(containerName);
//
//        final String encodedObjectName = encodeObjectName(objectName);
//
//        if (storageObject == null) {
//            throw new NullPointerException("storageObjects");
//        }
//
//        if (!authenticate()) {
//            return false;
//        }
//
//        final String spec = storageUrl + PATH_SEPARATOR + encodedContainerName
//                            + PATH_SEPARATOR + encodedObjectName;
//
//        final URL url = new URL(spec);
//
//        final HttpURLConnection connection =
//            (HttpURLConnection) url.openConnection();
//
//        connection.setRequestMethod("HEAD");
//        connection.setRequestProperty("X-Auth-Token", authToken);
//
//        setTimeouts(connection);
//
//        connection.connect();
//        try {
//            setResponses(connection);
//            if (responseCode == RESPONSE_CODE_200_OK
//                || responseCode == RESPONSE_CODE_204_NO_CONTENT) {
//                storageObject.setObjectName(objectName);
//                storageObject.setLastModified(connection.getLastModified());
//                storageObject.setEntityTag(getFirstHeaderValue("Etag"));
//                storageObject.setContentType(connection.getContentType());
//                storageObject.setContentLength(getContentLength(connection));
//                storageObject.setLastModified(connection.getLastModified());
//            }
//            if (responseCode == RESPONSE_CODE_200_OK
//                || responseCode == RESPONSE_CODE_204_NO_CONTENT) {
//                return true;
//            }
//        } finally {
//            connection.disconnect();
//        }
//
//        return false;
//    }
    public StorageObject readStorageObject(final String containerName,
                                           final String objectName)
        throws IOException {

        LOGGER.debug("readStorageObject({}, {})", containerName, objectName);

        final String encodedContainerName = encodeContainerName(containerName);

        final String encodedObjectName = encodeObjectName(objectName);

        if (!authenticate()) {
            return null;
        }

        final String spec = storageUrl + PATH_SEPARATOR + encodedContainerName
                            + PATH_SEPARATOR + encodedObjectName;

        final URL url = new URL(spec);

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
                final StorageObject storageObject = new StorageObject();
                storageObject.setObjectName(objectName);
                storageObject.setLastModified(connection.getLastModified());
                storageObject.setEntityTag(getFirstHeaderValue("Etag"));
                storageObject.setContentType(connection.getContentType());
                storageObject.setContentLength(getContentLength(connection));
                storageObject.setLastModified(connection.getLastModified());
                return storageObject;
            }
        } finally {
            connection.disconnect();
        }

        return null;
    }


    /**
     * Deletes an object.
     *
     * @param containerName the container name
     * @param objectName the object name
     *
     * @return {@code true} if succeeded; {@code false} otherwise
     *
     * @throws IOException if an I/O error occurs.
     */
    public boolean deleteStorageObject(final String containerName,
                                       final String objectName)
        throws IOException {

        LOGGER.debug("deleteStorageObject({}, {})", containerName, objectName);

        final String encodedContainerName = encodeContainerName(containerName);

        final String encodedObjectName = encodeObjectName(objectName);

        if (!authenticate()) {
            return false;
        }

        final String base = storageUrl + PATH_SEPARATOR + encodedContainerName
                            + PATH_SEPARATOR + encodedObjectName;

        final URL url = new URL(base);

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
     * Updates or creates an storage content.
     *
     * @param containerName container name
     * @param objectName object name
     * @param contentProducer content producer
     *
     * @return true if succeeded; false otherwise
     *
     * @throws IOException if an I/O error occurs
     */
    public boolean updateStorageContent(final String containerName,
                                        final String objectName,
                                        final ContentProducer contentProducer)
        throws IOException {

        LOGGER.debug("updateStorageContent({}, {}, {})", containerName,
                     objectName, contentProducer);

        // check
        final String encodedContainerName = encodeContainerName(containerName);

        // check
        final String encodedObjectName = encodeObjectName(objectName);

        if (!createStorageContainer(containerName)) {
            return false;
        }

        final String base = storageUrl + PATH_SEPARATOR + encodedContainerName
                            + PATH_SEPARATOR + encodedObjectName;

        final URL url = new URL(base);

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setDoOutput(true);
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("X-Auth-Token", authToken);

        String contentType = contentProducer.getContentType();
        if (contentType == null) {
            contentType = UNKNOWN_CONTENT_TYPE;
        }
        connection.setRequestProperty("Content-Type", contentType);

        long contentLength = contentProducer.getContentLength();
        if (contentLength > UNKNOWN_CONTENT_LENGTH) {
            connection.setRequestProperty(
                "Content-Length", Long.toString(contentLength));
        } else {
            connection.setChunkedStreamingMode(CHUNK_LENGTH);
        }

        setTimeouts(connection);

        connection.connect();
        try {
            final OutputStream contentData = connection.getOutputStream();
            try {
                contentProducer.getContentData(contentData);
            } finally {
                contentData.close();
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
     * Updates an storage object's content.
     *
     * @param containerName container name
     * @param objectName object name
     * @param contentType content type; {@code null} for unknown
     * @param contentLength content length; {@code -1L} for unknown
     * @param contentData content data
     *
     * @return true if succeeded; false otherwise
     *
     * @throws IOException if an I/O error occurs
     */
    public boolean updateStorageContent(final String containerName,
                                        final String objectName,
                                        final String contentType,
                                        final long contentLength,
                                        final InputStream contentData)
        throws IOException {

        LOGGER.debug("updateStorageContent({}, {}, {}, {}, {})", containerName,
                     objectName, contentType, contentLength, contentData);

        if (contentData == null) {
            throw new NullPointerException("contentData");
        }

        final ContentProducer contentProduer = new ContentProducer() {


            @Override
            public String getContentType() {
                return contentType;
            }


            @Override
            public long getContentLength() {
                return contentLength;
            }


            @Override
            public void getContentData(final OutputStream contentData_)
                throws IOException {
                copy(contentData, contentData_);
            }

        };

        return updateStorageContent(containerName, objectName, contentProduer);
    }


    /**
     * Updates an object's content.
     *
     * @param containerName container name
     * @param objectName object name
     * @param contentType content type; {@code null} for unknown
     * @param contentData content data
     *
     * @return true if succeeded; false otherwise
     *
     * @throws IOException if an I/O error occurs
     */
    public boolean updateStorageContent(final String containerName,
                                        final String objectName,
                                        final String contentType,
                                        final byte[] contentData)
        throws IOException {

        LOGGER.debug("updateStorageContent({}, {}, {}, {})", containerName,
                     objectName, contentType, contentData);

        if (contentData == null) {
            throw new NullPointerException("contentData");
        }

        return updateStorageContent(
            containerName, objectName, contentType, contentData.length,
            new ByteArrayInputStream(contentData));
    }


    /**
     * Reads the content of a storage object.
     *
     * @param containerName the container name
     * @param objectName the object name
     * @param contentConsumer the content consumer
     *
     * @return true if succeeded; false otherwise
     *
     * @throws IOException if an I/O error occurs
     */
    public boolean readStorageContent(
        final String containerName, final String objectName,
        final ContentConsumer contentConsumer)
        throws IOException {

        LOGGER.debug("readStorageContent({}, {}, {})", containerName,
                     objectName, contentConsumer);

        final String encodedContainerName = encodeContainerName(containerName);

        final String encodedObjectName = encodeObjectName(objectName);

        if (contentConsumer == null) {
            throw new NullPointerException("contentConsumer");
        }

        if (!authenticate()) {
            return false;
        }

        final String spec = storageUrl + PATH_SEPARATOR + encodedContainerName
                            + PATH_SEPARATOR + encodedObjectName;

        final URL url = new URL(spec);

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
                final InputStream contentData = connection.getInputStream();
                try {
                    contentConsumer.setContentData(contentData);
                } finally {
                    contentData.close();
                }
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
     * Sets timeouts to given {@code connection}.
     *
     * @param connection the connection
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
     * Sets response code and message from given {@code connection}.
     *
     * @param connection the connection
     *
     * @throws IOException if an I/O error occurs.
     */
    private void setResponses(final HttpURLConnection connection)
        throws IOException {

        LOGGER.debug("setResponse({})", connection);

        if (connection == null) {
            throw new IllegalArgumentException("null connection");
        }

        responseCode = connection.getResponseCode();
        LOGGER.debug("responseCode: {}", responseCode);

        responseMessage = connection.getResponseMessage();
        LOGGER.debug("responseMessage: {}", responseMessage);

        headerFields = connection.getHeaderFields();
        LOGGER.debug("headerFields: {}", headerFields);
    }


    /**
     * Returns last HTTP response status code.
     *
     * @return status code
     */
    public int getResponseCode() {

        LOGGER.debug("getResponseCode() -> {}", responseCode);

        return responseCode;
    }


    /**
     * Returns last HTTP response reason phrase.
     *
     * @return reason phrase
     */
    public String getResponseMessage() {

        LOGGER.debug("getResponseMessage() -> {}", responseMessage);

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


    public List<String> getHeaderValues(final String headerName) {

        LOGGER.debug("getHeaderValues({})", headerName);

        if (headerName == null) {
            throw new NullPointerException("headerName");
        }

        if (headerFields == null) {
            throw new IllegalStateException("no header fields");
        }

        return headerFields.get(headerName);
    }


    public String getFirstHeaderValue(final String headerName) {

        LOGGER.debug("getFirstHeaderValue({})", headerName);

        final List<String> headerValues = getHeaderValues(headerName);
        if (headerValues == null || headerValues.isEmpty()) {
            return null;
        }

        return headerValues.get(0);
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
     * authentication output. storage url.
     */
    private transient String storageUrl;


    /**
     * authentication output. authentication token.
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
