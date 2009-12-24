package jinahya.util.els;


import java.util.EventListener;
import java.util.EventObject;

import static org.junit.Assert.*;
import org.junit.Test;


/**
 * Unit test for EventListenerSupport.
 */
public class EventListenerSupportTest {


    @Test
    public void test() {

        support.add(EventListener.class, listener);
        assertEquals(1, support.getListenerCount());
        assertEquals(1, support.getListenerCount(EventListener.class));
        assertEquals(listener, support.getListeners()[0]);
        assertEquals(listener, support.getListeners(EventListener.class)[0]);

        support.remove(EventListener.class, listener);
        assertEquals(0, support.getListeners().length);

        assertEquals(Boolean.FALSE.booleanValue(),
                     support.removeIfAdded(EventListener.class, listener));
        assertEquals(Boolean.TRUE.booleanValue(),
                     support.addIfNotAdded(EventListener.class, listener));
        assertEquals(Boolean.FALSE.booleanValue(),
                     support.addIfNotAdded(EventListener.class, listener));
        assertEquals(Boolean.TRUE.booleanValue(),
                     support.removeIfAdded(EventListener.class, listener));
    }


    private final EventListenerSupport support = new EventListenerSupport();
    private final EventListener listener = new EventListener() {
        public void eventFired(EventObject e) {
            System.out.println(e);
        }
    };
}
