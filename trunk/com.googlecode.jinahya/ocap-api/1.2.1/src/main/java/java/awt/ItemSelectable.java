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


  


package java.awt;

import java.awt.event.*;

/** 
 * The interface for objects which contain a set of items for
 * which zero or more can be selected.
 *
 * @version 1.15 01/23/03
 * @author Amy Fowler
 */
public interface ItemSelectable
{

    /** 
     * Returns the selected items or <code>null</code> if no
     * items are selected.
     */
    public Object[] getSelectedObjects();

    /** 
     * Adds a listener to receive item events when the state of an item is
     * changed by the user. Item events are not sent when an item's
     * state is set programmatically.  If <code>l</code> is
     * <code>null</code>, no exception is thrown and no action is performed.
     *
     * @param    l the listener to receive events
     * @see ItemEvent
     */
    public void addItemListener(ItemListener l);

    /** 
     * Removes an item listener.
     * If <code>l</code> is <code>null</code>,
     * no exception is thrown and no action is performed.
     *
     * @param 	l the listener being removed
     * @see ItemEvent
     */
    public void removeItemListener(ItemListener l);
}
