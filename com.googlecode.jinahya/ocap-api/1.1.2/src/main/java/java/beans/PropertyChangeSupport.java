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
 * This is a utility class that can be used by beans that support bound
 * properties.  You can use an instance of this class as a member field
 * of your bean and delegate various work to it.
 *
 * This class is serializable.  When it is serialized it will save
 * (and restore) any listeners that are themselves serializable.  Any
 * non-serializable listeners will be skipped during serialization.
 *
 */
public class PropertyChangeSupport implements Serializable
{
    /** 
     * Hashtable for managing listeners for specific properties.
     * Maps property names to PropertyChangeSupport objects.
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
     * @since
     */
    private int propertyChangeSupportSerializedDataVersion;

    /** 
     * Constructs a <code>PropertyChangeSupport</code> object.
     *
     * @param sourceBean  The bean to be given as the source for any events.
     */
    public PropertyChangeSupport(Object sourceBean) { }

    /** 
     * Add a PropertyChangeListener to the listener list.
     * The listener is registered for all properties.
     *
     * @param listener  The PropertyChangeListener to be added
     */
    public synchronized void addPropertyChangeListener(PropertyChangeListener
        listener)
    { }

    /** 
     * Remove a PropertyChangeListener from the listener list.
     * This removes a PropertyChangeListener that was registered
     * for all properties.
     *
     * @param listener  The PropertyChangeListener to be removed
     */
    public synchronized void removePropertyChangeListener(PropertyChangeListener
        listener)
    { }

  // PBP/PP
  // [6187238]

     /** 
      * Returns an array of all the listeners that were added to the
      * PropertyChangeSupport object with addPropertyChangeListener().
      * 
      * 
      * @return all of the <code>PropertyChangeListeners</code> added or an 
      *         empty array if no listeners have been added
      * @since 1.4
      *
      * 
      */
     public synchronized PropertyChangeListener[] getPropertyChangeListeners()
    { 
	return null;
    }

     /** 
      * Add a PropertyChangeListener for a specific property.  The listener
      * will be invoked only when a call on firePropertyChange names that
      * specific property.
      *
      * @param propertyName  The name of the property to listen on.
      * @param listener  The PropertyChangeListener to be added
      */
    // public synchronized void addPropertyChangeListener(String propertyName,
    //     PropertyChangeListener listener)
    // { }

    // /** 
     // * Remove a PropertyChangeListener for a specific property.
     // *
     // * @param propertyName  The name of the property that was listened on.
     // * @param listener  The PropertyChangeListener to be removed
     // */
    // public synchronized void removePropertyChangeListener(String propertyName,
        // PropertyChangeListener listener)
    // { }

    // /** 
     // * Returns an array of all the listeners which have been associated 
     // * with the named property.
     // *
     // * @return all of the <code>PropertyChangeListeners</code> associated with
     // *         the named property or an empty array if no listeners have 
     // *         been added
     // */
    // public synchronized PropertyChangeListener[]
        // getPropertyChangeListeners(String propertyName)
    // { }

    /** 
     * Report a bound property update to any registered listeners.
     * No event is fired if old and new are equal and non-null.
     *
     * @param propertyName  The programmatic name of the property
     *		that was changed.
     * @param oldValue  The old value of the property.
     * @param newValue  The new value of the property.
     */
    public void firePropertyChange(String propertyName, Object oldValue, Object
        newValue)
    { }

    // /** 
     // * Report an int bound property update to any registered listeners.
     // * No event is fired if old and new are equal and non-null.
     // * <p>
     // * This is merely a convenience wrapper around the more general
     // * firePropertyChange method that takes Object values.
     // *
     // * @param propertyName  The programmatic name of the property
     // *		that was changed.
     // * @param oldValue  The old value of the property.
     // * @param newValue  The new value of the property.
     // */
    // public void firePropertyChange(String propertyName, int oldValue, int
        // newValue)
    // { }

    // /** 
     // * Report a boolean bound property update to any registered listeners.
     // * No event is fired if old and new are equal and non-null.
     // * <p>
     // * This is merely a convenience wrapper around the more general
     // * firePropertyChange method that takes Object values.
     // *
     // * @param propertyName  The programmatic name of the property
     // *		that was changed.
     // * @param oldValue  The old value of the property.
     // * @param newValue  The new value of the property.
     // */
    // public void firePropertyChange(String propertyName, boolean oldValue,
        // boolean newValue)
    // { }

    // /** 
     // * Fire an existing PropertyChangeEvent to any registered listeners.
     // * No event is fired if the given event's old and new values are
     // * equal and non-null.
     // * @param evt  The PropertyChangeEvent object.
     // */
    // public void firePropertyChange(PropertyChangeEvent evt) { }

  //    /** 
//       * Check if there are any listeners for a specific property.
//       *
//       * @param propertyName  the property name.
//       * @return true if there are ore or more listeners for the given property
//       */
//      public synchronized boolean hasListeners(String propertyName) { }
 
     private void readObject(ObjectInputStream s)
         throws ClassNotFoundException, IOException
     { }

     /** 
      * @serialData Null terminated list of <code>PropertyChangeListeners</code>.
      * <p>
      * At serialization time we skip non-serializable listeners and
      * only serialize the serializable listeners.
      *
      */
     private void writeObject(ObjectOutputStream s) throws IOException { }

    /**
     * Serialization version ID, so we're compatible with JDK 1.1
     */
    static final long serialVersionUID = 6401253773779951803L;
}
