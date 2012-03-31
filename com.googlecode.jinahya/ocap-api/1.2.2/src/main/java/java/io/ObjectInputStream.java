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


  


package java.io;

import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.HashMap;

/** 
 * An ObjectInputStream deserializes primitive data and objects previously
 * written using an ObjectOutputStream.
 * 
 * <p>ObjectOutputStream and ObjectInputStream can provide an application
 * with persistent storage for graphs of objects when used with a
 * FileOutputStream and FileInputStream respectively.
 * ObjectInputStream is used to recover those objects previously
 * serialized. Other uses include passing objects between hosts using
 * a socket stream or for marshaling and unmarshaling arguments and
 * parameters in a remote communication system.<p>
 *
 * ObjectInputStream ensures that the types of all objects in the
 * graph created from the stream match the classes present in the
 * Java Virtual Machine.  Classes are loaded as required using the
 * standard mechanisms. <p>
 *
 * Only objects that support the java.io.Serializable or
 * java.io.Externalizable interface can be read from streams.
 *
 * <p>The method <STRONG>readObject</STRONG> is used to read an object
 * from the stream.  Java's safe casting should be used to get the
 * desired type.  In Java, strings and arrays are objects and are
 * treated as objects during serialization. When read they need to be
 * cast to the expected type.<p>
 *
 * Primitive data types can be read from the stream using the appropriate
 * method on DataInput. <p>
 * 
 * The default deserialization mechanism for objects restores the
 * contents of each field to the value and type it had when it was written.
 * Fields declared as transient or static are ignored by the
 * deserialization process.  References to other objects cause those
 * objects to be read from the stream as necessary.  Graphs of objects
 * are restored correctly using a reference sharing mechanism.  New
 * objects are always allocated when deserializing, which prevents
 * existing objects from being overwritten.
 *
 * <p>Reading an object is analogous to running the constructors of a new
 * object.  Memory is allocated for the object and initialized to zero (NULL).
 * No-arg constructors are invoked for the non-serializable classes and then
 * the fields of the serializable classes are restored from the stream starting
 * with the serializable class closest to java.lang.object and finishing with
 * the object's most specific class.
 *
 * <p>For example to read from a stream as written by the example in
 * ObjectOutputStream: 
 * <br>
 * <pre>
 *	FileInputStream fis = new FileInputStream("t.tmp");
 *	ObjectInputStream ois = new ObjectInputStream(fis);
 *
 *	int i = ois.readInt();
 *	String today = (String) ois.readObject();
 *	Date date = (Date) ois.readObject();
 *
 *	ois.close();
 * </pre>
 *
 * <p>Classes control how they are serialized by implementing either the
 * java.io.Serializable or java.io.Externalizable interfaces.<P>
 *
 * Implementing the Serializable interface allows object serialization
 * to save and restore the entire state of the object and it allows
 * classes to evolve between the time the stream is written and the time it is
 * read.  It automatically traverses references between objects,
 * saving and restoring entire graphs.
 *
 * <p>Serializable classes that require special handling during the
 * serialization and deserialization process should implement the following
 * methods:<p>
 *
 * <PRE>
 * private void writeObject(java.io.ObjectOutputStream stream)
 *     throws IOException;
 * private void readObject(java.io.ObjectInputStream stream)
 *     throws IOException, ClassNotFoundException;
 * private void readObjectNoData() 
 *     throws ObjectStreamException; 
 * </PRE><p>
 *
 * The readObject method is responsible for reading and restoring the
 * state of the object for its particular class using data written to
 * the stream by the corresponding writeObject method.  The method
 * does not need to concern itself with the state belonging to its
 * superclasses or subclasses.  State is restored by reading data from
 * the ObjectInputStream for the individual fields and making
 * assignments to the appropriate fields of the object.  Reading
 * primitive data types is supported by DataInput. 
 * 
 * <p>Any attempt to read object data which exceeds the boundaries of the
 * custom data written by the corresponding writeObject method will cause an
 * OptionalDataException to be thrown with an eof field value of true.
 * Non-object reads which exceed the end of the allotted data will reflect the
 * end of data in the same way that they would indicate the end of the stream:
 * bytewise reads will return -1 as the byte read or number of bytes read, and
 * primitive reads will throw EOFExceptions.  If there is no corresponding
 * writeObject method, then the end of default serialized data marks the end of
 * the allotted data.
 *
 * <p>Primitive and object read calls issued from within a readExternal method
 * behave in the same manner--if the stream is already positioned at the end of
 * data written by the corresponding writeExternal method, object reads will
 * throw OptionalDataExceptions with eof set to true, bytewise reads will
 * return -1, and primitive reads will throw EOFExceptions.  Note that this
 * behavior does not hold for streams written with the old
 * <code>ObjectStreamConstants.PROTOCOL_VERSION_1</code> protocol, in which the
 * end of data written by writeExternal methods is not demarcated, and hence
 * cannot be detected.
 *
 * <p>The readObjectNoData method is responsible for initializing the state of
 * the object for its particular class in the event that the serialization
 * stream does not list the given class as a superclass of the object being
 * deserialized.  This may occur in cases where the receiving party uses a
 * different version of the deserialized instance's class than the sending
 * party, and the receiver's version extends classes that are not extended by
 * the sender's version.  This may also occur if the serialization stream has
 * been tampered; hence, readObjectNoData is useful for initializing
 * deserialized objects properly despite a "hostile" or incomplete source
 * stream.
 *
 * <p>Serialization does not read or assign values to the fields of any
 * object that does not implement the java.io.Serializable interface.
 * Subclasses of Objects that are not serializable can be
 * serializable. In this case the non-serializable class must have a
 * no-arg constructor to allow its fields to be initialized.  In this
 * case it is the responsibility of the subclass to save and restore
 * the state of the non-serializable class. It is frequently the case that
 * the fields of that class are accessible (public, package, or
 * protected) or that there are get and set methods that can be used
 * to restore the state.
 *
 * <p>Any exception that occurs while deserializing an object will be
 * caught by the ObjectInputStream and abort the reading process.
 *
 * <p>Implementing the Externalizable interface allows the object to
 * assume complete control over the contents and format of the object's
 * serialized form.  The methods of the Externalizable interface,
 * writeExternal and readExternal, are called to save and restore the
 * objects state.  When implemented by a class they can write and read
 * their own state using all of the methods of ObjectOutput and
 * ObjectInput.  It is the responsibility of the objects to handle any
 * versioning that occurs.
 *
 * @author	Mike Warres
 * @author	Roger Riggs
 * @version 1.135, 01/09/15
 * @see java.io.DataInput
 * @see java.io.ObjectOutputStream
 * @see java.io.Serializable
 * @see <a href="../../../guide/serialization/spec/input.doc.html"> Object Serialization Specification, Section 3, Object Input Classes</a>
 * @since   JDK1.1
 */
public class ObjectInputStream extends InputStream
    implements ObjectInput, ObjectStreamConstants
{

    /** 
     * Creates an ObjectInputStream that reads from the specified InputStream.
     * A serialization stream header is read from the stream and verified.
     * This constructor will block until the corresponding ObjectOutputStream
     * has written and flushed the header.
     *
     * <p>If a security manager is installed, this constructor will check for
     * the "enableSubclassImplementation" SerializablePermission when invoked
     * directly or indirectly by the constructor of a subclass which overrides
     * the ObjectInputStream.readFields or ObjectInputStream.readUnshared
     * methods.
     *
     * @param	in input stream to read from
     * @throws	StreamCorruptedException if the stream header is incorrect
     * @throws	IOException if an I/O error occurs while reading stream header
     * @throws	SecurityException if untrusted subclass illegally overrides
     * 		security-sensitive methods
     * @throws	NullPointerException if <code>in</code> is <code>null</code>
     * @see	ObjectInputStream#ObjectInputStream()
     * @see	ObjectInputStream#readFields()
     * @see	ObjectOutputStream#ObjectOutputStream(OutputStream).
     */
    public ObjectInputStream(InputStream in) throws IOException { }

    /** 
     * Provide a way for subclasses that are completely reimplementing
     * ObjectInputStream to not have to allocate private data just used by
     * this implementation of ObjectInputStream.
     *
     * <p>If there is a security manager installed, this method first calls the
     * security manager's <code>checkPermission</code> method with the
     * <code>SerializablePermission("enableSubclassImplementation")</code>
     * permission to ensure it's ok to enable subclassing.
     *
     * @exception IOException   Thrown if not called by a subclass.
     * @throws SecurityException
     *    if a security manager exists and its 
     *    <code>checkPermission</code> method denies
     *    enabling subclassing.
     *
     * @see SecurityManager#checkPermission
     * @see java.io.SerializablePermission
     */
    protected ObjectInputStream() throws IOException, SecurityException { }

    /** 
     * Read an object from the ObjectInputStream.
     * The class of the object, the signature of the class, and the values
     * of the non-transient and non-static fields of the class and all
     * of its supertypes are read.  Default deserializing for a class can be
     * overriden using the writeObject and readObject methods.
     * Objects referenced by this object are read transitively so
     * that a complete equivalent graph of objects is reconstructed by 
     * readObject. <p>
     *
     * The root object is completly restored when all of its fields
     * and the objects it references are completely restored.  At this
     * point the object validation callbacks are executed in order
     * based on their registered priorities. The callbacks are
     * registered by objects (in the readObject special methods)
     * as they are individually restored.
     *
     * Exceptions are thrown for problems with the InputStream and for classes
     * that should not be deserialized.  All exceptions are fatal to the 
     * InputStream and leave it in an indeterminate state; it is up to the 
     * caller to ignore or recover the stream state.
     *
     * @exception java.lang.ClassNotFoundException Class of a serialized object
     *      cannot be found.
     * @exception InvalidClassException Something is wrong with a class used by
     *     serialization.
     * @exception StreamCorruptedException Control information in the
     *     stream is inconsistent.
     * @exception OptionalDataException Primitive data was found in the 
     * stream instead of objects.
     * @exception IOException Any of the usual Input/Output related exceptions.
     */
    public final Object readObject()
        throws OptionalDataException, ClassNotFoundException, IOException
    {
        return null;
    }

    /** 
     * This method is called by trusted subclasses of ObjectOutputStream
     * that constructed ObjectOutputStream using the 
     * protected no-arg constructor. The subclass is expected to provide
     * an override method with the modifier "final".
     *
     * @return the Object read from the stream.
     * @exception java.lang.ClassNotFoundException Class definition of a
     * serialized object cannot be found.
     * @exception OptionalDataException Primitive data was found in the 
     * stream instead of objects.
     * @exception IOException if I/O errors occurred while reading from the
     * underlying stream
     *
     * @see #ObjectInputStream()
     * @see #readObject()
     * @since 1.2
     */
    protected Object readObjectOverride()
        throws OptionalDataException, ClassNotFoundException, IOException
    {
        return null;
    }

    /** 
     * Reads an "unshared" object from the ObjectInputStream.  This method is
     * identical to readObject, except that it prevents subsequent calls to
     * readObject and readUnshared from returning additional references to the
     * deserialized instance obtained via this call.  Specifically:
     * <ul>
     *   <li>If readUnshared is called to deserialize a back-reference (the
     *       stream representation of an object which has been written
     *       previously to the stream), an ObjectStreamException will be
     *       thrown.
     *
     *   <li>If readUnshared returns successfully, then any subsequent attempts
     *       to deserialize back-references to the stream handle deserialized
     *       by readUnshared will cause an ObjectStreamException to be thrown.
     * </ul>
     * Deserializing an object via readUnshared invalidates the stream handle
     * associated with the returned object.  Note that this in itself does not
     * always guarantee that the reference returned by readUnshared is unique;
     * the deserialized object may define a readResolve method which returns an
     * object visible to other parties, or readUnshared may return a Class
     * object obtainable elsewhere in the stream or through external means.
     *
     * <p>However, for objects which are not instances of java.lang.Class and
     * do not define readResolve methods, readUnshared guarantees that the
     * returned object reference is unique and cannot be obtained a second time
     * from the ObjectInputStream that created it, even if the underlying data
     * stream has been manipulated.  This guarantee applies only to the
     * base-level object returned by readUnshared, and not to any transitively
     * referenced sub-objects in the returned object graph.
     *
     * <p>ObjectInputStream subclasses which override this method can only be
     * constructed in security contexts possessing the
     * "enableSubclassImplementation" SerializablePermission; any attempt to
     * instantiate such a subclass without this permission will cause a
     * SecurityException to be thrown.
     *
     * @return  reference to deserialized object
     * @throws  ClassNotFoundException if class of an object to deserialize
     *          cannot be found
     * @throws  StreamCorruptedException if control information in the stream
     *          is inconsistent
     * @throws  ObjectStreamException if object to deserialize has already
     *          appeared in stream
     * @throws  OptionalDataException if primitive data is next in stream
     * @throws  IOException if an I/O error occurs during deserialization
     *
     */
    public Object readUnshared() throws IOException, ClassNotFoundException {
        return null;
    }

    /** 
     * Read the non-static and non-transient fields of the current class from
     * this stream.  This may only be called from the readObject method of the
     * class being deserialized. It will throw the NotActiveException if it is
     * called otherwise.
     *
     * @throws	ClassNotFoundException if the class of a serialized object
     * 		could not be found.
     * @throws	IOException if an I/O error occurs.
     * @throws	NotActiveException if the stream is not currently reading
     * 		objects.
     */
    public void defaultReadObject() throws IOException, ClassNotFoundException
    { }

    /** 
     * Reads the persistent fields from the stream and makes them 
     * available by name.
     * 
     * @return the <code>GetField</code> object representing the persistent
     * fields of the object being deserialized
     * @exception java.lang.ClassNotFoundException if the class of a serialized
     *              object could not be found.
     * @exception IOException        if an I/O error occurs.
     * @exception NotActiveException if the stream is not currently reading
     *              objects.
     * @since 1.2
     */
    public java.io.ObjectInputStream.GetField readFields()
        throws IOException, ClassNotFoundException, NotActiveException
    {
        return null;
    }

    /** 
     * Register an object to be validated before the graph is returned.  While
     * similar to resolveObject these validations are called after the entire
     * graph has been reconstituted.  Typically, a readObject method will
     * register the object with the stream so that when all of the objects are
     * restored a final set of validations can be performed.
     *
     * @param	obj the object to receive the validation callback.
     * @param	prio controls the order of callbacks;zero is a good default.
     * 		Use higher numbers to be called back earlier, lower numbers for
     * 		later callbacks. Within a priority, callbacks are processed in
     * 		no particular order.
     * @throws	NotActiveException The stream is not currently reading objects
     * 		so it is invalid to register a callback.
     * @throws	InvalidObjectException The validation object is null.
     */
    public void registerValidation(ObjectInputValidation obj, int prio)
        throws NotActiveException, InvalidObjectException
    { }

    /** 
     * Load the local class equivalent of the specified stream class
     * description.  Subclasses may implement this method to allow classes to
     * be fetched from an alternate source. 
     *
     * <p>The corresponding method in <code>ObjectOutputStream</code> is
     * <code>annotateClass</code>.  This method will be invoked only once for
     * each unique class in the stream.  This method can be implemented by
     * subclasses to use an alternate loading mechanism but must return a
     * <code>Class</code> object.  Once returned, the serialVersionUID of the
     * class is compared to the serialVersionUID of the serialized class.  If
     * there is a mismatch, the deserialization fails and an exception is
     * raised.
     *
     * <p>By default the class name is resolved relative to the class that
     * called <code>readObject</code>.
     *
     * @param	desc an instance of class <code>ObjectStreamClass</code>
     * @return	a <code>Class</code> object corresponding to <code>desc</code>
     * @throws	IOException any of the usual input/output exceptions
     * @throws	ClassNotFoundException if class of a serialized object cannot
     * 		be found
     */
    protected Class resolveClass(ObjectStreamClass desc)
        throws IOException, ClassNotFoundException
    {
        return null;
    }

    /** 
     * Returns a proxy class that implements the interfaces named in a
     * proxy class descriptor; subclasses may implement this method to
     * read custom data from the stream along with the descriptors for
     * dynamic proxy classes, allowing them to use an alternate loading
     * mechanism for the interfaces and the proxy class.
     *
     * <p>This method is called exactly once for each unique proxy class
     * descriptor in the stream.
     *
     * <p>The corresponding method in <code>ObjectOutputStream</code> is
     * <code>annotateProxyClass</code>.  For a given subclass of
     * <code>ObjectInputStream</code> that overrides this method, the
     * <code>annotateProxyClass</code> method in the corresponding
     * subclass of <code>ObjectOutputStream</code> must write any data or
     * objects read by this method.
     *
     * <p>The default implementation of this method in
     * <code>ObjectInputStream</code> returns the result of calling
     * <code>Proxy.getProxyClass</code> with the list of
     * <code>Class</code> objects for the interfaces that are named in
     * the <code>interfaces</code> parameter.  The <code>Class</code>
     * object for each interface name <code>i</code> is the value
     * returned by calling
     * <pre>
     *     Class.forName(i, false, loader)
     * </pre>
     * where <code>loader</code> is that of the first non-null class
     * loader up the execution stack, or <code>null</code> if no non-null
     * class loaders are on the stack (the same class loader choice used
     * by the <code>resolveClass</code> method).  This same value of
     * <code>loader</code> is also the class loader passed to
     * <code>Proxy.getProxyClass</code>.  If <code>Proxy.getProxyClass</code>
     * throws an <code>IllegalArgumentException</code>,
     * <code>resolveProxyClass</code> will throw a
     * <code>ClassNotFoundException</code> containing the
     * <code>IllegalArgumentException</code>.
     *
     * @param	interfaces the list of interface names that were
     *		deserialized in the proxy class descriptor
     * @return  a proxy class for the specified interfaces
     * @throws	IOException any exception thrown by the underlying
     *		<code>InputStream</code>
     * @throws	ClassNotFoundException if the proxy class or any of the
     * 		named interfaces could not be found
     * @see ObjectOutputStream#annotateProxyClass(Class)
     * @since	1.3
     */
    protected Class resolveProxyClass(String[] interfaces)
        throws IOException, ClassNotFoundException
    {
        return null;
    }

    /** 
     * This method will allow trusted subclasses of ObjectInputStream to
     * substitute one object for another during deserialization. Replacing
     * objects is disabled until enableResolveObject is called. The
     * enableResolveObject method checks that the stream requesting to resolve
     * object can be trusted. Every reference to serializable objects is passed
     * to resolveObject.  To insure that the private state of objects is not
     * unintentionally exposed only trusted streams may use resolveObject.
     *
     * <p>This method is called after an object has been read but before it is
     * returned from readObject.  The default resolveObject method just returns
     * the same object.
     *
     * <p>When a subclass is replacing objects it must insure that the
     * substituted object is compatible with every field where the reference
     * will be stored.  Objects whose type is not a subclass of the type of the
     * field or array element abort the serialization by raising an exception
     * and the object is not be stored.
     *
     * <p>This method is called only once when each object is first
     * encountered.  All subsequent references to the object will be redirected
     * to the new object.
     *
     * @param	obj object to be substituted
     * @return	the substituted object
     * @throws	IOException Any of the usual Input/Output exceptions.
     */
    protected Object resolveObject(Object obj) throws IOException {
        return null;
    }

    /** 
     * Enable the stream to allow objects read from the stream to be replaced.
     * 
     * When enabled, the resolveObject method is called for every object
     * being deserialized.
     *
     * If <i>enable</i> is true, and there is a security manager installed, 
     * this method first calls the
     * security manager's <code>checkPermission</code> method with the
     * <code>SerializablePermission("enableSubstitution")</code>
     * permission to ensure it's ok to 
     * enable the stream to allow objects read from the stream to be replaced.
     * 
     * @param enable true for enabling use of <code>resolveObject</code> for
     *               every object being deserialized
     * @return the previous setting before this method was invoked
     * @throws SecurityException
     *    if a security manager exists and its 
     *    <code>checkPermission</code> method denies
     *    enabling the stream to allow objects read from the stream to be
     *    replaced.
     *
     * @see SecurityManager#checkPermission
     * @see java.io.SerializablePermission
     */
    protected boolean enableResolveObject(boolean enable)
        throws SecurityException
    {
        return false;
    }

    /** 
     * The readStreamHeader method is provided to allow subclasses to
     * read and verify their own stream headers. It reads and
     * verifies the magic number and version number.
     *
     * @throws IOException if there are I/O errors while reading from the
     * underlying <code>InputStream</code> 
     * @throws StreamCorruptedException if control information in the
     * stream is inconsistent
     */
    protected void readStreamHeader()
        throws IOException, StreamCorruptedException
    { }

    /** 
     * Read a class descriptor from the serialization stream.  This method is
     * called when the ObjectInputStream expects a class descriptor as the next
     * item in the serialization stream.  Subclasses of ObjectInputStream may
     * override this method to read in class descriptors that have been written
     * in non-standard formats (by subclasses of ObjectOutputStream which have
     * overridden the <code>writeClassDescriptor</code> method).  By default,
     * this method reads class descriptors according to the format defined in
     * the Object Serialization specification.
     * <p>
     *
     * @return the class descriptor read
     * @exception IOException If an I/O error has occurred.
     * @exception ClassNotFoundException If the Class of a serialized object
     *            used in the class descriptor representation cannot be found
     * @see
     * java.io.ObjectOutputStream#writeClassDescriptor(java.io.ObjectStreamClass)
     * @since 1.3
     */
    protected ObjectStreamClass readClassDescriptor()
        throws IOException, ClassNotFoundException
    {
        return null;
    }

    /** 
     * Reads a byte of data. This method will block if no input is 
     * available.
     *
     * @return 	the byte read, or -1 if the end of the
     *		stream is reached.
     * @exception IOException If an I/O error has occurred.
     */
    public int read() throws IOException {
        return 0;
    }

    /** 
     * Reads into an array of bytes.  This method will
     * block until some input is available. Consider
     * using java.io.DataInputStream.readFully to read exactly
     * 'length' bytes.
     *
     * @param b	the buffer into which the data is read
     * @param off the start offset of the data
     * @param len the maximum number of bytes read
     * @return  the actual number of bytes read, -1 is
     * 		returned when the end of the stream is reached.
     * @exception IOException If an I/O error has occurred.
     * @see java.io.DataInputStream#readFully(byte[],int,int)
     */
    public int read(byte[] buf, int off, int len) throws IOException {
        return 0;
    }

    /** 
     * Returns the number of bytes that can be read without blocking.
     *
     * @return the number of available bytes.
     * @throws IOException if there are I/O errors while reading from the
     * underlying <code>InputStream</code>
     */
    public int available() throws IOException {
        return 0;
    }

    /** 
     * Closes the input stream. Must be called to release any resources
     * associated with the stream.
     *
     * @throws	IOException If an I/O error has occurred.
     */
    public void close() throws IOException { }

    /** 
     * Reads in a boolean.
     * 
     * @return	the boolean read.
     * @throws	EOFException If end of file is reached.
     * @throws	IOException If other I/O error has occurred.
     */
    public boolean readBoolean() throws IOException {
        return false;
    }

    /** 
     * Reads an 8 bit byte.
     * 
     * @return	the 8 bit byte read.
     * @throws	EOFException If end of file is reached.
     * @throws	IOException If other I/O error has occurred.
     */
    public byte readByte() throws IOException {
        return ' ';
    }

    /** 
     * Reads an unsigned 8 bit byte.
     *
     * @return	the 8 bit byte read.
     * @throws	EOFException If end of file is reached.
     * @throws	IOException If other I/O error has occurred.
     */
    public int readUnsignedByte() throws IOException {
        return 0;
    }

    /** 
     * Reads a 16 bit char.
     *
     * @return	the 16 bit char read. 
     * @throws	EOFException If end of file is reached.
     * @throws	IOException If other I/O error has occurred.
     */
    public char readChar() throws IOException {
        return ' ';
    }

    /** 
     * Reads a 16 bit short.
     *
     * @return	the 16 bit short read.
     * @throws	EOFException If end of file is reached.
     * @throws	IOException If other I/O error has occurred.
     */
    public short readShort() throws IOException {
        return -1;
    }

    /** 
     * Reads an unsigned 16 bit short.
     *
     * @return	the 16 bit short read.
     * @throws	EOFException If end of file is reached.
     * @throws	IOException If other I/O error has occurred.
     */
    public int readUnsignedShort() throws IOException {
        return 0;
    }

    /** 
     * Reads a 32 bit int.
     *
     * @return	the 32 bit integer read.
     * @throws	EOFException If end of file is reached.
     * @throws	IOException If other I/O error has occurred.
     */
    public int readInt() throws IOException {
        return 0;
    }

    /** 
     * Reads a 64 bit long.
     *
     * @return	the read 64 bit long.
     * @throws	EOFException If end of file is reached.
     * @throws	IOException If other I/O error has occurred.
     */
    public long readLong() throws IOException {
        return -1;
    }

    /** 
     * Reads a 32 bit float.
     *
     * @return	the 32 bit float read.
     * @throws	EOFException If end of file is reached.
     * @throws	IOException If other I/O error has occurred.
     */
    public float readFloat() throws IOException {
        return 0.0f;
    }

    /** 
     * Reads a 64 bit double.
     *
     * @return	the 64 bit double read.
     * @throws	EOFException If end of file is reached.
     * @throws	IOException If other I/O error has occurred.
     */
    public double readDouble() throws IOException {
        return 0.0d;
    }

    /** 
     * Reads bytes, blocking until all bytes are read.
     *
     * @param	buf the buffer into which the data is read
     * @throws	EOFException If end of file is reached.
     * @throws	IOException If other I/O error has occurred.
     */
    public void readFully(byte[] buf) throws IOException { }

    /** 
     * Reads bytes, blocking until all bytes are read.
     *
     * @param	buf the buffer into which the data is read
     * @param	off the start offset of the data
     * @param	len the maximum number of bytes to read
     * @throws	EOFException If end of file is reached.
     * @throws	IOException If other I/O error has occurred.
     */
    public void readFully(byte[] buf, int off, int len) throws IOException { }

    /** 
     * Skips bytes, block until all bytes are skipped.
     *
     * @param	len the number of bytes to be skipped
     * @return	the actual number of bytes skipped.
     * @throws	EOFException If end of file is reached.
     * @throws	IOException If other I/O error has occurred.
     */
    public int skipBytes(int len) throws IOException {
        return 0;
    }

    /** 
     * Reads in a line that has been terminated by a \n, \r, \r\n or EOF.
     *
     * @return	a String copy of the line.
     * @throws	IOException if there are I/O errors while reading from the
     * 		underlying <code>InputStream</code>
     * @deprecated This method does not properly convert bytes to characters.
     * 		see DataInputStream for the details and alternatives.
     */
    public String readLine() throws IOException {
        return null;
    }

    /** 
     * Reads a UTF format String.
     *
     * @return	the String.
     * @throws	IOException if there are I/O errors while reading from the
     * 		underlying <code>InputStream</code>
     * @throws	UTFDataFormatException if read bytes do not represent a valid
     * 		UTF-8 encoding of a string
     */
    public String readUTF() throws IOException {
        return null;
    }

    /** 
     * Provide access to the persistent fields read from the input stream.
     */
    public abstract static class GetField
    {

        public GetField() { }

        /** 
         * Get the ObjectStreamClass that describes the fields in the stream.
         *
         * @return  the descriptor class that describes the serializable fields
         */
        public abstract ObjectStreamClass getObjectStreamClass();

        /** 
         * Return true if the named field is defaulted and has no value in this
         * stream.
         *
         * @param  name the name of the field
         * @return true, if and only if the named field is defaulted
         * @throws IOException if there are I/O errors while reading from
         * 	   the underlying <code>InputStream</code>
         * @throws IllegalArgumentException if <code>name</code> does not
         * 	   correspond to a serializable field
         */
        public abstract boolean defaulted(String name)
            throws IOException, IllegalArgumentException;

        /** 
         * Get the value of the named boolean field from the persistent field.
         *
         * @param  name the name of the field
         * @param  val the default value to use if <code>name</code> does not
         * 	   have a value
         * @return the value of the named <code>boolean</code> field
         * @throws IOException if there are I/O errors while reading from the
         * 	   underlying <code>InputStream</code>
         * @throws IllegalArgumentException if type of <code>name</code> is
         * 	   not serializable or if the field type is incorrect
         */
        public abstract boolean get(String name, boolean val)
            throws IOException, IllegalArgumentException;

        /** 
         * Get the value of the named byte field from the persistent field.
         *
         * @param  name the name of the field
         * @param  val the default value to use if <code>name</code> does not
         * 	   have a value
         * @return the value of the named <code>byte</code> field
         * @throws IOException if there are I/O errors while reading from the
         * 	   underlying <code>InputStream</code>
         * @throws IllegalArgumentException if type of <code>name</code> is
         * 	   not serializable or if the field type is incorrect
         */
        public abstract byte get(String name, byte val)
            throws IOException, IllegalArgumentException;

        /** 
         * Get the value of the named char field from the persistent field.
         *
         * @param  name the name of the field
         * @param  val the default value to use if <code>name</code> does not
         * 	   have a value
         * @return the value of the named <code>char</code> field
         * @throws IOException if there are I/O errors while reading from the
         * 	   underlying <code>InputStream</code>
         * @throws IllegalArgumentException if type of <code>name</code> is
         * 	   not serializable or if the field type is incorrect
         */
        public abstract char get(String name, char val)
            throws IOException, IllegalArgumentException;

        /** 
         * Get the value of the named short field from the persistent field.
         *
         * @param  name the name of the field
         * @param  val the default value to use if <code>name</code> does not
         * 	   have a value
         * @return the value of the named <code>short</code> field
         * @throws IOException if there are I/O errors while reading from the
         * 	   underlying <code>InputStream</code>
         * @throws IllegalArgumentException if type of <code>name</code> is
         * 	   not serializable or if the field type is incorrect
         */
        public abstract short get(String name, short val)
            throws IOException, IllegalArgumentException;

        /** 
         * Get the value of the named int field from the persistent field.
         *
         * @param  name the name of the field
         * @param  val the default value to use if <code>name</code> does not
         * 	   have a value
         * @return the value of the named <code>int</code> field
         * @throws IOException if there are I/O errors while reading from the
         * 	   underlying <code>InputStream</code>
         * @throws IllegalArgumentException if type of <code>name</code> is
         * 	   not serializable or if the field type is incorrect
         */
        public abstract int get(String name, int val)
            throws IOException, IllegalArgumentException;

        /** 
         * Get the value of the named long field from the persistent field.
         *
         * @param  name the name of the field
         * @param  val the default value to use if <code>name</code> does not
         * 	   have a value
         * @return the value of the named <code>long</code> field
         * @throws IOException if there are I/O errors while reading from the
         * 	   underlying <code>InputStream</code>
         * @throws IllegalArgumentException if type of <code>name</code> is
         * 	   not serializable or if the field type is incorrect
         */
        public abstract long get(String name, long val)
            throws IOException, IllegalArgumentException;

        /** 
         * Get the value of the named float field from the persistent field.
         *
         * @param  name the name of the field
         * @param  val the default value to use if <code>name</code> does not
         * 	   have a value
         * @return the value of the named <code>float</code> field
         * @throws IOException if there are I/O errors while reading from the
         * 	   underlying <code>InputStream</code>
         * @throws IllegalArgumentException if type of <code>name</code> is
         * 	   not serializable or if the field type is incorrect
         */
        public abstract float get(String name, float val)
            throws IOException, IllegalArgumentException;

        /** 
         * Get the value of the named double field from the persistent field.
         *
         * @param  name the name of the field
         * @param  val the default value to use if <code>name</code> does not
         * 	   have a value
         * @return the value of the named <code>double</code> field
         * @throws IOException if there are I/O errors while reading from the
         * 	   underlying <code>InputStream</code>
         * @throws IllegalArgumentException if type of <code>name</code> is
         * 	   not serializable or if the field type is incorrect
         */
        public abstract double get(String name, double val)
            throws IOException, IllegalArgumentException;

        /** 
         * Get the value of the named Object field from the persistent field.
         *
         * @param  name the name of the field
         * @param  val the default value to use if <code>name</code> does not
         * 	   have a value
         * @return the value of the named <code>Object</code> field
         * @throws IOException if there are I/O errors while reading from the
         * 	   underlying <code>InputStream</code>
         * @throws IllegalArgumentException if type of <code>name</code> is
         * 	   not serializable or if the field type is incorrect
         */
        public abstract Object get(String name, Object val)
            throws IOException, IllegalArgumentException;
    }
}
