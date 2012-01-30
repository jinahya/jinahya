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
 * <code>ListResourceBundle</code> is an abstract subclass of
 * <code>ResourceBundle</code> that manages resources for a locale
 * in a convenient and easy to use list. See <code>ResourceBundle</code> for
 * more information about resource bundles in general.
 *
 * <P>
 * Subclasses must override <code>getContents</code> and provide an array,
 * where each item in the array is a pair of objects.
 * The first element of each pair is the key, which must be a
 * <code>String</code>, and the second element is the value associated with
 * that key.
 *
 * <p>
 * The following <a name="sample">example</a> shows two members of a resource
 * bundle family with the base name "MyResources".
 * "MyResources" is the default member of the bundle family, and
 * "MyResources_fr" is the French member.
 * These members are based on <code>ListResourceBundle</code>
 * (a related <a href="PropertyResourceBundle.html#sample">example</a> shows
 * how you can add a bundle to this family that's based on a properties file).
 * The keys in this example are of the form "s1" etc. The actual
 * keys are entirely up to your choice, so long as they are the same as
 * the keys you use in your program to retrieve the objects from the bundle.
 * Keys are case-sensitive.
 * <blockquote>
 * <pre>
 * 
 * public class MyResources extends ListResourceBundle {
 *     public Object[][] getContents() {
 *         return contents;
 *     }
 *     static final Object[][] contents = {
 *     // LOCALIZE THIS
 *         {"s1", "The disk \"{1}\" contains {0}."},  // MessageFormat pattern
 *         {"s2", "1"},                               // location of {0} in pattern
 *         {"s3", "My Disk"},                         // sample disk name
 *         {"s4", "no files"},                        // first ChoiceFormat choice
 *         {"s5", "one file"},                        // second ChoiceFormat choice
 *         {"s6", "{0,number} files"},                // third ChoiceFormat choice
 *         {"s7", "3 Mar 96"},                        // sample date
 *         {"s8", new Dimension(1,5)}                 // real object, not just string
 *     // END OF MATERIAL TO LOCALIZE
 *     };
 * }
 *
 * public class MyResources_fr extends ListResourceBundle {
 *     public Object[][] getContents() {
 *         return contents;
 *     }
 *     static final Object[][] contents = {
 *     // LOCALIZE THIS
 *         {"s1", "Le disque \"{1}\" {0}."},          // MessageFormat pattern
 *         {"s2", "1"},                               // location of {0} in pattern
 *         {"s3", "Mon disque"},                      // sample disk name
 *         {"s4", "ne contient pas de fichiers"},     // first ChoiceFormat choice
 *         {"s5", "contient un fichier"},             // second ChoiceFormat choice
 *         {"s6", "contient {0,number} fichiers"},    // third ChoiceFormat choice
 *         {"s7", "3 mars 1996"},                     // sample date
 *         {"s8", new Dimension(1,3)}                 // real object, not just string
 *     // END OF MATERIAL TO LOCALIZE
 *     };
 * }
 * </pre>
 * </blockquote>
 * @see ResourceBundle
 * @see PropertyResourceBundle
 * @since JDK1.1
 */
public abstract class ListResourceBundle extends ResourceBundle
{

    /** 
     * Sole constructor.  (For invocation by subclass constructors, typically
     * implicit.)
     */
    public ListResourceBundle() { }

    public final Object handleGetObject(String key) {
        return null;
    }

    /** 
     * Implementation of ResourceBundle.getKeys.
     */
    public Enumeration getKeys() {
        return null;
    }

    /** 
     * See class description.
     */
    protected abstract Object[][] getContents();
}
