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


import java.sql.SQLException;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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


    @BeforeClass
    public static void setUp() throws ClassNotFoundException, SQLException {

        ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("ucloudStorageTestPU");

        ENTTIY_MANAGER = ENTITY_MANAGER_FACTORY.createEntityManager();
    }


    @AfterClass
    public static void tearDown() {
    }


    @Test
    public void test() {

        final StorageLocator locator = new StorageLocator();
        ENTTIY_MANAGER.persist(locator);
        System.out.println(locator.getId());
    
    }


}

