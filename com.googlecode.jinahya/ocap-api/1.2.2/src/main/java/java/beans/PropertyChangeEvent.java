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

/** 
 * A "PropertyChange" event gets delivered whenever a bean changes a "bound"
 * or "constrained" property.  A PropertyChangeEvent object is sent as an
 * argument to the PropertyChangeListener and VetoableChangeListener methods.
 * <P>
 * Normally PropertyChangeEvents are accompanied by the name and the old
 * and new value of the changed property.  If the new value is a primitive
 * type (such as int or boolean) it must be wrapped as the 
 * corresponding java.lang.* Object type (such as Integer or Boolean).
 * <P>
 * Null values may be provided for the old and the new values if their
 * true values are not known.
 * <P>
 * An event source may send a null object as the name to indicate that an
 * arbitrary set of if its properties have changed.  In this case the
 * old and new values should also be null.
 */
public class PropertyChangeEvent extends java.util.EventObject
{
    /** 
     * name of the property that changed.  May be null, if not known.
     * @serial
     */
    private String propertyName;

    /** 
     * New value for property.  May be null if not known.
     * @serial
     */
    private Object newValue;

    /** 
     * Previous value for property.  May be null if not known.
     * @serial
     */
    private Object oldValue;

    /** 
     * Propagation ID.  May be null.
     * @serial
     */
    private Object propagationId;

    /** 
     * Constructs a new <code>PropertyChangeEvent</code>.
     *
     * @param source  The bean that fired the event.
     * @param propertyName  The programmatic name of the property
     *		that was changed.
     * @param oldValue  The old value of the property.
     * @param newValue  The new value of the property.
     */
    public PropertyChangeEvent(Object source, String propertyName, Object
        oldValue, Object newValue)
    {super(null); }

    /** 
     * Gets the programmatic name of the property that was changed.
     *
     * @return  The programmatic name of the property that was changed.
     *		May be null if multiple properties have changed.
     */
    public String getPropertyName() {return null; }

    /** 
     * Sets the new value for the property, expressed as an Object.
     *
     * @return  The new value for the property, expressed as an Object.
     *		May be null if multiple properties have changed.
     */
    public Object getNewValue() {return null; }

    /** 
     * Gets the old value for the property, expressed as an Object.
     *
     * @return  The old value for the property, expressed as an Object.
     *		May be null if multiple properties have changed.
     */
    public Object getOldValue() { return null;}

    /** 
     * Sets the propagationId object for the event.
     *
     * @param propagationId  The propagationId object for the event.
     */
    public void setPropagationId(Object propagationId) { }

    /** 
     * The "propagationId" field is reserved for future use.  In Beans 1.0
     * the sole requirement is that if a listener catches a PropertyChangeEvent
     * and then fires a PropertyChangeEvent of its own, then it should
     * make sure that it propagates the propagationId field from its
     * incoming event to its outgoing event.
     *
     * @return the propagationId object associated with a bound/constrained
     *		property update.
     */
    public Object getPropagationId() { return null;}
}
