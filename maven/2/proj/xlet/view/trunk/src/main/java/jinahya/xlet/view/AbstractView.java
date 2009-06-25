package jinahya.xlet.view;


import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;


/**
 *
 *
 * @author <a href="mailto:onacit@gmail.com">Jin Kwon</a>
 */
public abstract class AbstractView
    extends Container
    implements PropertyChangeListener {


    public static final void activate(Component component) {
        synchronized (component) {
            if (component instanceof Container) {
                Component[] components =
                    ((Container) component).getComponents();
                for (int i = 0; i < components.length; i++) {
                    activate(components[i]);
                }
            }
            if (component instanceof View) {
                View view = (View) component;
                if (!view.active) {
                    view.activating();
                    view.active = true;
                    view.activated();
                }
            }
        }
    }


    public static final void deactivate(Component component) {
        synchronized (component) {
            if (component instanceof Container) {
                Component[] components =
                    ((Container) component).getComponents();
                for (int i = 0; i < components.length; i++) {
                    deactivate(components[i]);
                }
            }
            if (component instanceof View) {
                View view = (View) component;
                if (view.active) {
                    view.deactivating();
                    view.active = false;
                    view.deactivated();
                }
            }
        }
    }


    public static final void removeAllImages(Component component) {
        synchronized (component) {
            if (component instanceof Container) {
                Component[] components =
                    ((Container) component).getComponents();
                for (int i = 0; i < components.length; i++) {
                    removeAllImages(components[i]);
                }
            }
            if (component instanceof View) {
                View view = (View) component;
                synchronized (view.table) {
                    for (Enumeration e = view.table.elements();
                         e.hasMoreElements(); ) {

                        ((Image) e.nextElement()).flush();
                    }
                    view.table.clear();
                }
            }
        }
    }


    public View() {
        tracker = new MediaTracker(this);
    }


    // java.beans.PropertyChangeListener
    public void propertyChange(PropertyChangeEvent pce) {
        // empty
    }



    protected synchronized final boolean active() {
        return active;
    }


    protected void activating() {
        // empty
    }


    protected void activated() {
        // empty
    }


    protected void deactivating() {
        // empty
    }


    protected void deactivated() {
        // empty
    }


    private boolean active = false;


    protected final Image addImage(InputStream in) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            int available = in.available();
            byte[] buffer = new byte[available > 0 ? available : 1024];
            for (int read = -1; (read = in.read(buffer)) != -1; ) {
                baos.write(buffer, 0, read);
            }
            baos.flush();
            return addImage(baos.toByteArray());
        } finally {
            baos.close();
        }
    }


    protected final Image addImage(String name) {
        return addImage(toolkit.getImage(name));
    }


    protected final Image addImage(URL url) {
        return addImage(toolkit.getImage(url));
    }


    protected final Image addImage(byte[] img) {
        return addImage(toolkit.createImage(img));
    }


    protected final Image addImage(byte[] img, int off, int len) {
        return addImage(toolkit.createImage(img, len, len));
    }


    protected final Image addImage(Image image) {
        synchronized (table) {
            if (!table.containsKey(image)) {
                table.put(image, Boolean.FALSE);
                tracker.addImage(image, image.hashCode());
            }
            return image;
        }
    }


    protected final Image getImage(Image image) {
        synchronized (table) {
            if (!table.containsKey(image)) { // NOT EVEN ADDED YET
                table.put(image, Boolean.FALSE);
            }
            if (((Boolean) table.get(image)).booleanValue()) {
                return image;
            }
            tracker.addImage(image, image.hashCode());
            try {
                tracker.waitForID(image.hashCode(), 5000L); // 5 secs
                if (!tracker.isErrorID(image.hashCode())) {
                    table.put(image, Boolean.TRUE);
                }
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            tracker.removeImage(image, image.hashCode());
            return image;
        }
    }


    protected final void removeImage(Image image) {
        synchronized (table) {
            table.remove(image);
        }
    }



    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private final Hashtable table = new Hashtable();
    private MediaTracker tracker;
}