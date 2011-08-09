/*
 * Copyright 2011 <a href="mailto:jinahya@gmail.com">Jin Kwon</a>.
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


import java.util.Iterator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class DependencyResolverTest {


    @Test
    public void testAddDependency() {

        final DependencyResolver resolver = new DependencyResolver();

        try {
            resolver.addDependency(null, "null");
            Assert.fail("passed: null source");
        } catch (NullPointerException npe) {
            // expected
        }

        try {
            resolver.addDependency("null", null);
            Assert.fail("passed: null target");
        } catch (NullPointerException npe) {
            // expected
        }

        resolver.addDependency("source", "target");
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testAddDependencyForSelfDependency() {

        final DependencyResolver resolver = new DependencyResolver();

        resolver.addDependency("A", "A");
    }


    @Test(expectedExceptions = {IllegalStateException.class})
    public void testAddDependencyForCyclicDependency() {

        final DependencyResolver resolver = new DependencyResolver();

        resolver.addDependency("A", "B");
        resolver.addDependency("B", "C");

        resolver.addDependency("C", "A");
    }


    @Test
    public void testFindDependencies() {

        final DependencyResolver resolver = new DependencyResolver();

        resolver.addDependency("A", "C");

        resolver.addDependency("A", "B");

        resolver.addDependency("B", "C");

        final List<?> dependencies = resolver.findDependencies("A", "C");
        for (Iterator<?> i = dependencies.iterator(); i.hasNext();) {
            final List<?> dependency = (List<?>) i.next();
            System.out.println(dependency);
        }
    }


    @Test
    public void testHasDependency() {

        final DependencyResolver resolver = new DependencyResolver();

        Assert.assertFalse(resolver.hasDependency("A", "B"));

        resolver.addDependency("A", "B");

        Assert.assertTrue(resolver.hasDependency("A", "B"));

        resolver.removeDependency("A", "B");

        Assert.assertFalse(resolver.hasDependency("A", "B"));
    }


    @Test
    public void testRemoveDependency() {

        final DependencyResolver resolver = new DependencyResolver();

        resolver.removeDependency("A", "B");

        resolver.addDependency("A", "B");

        resolver.removeDependency("A", "B");
    }
}

