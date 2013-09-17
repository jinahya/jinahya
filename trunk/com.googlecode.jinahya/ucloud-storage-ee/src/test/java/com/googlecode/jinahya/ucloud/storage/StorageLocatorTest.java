/*
 * Copyright 2013 Jin Kwon <onacit at gmail.com>.
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


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class StorageLocatorTest {


    private static final Logger LOGGER =
        LoggerFactory.getLogger(StorageLocatorTest.class);


    protected static StorageLocator persistInstance(
        final EntityManager manager) {

        final StorageLocator storageLocator = new StorageLocator();

        manager.persist(storageLocator);

        storageLocator.setContainerName(null, storageLocator.getId());
        storageLocator.setObjectName(null, storageLocator.getId());

        return storageLocator;
    }


    @Test
    public void testPersistInstance() {

        final EntityManager manager = LocalPU.createEntityManager();
        try {
            final EntityTransaction transaction = manager.getTransaction();
            transaction.begin();
            try {
                final StorageLocator storageLocator = persistInstance(manager);
                LOGGER.debug("storageLocator.containerName: {}",
                             storageLocator.getContainerName());
                LOGGER.debug("storageLocator.objectName: {}",
                             storageLocator.getObjectName());
                transaction.commit();
            } catch (Exception e) {
                LocalPU.printConstraintViolation(e);
                transaction.rollback();
                e.printStackTrace(System.err);
                Assert.fail(e.getMessage());
            }
        } finally {
            manager.close();
        }
    }


}
