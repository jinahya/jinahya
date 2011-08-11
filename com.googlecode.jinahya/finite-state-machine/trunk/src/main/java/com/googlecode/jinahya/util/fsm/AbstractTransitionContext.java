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
abstract class AbstractTransitionContext implements TransitionContext {


    /**
     * Creates a new instance
     * @param transition 
     */
    protected AbstractTransitionContext(final Transition transition) {
        super();

        if (transition == null) {
            throw new NullPointerException("null transition");
        }

        this.transition = transition;
    }


    @Override
    public final Transition getTransition() {
        return transition;
    }


    /** transition. */
    private final Transition transition;
}

