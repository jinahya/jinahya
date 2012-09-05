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


import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class EmbeddableContentLocatorTest {


    private static final Random RANDOM = new Random();


    @Test
    public void testNewInstance() {

        for (long i = 0L; i < 10L; i++) {
            final EmbeddableContentLocator locator =
                new EmbeddableContentLocator();
            locator.setContainerName(null, i);
            locator.setObjectName(null, i);
            System.out.println(locator);
            Assert.assertNotNull(locator.getContainerName());
            Assert.assertNotNull(locator.getObjectName());
        }
    }


    @Test
    public void testSetContainerName() {

        final EmbeddableContentLocator locator = new EmbeddableContentLocator();

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

        final EmbeddableContentLocator locator = new EmbeddableContentLocator();

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

