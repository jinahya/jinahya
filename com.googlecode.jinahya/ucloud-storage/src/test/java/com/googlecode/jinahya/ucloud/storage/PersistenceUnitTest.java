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


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.xml.bind.JAXBException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class PersistenceUnitTest {


    private static final Logger LOGGER =
        Logger.getLogger(PersistenceUnitTest.class.getName());


    private static EntityManagerFactory ENTITY_MANAGER_FACTORY;


    private static EntityManager ENTTIY_MANAGER;


    private static final Random RANDOM = new Random();


    @BeforeClass
    public static void setUp() throws ClassNotFoundException, SQLException {

        ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("ucloudStorageTestPU");

        ENTTIY_MANAGER = ENTITY_MANAGER_FACTORY.createEntityManager();
    }


    private static StorageLocator newStorageLocator() {

        final StorageLocator storageLocator = new StorageLocator();
        ENTTIY_MANAGER.persist(storageLocator);

        storageLocator.setContainerName(null, storageLocator.getId());
        storageLocator.setObjectName(null, storageLocator.getId());

        return storageLocator;
    }


    private static StorageReference newStorageReference() {
        return newStorageReference(newStorageLocator());
    }


    private static StorageReference newStorageReference(
        final StorageLocator storageLocator) {

        final StorageReference storageReference =
            StorageReference.newInstance(storageLocator);
        ENTTIY_MANAGER.persist(storageReference);

        return storageReference;
    }


    @AfterClass
    public static void tearDown() {
    }


    //@Test
    public void test() {

        ENTTIY_MANAGER.getTransaction().begin();

        for (int i = 0; i < 10; i++) {

            final StorageLocator storageLocator = newStorageLocator();

            final StorageReference storageReference =
                newStorageReference(storageLocator);

            if (RANDOM.nextBoolean()) {
                ENTTIY_MANAGER.remove(storageReference);
            }
        }

        ENTTIY_MANAGER.getTransaction().commit();


        ENTTIY_MANAGER.getTransaction().begin();

        {
            final TypedQuery<Long> query = ENTTIY_MANAGER.createNamedQuery(
                StorageLocator.NQ_COUNT, Long.class);
            @SuppressWarnings("unchecked")
            final long count = query.getSingleResult();
            LOGGER.log(Level.INFO, "storageLocators.count: {0}", count);
        }

        {
            final Query query = ENTTIY_MANAGER.createNamedQuery(
                StorageLocator.NQ_LIST);
            @SuppressWarnings("unchecked")
            final List<StorageLocator> storageLocators =
                (List<StorageLocator>) query.getResultList();
            for (StorageLocator storageLocator : storageLocators) {
                LOGGER.log(Level.INFO, "storageLocator: {0}", storageLocator);
            }
        }

        {
            final Query query = ENTTIY_MANAGER.createNamedQuery(
                StorageReference.NQ_LIST);
            @SuppressWarnings("unchecked")
            final List<StorageReference> storageReferences =
                (List<StorageReference>) query.getResultList();
            for (StorageReference storageReference : storageReferences) {
                System.out.println(storageReference);
            }
        }

        ENTTIY_MANAGER.getTransaction().commit();
    }


    @Test
    public void testStorageConsumer() throws JAXBException, IOException {

        ENTTIY_MANAGER.getTransaction().begin();

        final StorageConsumer storageConsumer = new StorageConsumer();
        ENTTIY_MANAGER.persist(storageConsumer);

        if (RANDOM.nextBoolean()) {
            final StorageReference storageReference1 = newStorageReference();
            storageConsumer.setStorageReference1(storageReference1);
            if (RANDOM.nextBoolean()) {
                storageConsumer.setStorageReference1(null);
            }
        }

        if (RANDOM.nextBoolean()) {
            final StorageReference storageReference2 = newStorageReference();
            storageConsumer.setStorageReference2(storageReference2);
            if (RANDOM.nextBoolean()) {
                storageConsumer.setStorageReference2(null);
            }
        }

        LOGGER.log(Level.INFO, "storageConsumer\r\n{0}",
                   storageConsumer.toXml());

        ENTTIY_MANAGER.getTransaction().commit();
    }


}

