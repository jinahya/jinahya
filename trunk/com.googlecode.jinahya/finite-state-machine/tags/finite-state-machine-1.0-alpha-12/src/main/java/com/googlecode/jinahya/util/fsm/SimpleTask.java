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
 * Task for simple conditions.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class SimpleTask extends SwitchTask {


    /** A TransitionMatcher doesn't match at all. */
    private static final TransitionMatcher NOT_MATCHES_AT_ALL =
        new TransitionMatcher() {


            @Override
            public boolean matches(final Transition transition) {
                return false;
            }
        };


    /**
     * Creates a new instance.
     *
     * @param id task id
     * @param matchers matchers; not null; not empty
     */
    public SimpleTask(final String id, final TransitionMatcher[] matchers) {

        super(id, matchers, new TransitionMatcher[]{NOT_MATCHES_AT_ALL});
    }


    @Override
    protected final void prepareOff(final TransitionContext context)
        throws FSMException {

        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    protected final void performOff(final TransitionContext context)
        throws FSMException {

        throw new UnsupportedOperationException("Not supported yet.");
    }
}
