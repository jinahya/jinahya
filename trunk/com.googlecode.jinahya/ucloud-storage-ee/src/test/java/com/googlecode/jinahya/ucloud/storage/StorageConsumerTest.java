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


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class StorageConsumerTest {


    protected static StorageConsumer persistInstance(
        final EntityManager manager, StorageReference storageReference1,
        StorageReference storageReference2) {

        if (storageReference1 == null) {
            storageReference1 =
                StorageReferenceTest.persistInstance(manager, null);
            manager.flush();
        }

        if (storageReference2 == null) {
            storageReference2 =
                StorageReferenceTest.persistInstance(manager, null);
            manager.flush();
        }

        final StorageConsumer storageConsumer = new StorageConsumer();

        storageConsumer.setStorageReference1(storageReference1);
        storageConsumer.setStorageReference2(storageReference2);

        manager.persist(storageConsumer);

        return storageConsumer;
    }


    @Test
    public void testPersistInstance() {

        final EntityManager manager = LocalPU.createEntityManager();
        try {
            final EntityTransaction transaction = manager.getTransaction();
            transaction.begin();
            try {
                final StorageConsumer storageConsumer =
                    persistInstance(manager, null, null);
                final StorageReference storageReference1 =
                    storageConsumer.getStorageReference1();
                Assert.assertNotNull(storageReference1);
                final StorageReference storageReference2 =
                    storageConsumer.getStorageReference2();
                Assert.assertNotNull(storageReference2);
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


    @Test(enabled = true)
    public void testStorageReferenceRemovedWhenOrphaned() {

        StorageConsumer storageConsumer;

        StorageReference storageReference1;
        StorageLocator storageLocator1;

        StorageReference storageReference2;
        StorageLocator storageLocator2;

        EntityManager manager;

        manager = LocalPU.createEntityManager();
        try {
            final EntityTransaction transaction = manager.getTransaction();
            transaction.begin();
            storageConsumer = persistInstance(manager, null, null);
            storageReference1 = storageConsumer.getStorageReference1();
            storageLocator1 = storageReference1.getStorageLocator();
            storageReference2 = storageConsumer.getStorageReference2();
            storageLocator2 = storageReference2.getStorageLocator();
            transaction.commit();
        } finally {
            manager.close();
        }

        manager = LocalPU.createEntityManager();
        try {
            final EntityTransaction transaction = manager.getTransaction();
            transaction.begin();
            storageConsumer = manager.getReference(
                StorageConsumer.class, storageConsumer.getId());
            Assert.assertNotNull(storageConsumer);
//            storageReference1 = storageConsumer.getStorageReference1();
//            storageLocator1 = storageReference1.getStorageLocator();
//            storageReference2 = storageConsumer.getStorageReference2();
//            storageLocator2 = storageReference2.getStorageLocator();
            manager.remove(storageConsumer);
            transaction.commit();
        } finally {
            manager.close();
        }

        manager = LocalPU.createEntityManager();
        try {
            final EntityTransaction transaction = manager.getTransaction();
            transaction.begin();

            storageConsumer = manager.find(
                StorageConsumer.class, storageConsumer.getId());
            Assert.assertNull(storageConsumer);

            storageReference1 = manager.find(
                StorageReference.class, storageReference1.getId());
            Assert.assertNull(storageReference1);
            storageLocator1 = manager.find(
                StorageLocator.class, storageLocator1.getId());
            Assert.assertNotNull(storageLocator1.getDeletedAt());

            storageReference2 = manager.find(
                StorageReference.class, storageReference2.getId());
            Assert.assertNull(storageReference2);
            storageLocator2 = manager.find(
                StorageLocator.class, storageLocator2.getId());
            Assert.assertNotNull(storageLocator2.getDeletedAt());

            transaction.commit();
        } finally {
            manager.close();
        }

        Assert.assertNotNull(storageLocator1.getDeletedAt());
    }


}
