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


import junit.framework.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class BufferedContentProducerTest {


    @Test
    public void testConstructors() {

        new BufferedContentProducer("application/octet-stream", new byte[0]);

        new BufferedContentProducer(null, new byte[0]);

        try {
            new BufferedContentProducer("application/octet-stream", null);
            Assert.fail("passed: new BufferedContentProducer(N/A, null)");
        } catch (IllegalArgumentException iae) {
        }
    }


}

