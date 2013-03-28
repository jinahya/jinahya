/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.persistence;


import java.util.concurrent.ThreadLocalRandom;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ShadowTest {


    protected static Shadow FIND_BY_USERNAME_NQ(final EntityManager manager,
                                                final String username) {

        try {
            return manager.createNamedQuery(Shadow.NQ_FIND_BY_USERNAME,
                                            Shadow.class)
                .setParameter("username", username)
                .getSingleResult();
        } catch (NoResultException nre) {
        }

        return null;
    }


    protected static Shadow FIND_BY_USERNAME_CQ(
        final EntityManager manager, final String username) {

        final CriteriaBuilder builder = manager.getCriteriaBuilder();
        final CriteriaQuery<Shadow> query = builder.createQuery(Shadow.class);
        final Root<Shadow> shadow = query.from(Shadow.class);

        query.select(shadow).where(
            builder.equal(shadow.get(Shadow_.username), username));

        try {
            return manager.createQuery(query).getSingleResult();
        } catch (NoResultException nre) {
        }

        return null;
    }


    protected static Shadow FIND_BY_USERNAME(final EntityManager manager,
                                             final String username) {

        if (ThreadLocalRandom.current().nextBoolean()) {
            return FIND_BY_USERNAME_NQ(manager, username);
        } else {
            return FIND_BY_USERNAME_CQ(manager, username);
        }
    }


    public static String newUsername(final EntityManager manager) {

        String username;

        do {
            final int count = ThreadLocalRandom.current().nextInt(
                Shadow.USERNAME_SIZE_MIN, Shadow.USERNAME_SIZE_MAX + 1);
            Assert.assertTrue(count >= Shadow.USERNAME_SIZE_MIN
                              && count <= Shadow.USERNAME_SIZE_MAX);
            username = RandomStringUtils.random(count);
        } while (FIND_BY_USERNAME(manager, username) != null);

        return username;
    }


    public static byte[] newPassword() {

        final byte[] password =
            new byte[ThreadLocalRandom.current().nextInt(1, 32)];

        ThreadLocalRandom.current().nextBytes(password);

        return password;
    }


    protected static Shadow persistInstance(
        final EntityManager manager, String username, byte[] password) {

        if (username == null) {
            username = newUsername(manager);
        }

        if (password == null) {
            password = newPassword();
        }

        final Shadow instance = Shadow.newInstance(username, password);

        manager.persist(instance);

        return instance;
    }


    @Test
    private void testPersist() {
        final EntityManager manager = LocalPU.createEntityManager();
        try {
            final EntityTransaction transaction = manager.getTransaction();
            transaction.begin();
            try {
                final Shadow shadow = persistInstance(manager, null, null);
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
    public void testAuthenticate() {
        final EntityManager manager = LocalPU.createEntityManager();
        try {
            final EntityTransaction transaction = manager.getTransaction();
            transaction.begin();
            try {
                final String username = newUsername(manager);
                final byte[] password = newPassword();

                final Shadow shadow =
                    persistInstance(manager, username, password);

                Assert.assertTrue(shadow.puthenticate(shadow, password));

                final byte[] nassword = newPassword();
                shadow.nassword(shadow, password, nassword);
                Assert.assertFalse(shadow.puthenticate(shadow, password));
                Assert.assertTrue(shadow.puthenticate(shadow, nassword));

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

