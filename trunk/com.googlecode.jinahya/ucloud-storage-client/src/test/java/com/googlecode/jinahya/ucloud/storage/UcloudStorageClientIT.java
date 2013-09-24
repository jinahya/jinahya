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
import org.testng.Assert;
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
        "테스트"
    };


    private static final String[] OBJECT_NAMES = new String[]{
        "abc",
        "def/ghi",
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
        }
        LOGGER.debug("storageUser: " + storageUser);

        storagePass = System.getProperty("storagePass");
        if (storagePass == null) {
            throw new SkipException("no storagePass");
        }
        LOGGER.debug("storagePass: " + storagePass);
    }


    @Test(enabled = true)
    public void testReadStorageAccount() throws IOException {

        LOGGER.debug("testReadStorageAccount()");

        final UcloudStorageClient client =
            new UcloudStorageClient(storageUser, storagePass);

        final StorageAccount storageAccount = client.readStorageAccount();
        Assert.assertNotNull(storageAccount);
        LOGGER.debug("storageAccount: " + storageAccount);
    }


    @Test(dependsOnMethods = {"testReadStorageAccount"}, enabled = true)
    public void testStorageAccountMetadata() throws IOException {

        LOGGER.debug("testStorageAccountMetadata()");

        final UcloudStorageClient client =
            new UcloudStorageClient(storageUser, storagePass);

        final Map<String, String> metadata =
            client.readStorageAccountMetadata();
        Assert.assertNotNull(metadata);

        final String name = "name";
        final String value = "value";

        final boolean updated =
            client.updateStorageAccountMetadata(name, value);
        LOGGER.debug("updated: {}: ", updated);
        Assert.assertTrue(updated);

        final String value1 = client.readStorageAccountMetadata(name);
        Assert.assertEquals(value1, value);

        final boolean deleted = client.deleteStorageAccountMetadata(name);
        LOGGER.debug("deleted: {}: ", deleted);
        Assert.assertTrue(deleted);

        final String value2 = client.readStorageAccountMetadata(name);
        Assert.assertNull(value2);
    }


    @Test(dependsOnMethods = {"testStorageAccountMetadata"}, enabled = true)
    public void testCreateStorageContainer() throws IOException {

        LOGGER.debug("testCreateStorageContainer()");

        final UcloudStorageClient client =
            new UcloudStorageClient(storageUser, storagePass);

        for (final String containerName : CONTAINER_NAMES) {
            Assert.assertTrue(client.createStorageContainer(containerName));
            Assert.assertTrue(client.createStorageContainer(containerName));
            Assert.assertTrue(client.createStorageContainer(containerName));
            Assert.assertTrue(client.createStorageContainer(containerName));
        }
    }


    @Test(dependsOnMethods = {"testCreateStorageContainer"}, enabled = true)
    public void testStorageContainerMetadata() throws IOException {

        LOGGER.debug("testStorageContainerMetadata()");

        final UcloudStorageClient client =
            new UcloudStorageClient(storageUser, storagePass);

        for (final String containerName : CONTAINER_NAMES) {

            final Map<String, String> metadata =
                client.readStorageContainerMetadata(containerName);
            Assert.assertNotNull(metadata);

            final String name = "container-metadata-name";
            final String value = "container-metadata-value";

            final boolean updated = client.updateStorageContainerMetadata(
                containerName, name, value);
            LOGGER.debug("container.metadata.updated: {}: ", updated);
            Assert.assertTrue(updated);

            final String value1 = client.readStorageContainerMetadata(
                containerName, name);
            Assert.assertEquals(value1, value);

            final boolean deleted = client.deleteStorageContainerMetadata(
                containerName, name);
            LOGGER.debug("container.metadata.deleted: {}: ", deleted);
            Assert.assertTrue(deleted);

            final String value2 = client.readStorageContainerMetadata(
                containerName, name);
            Assert.assertNull(value2);
        }
    }


    @Test(dependsOnMethods = {"testStorageContainerMetadata"}, enabled = true)
    public void testReadStorageContainers() throws IOException {

        LOGGER.debug("testReadStorageContainers()");

        final UcloudStorageClient client =
            new UcloudStorageClient(storageUser, storagePass);

        final Map<String, Object> queryParameters = new HashMap<>();

        final Collection<StorageContainer> storageContainers =
            new ArrayList<>();

        Assert.assertTrue(
            client.readStorageContainers(queryParameters, storageContainers));

        for (final StorageContainer storageContainer : storageContainers) {
            LOGGER.info("storageContainer: " + storageContainer);
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
            LOGGER.debug("storageContainer: {}", storageContainer);
            Assert.assertEquals(storageContainer.getContainerName(), containerName);
            Assert.assertEquals(storageContainer.getObjectCount(), 0L);
            Assert.assertEquals(storageContainer.getBytesUsed(), 0L);
        }
    }


    @Test(dependsOnMethods = {"testReadStorageContainer"}, enabled = true)
    public void testUpdateStorageContent() throws IOException {

        LOGGER.debug("testUpdateStorageContent()");

        final UcloudStorageClient client =
            new UcloudStorageClient(storageUser, storagePass);

        final Random random = new Random();

        final byte[] contentData = new byte[128];
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
            Assert.assertTrue(client.readStorageObjects(
                containerName, queryParameters, storageObjects));
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
                LOGGER.debug("storageObject: {}", storageObject);
                Assert.assertNotNull(storageObject);
            }
        }
    }


    @Test(dependsOnMethods = {"testReadStorageObject"}, enabled = true)
    public void testDeleteStorageObject() throws IOException {

        LOGGER.debug("testDeleteStorageObject()");

        final UcloudStorageClient client =
            new UcloudStorageClient(storageUser, storagePass);

        for (final String containerName : CONTAINER_NAMES) {
            for (final String objectName : OBJECT_NAMES) {
                LOGGER.debug("deleting {}/{}", containerName, objectName);
                Assert.assertTrue(
                    client.deleteStorageObject(containerName, objectName));
            }
        }
    }


    @Test(dependsOnMethods = {"testDeleteStorageObject"}, enabled = true)
    public void testDeleteStorageContainer() throws IOException {

        LOGGER.debug("testDeleteStorageContainer()");

        final UcloudStorageClient client =
            new UcloudStorageClient(storageUser, storagePass);

        for (final String containerName : CONTAINER_NAMES) {
            Assert.assertTrue(client.deleteStorageContainer(containerName));
        }
    }


    private transient String storageUser;


    private transient String storagePass;


}
