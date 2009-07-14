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


import java.awt.Component;
import java.awt.Container;
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
public class View extends Container {


    public static synchronized void activate(Component component) {
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


    public static synchronized void deactivate(Component component) {
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


    public static synchronized void removeAllImages(Component component) {
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


    protected synchronized boolean active() {
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


    protected Image addImage(Image image) {
        synchronized (table) {
            if (!table.containsKey(image)) {
                table.put(image, Boolean.FALSE);
                tracker.addImage(image, image.hashCode());
            }
            return image;
        }
    }


    protected Image getImage(Image image) {
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


    protected void removeImage(Image image) {
        synchronized (table) {
            table.remove(image);
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