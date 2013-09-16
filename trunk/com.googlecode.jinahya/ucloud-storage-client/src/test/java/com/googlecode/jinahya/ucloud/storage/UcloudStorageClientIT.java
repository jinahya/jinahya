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
import java.util.Map;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class UcloudStorageClientIT {


    private static final String[] CONTAINER_NAMES = new String[]{
        "test",
        "테스트",
        "1*1"
    };


    private static final String[] OBJECT_NAMES = new String[]{
        "abc",
        "def/ghi",
        "!@#$%^&*()",
        "가나다",
        "abc/def",
        "라마바/사아자"
    };


    /**
     * logger.
     */
    private static final Logger LOGGER =
        LoggerFactory.getLogger(UcloudStorageClientIT.class);


    @BeforeClass()
    public void readUserAndPass() {

        LOGGER.debug("readUserAndPass()");

        storageUser = System.getProperty("storageUser");
        if (storageUser == null) {
            throw new SkipException("no storageUser");
            //Assert.fail("missing property 'storageUser'");
        }
        LOGGER.debug("storageUser: " + storageUser);

        storagePass = System.getProperty("storagePass");
        if (storagePass == null) {
            throw new SkipException("no storagePass");
            //Assert.fail("missing property 'storagePass'");
        }
        LOGGER.debug("storagePass: " + storagePass);
    }


    @Test(enabled = true)
    public void testCreateStorageContainer() throws IOException {

        final UcloudStorageClient client =
            new UcloudStorageClient(storageUser, storagePass);

        for (final String containerName : CONTAINER_NAMES) {
            {
                final boolean succeeded =
                    client.createStorageContainer(containerName);
            }
            {
                final boolean succeeded =
                    client.createStorageContainer(containerName);
            }
        }
    }


    @Test(dependsOnMethods = {"testCreateStorageContainer"}, enabled = true)
    public void testReadStorageContainers() throws IOException {

        LOGGER.debug("testReadStorageContainers()");

        final UcloudStorageClient client =
            new UcloudStorageClient(storageUser, storagePass);

        final Map<String, Object> queryParameters = new HashMap<>();

        final Collection<StorageContainer> storageContainers =
            new ArrayList<>();

        final boolean succeeded =
            client.readStorageContainers(queryParameters, storageContainers);

        for (final StorageContainer storageContainer : storageContainers) {
        }
    }


    @Test(dependsOnMethods = {"testReadStorageContainers"}, enabled = true)
    public void testReadStorageContainer() throws IOException {

        LOGGER.debug("testReadStorageContainer()");

        final UcloudStorageClient client =
            new UcloudStorageClient(storageUser, storagePass);

        for (final String containerName : CONTAINER_NAMES) {
            final StorageContainer storageContainer =
                client.readStorageContainer(containerName);
        }
    }


    @Test(dependsOnMethods = {"testReadStorageContainer"}, enabled = true)
    public void testUpdateStorageContent() throws IOException {

        LOGGER.debug("testUpdateStorageContent()");

        final UcloudStorageClient client =
            new UcloudStorageClient(storageUser, storagePass);

        final Random random = new Random();

        final byte[] contentData = new byte[1048576];
        random.nextBytes(contentData);

        for (final String containerName : CONTAINER_NAMES) {
            for (final String objectName : OBJECT_NAMES) {
                client.updateStorageContent(
                    containerName, objectName, "application/octet-stream",
                    contentData);
            }
            for (final String objectName : OBJECT_NAMES) {
                client.updateStorageContent(
                    containerName, objectName, "application/octet-stream",
                    contentData.length, new ByteArrayInputStream(contentData));
            }
            for (final String objectName : OBJECT_NAMES) {
                client.updateStorageContent(
                    containerName, objectName, "application/octet-stream", -1L,
                    new ByteArrayInputStream(contentData));
            }
        }
    }


    @Test(dependsOnMethods = {"testUpdateStorageContent"}, enabled = true)
    public void testReadStorageObjects() throws IOException {

        LOGGER.debug("testReadStorageObjects()");

        final UcloudStorageClient client =
            new UcloudStorageClient(storageUser, storagePass);

        final Map<String, Object> queryParameters = new HashMap<>();

        final Collection<StorageObject> storageObjects = new ArrayList<>();

        for (final String containerName : CONTAINER_NAMES) {
            final boolean succeeded = client.readStorageObjects(
                containerName, queryParameters, storageObjects);
        }
    }


    @Test(dependsOnMethods = {"testReadStorageObjects"}, enabled = true)
    public void testReadStorageObject() throws IOException {

        LOGGER.debug("testStorageObject()");

        final UcloudStorageClient client =
            new UcloudStorageClient(storageUser, storagePass);

        for (final String containerName : CONTAINER_NAMES) {
            for (final String objectName : OBJECT_NAMES) {
                final StorageObject storageObject =
                    client.readStorageObject(containerName, objectName);
            }
        }
    }


    @Test(dependsOnMethods = {"testReadStorageObject"}, enabled = true)
    public void testDeleteStorageObject() throws IOException {

        LOGGER.debug("testDeleteStorageObject()");

        final UcloudStorageClient client =
            new UcloudStorageClient(storageUser, storagePass);

        for (String containerName : CONTAINER_NAMES) {
            for (String objectName : OBJECT_NAMES) {
                final boolean succeeded = client.deleteStorageObject(
                    containerName, objectName);
            }
        }
    }


    @Test(dependsOnMethods = {"testDeleteStorageObject"}, enabled = true)
    public void testDeleteStorageContainer() throws IOException {

        LOGGER.debug("testDeleteStorageContainer()");

        final UcloudStorageClient client =
            new UcloudStorageClient(storageUser, storagePass);

        for (final String containerName : CONTAINER_NAMES) {
            final boolean succeeded =
                client.deleteStorageContainer(containerName);
        }
    }


    private transient String storageUser;


    private transient String storagePass;


}
