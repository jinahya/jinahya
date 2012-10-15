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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Test(singleThreaded = true)
public class StorageClientHttpTest {


    private static final String[] CONTAINER_NAMES = new String[]{
        "test",
        "테스트"
    };


    private static final String[] OBJECT_NAMES = new String[]{
        "abc",
        "def/ghi",
        "!@#$%^&*()",
        "가나다",
        "abc/def",
        "라마바/사아자"
    };


    @BeforeClass()
    public void readUserAndPass() {

        storageUser = System.getProperty("storageUser");
        if (storageUser == null) {
            throw new SkipException("no storageUser");
            //Assert.fail("missing property 'storageUser'");
        }

        storagePass = System.getProperty("storagePass");
        if (storagePass == null) {
            throw new SkipException("no storagePass");
            //Assert.fail("missing property 'storagePass'");
        }

        client = new StorageClient(storageUser, storagePass);
    }


    @Test
    public void testCreateContainer() throws IOException {

        System.out.println("--------------------------- testCreateContainer()");

        for (String containerName : CONTAINER_NAMES) {
            System.out.println("\tcontainerName: " + containerName);
            client.createContainer(containerName);
            System.out.println("\t\tresponse: " + client.getResponseCode() + " "
                               + client.getResponseMessage());
        }
    }


    @Test(dependsOnMethods = {"testCreateContainer"})
    public void testUpdateObject() throws IOException {

        System.out.println("------------------------------ testUpdateObject()");

        final Random random = new Random();

        final byte[] contentData = new byte[100];
        random.nextBytes(contentData);

        for (String containerName : CONTAINER_NAMES) {
            System.out.println("containerName: " + containerName);
            for (String objectName : OBJECT_NAMES) {
                System.out.println("\tusing byte array");
                client.updateObject(
                    containerName, objectName, "application/octet-stream",
                    contentData);
                System.out.println("\t\tresponse: " + client.getResponseCode()
                                   + " " + client.getResponseMessage());
            }
            for (String objectName : OBJECT_NAMES) {
                System.out.println("\tusing input stream");
                client.updateObject(
                    containerName, objectName, null, -1L,
                    new ByteArrayInputStream(contentData));
                System.out.println("\t\tresponse: " + client.getResponseCode()
                                   + " " + client.getResponseMessage());
            }
        }
    }


    @Test(dependsOnMethods = {"testUpdateObject"})
    public void testReadObject() throws IOException {

        System.out.println("-------------------------------- testReadObject()");

        for (String containerName : CONTAINER_NAMES) {
            System.out.println("containerName: " + containerName);

            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final DefaultContentConsumer contentConsumer =
                new DefaultContentConsumer(baos);

            for (String objectName : OBJECT_NAMES) {
                System.out.println("\tobjectName: " + objectName);
                baos.reset();

                client.readObject(containerName, objectName, contentConsumer);
                System.out.println(
                    "\t\tcontentType: " + contentConsumer.getContentType());
                System.out.println(
                    "\t\tcontentLength: " + contentConsumer.getContentLength());
                System.out.println(
                    "\t\tcontentData: " + baos.toByteArray());
            }
        }
    }


    @Test(dependsOnMethods = {"testUpdateObject"})
    public void testReadStorageObjects() throws IOException {

        System.out.println("------------------------ testReadStorageObjects()");

        final Collection<StorageObject> storageObjects =
            new ArrayList<StorageObject>();

        final Map<String, Object> queryParameters =
            new HashMap<String, Object>();

        for (String containerName : CONTAINER_NAMES) {
            System.out.println("containerName: " + containerName);
            storageObjects.clear();
            client.readStorageObjects(containerName,
                                      queryParameters, storageObjects);
            for (StorageObject storageObject : storageObjects) {
                System.out.println("\tstorageObject: " + storageObject);
            }
        }
    }


    @Test(dependsOnMethods = {"testReadStorageObjects"})
    public void testReadStorageObject() throws IOException {

        System.out.println("------------------------- testReadStorageObject()");

        for (String containerName : CONTAINER_NAMES) {
            System.out.println("containerName: " + containerName);
            final StorageObject storageObject = new StorageObject();
            for (String objectName : OBJECT_NAMES) {
                System.out.println("\tobjectName: " + objectName);
                final boolean result = client.readStorageObject(
                    containerName, objectName, storageObject);
                System.out.println("\t\tresult: " + result);
                System.out.println("\t\tresponse: " + client.getResponseCode()
                                   + " " + client.getResponseMessage());
                System.out.println("\t\tstorageObject: " + storageObject);
                for (Entry<String, List<String>> headerField :
                     client.getHeaderFields().entrySet()) {
                    System.out.println("\t\t" + headerField.getKey() + ": "
                                       + headerField.getValue());
                }
            }
        }
    }


    @Test(dependsOnMethods = {"testReadStorageObject"})
    public void testReadStorageContainers() throws IOException {

        System.out.println("--------------------- testReadStorageContainers()");

        final Collection<StorageContainer> storageContainers =
            new ArrayList<StorageContainer>();

        client.readStorageContainers(null, storageContainers);

        for (StorageContainer storageContainer : storageContainers) {
            System.out.println("storageContainer: " + storageContainer);
        }
    }


    @Test(dependsOnMethods = {"testReadStorageContainers"})
    public void testReadStorageContainer() throws IOException {

        System.out.println("---------------------- testReadStorageContainer()");

        final StorageContainer storageContainer = new StorageContainer();
        for (String containerName : CONTAINER_NAMES) {
            System.out.println("containerName: " + storageContainer);
            final boolean result = client.readStorageContainer(
                containerName, storageContainer);
            System.out.println("result: " + result);
            System.out.println("\tresponse: " + client.getResponseCode()
                               + " " + client.getResponseMessage());
            System.out.println("\tstorageContainer: " + storageContainer);
            for (Entry<String, List<String>> headerField :
                 client.getHeaderFields().entrySet()) {
                System.out.println("\t\t" + headerField.getKey() + ": "
                                   + headerField.getValue());
            }
        }
    }


    @Test(dependsOnMethods = {"testReadStorageContainer"})
    public void testDeleteObject() throws IOException {

        System.out.println("------------------------------ testDeleteObject()");

        for (String containerName : CONTAINER_NAMES) {
            System.out.println("containerName: " + containerName);
            for (String objectName : OBJECT_NAMES) {
                System.out.println("\tobjectName: " + objectName);
                final boolean result =
                    client.deleteObject(containerName, objectName);
                System.out.println("\t\tresult: " + result);
                System.out.println("\t\tresponse: " + client.getResponseCode()
                                   + " " + client.getResponseMessage());
            }
        }
    }


    @Test(dependsOnMethods = {"testDeleteObject"})
    public void testDeleteContainer() throws IOException {

        System.out.println("--------------------------- testDeleteContainer()");

        for (String containerName : CONTAINER_NAMES) {
            System.out.println("containerName: " + containerName);
            client.deleteContainer(containerName);
            System.out.println("\tresponse: " + client.getResponseCode()
                               + " " + client.getResponseMessage());
        }
    }


    private String storageUser;


    private String storagePass;


    private StorageClient client;


}

