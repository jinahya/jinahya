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


import java.util.List;

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
        RESOLVER.addDependency("A", "B");
        RESOLVER.addDependency("A", "C");
        RESOLVER.addDependency("A", null);

        RESOLVER.addDependency("B", "F");
        RESOLVER.addDependency("B", null);

        RESOLVER.addDependency("C", "G");
        RESOLVER.addDependency("C", null);

        RESOLVER.addDependency("H", "B");

        RESOLVER.addDependency("I", "J");
        RESOLVER.addDependency("I", "K");

        RESOLVER.addDependency("L", "K");

        RESOLVER.addDependency("X", null);
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
    public void testFindPaths() {

        final DependencyResolver<String> resolver =
            new DependencyResolver<String>();

        resolver.addDependency("A", "C");

        resolver.addDependency("A", "B");

        resolver.addDependency("B", "C");

        final List<List<String>> paths = resolver.findDependencyPath("A", "C");
        for (List<String> path : paths) {
            System.out.println(path);
        }
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
    public void testRemoveDependency() {

        final DependencyResolver<String> resolver =
            new DependencyResolver<String>();

        resolver.removeDependency("A", "B");

        resolver.addDependency("A", "B");

        resolver.removeDependency("A", "B");
    }


    @Test
    public void testGetSingleGroup() {

        System.out.println("single: " + RESOLVER.getSingleGroup());
    }


    @Test
    public void testGetHorizontalGroup() {

        final List<List<String>> groups = RESOLVER.getHorizontalGroups(0);
        for (List<String> group : groups) {
            System.out.println("horizontal: " + group);
        }
    }


    @Test
    public void testGetVerticalGroup() {

        final List<List<String>> groups = RESOLVER.getVerticalGroups(0);
        for (List<String> group : groups) {
            System.out.println("vertical: " + group);
        }
    }
}

