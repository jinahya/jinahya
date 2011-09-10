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


package com.googlecode.jinahya.util.fsm;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class Task {


    /**
     * Creates a new instance.
     *
     * @param id 
     */
    public Task(final String id) {
        super();

        if (id == null) {
            throw new NullPointerException("null id");
        }

        this.id = id;
    }


    /**
     * Returns identifier.
     *
     * @return id
     */
    public final String getId() {
        return id;
    }


    /**
     * Prepares for given <code>context</code>.
     *
     * @param context transition context
     * @throws FSMException if an error occurs.
     */
    public abstract void prepare(TransitionContext context) throws FSMException;


    /**
     * Performs desired actions for given <code>transition</code>.
     *
     * @param context transition context
     * @throws FSMException if an error occurs.
     */
    public abstract void perform(TransitionContext context) throws FSMException;


    /** id. */
    private final String id;
}

