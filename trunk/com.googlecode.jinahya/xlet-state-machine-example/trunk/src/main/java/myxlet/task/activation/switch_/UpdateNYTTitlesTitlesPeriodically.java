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


package myxlet.task.activation.switch_;


import com.googlecode.jinahya.util.fsm.FSMException;
import com.googlecode.jinahya.util.fsm.TransitionContext;

import com.googlecode.jinahya.util.fsm.XletActivationSwitchTask;

import myxlet.model.RSSTitles;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class UpdateNYTTitlesTitlesPeriodically extends XletActivationSwitchTask
    implements Runnable {


    /** ID. */
    public static final String ID =
        UpdateNYTTitlesTitlesPeriodically.class.getSimpleName();


    /**
     * creates a new instance.
     */
    public UpdateNYTTitlesTitlesPeriodically() {
        super(ID);
    }


    @Override
    public void prepareOn(final TransitionContext transition)
        throws FSMException {

        System.out.println("[" + getId() + "] prepareOn");
    }


    @Override
    public void prepareOff(final TransitionContext transition)
        throws FSMException {

        System.out.println("[" + getId() + "] prepareOn");
    }


    @Override
    public void performOn(final TransitionContext context)
        throws FSMException {

        System.out.println("[" + getId() + "] performOn");

        // synchronized block is unnessary
        // because state changes are synchronized'
        synchronized (this) {
            if (thread != null) {
                thread.interrupt();
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

            synchronized (this) {
                if (titles != null) {
                    try {
                        titles.update();
                    } catch (Exception e) {
                        e.printStackTrace(System.err);
                    }
                }
            }

            try {
                Thread.sleep(60000L); // 1 minute
            } catch (InterruptedException ie) {
                break;
            }
        }
    }


    public RSSTitles getTitles() {
        synchronized (this) {
            return this.titles;
        }
    }


    public void setTitles(final RSSTitles titles) {
        synchronized (this) {
            this.titles = titles;
        }
    }


    private volatile Thread thread;


    private volatile RSSTitles titles;
}
