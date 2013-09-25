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
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
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
public class StorageClient {


    /**
     * auth url.
     */
    private static final String AUTH_URL =
        "https://api.ucloudbiz.olleh.com/storage/v1/auth";


    /**
     * content type for unknown.
     */
    private static final String UNKNOWN_CONTENT_TYPE =
        "application/octet-stream";


    /**
     * content length for unknown.
     */
    private static final long UNKNOWN_CONTENT_LENGTH = -1L;


    /**
     * utc timeZone.
     */
    private static final TimeZone UTC = TimeZone.getTimeZone("UTC");


    /**
     * logger.
     */
    private static final Logger LOGGER =
        LoggerFactory.getLogger(StorageClient.class);


    private static final int RESPONSE_CODE_200_OK = 200;


    private static final int RESPONSE_CODE_201_CREATED = 201;


    private static final int RESPONSE_CODE_202_ACCEPTED = 202;


    private static final int RESPONSE_CODE_204_NO_CONTENT = 204;


    private static final int RESPONSE_CODE_403_FORBIDDEN = 403;


    private static final int RESPONSE_CODE_404_NOT_FOUND = 404;


    private static final int CHUNK_LENGTH = 4096;


    private static final int BUFFER_LENGTH = 4096;


    private static final String PATH_SEPARATOR = "/";


    private static final int CONTAINER_NAME_LENGTH_MAX = 256;


    private static final int OBJECT_NAME_LENGTH_MAX = 1024;


    private static final String PROPERTY_PREFIX_KEY_ACCOUNT =
        "X-Account-Meta-0x";


    private static final String PROPERTY_PREFIX_VALUE_ACCOUNT = "0x";


    private static final String PROPERTY_PREFIX_KEY_CONTAINER =
        "X-Container-Meta-0x";


    private static final String PROPERTY_PREFIX_VALUE_CONTAINER = "0x";


    private static final String PROPERTY_PREFIX_KEY_OBJECT = "X-Object-Meta-0x";


    private static final String PROPERTY_PREFIX_VALUE_OBJECT = "0x";


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


//    private static int percent(final int decoded) {
//
//        switch (decoded) {
//            case 0x00:
//            case 0x01:
//            case 0x02:
//            case 0x03:
//            case 0x04:
//            case 0x05:
//            case 0x06:
//            case 0x07:
//            case 0x08:
//            case 0x09:
//                return decoded + 0x30; // 0x30('0') - 0x39('9')
//            case 0x0A:
//            case 0x0B:
//            case 0x0C:
//            case 0x0D:
//            case 0x0E:
//            case 0x0F:
//                return decoded + 0x37; // 0x41('A') - 0x46('F')
//            default:
//                throw new IllegalArgumentException("illegal half: " + decoded);
//        }
//    }
//
//
//    private static void percent(final int decoded, final byte[] encoded,
//                                final int offset) {
//
//        if ((decoded >= 0x30 && decoded <= 0x39) // digit
//            || (decoded >= 0x41 && decoded <= 0x5A) // upper case alpha
//            || (decoded >= 0x61 && decoded <= 0x7A) // lower case alpha
//            || decoded == 0x2D || decoded == 0x5F || decoded == 0x2E
//            || decoded == 0x7E) { // -_.~
//            encoded[offset] = (byte) decoded;
//        } else {
//            encoded[offset] = 0x25; // '%'
//            encoded[offset + 1] = (byte) percent(decoded >> 4);
//            encoded[offset + 2] = (byte) percent(decoded & 0x0F);
//        }
//    }
//
//
//    private static byte[] percent(final byte[] decoded) {
//
//        final byte[] encoded = new byte[(decoded.length << 2) + decoded.length];
//
//        int offset = 0;
//        for (int i = 0; i < decoded.length; i++) {
//            percent(decoded[i] & 0xFF, encoded, offset);
//            offset += encoded[offset] == 0x25 ? 3 : 1;
//        }
//
//        if (offset == encoded.length) {
//            return encoded;
//        }
//
//        final byte[] trimmed = new byte[offset];
//        System.arraycopy(encoded, 0, trimmed, 0, offset);
//
//        return trimmed;
//    }
//
//
//    private static String percent(final Object string) {
//
//        try {
//            final byte[] decoded = String.valueOf(string).getBytes("UTF-8");
//            return new String(percent(decoded), "US-ASCII");
//        } catch (UnsupportedEncodingException uee) {
//            throw new RuntimeException(uee);
//        }
//    }
    private static String encode(final String decoded) {

        if (decoded == null) {
            throw new NullPointerException("decoded");
        }

        try {
            return URLEncoder.encode(decoded.toString(), "UTF-8");
        } catch (final UnsupportedEncodingException uee) {
            throw new RuntimeException(uee);
        }
    }


    private static String decode(final String encoded) {

        if (encoded == null) {
            throw new NullPointerException("encoded");
        }

        try {
            return URLDecoder.decode(encoded.toString(), "UTF-8");
        } catch (final UnsupportedEncodingException uee) {
            throw new RuntimeException(uee);
        }
    }


    /**
     * Encodes given container name.
     *
     * @param containerName the container name
     *
     * @return the encoded value of given container name
     */
    private static String encodeContainerName(String containerName) {

        LOGGER.debug("encodeContainerName({})", containerName);

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

        containerName = encode(containerName);
        LOGGER.debug("encoded: {}", containerName);
        if (containerName.length() > CONTAINER_NAME_LENGTH_MAX) {
            throw new IllegalArgumentException(
                "encoded containerName's length(" + containerName.length()
                + " > (" + CONTAINER_NAME_LENGTH_MAX + ")");
        }

        return containerName;
    }


    /**
     * Encodes given object name.
     *
     * @param objectName the object name
     *
     * @return the encoded value of given object name
     */
    private static String encodeObjectName(String objectName) {

        if (objectName == null) {
            throw new NullPointerException("objectName");
        }

        objectName = objectName.trim();
        if (objectName.isEmpty()) {
            throw new IllegalArgumentException("empty objectName");
        }

        objectName = encode(objectName);
        if (objectName.length() > OBJECT_NAME_LENGTH_MAX) {
            throw new IllegalArgumentException(
                "encoded objectName's length(" + objectName.length()
                + "> (" + OBJECT_NAME_LENGTH_MAX + ")");
        }

        return objectName;
    }


    private static String h(final String d)
        throws UnsupportedEncodingException {

        final byte[] bytes = d.getBytes("UTF-8");

        final char[] chars = new char[bytes.length << 1];
        int index = 0;

        for (final byte b : bytes) {
            final String s = Integer.toHexString(b & 0xFF);
            if (s.length() == 1) {
                chars[index++] = '0';
                chars[index++] = s.charAt(0);
            } else {
                chars[index++] = s.charAt(0);
                chars[index++] = s.charAt(1);
            }
        }

        return new String(chars);
    }


    private static String d(final String h)
        throws UnsupportedEncodingException {

        final byte[] bytes = new byte[h.length() / 2];
        int index = 0;

        for (int i = 0; i < h.length(); i += 2) {
            bytes[index++] = (byte) Integer.parseInt(h.substring(i, i + 2), 16);
        }

        return new String(bytes, "UTF-8");
    }


    /**
     * Appends given query parameters onto specified base url.
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
            final String key = entry.getKey();
            if (key == null) {
                throw new IllegalArgumentException("null parameter key");
            }
            final Object value = entry.getValue();
            if (value == null) {
                throw new IllegalArgumentException("null parameter value");
            }
            builder.append("?")
                .append(encode(key))
                .append("=")
                .append(encode(value.toString()));
        }
        while (i.hasNext()) {
            final Entry<String, Object> entry = i.next();
            final String key = entry.getKey();
            if (key == null) {
                throw new IllegalArgumentException("null parameter key");
            }
            final Object value = entry.getValue();
            if (value == null) {
                throw new IllegalArgumentException("null parameter value");
            }
            builder.append("&")
                .append(encode(key))
                .append("=")
                .append(encode(value.toString()));
        }

        return builder.toString();
    }


    /**
     * Copies bytes from given input stream to specified output stream using
     * given buffer.
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
            throw new NullPointerException("input");
        }

        if (output == null) {
            throw new NullPointerException("output");
        }

        if (buffer == null) {
            throw new NullPointerException("buffer");
        }

        if (buffer.length == 0) {
            throw new IllegalArgumentException("buffer.length == 0");
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


    private static long getHeaderFieldLong(final URLConnection connection,
                                           final String fieldName,
                                           final long defaultValue) {
        try {
            final Method method = URLConnection.class.getMethod(
                "getHeaderFieldLong", String.class, Long.TYPE);
            try {
                return (Long) method.invoke(
                    connection, fieldName, defaultValue);
            } catch (IllegalAccessException iae) {
                LOGGER.debug("failed to invoke getHeaderFieldLong", iae);
            } catch (InvocationTargetException ite) {
                LOGGER.debug("failed to invoke getHeaderFieldLong", ite);
            }
        } catch (NoSuchMethodException nsme) {
            LOGGER.debug("no getHeaderFieldLong", nsme);
        }

        return defaultValue;
    }


    private static void getStorageProperties(
        final Map<String, List<String>> headerFields, final String keyPrefix,
        final String valuePrefix, final Map<String, String> storageProperties)
        throws IOException {

        LOGGER.debug("getStorageProperties({}, {}, {}, {})", headerFields,
                     keyPrefix, valuePrefix, storageProperties);

//        final Collection<StorageProperty> collected =
//            new ArrayList<StorageProperty>();
//        getStorageProperties(headerFields, keyPrefix, valuePrefix, collected);
//
//        StorageProperty.copy(collected, storageProperties);

        for (final Entry<String, List<String>> e : headerFields.entrySet()) {

            String fieldName = e.getKey();
            if (fieldName == null) {
                continue;
            }
            if (!fieldName.toLowerCase().startsWith(keyPrefix.toLowerCase())) {
                continue;
            }
            fieldName = fieldName.substring(keyPrefix.length());
            fieldName = d(fieldName);

            final List<String> fieldValues = e.getValue();
            if (fieldValues == null || fieldValues.isEmpty()) {
                continue;
            }
            String fieldValue = fieldValues.get(0);
            if (fieldValue == null) {
                continue;
            }
            if (!fieldValue.toLowerCase().startsWith(
                valuePrefix.toLowerCase())) {
                continue;
            }
            fieldValue = fieldValue.substring(valuePrefix.length());
            fieldValue = d(fieldValue);

            storageProperties.put(fieldName, fieldValue);
        }
    }


    /**
     *
     * @param headerFields the header fields
     * @param keyPrefix the name prefix
     * @param valuePrefix the value prefix
     * @param storageProperties the collection
     *
     * @throws IOException if an I/O error occurs.
     */
    private static void getStorageProperties(
        final Map<String, List<String>> headerFields, final String keyPrefix,
        final String valuePrefix,
        final Collection<? super StorageProperty> storageProperties)
        throws IOException {

        LOGGER.debug("getStorageProperties({}, {}, {}, {})", headerFields,
                     keyPrefix, valuePrefix, storageProperties);

        final Map<String, String> mapped = new HashMap<String, String>();
        getStorageProperties(headerFields, keyPrefix, valuePrefix, mapped);

        StorageProperty.copy(mapped, storageProperties);
    }


    private static void setStorageProperties(
        final URLConnection connection, final String keyPrefix,
        final String valuePrefix, final Map<String, String> storageProperties)
        throws IOException {

        LOGGER.debug("setStorageProperties({}, {}, {}, {})", connection,
                     keyPrefix, valuePrefix, storageProperties);

        for (final Entry<String, String> e : storageProperties.entrySet()) {
            final String key = keyPrefix + h(e.getKey());
            final String value = valuePrefix + h(e.getValue());
            connection.setRequestProperty(key, value);
        }
    }


    private static void setStorageProperties(
        final URLConnection connection, final String keyPrefix,
        final String valuePrefix,
        final Collection<? extends StorageProperty> storageProperties)
        throws IOException {

        LOGGER.debug("setStorageProperties({}, {}, {}, {})", connection,
                     keyPrefix, valuePrefix, storageProperties);

        final Map<String, String> mapped =
            new HashMap<String, String>(storageProperties.size());
        StorageProperty.copy(storageProperties, mapped);

        setStorageProperties(connection, keyPrefix, valuePrefix, mapped);
    }


    /**
     * Creates a new instance.
     *
     * @param storageUser user id
     * @param storagePass api key
     */
    public StorageClient(final String storageUser,
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


    public boolean peekStorageAccount() throws IOException {

        LOGGER.debug("peekStoageAccount()");

        if (!authenticate()) {
            return false;
        }

        final String base = storageUrl;

        final String spec = base;

        final URL url = new URL(spec);

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("HEAD");
        connection.setRequestProperty("X-Auth-Token", authToken);

        // 406 Not Acceptable without this header, damn.
        connection.setRequestProperty("Accept", "*/*");

        setTimeouts(connection);

        connection.connect();
        try {
            setResponses(connection);
            if (responseCode == RESPONSE_CODE_204_NO_CONTENT) {
                // do nothing
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
     * Retrieves a storage account.
     *
     * @return a storage account or {@code null} if failed.
     *
     * @throws IOException if an I/O error occurs.
     */
    public StorageAccount readStorageAccount() throws IOException {

        LOGGER.debug("readStorageAccount()");

        if (!peekStorageAccount()) {
            return null;
        }

        final StorageAccount storageAccount = new StorageAccount();

        storageAccount.setContainerCount(getFirstHeaderFieldLong(
            "X-Account-Container-Count", -1L));
        storageAccount.setObjectCount(getFirstHeaderFieldLong(
            "X-Account-Object-Count", -1L));
        storageAccount.setBytesUsed(getFirstHeaderFieldLong(
            "X-Account-Bytes-Used", -1L));

        return storageAccount;
    }


    public boolean readStorageAccountProperties(
        final Collection<? super StorageProperty> storageProperties)
        throws IOException {

        LOGGER.debug("readStorageAccountProperties({})", storageProperties);

        if (storageProperties == null) {
            throw new NullPointerException("storageProperties");
        }

        if (!peekStorageAccount()) {
            return false;
        }

        getStorageProperties(headerFields, "X-Account-Meta-0x", "0x",
                             storageProperties);

        return true;
    }


    public Collection<StorageProperty> readStorageAccountProperties()
        throws IOException {

        LOGGER.debug("readStorageAccountProperties()");

        final Collection<StorageProperty> storageProperties =
            new ArrayList<StorageProperty>();

        if (!readStorageAccountProperties(storageProperties)) {
            return null;
        }

        return storageProperties;
    }


    public boolean readStorageAccountProperties(
        final Map<String, String> storageProperties)
        throws IOException {

        LOGGER.debug("readStorageAccountProperties({})", storageProperties);

        if (storageProperties == null) {
            throw new NullPointerException("storageProperties");
        }

        final Collection<StorageProperty> collected =
            readStorageAccountProperties();
        if (collected == null) {
            return false;
        }

        StorageProperty.copy(collected, storageProperties);

        return true;
    }


    public boolean updateStorageAccountProperties(
        final Map<String, String> storageProperties)
        throws IOException {

        LOGGER.debug("updateStorageAccountProperties({})", storageProperties);

        if (storageProperties == null) {
            throw new NullPointerException("storageProperties");
        }

        final Map<String, String> previousProperties =
            new HashMap<String, String>();
        if (!readStorageAccountProperties(previousProperties)) {
            return false;
        }
        previousProperties.keySet().removeAll(storageProperties.keySet());

        if (false || !authenticate()) { // skip
            return false;
        }

        final String base = storageUrl;

        final String spec = base;

        final URL url = new URL(spec);

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("X-Auth-Token", authToken);

        setStorageProperties(connection, "X-Remove-Account-Meta-0x", "0x",
                             previousProperties);
        setStorageProperties(connection, "X-Account-Meta-0x", "0x",
                             storageProperties);

        setTimeouts(connection);

        connection.connect();
        try {
            setResponses(connection);
            if (responseCode == RESPONSE_CODE_204_NO_CONTENT) {
                // do nothing
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
     * Updates (replaces) all storage properties on the storage account.
     *
     * @param storageProperties storage properties
     *
     * @return {@code true} if succeeded; {@code false} otherwise.
     *
     * @throws IOException if an I/O error occurs.
     */
    public boolean updateStorageAccountProperties(
        final Collection<? extends StorageProperty> storageProperties)
        throws IOException {

        LOGGER.debug("updateStorageAccountProperties({})", storageProperties);

        if (storageProperties == null) {
            throw new NullPointerException("storageProperties");
        }

        final Map<String, String> mapped =
            new HashMap<String, String>(storageProperties.size());
        mapped.entrySet().addAll(storageProperties);

        return updateStorageAccountProperties(mapped);
    }


    /**
     * Deletes all storage properties on the storage account.
     *
     * @return {@code true} if succeeded; {@code false} otherwise.
     *
     * @throws IOException if an I/O error occurs.
     */
    public boolean deleteStorageAccountProperties() throws IOException {

        return updateStorageAccountProperties(
            Collections.<String, String>emptyMap());
    }


    public String readStorageAccountProperty(final String propertykey)
        throws IOException {

        LOGGER.debug("readStorageAccountProperty({})", propertykey);

        if (propertykey == null) {
            throw new NullPointerException("propertyKey");
        }

        final Collection<StorageProperty> storageProperties =
            readStorageAccountProperties();
        if (storageProperties == null) {
            return null;
        }

        for (StorageProperty storageProperty : storageProperties) {
            if (propertykey.equals(storageProperty.getKey())) {
                return storageProperty.getValue();
            }
        }

        return null;
    }


    public boolean updateStorageAccountProperty(
        final StorageProperty storageProperty)
        throws IOException {

        LOGGER.debug("updateStorageAccountProperty({}, {})", storageProperty);

        if (storageProperty == null) {
            throw new NullPointerException("storageProperty");
        }

        if (!authenticate()) {
            return false;
        }

        final String base = storageUrl;

        final URL url = new URL(base);

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("X-Auth-Token", authToken);

        setStorageProperties(connection, "X-Account-Meta-0x", "0x",
                             Collections.singleton(storageProperty));

        setTimeouts(connection);

        connection.connect();
        try {
            setResponses(connection);
            if (responseCode == RESPONSE_CODE_204_NO_CONTENT) {
                return true;
            }
        } finally {
            connection.disconnect();
        }

        return false;
    }


    public boolean updateStorageAccountProperty(final String propertyKey,
                                                final String propertyValue)
        throws IOException {

        LOGGER.debug("updateStorageAccountProperty({}, {})", propertyKey,
                     propertyValue);

        if (propertyKey == null) {
            throw new NullPointerException("propertyKey");
        }

        if (propertyValue == null) {
            throw new NullPointerException("propertyValue");
        }

        final StorageProperty storageProperty = new StorageProperty();
        storageProperty.setKey(propertyKey);
        storageProperty.setValue(propertyValue);

        return updateStorageAccountProperty(storageProperty);
    }


    /**
     * Deletes an storage account properties mapped to given name.
     *
     * @param propertyKey the name
     *
     * @return {@code true} if succeeded; {@code false} if failed.
     *
     * @throws IOException if an I/O error occurs.
     */
    public boolean deleteStorageAccountProperty(final String propertyKey)
        throws IOException {

        LOGGER.debug("deleteStorageAccountProperty({})", propertyKey);

        if (propertyKey == null) {
            throw new NullPointerException("propertyKey");
        }

        if (!authenticate()) {
            return false;
        }

        final String base = storageUrl;

        final String spec = base;

        final URL url = new URL(spec);

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("X-Auth-Token", authToken);

        final StorageProperty storageProperty = new StorageProperty();
        storageProperty.setKey(propertyKey);
        storageProperty.setValue("delete");

        setStorageProperties(connection, "X-Remove-Account-Meta-0x", "0x",
                             Collections.singletonList(storageProperty));

        setTimeouts(connection);

        connection.connect();
        try {
            setResponses(connection);
            if (responseCode == RESPONSE_CODE_204_NO_CONTENT) {
                return true;
            }
        } finally {
            connection.disconnect();
        }

        return false;
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


    public boolean peekStorageContainer(final String containerName)
        throws IOException {

        LOGGER.debug("peekStorageContainer({})", containerName);

        // check
        final String encodedContainerName = encodeContainerName(containerName);

        if (!authenticate()) {
            return false;
        }

        final String base = storageUrl + PATH_SEPARATOR + encodedContainerName;

        final String spec = base;

        final URL url = new URL(spec);

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("HEAD");
        connection.setRequestProperty("X-Auth-Token", authToken);

        // 406
        connection.setRequestProperty("Accept", "*/*");

        setTimeouts(connection);

        connection.connect();
        try {
            setResponses(connection);
            if (responseCode == RESPONSE_CODE_204_NO_CONTENT) {
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

        final String base = storageUrl + PATH_SEPARATOR + encodedContainerName;

        final String spec = base;

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
                // do nothing
            }
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

        if (!peekStorageContainer(containerName)) {
            return null;
        }

        final StorageContainer storageContainer = new StorageContainer();

        storageContainer.setContainerName(containerName);
        storageContainer.setObjectCount(getFirstHeaderFieldLong(
            "X-Container-Object-Count", -1L));
        storageContainer.setBytesUsed(getFirstHeaderFieldLong(
            "X-Container-Bytes-Used", -1L));

        return storageContainer;
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

        final String base = storageUrl + PATH_SEPARATOR + encodedContainerName;

        final String spec = base;

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
                // do nothing
            }
            if (responseCode == RESPONSE_CODE_204_NO_CONTENT
                || responseCode == RESPONSE_CODE_404_NOT_FOUND) {
                return true;
            }
        } finally {
            connection.disconnect();
        }

        return false;
    }


    public boolean readStorageContainerProperties(
        final String containerName,
        final Collection<? super StorageProperty> storageProperties)
        throws IOException {

        LOGGER.debug("readStorageContainerProperties({}, {})", containerName,
                     storageProperties);

        if (storageProperties == null) {
            throw new NullPointerException("storageProperties");
        }

        if (!peekStorageContainer(containerName)) {
            return false;
        }

        getStorageProperties(headerFields, "X-Container-Meta-0x", "0x",
                             storageProperties);

        return true;
    }


    public Collection<StorageProperty> readStorageContainerProperties(
        final String containerName)
        throws IOException {

        LOGGER.debug("readStorageContainerProperties({})", containerName);

        final Collection<StorageProperty> storageProperties =
            new ArrayList<StorageProperty>();

        if (!readStorageContainerProperties(containerName, storageProperties)) {
            return null;
        }

        return storageProperties;
    }


    public boolean readStorageContainerProperties(
        final String containerName, final Map<String, String> storageProperties)
        throws IOException {

        LOGGER.debug("readStorageContainerProperties({}, {})", containerName,
                     storageProperties);

        if (storageProperties == null) {
            throw new NullPointerException("storageProperties");
        }

        final Collection<StorageProperty> collected =
            readStorageContainerProperties(containerName);
        if (collected == null) {
            return false;
        }

        try {
            storageProperties.entrySet().addAll(collected); // unsupported
        } catch (UnsupportedOperationException uoe) {
            for (final StorageProperty storageProperty : collected) {
                storageProperties.put(storageProperty.getKey(),
                                      storageProperty.getValue());
            }
        }

        return true;
    }


    public boolean updateStorageContainerProperties(
        final String containerName, final Map<String, String> storageProperties)
        throws IOException {

        LOGGER.debug("updateStorageContainerProperties({})", storageProperties);

        if (storageProperties == null) {
            throw new NullPointerException("storageProperties");
        }

        final Map<String, String> previousProperties =
            new HashMap<String, String>();
        if (!readStorageContainerProperties(
            containerName, previousProperties)) {
            return false;
        }
        previousProperties.keySet().removeAll(storageProperties.keySet());

        if (false || !authenticate()) { // skip
            return false;
        }

        final String base = storageUrl;

        final String spec = base;

        final URL url = new URL(spec);

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("X-Auth-Token", authToken);

        setStorageProperties(connection, "X-Remove-Container-Meta-0x", "0x",
                             previousProperties);
        setStorageProperties(connection, "X-Container-Meta-0x", "0x",
                             storageProperties);

        setTimeouts(connection);

        connection.connect();
        try {
            setResponses(connection);
            if (responseCode == RESPONSE_CODE_204_NO_CONTENT) {
                return true;
            }
        } finally {
            connection.disconnect();
        }

        return false;
    }


    public boolean updateStorageContainerProperties(
        final String containerName,
        final Collection<? extends StorageProperty> storageProperties)
        throws IOException {

        LOGGER.debug("updateStorageContainerProperties({})", storageProperties);

        if (storageProperties == null) {
            throw new NullPointerException("storageProperties");
        }

        final Map<String, String> mapped =
            new HashMap<String, String>(storageProperties.size());
        StorageProperty.copy(storageProperties, mapped);

        return updateStorageContainerProperties(containerName, mapped);
    }


    public boolean deleteStorageContainerProperties(final String containerName)
        throws IOException {

        return updateStorageContainerProperties(
            containerName, Collections.<String, String>emptyMap());
    }


    public String readStorageContainerProperty(
        final String containerName, final String propertyKey)
        throws IOException {

        LOGGER.debug("readStorageContainerProperty({}, {})", containerName,
                     propertyKey);

        if (propertyKey == null) {
            throw new NullPointerException("storageProperty");
        }

        final Map<String, String> storageProperties =
            new HashMap<String, String>();
        if (!readStorageContainerProperties(containerName, storageProperties)) {
            return null;
        }

        return storageProperties.get(propertyKey);
    }


    public boolean updateStorageContainerProperty(
        final String containerName, final StorageProperty storageProperty)
        throws IOException {

        LOGGER.debug("updateStorageContainerProperty({}, {})",
                     containerName, storageProperty);

        // check
        final String encodedContainerName = encodeContainerName(containerName);

        if (storageProperty == null) {
            throw new NullPointerException("storageProperty");
        }

        if (!authenticate()) {
            return false;
        }

        final String base = storageUrl + PATH_SEPARATOR + encodedContainerName;

        final String spec = base;

        final URL url = new URL(spec);

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("X-Auth-Token", authToken);

        setStorageProperties(connection, "X-Container-Meta-0x", "0x",
                             Collections.singleton(storageProperty));

        setTimeouts(connection);

        connection.connect();
        try {
            setResponses(connection);
            if (responseCode == RESPONSE_CODE_204_NO_CONTENT) {
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
     * Updates a metadata value with given metadata name of the storage
     * container mapped to given container name.
     *
     * @param containerName the container name
     * @param propertyKey the metadata name
     * @param propertyValue the metadata value
     *
     * @return {@code true} if succeeded; {@code false} otherwise.
     *
     * @throws IOException if an I/O error occurs.
     */
    public boolean updateStorageContainerProperty(final String containerName,
                                                  final String propertyKey,
                                                  final String propertyValue)
        throws IOException {

        LOGGER.debug("updateStorageContainerProperty({}, {}, {})",
                     containerName, propertyKey, propertyValue);

        if (propertyKey == null) {
            throw new NullPointerException("propertyKey");
        }

        if (propertyValue == null) {
            throw new NullPointerException("propertyValue");
        }

        final StorageProperty storageProperty = new StorageProperty();

        storageProperty.setKey(propertyKey);
        storageProperty.setValue(propertyValue);

        return updateStorageContainerProperty(containerName, storageProperty);
    }


    public boolean deleteStorageContainerProperty(final String containerName,
                                                  final String propertyKey)
        throws IOException {

        LOGGER.debug("deleteStorageContainerProperty({}, {})", containerName,
                     propertyKey);

        // check
        final String encodedContainerName = encodeContainerName(containerName);

        if (propertyKey == null) {
            throw new NullPointerException("propertyKey");
        }

        if (!authenticate()) {
            return false;
        }

        final String base = storageUrl + PATH_SEPARATOR + encodedContainerName;

        final String spec = base;

        final URL url = new URL(spec);

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("X-Auth-Token", authToken);

        final StorageProperty storageProperty = new StorageProperty();
        storageProperty.setKey(propertyKey);
        storageProperty.setValue("delete");

        setStorageProperties(connection, "X-Remove-Container-Meta-0x", "0x",
                             Collections.singletonList(storageProperty));

        setTimeouts(connection);

        connection.connect();
        try {
            setResponses(connection);
            if (responseCode == RESPONSE_CODE_204_NO_CONTENT) {
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
                            lastModified.setTimeZone(UTC);
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
    public boolean peekStorageObject(final String containerName,
                                     final String objectName)
        throws IOException {

        LOGGER.debug("peekStorageObject({}, {})", containerName, objectName);

        final String encodedContainerName = encodeContainerName(containerName);

        final String encodedObjectName = encodeObjectName(objectName);

        if (!authenticate()) {
            return false;
        }

        final String base = storageUrl + PATH_SEPARATOR + encodedContainerName
                            + PATH_SEPARATOR + encodedObjectName;

        final String spec = base;

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
                return true;
            }
        } finally {
            connection.disconnect();
        }

        return false;
    }


    public StorageObject readStorageObject(final String containerName,
                                           final String objectName)
        throws IOException {

        LOGGER.debug("readStorageObject({}, {})", containerName, objectName);

        if (!peekStorageObject(containerName, objectName)) {
            return null;
        }

        final StorageObject storageObject = new StorageObject();

        storageObject.setObjectName(objectName);
        storageObject.setLastModified(
            getFirstHeaderFieldLong("Last-Modified", -1L));
        storageObject.setEntityTag(getFirstHeaderFieldValue("Etag"));
        storageObject.setContentType(getFirstHeaderFieldValue("Content-Type"));
        storageObject.setContentLength(
            getFirstHeaderFieldLong("Content-Length", -1L));

        return storageObject;
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

        final String spec = base;

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

        final String base = storageUrl + PATH_SEPARATOR + encodedContainerName
                            + PATH_SEPARATOR + encodedObjectName;

        final String spec = base;

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


    public boolean readStorageObjectProperties(
        final String containerName, final String objectName,
        final Collection<? super StorageProperty> storageProperties)
        throws IOException {

        LOGGER.debug("readStorageObjectProperties({}, {}, {})", containerName,
                     objectName, storageProperties);

        if (storageProperties == null) {
            throw new NullPointerException("storageProperties");
        }

        if (!peekStorageObject(containerName, objectName)) {
            return false;
        }

        getStorageProperties(headerFields, "X-Object-Meta-0x", "0x",
                             storageProperties);

        return true;
    }


    public Collection<StorageProperty> readStorageObjectProperties(
        final String containerName, final String objectName)
        throws IOException {

        LOGGER.debug("readStorageObjectProperties({}, {})", containerName,
                     objectName);

        final Collection<StorageProperty> storageProperties =
            new ArrayList<StorageProperty>();

        if (!readStorageObjectProperties(containerName, objectName,
                                         storageProperties)) {
            return null;
        }

        return storageProperties;
    }


    public boolean readStorageObjectProperties(
        final String containerName, final String objectName,
        final Map<String, String> storageProperties)
        throws IOException {

        LOGGER.debug("readStorageObjectProperties({}, {}, {})", containerName,
                     objectName, storageProperties);

        if (storageProperties == null) {
            throw new NullPointerException("storageProperties");
        }

        final Collection<StorageProperty> collected =
            readStorageObjectProperties(containerName, objectName);
        if (collected == null) {
            return false;
        }

        try {
            storageProperties.entrySet().addAll(collected);
        } catch (UnsupportedOperationException uoe) {
            for (StorageProperty storageProperty : collected) {
                storageProperties.put(storageProperty.getKey(),
                                      storageProperty.getValue());
            }
        }

        return true;
    }


    public Map<String, String> readStorageObjectMetadata(
        final String containerName, final String objectName)
        throws IOException {

        LOGGER.debug("readStorageObjectMetadata({}, {})", containerName,
                     objectName);

        final Map<String, String> objectMetadata =
            new HashMap<String, String>();

        if (!readStorageObjectProperties(containerName, objectName,
                                         objectMetadata)) {
            return null;
        }

        return objectMetadata;
    }


    public boolean updateStorageObjectProperties(
        final String containerName, final String objectName,
        final Map<String, String> storageProperties)
        throws IOException {

        LOGGER.debug("updateStorageObjectProperties({}, {}, {})", containerName,
                     objectName, storageProperties);

        final String encodedContainerName = encodeContainerName(containerName);

        final String encodedObjectName = encodeObjectName(objectName);

        if (storageProperties == null) {
            throw new NullPointerException("storageProperties");
        }

        if (!authenticate()) {
            return false;
        }

        final String base = storageUrl + PATH_SEPARATOR + encodedContainerName
                            + PATH_SEPARATOR + encodedObjectName;

        final String spec = base;

        final URL url = new URL(spec);

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("X-Auth-Token", authToken);

        setStorageProperties(connection, "X-Object-Meta-0x", "0x",
                             storageProperties);

        setTimeouts(connection);

        connection.connect();
        try {
            setResponses(connection);
            if (responseCode == RESPONSE_CODE_202_ACCEPTED) {
            }
            if (responseCode == RESPONSE_CODE_202_ACCEPTED) {
                return true;
            }
        } finally {
            connection.disconnect();
        }

        return false;
    }


    public boolean updateStorageObjectProperties(
        final String containerName, final String objectName,
        final Collection<? extends StorageProperty> storageProperties)
        throws IOException {

        LOGGER.debug("updateStorageObjectProperties({}, {}, {})", containerName,
                     objectName, storageProperties);

        if (storageProperties == null) {
            throw new NullPointerException("storageProperties");
        }

        final Map<String, String> mapped =
            new HashMap<String, String>(storageProperties.size());
        mapped.entrySet().addAll(storageProperties);

        return updateStorageObjectProperties(containerName, objectName, mapped);
    }


    /**
     * Sets timeouts to given {@code connection}.
     *
     * @param connection the connection
     */
    private void setTimeouts(final URLConnection connection) {

        if (connection == null) {
            throw new NullPointerException("connection");
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

        if (headerFields == null) {
            throw new IllegalStateException("no header fields");
        }

        return headerFields;
    }


    public List<String> getHeaderFieldValues(final String fieldName) {

        LOGGER.debug("getHeaderFieldValues({})", fieldName);

        if (fieldName == null) {
            throw new NullPointerException("fieldName");
        }

        for (Entry<String, List<String>> entry : getHeaderFields().entrySet()) {
            if (fieldName.equalsIgnoreCase(entry.getKey())) {
                LOGGER.debug("-> {}", entry.getValue());
                return entry.getValue();
            }
        }

        return null;
    }


    public String getFirstHeaderFieldValue(final String fieldName) {

        LOGGER.debug("getFirstHeaderFieldValue({})", fieldName);

        final List<String> headerFieldValues = getHeaderFieldValues(fieldName);
        if (headerFieldValues == null || headerFieldValues.isEmpty()) {
            return null;
        }

        LOGGER.debug("-> {}", headerFieldValues.get(0));
        return headerFieldValues.get(0);
    }


    public long getFirstHeaderFieldLong(final String fieldName,
                                        final long defaultValue) {

        LOGGER.debug("getFirstHeaderFieldLong({}, {})", fieldName,
                     defaultValue);

        try {
            return Long.parseLong(getFirstHeaderFieldValue(fieldName));
        } catch (NullPointerException npe) {
        } catch (NumberFormatException nfe) {
        }

        return defaultValue;
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
