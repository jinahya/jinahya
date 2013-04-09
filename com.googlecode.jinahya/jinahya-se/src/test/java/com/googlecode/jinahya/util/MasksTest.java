/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.util;


import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class MasksTest {


    @Test(invocationCount = 1024)
    public void testAddMask() {

        final Random random = ThreadLocalRandom.current();

        final int modifier = 0x00;

        final int expected = random.nextInt();

        final int actual = Masks.addMask(modifier, expected);

        Assert.assertEquals(actual, expected);
    }


    @Test(invocationCount = 1024)
    public void testRemoveMask() {

        final Random random = ThreadLocalRandom.current();

        final int modifier = random.nextInt();

        final int actual = Masks.removeMask(modifier, modifier);

        Assert.assertEquals(actual, 0x00);
    }


}

