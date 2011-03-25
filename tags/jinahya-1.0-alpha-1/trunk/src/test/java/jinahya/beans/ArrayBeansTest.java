/*
 * Copyright 2011 Jin Kwon.
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


package jinahya.beans;


import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ArrayBeansTest {


    @Test
    public void testGetIndex() {
        final ArrayBean<String> bean = new ArrayBean<String>(String.class);
        Assert.assertTrue(bean.getIndex() == -1);
    }


    @Test
    public void testSetIndex() {

        final ArrayBean<String> bean = new ArrayBean<String>(String.class);
        bean.setIndex(-1);

        bean.insertItemsAt(0, new String[]{"a", "b", "c", "d"});

        final int length = bean.getLength();
        Assert.assertTrue(length == 4);

        for (int i = 0; i < length; i++) {
            bean.setIndex(i);
        }
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testSetIndexForEmptyBeanWithNewIndexWrong() {

        final ArrayBean<String> bean = new ArrayBean<String>(String.class);
        bean.setIndex(0);
    }


    @Test
    public void testInsertItemsAt() {

        final ArrayBean<String> bean = new ArrayBean<String>(String.class);
        Assert.assertTrue(bean.getIndex() == -1);

        bean.insertItemsAt(0, new String[]{"c", "d"});
        Assert.assertTrue(bean.getIndex() == 0);
        Assert.assertTrue(bean.getLength() == 2);
        Assert.assertTrue(Arrays.equals(
            bean.getItems(), new String[]{"c", "d"}));

        bean.insertItemsAt(0, new String[]{"a", "b"});
        Assert.assertTrue(bean.getIndex() == 2);
        Assert.assertTrue(bean.getLength() == 4);

        Assert.assertTrue(Arrays.equals(
            bean.getItems(), new String[]{"a", "b", "c", "d"}));
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testInsertItemsAtWithTargetIndexWrong() {

        final ArrayBean<String> bean = new ArrayBean<String>(String.class);

        bean.insertItemsAt(1, new String[0]);
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testInsertItemsAtWithNewItemsNull() {

        final ArrayBean<String> bean = new ArrayBean<String>(String.class);

        bean.insertItemsAt(0, null);
    }


    @Test()
    public void testInsertItemsAtFirst() {

        final ArrayBean<String> bean = new ArrayBean<String>(String.class);
        Assert.assertTrue(bean.getIndex() == -1);

        bean.insertItemsAt(0, new String[]{"c", "d"});
        Assert.assertTrue(bean.getIndex() == 0);

        bean.insertItemsAtFirst(new String[]{"a", "b"});
        Assert.assertTrue(bean.getIndex() == 2);

        Assert.assertTrue(Arrays.equals(
            bean.getItems(), new String[]{"a", "b", "c", "d"}));
    }


    @Test()
    public void testInsertItemsAtLast() {

        final ArrayBean<String> bean = new ArrayBean<String>(String.class);
        Assert.assertTrue(bean.getIndex() == -1);

        bean.insertItemsAt(0, new String[]{"a", "b"});
        Assert.assertTrue(bean.getIndex() == 0);

        bean.insertItemsAtLast(new String[]{"c", "d"});
        Assert.assertTrue(bean.getIndex() == 0);

        Assert.assertTrue(Arrays.equals(
            bean.getItems(), new String[]{"a", "b", "c", "d"}));
    }
}
