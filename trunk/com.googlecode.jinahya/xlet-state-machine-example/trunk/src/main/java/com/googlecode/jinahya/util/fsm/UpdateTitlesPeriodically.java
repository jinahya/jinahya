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
public class UpdateTitlesPeriodically extends XletActivationSwitchTask
    implements Runnable {


    /**
     * ID.
     */
    public static final String ID = UpdateTitlesPeriodically.class.getSimpleName();


    /**
     * creates a new instance.
     */
    public UpdateTitlesPeriodically() {
        super(ID);
    }


    @Override
    public void prepare(final TransitionContext transition)
        throws FSMException {

        System.out.println("[" + getId() + "] prepare: " + isOn());
    }


    @Override
    public void perform(final TransitionContext context)
        throws FSMException {

        System.out.println("[" + getId() + "] perform: " + isOn());

        if (isOn()) {
        } else {
            if (thread != null) {
                throw new IllegalStateException(
                    "thread is not null? this is an unacceptable state.");
            }

            System.out.println("Starting update thread...");
            thread = new Thread(this);
            thread.start();
            System.out.println("Update thread started.");
        }
    }


    @Override
    protected void performOff(final TransitionContext context)
        throws FSMException {

        System.out.println("[" + getId() + "] performOff");

        // synchronized block is unnessary
        // because state changes are synchronized'
        synchronized (this) {
            if (thread != null) {
                System.out.println("Finishing update thread...");
                thread.interrupt();
                System.out.println("Update thread interrupted.");
                try {
                    thread.join();
                    System.out.println("Update thread joined.");
                } catch (InterruptedException ie) {
                    ie.printStackTrace(System.err);
                }
                thread = null;
            }
        }
    }


    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {

            try {
                MyXletNYTRSSTitles.getInstance().update();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }

            try {
                Thread.sleep(60000L); // 1 minute
            } catch (InterruptedException ie) {
                break;
            }
        }
    }


    private volatile Thread thread = null;
}
