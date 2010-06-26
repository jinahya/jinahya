/*
 *  Copyright 2010 Jin Kwon.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package jinahya.util;


import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class KeyValueSupportTest {


    @Test
    public void testGetInt() {
        final KeyValueSupport support = new KeyValueSupport();

        Assert.assertEquals(support.getInt("key", 1), 1);
        Assert.assertEquals(support.putInt("key", 2), 0);
        Assert.assertEquals(support.getInt("key", 1), 2);
    }


    @Test
    public void testPutInt() {
        final KeyValueSupport support = new KeyValueSupport();
        Assert.assertEquals(support.putInt("key", 1), 0);
        Assert.assertEquals(support.putInt("key", 2), 1);
    }


    @Test
    public void testRidInt() {
        final KeyValueSupport support = new KeyValueSupport();
        Assert.assertEquals(support.ridInt("key"), 0);
        Assert.assertEquals(support.putInt("key", 1), 0);
        Assert.assertEquals(support.ridInt("key"), 1);
    }
}
