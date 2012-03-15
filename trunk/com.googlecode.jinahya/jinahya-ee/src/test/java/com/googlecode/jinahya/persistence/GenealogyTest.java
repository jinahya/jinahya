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


import com.googlecode.jinahya.persistence.Genealogy.ChildrenFinder;
import com.googlecode.jinahya.persistence.Genealogy.ParentFinder;
import java.util.ArrayList;
import java.util.Collection;
import junit.framework.Assert;
import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class GenealogyTest {


    private static final ParentFinder<Entity> PARENT_FINDER =
        new ParentFinder<Entity>() {


            @Override
            public Entity findParent(final Entity of) {
                if (of == null) {
                    throw new NullPointerException("null of");
                }
                return of.parent;
            }


        };


    private static final ChildrenFinder<Entity> CHILDREN_FINDER =
        new ChildrenFinder<Entity>() {


            @Override
            public Collection<Entity> findChildren(final Entity of) {
                if (of == null) {
                    throw new NullPointerException("null of");
                }
                return of.children;
            }


        };


    private static class Entity {


        public Entity getParent() {
            return parent;
        }


        public void setParent(final Entity parent) {

            if (parent == this) {
                throw new IllegalStateException("parent == this");
            }

            if (Genealogy.isAncestor(this, parent, PARENT_FINDER)) {
                throw new IllegalStateException(
                    "this is an ancestor of parent");
            }

            if (Genealogy.isDescendant(parent, this, CHILDREN_FINDER)) {
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
            Genealogy.isAncestor(is, null, PARENT_FINDER);
            Assert.fail("passed isAncestor(, null,)");
        } catch (NullPointerException npe) {
            // expected
        }

        Assert.assertFalse(Genealogy.isAncestor(null, of, PARENT_FINDER));

        Assert.assertFalse(Genealogy.isAncestor(is, of, PARENT_FINDER));

        of.setParent(tm);
        Assert.assertTrue(Genealogy.isAncestor(tm, of, PARENT_FINDER));

        tm.setParent(is);
        Assert.assertTrue(Genealogy.isAncestor(is, of, PARENT_FINDER));


        try {
            tm.setParent(of);
            Assert.fail("cyclic");
        } catch (IllegalStateException ise) {
            // expected
        }
        
        try {
            is.setParent(of);
            Assert.fail("cyclic");
        } catch (IllegalStateException ise) {
            // expected
        }


    }


}

