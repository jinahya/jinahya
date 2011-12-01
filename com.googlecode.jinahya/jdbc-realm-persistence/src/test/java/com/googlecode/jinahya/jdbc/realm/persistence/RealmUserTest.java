/*
 * Copyright 2011 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.jdbc.realm.persistence;


import java.lang.reflect.Field;

import java.security.NoSuchAlgorithmException;

import java.util.Collection;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class RealmUserTest {


    private static final Random RANDOM = new Random();


    private static String getPassword(final RealmUser user)
        throws NoSuchFieldException, IllegalAccessException {

        final Field passwordField =
            RealmUser.class.getDeclaredField("password");
        if (!passwordField.isAccessible()) {
            passwordField.setAccessible(true);
        }
        return (String) passwordField.get(user);
    }


    private static void setPassword(final RealmUser user, final String password)
        throws NoSuchFieldException, IllegalAccessException {

        final Field passwordField =
            RealmUser.class.getDeclaredField("password");
        if (!passwordField.isAccessible()) {
            passwordField.setAccessible(true);
        }

        passwordField.set(user, password);
    }


    private static void testHashPassword(final String passwordInPlainText,
                                         final String expected)
        throws NoSuchAlgorithmException {

        final String actual = RealmUser.hashPassword(passwordInPlainText);

        Assert.assertEquals(actual, expected);
    }


    @Test
    public void testHashPassword() throws Exception {

        testHashPassword("", "cf83e1357eefb8bdf1542850d66d8007"
                             + "d620e4050b5715dc83f4a921d36ce9ce"
                             + "47d0d13c5d85f2b0ff8318d2877eec2f"
                             + "63b931bd47417a81a538327af927da3e");
    }


    @Test
    public void testGetUsername() {

        final RealmUser user = new RealmUser();

        user.getUserName();
    }


    @Test
    public void testSetUsername() {

        final RealmUser user = new RealmUser();

        try {
            user.setUserName(null);
            Assert.fail("passed: setUserName(null)");
        } catch (NullPointerException npe) {
            // expected
        }

        try {
            user.setUserName("");
            Assert.fail("passed: setUserName(\"\")");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        final String expected = RandomStringUtils.randomAscii(128);
        user.setUserName(expected);

        final String actual = user.getUserName();
        Assert.assertEquals(actual, expected);
    }


    @Test
    public void testIsEnabled() {

        final RealmUser user = new RealmUser();

        Assert.assertFalse(user.isEnabled());
    }


    @Test
    public void testSetEnabled() {

        final RealmUser user = new RealmUser();

        final boolean expected = RANDOM.nextBoolean();
        user.setEnabled(expected);

        final boolean actual = user.isEnabled();
        Assert.assertEquals(actual, expected);
    }


    @Test
    public void testGetRoles() {

        final RealmUser user = new RealmUser();

        final Collection<RealmRole> roles = user.getRoles();

        Assert.assertNotNull(roles);
        Assert.assertTrue(roles.isEmpty());
    }
}
