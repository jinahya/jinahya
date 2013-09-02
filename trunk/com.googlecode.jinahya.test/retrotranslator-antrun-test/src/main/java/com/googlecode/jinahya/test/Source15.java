/*
 * Copyright 2013 Jin Kwon <onacit at gmail.com>.
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


package com.googlecode.jinahya.test;


import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class Source15 {


    static {
        final Long autoboxed = ActionEvent.ACTION_EVENT_MASK; // autoboing
        final long unboxed = autoboxed; // unboxing
    }


    // Typesafe enums
    public static enum Target13Enum {


        A;


    }


    public interface Interface13 {


        public Appendable appendable();


    }


    public class Implementation13 implements Interface13 {


        @Override // Annotations
        public StringBuilder appendable() { // Covariant return types

            return null;
        }


    }


    public static void main(final String... args) { // Varargs

        // Enhanced for loop
        for (final String arg : args) {
            System.out.printf("%1$s\n", arg); // Formatted output
        }

        // Generics
        final List<String> list = new ArrayList<String>();

    }


}
