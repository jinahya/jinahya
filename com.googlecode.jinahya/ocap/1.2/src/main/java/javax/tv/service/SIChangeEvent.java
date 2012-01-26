/**
<p>This is not an official specification document, and usage is restricted.
</p>
<a name="notice"><strong><center>
NOTICE
</center></strong><br>
<br>

(c) 2005-2008 Sun Microsystems, Inc. All Rights Reserved.
<p>

Neither this file nor any files generated from it describe a complete
specification, and they may only be used as described below.  
<p>
Sun Microsystems Inc. owns the copyright in this file and it is provided
to you for informative use only. For example, 
this file and any files generated from it may be used to generate other documentation, 
such as a unified set of documents of API signatures for a platform 
that includes technologies expressed as Java APIs. 
This file may also be used to produce "compilation stubs," 
which allow applications to be compiled and validated for such platforms. 
By contrast, no permission is given for you to incorporate this file, 
in whole or in part, in an implementation of a Java specification.
<p>
Any work generated from this file, such as unified javadocs or compiled
stub files, must be accompanied by this notice in its entirety.
<p>
This work corresponds to the API signatures of JSR 927: Java TV API 1.1.1.  
In the event of a discrepency between this work and the JSR 927 specification, 
which is available at http://www.jcp.org/en/jsr/detail?id=927, the latter takes precedence.
*/



  


package javax.tv.service;

import java.util.EventObject;

/** 
 * <code>SIChangeEvent</code> objects are sent to
 * <code>SIChangeListener</code> instances to signal detected changes
 * in the SI database.<p>
 *
 * Note that while the SI database may detect changes, notification of
 * which specific <code>SIElement</code> has changed is not guaranteed.
 * The entity reported by the method <code>getSIElement()</code> will
 * be either:
 * <ul>
 * <li>The specific SI element that changed, or<p>
 * <li>An SI element that contains, however indirectly, the specific SI
 * element that changed, or<p>
 * <li><code>null</code>, if the specific changed element is unknown.
 * </ul>
 * 
 * The level of specificity provided by the change mechanism is
 * entirely dependent on the capabilities and current resources of the
 * implementation.
 *
 * <code>SIChangeEvent</code> instances also report the kind of change
 * that occurred to the SI element, via the method
 * <code>getChangeType()</code>:
 * <ul>
 *
 * <li><code>SIChangeType.ADD</code> indicates that
 * the reported SI element is new in the database.<p>
 *
 * <li><code>SIChangeType.REMOVE</code> indicates
 * that the reported SI element is defunct and no longer cached by the
 * database.  The results of subsequent method invocations on the
 * removed SIElement are undefined.<p>
 *
 * <li><code>SIChangeType.MODIFY</code> indicates
 * that the data encapsulated by the reported SI element has changed.
 * 
 * </ul>
 *
 * In the event that the SIElement reported by this event is not
 * the actual element that changed in the broadcast (i.e. it is
 * instead a containing element or <code>null</code>), the
 * <code>SIChangeType</code> will be <code>SIChangeTypeMODIFY</code>.
 * Individual SI element changes are reported only once, i.e.,
 * a change to an SI element is not also reported as a change
 * to any containing (or "parent") SI elements.
 *
 * @see #getSIElement
 * @see #getChangeType
 */
public abstract class SIChangeEvent extends EventObject
{

    /** 
     * Constructs an <code>SIChangeEvent</code> object.
     *
     * @param source The entity in which the change occurred.
     *
     * @param type The type of change that occurred.
     *
     * @param e The <code>SIElement</code> that changed, or
     * <code>null</code> if this is unknown.
     */
    public SIChangeEvent(Object source, SIChangeType type, SIElement e) { 
        super(source);
    }

    /** 
     * Reports the <code>SIElement</code> that changed.<p>
     * 
     * This method may return <code>null</code>, since it is not
     * guaranteed that the SI database can or will determine which
     * element in a particular table changed.
     *
     * @return The <code>SIElement</code> that changed, or
     * <code>null</code> if this is unknown.  
     */
    public SIElement getSIElement() {
        return null;
    }

    /** 
     * Indicates the type of change that occurred.
     *
     * @return The type of change that occurred.
     */
    public SIChangeType getChangeType() {
        return null;
    }
}
