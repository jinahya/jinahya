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


    @Test
    public void testAddDependency() {
        final DependencyResolver<String> resolver =
            new DependencyResolver<String>(String.class);

        resolver.addDependency("A", "B");
        resolver.addDependency("A", "B");
        resolver.addDependency("A", "B");
        resolver.addDependency("A", "B");

        resolver.print(System.out);
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddDependencyWithNullSource() {
        final DependencyResolver<String> resolver =
            new DependencyResolver<String>(String.class);

        resolver.addDependency(null, "B");
    }


    @Test
    public void testGetFlatten() {
        final DependencyResolver<String> resolver =
            new DependencyResolver<String>(String.class);

        resolver.addDependency("A", "B");
        resolver.addDependency("A", "C");

        resolver.addDependency("B", "C");
        resolver.addDependency("B", "D");

        resolver.addDependency("D", "E");
        resolver.addDependency("D", "F");

        resolver.addDependency("H", "I");

        resolver.addDependency("J", "I");

        resolver.addDependency("K", null);
        resolver.addDependency("L", "K");

        resolver.addDependency("M", null);

        resolver.addDependency("N", null);

        resolver.addDependency("O", "P");
        resolver.addDependency("Q", "R");
        resolver.addDependency("S", "T");
        resolver.addDependency("T", "P");
        resolver.addDependency("U", "Q");

        resolver.print(System.out);
        /*
        for (int i = 1; i < 10; i++) {
            System.out.println("---------------------------------------- " + i);
            for (List<String> flatten : resolver.getHorizontalGroups(i)) {
                System.out.println(flatten);
            }
        }
         */
    }


    @Test
    public void removeDependency() {
        final DependencyResolver<String> resolver =
            new DependencyResolver<String>(String.class);

        final String source = "A";
        final String target = "B";

        resolver.addDependency(source, target);

        resolver.removeDependency(source, target);
        resolver.removeDependency(source, target);
    }


    @Test
    public void removeDependencyWithNullTarget() {
        final DependencyResolver<String> resolver =
            new DependencyResolver<String>(String.class);

        final String source = "A";
        final String target = null;

        resolver.addDependency(source, target);

        resolver.removeDependency(source, target);

        resolver.removeDependency(source, target);
    }


    @Test
    public void testDependencyGroups() {
        final DependencyResolver<String> resolver =
            new DependencyResolver<String>(String.class);

        final String source = "A";
        final String target = null;

        resolver.addDependency("A", "B");
        resolver.addDependency("A", "C");
        resolver.addDependency("A", "D");

        resolver.addDependency("B", "D");

        resolver.addDependency("C", "D");
        resolver.addDependency("C", "E");
        resolver.addDependency("C", "I");

        resolver.addDependency("E", "F");
        resolver.addDependency("I", "F");

        for (List<String> group : resolver.getDependencyGroups("A", "F")) {
            System.out.println("\tDEPENDENCY(A->F)" + group);
        }

        for (List<String> group : resolver.getDependencyGroups("A", "D")) {
            System.out.println("\tDEPENDENCY(A->D)" + group);
        }

        for (List<String> group : resolver.getDependencyGroups("A", "E")) {
            System.out.println("\tDEPENDENCY(A->E)" + group);
        }

        for (List<String> group : resolver.getDependencyGroups("A", "F")) {
            System.out.println("\tDEPENDENCY(A->F)" + group);
        }

        for (List<String> group : resolver.getDependencyGroups("A", "G")) {
            System.out.println("\tDEPENDENCY(A->G)" + group);
        }

        for (List<String> group : resolver.getDependencyGroups("I", "F")) {
            System.out.println("\tDEPENDENCY(I->F)" + group);
        }
    }


    @Test
    public void testDependencyGroupsWithNullTarget() {
        final DependencyResolver<String> resolver =
            new DependencyResolver<String>(String.class);

        resolver.addDependency("A", null);

        for (List<String> group : resolver.getDependencyGroups("A", null)) {
            System.out.println("\tDEPENDENCY(A->null)" + group);
        }

        for (List<String> group : resolver.getDependencyGroups("B", null)) {
            System.out.println("\tDEPENDENCY(B->null)" + group);
        }
    }


    @Test
    public void testGetHorizontalGroups() {
        System.out.println("testGetHorizontalGroups -------------------------");

        final DependencyResolver<String> resolver =
            new DependencyResolver<String>(String.class);

        resolver.addDependencies("A", "B", "C");
        resolver.addDependencies("B", "C", "D");
        resolver.addDependency("E", "F");
        resolver.addDependency("G", "F");

        resolver.print(System.out);

        for (Vector<String> group : resolver.getHorizontalGroups(3)) {
            System.out.println("H: " + group);
        }
    }
}
