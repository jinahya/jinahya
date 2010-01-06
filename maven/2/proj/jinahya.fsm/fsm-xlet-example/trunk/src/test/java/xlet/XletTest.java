/*
 *  Copyright 2009 Jin Kwon.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or xletied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package xlet;


import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletStateChangeException;

import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class XletTest {

    @Before
    public void create() {
        xlet = createXlet();
    }

    protected abstract Xlet createXlet();


    @Test
    public void test() throws XletStateChangeException {

        if (xlet == null) {
            System.out.println("NULL");
            return;
        }

        long start = System.currentTimeMillis();

        xlet.initXlet(null);

        for (int i = 0; i < 10; i++) {
            xlet.startXlet();
            xlet.pauseXlet();
        }

        xlet.destroyXlet(true);

        System.out.println("ELLAPSED: " + (System.currentTimeMillis() - start));
    }


    private Xlet xlet;
}
