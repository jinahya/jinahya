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
This work corresponds to the API signatures of CDC Security
(Java Secure Socket Extension - JSSE) Optional Package interfaces and modules.

*/




/*
 * @(#)SSLSessionBindingListener.java	1.6 05/03/12
 */
  
/*
 * NOTE:
 * Because of various external restrictions (i.e. US export
 * regulations, etc.), the actual source code can not be provided
 * at this time. This file represents the skeleton of the source
 * file, so that javadocs of the API can be created.
 */

package javax.net.ssl;

import java.util.EventListener;

/** 
 * This interface is implemented by objects which want to know when
 * they are being bound or unbound from a SSLSession.  When either event
 * occurs via {@link SSLSession#putValue(String, Object)}
 * or {@link SSLSession#removeValue(String)}, the event is communicated
 * through a SSLSessionBindingEvent identifying the session.
 *
 * @see SSLSession
 * @see SSLSessionBindingEvent
 *
 * @since 1.4
 * @author Nathan Abramson
 * @author David Brownell
 * @version 1.10
 */
public interface SSLSessionBindingListener extends EventListener
{

    /** 
     * This is called to notify the listener that it is being bound into
     * an SSLSession.
     *
     * @param event the event identifying the SSLSession into
     *		which the listener is being bound.
     */
    public void valueBound(SSLSessionBindingEvent event);

    /** 
     * This is called to notify the listener that it is being unbound
     * from a SSLSession.
     *
     * @param event the event identifying the SSLSession from
     *		which the listener is being unbound.
     */
    public void valueUnbound(SSLSessionBindingEvent event);
}
