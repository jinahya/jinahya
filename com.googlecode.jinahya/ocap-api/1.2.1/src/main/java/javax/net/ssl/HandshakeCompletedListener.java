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
 * @(#)HandshakeCompletedListener.java	1.6 05/03/12
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
 * This interface is implemented by any class which wants to receive
 * notifications about the completion of an SSL protocol handshake
 * on a given SSL connection.
 *
 * <P> When an SSL handshake completes, new security parameters will
 * have been established.  Those parameters always include the security
 * keys used to protect messages.  They may also include parameters
 * associated with a new <em>session</em> such as authenticated
 * peer identity and a new SSL cipher suite.
 *
 * @since 1.4
 * @version 1.7
 * @author David Brownell
 */
public interface HandshakeCompletedListener extends EventListener
{

    /** 
     * This method is invoked on registered objects
     * when a SSL handshake is completed.
     *
     * @param event the event identifying when the SSL Handshake
     *		completed on a given SSL connection
     */
    public void handshakeCompleted(HandshakeCompletedEvent event);
}
