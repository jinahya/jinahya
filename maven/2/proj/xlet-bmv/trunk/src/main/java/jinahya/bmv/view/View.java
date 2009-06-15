package jinahya.bmv.view;


import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.Container;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import jinahya.bmv.model.Model;


/**
 *
 *
 * @author <a href="mailto:onacit@gmail.com">Jin Kwon</a>
 */
public abstract class View extends Container
    implements KeyListener, PropertyChangeListener {


    public View() {
        super();

        enableEvents(AWTEvent.KEY_EVENT_MASK);
        enableEvents(AWTEvent.COMPONENT_EVENT_MASK);

        tracker = new MediaTracker(this);
    }


    public boolean activated() {
        return activated;
    }


    public synchronized void activate() {
        activated = true;
        Component[] components = getComponents();
        for (int i = 0; i < components.length; i++) {
            if (components[i] instanceof View) {
                ((View) components[i]).activate();
            }
        }

    }


    public synchronized void deactivate() {
        Component[] components = getComponents();
        for (int i = 0; i < components.length; i++) {
            if (components[i] instanceof View) {
                ((View) components[i]).deactivate();
            }
        }
        activated = false;
    }


    /*
    public boolean isActivated() {
        return activated;
    }


    public void setActivated(boolean activated) {
        this.activated = activated;

        Component[] components = getComponents();
        for (int i = 0; i < components.length; i++) {
            if (components[i] instanceof View) {
                View view = (View) components[i];
                view.setActivated(activated);
            }
        }

        if (activated) {
            repaint();
        }
    }
    */


    protected Vector splitText(Graphics g, int width, String string,
                               Vector vector)
        throws IOException {

        BufferedReader reader = new BufferedReader(new StringReader(string));
        try {
            splitText(g, width, reader, vector);
        } finally {
            reader.close();
        }
        return vector;
    }


    protected Vector splitText(Graphics g, int width, BufferedReader reader,
                               Vector vector)
        throws IOException {

        FontMetrics metrics = g.getFontMetrics();

        String line = null;
        while ((line = reader.readLine()) != null) {
            splitText(metrics, width, line.toCharArray(), vector);
        }

        return vector;
    }


    private static void splitText(FontMetrics metrics, int width, char[] line,
                                  Vector vector) {

        int offset = 0;
        while (offset < line.length) {
            int length = splitText(metrics, width, line, offset, 1,
                                   line.length - offset);
            vector.addElement(new String(line, offset, length));
            offset += length;
        }
    }


    private static int splitText(FontMetrics metrics, int width, char[] line,
                                 int offset, int low, int high) {

        if (metrics.charsWidth(line, offset, high) <= width) {
            return high;
        }
        int half = (low + high) / 2;
        if (metrics.charsWidth(line, offset, half) <= width) { // fit
            return splitText(metrics, width, line, offset, half + 1, high);
        } else { // not fit
            return splitText(metrics, width, line, offset, low, half - 1);
        }
    }


    protected void drawString(Graphics g, String string, Rectangle boundary,
                              boolean clip, int halign, int valign) {

        if (string == null) {
            return;
        }
        FontMetrics metrics = g.getFontMetrics();

        int stringWidth = metrics.stringWidth(string);
        int x = boundary.x;
        switch (halign) {
            case 0x01: // LEFT
                break;
            case 0x02: // CENTER
                x += (boundary.width - stringWidth) / 2;
                break;
            case 0x03: // RIGHT
                x += (boundary.width - stringWidth);
                break;
            default:
                break;
        }

        int stringHeight = metrics.getAscent() + metrics.getDescent();
        int y = boundary.y + metrics.getAscent(); // TOP
        switch (valign) {
            case 0x01: // TOP
                break;
            case 0x02: // CENTER
                y += (boundary.height - stringHeight) / 2;
                break;
            case 0x03: // BOTTOM
                y = boundary.y + boundary.height - metrics.getDescent();
                break;
            default:
                break;
        }
        Shape previousClip = g.getClip();
        if (clip) {
            g.setClip(boundary);
        }
        g.drawString(string, x, y);
        if (clip) {
            g.setClip(previousClip);
        }
    }


    // java.awt.event.KeyListener
    public void keyPressed(KeyEvent ke) {
        //empty
    }


    // java.awt.event.KeyListener
    public void keyReleased(KeyEvent ke) {
        // empty
    }


    // java.awt.event.KeyListener
    public void keyTyped(KeyEvent ke) {
        // empty
    }


    // java.beans.PropertyChangeListener
    public void propertyChange(PropertyChangeEvent pce) {
        // empty
    }


    protected Image addImageFromResource(String name) {
        return addImage(getClass().getResource(name));
    }


    protected Image addImage(URL url) {
        return addImage(toolkit.createImage(url));
    }


    protected Image addImage(byte[] image) {
        return addImage(toolkit.createImage(image));
    }


    protected void addImage(byte[] image, int offset, int length) {
        addImage(toolkit.createImage(image, offset, length));
    }


    protected Image addImage(String filename) {
        return addImage(toolkit.createImage(filename));
    }


    protected Image addImage(Image image) {
        synchronized (table) {
            if (!table.containsKey(image)) {
                table.put(image, Boolean.FALSE);
            }
        }
        return image;
    }


    protected Image getImage(Image image) {
        synchronized (table) {
            if (!table.containsKey(image)) { // NOT EVEN ADDED YET
                addImage(image);
            }
            if (!((Boolean) table.get(image)).booleanValue()) { // NOT LOADED
                int id = image.hashCode();
                tracker.addImage(image, id);
                try {
                    tracker.waitForID(id);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
                if (tracker.isErrorID(id)) {
                    System.out.println("IMAGE LOADING ERROR");
                }
                tracker.removeImage(image, id);
                table.put(image, Boolean.TRUE);
            }
            return image;
        }
    }


    protected void removeImage(Image image) {
        synchronized (table) {
            table.remove(image);
            image.flush();
        }
    }


    public void removeAllImages() {
        synchronized (table) {
            Component[] components = getComponents();
            for (int i = 0; i < components.length; i++) {
                if (components[i] instanceof View) {
                    ((View) components[i]).removeAllImages();
                }
            }
            for (Enumeration e = table.keys(); e.hasMoreElements(); ) {
                Image image = (Image) e.nextElement();
                image.flush();
            }
            table.clear();
        }

    }


    public void show(final Component focusOwner, boolean requestFocus) {
        if (isVisible()) {
            repaint();
        } else {
            setVisible(true);
        }
        if (requestFocus) {
            requestFocus();
            addComponentListener(new ComponentAdapter() {
                    public void componentHidden(ComponentEvent ce) {
                        focusOwner.requestFocus();
                        removeComponentListener(this);
                    }
                });
        }
    }


    public Model getModel() {
        return model;
    }


    public void setModel(Model newModel) {
        if (model != null) {
            model.removePropertyChangeListener(this);
        }
        model = newModel;
        if (model != null) {
            model.addPropertyChangeListener(this);
        }
    }


    private MediaTracker tracker;
    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private final Hashtable table = new Hashtable();

    private boolean activated = false;

    private Model model = null;
}