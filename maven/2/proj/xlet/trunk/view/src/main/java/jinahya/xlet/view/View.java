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
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package jinahya.xlet.view;


import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;


/**
 *
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class View extends Container {

    protected static final int HALIGN_LEFT = 0x01;
    protected static final int HALIGN_CENTER = 0x02;
    protected static final int HALIGN_RIGHT = 0x03;

    protected static final int VALIGN_TOP = 0x01;
    protected static final int VALIGN_CENTER = 0x02;
    protected static final int VALIGN_BOTTOM = 0x03;


    public static final void activate(Component component) {
        activate(component, true);
    }


    public static final void activate(Component component,
                                      boolean includeChildren) {
        synchronized (component) {
            if (component instanceof Container & includeChildren) {
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
        deactivate(component, true);
    }


    public static final void deactivate(Component component,
                                        boolean includeChildren) {
        synchronized (component) {
            if (component instanceof Container & includeChildren) {
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
        removeAllImages(component, true);
    }


    public static final void removeAllImages(Component component,
                                             boolean includeChildren) {

        synchronized (component) {

            deactivate(component, includeChildren); // deactivate if active

            if (component instanceof Container & includeChildren) {
                Component[] components =
                    ((Container) component).getComponents();
                for (int i = 0; i < components.length; i++) {
                    removeAllImages(components[i]);
                }
            }

            if (component instanceof View) {
                View view = (View) component;
                synchronized (view.table) {
                    Enumeration e = view.table.keys();
                    while (e.hasMoreElements()) {
                        ((Image) e.nextElement()).flush();
                    }
                    view.table.clear();
                }
            }
        }
    }


    // <Class, Hashtable<String, Object>>
    private static final Hashtable SHARED_RESOURCE_TABLE = new Hashtable();


    protected static final Object getSharedResource(Class cls, String key) {
        synchronized (SHARED_RESOURCE_TABLE) {
            Hashtable classified = (Hashtable) SHARED_RESOURCE_TABLE.get(cls);
            if (classified == null) {
                return null;
            }
            return classified.get(key);
        }
    }


    protected static final Object putSharedResource(Class cls, String key,
                                                    Object val) {

        if (!cls.isInstance(val)) {
            throw new IllegalArgumentException
                (String.valueOf(val) + " is not an instance of " + cls);
        }
        synchronized (SHARED_RESOURCE_TABLE) {
            Hashtable classified = (Hashtable) SHARED_RESOURCE_TABLE.get(cls);
            if (classified == null) {
                classified = new Hashtable();
                SHARED_RESOURCE_TABLE.put(cls, classified);
            }
            return classified.put(key, val);
        }
    }


    protected static final Font getSharedFont(String name, int style,
                                              int size) {

        String key = (name + "|" + Integer.toString(style) + "|" +
                      Integer.toString(size));
        Font font = (Font) getSharedResource(Font.class, key);
        if (font == null) {
            font = new Font(name, style, size);
            putSharedResource(Font.class, key, font);
        }
        return font;
    }


    protected static final Color getSharedColor(int red, int green, int blue) {
        String key = (Integer.toString(red) + "|" + Integer.toString(green) +
                      "|" + Integer.toString(blue));
        Color color = (Color) getSharedResource(Color.class, key);
        if (color == null) {
            color = new Color(red, green, blue);
            putSharedResource(Color.class, key, color);
        }
        return color;
    }


    public View() {
        super();

        super.setVisible(false);

        tracker = new MediaTracker(this);
    }


    protected synchronized boolean active() {
        return active;
    }


    protected abstract void activating();


    protected abstract void activated();


    protected abstract void deactivating();


    protected abstract void deactivated();


    private boolean active = false;


    protected Image addImage(InputStream in) throws IOException {
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


    protected Image addImage(String name) {
        return addImage(toolkit.getImage(name));
    }


    protected Image addImage(URL url) {
        return addImage(toolkit.getImage(url));
    }


    protected Image addImage(byte[] img) {
        return addImage(toolkit.createImage(img));
    }


    protected Image addImage(byte[] img, int off, int len) {
        return addImage(toolkit.createImage(img, len, len));
    }


    private Image addImage(Image image) {
        synchronized (table) {
            if (!table.containsKey(image)) {
                table.put(image, Boolean.FALSE);
            }
            return image;
        }
    }


    protected Image getImage(Image image) {
        synchronized (table) {

            if (!table.containsKey(image)) {
                return image;
            }

            if (((Boolean) table.get(image)).booleanValue()) {
                return image;
            }

            int id = 1;
            tracker.addImage(image, id);
            try {
                tracker.waitForID(id, 5000L); // 5 secs
                if (!tracker.isErrorID(id)) {
                    table.put(image, Boolean.TRUE);
                }
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            tracker.removeImage(image, id);

            return image;
        }
    }


    protected void removeImage(Image image) {
        synchronized (table) {
            if (table.containsKey(image)) {
                table.remove(image);
                image.flush();
            }
        }
    }


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
            case HALIGN_LEFT: // LEFT
                break;
            case HALIGN_CENTER: // CENTER
                x += (boundary.width - stringWidth) / 2;
                break;
            case HALIGN_RIGHT: // RIGHT
                x += (boundary.width - stringWidth);
                break;
            default:
                break;
        }

        int stringHeight = metrics.getAscent() + metrics.getDescent();
        int y = boundary.y + metrics.getAscent(); // TOP
        switch (valign) {
            case VALIGN_TOP: // TOP
                break;
            case VALIGN_CENTER: // CENTER
                //y += (boundary.height - stringHeight) / 2;
                int gap = stringHeight - boundary.height;
                y = boundary.y + boundary.height + (gap / 2);
                break;
            case VALIGN_BOTTOM: // BOTTOM
                //y = boundary.y + boundary.height - metrics.getDescent();
                y = boundary.y + boundary.height;
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


    protected void drawString(Graphics g, String string, Rectangle boundary,
                              boolean clip, int halign, int valign,
                              String ellipsis) {

        if (string == null) {
            return;
        }
        FontMetrics metrics = g.getFontMetrics();

        if (metrics.stringWidth(string) <= boundary.width) {
            drawString(g, string, boundary, clip, halign, valign);
            return;
        }

        for (int i = 0; i < string.length(); i++) {
            String s = string.substring(0, string.length() - i) + ellipsis;
            if (metrics.stringWidth(s) <= boundary.width) {
                drawString(g, s, boundary, clip, halign, valign);
                return;
            }
        }
        return;
    }


    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private final Hashtable table = new Hashtable();
    private MediaTracker tracker;
}