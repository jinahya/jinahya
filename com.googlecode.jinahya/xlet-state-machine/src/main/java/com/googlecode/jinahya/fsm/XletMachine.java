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


package com.googlecode.jinahya.fsm;


import java.util.List;



/**
 * Xlet machine.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class XletMachine extends Machine {


    /**
     * Creates a new instance.
     *
     * @param tasks tasks
     */
    public XletMachine(final List<Task> tasks) {
        super(tasks);
    }


    @Override
    protected boolean isStarting(final Transition transition) {

        if (transition == null) {
            throw new NullPointerException("null transition");
        }

        return transition.matchesAny(
            XletTransitionMatcher.LOAD_XLET, XletTransitionMatcher.INIT_XLET);
    }


    @Override
    protected boolean isAllowed(final Transition transition) {

        if (transition == null) {
            throw new NullPointerException("null transition");
        }

        return transition.matchesAny(XletTransitionMatcher.VALUES);
    }


    @Override
    protected boolean isFinishing(final Transition transition) {

        if (transition == null) {
            throw new NullPointerException("null transition");
        }

        return XletTransitionMatcher.DESTROY_XLET.matches(transition);
    }
}
