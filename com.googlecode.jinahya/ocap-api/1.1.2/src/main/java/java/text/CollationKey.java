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


  


package java.text;

/** 
 * A <code>CollationKey</code> represents a <code>String</code> under the
 * rules of a specific <code>Collator</code> object. Comparing two
 * <code>CollationKey</code>s returns the relative order of the
 * <code>String</code>s they represent. Using <code>CollationKey</code>s
 * to compare <code>String</code>s is generally faster than using
 * <code>Collator.compare</code>. Thus, when the <code>String</code>s
 * must be compared multiple times, for example when sorting a list
 * of <code>String</code>s. It's more efficient to use <code>CollationKey</code>s.
 *
 * <p>
 * You can not create <code>CollationKey</code>s directly. Rather,
 * generate them by calling <code>Collator.getCollationKey</code>.
 * You can only compare <code>CollationKey</code>s generated from
 * the same <code>Collator</code> object.
 *
 * <p>
 * Generating a <code>CollationKey</code> for a <code>String</code>
 * involves examining the entire <code>String</code>
 * and converting it to series of bits that can be compared bitwise. This
 * allows fast comparisons once the keys are generated. The cost of generating
 * keys is recouped in faster comparisons when <code>String</code>s need
 * to be compared many times. On the other hand, the result of a comparison
 * is often determined by the first couple of characters of each <code>String</code>.
 * <code>Collator.compare</code> examines only as many characters as it needs which
 * allows it to be faster when doing single comparisons.
 * <p>
 * The following example shows how <code>CollationKey</code>s might be used
 * to sort a list of <code>String</code>s.
 * <blockquote>
 * <pre>
 * // Create an array of CollationKeys for the Strings to be sorted.
 * Collator myCollator = Collator.getInstance();
 * CollationKey[] keys = new CollationKey[3];
 * keys[0] = myCollator.getCollationKey("Tom");
 * keys[1] = myCollator.getCollationKey("Dick");
 * keys[2] = myCollator.getCollationKey("Harry");
 * sort( keys );
 * <br>
 * //...
 * <br>
 * // Inside body of sort routine, compare keys this way
 * if( keys[i].compareTo( keys[j] ) > 0 )
 *    // swap keys[i] and keys[j]
 * <br>
 * //...
 * <br>
 * // Finally, when we've returned from sort.
 * System.out.println( keys[0].getSourceString() );
 * System.out.println( keys[1].getSourceString() );
 * System.out.println( keys[2].getSourceString() );
 * </pre>
 * </blockquote>
 *
 * @see          Collator
 * @see          RuleBasedCollator
 * @version      1.14, 01/19/00
 * @author       Helena Shih
 */
public final class CollationKey implements Comparable
{

    /*
     * This hidden constructor does not necessarily correspond to
     * a constructor in the original source file -- it keeps javadoc
     * from generating an inappropriate default constructor.
     */
    private CollationKey() { }

    /** 
     * Compare this CollationKey to the target CollationKey. The collation rules of the
     * Collator object which created these keys are applied. <strong>Note:</strong>
     * CollationKeys created by different Collators can not be compared.
     * @param target target CollationKey
     * @return Returns an integer value. Value is less than zero if this is less
     * than target, value is zero if this and target are equal and value is greater than
     * zero if this is greater than target.
     * @see java.text.Collator#compare
     */
    public int compareTo(CollationKey target) {
        return 0;
    }

    /** 
     * Compares this CollationKey with the specified Object for order.  Returns
     * a negative integer, zero, or a positive integer as this CollationKey
     * is less than, equal to, or greater than the given Object.
     * 
     * @param   o the Object to be compared.
     * @return  a negative integer, zero, or a positive integer as this
     *		Collation Key is less than, equal to, or greater than the given
     *		Object. 
     * @exception ClassCastException the specified Object is not a
     *		  CollationKey.
     * @see   Comparable
     * @since 1.2
     */
    public int compareTo(Object o) {
        return 0;
    }

    /** 
     * Compare this CollationKey and the target CollationKey for equality.
     * The collation rules of the Collator object which created these keys are applied.
     * <strong>Note:</strong> CollationKeys created by different Collators can not be
     * compared.
     * @param target the CollationKey to compare to.
     * @return Returns true if two objects are equal, false otherwise.
     */
    public boolean equals(Object target) {
        return false;
    }

    /** 
     * Creates a hash code for this CollationKey. The hash value is calculated on the
     * key itself, not the String from which the key was created.  Thus
     * if x and y are CollationKeys, then x.hashCode(x) == y.hashCode() if
     * x.equals(y) is true.  This allows language-sensitive comparison in a hash table.
     * See the CollatinKey class description for an example.
     * @return the hash value based on the string's collation order.
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Returns the String that this CollationKey represents.
     */
    public String getSourceString() {
        return null;
    }

    /** 
     * Converts the CollationKey to a sequence of bits. If two CollationKeys
     * could be legitimately compared, then one could compare the byte arrays
     * for each of those keys to obtain the same result.  Byte arrays are
     * organized most significant byte first.
     */
    public byte[] toByteArray() {
        return null;
    }
}
