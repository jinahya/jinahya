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
            new DependencyResolver<String>();

        resolver.addDependency("A", "B");
        resolver.addDependency("A", "B");
        resolver.addDependency("A", "B");
        resolver.addDependency("A", "B");
        resolver.print(System.out);
    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddDependencyWithNullSource() {
        final DependencyResolver<String> resolver =
            new DependencyResolver<String>();

        resolver.addDependency(null, "B");
    }


    @Test
    public void testGetFlatten() {
        final DependencyResolver<String> resolver =
            new DependencyResolver<String>();

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

        for (int i = 1; i < 10; i++) {
            System.out.println("---------------------------------------- " + i);
            for (List<String> flatten : resolver.getFlattens(i)) {
                System.out.println(flatten);
            }
        }
    }
}
