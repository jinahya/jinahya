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


  


package java.beans;

import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** 
 * This is a utility class that can be used by beans that support constrained
 * properties.  You can use an instance of this class as a member field
 * of your bean and delegate various work to it.
 *
 * This class is serializable.  When it is serialized it will save
 * (and restore) any listeners that are themselves serializable.  Any
 * non-serializable listeners will be skipped during serialization.
 */
public class VetoableChangeSupport implements Serializable
{
    /** 
     * Hashtable for managing listeners for specific properties.
     * Maps property names to VetoableChangeSupport objects.
     * @serial 
     * @since 1.2
     */
    private java.util.Hashtable children;

    /** 
     * The object to be provided as the "source" for any generated events.
     * @serial
     */
    private Object source;

    /** 
     * Internal version number
     * @serial
     */
    private int vetoableChangeSupportSerializedDataVersion;

    /** 
     * Constructs a <code>VetoableChangeSupport</code> object.
     *
     * @param sourceBean  The bean to be given as the source for any events.
     */
    public VetoableChangeSupport(Object sourceBean) { }

    /** 
     * Add a VetoableListener to the listener list.
     * The listener is registered for all properties.
     *
     * @param listener  The VetoableChangeListener to be added
     */
    public synchronized void addVetoableChangeListener(VetoableChangeListener
        listener)
    { }

    /** 
     * Remove a VetoableChangeListener from the listener list.
     * This removes a VetoableChangeListener that was registered
     * for all properties.
     *
     * @param listener  The VetoableChangeListener to be removed
     */
    public synchronized void removeVetoableChangeListener(VetoableChangeListener
        listener)
    { }

  // PBP/PP
  // [6187238]

     /** 
      * Returns the list of VetoableChangeListeners.
      * 
      * 
      * @return List of VetoableChangeListeners
      * 
      * @since 1.4
      */
     public synchronized VetoableChangeListener[] getVetoableChangeListeners()
     { 
	 return null;
     }

     /** 
      * Add a VetoableChangeListener for a specific property.  The listener
      * will be invoked only when a call on fireVetoableChange names that
      * specific property.
      *
      * @param propertyName  The name of the property to listen on.
      * @param listener  The VetoableChangeListener to be added
      */
    // public synchronized void addVetoableChangeListener(String propertyName,
    //     VetoableChangeListener listener)
    // { }

    // /** 
     // * Remove a VetoableChangeListener for a specific property.
     // *
     // * @param propertyName  The name of the property that was listened on.
     // * @param listener  The VetoableChangeListener to be removed
     // */
    // public synchronized void removeVetoableChangeListener(String propertyName,
        // VetoableChangeListener listener)
    // { }

    // /** 
     // * Returns an array of all the listeners which have been associated 
     // * with the named property.
     // *
     // * @return all the <code>VetoableChangeListeners</code> associated with
     // *         the named property or an empty array if no listeners have 
     // *         been added.
     // */
    // public synchronized VetoableChangeListener[]
        // getVetoableChangeListeners(String propertyName)
    // { }

    /** 
     * Report a vetoable property update to any registered listeners.  If
     * anyone vetos the change, then fire a new event reverting everyone to 
     * the old value and then rethrow the PropertyVetoException.
     * <p>
     * No event is fired if old and new are equal and non-null.
     *
     * @param propertyName  The programmatic name of the property
     *		that is about to change..
     * @param oldValue  The old value of the property.
     * @param newValue  The new value of the property.
     * @exception PropertyVetoException if the recipient wishes the property
     *              change to be rolled back.
     */
    public void fireVetoableChange(String propertyName, Object oldValue, Object
        newValue) throws PropertyVetoException
    { }

    // /** 
     // * Report a int vetoable property update to any registered listeners.
     // * No event is fired if old and new are equal and non-null.
     // * <p>
     // * This is merely a convenience wrapper around the more general
     // * fireVetoableChange method that takes Object values.
     // *
     // * @param propertyName  The programmatic name of the property
     // *		that is about to change.
     // * @param oldValue  The old value of the property.
     // * @param newValue  The new value of the property.
     // */
    // public void fireVetoableChange(String propertyName, int oldValue, int
        // newValue) throws PropertyVetoException
    // { }

    // /** 
     // * Report a boolean vetoable property update to any registered listeners.
     // * No event is fired if old and new are equal and non-null.
     // * <p>
     // * This is merely a convenience wrapper around the more general
     // * fireVetoableChange method that takes Object values.
     // *
     // * @param propertyName  The programmatic name of the property
     // *		that is about to change.
     // * @param oldValue  The old value of the property.
     // * @param newValue  The new value of the property.
     // */
    // public void fireVetoableChange(String propertyName, boolean oldValue,
        // boolean newValue) throws PropertyVetoException
    // { }

    // /** 
     // * Fire a vetoable property update to any registered listeners.  If
     // * anyone vetos the change, then fire a new event reverting everyone to 
     // * the old value and then rethrow the PropertyVetoException.
     // * <p>
     // * No event is fired if old and new are equal and non-null.
     // *
     // * @param evt  The PropertyChangeEvent to be fired.
     // * @exception PropertyVetoException if the recipient wishes the property
     // *              change to be rolled back.
     // */
    // public void fireVetoableChange(PropertyChangeEvent evt)
        // throws PropertyVetoException
    // { }

    // /** 
     // * Check if there are any listeners for a specific property.
     // *
     // * @param propertyName  the property name.
     // * @return true if there are one or more listeners for the given property
     // */
    // public synchronized boolean hasListeners(String propertyName) { }
// 
     private void readObject(ObjectInputStream s)
         throws ClassNotFoundException, IOException
     { }
 
     /** 
      * @serialData Null terminated list of <code>VetoableChangeListeners</code>.
      * <p>
      * At serialization time we skip non-serializable listeners and
      * only serialize the serializable listeners.
      *
      */
     private void writeObject(ObjectOutputStream s) throws IOException { }

    /**
     * Serialization version ID, so we're compatible with JDK 1.1
     */
    static final long serialVersionUID = -5090210921595982017L;
}
