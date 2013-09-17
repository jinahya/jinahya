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
public class StorageReferenceTest {


    /**
     * logger.
     */
    private static final Logger LOGGER =
        LoggerFactory.getLogger(StorageReferenceTest.class);


    protected static StorageReference persistInstance(
        final EntityManager manager, StorageLocator storageLocator) {

        if (storageLocator == null) {
            storageLocator = StorageLocatorTest.persistInstance(manager);
        }

        final StorageReference storageReference = new StorageReference();

        storageReference.setStorageLocator(storageLocator);

        manager.persist(storageReference);

        return storageReference;
    }


    @Test
    public void testPersistInstance() {

        final EntityManager manager = LocalPU.createEntityManager();
        try {
            final EntityTransaction transaction = manager.getTransaction();
            transaction.begin();
            try {
                final StorageReference storageReference =
                    persistInstance(manager, null);
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


    @Test
    public void testStorageLocatorDeletedOnRemove() {

        StorageReference storageReference;
        StorageLocator storageLocator;

        EntityManager manager;

        manager = LocalPU.createEntityManager();
        try {
            final EntityTransaction transaction = manager.getTransaction();
            transaction.begin();
            storageReference = persistInstance(manager, null);
            storageLocator = storageReference.getStorageLocator();
            transaction.commit();
        } finally {
            manager.close();
        }

        manager = LocalPU.createEntityManager();
        try {
            final EntityTransaction transaction = manager.getTransaction();
            transaction.begin();
//            storageReference = manager.find(
//                StorageReference.class, storageReference.getId());
            storageReference = manager.getReference(
                StorageReference.class, storageReference.getId());
//            storageReference = manager.merge(storageReference);
            Assert.assertNotNull(storageReference);
            storageLocator = storageReference.getStorageLocator();
            Assert.assertNotNull(storageLocator);
            manager.remove(storageReference);
            //manager.flush();
            transaction.commit();
        } finally {
            manager.close();
        }

        manager = LocalPU.createEntityManager();
        try {
            final EntityTransaction transaction = manager.getTransaction();
            transaction.begin();
            storageReference = manager.find(
                StorageReference.class, storageReference.getId());
            Assert.assertNull(storageReference);
            transaction.commit();
        } finally {
            manager.close();
        }

        Assert.assertNotNull(storageLocator.getDeletedAt());
    }


}
