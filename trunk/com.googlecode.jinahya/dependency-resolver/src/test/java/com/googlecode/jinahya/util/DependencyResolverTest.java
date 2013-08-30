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


import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class DependencyResolverTest {


//    @Test
//    public void testAdd() {
//
//        final RawDependencyResolver<String> resolver =
//            new RawDependencyResolver<String>();
//
//        try {
//            resolver.add(null, "null");
//            Assert.fail("passed: add(null, E)");
//        } catch (NullPointerException npe) {
//            // expected
//        }
//
//        try {
//            resolver.add("null", (String[]) null);
//            Assert.fail("passed: add(E, null)");
//        } catch (NullPointerException npe) {
//            // expected
//        }
//
//        resolver.add("source", (String) null);
//        Assert.assertTrue(resolver.contains("source", (String) null));
//
//        for (int i = 0; i < 10; i++) {
//            resolver.add("source", "target" + i);
//            Assert.assertTrue(resolver.contains("source", "target" + i));
//        }
//    }
//
//
//    @Test(expectedExceptions = {IllegalArgumentException.class})
//    public void testAddForSelfDependency() {
//
//        final RawDependencyResolver<String> resolver =
//            new RawDependencyResolver<String>();
//
//        resolver.add("A", "A");
//    }
//
//
//    @Test(expectedExceptions = {IllegalStateException.class})
//    public void testAddDependencyForCyclicDependency() {
//
//        final RawDependencyResolver<String> resolver =
//            new RawDependencyResolver<String>();
//
//        resolver.add("A", "B");
//        resolver.add("B", "C");
//
//        resolver.add("C", "A");
//    }
//
//
//    @Test
//    public void testAddDependencies() {
//
//        final RawDependencyResolver<String> resolver =
//            new RawDependencyResolver<String>();
//
//        try {
//            resolver.add(null, "null");
//            Assert.fail("passed: null source");
//        } catch (NullPointerException npe) {
//            // expected
//        }
//
//        try {
//            resolver.add("source", (String[]) null);
//            Assert.fail("passed: null targets");
//        } catch (NullPointerException npe) {
//            // expected
//        }
//
//        resolver.add("source", "target1", "target2", null);
//    }
//
//
//    @Test
//    public void testContains() {
//
//        final RawDependencyResolver<String> resolver =
//            new RawDependencyResolver<String>();
//
//        Assert.assertFalse(resolver.contains("A", "B"));
//
//        resolver.add("A", "B");
//        Assert.assertTrue(resolver.contains("A", "B"));
//        resolver.remove("A", "B");
//        Assert.assertFalse(resolver.contains("A", "B"));
//
//        resolver.add("A", (String) null);
//        Assert.assertTrue(resolver.contains("A", null));
//        resolver.remove("A", (String) null);
//        Assert.assertFalse(resolver.contains("A", null));
//    }
//
//
//    @Test
//    public void testRemove() {
//
//        final RawDependencyResolver<String> resolver =
//            new RawDependencyResolver<String>();
//
//        resolver.remove("A", "B");
//
//        resolver.add("A", "B");
//
//        resolver.remove("A", "B");
//
//        resolver.remove("A", "B", "C", "D", null);
//    }
//
//
//    @Test
//    public void checkSynchronizedDependencyResolverInheritesAllMethods()
//        throws NoSuchMethodException {
//
//        final RawDependencyResolver<Object> resolver1 =
//            new RawDependencyResolver<Object>();
//
//        final Class<?> class1 = resolver1.getClass();
//
//        final RawDependencyResolver<Object> resolver2 =
//            RawDependencyResolver.sysnchronizedDependencyResolver(resolver1);
//
//        final Class<?> class2 = resolver2.getClass();
//
//        for (Method method1 : class1.getDeclaredMethods()) {
//
//            final int modifiers1 = method1.getModifiers();
//
//            if (Modifier.isStatic(modifiers1)) {
//                continue;
//            }
//
//            if (Modifier.isPrivate(modifiers1)) {
//                continue;
//            }
//
//            final Method method2 = class2.getDeclaredMethod(
//                method1.getName(), method1.getParameterTypes());
//
//            final int modifiers2 = method2.getModifiers();
//
//            Assert.assertTrue(Modifier.isSynchronized(modifiers2));
//        }
//    }
//
//
//    @Test
//    public void testGetVerticalGroups() {
//
//        final RawDependencyResolver<String> resolver =
//            new RawDependencyResolver<String>();
//
//        resolver.add("A", "B");
//        resolver.add("A", "C");
//        resolver.add("B", "C");
//
//        System.out.println("singleGroup: " + resolver.getSingleGroup());
//
//        for (List<String> verticalGroup : resolver.getVerticalGroups()) {
//            System.out.println("verticalGroup: " + verticalGroup);
//        }
//    }


}

