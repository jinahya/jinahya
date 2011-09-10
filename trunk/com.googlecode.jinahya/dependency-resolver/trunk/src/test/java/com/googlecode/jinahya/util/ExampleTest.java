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
public class ExampleTest {


    private static final DependencyResolver<String> RESOLVER =
        new DependencyResolver<String>();


    static {
        try {
            RESOLVER.add("A", "B", "C", null);

            RESOLVER.add("B", "F", null);

            RESOLVER.add("C", "G", null);

            RESOLVER.add("H", "B");

            RESOLVER.add("I", "J", "K");

            RESOLVER.add("L", "K");

            RESOLVER.add("M", (String) null);

            RESOLVER.add("N", (String) null);

            RESOLVER.add("O", "P", "Q");
            RESOLVER.add("P", "R");
            RESOLVER.add("Q", "R");
        } catch (DependencyResolverException dre) {
            throw new InstantiationError(dre.getMessage());
        }
    }


    @Test
    public void testGetDependencyGroups() {

        System.out.println("paths from O to R: "
                           + RESOLVER.getPaths("O", "R"));
    }


    @Test
    public void testHasDependencies() {

        Assert.assertTrue(RESOLVER.containsAll("A", "F", "G"));

        Assert.assertFalse(RESOLVER.containsAll("H", "F", "G"));
    }


    @Test
    public void testGetSingleGroup() {

        System.out.println("single: " + RESOLVER.getSingleGroup());
    }


    @Test
    public void testGetVerticalGroup() {

        System.out.println("vertical: " + RESOLVER.getVerticalGroups());
    }


    @Test
    public void testGetHorizontalGroup() {

        System.out.println("horizontal: " + RESOLVER.getHorizontalGroups());
    }


    public void testGetDependencyPaths() {
        System.out.println("from O to R: "
                           + RESOLVER.getPaths("O", "R"));
    }
}
