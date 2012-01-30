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
 * The <code>Stack</code> class represents a last-in-first-out 
 * (LIFO) stack of objects. It extends class <tt>Vector</tt> with five 
 * operations that allow a vector to be treated as a stack. The usual 
 * <tt>push</tt> and <tt>pop</tt> operations are provided, as well as a
 * method to <tt>peek</tt> at the top item on the stack, a method to test 
 * for whether the stack is <tt>empty</tt>, and a method to <tt>search</tt> 
 * the stack for an item and discover how far it is from the top.
 * <p>
 * When a stack is first created, it contains no items. 
 *
 * @author  Jonathan Payne
 * @version 1.24, 02/02/00
 * @since   JDK1.0
 */
public class Stack extends Vector
{

    /** 
     * Creates an empty Stack.
     */
    public Stack() { }

    /** 
     * Pushes an item onto the top of this stack. This has exactly 
     * the same effect as:
     * <blockquote><pre>
     * addElement(item)</pre></blockquote>
     *
     * @param   item   the item to be pushed onto this stack.
     * @return  the <code>item</code> argument.
     * @see     java.util.Vector#addElement
     */
    public Object push(Object item) {
        return null;
    }

    /** 
     * Removes the object at the top of this stack and returns that 
     * object as the value of this function. 
     *
     * @return     The object at the top of this stack (the last item 
     *             of the <tt>Vector</tt> object).
     * @exception  EmptyStackException  if this stack is empty.
     */
    public synchronized Object pop() {
        return null;
    }

    /** 
     * Looks at the object at the top of this stack without removing it 
     * from the stack. 
     *
     * @return     the object at the top of this stack (the last item 
     *             of the <tt>Vector</tt> object). 
     * @exception  EmptyStackException  if this stack is empty.
     */
    public synchronized Object peek() {
        return null;
    }

    /** 
     * Tests if this stack is empty.
     *
     * @return  <code>true</code> if and only if this stack contains 
     *          no items; <code>false</code> otherwise.
     */
    public boolean empty() {
        return false;
    }

    /** 
     * Returns the 1-based position where an object is on this stack. 
     * If the object <tt>o</tt> occurs as an item in this stack, this 
     * method returns the distance from the top of the stack of the 
     * occurrence nearest the top of the stack; the topmost item on the 
     * stack is considered to be at distance <tt>1</tt>. The <tt>equals</tt> 
     * method is used to compare <tt>o</tt> to the 
     * items in this stack.
     *
     * @param   o   the desired object.
     * @return  the 1-based position from the top of the stack where 
     *          the object is located; the return value <code>-1</code>
     *          indicates that the object is not on the stack.
     */
    public synchronized int search(Object o) {
        return 0;
    }

    /** use serialVersionUID from JDK 1.0.2 for interoperability */
    private static final long serialVersionUID = 1224463164541339165L;

}
