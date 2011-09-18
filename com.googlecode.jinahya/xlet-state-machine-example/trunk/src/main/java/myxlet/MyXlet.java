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


package myxlet;


import com.googlecode.jinahya.util.fsm.TVXlet;
import com.googlecode.jinahya.util.fsm.TransitionListener;

import java.beans.PropertyChangeListener;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class MyXlet extends TVXlet {


    /**
     * Creates a new instance.
     */
    public MyXlet() {
        super(MyTaskContextFactory.getTaskContext());
    }


    @Override
    public void initXlet(final XletContext xletContext)
        throws XletStateChangeException {

        synchronized (this) {
            tl = new MyTransitionListener();
            getMachine().addTransitionListener(tl);
            pcl = new MyPropertyChangeListener();
            getMachine().addPropertyChangeListener(pcl);
        }

        super.initXlet(xletContext);
    }


    @Override
    public void startXlet() throws XletStateChangeException {

        super.startXlet();
    }


    @Override
    public void pauseXlet() {

        super.pauseXlet();
    }


    @Override
    public void destroyXlet(final boolean unconditional)
        throws XletStateChangeException {

        super.destroyXlet(unconditional);

        synchronized (this) {
            if (tl != null) {
                getMachine().removeTransitionListener(tl);
                tl = null;
            }
            if (pcl != null) {
                getMachine().removePropertyChangeListener(pcl);
                pcl = null;
            }
        }
    }


    /** transition listener. */
    private volatile TransitionListener tl;


    /** transition listener. */
    private volatile PropertyChangeListener pcl;
}
