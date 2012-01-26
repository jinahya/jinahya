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
 * <code>MediaProxy</code> is a <code>MediaHandler</code> which
 * processes content from one <code>DataSource</code>,
 * to produce another <code>DataSource</code>.
 * <p>
 *
 * Typically, a <code>MediaProxy</code> reads a text configuration file
 * that contains all of the information needed to 
 * make a connection to a server and obtain media data.
 * To produce a <code>Player</code> from a <code>MediaLocator</code>
 * referencing the configuration file,
 * <code>Manger</code>:
 * <ul>
 * <li>constructs a <code>DataSource</code>
 * for the protocol described by the <code>MediaLocator</code>
 * <li>constructs a <code>MediaProxy</code> to read
 * the configuration file using the content-type of the
 * <code>DataSource</code>
 * <li> obtains a new <code>DataSource</code>
 * from the <code>MediaProxy</code>
 * <li>constructs the <code>Player</code> using the content-type of the new
 * <code>DataSource</code>
 * </ul>
 * 
 *
 * @see Manager
 *
 * @version 1.10, 97/08/25.
 */
public interface MediaProxy extends MediaHandler
{

    /** 
     * Obtain the new <code>DataSource</code>.
     * The <code>DataSource</code> is already connected.
     *
     * @exception IOException Thrown when if there are IO
     * problems in reading the the original or new
     * <code>DataSource</code>.
     *
     * @exception NoDataSourceException Thrown if this proxy
     * can't produce a <code>DataSource</code>.
     * 
     * @return the new <code>DataSource</code> for this content.
     */
    public DataSource getDataSource() throws IOException, NoDataSourceException;
}
