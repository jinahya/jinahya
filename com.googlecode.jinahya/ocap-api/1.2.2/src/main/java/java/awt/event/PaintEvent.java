/*
</p>
<a name="notice"><strong><center>
NOTICE
</center></strong><br>
<br>

(c) 2005-2007 Sun Microsystems, Inc. All Rights Reserved.
<p>
Neither this file nor any files generated from it describe a complete
specification, and they may only be used as described below. For
example, no permission is given for you to incorporate this file, in
whole or in part, in an implementation of a Java specification.
<p>
Sun Microsystems Inc. owns the copyright in this file and it is provided
to you for informative, as opposed to normative, use. The file and any
files generated from it may be used to generate other informative
documentation, such as a unified set of documents of API signatures for
a platform that includes technologies expressed as Java APIs. The file
may also be used to produce "compilation stubs," which allow
applications to be compiled and validated for such platforms.
<p>
Any work generated from this file, such as unified javadocs or compiled
stub files, must be accompanied by this notice in its entirety.
<p>
This work corresponds to the API signatures of JSR 217: Personal Basis
Profile 1.1. In the event of a discrepency between this work and the
JSR 217 specification, which is available at
http://www.jcp.org/en/jsr/detail?id=217, the latter takes precedence.
*/


  


package java.awt.event;

import java.awt.Component;
// import java.awt.Event;
import java.awt.Rectangle;

/** 
 * The component-level paint event.
 * This event is a special type which is used to ensure that
 * paint/update method calls are serialized along with the other
 * events delivered from the event queue.  This event is not
 * designed to be used with the Event Listener model; programs
 * should continue to override paint/update methods in order
 * render themselves properly.
 *
 * @author Amy Fowler
 * @version 1.18, 01/23/03
 * @since 1.1
 */
public class PaintEvent extends ComponentEvent
{
    /** 
     * Marks the first integer id for the range of paint event ids.
     */
    public static final int PAINT_FIRST = 800;

    /** 
     * Marks the last integer id for the range of paint event ids.
     */
    public static final int PAINT_LAST = 801;

    /** 
     * The paint event type.  
     */
    public static final int PAINT = PAINT_FIRST;

    /** 
     * The update event type.  
     */
    public static final int UPDATE = PAINT_FIRST + 1;

    /** 
     * This is the rectangle that represents the area on the source
     * component that requires a repaint.
     * This rectangle should be non null.
     *
     * @serial
     * @see java.awt.Rectangle
     * @see #setUpdateRect(Rectangle)
     * @see #getUpdateRect()
     */
     Rectangle updateRect;

    /*
     * JDK 1.1 serialVersionUID 
     */
    private static final long serialVersionUID = 1267492026433337593L;

    /** 
     * Constructs a <code>PaintEvent</code> object with the specified
     * source component and type.
     * <p>Note that passing in an invalid <code>id</code> results in
     * unspecified behavior.
     *
     * @param source     the object where the event originated
     * @param id         the event type
     * @param updateRect the rectangle area which needs to be repainted
     */
    public PaintEvent(Component source, int id, Rectangle updateRect) { super(null,0); }

    /** 
     * Returns the rectangle representing the area which needs to be
     * repainted in response to this event.
     */
    public Rectangle getUpdateRect() {return null;  }

    /** 
     * Sets the rectangle representing the area which needs to be
     * repainted in response to this event.
     * @param updateRect the rectangle area which needs to be repainted
     */
    public void setUpdateRect(Rectangle updateRect) { }

    public String paramString() { return null; }
}
