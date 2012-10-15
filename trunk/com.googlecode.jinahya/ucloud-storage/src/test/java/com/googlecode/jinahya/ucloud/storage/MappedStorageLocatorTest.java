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


import java.lang.reflect.Field;
import java.util.Random;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class MappedStorageLocatorTest {


    private static final Random RANDOM = new Random();


    private static String getStaticFieldValueString(final String name) {
        try {
            final Field field =
                MappedStorageLocator.class.getDeclaredField(name);
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            return (String) field.get(null);
        } catch (NoSuchFieldException nsfe) {
            throw new RuntimeException(nsfe.getMessage());
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(iae.getMessage());
        }
    }


    private static int getStaticFieldValueInt(final String name) {
        try {
            final Field field =
                MappedStorageLocator.class.getDeclaredField(name);
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            return field.getInt(null);
        } catch (NoSuchFieldException nsfe) {
            throw new RuntimeException(nsfe.getMessage());
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(iae.getMessage());
        }
    }


    @Test
    public static void testCONTAINER_NAME_PREFIX_SIZE_MAX() {

        final int CONTAINER_NAME_PREFIX_SIZE_MAX =
            getStaticFieldValueInt("CONTAINER_NAME_PREFIX_SIZE_MAX");

        final int CONTAINER_NAME_SEQUENCE_FORMAT_WIDTH =
            getStaticFieldValueInt("CONTAINER_NAME_SEQUENCE_FORMAT_WIDTH");
        final String PREFIX_SEQUENCE_DELIMITER =
            getStaticFieldValueString("PREFIX_SEQUENCE_DELIMITER");

        Assert.assertEquals(
            CONTAINER_NAME_PREFIX_SIZE_MAX,
            MappedStorageLocator.CONTAINER_NAME_SIZE_MAX
            - CONTAINER_NAME_SEQUENCE_FORMAT_WIDTH
            - PREFIX_SEQUENCE_DELIMITER.length());
    }


    @Test
    public static void testOBJECT_NAME_PREFIX_SIZE_MAX() {

        final int OBJECT_NAME_PREFIX_SIZE_MAX =
            getStaticFieldValueInt("OBJECT_NAME_PREFIX_SIZE_MAX");

        final int OBJECT_NAME_SEQUENCE_FORMAT_WIDTH =
            getStaticFieldValueInt("OBJECT_NAME_SEQUENCE_FORMAT_WIDTH");
        final String PREFIX_SEQUENCE_DELIMITER =
            getStaticFieldValueString("PREFIX_SEQUENCE_DELIMITER");

        Assert.assertEquals(
            OBJECT_NAME_PREFIX_SIZE_MAX,
            MappedStorageLocator.OBJECT_NAME_SIZE_MAX
            - OBJECT_NAME_SEQUENCE_FORMAT_WIDTH
            - PREFIX_SEQUENCE_DELIMITER.length());
    }


    @Test
    public static void testOBJECT_NAME_SEQUENCE_BITS() {

        final int OBJECT_NAME_SEQUENCE_BITS =
            getStaticFieldValueInt("OBJECT_NAME_SEQUENCE_BITS");
        Assert.assertTrue(OBJECT_NAME_SEQUENCE_BITS > 0);
        Assert.assertTrue(OBJECT_NAME_SEQUENCE_BITS < Long.SIZE);
    }


//    @Test
//    public static void testPREFIX_SEQUENCE_DELIMITERIsURLSafe()
//        throws UnsupportedEncodingException {
//
//        final String expected = String.valueOf(PREFIX_SEQUENCE_DELIMITER);
//        final String actual = URLEncoder.encode(expected, "UTF-8");
//        Assert.assertEquals(actual, expected);
//    }
    @Test
    public void testNameAndLength() {

        final MappedStorageLocator locator = new MappedStorageLocator();

        testNameAndLength(locator, Long.MIN_VALUE);
        testNameAndLength(locator, -1L);
        testNameAndLength(locator, 0);
        testNameAndLength(locator, 1L);
        testNameAndLength(locator, Long.MAX_VALUE);
    }


    private void testNameAndLength(final MappedStorageLocator storageLocator,
                                   final long sequenceNumber) {

        storageLocator.setContainerName(
            MappedStorageLocator.formatContainerName(null, sequenceNumber));
        storageLocator.setObjectName(
            MappedStorageLocator.formatObjectName(null, sequenceNumber));
        final String containerName = storageLocator.getContainerName();
        final String objectName = storageLocator.getObjectName();
        System.out.printf("%20d %16s (%2d) %8s (%2d)\n", sequenceNumber,
                          containerName, containerName.length(), objectName,
                          objectName.length());
    }


//    @Test(invocationCount = 100)
//    public void testSequenceNumber() {
//
//        final MappedStorageLocator locator = new MappedStorageLocator();
//
//        final long expected = RANDOM.nextLong();
//        locator.setContainerName(null, expected);
//        locator.setObjectName(null, expected);
//
//        final String containerName = locator.getContainerName();
//        final String objectName = locator.getObjectName();
//
//        final long actual = (Long.parseLong(containerName) << OBJECT_NAME_BITS)
//                            | Long.parseLong(objectName);
//
//        Assert.assertEquals(actual, expected);
//    }
    @Test
    public void testGetContainerName() {

        final MappedStorageLocator locator = new MappedStorageLocator();

        Assert.assertNull(locator.getContainerName());
    }


    @Test
    public void testSetContainerName() {

        final MappedStorageLocator locator = new MappedStorageLocator();

        locator.setContainerName(null);
        Assert.assertNull(locator.getContainerName());

//        final String expected = RandomStringUtils.random(RANDOM.nextInt(128));
//        locator.setContainerName(expected);
//
//        final String actual = locator.getContainerName();
//        Assert.assertEquals(actual, expected);
    }


    @Test
    public void testGetObjectName() {

        final MappedStorageLocator locator = new MappedStorageLocator();

        Assert.assertNull(locator.getObjectName());
    }


    @Test
    public void testSetObjectName() {

        final MappedStorageLocator locator = new MappedStorageLocator();

        locator.setObjectName(null);
        Assert.assertNull(locator.getObjectName());

//        final String expected = RandomStringUtils.random(RANDOM.nextInt(128));
//        locator.setObjectName(expected);
//
//        final String actual = locator.getObjectName();
//        Assert.assertEquals(actual, expected);
    }


}

