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


import android.app.Activity;
import android.os.Bundle;
import android.util.AndroidRuntimeException;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class FSMActivity extends Activity {


    /**
     * Creates a new instance with given <code>context</code>.
     *
     * @param context context
     */
    public FSMActivity(final MachineContext context) {
        this(new ActivityMachine(context));
    }


    /**
     * Creates a new instance with given <code>machine</code>. The specified
     * Machine's state must be {@link State#UNKNOWN}.
     *
     * @param machine machine
     */
    protected FSMActivity(final Machine machine) {
        super();

        if (machine == null) {
            throw new NullPointerException("null machine");
        }

        final State state = machine.getState();
        if (!state.equals(State.UNKNOWN)) {
            throw new IllegalArgumentException(
                "illegal machine state: " + state);
        }

        this.machine = machine;
    }


    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        try {
            machine.setState(ActivityState.STOPPED);
        } catch (FSMException fsme) {
            throw new AndroidRuntimeException(fsme);
        }
    }


    @Override
    protected void onStart() {

        super.onStart();

        try {
            machine.setState(ActivityState.PAUSED);
        } catch (FSMException fsme) {
            throw new AndroidRuntimeException(fsme);
        }
    }


    @Override
    protected void onResume() {

        super.onResume();

        try {
            machine.setState(ActivityState.ACTIVE);
        } catch (FSMException fsme) {
            throw new AndroidRuntimeException(fsme);
        }
    }


    @Override
    protected void onPause() {

        super.onPause();

        try {
            machine.setState(ActivityState.PAUSED);
        } catch (FSMException fsme) {
            throw new AndroidRuntimeException(fsme);
        }
    }


    @Override
    protected void onStop() {

        super.onStop();

        try {
            machine.setState(ActivityState.STOPPED);
        } catch (FSMException fsme) {
            throw new AndroidRuntimeException(fsme);
        }
    }


    @Override
    protected void onRestart() {

        super.onRestart();

        try {
            machine.setState(ActivityState.RESTARTING);
        } catch (FSMException fsme) {
            throw new AndroidRuntimeException(fsme);
        }
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();

        try {
            machine.setState(ActivityState.DESTROYED);
        } catch (FSMException fsme) {
            throw new AndroidRuntimeException(fsme);
        }
    }


    /** machine. */
    private final Machine machine;
}

