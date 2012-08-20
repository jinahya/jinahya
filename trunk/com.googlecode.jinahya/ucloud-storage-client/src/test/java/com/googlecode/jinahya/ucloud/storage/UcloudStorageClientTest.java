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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class UcloudStorageClientTest {


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
    }


    @Test
    public void testCreateContainer() throws IOException {

        final UcloudStorageClient client =
            new UcloudStorageClient(storageUser, storagePass);

        for (String containerName : CONTAINER_NAMES) {
            client.createContainer(containerName);
        }
    }


    @Test(dependsOnMethods = {"testCreateContainer"})
    public void testReadContainerNames() throws IOException {

        final UcloudStorageClient client =
            new UcloudStorageClient(storageUser, storagePass);

        final List<String> containerNames = new ArrayList<String>();

        final Map<String, String> queryParams = new HashMap<String, String>();

        queryParams.put("format", "json");
        client.readContainerNames(queryParams, containerNames);
        for (String containerName : containerNames) {
            System.out.println("containerName: " + containerName);
        }

        queryParams.put("format", "json");
        client.readContainerNames(queryParams, containerNames);
        for (String containerName : containerNames) {
            System.out.println("containerName: " + containerName);
        }
    }


    @Test(dependsOnMethods = {"testReadContainerNames"})
    public void testUpdateObject() throws IOException {

        System.out.println("testUpdateObject()");

        final UcloudStorageClient client =
            new UcloudStorageClient(storageUser, storagePass);

        final Random random = new Random();

        final byte[] contentData = new byte[1048576];
        //final byte[] contentData = new byte[100];
        random.nextBytes(contentData);

        for (String containerName : CONTAINER_NAMES) {
            //final byte[] contentData = new byte[random.nextInt(100)];
            //random.nextBytes(contentData);
            for (String objectName : OBJECT_NAMES) {
                client.updateObject(
                    containerName, objectName, "application/octet-stream",
                    contentData);
            }
            for (String objectName : OBJECT_NAMES) {
                client.updateObject(
                    containerName, objectName, "application/octet-stream",
                    contentData.length, new ByteArrayInputStream(contentData));
            }
            for (String objectName : OBJECT_NAMES) {
                client.updateObject(
                    containerName, objectName, "application/octet-stream", -1L,
                    new ByteArrayInputStream(contentData));
            }
        }
    }


    @Test(dependsOnMethods = {"testUpdateObject"})
    public void testReadObjectNames() throws IOException {

        System.out.println("readObjectNames()");

        final UcloudStorageClient client =
            new UcloudStorageClient(storageUser, storagePass);

        final Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("format", "xml");

        final Collection<String> objectNames = new ArrayList<String>();

        queryParams.put("format", "json");
        for (String containerName : CONTAINER_NAMES) {
            System.out.println("containerName: " + containerName);
            objectNames.clear();
            client.readObjectNames(containerName, queryParams, objectNames);
            for (String objectName : objectNames) {
                System.out.println("\tobjectName: " + objectName);
            }
        }

        queryParams.put("format", "xml");
        for (String containerName : CONTAINER_NAMES) {
            System.out.println("containerName: " + containerName);
            objectNames.clear();
            client.readObjectNames(containerName, null, objectNames);
            for (String objectName : objectNames) {
                System.out.println("\tobjectName: " + objectName);
            }
        }
    }


    @Test(dependsOnMethods = {"testUpdateObject"})
    public void testReadObject() throws IOException {

        System.out.println("testReadObject()");

        final UcloudStorageClient client =
            new UcloudStorageClient(storageUser, storagePass);

        for (String containerName : CONTAINER_NAMES) {
            for (String objectName : OBJECT_NAMES) {
                final BufferedContentConsumer contentConsumer =
                    new BufferedContentConsumer();

                client.readObject(containerName, objectName, contentConsumer);
                System.out.println("type: " + contentConsumer.getType());
                System.out.println("length: " + contentConsumer.getLength());
                System.out.println("data: " + contentConsumer.getData());
            }
        }
    }


    @Test(dependsOnMethods = {"testReadObject"})
    public void testDeleteObject() throws IOException {

        System.out.println("testDeleteObject()");

        final UcloudStorageClient client =
            new UcloudStorageClient(storageUser, storagePass);

        for (String containerName : CONTAINER_NAMES) {
            for (String objectName : OBJECT_NAMES) {
                client.deleteObject(containerName, objectName);
            }
        }
    }


    @Test(dependsOnMethods = {"testDeleteObject"})
    public void testDeleteContainer() throws IOException {

        System.out.println("testDeleteContainer()");

        final UcloudStorageClient client =
            new UcloudStorageClient(storageUser, storagePass);

        for (String containerName : CONTAINER_NAMES) {
            client.deleteContainer(containerName);
        }
    }


    private String storageUser;


    private String storagePass;


}

