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


import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class DependencyResolverTest {


    @Test
    public void testAddDependency() throws DependencyResolverException {

        final DependencyResolver<String> resolver =
            new MapDependencyResolver<String>();

        try {
            resolver.addDependency(null, "null");
            Assert.fail("passed: null source");
        } catch (NullPointerException npe) {
            // expected
        }

        resolver.addDependency("source", null);

        resolver.addDependency("source", "target");
    }


    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void testAddDependencyForSelfDependency()
        throws DependencyResolverException {

        final DependencyResolver<String> resolver =
            new MapDependencyResolver<String>();

        resolver.addDependency("A", "A");
    }


    @Test(expectedExceptions = {CyclicDependencyException.class})
    public void testAddDependencyForCyclicDependency()
        throws DependencyResolverException {

        final DependencyResolver<String> resolver =
            new MapDependencyResolver<String>();

        resolver.addDependency("A", "B");
        resolver.addDependency("B", "C");

        resolver.addDependency("C", "A");
    }


    @Test
    public void testAddDependencies() throws DependencyResolverException {

        final DependencyResolver<String> resolver =
            new MapDependencyResolver<String>();

        try {
            resolver.addDependencies(null, "null");
            Assert.fail("passed: null source");
        } catch (NullPointerException npe) {
            // expected
        }

        try {
            resolver.addDependencies("source", (String[]) null);
            Assert.fail("passed: null targets");
        } catch (NullPointerException npe) {
            // expected
        }

        resolver.addDependencies("source", "target1", "target2", null);
    }


    @Test
    public void testHasDependency() throws DependencyResolverException {

        final DependencyResolver<String> resolver =
            new MapDependencyResolver<String>();

        Assert.assertFalse(resolver.hasDependency("A", "B"));

        resolver.addDependency("A", "B");

        Assert.assertTrue(resolver.hasDependency("A", "B"));

        resolver.removeDependency("A", "B");

        Assert.assertFalse(resolver.hasDependency("A", "B"));
    }


    @Test
    public void testRemoveDependency() throws DependencyResolverException {

        final DependencyResolver<String> resolver =
            new MapDependencyResolver<String>();

        resolver.removeDependency("A", "B");

        resolver.addDependency("A", "B");

        resolver.removeDependency("A", "B");
    }


    @Test
    public void testRemoveDependencies() {

        final DependencyResolver<String> resolver =
            new MapDependencyResolver<String>();

        resolver.removeDependencies("A", "B", "C", "D", null);
    }
}

