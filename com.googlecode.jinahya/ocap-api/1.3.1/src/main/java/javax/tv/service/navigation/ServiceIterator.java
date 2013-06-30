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



  


package javax.tv.service.navigation;

import java.util.NoSuchElementException;
import javax.tv.service.Service;

/** 
 * <code>ServiceIterator</code> permits iteration over an ordered
 * list of <code>Service</code> objects.  Applications may use the
 * <code>ServiceIterator</code> interface to browse a
 * <code>ServiceList</code> forward or backward.<p>
 *
 * Upon initial usage, <code>hasPrevious()</code> will return
 * <code>false</code> and <code>nextService()</code> will return the
 * first <code>Service</code> in the list, if present.
 *
 * @see ServiceList 
 */
public interface ServiceIterator
{

    /** 
     * Resets the iterator to the beginning of the list, such that
     * <code>hasPrevious()</code> returns <code>false</code> and
     * <code>nextService()</code> returns the first <code>Service</code>
     * in the list (if the list is not empty).
     *
     * 
     */
    public void toBeginning();

    /** 
     * Sets the iterator to the end of the list, such that
     * <code>hasNext()</code> returns <code>false</code> and
     * <code>previousService()</code> returns the last <code>Service</code>
     * in the list (if the list is not empty).
     */
    public void toEnd();

    /** 
     * Reports the next <code>Service</code> object in the list.  This
     * method may be called repeatedly to iterate through the list.
     *
     * @return The <code>Service</code> object at the next position in
     * the list.
     *
     * @throws NoSuchElementException If the iteration has no next
     * <code>Service</code>. 
     */
    public Service nextService();

    /** 
     * Reports the previous <code>Service</code> object in the list.
     * This method may be called repeatedly to iterate through the list
     * in reverse order.
     *
     * @return The <code>Service</code> object at the previous position
     * in the list.
     *
     * @throws NoSuchElementException If the iteration has no previous
     * <code>Service</code>.  
     */
    public Service previousService();

    /** 
     * Tests if there is a <code>Service</code> in the next position in
     * the list.
     * 
     * @return <code>true</code> if there is a <code>Service</code> in
     * the next position in the list; <code>false</code> otherwise.  
     */
    public boolean hasNext();

    /** 
     * Tests if there is a <code>Service</code> in the previous
     * position in the list.
     * 
     * @return <code>true</code> if there is a <code>Service</code> in
     * the previous position in the list; <code>false</code> otherwise.  
     */
    public boolean hasPrevious();
}
