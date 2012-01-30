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


  


package java.lang;

import java.io.*;

/** 
 * The <code>Runtime.exec</code> methods create a native process and
 * return an instance of a subclass of <code>Process</code> that can 
 * be used to control the process and obtain information about it. 
 *  The class <code>Process</code> provides methods for performing 
 * input from the process, performing output to the process, waiting 
 * for the process to complete, checking the exit status of the process, 
 * and destroying (killing) the process. 
 * <p>
 * The <code>Runtime.exec</code> methods may not work well for special
 * processes on certain native platforms, such as native windowing
 * processes, daemon processes, Win16/DOS processes on Microsoft Windows, or shell
 * scripts. The created subprocess does not have its own terminal or
 * console. All its standard io (i.e. stdin, stdout, stderr)  operations
 * will be redirected to the parent process through three streams
 * (<code>Process.getOutputStream()</code>,
 * <code>Process.getInputStream()</code>,
 * <code>Process.getErrorStream()</code>).
 * The parent process uses these streams to feed input to and get output
 * from the subprocess. Because some native platforms only provide
 * limited buffer size for standard input and output streams, failure
 * to promptly write the input stream or read the output stream of
 * the subprocess may cause the subprocess to block, and even deadlock.
 * <p>
 * The subprocess is not killed when there are no more references to 
 * the <code>Process</code> object, but rather the subprocess 
 * continues executing asynchronously. 
 * <p>
 * There is no requirement that a process represented by a <code>Process</code> 
 * object execute asynchronously or concurrently with respect to the Java 
 * process that owns the <code>Process</code> object.
 *
 * @author  unascribed
 * @version 1.17, 02/02/00
 * @see     java.lang.Runtime#exec(java.lang.String)
 * @see     java.lang.Runtime#exec(java.lang.String, java.lang.String[])
 * @see     java.lang.Runtime#exec(java.lang.String[])
 * @see     java.lang.Runtime#exec(java.lang.String[], java.lang.String[])
 * @since   JDK1.0
 */
public abstract class Process
{

    public Process() { }

    /** 
     * Gets the output stream of the subprocess.
     * Output to the stream is piped into the standard input stream of 
     * the process represented by this <code>Process</code> object. 
     * <p>
     * Implementation note: It is a good idea for the output stream to 
     * be buffered.
     *
     * @return  the output stream connected to the normal input of the
     *          subprocess.
     */
    public abstract OutputStream getOutputStream();

    /** 
     * Gets the input stream of the subprocess.
     * The stream obtains data piped from the standard output stream 
     * of the process represented by this <code>Process</code> object. 
     * <p>
     * Implementation note: It is a good idea for the input stream to 
     * be buffered.
     *
     * @return  the input stream connected to the normal output of the
     *          subprocess.
     */
    public abstract InputStream getInputStream();

    /** 
     * Gets the error stream of the subprocess.
     * The stream obtains data piped from the error output stream of the 
     * process represented by this <code>Process</code> object. 
     * <p>
     * Implementation note: It is a good idea for the input stream to be 
     * buffered.
     *
     * @return  the input stream connected to the error stream of the
     *          subprocess.
     */
    public abstract InputStream getErrorStream();

    /** 
     * causes the current thread to wait, if necessary, until the 
     * process represented by this <code>Process</code> object has 
     * terminated. This method returns 
     * immediately if the subprocess has already terminated. If the
     * subprocess has not yet terminated, the calling thread will be
     * blocked until the subprocess exits.
     *
     * @return     the exit value of the process. By convention, 
     *             <code>0</code> indicates normal termination.
     * @exception  InterruptedException  if the current thread is 
     *             {@link Thread#interrupt() interrupted} by another thread 
     *             while it is waiting, then the wait is ended and an 
     *             <code>InterruptedException</code> is thrown.
     */
    public abstract int waitFor() throws java.lang.InterruptedException;

    /** 
     * Returns the exit value for the subprocess.
     *
     * @return  the exit value of the subprocess represented by this 
     *          <code>Process</code> object. by convention, the value 
     *          <code>0</code> indicates normal termination.
     * @exception  IllegalThreadStateException  if the subprocess represented 
     *             by this <code>Process</code> object has not yet terminated.
     */
    public abstract int exitValue();

    /** 
     * Kills the subprocess. The subprocess represented by this 
     * <code>Process</code> object is forcibly terminated.
     */
    public abstract void destroy();
}
