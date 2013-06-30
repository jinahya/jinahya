/*
<p>This is not an official specification document, and usage is restricted.
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
This work corresponds to the API signatures of JSR 219: Foundation
Profile 1.1. In the event of a discrepency between this work and the
JSR 219 specification, which is available at
http://www.jcp.org/en/jsr/detail?id=219, the latter takes precedence.
*/


  


package java.util;

/** 
 * An abstract wrapper class for an EventListener class which associates a set
 * of additional parameters with the listener. Subclasses must provide the
 * storage and accessor methods for the additional arguments or parameters.
 * 
 * Subclasses of EventListerProxy may be returned by getListeners() methods
 * as a way of associating named properties with their listeners.
 *
 * If the calling method is interested in retrieving the named property then it
 * would have to test the element to see if it is a proxy class.
 * 
 * @since 1.4
 */
public abstract class EventListenerProxy implements EventListener
{

    /** 
     * @param listener The listener object.
     */
    public EventListenerProxy(EventListener listener) { }

    /** 
     * @return The listener associated with this proxy.
     */
    public EventListener getListener() {
        return null;
    }
}
