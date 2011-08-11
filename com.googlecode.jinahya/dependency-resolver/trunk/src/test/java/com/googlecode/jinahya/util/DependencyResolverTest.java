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


    private static final DependencyResolver<String> RESOLVER =
        new DependencyResolver<String>();


    static {
        RESOLVER.addDependencies("A", "B", "C", null);

        RESOLVER.addDependencies("B", "F", null);

        RESOLVER.addDependencies("C", "G", null);

        RESOLVER.addDependency("H", "B");

        RESOLVER.addDependencies("I", "J", "K");

        RESOLVER.addDependency("L", "K");

        RESOLVER.addDependency("M", null);

        RESOLVER.addDependency("N", null);

        RESOLVER.addDependencies("O", "P", "Q");
        RESOLVER.addDependency("P", "R");
        RESOLVER.addDependencies("Q", "R");
    }


    @Test
    public void testAddDependency() {

        final DependencyResolver<String> resolver =
            new DependencyResolver<String>();

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
    public void testAddDependencyForSelfDependency() {

        final DependencyResolver<String> resolver =
            new DependencyResolver<String>();

        resolver.addDependency("A", "A");
    }


    @Test(expectedExceptions = {IllegalStateException.class})
    public void testAddDependencyForCyclicDependency() {

        final DependencyResolver<String> resolver =
            new DependencyResolver<String>();

        resolver.addDependency("A", "B");
        resolver.addDependency("B", "C");

        resolver.addDependency("C", "A");
    }


    @Test
    public void testAddDependencies() {

        final DependencyResolver<String> resolver =
            new DependencyResolver<String>();

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
    public void testGetDependencyGroups() {

        System.out.println("paths from O to R: "
                           + RESOLVER.getDependencyGroups("O", "R"));
    }


    @Test
    public void testHasDependency() {

        final DependencyResolver<String> resolver =
            new DependencyResolver<String>();

        Assert.assertFalse(resolver.hasDependency("A", "B"));

        resolver.addDependency("A", "B");

        Assert.assertTrue(resolver.hasDependency("A", "B"));

        resolver.removeDependency("A", "B");

        Assert.assertFalse(resolver.hasDependency("A", "B"));
    }


    @Test
    public void testHasDependencies() {

        Assert.assertTrue(RESOLVER.hasDependencies("A", "F", "G"));

        Assert.assertFalse(RESOLVER.hasDependencies("H", "F", "G"));
    }


    @Test
    public void testRemoveDependency() {

        final DependencyResolver<String> resolver =
            new DependencyResolver<String>();

        resolver.removeDependency("A", "B");

        resolver.addDependency("A", "B");

        resolver.removeDependency("A", "B");
    }


    @Test
    public void testRemoveDependencies() {

        final DependencyResolver<String> resolver =
            new DependencyResolver<String>();

        resolver.removeDependencies("A", "B", "C", "D", null);
    }


    @Test
    public void testGetSingleGroup() {

        System.out.println("single: " + RESOLVER.getSingleGroup());
    }


    @Test
    public void testGetHorizontalGroup() {

        System.out.println("horizontal(0): " + RESOLVER.getHorizontalGroups(0));

        System.out.println("horizontal(1): " + RESOLVER.getHorizontalGroups(1));
    }


    @Test
    public void testGetVerticalGroup() {

        System.out.println("vertical(0): " + RESOLVER.getVerticalGroups(0));

        System.out.println("vertical(2): " + RESOLVER.getVerticalGroups(2));
    }
}

