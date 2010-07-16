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
 */

package jinahya.util;


import java.util.List;
import java.util.Vector;

import org.testng.Assert;
//import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class DependencyResolverTest {


    public void testAddDependencies() {

        final DependencyResolver<String> resolver =
            new DependencyResolver<String>();

        resolver.addDependencies("A");
        resolver.addDependencies("A", "B");
        resolver.addDependencies("A", "B", "C");
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddDependenciesWithNullSource() {
        new DependencyResolver<String>().addDependencies(null, new String[0]);
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddDependenciesWithNullTargets() {
        final String[] targets = null;
        new DependencyResolver<String>().addDependencies("A", targets);
    }


    @Test
    public void testAddDependency() {
        new DependencyResolver<String>().addDependency("A", "B");
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddDependencyWithNullSource() {
        new DependencyResolver<String>().addDependency(null, "B");
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddDependencyWithNullTarget() {
        new DependencyResolver<String>().addDependency("A", null);
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddDependencyForSelfDependency() {
        final String source = "A";
        final String target = source;
        new DependencyResolver<String>().addDependency(source, target);
    }


    @Test
    public void testAddSource() {
        new DependencyResolver<String>().addSource("A");
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddSourceWithNull() {
        new DependencyResolver<String>().addSource(null);
    }


    @Test
    public void testRemoveSource() {
        new DependencyResolver<String>().addSource("A");
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testRemoveSourceWithNull() {
        new DependencyResolver<String>().addSource(null);
    }


    @Test
    public void testGetDependencyGroups() {

        final DependencyResolver<String> resolver =
            new DependencyResolver<String>();

        resolver.addDependencies("A", "B", "C");
        resolver.addDependencies("B", "C");


        final Vector<Vector<String>> groups =
            resolver.getDependencyGroups("A", "C");

        for (Vector<String> group : groups) {
            Assert.assertEquals(group.firstElement(), "A");
            Assert.assertEquals(group.lastElement(), "C");
            System.out.println(group);
        }

        Assert.assertEquals(groups.size(), 2);

        Assert.assertEquals(groups.elementAt(1).size(), 2);

        Assert.assertEquals(groups.elementAt(0).size(), 3);
        Assert.assertEquals(groups.elementAt(0).elementAt(1), "B");
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetDependencyGroupsWithNullSource() {
        new DependencyResolver<String>().getDependencyGroups(null, "B");
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetDependencyGroupsWithNullTarget() {
        new DependencyResolver<String>().getDependencyGroups("A", null);
    }


    @Test
    public void testClear() {

        final DependencyResolver<String> resolver =
            new DependencyResolver<String>();

        resolver.clear();
    }
}
