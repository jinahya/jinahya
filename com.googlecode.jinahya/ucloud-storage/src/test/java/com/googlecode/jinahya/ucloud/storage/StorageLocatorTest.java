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
public class StorageLocatorTest {


    private static final Random RANDOM = new Random();


    private static final int OBJECT_NAME_BITS;


    static {
        try {
            final Field field = StorageLocator.class.getDeclaredField(
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


    private static final long OBJECT_NAME_MASK;


    static {
        try {
            final Field field = StorageLocator.class.getDeclaredField(
                "OBJECT_NAME_MASK");
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            OBJECT_NAME_MASK = field.getLong(null);
        } catch (NoSuchFieldException nsfe) {
            throw new InstantiationError(nsfe.toString());
        } catch (IllegalAccessException iae) {
            throw new InstantiationError(iae.toString());
        }
    }


    private static final char PREFIX_SEQUENCE_DELIMITER;


    static {
        try {
            final Field field = StorageLocator.class.getDeclaredField(
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


    private static class ExtendedStorageLocator extends StorageLocator {
    }


    @Test
    public static void testNewInstance() {

        final String containerNamePrefix =
            RandomStringUtils.randomAscii(RANDOM.nextInt(49));
        final String objectNamePrefix =
            RandomStringUtils.randomAscii(RANDOM.nextInt(255));
        final ExtendedStorageLocator instance = StorageLocator.newInstance(
            ExtendedStorageLocator.class, containerNamePrefix, objectNamePrefix,
            RANDOM.nextLong());
        System.out.println(instance.getContainerName());
        System.out.println(instance.getObjectName());
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
    public void testNameAndLength() {

        final StorageLocator locator = new StorageLocator();

        testNameAndLength(locator, Long.MIN_VALUE);
        testNameAndLength(locator, -1L);
        testNameAndLength(locator, 0);
        testNameAndLength(locator, 1L);
        testNameAndLength(locator, Long.MAX_VALUE);
    }


    private void testNameAndLength(final StorageLocator storageLocator,
                                   final long sequenceNumber) {

        storageLocator.setContainerName(null, sequenceNumber);
        storageLocator.setObjectName(null, sequenceNumber);
        final String containerName = storageLocator.getContainerName();
        final String objectName = storageLocator.getObjectName();
        System.out.printf("%20d %16s (%2d) %8s (%2d)\n", sequenceNumber,
                          containerName, containerName.length(), objectName,
                          objectName.length());
    }


    @Test(invocationCount = 100)
    public void testSequenceNumber() {

        final StorageLocator locator = new StorageLocator();

        final long expected = RANDOM.nextLong();
        locator.setContainerName(null, expected);
        locator.setObjectName(null, expected);

        final String containerName = locator.getContainerName();
        final String objectName = locator.getObjectName();

        final long actual = (Long.parseLong(containerName) << OBJECT_NAME_BITS)
                            | Long.parseLong(objectName);

        Assert.assertEquals(actual, expected);
    }


    @Test
    public void testContainerName() {

        final StorageLocator locator = new StorageLocator();

        locator.setContainerName(null);
        Assert.assertNull(locator.getContainerName());

        final String expected = RandomStringUtils.random(RANDOM.nextInt(128));
        locator.setContainerName(expected);

        final String actual = locator.getContainerName();
        Assert.assertEquals(actual, expected);
    }


    @Test
    public void testObjectName() {

        final StorageLocator locator = new StorageLocator();

        locator.setObjectName(null);
        Assert.assertNull(locator.getObjectName());

        final String expected = RandomStringUtils.random(RANDOM.nextInt(128));
        locator.setObjectName(expected);

        final String actual = locator.getObjectName();
        Assert.assertEquals(actual, expected);
    }


}

