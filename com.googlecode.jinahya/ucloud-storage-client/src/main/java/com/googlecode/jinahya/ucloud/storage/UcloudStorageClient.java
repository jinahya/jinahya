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


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class UcloudStorageClient {


    private static final String AUTH_URL =
        "https://api.ucloudbiz.olleh.com/storage/v1/auth";


    /**
     * Appends given
     * <code>queryParams</code> to specified
     * <code>baseUrl</code>.
     *
     * @param baseUrl base url
     * @param queryParams query params
     * @return append url
     * @throws UnsupportedEncodingException
     */
    private static String append(final String baseUrl,
                                 final Map<String, String> queryParams)
        throws UnsupportedEncodingException {

        if (baseUrl == null) {
            throw new IllegalArgumentException("null baseUri");
        }

        if (queryParams == null || queryParams.isEmpty()) {
            return baseUrl;
        }

        final StringBuilder builder = new StringBuilder(baseUrl);

        final Iterator<Entry<String, String>> entries =
            queryParams.entrySet().iterator();
        if (entries.hasNext()) {
            builder.append("?");
            final Entry<String, String> entry = entries.next();
            builder.append(URLEncoder.encode(entry.getKey(), "UTF-8")).
                append("=").
                append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        while (entries.hasNext()) {
            builder.append("&");
            final Entry<String, String> entry = entries.next();
            builder.append(URLEncoder.encode(entry.getKey(), "UTF-8")).
                append("=").
                append(URLEncoder.encode(entry.getValue(), "UTF-8"));
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

        if (storageUser.trim().isEmpty()) {
            throw new IllegalArgumentException("empty storageUser");
        }

        if (storagePass == null) {
            throw new IllegalArgumentException("null storagePass");
        }

        if (storagePass.trim().isEmpty()) {
            throw new IllegalArgumentException("empty storagePass");
        }

        this.storageUser = storageUser;
        this.storagePass = storagePass;
    }


    /**
     * Authenticates.
     *
     * @return true if succeeded; false otherwise
     * @throws IOException if an I/O error occurs.
     */
    private boolean authenticate() throws IOException {

        System.out.println("authenticate()");

        final URL url = new URL(AUTH_URL);

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setRequestProperty("X-Storage-User", storageUser);
        connection.setRequestProperty("X-Storage-Pass", storagePass);

        setTimeout(connection);

        connection.connect();

        setResponse(connection);

        if (responseCode == 200) {
            storageUrl = connection.getHeaderField("X-Storage-URL");
            System.out.println("storageUrl: " + storageUrl);
            authToken = connection.getHeaderField("X-Auth-Token");
            System.out.println("authToken: " + authToken);
            return true;
        }

        connection.disconnect();

        return false;
    }


    /**
     *
     * @param queryParams
     * @param containerNames
     * @return
     * @throws IOException
     */
    public boolean readContainerNames(final Map<String, String> queryParams,
                                      final Collection<String> containerNames)
        throws IOException {

        System.out.println("readContainerNames(" + queryParams + ", "
                           + containerNames + ")");

        if (queryParams == null) {
            // ok
            //throw new IllegalArgumentException("null queryParams");
        }

        if (containerNames == null) {
            throw new IllegalArgumentException("null containerNames");
        }

        if (!authenticate()) {
            return false;
        }

        final String path = storageUrl;
        System.out.println("path: " + storageUrl);
        final URL url = new URL(append(storageUrl, queryParams));
        System.out.println("url: " + url.toExternalForm());

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-Auth-Token", authToken);

        setTimeout(connection);

        connection.connect();

        setResponse(connection);

        if (responseCode == 200) {
            final BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "UTF-8"));
            for (String line = null; (line = reader.readLine()) != null;) {
                containerNames.add(line);
            }
            reader.close();
        }

        connection.disconnect();

        if (responseCode == 200 || responseCode == 204) {
            return true;
        }

        return false;
    }


    /**
     * Creates a container.
     *
     * @param containerName container name
     * @return true if succeeded; false otherwise
     * @throws IOException if an I/O error occurs.
     */
    public boolean createContainer(final String containerName)
        throws IOException {

        System.out.println("createContainer(" + containerName + ")");

        if (containerName == null) {
            throw new IllegalArgumentException("null containerName");
        }

        if (!authenticate()) {
            return false;
        }

        final URL url = new URL(
            storageUrl + "/" + URLEncoder.encode(containerName, "UTF-8"));

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("PUT");
        connection.setRequestProperty("X-Auth-Token", authToken);

        setTimeout(connection);

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
     * @return true if succeeded; false otherwise
     * @throws IOException if an I/O error occurs.
     */
    public boolean deleteContainer(final String containerName)
        throws IOException {

        System.out.println("deleteContainer(" + containerName + ")");

        if (containerName == null) {
            throw new IllegalArgumentException("null containerName");
        }

        if (!authenticate()) {
            return false;
        }

        final URL url = new URL(
            storageUrl + "/" + URLEncoder.encode(containerName, "UTF-8"));

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("DELETE");
        connection.setRequestProperty("X-Auth-Token", authToken);

        setTimeout(connection);

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
     * @param queryParams
     * @param objectNames
     * @return true if succeeded false otherwise
     * @throws IOException if an I/O error occurs
     */
    public boolean readObjectNames(final String containerName,
                                   final Map<String, String> queryParams,
                                   final Collection<String> objectNames)
        throws IOException {

        if (containerName == null) {
            throw new IllegalArgumentException("null containerName");
        }

        if (queryParams == null) {
            // ok
            //throw new IllegalArgumentException("null queryParams");
        }

        if (objectNames == null) {
            throw new IllegalArgumentException("null objects");
        }

        if (!authenticate()) {
            return false;
        }

        final String path =
            storageUrl + "/" + URLEncoder.encode(containerName, "UTF-8");
        System.out.println("path: " + path);
        final URL url = new URL(append(path, queryParams));

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setRequestProperty("X-Auth-Token", authToken);

        setTimeout(connection);

        connection.connect();

        setResponse(connection);

        if (responseCode == 200) {
            final BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "UTF-8"));
            for (String line = null; (line = reader.readLine()) != null;) {
                objectNames.add(line);
            }
            reader.close();
        }

        connection.disconnect();

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
     * @return true if succeeded; false otherwise
     * @throws IOException if an I/O error occurs
     */
    public boolean updateObject(final String containerName,
                                final String objectName,
                                final String contentType,
                                final byte[] contentData)
        throws IOException {

        System.out.println("updateObject(" + containerName + "," + objectName
                           + "," + contentType + "," + contentData + ")");

        if (contentData == null) {
            throw new IllegalArgumentException("null contentData");
        }

        return updateObject(containerName, objectName, contentType,
                            contentData.length,
                            new ByteArrayInputStream(contentData));
    }


    public boolean updateObject(final String containerName,
                                final String objectName,
                                final ContentProducer contentProducer)
        throws IOException {

        System.out.println("updateObject(" + containerName + "," + objectName
                           + "," + contentProducer + ")");

        if (contentProducer == null) {
            throw new IllegalArgumentException("null contentProducer");
        }

        return updateObject(containerName, objectName,
                            contentProducer.getType(),
                            contentProducer.getLength(),
                            contentProducer.getData());
    }


    /**
     * Updates object.
     *
     * @param containerName container name
     * @param objectName object name
     * @param contentType content type
     * @param contentLength content length; <code>-1L</code> for unknown
     * @param contentData content data
     * @return true if succeeded; false otherwise
     * @throws IOException if an I/O error occurs
     */
    public boolean updateObject(final String containerName,
                                final String objectName,
                                final String contentType,
                                final long contentLength,
                                final InputStream contentData)
        throws IOException {

        System.out.println("updateObject(" + containerName + "," + objectName
                           + "," + contentType + "," + contentLength + ","
                           + contentData + ")");

        if (containerName == null) {
            throw new IllegalArgumentException("null containerName");
        }

        if (objectName == null) {
            throw new IllegalArgumentException("null objectName");
        }

        if (contentType == null) {
            throw new IllegalArgumentException("null contentType");
        }

        if (contentData == null) {
            throw new IllegalArgumentException("null contentData");
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
        connection.setRequestProperty("Content-Type", contentType);
        final boolean chunked = contentLength == -1L;
        if (chunked) {
            connection.setRequestProperty("Transfer-Encoding", "chunked");
        } else {
            connection.setRequestProperty(
                "Content-Length", Long.toString(contentLength));
        }

        /*
         * Note that ucloud storage actaully don't work for
         * 'Transfer-Encoding: chunked' as it intended.
         * They just reads all octets and sets 'Connection: close'
         */

        setTimeout(connection);

        connection.connect();

        final OutputStream output = connection.getOutputStream();
        final byte[] buffer = new byte[8192];
        for (int read = -1; (read = contentData.read(buffer)) != -1;) {
            if (read == 0) {
                continue;
            }
            if (false && chunked) {
                output.write(Integer.toHexString(read).getBytes("US-ASCII"));
                output.write('\r');
                output.write('\n');
            }
            output.write(buffer, 0, read);
            if (false && chunked) {
                output.write('\r');
                output.write('\n');
            }
        }
        if (false && chunked) {
            output.write('0');
            output.write('\r');
            output.write('\n');
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
     * Reads an object.
     *
     * @param containerName container name
     * @param objectName object name
     * @param contentType the array whose first index is set to content type;
     * may be <code>null</code> but must not be zero-length.
     * @param contentLength the array whose first index is set to content
     * length; may be <code>null</code> but must not be zero-length
     * @param contentData the output stream to which content data is written
     * @return true if succeeded; false otherwise
     * @throws IOException if an I/O error occurs.
     */
    public boolean readObject(final String containerName,
                              final String objectName,
                              final String[] contentType,
                              final Long[] contentLength,
                              final OutputStream contentData)
        throws IOException {

        if (containerName == null) {
            throw new IllegalArgumentException("null containerName");
        }

        if (objectName == null) {
            throw new IllegalArgumentException("null objectName");
        }

        if (contentType != null && contentType.length == 0) {
            throw new IllegalArgumentException(
                "contentType.length(" + contentType.length + ") == 0");
        }

        if (contentLength != null && contentLength.length == 0) {
            throw new IllegalArgumentException(
                "contentLength.length(" + contentLength.length + ") == 0");
        }

        if (contentData == null) {
            throw new IllegalArgumentException("null contentData");
        }

        final ContentConsumer contentConsumer = new ContentConsumer() {
            @Override
            public void setType(final String type) {
                if (contentType != null) {
                    contentType[0] = type;
                }
            }


            @Override
            public void setLength(long length) {
                if (contentLength != null) {
                    contentLength[0] = Long.valueOf(length);
                }
            }


            @Override
            public void setData(final InputStream data) throws IOException {
                final byte[] buffer = new byte[8192];
                for (int read = -1; (read = data.read(buffer)) != -1;) {
                    contentData.write(buffer, 0, read);
                }
            }
        };

        return readObject(containerName, objectName, contentConsumer);
    }


    public boolean readObject(final String containerName,
                              final String objectName,
                              final ContentConsumer contentConsumer)
        throws IOException {

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

        final URL url = new URL(
            storageUrl + "/" + URLEncoder.encode(containerName, "UTF-8") + "/"
            + URLEncoder.encode(objectName, "UTF-8"));

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-Auth-Token", authToken);

        setTimeout(connection);

        connection.connect();

        setResponse(connection);

        if (responseCode == 200) {

            contentConsumer.setType(connection.getContentType());

            contentConsumer.setLength(connection.getContentLength());
            try {
                // getCotnentLengthLong(); Since 7.0
                contentConsumer.setLength((Long) URLConnection.class.getMethod(
                    "getContentLengthLong").invoke(connection));
            } catch (NoSuchMethodException nsme) {
            } catch (IllegalAccessException iae) {
            } catch (InvocationTargetException ite) {
            }

            final InputStream input = connection.getInputStream();
            try {
                contentConsumer.setData(input);
            } finally {
                input.close();
            }
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
     * @return true if succeeded; false otherwise
     * @throws IOException if an I/O error occurs.
     */
    public boolean deleteObject(final String containerName,
                                final String objectName)
        throws IOException {

        System.out.println("deleteObject(" + containerName + ", " + objectName
                           + ")");

        if (containerName == null) {
            throw new IllegalArgumentException("null containerName");
        }

        if (objectName == null) {
            throw new IllegalArgumentException("null objectName");
        }

        if (!authenticate()) {
            return false;
        }

        final URL url = new URL(
            storageUrl + "/" + URLEncoder.encode(containerName, "UTF-8") + "/"
            + URLEncoder.encode(objectName, "UTF-8"));

        final HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("DELETE");
        connection.setRequestProperty("X-Auth-Token", authToken);

        setTimeout(connection);

        connection.connect();

        setResponse(connection);

        connection.disconnect();

        if (responseCode == 204 || responseCode == 404) {
            return true;
        }

        return false;
    }


    private void setTimeout(final URLConnection connection) {

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


    private final String storageUser;


    private final String storagePass;


    private transient String storageUrl;


    private transient String authToken;


    private transient Integer connectTimeout;


    private transient Integer readTimeout;


    private transient int responseCode;


    private transient String responseMessage;


}

