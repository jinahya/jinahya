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

import javax.tv.service.*;

/** 
 * <code>SIElementFilter</code> represents a
 * <code>ServiceFilter</code> based on a particular
 * <code>SIElement</code> (such as a <code>TransportStream</code> or
 * <code>ProgramEvent</code>).  A <code>ServiceList</code> resulting
 * from this filter will include only <code>Service</code> objects
 * with one or more corresponding <code>ServiceDetails</code>,
 * <code>sd</code>, such that:
 * <ul>
 * <li> <code>sd</code> is contained by
 * the specified <code>SIElement</code>, or
 * <li><code>sd</code>
 * contains the specified <code>SIElement</code>
 * </ul>
 * -- according to the
 * type of <code>SIElement</code> provided.  Note that no guarantee
 * is made that every <code>SIElement</code> type is supported for
 * filtering.
 *
 * @see SIElement
 * @see ServiceList 
 */
public final class SIElementFilter extends ServiceFilter
{

    /** 
     * Constructs the filter based on a particular <code>SIElement</code>.
     *
     * @param element An <code>SIElement</code> indicating the services
     * to be included in a resulting service list.
     *
     * @throws FilterNotSupportedException If <code>element</code> is
     * not supported for filtering.  
     */
    public SIElementFilter(SIElement element) throws FilterNotSupportedException
    { }

    /** 
     * Reports the <code>SIElement</code> used to create this filter.
     *
     * @return The <code>SIElement</code> used to create this filter.
     */
    public SIElement getFilterValue() {
        return null;
    }

    /** 
     * Tests if the given service passes the filter.
     *
     * @param service An individual <code>Service</code> to be evaluated
     * against the filtering algorithm.
     *
     * @return <code>true</code> if <code>service</code> has a
     * corresponding <code>ServiceDetails</code> which contains or
     * is contained by the <code>SIElement</code> indicated
     * by the filter value; <code>false</code> otherwise.
     */
    public boolean accept(Service service) {
        return false;
    }
}
