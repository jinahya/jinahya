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

import javax.media.protocol.DataSource;
import java.io.IOException;

/** 
 * <code>MediaHandler</code> is the base interface for objects
 * that read and manage media content delivered from a
 * <code>DataSource</code>.
 * <p>
 * 
 * There are currently two supported types of <code>MediaHandler</code>:
 * <code>Player</code> and <code>MediaProxy</code>.
 *
 * @see Player
 * @see MediaProxy
 *
 * @version 1.5, 05/10/17.
 */
public interface MediaHandler
{

    /** 
     * Set the media source the <code>MediaHandler</code>
     * should use to obtain content.
     *
     * @param source The <code>DataSource</code> used
     * by this <code>MediaHandler</code>.
     *
     * @exception IOException Thrown if there is an error
     * using the <code>DataSource</code>
     *
     * @exception IncompatibleSourceException Thrown if
     * this <code>MediaHandler</code> cannot make use
     * of the <code>DataSource</code>.
     */
    public void setSource(DataSource source)
        throws IOException, IncompatibleSourceException;
}
