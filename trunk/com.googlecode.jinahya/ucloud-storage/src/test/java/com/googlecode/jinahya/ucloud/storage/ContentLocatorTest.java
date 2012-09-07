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


import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ContentLocatorTest {


    private static final Random RANDOM = new Random();


    private static final int OBJECT_NAME_BITS;


    static {
        try {
            final Field field = ContentLocator.class.getDeclaredField(
                "OBJECT_NAME_BITS");
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            OBJECT_NAME_BITS = field.getInt(null);
        } catch (NoSuchFieldException nsfe) {
            throw new InstantiationError(nsfe.toString());
        } catch (IllegalAccessException iae) {
            throw new InstantiationError(iae.toString());
        }
    }


    private static final char PREFIX_SEQUENCE_DELIMITER;


    static {
        try {
            final Field field = ContentLocator.class.getDeclaredField(
                "PREFIX_SEQUENCE_DELIMITER");
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            PREFIX_SEQUENCE_DELIMITER = field.getChar(null);
        } catch (NoSuchFieldException nsfe) {
            throw new InstantiationError(nsfe.toString());
        } catch (IllegalAccessException iae) {
            throw new InstantiationError(iae.toString());
        }
    }


    @Test
    public static void testOBJECT_NAME_BITS() {

        Assert.assertTrue(OBJECT_NAME_BITS > 0);
        Assert.assertTrue(OBJECT_NAME_BITS < Long.SIZE);
    }


    @Test
    public static void testPREFIX_SEQUENCE_DELIMITERIsURLSafe()
        throws UnsupportedEncodingException {

        final String expected = String.valueOf(PREFIX_SEQUENCE_DELIMITER);
        final String actual = URLEncoder.encode(expected, "UTF-8");
        Assert.assertEquals(actual, expected);
    }


    @Test
    public void testContainerNameLength() {

        final ContentLocator locator = new ContentLocator();

        testContainerNameLength(locator, Long.MIN_VALUE);
        testContainerNameLength(locator, -1L);
        testContainerNameLength(locator, 0);
        testContainerNameLength(locator, 1L);
        testContainerNameLength(locator, Long.MAX_VALUE);
    }


    private void testContainerNameLength(
        final ContentLocator contentLoctor,
        final long sequenceNumber) {

        contentLoctor.setContainerName(null, sequenceNumber);
        contentLoctor.setObjectName(null, sequenceNumber);
        final String containerName = contentLoctor.getContainerName();
        final String objectName = contentLoctor.getObjectName();
        System.out.println(
            sequenceNumber + " / "
            + containerName + " (" + containerName.length() + ")  / "
            + objectName + " (" + objectName.length() + ")");
    }


    @Test(invocationCount = 100)
    public void testSequenceNumber() {

        final ContentLocator locator = new ContentLocator();

        final long expected = RANDOM.nextLong();
        locator.setContainerName(null, expected);
        locator.setObjectName(null, expected);

        final String containerName = locator.getContainerName();
        final String objectName = locator.getObjectName();
        System.out.println(containerName + " / " + objectName);

        final long actual = (Long.parseLong(containerName) << OBJECT_NAME_BITS)
                            | Long.parseLong(objectName);

        Assert.assertEquals(actual, expected);
    }


    @Test
    public void testNewInstance() {

        for (long i = 0L; i < 10L; i++) {
            final ContentLocator locator =
                new ContentLocator();
            locator.setContainerName(null, i);
            locator.setObjectName(null, i);
            System.out.println(locator);
            Assert.assertNotNull(locator.getContainerName());
            Assert.assertNotNull(locator.getObjectName());
        }
    }


    @Test
    public void testSetContainerName() {

        final ContentLocator locator = new ContentLocator();

        locator.setContainerName(null);
        locator.setContainerName(RandomStringUtils.random(100));

        for (int i = 0; i < 100; i++) {
            final String containerNamePrefix =
                RandomStringUtils.random(RANDOM.nextInt(10));
            locator.setContainerName(containerNamePrefix, i);
            Assert.assertTrue(
                locator.getContainerName().startsWith(containerNamePrefix));
        }
    }


    @Test
    public void testSetObjectName() {

        final ContentLocator locator = new ContentLocator();

        locator.setObjectName(null);
        locator.setObjectName(RandomStringUtils.random(100));

        for (int i = 0; i < 100; i++) {
            final String objectNamePrefix =
                RandomStringUtils.random(RANDOM.nextInt(10));
            locator.setObjectName(objectNamePrefix, i);
            Assert.assertTrue(
                locator.getObjectName().startsWith(objectNamePrefix));
        }
    }


}

