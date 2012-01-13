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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.lang3.RandomStringUtils;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class UserTest {


    private static final Random RANDOM = new Random();


    private static String getPassword(final UserTest user)
        throws NoSuchFieldException, IllegalAccessException {

        final Field passwordField =
            UserTest.class.getDeclaredField("password");
        if (!passwordField.isAccessible()) {
            passwordField.setAccessible(true);
        }
        return (String) passwordField.get(user);
    }


    private static void setPassword(final UserTest user, final String password)
        throws NoSuchFieldException, IllegalAccessException {

        final Field passwordField =
            UserTest.class.getDeclaredField("password");
        if (!passwordField.isAccessible()) {
            passwordField.setAccessible(true);
        }

        passwordField.set(user, password);
    }


    private static void testHashPassword(final String passwordInPlainText,
                                         final String expected)
        throws NoSuchAlgorithmException {

        final String actual = User.hash(passwordInPlainText);

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

        final User user = new User();

        user.getName();
    }


    @Test
    public void testSetUsername() {

        final User user = new User();

        final String expected = RandomStringUtils.randomAscii(128);
        user.setName(expected);

        final String actual = user.getName();
        Assert.assertEquals(actual, expected);
    }


    @Test
    public void testGetRoles() {

        final User user = new User();

        final Collection<Role> roles = user.getRoles();

        Assert.assertNotNull(roles);
        Assert.assertTrue(roles.isEmpty());
    }


    @Test
    public void testXml() throws JAXBException {

        final Service service = new Service();
        service.setName("service");

        final Role role = new Role();
        role.setName("role");

        service.getRoles().add(role);

        final User user = new User();
        user.setName("user");

        user.getRoles().add(role);

        final JAXBContext context =
            JAXBContext.newInstance(JAXBTest.class.getPackage().getName());

        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        marshaller.marshal(user, System.out);
        System.out.flush();
    }


}

