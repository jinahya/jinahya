/*
 * Copyright 2011 onacit.
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


import java.util.EventObject;


/**
 * Event for a Transition.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class TransitionEvent extends EventObject {


    /** GENERATED. */
    private static final long serialVersionUID = 9092651112310564217L;


    /**
     * Creates a new instance.
     *
     * @param transition transition
     */
    public TransitionEvent(final Transition transition) {
        super(transition.getMachine());

        this.transition = transition;
    }


    /**
     * Returns transition.
     *
     * @return transition.
     */
    public final Transition getTransition() {
        return transition;
    }


    @Override
    public String toString() {
        return super.toString() + "/" + transition.toString();
    }


    /** transition. */
    private final Transition transition;
}
