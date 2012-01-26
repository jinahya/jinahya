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

import javax.tv.locator.Locator;

/** 
 * The base interface of elements provided by the SI database.
 * <code>SIElement</code> objects represent immutable <em>copies</em>
 * of the service information data contained in the SI database.  If
 * the information represented by an <code>SIElement</code> <em>E</em>
 * changes in the database, <em>E</em> will not be changed.  The value
 * of the <code>SIElement</code>'s locator (obtained by the
 * <code>getLocator()</code> method) will remain unchanged in this
 * case; the locator may be used to retrieve a copy of the SI element
 * with the new data.  Two <code>SIElement</code> objects retrieved
 * from the SI database using the same input <code>Locator</code> at
 * different times will report <code>Locator</code> objects that are
 * equal according to <code>Locator.equal()</code>.  However, the
 * <code>SIElement</code> objects themselves will not be
 * <code>equal()</code> if the corresponding data changed in the SI
 * database between the times of their respective retrievals.
 *
 * @see #getLocator
 * @see SIManager#retrieveSIElement
 */
public interface SIElement extends SIRetrievable
{

    /** 
     * Reports the <code>Locator</code> of this <code>SIElement</code>.
     *
     * @return Locator The locator referencing this
     * <code>SIElement</code> 
     */
    public Locator getLocator();

    /** 
     * Tests two <code>SIElement</code> objects for equality.  Returns
     * <code>true</code> if and only if:
     * <ul>
     * <li><code>obj</code>'s class is the
     * same as the class of this <code>SIElement</code>, and<p>
     * <li><code>obj</code>'s <code>Locator</code> is equal to
     * the <code>Locator</code> of this object (as reported by
     * <code>SIElement.getLocator()</code>, and<p>
     * <li><code>obj</code> and this object encapsulate identical data.
     * </ul>
     *
     * @param obj The object against which to test for equality.
     *
     * @return <code>true</code> if the two <code>SIElement</code> objects
     * are equal; <code>false</code> otherwise.
     */
    public boolean equals(Object obj);

    /** 
     * Reports the hash code value of this <code>SIElement</code>.  Two
     * <code>SIElement</code> objects that are equal will have identical
     * hash codes.
     *
     * @return The hash code value of this <code>SIElement</code>.
     */
    public int hashCode();

    /** 
     * Reports the SI format in which this <code>SIElement</code> was
     * delivered.
     * <p>
     * If the SI format of the <code>SIElement</code> is not represented
     * as a constant in the class {@link ServiceInformationType}, the value
     * returned by this method may be outside the defined set of constants.
     * 
     * @return The SI format in which this SI element was delivered.
     */
    public ServiceInformationType getServiceInformationType();
}
