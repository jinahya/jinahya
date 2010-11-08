package com.kt.iptv;


import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class App implements Xlet {


    @Override
    public void initXlet(final XletContext context)
        throws XletStateChangeException {

        // empty
    }


    @Override
    public void startXlet() throws XletStateChangeException {
        
        new Madonna().secret();
    }



    @Override
    public void pauseXlet() {
        // empty
    }


    @Override
    public void destroyXlet(final boolean unconditional)
        throws XletStateChangeException {

        // empty
    }
}
