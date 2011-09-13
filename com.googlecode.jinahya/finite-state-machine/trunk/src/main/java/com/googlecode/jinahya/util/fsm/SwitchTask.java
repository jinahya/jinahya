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
 * Task for switching conditions.
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class SwitchTask extends Task {


    /**
     * Creates a new instance.
     *
     * @param id task id
     * @param onMatchers matchers for switching on; none null; none empty
     * @param offMatchers matchers for switching off; none null; none empty
     */
    public SwitchTask(final String id, final TransitionMatcher[] onMatchers,
                      final TransitionMatcher[] offMatchers) {

        super(id, onMatchers);

        if (offMatchers == null) {
            throw new NullPointerException("null offMatchers");
        }

        if (offMatchers.length == 0) {
            throw new NullPointerException("empty offMatchers");
        }

        this.offMatchers = offMatchers;
    }


    @Override
    public boolean matches(final Transition transition) {

        final boolean matches;

        if (on) {
            matches = transition.matchesAny(offMatchers);
            if (matches) {
                on = false;
            }
        } else { // off
            matches = super.matches(transition);
            if (matches) {
                on = true;
            }
        }

        return matches;
    }


    /**
     * 
     * @return 
     */
    protected final boolean isOn() {
        return on;
    }


    /** matchers for switching off. */
    private final TransitionMatcher[] offMatchers;


    /** on flag. */
    private volatile boolean on = false;
}
