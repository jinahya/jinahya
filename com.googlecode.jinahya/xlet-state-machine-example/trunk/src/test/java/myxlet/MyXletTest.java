/*
 * Copyright 2011 Jin Kwon <jinahya at gmail.com>.
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


import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletStateChangeException;

import org.testng.annotations.Test;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class MyXletTest {


    @Test
    public void testLifeCycles() throws XletStateChangeException {

        {
            final Xlet xlet = new MyXlet();
            xlet.destroyXlet(true);
        }

        {
            final Xlet xlet = new MyXlet();
            xlet.initXlet(null);
            xlet.destroyXlet(true);
        }

        {
            final Xlet xlet = new MyXlet();
            xlet.initXlet(null);
            xlet.startXlet();
            xlet.destroyXlet(true);
        }

        {
            final Xlet xlet = new MyXlet();
            xlet.initXlet(null);
            for (int i = 0; i < 100; i++) {
                xlet.startXlet();
                xlet.pauseXlet();
            }
            xlet.destroyXlet(true);
        }
    }
}
