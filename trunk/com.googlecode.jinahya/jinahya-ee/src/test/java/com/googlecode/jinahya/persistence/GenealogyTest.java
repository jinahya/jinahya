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


import java.util.ArrayList;
import java.util.Collection;
import org.testng.Assert;

import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class GenealogyTest {


    private static class Entity {


        public Entity getParent() {
            return parent;
        }


        public void setParent(final Entity parent) {

            if (parent == this) {
                throw new IllegalArgumentException("parent == this");
            }

            if (Genealogy.isAncestor(Entity.class, this, parent, "parent")) {
                throw new IllegalArgumentException(
                    "this is an ancestor of parent");
            }

            if (Genealogy.isDescendant(Entity.class, parent, this, "children")) {
                throw new IllegalStateException(
                    "parent is a descendant of this");
            }

            this.parent = parent;
        }


        public Collection<Entity> getChildren() {
            if (children == null) {
                children = new ArrayList<Entity>();
            }
            return children;
        }


        private Entity parent;


        private Collection<Entity> children;


    }


    @Test
    public void test() {

        final Entity of = new Entity();
        final Entity tm = new Entity();
        final Entity is = new Entity();

        try {
            Genealogy.isAncestor(Entity.class, is, null, "parent");
            Assert.fail("passed isAncestor(, null,)");
        } catch (NullPointerException npe) {
            // expected
        }

        Assert.assertFalse(Genealogy.isAncestor(Entity.class, null, of, "parent"));

        Assert.assertFalse(Genealogy.isAncestor(Entity.class, is, of, "parent"));

        of.setParent(tm);
        Assert.assertTrue(Genealogy.isAncestor(tm, of, "parent"));

        tm.setParent(is);
        Assert.assertTrue(Genealogy.isAncestor(Entity.class, is, of, "parent"));


        try {
            tm.setParent(of);
            Assert.fail("cyclic");
        } catch (IllegalArgumentException ise) {
            // expected
        }

        try {
            is.setParent(of);
            Assert.fail("cyclic");
        } catch (IllegalArgumentException ise) {
            // expected
        }


    }


}

