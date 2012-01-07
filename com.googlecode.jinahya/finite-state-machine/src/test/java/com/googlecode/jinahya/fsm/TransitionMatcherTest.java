/*
 * Copyright 2011 onacit.
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


package com.googlecode.jinahya.fsm;


import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author onacit
 */
public class TransitionMatcherTest {


    @Test
    public void testYES() throws FSMException {

        try {
            TransitionMatcher.YES.matches(null);
            Assert.fail("passed: matches(null)");
        } catch (NullPointerException npe) {
            // expected
        }

        final Machine machine = new MachineImpl();

        final Transition transition = new Transition(
            machine, machine.getState(), State.INVALID);

        Assert.assertTrue(TransitionMatcher.YES.matches(transition));
    }


    @Test
    public void testNO() throws FSMException {

        try {
            TransitionMatcher.NO.matches(null);
            Assert.fail("passed: matches(null)");
        } catch (NullPointerException npe) {
            // expected
        }

        final Machine machine = new MachineImpl();

        final Transition transition = new Transition(
            machine, machine.getState(), State.INVALID);

        Assert.assertFalse(TransitionMatcher.NO.matches(transition));
    }


}

