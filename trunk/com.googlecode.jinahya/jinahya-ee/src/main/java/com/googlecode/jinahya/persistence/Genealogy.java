/*
 * Copyright 2012 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.persistence;


import java.util.Collection;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
final class Genealogy {


    /**
     * Checks if
     * <code>is</code> is an ancestor of
     * <code>of</code>.
     *
     * @param <T> type parameter
     * @param is the base object
     * @param of the object to be checked
     * @param finder the parent finder
     * @return true if
     * <code>is</code> is an ancestor of
     * <code>of</code>; false otherwise.
     */
    public static <T> boolean isAncestor(final T is, final T of,
                                         final ParentFinder<T> finder) {

        if (of == null) {
            throw new NullPointerException("null of");
        }

        if (is == null) {
            return false;
        }

        if (is.equals(of)) {
            throw new IllegalArgumentException("is == of");
        }

        for (T p = finder.findParent(of); p != null; p = finder.findParent(p)) {
            if (p == is) {
                return true;
            }
        }

        return false;
    }


    public static interface ParentFinder<T> {


        /**
         * Returns the parent of
         * <code>of</code>.
         *
         * @param of current object
         * @return the parent
         */
        T findParent(T of);


    }


    public static <T> boolean isDescendant(final T is, final T of,
                                           final ChildrenFinder<T> finder) {

        if (of == null) {
            throw new NullPointerException("null of");
        }

        if (is == null) {
            return false;
        }

        if (is.equals(of)) {
            throw new IllegalArgumentException("is == of");
        }

        final Collection<T> children = finder.findChildren(of);
        if (children == null) {
            return false;
        }
        for (T child : children) {
            if (isDescendant(is, child, finder)) {
                return true;
            }
        }

        return false;
    }


    public static interface ChildrenFinder<T> {


        /**
         * Returns children of given
         * <code>of</code>.
         *
         * @param of current object
         * @return the children
         */
        Collection<T> findChildren(T of);


    }


    /**
     * PRIVATE.
     */
    private Genealogy() {
        super();
    }


}

