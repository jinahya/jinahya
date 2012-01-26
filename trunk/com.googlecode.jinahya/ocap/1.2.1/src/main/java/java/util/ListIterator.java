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
 * 
 * An iterator for lists that allows the programmer 
 * to traverse the list in either direction, modify 
 * the list during iteration, and obtain the iterator's 
 * current position in the list. A <TT>ListIterator</TT> 
 * has no current element; its <I>cursor position</I> always 
 * lies between the element that would be returned by a call 
 * to <TT>previous()</TT> and the element that would be 
 * returned by a call to <TT>next()</TT>. In a list of 
 * length <TT>n</TT>, there are <TT>n+1</TT> valid 
 * index values, from <TT>0</TT> to <TT>n</TT>, inclusive. 
 * <PRE>
 *
 *          Element(0)   Element(1)   Element(2)   ... Element(n)   
 *        ^            ^            ^            ^               ^
 * Index: 0            1            2            3               n+1
 *
 * </PRE>
 * <P>
 * Note that the {@link #remove} and {@link #set(Object)} methods are
 * <i>not</i> defined in terms of the cursor position;  they are defined to
 * operate on the last element returned by a call to {@link #next} or {@link
 * #previous()}.
 * <P>
 * This interface is a member of the 
 * <a href="{@docRoot}/../guide/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @author  Josh Bloch
 * @version 1.16, 02/02/00
 * @see Collection
 * @see List
 * @see Iterator
 * @see Enumeration
 * @since   1.2
 */
public interface ListIterator extends Iterator
{

    /** 
     * Returns <tt>true</tt> if this list iterator has more elements when
     * traversing the list in the forward direction. (In other words, returns
     * <tt>true</tt> if <tt>next</tt> would return an element rather than
     * throwing an exception.)
     *
     * @return <tt>true</tt> if the list iterator has more elements when
     *		traversing the list in the forward direction.
     */
    public boolean hasNext();

    /** 
     * Returns the next element in the list.  This method may be called
     * repeatedly to iterate through the list, or intermixed with calls to
     * <tt>previous</tt> to go back and forth.  (Note that alternating calls
     * to <tt>next</tt> and <tt>previous</tt> will return the same element
     * repeatedly.)
     *
     * @return the next element in the list.
     * @exception NoSuchElementException if the iteration has no next element.
     */
    public Object next();

    /** 
     * Returns <tt>true</tt> if this list iterator has more elements when
     * traversing the list in the reverse direction.  (In other words, returns
     * <tt>true</tt> if <tt>previous</tt> would return an element rather than
     * throwing an exception.)
     *
     * @return <tt>true</tt> if the list iterator has more elements when
     *	       traversing the list in the reverse direction.
     */
    public boolean hasPrevious();

    /** 
     * Returns the previous element in the list.  This method may be called
     * repeatedly to iterate through the list backwards, or intermixed with
     * calls to <tt>next</tt> to go back and forth.  (Note that alternating
     * calls to <tt>next</tt> and <tt>previous</tt> will return the same
     * element repeatedly.)
     *
     * @return the previous element in the list.
     * 
     * @exception NoSuchElementException if the iteration has no previous
     *            element.
     */
    public Object previous();

    /** 
     * Returns the index of the element that would be returned by a subsequent
     * call to <tt>next</tt>. (Returns list size if the list iterator is at the
     * end of the list.)
     *
     * @return the index of the element that would be returned by a subsequent
     * 	       call to <tt>next</tt>, or list size if list iterator is at end
     *	       of list. 
     */
    public int nextIndex();

    /** 
     * Returns the index of the element that would be returned by a subsequent
     * call to <tt>previous</tt>. (Returns -1 if the list iterator is at the
     * beginning of the list.)
     *
     * @return the index of the element that would be returned by a subsequent
     * 	       call to <tt>previous</tt>, or -1 if list iterator is at
     *	       beginning of list.
     */
    public int previousIndex();

    /** 
     * Removes from the list the last element that was returned by
     * <tt>next</tt> or <tt>previous</tt> (optional operation).  This call can
     * only be made once per call to <tt>next</tt> or <tt>previous</tt>.  It
     * can be made only if <tt>ListIterator.add</tt> has not been called after
     * the last call to <tt>next</tt> or <tt>previous</tt>.
     *
     * @exception UnsupportedOperationException if the <tt>remove</tt>
     * 		  operation is not supported by this list iterator.
     * @exception IllegalStateException neither <tt>next</tt> nor
     *		  <tt>previous</tt> have been called, or <tt>remove</tt> or
     *		  <tt>add</tt> have been called after the last call to *
     *		  <tt>next</tt> or <tt>previous</tt>.
     */
    public void remove();

    /** 
     * Replaces the last element returned by <tt>next</tt> or
     * <tt>previous</tt> with the specified element (optional operation).
     * This call can be made only if neither <tt>ListIterator.remove</tt> nor
     * <tt>ListIterator.add</tt> have been called after the last call to
     * <tt>next</tt> or <tt>previous</tt>.
     *
     * @param o the element with which to replace the last element returned by
     *          <tt>next</tt> or <tt>previous</tt>.
     * @exception UnsupportedOperationException if the <tt>set</tt> operation
     * 		  is not supported by this list iterator.
     * @exception ClassCastException if the class of the specified element
     * 		  prevents it from being added to this list.
     * @exception IllegalArgumentException if some aspect of the specified
     *		  element prevents it from being added to this list.
     * @exception IllegalStateException if neither <tt>next</tt> nor
     *	          <tt>previous</tt> have been called, or <tt>remove</tt> or
     *		  <tt>add</tt> have been called after the last call to
     * 		  <tt>next</tt> or <tt>previous</tt>.
     */
    public void set(Object o);

    /** 
     * Inserts the specified element into the list (optional operation).  The
     * element is inserted immediately before the next element that would be
     * returned by <tt>next</tt>, if any, and after the next element that
     * would be returned by <tt>previous</tt>, if any.  (If the list contains
     * no elements, the new element becomes the sole element on the list.)
     * The new element is inserted before the implicit cursor: a subsequent
     * call to <tt>next</tt> would be unaffected, and a subsequent call to
     * <tt>previous</tt> would return the new element.  (This call increases
     * by one the value that would be returned by a call to <tt>nextIndex</tt>
     * or <tt>previousIndex</tt>.)
     *
     * @param o the element to insert.
     * @exception UnsupportedOperationException if the <tt>add</tt> method is
     * 		  not supported by this list iterator.
     * 
     * @exception ClassCastException if the class of the specified element
     * 		  prevents it from being added to this list.
     * 
     * @exception IllegalArgumentException if some aspect of this element
     *            prevents it from being added to this list.
     */
    public void add(Object o);
}
