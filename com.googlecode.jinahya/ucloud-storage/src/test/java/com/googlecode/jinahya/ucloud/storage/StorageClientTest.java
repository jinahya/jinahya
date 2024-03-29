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


import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Test(singleThreaded = true)
public class StorageClientTest {


    @Test
    public void testConstructor() {

        try {
            new StorageClient(null, "storagePass");
            Assert.fail("passed: new UcloudStorageClient(null, N/A)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            new StorageClient("", "storagePass");
            Assert.fail("passed: new UcloudStorageClient(\"\", N/A)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            new StorageClient("storageUser", null);
            Assert.fail("passed: new UcloudStorageClient(N/A, null)");
        } catch (IllegalArgumentException iae) {
            // expected
        }

        try {
            new StorageClient("storageUser", "");
            Assert.fail("passed: new UcloudStorageClient(N/A, \"\")");
        } catch (IllegalArgumentException iae) {
            // expected
        }
    }


}

