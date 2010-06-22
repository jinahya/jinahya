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
 *  under the License.
 */

package jinahya.util.deps;


import java.util.List;

import org.testng.Assert;
//import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class DependencyResolverTest {


    @Test
    public void test01() throws DependencyResolverException {

        final DependencyResolver<String> resolver =
            new DependencyResolver<String>(String.class);

        resolver.addDependency("A", "B");
        resolver.addDependency("A", "C");

        resolver.addDependency("C", "D");
        resolver.addDependency("C", "E");
        resolver.addDependency("C", "F");

        resolver.addDependency("E", "F");

        resolver.addDependency("H", "I");

        resolver.addDependency("J", null);

        resolver.addDependency("K", "J");

        resolver.addDependency("B", "J");

        resolver.print(System.out);
    }


    @Test
    public void test02() throws DependencyResolverException {

        final DependencyResolver<String> resolver =
            new DependencyResolver<String>(String.class);

        resolver.addDependency("A", "B");
        resolver.addDependency("A", "C");

        resolver.addDependency("B", "C");
        resolver.addDependency("B", "D");

        resolver.addDependency("C", "D");
        resolver.addDependency("C", "E");

        resolver.addDependency("D", "E");

        resolver.addDependency("F", "A");
        resolver.addDependency("F", "E");

        resolver.addDependency("H", "K");

        resolver.print(System.out);
    }


    @Test
    public void testAdd() throws DependencyResolverException {

        final DependencyResolver<String> resolver =
            new DependencyResolver<String>(String.class);

        resolver.addDependency("A", "B");
        Assert.assertEquals(resolver.hasDependency("A", "B"), true, null);
    }


    @Test
    public void testAddWithNullTarget() throws DependencyResolverException {

        final DependencyResolver<String> resolver =
            new DependencyResolver<String>(String.class);

        resolver.addDependency("A", null);
        Assert.assertEquals(resolver.hasDependency("A", null), true, null);
    }


    @Test(expectedExceptions = DependencyResolverException.class)
    public void testAddForSelfDependency() throws DependencyResolverException {

        final DependencyResolver<String> resolver =
            new DependencyResolver<String>(String.class);

        resolver.addDependency("A", "A");
    }


    @Test(expectedExceptions = DependencyResolverException.class)
    public void testAddForDuplicateDependency()
        throws DependencyResolverException {

        final DependencyResolver<String> resolver =
            new DependencyResolver<String>(String.class);

        resolver.addDependency("A", "A");
        resolver.addDependency("A", "A");
    }


    @Test(expectedExceptions = DependencyResolverException.class)
    public void testAddForCyclicDependency()
        throws DependencyResolverException {

        final DependencyResolver<String> resolver =
            new DependencyResolver<String>(String.class);

        resolver.addDependency("A", "A");
        resolver.addDependency("B", "C");
        resolver.addDependency("C", "A");
    }


    @Test
    public void testRemove() throws DependencyResolverException {

        final DependencyResolver<String> resolver =
            new DependencyResolver<String>(String.class);

        resolver.addDependency("A", "B");
        Assert.assertEquals(resolver.removeDependency("A", "B"), true, null);
        Assert.assertEquals(resolver.removeDependency("A", "B"), false, null);
    }


    @Test
    public void testRemoveWithNullTarget() throws DependencyResolverException {

        final DependencyResolver<String> resolver =
            new DependencyResolver<String>(String.class);

        resolver.addDependency("A", null);
        Assert.assertEquals(resolver.removeDependency("A", null), true, null);
        Assert.assertEquals(resolver.removeDependency("A", null), false, null);
    }
}
