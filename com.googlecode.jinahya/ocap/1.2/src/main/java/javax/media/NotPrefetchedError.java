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



  


package javax.media;

/** 
 * <code>NotPrefetchedError</code> is thrown when a method that
 * requires a <CODE>Controller</CODE> to be in the <I>Prefetched</I> state is called 
 * and the <CODE>Controller</CODE> has not been <i>Prefetched</i>.  
 * <p>
 * This typically happens
 * when <code>syncStart</code> is invoked on a <I>Stopped</I>&nbsp;<code>Controller</code>
 * that hasn't been <I>Prefetched</I>.
 *
 * @see Controller
 * @version 1.12, 97/08/23.
 */
public class NotPrefetchedError extends MediaError
{

    public NotPrefetchedError(String reason) { }
}
