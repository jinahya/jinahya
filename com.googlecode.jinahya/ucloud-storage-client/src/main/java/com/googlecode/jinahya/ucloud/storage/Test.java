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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class Test {


    private static final String AUTH_URL =
        "https://api.ucloudbiz.olleh.com/storage/v1/auth";


    private static final String STORAGE_USER =
        "server_admin@ticorporation.co.kr";


    private static final String STORAGE_PASS =
        "MTM0MDAzMTkwMzEzNDAwMzEzMTc2NTE0";


    public static void main(final String[] args) throws IOException {

        String storageUrl;
        String authToken;

        //final String storageUser = args[0];
        //final String storagePass = args[1];

        // authenticate
        {
            System.out.println("authenticate");
            final URL url = new URL(AUTH_URL);
            System.out.println("url: " + url.toExternalForm());
            final HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("X-Storage-User", STORAGE_USER);
            connection.setRequestProperty("X-Storage-Pass", STORAGE_PASS);
            connection.connect();
            final int responseCode = connection.getResponseCode();
            final String responseMessage = connection.getResponseMessage();
            System.out.println(
                "response: " + responseCode + " " + responseMessage);
            if (responseCode != 200) {
                return;
            }
            storageUrl = connection.getHeaderField("X-Storage-URL");
            System.out.println("storageUrl: " + storageUrl);
            authToken = connection.getHeaderField("X-Auth-Token");
            System.out.println("authToken: " + authToken);
            connection.disconnect();
        }

        final String containerName = "KT클라우드 스토리지팀";

        // create container
        {
            final URL url = new URL(
                storageUrl + "/" + URLEncoder.encode(containerName, "UTF-8"));
            final HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("X-Auth-Token", authToken);
            connection.connect();
            final int responseCode = connection.getResponseCode();
            final String responseMessage = connection.getResponseMessage();
            System.out.println(
                "response: " + responseCode + " " + responseMessage);;
            connection.disconnect();
            if (responseCode != 201 && responseCode != 202) {
                return;
            }
        }

        // list containers
        {
            System.out.println("list containers without parameters");
            final URL url = new URL(storageUrl);
            System.out.println("url: " + url.toExternalForm());
            final HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-Auth-Token", authToken);
            connection.connect();
            final int responseCode = connection.getResponseCode();
            final String responseMessage = connection.getResponseMessage();
            System.out.println(
                "response: " + responseCode + " " + responseMessage);;
            if (responseCode == 200) {
                final BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "UTF-8"));
                for (String line = null; (line = reader.readLine()) != null;) {
                    System.out.println("\tcontainerName: " + line);
                }
                reader.close();
            }
            connection.disconnect();
        }


        // list containers with format
        {
            System.out.println("list containers with parameters");
            final URL url = new URL(storageUrl + "?format=xml");
            System.out.println("url: " + url.toExternalForm());
            final HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-Auth-Token", authToken);
            connection.connect();
            final int responseCode = connection.getResponseCode();
            final String responseMessage = connection.getResponseMessage();
            System.out.println(
                "response: " + responseCode + " " + responseMessage);;
            if (responseCode == 200) {
                final BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "UTF-8"));
                for (String line = null; (line = reader.readLine()) != null;) {
                    System.out.println("\tcontainerName: " + line);
                }
                reader.close();
            }
            connection.disconnect();
        }


        final String objectName = "김휴민";

        // update(create) object
        {
            System.out.println("creating object");
            final URL url = new URL(
                storageUrl + "/" + URLEncoder.encode(containerName, "UTF-8")
                + "/" + URLEncoder.encode(objectName, "UTF-8"));
            System.out.println("url: " + url.toExternalForm());
            final HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);
            connection.setRequestProperty("X-Auth-Token", authToken);
            connection.setRequestProperty("Content-Type",
                                          "application/octet-stream");
            connection.setRequestProperty("Content-Length", "0");
            connection.connect();
            final OutputStream output = connection.getOutputStream();
            output.flush();
            output.close();
            final int responseCode = connection.getResponseCode();
            final String responseMessage = connection.getResponseMessage();
            System.out.println(
                "response: " + responseCode + " " + responseMessage);
            connection.disconnect();
            if (responseCode != 201) {
                return;
            }
        }
    }
}