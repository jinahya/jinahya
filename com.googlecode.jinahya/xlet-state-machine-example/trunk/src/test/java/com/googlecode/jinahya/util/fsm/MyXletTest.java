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


import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletStateChangeException;

import org.testng.annotations.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
@Test(singleThreaded = true)
public class MyXletTest {


    @Test
    public void loadAndDestroy() throws XletStateChangeException {

        System.out.println("loadAndDestroy ----------------------------------");

        new MyXlet().destroyXlet(true);
    }


    @Test
    public void loadInitAndDestroy() throws XletStateChangeException {

        System.out.println("loadInitAndDestroy ------------------------------");

        final Xlet xlet = new MyXlet();
        xlet.initXlet(null);
        xlet.destroyXlet(true);
    }


    @Test
    public void loadInitStartAndDestroy() throws XletStateChangeException {

        System.out.println("loadInitStartAndDestroy -------------------------");

        final Xlet xlet = new MyXlet();
        xlet.initXlet(null);
        xlet.startXlet();
        xlet.destroyXlet(true);
    }


    @Test
    public void loadInitStartPauseAndDestroy() throws XletStateChangeException {

        System.out.println("loadInitStartPauseAndDestroy --------------------");

        final Xlet xlet = new MyXlet();
        xlet.initXlet(null);
        xlet.startXlet();
        xlet.pauseXlet();
        xlet.destroyXlet(true);
    }
}

