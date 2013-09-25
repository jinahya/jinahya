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
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
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
public class StorageClientIT {


    private static final String[] CONTAINER_NAMES = new String[]{
        "it_test test",
        "it test_테스트"
    };


    private static final String[] OBJECT_NAMES = new String[]{
        "abc",
        "def/ghi",
        "가나다",
        "abc/def",
        "라마바/사아자"
    };


    private static transient String storageUser;


    private static transient String storagePass;


    /**
     * logger.
     */
    private static final Logger LOGGER =
        LoggerFactory.getLogger(StorageClientIT.class);


    private static final String CONTAINER_NAME_PREFIX;


    static {
        CONTAINER_NAME_PREFIX =
            MethodHandles.lookup().lookupClass().getCanonicalName();
        if (CONTAINER_NAME_PREFIX == null) {
            throw new InstantiationError("null canonical name");
        }
    }


    @BeforeClass()
    private static void readUserAndPass() {

        LOGGER.debug("readUserAndPass()");

        storageUser = System.getProperty("storageUser");
        if (storageUser == null) {
            throw new SkipException("no storageUser");
        }
        //LOGGER.debug("storageUser: " + storageUser);

        storagePass = System.getProperty("storagePass");
        if (storagePass == null) {
            throw new SkipException("no storagePass");
        }
        //LOGGER.debug("storagePass: " + storagePass);
    }


    protected static StorageClient newStorageClient() {

        return new StorageClient(storageUser, storagePass);
    }


    @Test(enabled = true)
    public void testReadStorageAccount() throws IOException {

        LOGGER.debug("testReadStorageAccount()");

        final StorageClient storageClient = newStorageClient();

        final StorageAccount storageAccount =
            storageClient.readStorageAccount();
        Assert.assertNotNull(storageAccount);
        LOGGER.debug("storageAccount: " + storageAccount);
    }


    @Test(dependsOnMethods = {"testReadStorageAccount"}, enabled = true)
    public void testStorageAccountProperties() throws IOException {

        LOGGER.debug("testStorageAccountProperties()");

        final StorageClient storageClient = newStorageClient();

        final Map<String, String> storageProperties =
            new HashMap<String, String>();

        storageProperties.clear();
        storageClient.readStorageAccountProperties(storageProperties);

        final String propertyKey = "account-property-key 가나다라";
        final String propertyValue = "account-property-value 마바사";

        final boolean updated = storageClient.updateStorageAccountProperty(
            propertyKey, propertyValue);
        LOGGER.info("account.property.updated: {}: ", updated);
        Assert.assertTrue(updated);

        storageProperties.clear();
        storageClient.readStorageAccountProperties(storageProperties);
        Assert.assertEquals(storageProperties.get(propertyKey), propertyValue);

        final String propertyValue1 =
            storageClient.readStorageAccountProperty(propertyKey);
        Assert.assertEquals(propertyValue1, propertyValue);

        final boolean deleted =
            storageClient.deleteStorageAccountProperty(propertyKey);
        LOGGER.debug("account.property.deleted: {}: ", deleted);
        Assert.assertTrue(deleted);

        storageProperties.clear();
        storageClient.readStorageAccountProperties(storageProperties);
        Assert.assertNull(storageProperties.get(propertyKey));

        final String propertyValue2 =
            storageClient.readStorageAccountProperty(propertyKey);
        Assert.assertNull(propertyValue2);
    }


    @Test(dependsOnMethods = {"testStorageAccountProperties"}, enabled = true)
    public void testCreateStorageContainer() throws IOException {

        LOGGER.debug("testCreateStorageContainer()");

        final StorageClient storageClient = newStorageClient();

        for (final String containerName : CONTAINER_NAMES) {
            for (int i = 0; i < 5; i++) {
                final boolean created =
                    storageClient.createStorageContainer(containerName);
                Assert.assertTrue(created);
            }
        }
    }


    @Test(dependsOnMethods = {"testCreateStorageContainer"}, enabled = true)
    public void testStorageContainerProperties() throws IOException {

        LOGGER.debug("testStorageContainerProperties()");

        final StorageClient storageClient = newStorageClient();

        final Map<String, String> storageProperties =
            new HashMap<String, String>();

        for (final String containerName : CONTAINER_NAMES) {

            storageProperties.clear();
            storageClient.readStorageContainerProperties(
                containerName, storageProperties);

            final String propertyKey = "container-property-key";
            final String propertyValue = "container-property-value";

            final boolean updated =
                storageClient.updateStorageContainerProperty(
                containerName, propertyKey, propertyValue);
            LOGGER.debug("container.property.updated: {}: ", updated);
            Assert.assertTrue(updated);

            storageProperties.clear();
            storageClient.readStorageContainerProperties(
                containerName, storageProperties);
            Assert.assertEquals(storageProperties.get(propertyKey),
                                propertyValue);

            final String propertyValue1 =
                storageClient.readStorageContainerProperty(
                containerName, propertyKey);
            Assert.assertEquals(propertyValue1, propertyValue);

            final boolean deleted =
                storageClient.deleteStorageContainerProperty(
                containerName, propertyKey);
            LOGGER.debug("container.proeprty.deleted: {}: ", deleted);
            Assert.assertTrue(deleted);

            storageProperties.clear();
            storageClient.readStorageContainerProperties(
                containerName, storageProperties);
            Assert.assertNull(storageProperties.get(propertyKey));

            final String propertyValue2 =
                storageClient.readStorageContainerProperty(
                containerName, propertyKey);
            Assert.assertNull(propertyValue2);
        }
    }


    @Test(dependsOnMethods = {"testStorageContainerProperties"}, enabled = true)
    public void testReadStorageContainers() throws IOException {

        LOGGER.debug("testReadStorageContainers()");

        final StorageClient storageClient = newStorageClient();

        final Map<String, Object> queryParameters = new HashMap<>();

        final Collection<StorageContainer> storageContainers =
            new ArrayList<>();

        final boolean read = storageClient.readStorageContainers(
            queryParameters, storageContainers);
        Assert.assertTrue(read);

        for (final StorageContainer storageContainer : storageContainers) {
            LOGGER.debug("storageContainer: {}", storageContainer);
        }
    }


    @Test(dependsOnMethods = {"testReadStorageContainers"}, enabled = true)
    public void testReadStorageContainer() throws IOException {

        LOGGER.debug("testReadStorageContainer()");

        final StorageClient storageClient = newStorageClient();

        for (final String containerName : CONTAINER_NAMES) {
            final StorageContainer storageContainer =
                storageClient.readStorageContainer(containerName);
            LOGGER.debug("storageContainer: {}", storageContainer);
            Assert.assertEquals(storageContainer.getContainerName(),
                                containerName);
            Assert.assertEquals(storageContainer.getObjectCount(), 0L);
            Assert.assertEquals(storageContainer.getBytesUsed(), 0L);
        }
    }


    @Test(dependsOnMethods = {"testReadStorageContainer"}, enabled = true)
    public void testUpdateStorageContent() throws IOException {

        LOGGER.debug("testUpdateStorageContent()");

        final StorageClient storageClient = newStorageClient();

        final Random random = ThreadLocalRandom.current();

        final byte[] contentData = new byte[random.nextInt(128)];
        random.nextBytes(contentData);

        for (final String containerName : CONTAINER_NAMES) {
            for (final String objectName : OBJECT_NAMES) {
                storageClient.updateStorageContent(
                    containerName, objectName, "application/octet-stream",
                    contentData);
            }
            for (final String objectName : OBJECT_NAMES) {
                storageClient.updateStorageContent(
                    containerName, objectName, "application/octet-stream",
                    contentData.length, new ByteArrayInputStream(contentData));
            }
            for (final String objectName : OBJECT_NAMES) {
                storageClient.updateStorageContent(
                    containerName, objectName, "application/octet-stream", -1L,
                    new ByteArrayInputStream(contentData));
            }

            final StorageContainer storageContainer =
                storageClient.readStorageContainer(containerName);
            Assert.assertEquals(storageContainer.getContainerName(),
                                containerName);
            Assert.assertTrue(storageContainer.getObjectCount() > 0L);
            Assert.assertTrue(storageContainer.getBytesUsed() >= 0L);
        }
    }


    @Test(dependsOnMethods = {"testUpdateStorageContent"}, enabled = true)
    public void testReadStorageObjects() throws IOException {

        LOGGER.debug("testReadStorageObjects()");

        final StorageClient storageClient = newStorageClient();

        final Map<String, Object> queryParameters = new HashMap<>();

        final Collection<StorageObject> storageObjects = new ArrayList<>();

        for (final String containerName : CONTAINER_NAMES) {
            Assert.assertTrue(storageClient.readStorageObjects(
                containerName, queryParameters, storageObjects));
        }
    }


    @Test(dependsOnMethods = {"testReadStorageObjects"}, enabled = true)
    public void testReadStorageObject() throws IOException {

        LOGGER.debug("testStorageObject()");

        final StorageClient client =
            new StorageClient(storageUser, storagePass);

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

        final StorageClient storageClient = newStorageClient();

        for (final String containerName : CONTAINER_NAMES) {
            for (final String objectName : OBJECT_NAMES) {
                LOGGER.debug("deleting {}/{}", containerName, objectName);
                final boolean deleted = storageClient.deleteStorageObject(
                    containerName, objectName);
                Assert.assertTrue(deleted);
            }

            final StorageContainer storageContainer =
                storageClient.readStorageContainer(containerName);
            Assert.assertEquals(storageContainer.getContainerName(),
                                containerName);
            Assert.assertEquals(storageContainer.getObjectCount(), 0L);
            Assert.assertEquals(storageContainer.getBytesUsed(), 0L);
        }
    }


    @Test(dependsOnMethods = {"testDeleteStorageObject"}, enabled = true)
    public void testDeleteStorageContainer() throws IOException {

        LOGGER.debug("testDeleteStorageContainer()");

        final StorageClient storageClient = newStorageClient();

        for (final String containerName : CONTAINER_NAMES) {
            final boolean deleted = storageClient.deleteStorageContainer(
                containerName);
            Assert.assertTrue(deleted);
        }
    }


}
