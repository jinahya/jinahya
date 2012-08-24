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
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ContentLocatorTest {


    private static final Random RANDOM = new Random();


    @Test
    public void testNewInstance() {

        for (long i = 0L; i < 10L; i++) {
            final ContentLocator locator =
                ContentLocator.newInstance(null, i);
            System.out.println(locator);
            Assert.assertNotNull(locator.getContainerName());
            Assert.assertNotNull(locator.getObjectName());
        }

        System.out.println(Long.MIN_VALUE >>> 20);
        System.out.println(Long.MAX_VALUE >>> 20);

        {
            final ContentLocator locator =
                ContentLocator.newInstance(null, -1L);
            System.out.println(locator);
            Assert.assertNotNull(locator.getContainerName());
            Assert.assertNotNull(locator.getObjectName());
        }

        {
            final ContentLocator locator =
                ContentLocator.newInstance(null, 1L);
            System.out.println(locator);
            Assert.assertNotNull(locator.getContainerName());
            Assert.assertNotNull(locator.getObjectName());
        }

        {
            final ContentLocator locator =
                ContentLocator.newInstance(null, Long.MIN_VALUE);
            System.out.println(locator);
            Assert.assertNotNull(locator.getContainerName());
            Assert.assertNotNull(locator.getObjectName());
        }

        {
            final ContentLocator locator =
                ContentLocator.newInstance(null, Long.MAX_VALUE);
            System.out.println(locator);
            Assert.assertNotNull(locator.getContainerName());
            Assert.assertNotNull(locator.getObjectName());
        }
    }


}

