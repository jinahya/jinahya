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


  


package java.net;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.Character;
import java.lang.NullPointerException;

/** 
 * Represents a Uniform Resource Identifier (URI) reference.
 *
 * <p> An instance of this class represents a URI reference as defined by <a
 * href="http://www.ietf.org/rfc/rfc2396.txt""><i>RFC&nbsp;2396: Uniform
 * Resource Identifiers (URI): Generic Syntax</i></a>, amended by <a
 * href="http://www.ietf.org/rfc/rfc2732.txt"><i>RFC&nbsp;2732: Format for
 * Literal IPv6 Addresses in URLs</i></a> and with the minor deviations noted
 * below.  This class provides constructors for creating URI instances from
 * their components or by parsing their string forms, methods for accessing the
 * various components of an instance, and methods for normalizing, resolving,
 * and relativizing URI instances.  Instances of this class are immutable.
 *
 *
 * <h4> URI syntax and components </h4>
 *
 * At the highest level a URI reference (hereinafter simply "URI") in string
 * form has the syntax
 *
 * <blockquote>
 * [<i>scheme</i><tt><b>:</b></tt><i></i>]<i>scheme-specific-part</i>[<tt><b>#</b></tt><i>fragment</i>]
 * </blockquote>
 *
 * where square brackets [...] delineate optional components and the characters
 * <tt><b>:</b></tt> and <tt><b>#</b></tt> stand for themselves.
 *
 * <p> An <i>absolute</i> URI specifies a scheme; a URI that is not absolute is
 * said to be <i>relative</i>.  URIs are also classified according to whether
 * they are <i>opaque</i> or <i>hierarchical</i>.
 *
 * <p> An <i>opaque</i> URI is an absolute URI whose scheme-specific part does
 * not begin with a slash character (<tt>'/'</tt>).  Opaque URIs are not
 * subject to further parsing.  Some examples of opaque URIs are:
 *
 * <blockquote><table cellpadding=0 cellspacing=0 summary="layout">
 * <tr><td><tt>mailto:java-net@java.sun.com</tt><td></tr>
 * <tr><td><tt>news:comp.lang.java</tt><td></tr>
 * <tr><td><tt>urn:isbn:096139210x</tt></td></tr>
 * </table></blockquote>
 *
 * <p> A <i>hierarchical</i> URI is either an absolute URI whose
 * scheme-specific part begins with a slash character, or a relative URI, that
 * is, a URI that does not specify a scheme.  Some examples of hierarchical
 * URIs are:
 *
 * <blockquote>
 * <tt>http://java.sun.com/j2se/1.3/</tt><br>
 * <tt>docs/guide/collections/designfaq.html#28</tt></br>
 * <tt>../../../demo/jfc/SwingSet2/src/SwingSet2.java</tt></br>
 * <tt>file:///~/calendar</tt>
 * </blockquote>
 *
 * <p> A hierarchical URI is subject to further parsing according to the syntax
 *
 * <blockquote>
 * [<i>scheme</i><tt><b>:</b></tt>][<tt><b>//</b></tt><i>authority</i>][<i>path</i>][<tt><b>?</b></tt><i>query</i>][<tt><b>#</b></tt><i>fragment</i>]
 * </blockquote>
 *
 * where the characters <tt><b>:</b></tt>, <tt><b>/</b></tt>,
 * <tt><b>?</b></tt>, and <tt><b>#</b></tt> stand for themselves.  The
 * scheme-specific part of a hierarchical URI consists of the characters
 * between the scheme and fragment components.
 *
 * <p> The authority component of a hierarchical URI is, if specified, either
 * <i>server-based</i> or <i>registry-based</i>.  A server-based authority
 * parses according to the familiar syntax
 *
 * <blockquote>
 * [<i>user-info</i><tt><b>@</b></tt>]<i>host</i>[<tt><b>:</b></tt><i>port</i>]
 * </blockquote>
 *
 * where the characters <tt><b>@</b></tt> and <tt><b>:</b></tt> stand for
 * themselves.  Nearly all URI schemes currently in use are server-based.  An
 * authority component that does not parse in this way is considered to be
 * registry-based.
 *
 * <p> The path component of a hierarchical URI is itself said to be absolute
 * if it begins with a slash character (<tt>'/'</tt>); otherwise it is
 * relative.  The path of a hierarchical URI that is either absolute or
 * specifies an authority is always absolute.
 *
 * <p> All told, then, a URI instance has the following nine components:
 *
 * <blockquote><table summary="Describes the components of a URI:scheme,scheme-specific-part,authority,user-info,host,port,path,query,fragment">
 * <tr><th><i>Component</i></th><th><i>Type</i></th></tr>
 * <tr><td>scheme</td><td><tt>String</tt></td></tr>
 * <tr><td>scheme-specific-part&nbsp;&nbsp;&nbsp;&nbsp;</td><td><tt>String</tt></td></tr>
 * <tr><td>authority</td><td><tt>String</tt></td></tr>
 * <tr><td>user-info</td><td><tt>String</tt></td></tr>
 * <tr><td>host</td><td><tt>String</tt></td></tr>
 * <tr><td>port</td><td><tt>int</tt></td></tr>
 * <tr><td>path</td><td><tt>String</tt></td></tr>
 * <tr><td>query</td><td><tt>String</tt></td></tr>
 * <tr><td>fragment</td><td><tt>String</tt></td></tr>
 * </table></blockquote>
 *
 * In a given instance any particular component is either <i>undefined</i> or
 * <i>defined</i> with a distinct value.  Undefined string components are
 * represented by <tt>null</tt>, while undefined integer components are
 * represented by <tt>-1</tt>.  A string component may be defined to have the
 * empty string as its value; this is not equivalent to that component being
 * undefined.
 *
 * <p> Whether a particular component is or is not defined in an instance
 * depends upon the type of the URI being represented.  An absolute URI has a
 * scheme component.  An opaque URI has a scheme, a scheme-specific part, and
 * possibly a fragment, but has no other components.  A hierarchical URI always
 * has a path (though it may be empty) and a scheme-specific-part (which at
 * least contains the path), and may have any of the other components.  If the
 * authority component is present and is server-based then the host component
 * will be defined and the user-information and port components may be defined.
 *
 *
 * <h4> Operations on URI instances </h4>
 *
 * The key operations supported by this class are those of
 * <i>normalization</i>, <i>resolution</i>, and <i>relativization</i>.
 *
 * <p> <i>Normalization</i> is the process of removing unnecessary <tt>"."</tt>
 * and <tt>".."</tt> segments from the path component of a hierarchical URI.
 * Each <tt>"."</tt> segment is simply removed.  A <tt>".."</tt> segment is
 * removed only if it is preceded by a non-<tt>".."</tt> segment.
 * Normalization has no effect upon opaque URIs.
 *
 * <p> <i>Resolution</i> is the process of resolving one URI against another,
 * <i>base</i> URI.  The resulting URI is constructed from components of both
 * URIs in the manner specified by RFC&nbsp;2396, taking components from the
 * base URI for those not specified in the original.  For hierarchical URIs,
 * the path of the original is resolved against the path of the base and then
 * normalized.  The result, for example, of resolving
 *
 * <blockquote>
 * <tt>docs/guide/collections/designfaq.html#28&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</tt>(1)
 * </blockquote>
 *
 * against the base URI <tt>http://java.sun.com/j2se/1.3/</tt> is the result
 * URI
 *
 * <blockquote>
 * <tt>http://java.sun.com/j2se/1.3/docs/guide/collections/designfaq.html#28</tt>
 * </blockquote>
 *
 * Resolving the relative URI
 *
 * <blockquote>
 * <tt>../../../demo/jfc/SwingSet2/src/SwingSet2.java&nbsp;&nbsp;&nbsp;&nbsp;</tt>(2)
 * </blockquote>
 *
 * against this result yields, in turn,
 *
 * <blockquote>
 * <tt>http://java.sun.com/j2se/1.3/demo/jfc/SwingSet2/src/SwingSet2.java</tt>
 * </blockquote>
 *
 * Resolution of both absolute and relative URIs, and of both absolute and
 * relative paths in the case of hierarchical URIs, is supported.  Resolving
 * the URI <tt>file:///~calendar</tt> against any other URI simply yields the
 * original URI, since it is absolute.  Resolving the relative URI (2) above
 * against the relative base URI (1) yields the normalized, but still relative,
 * URI
 *
 * <blockquote>
 * <tt>demo/jfc/SwingSet2/src/SwingSet2.java</tt>
 * </blockquote>
 *
 * <p> <i>Relativization</i>, finally, is the inverse of resolution: For any
 * two normalized URIs <i>u</i> and&nbsp;<i>v</i>,
 *
 * <blockquote>
 *   <i>u</i><tt>.relativize(</tt><i>u</i><tt>.resolve(</tt><i>v</i><tt>)).equals(</tt><i>v</i><tt>)</tt>&nbsp;&nbsp;and<br>
 *   <i>u</i><tt>.resolve(</tt><i>u</i><tt>.relativize(</tt><i>v</i><tt>)).equals(</tt><i>v</i><tt>)</tt>&nbsp;&nbsp;.<br>
 * </blockquote>
 *
 * This operation is often useful when constructing a document containing URIs
 * that must be made relative to the base URI of the document wherever
 * possible.  For example, relativizing the URI
 *
 * <blockquote>
 * <tt>http://java.sun.com/j2se/1.3/docs/guide/index.html</tt>
 * </blockquote>
 *
 * against the base URI
 *
 * <blockquote>
 * <tt>http://java.sun.com/j2se/1.3</tt>
 * </blockquote>
 *
 * yields the relative URI <tt>docs/guide/index.html</tt>.
 *
 *
 * <h4> Character categories </h4>
 *
 * RFC&nbsp;2396 specifies precisely which characters are permitted in the
 * various components of a URI reference.  The following categories, most of
 * which are taken from that specification, are used below to describe these
 * constraints:
 *
 * <blockquote><table cellspacing=2 summary="Describes categories alpha,digit,alphanum,unreserved,punct,reserved,escaped,and other">
 *   <tr><th valign=top><i>alpha</i></th>
 *       <td>The US-ASCII alphabetic characters,
 * 	  <tt>'A'</tt>&nbsp;through&nbsp;<tt>'Z'</tt>
 * 	  and <tt>'a'</tt>&nbsp;through&nbsp;<tt>'z'</tt></td></tr>
 *   <tr><th valign=top><i>digit</i></th>
 *       <td>The US-ASCII decimal digit characters,
 *       <tt>'0'</tt>&nbsp;through&nbsp;<tt>'9'</tt></td></tr>
 *   <tr><th valign=top><i>alphanum</i></th>
 *       <td>All <i>alpha</i> and <i>digit</i> characters</td></tr>
 *   <tr><th valign=top><i>unreserved</i>&nbsp;&nbsp;&nbsp;&nbsp;</th>
 *       <td>All <i>alphanum</i> characters together with those in the string
 * 	  <tt>"_-!.~'()*"</tt></td></tr>
 *   <tr><th valign=top><i>punct</i></th>
 *       <td>The characters in the string <tt>",;:$&+="</tt></td></tr>
 *   <tr><th valign=top><i>reserved</i></th>
 *       <td>All <i>punct</i> characters together with those in the string
 * 	  <tt>"?/[]@"</tt></td></tr>
 *   <tr><th valign=top><i>escaped</i></th>
 *       <td>Escaped octets, that is, triplets consisting of the percent
 *           character (<tt>'%'</tt>) followed by two hexadecimal digits
 *           (<tt>'0'</tt>-<tt>'9'</tt>, <tt>'A'</tt>-<tt>'F'</tt>, and
 *           <tt>'a'</tt>-<tt>'f'</tt>)</td></tr>
 *   <tr><th valign=top><i>other</i></th>
 *       <td>The Unicode characters that are not in the US-ASCII character set,
 *           are not control characters (according to the {@link
 *           java.lang.Character#isISOControl(char) Character.isISOControl}
 * 	     method), and are not space characters (according to the {@link
 * 	     java.lang.Character#isSpaceChar(char) Character.isSpaceChar}
 * 	     method)&nbsp;&nbsp;(<b><i>Deviation from RFC 2396</b>, which is
 * 	     limited to US-ASCII)</td></tr>
 * </table></blockquote>
 *
 * <p><a name="legal-chars"> The set of all legal URI characters consists of
 * the <i>unreserved</i>, <i>reserved</i>, <i>escaped</i>, and <i>other</i>
 * characters.
 *
 *
 * <h4> Escaped octets, quotation, encoding, and decoding </h4>
 *
 * RFC 2396 allows escaped octets to appear in the user-info, path, query, and
 * fragment components.  Escaping serves two purposes in URIs:
 *
 * <ul>
 *
 *   <li><p> To <i>encode</i> non-US-ASCII characters when a URI is required to
 *   conform strictly to RFC&nbsp;2396 by not containing any <i>other</i>
 *   characters.  </p></li>
 *
 *   <li><p> To <i>quote</i> characters that are otherwise illegal in a
 *   component.  The user-info, path, query, and fragment components differ
 *   slightly in terms of which characters are considered legal and illegal.
 *   </p></li>
 *
 * </ul>
 *
 * These purposes are served in this class by three related operations:
 *
 * <ul>
 *
 *   <li><p><a name="encode"> A character is <i>encoded</i> by replacing it
 *   with the sequence of escaped octets that represent that character in the
 *   UTF-8 character set.  The Euro currency symbol (<tt>'&#92;u20AC'</tt>),
 *   for example, is encoded as <tt>"%E2%82%AC"</tt>.  <i>(<b>Deviation from
 *   RFC&nbsp;2396</b>, which does not specify any particular character
 *   set.)</i> </li></p>
 *
 *   <li><p><a name="quote"> An illegal character is <i>quoted</i> simply by
 *   encoding it.  The space character, for example, is quoted by replacing it
 *   with <tt>"%20"</tt>.  UTF-8 contains US-ASCII, hence for US-ASCII
 *   characters this transformation has exactly the effect required by
 *   RFC&nbsp;2396.
 *
 *   <li><p><a name="decode"> A sequence of escaped octets is <i>decoded</i> by
 *   replacing it with the sequence of characters that it represents in the
 *   UTF-8 character set.  UTF-8 contains US-ASCII, hence decoding has the
 *   effect of de-quoting any quoted US-ASCII characters as well as that of
 *   decoding any encoded non-US-ASCII characters.  If a <a
 *   href="http://java.sun.com/j2se/1.4.2/docs/api/java/nio/charset/CharsetDecoder.html#ce">decoding error</a> occurs
 *   when decoding the escaped octets then the erroneous octets are replaced by
 *   <tt>'&#92;uFFFD'</tt>, the Unicode replacement character.  </p></li>
 *
 * </ul>
 *
 * These operations are exposed in the constructors and methods of this class
 * as follows:
 *
 * <ul>
 *
 *   <li><p> The {@link #URI(java.lang.String) </code>single-argument
 *   constructor<code>} requires any illegal characters in its argument to be
 *   quoted and preserves any escaped octets and <i>other</i> characters that
 *   are present.  </p></li>
 *
 *   <li><p> The {@link
 *   #URI(java.lang.String,java.lang.String,java.lang.String,int,java.lang.String,java.lang.String,java.lang.String)
 *   </code>multi-argument constructors<code>} quote illegal characters as
 *   required by the components in which they appear.  The percent character
 *   (<tt>'%'</tt>) is always quoted by these constructors.  Any <i>other</i>
 *   characters are preserved.  </p></li>
 *
 *   <li><p> The {@link #getRawUserInfo() getRawUserInfo}, {@link #getRawPath()
 *   getRawPath}, {@link #getRawQuery() getRawQuery}, {@link #getRawFragment()
 *   getRawFragment}, {@link #getRawAuthority() getRawAuthority}, and {@link
 *   #getRawSchemeSpecificPart() getRawSchemeSpecificPart} methods return the
 *   values of their corresponding components in raw form, without interpreting
 *   any escaped octets.  The strings returned by these methods may contain
 *   both escaped octets and <i>other</i> characters, and will not contain any
 *   illegal characters.  </p></li>
 *
 *   <li><p> The {@link #getUserInfo() getUserInfo}, {@link #getPath()
 *   getPath}, {@link #getQuery() getQuery}, {@link #getFragment()
 *   getFragment}, {@link #getAuthority() getAuthority}, and {@link
 *   #getSchemeSpecificPart() getSchemeSpecificPart} methods decode any escaped
 *   octets in their corresponding components.  The strings returned by these
 *   methods may contain both <i>other</i> characters and illegal characters,
 *   and will not contain any escaped octets.  </p></li>
 *
 *   <li><p> The {@link #toString() toString} method returns a URI string with
 *   all necessary quotation but which may contain <i>other</i> characters.
 *   </p></li>
 *
 *   <li><p> The {@link #toASCIIString() toASCIIString} method returns a fully
 *   quoted and encoded URI string that does not contain any <i>other</i>
 *   characters.  </p></li>
 *
 * </ul>
 *
 *
 * <h4> Identities </h4>
 *
 * For any URI <i>u</i>, it is always the case that
 *
 * <blockquote>
 * <tt>new URI(</tt><i>u</i><tt>.toString()).equals(</tt><i>u</i><tt>)</tt>&nbsp;.
 * </blockquote>
 *
 * For any URI <i>u</i> that does not contain redundant syntax such as two
 * slashes before an empty authority (as in <tt>file:///tmp/</tt>&nbsp;) or a
 * colon following a host name but no port (as in
 * <tt>http://java.sun.com:</tt>&nbsp;), and that does not encode characters
 * except those that must be quoted, the following identities also hold:
 *
 * <blockquote>
 * <tt>new URI(</tt><i>u</i><tt>.getScheme(),<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</tt><i>u</i><tt>.getSchemeSpecificPart(),<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</tt><i>u</i><tt>.getFragment())<br>
 * .equals(</tt><i>u</i><tt>)</tt>
 * </blockquote>
 *
 * in all cases,
 *
 * <blockquote>
 * <tt>new URI(</tt><i>u</i><tt>.getScheme(),<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</tt><i>u</i><tt>.getUserInfo(),&nbsp;</tt><i>u</i><tt>.getAuthority(),<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</tt><i>u</i><tt>.getPath(),&nbsp;</tt><i>u</i><tt>.getQuery(),<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</tt><i>u</i><tt>.getFragment())<br>
 * .equals(</tt><i>u</i><tt>)</tt>
 * </blockquote>
 *
 * if <i>u</i> is hierarchical, and
 *
 * <blockquote>
 * <tt>new URI(</tt><i>u</i><tt>.getScheme(),<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</tt><i>u</i><tt>.getUserInfo(),&nbsp;</tt><i>u</i><tt>.getHost(),&nbsp;</tt><i>u</i><tt>.getPort(),<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</tt><i>u</i><tt>.getPath(),&nbsp;</tt><i>u</i><tt>.getQuery(),<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</tt><i>u</i><tt>.getFragment())<br>
 * .equals(</tt><i>u</i><tt>)</tt>
 * </blockquote>
 *
 * if <i>u</i> is hierarchical and has either no authority or a server-based
 * authority.
 *
 *
 * <h4> URIs, URLs, and URNs </h4>
 *
 * A URI is a uniform resource <i>identifier</i> while a URL is a uniform
 * resource <i>locator</i>.  Hence every URL is a URI, abstractly speaking, but
 * not every URI is a URL.  This is because there is another subcategory of
 * URIs, uniform resource <i>names</i> (URNs), which name resources but do not
 * specify how to locate them.  The <tt>mailto</tt>, <tt>news</tt>, and
 * <tt>isbn</tt> URIs shown above are examples of URNs.
 *
 * <p> The conceptual distinction between URIs and URLs is reflected in the
 * differences between this class and the {@link URL} class.
 *
 * <p> An instance of this class represents a URI reference in the syntactic
 * sense defined by RFC&nbsp;2396.  A URI may be either absolute or relative.
 * A URI string is parsed according to the generic syntax without regard to the
 * scheme, if any, that it specifies.  No lookup of the host, if any, is
 * performed, and no scheme-dependent stream handler is constructed.  Equality,
 * hashing, and comparison are defined strictly in terms of the character
 * content of the instance.  In other words, a URI instance is little more than
 * a structured string that supports the syntactic, scheme-independent
 * operations of comparison, normalization, resolution, and relativization.
 *
 * <p> An instance of the {@link URL} class, by contrast, represents the
 * syntactic components of a URL together with some of the information required
 * to access the resource that it describes.  A URL must be absolute, that is,
 * it must always specify a scheme.  A URL string is parsed according to its
 * scheme.  A stream handler is always established for a URL, and in fact it is
 * impossible to create a URL instance for a scheme for which no handler is
 * available.  Equality and hashing depend upon both the scheme and the
 * Internet address of the host, if any; comparison is not defined.  In other
 * words, a URL is a structured string that supports the syntactic operation of
 * resolution as well as the network I/O operations of looking up the host and
 * opening a connection to the specified resource.
 *
 *
 * @version 1.38, 05/03/12
 * @author Mark Reinhold
 * @since 1.4
 *
 * @see <a href="http://ietf.org/rfc/rfc2279.txt"><i>RFC&nbsp;2279: UTF-8, a
 * transformation format of ISO 10646</i></a>, <br><a
 * href="http://www.ietf.org/rfc/rfc2373.txt"><i>RFC&nbsp;2373: IPv6 Addressing
 * Architecture</i></a>, <br><a
 * href="http://www.ietf.org/rfc/rfc2396.txt""><i>RFC&nbsp;2396: Uniform
 * Resource Identifiers (URI): Generic Syntax</i></a>, <br><a
 * href="http://www.ietf.org/rfc/rfc2732.txt"><i>RFC&nbsp;2732: Format for
 * Literal IPv6 Addresses in URLs</i></a>, <br><a
 * href="URISyntaxException.html">URISyntaxException</a>
 */
public final class URI implements Comparable, Serializable
{
    static final long serialVersionUID = -6052424284110960213L;
        
    /** 
     * The string form of this URI.
     *
     * @serial
     */
    private volatile String string;

    /** 
     * Constructs a URI by parsing the given string.
     *
     * <p> This constructor parses the given string exactly as specified by the
     * grammar in <a
     * href="http://www.ietf.org/rfc/rfc2396.txt">RFC&nbsp;2396</a>,
     * Appendix&nbsp;A, <b><i>except for the following deviations:</i></b> </p>
     *
     * <ul type=disc>
     *
     *   <li><p> An empty authority component is permitted as long as it is
     *   followed by a non-empty path, a query component, or a fragment
     *   component.  This allows the parsing of URIs such as
     *   <tt>"file:///foo/bar"</tt>, which seems to be the intent of
     *   RFC&nbsp;2396 although the grammar does not permit it.  If the
     *   authority component is empty then the user-information, host, and port
     *   components are undefined. </p></li>
     *
     *   <li><p> Empty relative paths are permitted; this seems to be the
     *   intent of RFC&nbsp;2396 although the grammar does not permit it.  The
     *   primary consequence of this deviation is that a standalone fragment
     *   such as <tt>"#foo"</tt> parses as a relative URI with an empty path
     *   and the given fragment, and can be usefully <a
     *   href="#resolve-frag">resolved</a> against a base URI.
     *
     *   <li><p> IPv4 addresses in host components are parsed rigorously, as
     *   specified by <a
     *   href="http://www.ietf.org/rfc/rfc2732.txt">RFC&nbsp;2732</a>: Each
     *   element of a dotted-quad address must contain no more than three
     *   decimal digits.  Each element is further constrained to have a value
     *   no greater than 255. </p></li>
     *
     *   <li> <p> Hostnames in host components that comprise only a single
     *   domain label are permitted to start with an <i>alphanum</i> 
     *   character. This seems to be the intent of <a
     *   href="http://www.ietf.org/rfc/rfc2396.txt">RFC&nbsp;2396</a>
     *   section&nbsp;3.2.2 although the grammar does not permit it. The
     *   consequence of this deviation is that the authority component of a
     *   hierarchical URI such as <tt>s://123</tt>, will parse as a server-based 
     *   authority. </p></li>
     *
     *   <li><p> IPv6 addresses are permitted for the host component.  An IPv6
     *   address must be enclosed in square brackets (<tt>'['</tt> and
     *   <tt>']'</tt>) as specified by <a
     *   href="http://www.ietf.org/rfc/rfc2732.txt">RFC&nbsp;2732</a>.  The
     *   IPv6 address itself must parse according to <a
     *   href="http://www.ietf.org/rfc/rfc2373.txt">RFC&nbsp;2373</a>.  IPv6
     *   addresses are further constrained to describe no more than sixteen
     *   bytes of address information, a constraint implicit in RFC&nbsp;2373
     *   but not expressible in the grammar. </p></li>
     *
     *   <li><p> Characters in the <i>other</i> category are permitted wherever
     *   RFC&nbsp;2396 permits <i>escaped</i> octets, that is, in the
     *   user-information, path, query, and fragment components, as well as in
     *   the authority component if the authority is registry-based.  This
     *   allows URIs to contain Unicode characters beyond those in the US-ASCII
     *   character set. </p></li>
     *
     * </ul>
     *
     * @param  str   The string to be parsed into a URI
     *
     * @throws  NullPointerException
     *          If <tt>str</tt> is <tt>null</tt>
     *
     * @throws  URISyntaxException
     *          If the given string violates RFC&nbsp;2396, as augmented
     *          by the above deviations
     */
    public URI(String str) throws URISyntaxException { }

    /** 
     * Constructs a hierarchical URI from the given components.
     *
     * <p> If a scheme is given then the path, if also given, must either be
     * empty or begin with a slash character (<tt>'/'</tt>).  Otherwise a
     * component of the new URI may be left undefined by passing <tt>null</tt>
     * for the corresponding parameter or, in the case of the <tt>port</tt>
     * parameter, by passing <tt>-1</tt>.
     *
     * <p> This constructor first builds a URI string from the given components
     * according to the rules specified in <a
     * href="http://www.ietf.org/rfc/rfc2396.txt">RFC&nbsp;2396</a>,
     * section&nbsp;5.2, step&nbsp;7: </p>
     *
     * <ol>
     *
     *   <li><p> Initially, the result string is empty. </p></li>
     *
     *   <li><p> If a scheme is given then it is appended to the result,
     *   followed by a colon character (<tt>':'</tt>).  </p></li>
     *
     *   <li><p> If user information, a host, or a port are given then the
     *   string <tt>"//"</tt> is appended.  </p></li>
     *
     *   <li><p> If user information is given then it is appended, followed by
     *   a commercial-at character (<tt>'@'</tt>).  Any character not in the
     *   <i>unreserved</i>, <i>punct</i>, <i>escaped</i>, or <i>other</i>
     *   categories is <a href="#quote">quoted</a>.  </p></li>
     *
     *   <li><p> If a host is given then it is appended.  If the host is a
     *   literal IPv6 address but is not enclosed in square brackets
     *   (<tt>'['</tt> and <tt>']'</tt>) then the square brackets are added.
     *   </p></li>
     *
     *   <li><p> If a port number is given then a colon character
     *   (<tt>':'</tt>) is appended, followed by the port number in decimal.
     *   </p></li>
     *
     *   <li><p> If a path is given then it is appended.  Any character not in
     *   the <i>unreserved</i>, <i>punct</i>, <i>escaped</i>, or <i>other</i>
     *   categories, and not equal to the slash character (<tt>'/'</tt>) or the
     *   commercial-at character (<tt>'@'</tt>), is quoted.  </p></li>
     *
     *   <li><p> If a query is given then a question-mark character
     *   (<tt>'?'</tt>) is appended, followed by the query.  Any character that
     *   is not a <a href="#legal-chars">legal URI character</a> is quoted.
     *   </p></li>
     *
     *   <li><p> Finally, if a fragment is given then a hash character
     *   (<tt>'#'</tt>) is appended, followed by the fragment.  Any character
     *   that is not a legal URI character is quoted.  </p></li>
     *
     * </ol>
     *
     * <p> The resulting URI string is then parsed as if by invoking the {@link
     * #URI(String)} constructor and then invoking the {@link
     * #parseServerAuthority()} method upon the result; this may cause a {@link
     * URISyntaxException} to be thrown.  </p>
     *
     * @param   scheme    Scheme name
     * @param   userInfo  User name and authorization information
     * @param   host      Host name
     * @param   port      Port number
     * @param   path      Path
     * @param   query     Query
     * @param   fragment  Fragment
     *
     * @throws URISyntaxException
     *         If both a scheme and a path are given but the path is relative,
     *         if the URI string constructed from the given components violates
     *         RFC&nbsp;2396, or if the authority component of the string is
     *         present but cannot be parsed as a server-based authority
     */
    public URI(String scheme, String userInfo, String host, int port, String
        path, String query, String fragment) throws URISyntaxException
    { }

    /** 
     * Constructs a hierarchical URI from the given components.
     *
     * <p> If a scheme is given then the path, if also given, must either be
     * empty or begin with a slash character (<tt>'/'</tt>).  Otherwise a
     * component of the new URI may be left undefined by passing <tt>null</tt>
     * for the corresponding parameter.
     *
     * <p> This constructor first builds a URI string from the given components
     * according to the rules specified in <a
     * href="http://www.ietf.org/rfc/rfc2396.txt">RFC&nbsp;2396</a>,
     * section&nbsp;5.2, step&nbsp;7: </p>
     *
     * <ol>
     *
     *   <li><p> Initially, the result string is empty.  </p></li>
     *
     *   <li><p> If a scheme is given then it is appended to the result,
     *   followed by a colon character (<tt>':'</tt>).  </p></li>
     *
     *   <li><p> If an authority is given then the string <tt>"//"</tt> is
     *   appended, followed by the authority.  If the authority contains a
     *   literal IPv6 address then the address must be enclosed in square
     *   brackets (<tt>'['</tt> and <tt>']'</tt>).  Any character not in the
     *   <i>unreserved</i>, <i>punct</i>, <i>escaped</i>, or <i>other</i>
     *   categories, and not equal to the commercial-at character
     *   (<tt>'@'</tt>), is <a href="#quote">quoted</a>.  </p></li>
     *
     *   <li><p> If a path is given then it is appended.  Any character not in
     *   the <i>unreserved</i>, <i>punct</i>, <i>escaped</i>, or <i>other</i>
     *   categories, and not equal to the slash character (<tt>'/'</tt>) or the
     *   commercial-at character (<tt>'@'</tt>), is quoted.  </p></li>
     *
     *   <li><p> If a query is given then a question-mark character
     *   (<tt>'?'</tt>) is appended, followed by the query.  Any character that
     *   is not a <a href="#legal-chars">legal URI character</a> is quoted.
     *   </p></li>
     *
     *   <li><p> Finally, if a fragment is given then a hash character
     *   (<tt>'#'</tt>) is appended, followed by the fragment.  Any character
     *   that is not a legal URI character is quoted.  </p></li>
     *
     * </ol>
     *
     * <p> The resulting URI string is then parsed as if by invoking the {@link
     * #URI(String)} constructor and then invoking the {@link
     * #parseServerAuthority()} method upon the result; this may cause a {@link
     * URISyntaxException} to be thrown.  </p>
     *
     * @param   scheme     Scheme name
     * @param   authority  Authority
     * @param   path       Path
     * @param   query      Query
     * @param   fragment   Fragment
     *
     * @throws URISyntaxException
     *         If both a scheme and a path are given but the path is relative,
     *         if the URI string constructed from the given components violates
     *         RFC&nbsp;2396, or if the authority component of the string is
     *         present but cannot be parsed as a server-based authority
     */
    public URI(String scheme, String authority, String path, String query,
        String fragment) throws URISyntaxException
    { }

    /** 
     * Constructs a hierarchical URI from the given components.
     *
     * <p> A component may be left undefined by passing <tt>null</tt>.
     *
     * <p> This convenience constructor works as if by invoking the
     * seven-argument constructor as follows:
     *
     * <blockquote><tt>
     * new&nbsp;{@link #URI(String, String, String, int, String, String, String)
     * URI}(scheme,&nbsp;null,&nbsp;host,&nbsp;-1,&nbsp;path,&nbsp;null,&nbsp;fragment);
     * </tt></blockquote>
     *
     * @param   scheme    Scheme name
     * @param   host      Host name
     * @param   path      Path
     * @param   fragment  Fragment
     *
     * @throws  URISyntaxException
     *          If the URI string constructed from the given components
     *          violates RFC&nbsp;2396
     */
    public URI(String scheme, String host, String path, String fragment)
        throws URISyntaxException
    { }

    /** 
     * Constructs a URI from the given components.
     *
     * <p> A component may be left undefined by passing <tt>null</tt>.
     *
     * <p> This constructor first builds a URI in string form using the given
     * components as follows:  </p>
     *
     * <ol>
     *
     *   <li><p> Initially, the result string is empty.  </p></li>
     *
     *   <li><p> If a scheme is given then it is appended to the result,
     *   followed by a colon character (<tt>':'</tt>).  </p></li>
     *
     *   <li><p> If a scheme-specific part is given then it is appended.  Any
     *   character that is not a <a href="#legal-chars">legal URI character</a>
     *   is <a href="#quote">quoted</a>.  </p></li>
     *
     *   <li><p> Finally, if a fragment is given then a hash character
     *   (<tt>'#'</tt>) is appended to the string, followed by the fragment.
     *   Any character that is not a legal URI character is quoted.  </p></li>
     *
     * </ol>
     *
     * <p> The resulting URI string is then parsed in order to create the new
     * URI instance as if by invoking the {@link #URI(String)} constructor;
     * this may cause a {@link URISyntaxException} to be thrown.  </p>
     *
     * @param   scheme    Scheme name
     * @param   ssp       Scheme-specific part
     * @param   fragment  Fragment
     *
     * @throws  URISyntaxException
     *          If the URI string constructed from the given components
     *          violates RFC&nbsp;2396
     */
    public URI(String scheme, String ssp, String fragment)
        throws URISyntaxException
    { }

    /** 
     * Creates a URI by parsing the given string.
     *
     * <p> This convenience factory method works as if by invoking the {@link
     * #URI(String)} constructor; any {@link URISyntaxException} thrown by the
     * constructor is caught and wrapped in a new {@link
     * IllegalArgumentException} object, which is then thrown.
     *
     * <p> This method is provided for use in situations where it is known that
     * the given string is a legal URI, for example for URI constants declared
     * within in a program, and so it would be considered a programming error
     * for the string not to parse as such.  The constructors, which throw
     * {@link URISyntaxException} directly, should be used situations where a
     * URI is being constructed from user input or from some other source that
     * may be prone to errors.  </p>
     *
     * @param  str   The string to be parsed into a URI
     * @return The new URI
     *
     * @throws  NullPointerException
     *          If <tt>str</tt> is <tt>null</tt>
     *
     * @throws  IllegalArgumentException
     *          If the given string violates RFC&nbsp;2396
     */
    public static URI create(String str) {
        return null;
    }

    /** 
     * Attempts to parse this URI's authority component, if defined, into
     * user-information, host, and port components.
     *
     * <p> If this URI's authority component has already been recognized as
     * being server-based then it will already have been parsed into
     * user-information, host, and port components.  In this case, or if this
     * URI has no authority component, this method simply returns this URI.
     *
     * <p> Otherwise this method attempts once more to parse the authority
     * component into user-information, host, and port components, and throws
     * an exception describing why the authority component could not be parsed
     * in that way.
     *
     * <p> This method is provided because the generic URI syntax specified in
     * <a href="http://www.ietf.org/rfc/rfc2396.txt">RFC&nbsp;2396</a>
     * cannot always distinguish a malformed server-based authority from a
     * legitimate registry-based authority.  It must therefore treat some
     * instances of the former as instances of the latter.  The authority
     * component in the URI string <tt>"//foo:bar"</tt>, for example, is not a
     * legal server-based authority but it is legal as a registry-based
     * authority.
     *
     * <p> In many common situations, for example when working URIs that are
     * known to be either URNs or URLs, the hierarchical URIs being used will
     * always be server-based.  They therefore must either be parsed as such or
     * treated as an error.  In these cases a statement such as
     *
     * <blockquote>
     * <tt>URI </tt><i>u</i><tt> = new URI(str).parseServerAuthority();</tt>
     * </blockquote>
     *
     * <p> can be used to ensure that <i>u</i> always refers to a URI that, if
     * it has an authority component, has a server-based authority with proper
     * user-information, host, and port components.  Invoking this method also
     * ensures that if the authority could not be parsed in that way then an
     * appropriate diagnostic message can be issued based upon the exception
     * that is thrown. </p>
     *
     * @return  A URI whose authority field has been parsed
     *          as a server-based authority
     *
     * @throws  URISyntaxException
     *          If the authority component of this URI is defined
     *          but cannot be parsed as a server-based authority
     *          according to RFC&nbsp;2396
     */
    public URI parseServerAuthority() throws URISyntaxException {
        return null;
    }

    /** 
     * Normalizes this URI's path.
     *
     * <p> If this URI is opaque, or if its path is already in normal form,
     * then this URI is returned.  Otherwise a new URI is constructed that is
     * identical to this URI except that its path is computed by normalizing
     * this URI's path in a manner consistent with <a
     * href="http://www.ietf.org/rfc/rfc2396.txt">RFC&nbsp;2396</a>,
     * section&nbsp;5.2, step&nbsp;6, sub-steps&nbsp;c through&nbsp;f; that is:
     * </p>
     *
     * <ol>
     *
     *   <li><p> All <tt>"."</tt> segments are removed. </p></li>
     *
     *   <li><p> If a <tt>".."</tt> segment is preceded by a non-<tt>".."</tt>
     *   segment then both of these segments are removed.  This step is
     *   repeated until it is no longer applicable. </p></li>
     *
     *   <li><p> If the path is relative, and if its first segment contains a
     *   colon character (<tt>':'</tt>), then a <tt>"."</tt> segment is
     *   prepended.  This prevents a relative URI with a path such as
     *   <tt>"a:b/c/d"</tt> from later being re-parsed as an opaque URI with a
     *   scheme of <tt>"a"</tt> and a scheme-specific part of <tt>"b/c/d"</tt>.
     *   <b><i>(Deviation from RFC&nbsp;2396)</i></b> </p></li>
     *
     * </ol>
     *
     * <p> A normalized path will begin with one or more <tt>".."</tt> segments
     * if there were insufficient non-<tt>".."</tt> segments preceding them to
     * allow their removal.  A normalized path will begin with a <tt>"."</tt>
     * segment if one was inserted by step 3 above.  Otherwise, a normalized
     * path will not contain any <tt>"."</tt> or <tt>".."</tt> segments. </p>
     *
     * @return  A URI equivalent to this URI,
     *          but whose path is in normal form
     */
    public URI normalize() {
        return null;
    }

    /** 
     * Resolves the given URI against this URI.
     *
     * <p> If the given URI is already absolute, or if this URI is opaque, then
     * the given URI is returned.
     *
     * <p><a name="resolve-frag"> If the given URI's fragment component is
     * defined, its path component is empty, and its scheme, authority, and
     * query components are undefined, then a URI with the given fragment but
     * with all other components equal to those of this URI is returned.  This
     * allows a URI representing a standalone fragment reference, such as
     * <tt>"#foo"</tt>, to be usefully resolved against a base URI.
     *
     * <p> Otherwise this method constructs a new hierarchical URI in a manner
     * consistent with <a
     * href="http://www.ietf.org/rfc/rfc2396.txt">RFC&nbsp;2396</a>,
     * section&nbsp;5.2; that is: </p>
     *
     * <ol>
     *
     *   <li><p> A new URI is constructed with this URI's scheme and the given
     *   URI's query and fragment components. </p></li>
     *
     *   <li><p> If the given URI has an authority component then the new URI's
     *   authority and path are taken from the given URI. </p></li>
     *
     *   <li><p> Otherwise the new URI's authority component is copied from
     *   this URI, and its path is computed as follows: </p></li>
     *
     *   <ol type=a>
     *
     *     <li><p> If the given URI's path is absolute then the new URI's path
     *     is taken from the given URI. </p></li>
     *
     *     <li><p> Otherwise the given URI's path is relative, and so the new
     *     URI's path is computed by resolving the path of the given URI
     *     against the path of this URI.  This is done by concatenating all but
     *     the last segment of this URI's path, if any, with the given URI's
     *     path and then normalizing the result as if by invoking the {@link
     *     #normalize() normalize} method. </p></li>
     *
     *   </ol>
     *
     * </ol>
     *
     * <p> The result of this method is absolute if, and only if, either this
     * URI is absolute or the given URI is absolute.  </p>
     *
     * @param  uri  The URI to be resolved against this URI
     * @return The resulting URI
     *
     * @throws  NullPointerException
     *          If <tt>uri</tt> is <tt>null</tt>
     */
    public URI resolve(URI uri) {
        return null;
    }

    /** 
     * Constructs a new URI by parsing the given string and then resolving it
     * against this URI.
     *
     * <p> This convenience method works as if invoking it were equivalent to
     * evaluating the expression <tt>{@link #resolve(java.net.URI)
     * resolve}(URI.{@link #create(String) create}(str))</tt>. </p>
     *
     * @param  str   The string to be parsed into a URI
     * @return The resulting URI
     *
     * @throws  NullPointerException
     *          If <tt>str</tt> is <tt>null</tt>
     *
     * @throws  IllegalArgumentException
     *          If the given string violates RFC&nbsp;2396
     */
    public URI resolve(String str) {
        return null;
    }

    /** 
     * Relativizes the given URI against this URI.
     *
     * <p> The relativization of the given URI against this URI is computed as
     * follows: </p>
     *
     * <ol>
     *
     *   <li><p> If either this URI or the given URI are opaque, or if the
     *   scheme and authority components of the two URIs are not identical, or
     *   if the path of this URI is not a prefix of the path of the given URI,
     *   then the given URI is returned. </p></li>
     *
     *   <li><p> Otherwise a new relative hierarchical URI is constructed with
     *   query and fragment components taken from the given URI and with a path
     *   component computed by removing this URI's path from the beginning of
     *   the given URI's path. </p></li>
     *
     * </ol>
     *
     * @param  uri  The URI to be relativized against this URI
     * @return The resulting URI
     *
     * @throws  NullPointerException
     *          If <tt>uri</tt> is <tt>null</tt>
     */
    public URI relativize(URI uri) {
        return null;
    }

    /** 
     * Constructs a URL from this URI.
     *
     * <p> This convenience method works as if invoking it were equivalent to
     * evaluating the expression <tt>new&nbsp;URL(this.toString())</tt> after
     * first checking that this URI is absolute. </p>
     *
     * @return  A URL constructed from this URI
     *
     * @throws  IllegalArgumentException
     *          If this URL is not absolute
     *
     * @throws  MalformedURLException
     *          If a protocol handler for the URL could not be found,
     *          or if some other error occurred while constructing the URL
     */
    public URL toURL() throws MalformedURLException {
        return null;
    }

    /** 
     * Returns the scheme component of this URI.
     *
     * <p> The scheme component of a URI, if defined, only contains characters
     * in the <i>alphanum</i> category and in the string <tt>"-.+"</tt>.  A
     * scheme always starts with an <i>alpha</i> character. </p>
     *
     * The scheme component of a URI cannot contain escaped octets, hence this
     * method does not perform any decoding.  </p>
     *
     * @return  The scheme component of this URI,
     *          or <tt>null</tt> if the scheme is undefined
     */
    public String getScheme() {
        return null;
    }

    /** 
     * Tells whether or not this URI is absolute.
     *
     * <p> A URI is absolute if, and only if, it has a scheme component. </p>
     *
     * @return  <tt>true</tt> if, and only if, this URI is absolute
     */
    public boolean isAbsolute() {
        return false;
    }

    /** 
     * Tells whether or not this URI is opaque.
     *
     * <p> A URI is opaque if, and only if, it is absolute and its
     * scheme-specific part does not begin with a slash character ('/').
     * An opaque URI has a scheme, a scheme-specific part, and possibly
     * a fragment; all other components are undefined. </p>
     *
     * @return  <tt>true</tt> if, and only if, this URI is opaque
     */
    public boolean isOpaque() {
        return false;
    }

    /** 
     * Returns the raw scheme-specific part of this URI.  The scheme-specific
     * part is never undefined, though it may be empty.
     *
     * <p> The scheme-specific part of a URI only contains legal URI
     * characters. </p>
     *
     * @return  The raw scheme-specific part of this URI
     *          (never <tt>null</tt>)
     */
    public String getRawSchemeSpecificPart() {
        return null;
    }

    /** 
     * Returns the decoded scheme-specific part of this URI.
     *
     * <p> The string returned by this method is equal to that returned by the
     * {@link #getRawSchemeSpecificPart() getRawSchemeSpecificPart} method
     * except that all sequences of escaped octets are <a
     * href="#decode">decoded</a>.  </p>
     *
     * @return  The decoded scheme-specific part of this URI
     *          (never <tt>null</tt>)
     */
    public String getSchemeSpecificPart() {
        return null;
    }

    /** 
     * Returns the raw authority component of this URI.
     *
     * <p> The authority component of a URI, if defined, only contains the
     * commercial-at character (<tt>'@'</tt>) and characters in the
     * <i>unreserved</i>, <i>punct</i>, <i>escaped</i>, and <i>other</i>
     * categories.  If the authority is server-based then it is further
     * constrained to have valid user-information, host, and port
     * components. </p>
     *
     * @return  The raw authority component of this URI,
     *          or <tt>null</tt> if the authority is undefined
     */
    public String getRawAuthority() {
        return null;
    }

    /** 
     * Returns the decoded authority component of this URI.
     *
     * <p> The string returned by this method is equal to that returned by the
     * {@link #getRawAuthority() getRawAuthority} method except that all
     * sequences of escaped octets are <a href="#decode">decoded</a>.  </p>
     *
     * @return  The decoded authority component of this URI,
     *          or <tt>null</tt> if the authority is undefined
     */
    public String getAuthority() {
        return null;
    }

    /** 
     * Returns the raw user-information component of this URI.
     *
     * <p> The user-information component of a URI, if defined, only contains
     * characters in the <i>unreserved</i>, <i>punct</i>, <i>escaped</i>, and
     * <i>other</i> categories. </p>
     *
     * @return  The raw user-information component of this URI,
     *          or <tt>null</tt> if the user information is undefined
     */
    public String getRawUserInfo() {
        return null;
    }

    /** 
     * Returns the decoded user-information component of this URI.
     *
     * <p> The string returned by this method is equal to that returned by the
     * {@link #getRawUserInfo() getRawUserInfo} method except that all
     * sequences of escaped octets are <a href="#decode">decoded</a>.  </p>
     *
     * @return  The decoded user-information component of this URI,
     *          or <tt>null</tt> if the user information is undefined
     */
    public String getUserInfo() {
        return null;
    }

    /** 
     * Returns the host component of this URI.
     *
     * <p> The host component of a URI, if defined, will have one of the
     * following forms: </p>
     *
     * <ul type=disc>
     *
     *   <li><p> A domain name consisting of one or more <i>labels</i>
     *   separated by period characters (<tt>'.'</tt>), optionally followed by
     *   a period character.  Each label consists of <i>alphanum</i> characters
     *   as well as hyphen characters (<tt>'-'</tt>), though hyphens never
     *   occur as the first or last characters in a label. The rightmost
     *   label of a domain name consisting of two or more labels, begins
     *   with an <i>alpha</i> character. </li></p>
     *
     *   <li><p> A dotted-quad IPv4 address of the form
     *   <i>digit</i><tt>+.</tt><i>digit</i><tt>+.</tt><i>digit</i><tt>+.</tt><i>digit</i><tt>+</tt>,
     *   where no <i>digit</i> sequence is longer than three characters and no
     *   sequence has a value larger than 255. </p></li>
     *
     *   <li><p> An IPv6 address enclosed in square brackets (<tt>'['</tt> and
     *   <tt>']'</tt>) and consisting of hexadecimal digits, colon characters
     *   (<tt>':'</tt>), and possibly an embedded IPv4 address.  The full
     *   syntax of IPv6 addresses is specified in <a
     *   href="http://www.ietf.org/rfc/rfc2373.txt"><i>RFC&nbsp;2373: IPv6
     *   Addressing Architecture</i></a>.  </p></li>
     *
     * </ul>
     *
     * The host component of a URI cannot contain escaped octets, hence this
     * method does not perform any decoding.  </p>
     *
     * @return  The host component of this URI,
     *          or <tt>null</tt> if the host is undefined
     */
    public String getHost() {
        return null;
    }

    /** 
     * Returns the port number of this URI.
     *
     * <p> The port component of a URI, if defined, is a non-negative
     * integer. </p>
     *
     * @return  The port component of this URI,
     *          or <tt>-1</tt> if the port is undefined
     */
    public int getPort() {
        return 0;
    }

    /** 
     * Returns the raw path component of this URI.
     *
     * <p> The path component of a URI, if defined, only contains the slash
     * character (<tt>'/'</tt>), the commercial-at character (<tt>'@'</tt>),
     * and characters in the <i>unreserved</i>, <i>punct</i>, <i>escaped</i>,
     * and <i>other</i> categories. </p>
     *
     * @return  The path component of this URI,
     *          or <tt>null</tt> if the path is undefined
     */
    public String getRawPath() {
        return null;
    }

    /** 
     * Returns the decoded path component of this URI.
     *
     * <p> The string returned by this method is equal to that returned by the
     * {@link #getRawPath() getRawPath} method except that all sequences of
     * escaped octets are <a href="#decode">decoded</a>.  </p>
     *
     * @return  The decoded path component of this URI,
     *          or <tt>null</tt> if the path is undefined
     */
    public String getPath() {
        return null;
    }

    /** 
     * Returns the raw query component of this URI.
     *
     * <p> The query component of a URI, if defined, only contains legal URI
     * characters. </p>
     *
     * @return  The raw query component of this URI,
     *          or <tt>null</tt> if the query is undefined
     */
    public String getRawQuery() {
        return null;
    }

    /** 
     * Returns the decoded query component of this URI.
     *
     * <p> The string returned by this method is equal to that returned by the
     * {@link #getRawQuery() getRawQuery} method except that all sequences of
     * escaped octets are <a href="#decode">decoded</a>.  </p>
     *
     * @return  The decoded query component of this URI,
     *          or <tt>null</tt> if the query is undefined
     */
    public String getQuery() {
        return null;
    }

    /** 
     * Returns the raw fragment component of this URI.
     *
     * <p> The fragment component of a URI, if defined, only contains legal URI
     * characters. </p>
     *
     * @return  The raw fragment component of this URI,
     *          or <tt>null</tt> if the fragment is undefined
     */
    public String getRawFragment() {
        return null;
    }

    /** 
     * Returns the decoded fragment component of this URI.
     *
     * <p> The string returned by this method is equal to that returned by the
     * {@link #getRawFragment() getRawFragment} method except that all
     * sequences of escaped octets are <a href="#decode">decoded</a>.  </p>
     *
     * @return  The decoded fragment component of this URI,
     *          or <tt>null</tt> if the fragment is undefined
     */
    public String getFragment() {
        return null;
    }

    /** 
     * Tests this URI for equality with another object.
     *
     * <p> If the given object is not a URI then this method immediately
     * returns <tt>false</tt>.
     *
     * <p> For two URIs to be considered equal requires that either both are
     * opaque or both are hierarchical.  Their schemes must either both be
     * undefined or else be equal without regard to case. Their fragments
     * must either both be undefined or else be equal.
     *
     * <p> For two opaque URIs to be considered equal, their scheme-specific
     * parts must be equal.
     *
     * <p> For two hierarchical URIs to be considered equal, their paths must
     * be equal and their queries must either both be undefined or else be
     * equal.  Their authorities must either both be undefined, or both be
     * registry-based, or both be server-based.  If their authorities are
     * defined and are registry-based, then they must be equal.  If their
     * authorities are defined and are server-based, then their hosts must be
     * equal without regard to case, their port numbers must be equal, and
     * their user-information components must be equal.
     *
     * <p> When testing the user-information, path, query, fragment, authority,
     * or scheme-specific parts of two URIs for equality, the raw forms rather
     * than the encoded forms of these components are compared and the
     * hexadecimal digits of escaped octets are compared without regard to
     * case.
     *
     * <p> This method satisfies the general contract of the {@link
     * java.lang.Object#equals(Object) Object.equals} method. </p>
     *
     * @param   ob   The object to which this object is to be compared
     *
     * @return  <tt>true</tt> if, and only if, the given object is a URI that
     *          is identical to this URI
     */
    public boolean equals(Object ob) {
        return false;
    }

    /** 
     * Returns a hash-code value for this URI.  The hash code is based upon all
     * of the URI's components, and satisfies the general contract of the
     * {@link java.lang.Object#hashCode() Object.hashCode} method. </p>
     *
     * @return  A hash-code value for this URI
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Compares this URI to another object, which must be a URI.
     *
     * <p> When comparing corresponding components of two URIs, if one
     * component is undefined but the other is defined then the first is
     * considered to be less than the second.  Unless otherwise noted, string
     * components are ordered according to their natural, case-sensitive
     * ordering as defined by the {@link java.lang.String#compareTo(Object)
     * String.compareTo} method.  String components that are subject to
     * encoding are compared by comparing their raw forms rather than their
     * encoded forms.
     *
     * <p> The ordering of URIs is defined as follows: </p>
     *
     * <ul type=disc>
     *
     *   <li><p> Two URIs with different schemes are ordered according the
     *   ordering of their schemes, without regard to case. </p></li>
     *
     *   <li><p> A hierarchical URI is considered to be less than an opaque URI
     *   with an identical scheme. </p></li>
     *
     *   <li><p> Two opaque URIs with identical schemes are ordered according
     *   to the ordering of their scheme-specific parts. </p></li>
     *
     *   <li><p> Two opaque URIs with identical schemes and scheme-specific
     *   parts are ordered according to the ordering of their
     *   fragments. </p></li>
     *
     *   <li><p> Two hierarchical URIs with identical schemes are ordered
     *   according to the ordering of their authority components: </p></li>
     *
     *   <ul type=disc>
     *
     *     <li><p> If both authority components are server-based then the URIs
     *     are ordered according to their user-information components; if these
     *     components are identical then the URIs are ordered according to the
     *     ordering of their hosts, without regard to case; if the hosts are
     *     identical then the URIs are ordered according to the ordering of
     *     their ports. </p></li>
     *
     *     <li><p> If one or both authority components are registry-based then
     *     the URIs are ordered according to the ordering of their authority
     *     components. </p></li>
     *
     *   </ul>
     *
     *   <li><p> Finally, two hierarchical URIs with identical schemes and
     *   authority components are ordered according to the ordering of their
     *   paths; if their paths are identical then they are ordered according to
     *   the ordering of their queries; if the queries are identical then they
     *   are ordered according to the order of their fragments. </p></li>
     *
     * </ul>
     *
     * <p> This method satisfies the general contract of the {@link
     * java.lang.Comparable#compareTo(Object) Comparable.compareTo}
     * method. </p>
     *
     * @param   ob
     *          The object to which this URI is to be compared
     *
     * @return  A negative integer, zero, or a positive integer as this URI is
     *          less than, equal to, or greater than the given URI
     *
     * @throws  ClassCastException
     *          If the given object is not a URI
     */
    public int compareTo(Object ob) {
        return 0;
    }

    /** 
     * Returns the content of this URI as a string.
     *
     * <p> If this URI was created by invoking one of the constructors in this
     * class then a string equivalent to the original input string, or to the
     * string computed from the originally-given components, as appropriate, is
     * returned.  Otherwise this URI was created by normalization, resolution,
     * or relativization, and so a string is constructed from this URI's
     * components according to the rules specified in <a
     * href="http://www.ietf.org/rfc/rfc2396.txt">RFC&nbsp;2396</a>,
     * section&nbsp;5.2, step&nbsp;7. </p>
     *
     * @return  The string form of this URI
     */
    public String toString() {
        return null;
    }

    /** 
     * Returns the content of this URI as a US-ASCII string.
     *
     * <p> If this URI does not contain any characters in the <i>other</i>
     * category then an invocation of this method will return the same value as
     * an invocation of the {@link #toString() toString} method.  Otherwise
     * this method works as if by invoking that method and then <a
     * href="#encode">encoding</a> the result.  </p>
     *
     * @return  The string form of this URI, encoded as needed
     *          so that it only contains characters in the US-ASCII
     *          charset
     */
    public String toASCIIString() {
        return null;
    }

    /** 
     * Reconstitutes a URI from the given serial stream.
     *
     * <p> The {@link java.io.ObjectInputStream#defaultReadObject()} method is
     * invoked to read the value of the <tt>string</tt> field.  The result is
     * then parsed in the usual way.
     *
     * @param  is  The object-input stream from which this object
     *             is being read
     */
    private void readObject(ObjectInputStream is)
        throws ClassNotFoundException, IOException
    { }

    /** 
     * Saves the content of this URI to the given serial stream.
     *
     * <p> The only serializable field of a URI instance is its <tt>string</tt>
     * field.  That field is given a value, if it does not have one already,
     * and then the {@link java.io.ObjectOutputStream#defaultWriteObject()}
     * method of the given object-output stream is invoked. </p>
     *
     * @param  os  The object-output stream to which this object
     *             is to be written
     */
    private void writeObject(ObjectOutputStream os) throws IOException { }
}
