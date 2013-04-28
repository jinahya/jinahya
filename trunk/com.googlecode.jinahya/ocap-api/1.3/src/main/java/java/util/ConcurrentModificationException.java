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
 * This exception may be thrown by methods that have detected concurrent
 * modification of an object when such modification is not permissible.
 * <p>
 * For example, it is not generally permssible for one thread to modify a Collection
 * while another thread is iterating over it.  In general, the results of the
 * iteration are undefined under these circumstances.  Some Iterator
 * implementations (including those of all the collection implementations
 * provided by the JRE) may choose to throw this exception if this behavior is
 * detected.  Iterators that do this are known as <i>fail-fast</i> iterators,
 * as they fail quickly and cleanly, rather that risking arbitrary,
 * non-deterministic behavior at an undetermined time in the future.
 * <p>
 * Note that this exception does not always indicate that an object has
 * been concurrently modified by a <i>different</i> thread.  If a single
 * thread issues a sequence of method invocations that violates the
 * contract of an object, the object may throw this exception.  For
 * example, if a thread modifies a collection directly while it is
 * iterating over the collection with a fail-fast iterator, the iterator
 * will thow this exception.
 *
 * <p>Note that fail-fast behavior cannot be guaranteed as it is, generally
 * speaking, impossible to make any hard guarantees in the presence of
 * unsynchronized concurrent modification.  Fail-fast operations
 * throw <tt>ConcurrentModificationException</tt> on a best-effort basis. 
 * Therefore, it would be wrong to write a program that depended on this
 * exception for its correctness: <i><tt>ConcurrentModificationException</tt>
 * should be used only to detect bugs.</i>
 *
 * @author  Josh Bloch
 * @version 1.11, 02/02/00
 * @see	    Collection
 * @see     Iterator
 * @see     ListIterator
 * @see	    Vector
 * @see	    LinkedList
 * @see	    HashSet
 * @see	    Hashtable
 * @see	    TreeMap
 * @see	    AbstractList
 * @since   1.2
 */
public class ConcurrentModificationException extends RuntimeException
{

    /** 
     * Constructs a ConcurrentModificationException with no
     * detail message.
     */
    public ConcurrentModificationException() { }

    /** 
     * Constructs a <tt>ConcurrentModificationException</tt> with the
     * specified detail message.
     *
     * @param message the detail message pertaining to this exception.
     */
    public ConcurrentModificationException(String message) { }
}
