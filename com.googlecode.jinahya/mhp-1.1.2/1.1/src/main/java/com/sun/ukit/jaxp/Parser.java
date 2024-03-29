
package com.sun.ukit.jaxp;

import java.util.Hashtable;
import java.io.InputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Locator;
import org.xml.sax.InputSource;
import org.xml.sax.Attributes;
import org.xml.sax.SAXParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;

/**
 * XML non-validating parser.
 *
 * This non-validating parser conforms to <a href="http://www.w3.org/TR/REC-xml"
 * >Extensible Markup Language (XML) 1.0</a> and <a href="http://www.w3.org/TR/REC-xml-names"
 * >"Namespaces in XML"</a> specifications. 
 * The API used by the parser is <a href="http://www.jcp.org/en/jsr/detail?id=172"
 * >JSR-172</a> subset of <a href="http://java.sun.com/xml/jaxp/index.html">JAXP</a> 
 * and <a href="http://www.saxproject.org/">SAX2</a>.
 *
 * @see org.xml.sax.helpers.DefaultHandler
 */

public final class Parser
	extends SAXParser
	implements Locator
{
	public final static String	FAULT	= "";

	private final static short  BUFFSIZE_READER	= 512;
	private final static short  BUFFSIZE_PARSER	= 128;
	private final static short  BUFFSIZE_ENTITY	= 32;

	/** The end of stream character. */
	public final static char	EOS		= 0xffff;

	private Pair			mNoNS;		// there is no namespace
	private Pair			mXml;		// the xml namespace

	private DefaultHandler	mHand;		// a document handler
	private Hashtable		mEnt;		// the entities look up table
	private Hashtable		mPEnt;		// the parmeter entities look up table

	private boolean			mIsNSAware;	// if true - to report QName

	private short			mSt;		// global state of the parser
	// mSt values:
	// - 0 : the begining of the document
	// - 1 : misc before DTD
	// - 2 : DTD
	// - 3 : misc after DTD
	// - 4 : document's element
	// - 5 : misc after document's element

	private char			mESt;		// built-in entity recognizer state
	// mESt values:
	//   0x100   : the initial state
	//   > 0x100 : unrecognized name
	//   < 0x100 : replacement character

	private char[]			mBuff;		// parser buffer
	private short			mBuffIdx;	// index of the last char

	private Pair			mPref;		// stack of prefixes
	private Pair			mElm;		// stack of elements

	private Pair			mAttL;		// list of defined attributes by element name

	private Input			mInp;		// stack of entities
	private Input			mDoc;		// document entity

	private char[]			mChars;		// reading buffer
	private char			mChLen;		// current capacity
	private char			mChIdx;		// index to the next char
 
	private Attrs			mAttrs;		// attributes of the curr. element
	private String[]		mItems;		// attributes array of the curr. element
	private char			mAttrIdx;	// attributes counter/index

	private Pair			mDltd;		// deleted objects for reuse

	/**
	 * Default prefixes
	 */
	private final static char NONS[];
	private final static char XML[];
	private final static char XMLNS[];
	static {
		NONS = new char[1];
		NONS[0] = (char)0;

		XML = new char[4];
		XML[0] = (char)4;
		XML[1] = 'x';
		XML[2] = 'm';
		XML[3] = 'l';

		XMLNS = new char[6];
		XMLNS[0] = (char)6;
		XMLNS[1] = 'x';
		XMLNS[2] = 'm';
		XMLNS[3] = 'l';
		XMLNS[4] = 'n';
		XMLNS[5] = 's';
	}

	/**
	 * ASCII character type array.
	 *
	 * This array maps an ASCII (7 bit) character to the character type.<br /> 
	 * Possible character type values are:<br /> 
	 * - ' ' for any kind of white space character;<br /> 
	 * - 'a' for any lower case alphabetical character value;<br /> 
	 * - 'A' for any upper case alphabetical character value;<br /> 
	 * - 'd' for any decimal digit character value;<br /> 
	 * - 'z' for any character less then ' ' except '\t', '\n', '\r';<br /> 
	 * An ASCII (7 bit) character which does not fall in any category listed 
	 * above is mapped to it self.
	 */
	private static final byte asctyp[];

	/**
	 * Static constructor.
	 *
	 * Sets up the ASCII character type array which is used by 
	 * {@link #asctyp asctyp} method.
	 */
	static {
		asctyp	= new byte[0x80];
		short	i	= 0;
		while (i <= 0x1f)
			asctyp[i++]	= (byte)'z';
		asctyp['\t']	= (byte)' ';
		asctyp['\r']	= (byte)' ';
		asctyp['\n']	= (byte)' ';
		for (;i <= 0x2f; i++)
			asctyp[i]	= (byte)i;
		while (i <= 0x39)
			asctyp[i++]	= (byte)'d';
		for (;i <= 0x40; i++)
			asctyp[i]	= (byte)i;
		while (i <= 0x5a)
			asctyp[i++]	= (byte)'A';
		for (;i <= 0x60; i++)
			asctyp[i]	= (byte)i;
		while (i <= 0x7a)
			asctyp[i++]	= (byte)'a';
		for (;i <= 0x7f; i++)
			asctyp[i]	= (byte)i;
	}

	/**
	 * Constructor.
	 */
	public Parser(boolean nsaware)
	{
		super();
		mIsNSAware	= nsaware;

		//		Initialize the parser
		mBuff	= new char[BUFFSIZE_PARSER];
		mAttrs	= new Attrs();

		//		Default namespace
		mPref	= pair(mPref);
		mPref.name	= "";
		mPref.value	= "";
		mPref.chars	= NONS;
		mNoNS	= mPref;	// no namespace
		//		XML namespace
		mPref	= pair(mPref);
		mPref.name	= "xml";
		mPref.value	= "http://www.w3.org/XML/1998/namespace";
		mPref.chars	= XML;
		mXml	= mPref;	// XML namespace
	}

	/**
	 * Return the public identifier for the current document event.
	 *
	 * <p>The return value is the public identifier of the document
	 * entity or of the external parsed entity in which the markup
	 * triggering the event appears.</p>
	 *
	 * @return A string containing the public identifier, or
	 *	null if none is available.
	 *
	 * @see #getSystemId
	 */
	public String getPublicId()
	{
		return (mInp != null)? mInp.pubid: null;
	}

	/**
	 * Return the system identifier for the current document event.
	 *
	 * <p>The return value is the system identifier of the document
	 * entity or of the external parsed entity in which the markup
	 * triggering the event appears.</p>
	 *
	 * <p>If the system identifier is a URL, the parser must resolve it
	 * fully before passing it to the application.</p>
	 *
	 * @return A string containing the system identifier, or null
	 *	if none is available.
	 *
	 * @see #getPublicId
	 */
	public String getSystemId()
	{
		return (mInp != null)? mInp.sysid: null;
	}

	/**
	 * Return the line number where the current document event ends.
	 *
	 * @return Always returns -1 indicating the line number is not 
	 *	available.
	 *
	 * @see #getColumnNumber
	 */
	public int getLineNumber()
	{
		return -1;
	}

	/**
	 * Return the column number where the current document event ends.
	 *
	 * @return Always returns -1 indicating the column number is not 
	 *	available.
	 *
	 * @see #getLineNumber
	 */
	public int getColumnNumber()
	{
		return -1;
	}

	/**
	 * Indicates whether or not this parser is configured to
	 * understand namespaces.
	 *
	 * @return true if this parser is configured to
	 *         understand namespaces; false otherwise.
	 */
	public boolean isNamespaceAware()
	{
		return mIsNSAware;
	}

    /**
     * Indicates whether or not this parser is configured to validate
     * XML documents.
     *
     * @return true if this parser is configured to validate XML
     *          documents; false otherwise.
     */
	public boolean isValidating()
	{
		return false;
	}

	/**
	 * Parse the content of the given {@link java.io.InputStream}
	 * instance as XML using the specified
	 * {@link org.xml.sax.helpers.DefaultHandler}.
	 *
	 * @param src InputStream containing the content to be parsed.
	 * @param handler The SAX DefaultHandler to use.
	 * @exception IOException If any IO errors occur.
	 * @exception IllegalArgumentException If the given InputStream or handler is null.
	 * @exception SAXException If the underlying parser throws a
	 * SAXException while parsing.
	 * @see org.xml.sax.helpers.DefaultHandler
	 */
	public void parse(InputStream src, DefaultHandler handler)
		throws SAXException, IOException
	{
		if ((src == null) || (handler == null))
			throw new IllegalArgumentException("");
		parse(new InputSource(src), handler);
	}

    /**
     * Parse the content given {@link org.xml.sax.InputSource}
     * as XML using the specified
     * {@link org.xml.sax.helpers.DefaultHandler}.
     *
     * @param is The InputSource containing the content to be parsed.
     * @param handler The SAX DefaultHandler to use.
	 * @exception IOException If any IO errors occur.
     * @exception IllegalArgumentException If the InputSource or handler is null.
     * @exception SAXException If the underlying parser throws a
     * SAXException while parsing.
	 * @see org.xml.sax.helpers.DefaultHandler
     */
	public void parse(InputSource is, DefaultHandler handler)
		throws SAXException, IOException
	{
		if ((is == null) || (handler == null))
			throw new IllegalArgumentException("");
		//		Set up the handler
		mHand	= handler;
		//		Set up the document
		mInp	= new Input(BUFFSIZE_READER);
		setinp(is);
		parse(handler);
	}

    /**
     * Parse the XML document content using the specified
     * {@link org.xml.sax.helpers.DefaultHandler}.
     *
     * @param handler The SAX DefaultHandler to use.
	 * @exception IOException If any IO errors occur.
     * @exception SAXException If the underlying parser throws a
     * SAXException while parsing.
	 * @see org.xml.sax.helpers.DefaultHandler
     */
	private void parse(DefaultHandler handler)
		throws SAXException, IOException
	{
		try {
			//		Initialize the parser
			mPEnt	= new Hashtable();
			mEnt	= new Hashtable();
			mDoc	= mInp;			// current input is document entity
			mChars	= mInp.chars;	// use document entity buffer
			//		Parse an xml document
			char	ch;
			mHand.setDocumentLocator(this);
			mHand.startDocument();
			mSt	= 1;
			while ((ch = next()) != EOS) {
				switch (chtyp(ch)) {
				case '<':
					ch	= next();
					switch (ch) {
					case '?':
						pi();
						break;

					case '!':
						ch	= next();
						back();
						if (ch == '-')
							comm();
						else
							dtd();
						break;

					default:			// must be the first char of an xml name 
						if (mSt == 5)	// misc after document's element
							panic(FAULT);
						//		Document's element.
						back();
						mSt	= 4;		// document's element
						elm();
						mSt	= 5;		// misc after document's element
						break;
					}
					break;

				case ' ':
					//		Skip white spaces
					break;

				default:
					panic(FAULT);
				}
			}
			if (mSt != 5)	// misc after document's element
				panic(FAULT);
		} finally {
			mHand.endDocument();
			while (mAttL != null) {
				while (mAttL.list != null) {
					if (mAttL.list.list != null)
						del(mAttL.list.list);
					mAttL.list = del(mAttL.list);
				}
				mAttL = del(mAttL);
			}
			while (mElm != null)
				mElm	= del(mElm);
			while (mPref != mXml)
				mPref	= del(mPref);
			while (mInp != null)
				pop();
			if ((mDoc != null) && (mDoc.src != null)) {
				try { mDoc.src.close(); } catch (IOException ioe) {}
			}
			mPEnt	= null;
			mEnt	= null;
			mDoc	= null;
			mHand	= null;
		}
	}

	/**
	 * Parses the document type declaration.
	 *
	 * @exception SAXException 
	 * @exception IOException 
	 */
	private void dtd()
		throws SAXException, IOException
	{
		char	ch;
		String	str		= null;
		String	name	= null;
		Pair	psid	= null;
		// read 'DOCTYPE'
		if ("DOCTYPE".equals(name(false)) != true)
			panic(FAULT);
		mSt	= 2;	// DTD
		for (short st = 0; st >= 0;) {
			ch	= next();
			switch (st) {
			case 0:		// read the document type name
				if (chtyp(ch) != ' ') {
					back();
					name	= name(mIsNSAware);
					wsskip();
					st		= 1;	// read 'PUPLIC' or 'SYSTEM'
				}
				break;

			case 1:		// read 'PUPLIC' or 'SYSTEM'
				switch (chtyp(ch)) {
				case 'A':
					back();
					psid = pubsys(' ');
					st	= 2;	// skip spaces before internal subset
					break;

				case '[':
					back();
					st	= 2;	// skip spaces before internal subset
					break;

				case '>':
					back();
					st	= 3;	// skip spaces after internal subset
					break;

				default:
					panic(FAULT);
				}
				if (psid != null) {
					//		DTD external subset
					// The parser reports DTD external subset by resolveEntity, but 
					// does not expect to get back an InputSource object. There are 
					// two reasons:
					// 1. the parser does not support validation thus it may ignore 
					//    the DTD external subset.
					// 2. the parser does not support schemas
					if (mHand.resolveEntity(psid.name, psid.value) != null)
						panic(FAULT);
					del(psid);
					//		Unresolved DTD external subset
					mHand.skippedEntity("[dtd]");
				}
				break;

			case 2:		// skip spaces before internal subset
				switch (chtyp(ch)) {
				case '[':
					//		Process internal subset
					dtdint();
					st	= 3;	// skip spaces after internal subset
					break;

				case '>':
					back();
					st	= 3;	// skip spaces after internal subset
					break;

				case ' ':
					// skip white spaces
					break;

				default:
					panic(FAULT);
				}
				break;

			case 3:		// skip spaces after internal subset
				switch (chtyp(ch)) {
				case '>':
					st	= -1;	// end of DTD
					break;

				case ' ':
					// skip white spaces
					break;

				default:
					panic(FAULT);
				}
				break;

			default:
				panic(FAULT);
			}
		}
		mSt	= 3;	// misc after DTD
	}

	/**
	 * Parses the document type declaration internal subset.
	 *
	 * @exception SAXException 
	 * @exception IOException 
	 */
	private void dtdint()
		throws SAXException, IOException
	{
		char ch;
		for (short st = 0; st >= 0;) {
			ch	= next();
			switch (st) {
			case 0:		// skip white spaces before a declaration
				switch (chtyp(ch)) {
				case '<':
					ch	= next();
					switch (ch) {
					case '?':
						pi();
						break;

					case '!':
						ch	= next();
						back();
						if (ch == '-') {
							comm();
							break;
						}
						// markup or entity declaration
						bntok();
						switch (bkeyword()) {
						case 'n':
							dtdent();
							break;

						case 'a':
							dtdattl();		// parse attributes declaration
							break;

						case 'e':
							dtdelm();		// parse element declaration
							break;

						case 'o':
							dtdnot();		// parse notation declaration
							break;

						default:
							panic(FAULT);	// unsupported markup declaration
							break;
						}
						st	= 1;		// read the end of declaration
						break;

					default:
						panic(FAULT);
						break;
					}
					break;

				case '%':
					//		A parameter entity reference
					pent(' ');
					break;

				case ']':
					//		End of DTD internal subset
					st	= -1;
					break;

				case ' ':
					//		Skip white spaces
					break;

				default:
					panic(FAULT);
				}
				break;

			case 1:		// read the end of declaration
				switch (ch) {
				case '>':		// there is no notation 
					st	= 0;	// skip white spaces before a declaration
					break;

				case ' ':
				case '\n':
				case '\r':
				case '\t':
					//		Skip white spaces
					break;

				default:
					panic(FAULT);
					break;
				}
				break;

			default:
				panic(FAULT);
			}
		}
	}

	/**
	 * Parses an entity declaration.
	 * This method fills the general (<code>mEnt</code>) and parameter 
	 * (<code>mPEnt</code>) entity look up table.
	 *
	 * @exception SAXException 
	 * @exception IOException 
	 */
	private void dtdent()
		throws SAXException, IOException
	{
		String	str = null;
		char[]	val = null;
		Input   inp = null;
		Pair    ids = null;
		char	ch;
		for (short st = 0; st >= 0;) {
			ch	= next();
			switch (st) {
			case 0:		// skip white spaces before entity name
				switch (chtyp(ch)) {
				case ' ':
					//		Skip white spaces
					break;

				case '%':
					//		Parameter entity or parameter entity declaration.
					ch = next();
					back();
					if (chtyp(ch) == ' ') {
						//		Parameter entity declaration.
						wsskip();
						str = name(false);
						switch (chtyp(wsskip())) {
						case 'A':
							//		Read the external identifier
							ids = pubsys(' ');
							if (wsskip() == '>') {
								//		External parsed entity
								inp       = new Input();
								inp.pubid = ids.name;
								inp.sysid = ids.value;
								mPEnt.put(str, inp);
							} else {
								panic(FAULT);
							}
							del(ids);
							st	= -1;	// the end of declaration
							break;

						case '\"':
						case '\'':
							//		Read the parameter entity value
							bqstr('-');
							//		Create the parameter entity value
							val	= new char[mBuffIdx + 1];
							System.arraycopy(mBuff, 1, val, 1, val.length - 1);
							//		Add surrounding spaces [#4.4.8]
							val[0] = ' ';
							//		Add the entity to the entity look up table
							inp       = new Input(val);
							inp.pubid = mInp.pubid;
							inp.sysid = mInp.sysid;
							mPEnt.put(str, inp);
							st	= -1;	// the end of declaration
							break;

						default:
							panic(FAULT);
							break;
						}
					} else {
						//		Parameter entity reference.
						pent(' ');
					}
					break;

				default:
					back();
					str	= name(false);
					st	= 1;	// read entity declaration value
					break;
				}
				break;

			case 1:		// read entity declaration value
				switch (chtyp(ch)) {
				case '\"':	// internal entity
				case '\'':
					back();
					bqstr('-');	// read a string into the buffer
					if (mEnt.get(str) == null) {
						//		Create general entity value
						val	= new char[mBuffIdx];
						System.arraycopy(mBuff, 1, val, 0, val.length);
						//		Add the entity to the entity look up table
						inp       = new Input(val);
						inp.pubid = mInp.pubid;
						inp.sysid = mInp.sysid;
						mEnt.put(str, inp);
					}
					st	= -1;	// the end of declaration
					break;

				case 'A':	// external entity
					back();
					ids = pubsys(' ');
					switch (wsskip()) {
					case '>':	// external parsed entity
						inp       = new Input();
						inp.pubid = ids.name;
						inp.sysid = ids.value;
						mEnt.put(str, inp);
						break;

					case 'N':	// external general unparsed entity
						if ("NDATA".equals(name(false)) == true) {
							wsskip();
							mHand.unparsedEntityDecl(
								str, ids.name, ids.value, name(false));
							break;
						}
					default:
						panic(FAULT);
						break;
					}
					del(ids);
					st	= -1;	// the end of declaration
					break;

				case ' ':
					//		Skip white spaces
					break;

				default:
					panic(FAULT);
					break;
				}
				break;

			default:
				panic(FAULT);
			}
		}
	}

	/**
	 * Parses an element declaration. 
	 *
	 * This method parses the declaration up to the closing angle 
	 * bracket.
	 *
	 * @exception SAXException 
	 * @exception IOException 
	 */
	private void dtdelm()
		throws SAXException, IOException
	{
		//		This is stub implementation which skips an element 
		//		declaration.
		wsskip();
		name(mIsNSAware);

		char	ch;
		while (true) {
			ch	= next();
			switch (ch) {
			case '>':
				back();
				return;

			case EOS:
				panic(FAULT);

			default:
				break;
			}
		}
	}

	/**
	 * Parses an attribute list declaration.
	 *
	 * This method parses the declaration up to the closing angle 
	 * bracket.
	 *
	 * @exception SAXException 
	 * @exception IOException 
	 */
	private void dtdattl()
		throws SAXException, IOException
	{
		char elmqn[] = null;
		Pair elm     = null;
		char ch;
		for (short st = 0; st >= 0;) {
			ch	= next();
			switch (st) {
			case 0:		// read the element name
				switch (chtyp(ch)) {
				case 'a':
				case 'A':
				case '_':
				case 'X':
				case ':':
					back();
					//		Get the element from the list or add a new one.
					elmqn = qname(mIsNSAware);
					elm   = find(mAttL, elmqn);
					if (elm == null) {
						elm = pair(mAttL);
						elm.chars = elmqn;
						mAttL     = elm;
					}
					st = 1;		// read an attribute declaration
					break;

				case ' ':
					break;

				case '%':
					pent(' ');
					break;

				default:
					panic(FAULT);
					break;
				}
				break;

			case 1:		// read an attribute declaration
				switch (chtyp(ch)) {
				case 'a':
				case 'A':
				case '_':
				case 'X':
				case ':':
					back();
					dtdatt(elm);
					if (wsskip() == '>')
						return;
					break;

				case ' ':
					break;

				case '%':
					pent(' ');
					break;

				default:
					panic(FAULT);
					break;
				}
				break;

			default:
				panic(FAULT);
				break;
			}
		}
	}

	/**
	 * Parses an attribute declaration.
	 *
	 * The attribut uses the following fields of Pair object:
	 * chars - characters of qualified name
	 * id    - the type identifier of the attribute
	 * list  - a pair which holds the default value (chars field)
	 *
	 * @param elm An object which reprecents all defined attributes on an element.
	 * @exception SAXException 
	 * @exception IOException 
	 */
	private void dtdatt(Pair elm)
		throws SAXException, IOException
	{
		char attqn[] = null;
		Pair att     = null;
		char ch;
		for (short st = 0; st >= 0;) {
			ch	= next();
			switch (st) {
			case 0:		// the attribute name
				switch (chtyp(ch)) {
				case 'a':
				case 'A':
				case '_':
				case 'X':
				case ':':
					back();
					//		Get the attribut from the list or add a new one.
					attqn = qname(mIsNSAware);
					att   = find(elm.list, attqn);
					if (att == null) {
						att = pair(elm.list);
						att.chars = attqn;
						elm.list  = att;
					} else {
						att.id    = 'c';
						if (att.list != null)
							del(att.list);
						att.list  = null;
					}
					wsskip();
					st = 1;
					break;

				case '%':
					pent(' ');
					break;

				case ' ':
					break;

				default:
					panic(FAULT);
					break;
				}
				break;

			case 1:		// the attribute type
				switch (ch) {
				case '(':
					back();
					att.id = 'u';	// enumeration type
					st = 2;			// read the first element of the list
					break;

				case '%':
					pent(' ');
					break;

				case ' ':
					break;

				default:
					back();
					bntok();		// read type id
					att.id = bkeyword();
					switch (att.id) {
					case 'o':		// NOTATION
						if (wsskip() != '(')
							panic(FAULT);
						st = 2;		// read the first element of the list
						break;

					case 'i':		// ID
					case 'r':		// IDREF
					case 'R':		// IDREFS
					case 'n':		// ENTITY
					case 'N':		// ENTITIES
					case 't':		// NMTOKEN
					case 'T':		// NMTOKENS
					case 'c':		// CDATA
						wsskip();
						st = 4;		// read default declaration
						break;

					default:
						panic(FAULT);
						break;
					}
					break;
				}
				break;

			case 2:		// read the first element of the list
				if (ch != '(')
					panic(FAULT);
				ch = wsskip();
				switch (chtyp(ch)) {
				case 'a':
				case 'A':
				case 'd':
				case '.':
				case ':':
				case '-':
				case '_':
				case 'X':
					switch (att.id) {
					case 'u':	// enumeration type
						bntok();
						break;

					case 'o':	// NOTATION
						mBuffIdx = -1;
						bname(false);
						break;

					default:
						panic(FAULT);
						break;
					}
					wsskip();
					st = 3;		// read next element of the list
					break;

				case '%':
					next();
					pent(' ');
					break;

				default:
					panic(FAULT);
					break;
				}
				break;

			case 3:		// read next element of the list
				switch (ch) {
				case ')':
					wsskip();
					st = 4;		// read default declaration
					break;

				case '|':
					wsskip();
					switch (att.id) {
					case 'u':	// enumeration type
						bntok();
						break;

					case 'o':	// NOTATION
						mBuffIdx = -1;
						bname(false);
						break;

					default:
						panic(FAULT);
						break;
					}
					wsskip();
					break;

				case '%':
					pent(' ');
					break;

				default:
					panic(FAULT);
					break;
				}
				break;

			case 4:		// read default declaration
				switch (ch) {
				case '#':
					bntok();
					switch (bkeyword()) {
					case 'F':	// FIXED
						switch (wsskip()) {
						case '\"':
						case '\'':
							st = 5;	// read the default value
							break;

						default:
							st = -1;
							break;
						}
						break;

					case 'Q':	// REQUIRED
					case 'I':	// IMPLIED
						st = -1;
						break;

					default:
						panic(FAULT);
						break;
					}
					break;

				case '\"':
				case '\'':
					back();
					st = 5;			// read the default value
					break;

				case ' ':
				case '\n':
				case '\r':
				case '\t':
					break;

				case '%':
					pent(' ');
					break;

				default:
					back();
					st = -1;
					break;
				}
				break;

			case 5:		// read the default value
				switch (ch) {
				case '\"':
				case '\'':
					back();
					bqstr('-');	// the value in the mBuff now
					att.list = pair(null);
					//		Create a string like "attqname='value' "
					att.list.chars = new char[att.chars.length + mBuffIdx + 3];
					System.arraycopy(
						att.chars, 1, att.list.chars, 0, att.chars.length - 1);
					att.list.chars[att.chars.length - 1] = '=';
					att.list.chars[att.chars.length]     = ch;
					System.arraycopy(
						mBuff, 1, att.list.chars, att.chars.length + 1, mBuffIdx);
					att.list.chars[att.chars.length + mBuffIdx + 1] = ch;
					att.list.chars[att.chars.length + mBuffIdx + 2] = ' ';
					st = -1;
					break;

				default:
					panic(FAULT);
					break;
				}
				break;

			default:
				panic(FAULT);
				break;
			}
		}
	}

	/**
	 * Parses a notation declaration.
	 *
	 * This method parses the declaration up to the closing angle 
	 * bracket.
	 *
	 * @exception SAXException 
	 * @exception IOException 
	 */
	private void dtdnot()
		throws SAXException, IOException
	{
		wsskip();
		String name = name(false);
		wsskip();
		Pair   ids	= pubsys('N');
		mHand.notationDecl(name, ids.name, ids.value);
		del(ids);
	}

	/**
	 * Parses an element.
	 *
	 * This recursive method is responsible for prefix scope control 
	 * (<code>mPref</code>). When the element is leaving the scope all 
	 * prefixes defined within the element are removed from the prefix 
	 * stack.
	 *
	 * @exception SAXException 
	 * @exception IOException 
	 */
	private void elm()
		throws SAXException, IOException
	{
		//		Save the current top of the prefix stack
		Pair	pref	= mPref;
		//		Read an element name and put it on top of the element stack
		mElm		= pair(mElm);
		mElm.chars	= qname(mIsNSAware);
		mElm.name	= mElm.local();
		//		Find the list of defined attributs of the current element 
		Pair	elm	= find(mAttL, mElm.chars);
		//		Read attributes till the end of the element tag
		mAttrIdx	= 0;
		Pair	att	= pair(null);
		att.list	= (elm != null)? elm.list: null;	// attrs defined on this elm
		attr(att);
		del(att);
		//		Read the element and it's content
		mBuffIdx	= -1;
		char	ch;
		for (short st = 0; st >= 0;) {
			ch	= next();
			switch (st) {
			case 0:		// read the end of the element tag
			case 1:		// read the end of the empty element
				switch (ch) {
				case '>':
					//		Report the element
					if (mIsNSAware == true) {
						mElm.value	= rslv(mElm.chars);
						mHand.startElement(
							mElm.value,
							mElm.name,
							"",
							mAttrs);
					} else {
						mHand.startElement(
							"",
							"",
							mElm.name,
							mAttrs);
					}
					mItems	= null;
					st	= (st == 0)? (short)2: (short)-1;
					break;

				case '/':
					if (st != 0)
						panic(FAULT);
					st	= 1;
					break;

				default:
					panic(FAULT);
				}
				break;

			case 2:		// skip white space between tags
				switch (chtyp(ch)) {
				case ' ':
					bappend(ch);
					break;

				case '<':
					// BUG:	With additional info from DTD and xml:space attr
					// the following call can be supported:
					// mHand.ignorableWhitespace(mBuff, 0, (mBuffIdx + 1));
					bflash();

				default:
					back();
					st	= 3;
					break;
				}
				break;

			case 3:		// read the text content of the element
				switch (ch) {
				case '&':
					ent('c');
					break;

				case '<':
					bflash();
					switch (next()) {
					case '/':	// the end of the element content
						//		Check element's open/close tags balance
						mBuffIdx = -1;
						bname(mIsNSAware);
						char[] chars = mElm.chars;
						if (chars.length == (mBuffIdx + 1)) {
							for (char i = 1; i <= mBuffIdx; i += 1) {
								if (chars[i] != mBuff[i])
									panic(FAULT);
							}
						} else {
							panic(FAULT);
						}
						//		Skip white spaces before '>'
						if (wsskip() != '>')
							panic(FAULT);
						ch	= next();
						st	= -1;
						break;

					case '!':	// a comment or a CDATA
						ch	= next();
						back();
						switch (ch) {
						case '-':	// must be a comment
							comm();
							break;

						case '[':	// must be a CDATA section
							cdat();
							break;

						default:
							panic(FAULT);
						}
						break;

					case '?':	// processing instruction
						pi();
						break;

					default:	// must be the first char of an xml name 
						back();
						elm();	// recursive call
						break;
					}
					mBuffIdx = -1;
					if (st != -1)
						st	= 2;
					break;

				case '\r':		// EOL processing [#2.11]
					if (next() != '\n')
						back();
					bappend('\n');
					break;

				default:
					bappend(ch);
					break;
				}
				break;

			default:
				panic(FAULT);
			}
		}
		//		Report the end of element
		if (mIsNSAware == true)
			mHand.endElement(mElm.value, mElm.name, "");
		else
			mHand.endElement("", "", mElm.name);
		//		Remove the top element tag
		mElm	= del(mElm);
		//		Restore the top of the prefix stack
		while (mPref != pref) {
			mHand.endPrefixMapping(mPref.name);
			mPref	= del(mPref);
		}
	}

	/**
	 * Parses an attribute.
	 *
	 * This recursive method is responsible for prefix addition 
	 * (<code>mPref</code>) and prefix mapping reports on the way down. The 
	 * element's start tag end triggers the return process. The method then 
	 * on it's way back resolves prefixes and accumulates attributes.<br />
	 * Note that this method will not report namespace declaration attributes
	 * (xmlns* attributes), the {@link Handler#mapStart mapStart} method is 
	 * invoked instead.
	 *
	 * @param att An object which reprecents current attribute.
	 * @exception SAXException 
	 * @exception IOException 
	 */
	private void attr(Pair att)
		throws SAXException, IOException
	{
		Pair	next = null;
		char	norm = 'c';		// CDATA-type normalization by default [#3.3.3]
		String	val;
		String	type;
		try {
			switch (wsskip()) {
			case '/':
			case '>':
				//		Go through all defined attributes on current tag to 
				//		find defaults
				for (Pair def = att.list; def != null; def = def.next) {
					if (def.list != null) {
						//		Attribut definition with default value
						Pair act = att.next;
						while (act != null) {
							if (act.eqname(def.chars) == true)
								break;
							act = act.next;
						}
						if (act == null) {
							//		Add default attribute
							push(new Input(def.list.chars));
							attr(att);
							return;
						}
					}
				}
				//		Ensure the attribute string array capacity
				mAttrs.setLength(mAttrIdx);
				mItems	= mAttrs.mItems;
				return;

			default:
				//		Read the attribute name and value
				att.chars = qname(mIsNSAware);
				att.name  = att.local();
				type = "CDATA";
				if (att.list != null) {
					Pair attr = find(att.list, att.chars);
					if (attr != null) {
						switch (attr.id) {
						case 'i':
							type = "ID";
							norm = 'i';
							break;

						case 'r':
							type = "IDREF";
							norm = 'i';
							break;

						case 'R':
							type = "IDREFS";
							norm = 'i';
							break;

						case 'n':
							type = "ENTITY";
							norm = 'i';
							break;

						case 'N':
							type = "ENTITIES";
							norm = 'i';
							break;

						case 't':
							type = "NMTOKEN";
							norm = 'i';
							break;

						case 'T':
							type = "NMTOKENS";
							norm = 'i';
							break;

						case 'u':
							type = "NMTOKEN";
							norm = 'i';
							break;

						case 'o':
							type = "NOTATION";
							norm = 'i';
							break;

						case 'c':
							norm = 'c';
							break;

						default:
							panic(FAULT);
							break;
						}
					}
				}
				wsskip();
				if (next() != '=')
					panic(FAULT);
				bqstr(norm);		// read the value with normalization.
				val = new String(mBuff, 1, mBuffIdx);
				//		Put a namespace declaration on top of the prefix stack
				if ((mIsNSAware == false) || (isdecl(att, val) == false)) {
					//		An ordinary attribute
					mAttrIdx++;
					//		Recursive call to parse the next attribute
					next = pair(att);
					next.list = att.list;
					attr(next);
					mAttrIdx--;
					//		Add the attribute to the attributes string array
					char	idx	= (char)(mAttrIdx << 3);
					mItems[idx + 1]	= att.qname();		// attr qname
					mItems[idx + 2]	= att.name;			// attr local name
					mItems[idx + 3]	= val;				// attr value
					mItems[idx + 4]	= type;				// attr type
					//		Resolve the prefix if any and report the attribute
					//		NOTE: The attribute does not accept the default namespace.
					mItems[idx + 0]	= (att.chars[0] != 0)? rslv(att.chars): "";
				} else {
					//		A namespace declaration
					//		Report a start of the new mapping
					mHand.startPrefixMapping(mPref.name, mPref.value);
					//		Recursive call to parse the next attribute
					next = pair(att);
					next.list = att.list;
					attr(next);
					//		NOTE: The namespace declaration is not reported.
				}
				break;
			}
		} finally {
			if (next != null)
				del(next);
		}
	}

	/**
	 * Parses a comment.
	 *
	 * @exception org.xml.SAXException 
	 * @exception java.io.IOException 
	 */
	private void comm()
		throws SAXException, IOException
	{
		if (mSt == 0)
			mSt	= 1;	// misc before DTD
		char	ch;
		for (short st = 0; st >= 0;) {
			ch	= next();
			switch (st) {
			case 0:		// first '-' of the comment open
				if (ch == '-')
					st	= 1;
				else
					panic(FAULT);
				break;

			case 1:		// secind '-' of the comment open
				if (ch == '-')
					st	= 2;
				else
					panic(FAULT);
				break;

			case 2:		// skip the comment body
				if (ch == '-')
					st	= 3;
				break;

			case 3:		// second '-' of the comment close
				st	= (ch == '-')? (short)4: (short)2;
				break;

			case 4:		// '>' of the comment close
				if (ch == '>')
					st	= -1;
				else
					panic(FAULT);
				break;

			default:
				panic(FAULT);
			}
		}
	}

	/**
	 * Parses a processing instruction.
	 *
	 * @exception SAXException 
	 * @exception IOException 
	 */
	private void pi()
		throws SAXException, IOException
	{
		char	ch;
		String	str	= null;
		mBuffIdx = -1;
		for (short st = 0; st >= 0;) {
			ch	= next();
			switch (st) {
			case 0:		// read the PI name
				switch (chtyp(ch)) {
				case 'a':
				case 'A':
				case '_':
				case 'X':
					back();
					str	= name(false);
					st	= 1;
					break;

				case ' ':
					str	= "";
					st	= 2;
					break;

				default:
					panic(FAULT);
				}
				break;

			case 1:		// skip spaces after the PI name
				if (chtyp(ch) != ' ') {
					back();
					if (mXml.name.equals(str.toLowerCase()) == true) {
						//		This is the xml text declaration
						panic(FAULT);
					} else {
						//		This is processing instruction
						if (mSt == 0)	// the begining of the document
							mSt	= 1;	// misc before DTD
						st	= 2;
					}
					mBuffIdx = -1;
				}
				break;

			case 2:		// accumulate the PI body
				if (ch == '?')
					st	= 3;
				else
					bappend(ch);
				break;

			case 3:		// end of the PI body
				if (ch == '>') {
					//		PI has been read.
					mHand.processingInstruction(
						str, new String(mBuff, 0, mBuffIdx + 1));
					st	= -1;
				} else {
					bappend('?');
					bappend(ch);
					st	= 2;
				}
				break;

			default:
				panic(FAULT);
			}
		}
	}

	/**
	 * Parses a character data.
	 *
	 * @exception SAXException 
	 * @exception IOException 
	 */
	private void cdat()
		throws SAXException, IOException
	{
		char	ch;
		mBuffIdx = -1;
		for (short st = 0; st >= 0;) {
			ch	= next();
			switch (st) {
			case 0:		// the first '[' of the CDATA open
				if (ch == '[')
					st	= 1;
				else
					panic(FAULT);
				break;

			case 1:		// read "CDATA"
				if (chtyp(ch) == 'A') {
					bappend(ch);
				} else {
					if ("CDATA".equals(
							new String(mBuff, 0, mBuffIdx + 1)) != true)
						panic(FAULT);
					back();
					st	= 2;
				}
				break;

			case 2:		// the second '[' of the CDATA open
				if (ch != '[')
					panic(FAULT);
				mBuffIdx = -1;
				st	= 3;
				break;

			case 3:		// read data before the first ']'
				if (ch != ']')
					bappend(ch);
				else
					st	= 4;
				break;

			case 4:		// read the second ']' or continue to read the data
				if (ch != ']') {
					bappend(']');
					bappend(ch);
					st	= 3;
				} else {
					st	= 5;
				}
				break;

			case 5:		// read '>' or continue to read the data
				if (ch != '>') {
					bappend(']');
					bappend(']');
					bappend(ch);
					st	= 3;
				} else {
					bflash();
					st	= -1;
				}
				break;

			default:
				panic(FAULT);
			}
		}
	}

	/**
	 * Reads a xml name.
	 *
	 * The xml name must conform "Namespaces in XML" specification. Therefore 
	 * the ':' character is not allowed in the name. This method should be 
	 * used for PI and entity names which may not have a namespace according 
	 * to the specification mentioned above.
	 *
	 * @param ns The true value turns namespace conformance on.
	 * @return The name has been read.
	 * @exception SAXException When incorrect character appear in the name.
	 * @exception IOException 
	 */
	private String name(boolean ns)
		throws SAXException, IOException
	{
		mBuffIdx = -1;
		bname(ns);
		return new String(mBuff, 1, mBuffIdx);
	}

	/**
	 * Reads a qualified xml name.
	 *
	 * The characters of a qualified name is an array of characters. The 
	 * first (chars[0]) character is the index of the colon character which 
	 * separates the prefix from the local name. If the index is zero, the 
	 * name does not contain separator or the parser works in the namespace 
	 * unaware mode. The length of qualified name is the length of the array 
	 * minus one. 
	 *
	 * @param ns The true value turns namespace conformance on.
	 * @return The characters of a qualified name.
	 * @exception SAXException  When incorrect character appear in the name.
	 * @exception IOException 
	 */
	private char[] qname(boolean ns)
		throws SAXException, IOException
	{
		mBuffIdx = -1;
		bname(ns);
		char chars[] = new char[mBuffIdx + 1];
		System.arraycopy(mBuff, 0, chars, 0, mBuffIdx + 1);
		return chars;
	}

	/**
	 * Reads the public or/and system identifiers.
	 *
	 * @param inp The input object. 
	 * @exception SAXException 
	 * @exception IOException 
	 */
	private void pubsys(Input inp)
		throws SAXException, IOException
	{
		Pair pair	= pubsys(' ');
		inp.pubid	= pair.name;
		inp.sysid	= pair.value;
		del(pair);
	}

	/**
	 * Reads the public or/and system identifiers.
	 *
	 * @param flag The 'N' allows public id be without system id.
	 * @return The public or/and system identifiers pair.
	 * @exception SAXException 
	 * @exception IOException 
	 */
	private Pair pubsys(char flag)
		throws SAXException, IOException
	{
		Pair   ids	= pair(null);
		String str	= name(false);
		if ("PUBLIC".equals(str) == true) {
			bqstr('i');		// non-CDATA normalization [#4.2.2]
			ids.name = new String(mBuff, 1, mBuffIdx);
			switch (wsskip()) {
			case '\"':
			case '\'':
				bqstr(' ');
				ids.value = new String(mBuff, 1, mBuffIdx);
				break;

			default:
				if (flag != 'N')	// [#4.7]
					panic(FAULT);
				ids.value = null;
				break;
			}
			return ids;
		} else if ("SYSTEM".equals(str) == true) {
			ids.name	= null;
			bqstr(' ');
			ids.value = new String(mBuff, 1, mBuffIdx);
			return ids;
		}
		panic(FAULT);
		return null;
	}

	/**
	 * Reads an attribute value.
	 *
	 * The grammar which this method can read is:<br /> 
	 * <code>eqstr := S &quot;=&quot; qstr</code><br /> 
	 * <code>qstr  := S (&quot;'&quot; string &quot;'&quot;) | 
	 *	('&quot;' string '&quot;')</code><br /> 
	 * This method resolves entities inside a string unless the parser 
	 * parses DTD.
	 *
	 * @param flag The '=' character forces the method 
	 *	to accept the '=' character before quoted string.
	 * @return The name has been read.
	 * @exception SAXException 
	 * @exception IOException 
	 */
	private String eqstr(char flag)
		throws SAXException, IOException
	{
		if (flag == '=') {
			wsskip();
			if (next() != '=')
				panic(FAULT);
		}
		bqstr(flag);
		return new String(mBuff, 1, mBuffIdx);
	}

	/**
	 * Resoves an entity.
	 *
	 * This method resolves built-in and character entity references. It is 
	 * also reports external entities to the application.
	 *
	 * @param flag The 'c' character forces the method 
	 *	to report a skipped entity.
	 * @exception SAXException 
	 * @exception IOException 
	 */
	private void ent(char flag)
		throws SAXException, IOException
	{
		char	ch;
		short	idx	= (short)(mBuffIdx + 1);
		Input	inp = null;
		String	str	= null;
		mESt = 0x100;	// reset the built-in entity recognizer
		bappend('&');
		for (short st = 0; st >= 0;) {
			ch	= next();
			switch (st) {
			case 0:		// the first character of the entity name
			case 1:		// read built-in entity name
				switch (chtyp(ch)) {
				case 'd':
				case '.':
				case '-':
					if (st != 1)
						panic(FAULT);
				case 'a':
				case 'A':
				case '_':
				case 'X':
					bappend(ch);
					eappend(ch);
					st	= 1;
					break;

				case ':':
					if (mIsNSAware != false)
						panic(FAULT);
					bappend(ch);
					eappend(ch);
					st	= 1;
					break;

				case ';':
					if (mESt < 0x100) {
						//		The entity is a built-in entity
						mBuffIdx	= (short)(idx - 1);
						bappend(mESt);
						st	= -1;
						break;
					} else if (mSt == 2) {
						//		In DTD entity declaration has to resolve character 
						//		entities and include "as is" others. [#4.4.7]
						bappend(';');
						st	= -1;
						break;
					}
					//		Convert an entity name to a string
					str	= new String(mBuff, idx + 1, mBuffIdx - idx);
					inp	= (Input)mEnt.get(str);
					//		Restore the buffer offset
					mBuffIdx	= (short)(idx - 1);
					if (inp != null) {
						if (inp.chars == null) {
							//		External entity
							InputSource is = mHand.resolveEntity(inp.pubid, inp.sysid);
							if (is != null) {
								push(new Input(BUFFSIZE_READER));
								setinp(is);
								mInp.pubid = inp.pubid;
								mInp.sysid = inp.sysid;
							} else {
								//		Unresolved external entity
								bflash();
								if (flag != 'c')
									panic(FAULT);	// unknown entity within marckup
								mHand.skippedEntity(str);
							}
						} else {
							//		Internal entity
							push(inp);
						}
					} else {
						//		Unknown or general unparsed entity
						bflash();
						if (flag != 'c')
							panic(FAULT);	// unknown entity within marckup
						mHand.skippedEntity(str);
					}
					st	= -1;
					break;

				case '#':
					if (st != 0)
						panic(FAULT);
					st	= 2;
					break;

				default:
					panic(FAULT);
				}
				break;

			case 2:		// read character entity
				switch (chtyp(ch)) {
				case 'd':
					bappend(ch);
					break;

				case ';':
					//		Convert the character entity to a character
					try {
						ch	= (char)Short.parseShort(
							new String(mBuff, idx + 1, mBuffIdx - idx), 10);
					} catch (NumberFormatException nfe) {
						panic(FAULT);
					}
					//		Restore the buffer offset
					mBuffIdx	= (short)(idx - 1);
					bappend(ch);
					st	= -1;
					break;

				case 'a':
					//		If the entity buffer is empty and ch == 'x'
					if ((mBuffIdx == idx) && (ch == 'x')) {
						st	= 3;
						break;
					}
				default:
					panic(FAULT);
				}
				break;

			case 3:		// read hex character entity
				switch (chtyp(ch)) {
				case 'A':
				case 'a':
				case 'd':
					bappend(ch);
					break;

				case ';':
					//		Convert the character entity to a character
					try {
						ch	= (char)Short.parseShort(
							new String(mBuff, idx + 1, mBuffIdx - idx), 16);
					} catch (NumberFormatException nfe) {
						panic(FAULT);
					}
					//		Restore the buffer offset
					mBuffIdx	= (short)(idx - 1);
					bappend(ch);
					st	= -1;
					break;

				default:
					panic(FAULT);
				}
				break;

			default:
				panic(FAULT);
			}
		}
	}

	/**
	 * Resoves a parameter entity.
	 *
	 * This method resolves a parameter entity references. It is also reports 
	 * external entities to the application.
	 *
	 * @param flag The '-' instruct the method to do not set up surrounding 
	 *	spaces [#4.4.8].
	 * @exception SAXException 
	 * @exception IOException 
	 */
	private void pent(char flag)
		throws SAXException, IOException
	{
		char	ch;
		short	idx	= (short)(mBuffIdx + 1);
		Input	inp = null;
		String	str	= null;
		bappend('%');
		if (mSt != 2)		// the DTD internal subset
			return;			// Not Recognized [#4.4.1]
		//		Read entity name
		bname(false);
		str = new String(mBuff, idx + 2, mBuffIdx - idx - 1);
		if (next() != ';')
			panic(FAULT);
		inp	= (Input)mPEnt.get(str);
		//		Restore the buffer offset
		mBuffIdx	= (short)(idx - 1);
		if (inp != null) {
			if (inp.chars == null) {
				//		External parameter entity
				InputSource is = mHand.resolveEntity(inp.pubid, inp.sysid);
				if (is != null) {
					if (flag != '-')
						bappend(' ');	// tail space
					push(new Input(BUFFSIZE_READER));
					// BUG: there is no leading space! [#4.4.8]
					setinp(is);
					mInp.pubid = inp.pubid;
					mInp.sysid = inp.sysid;
				} else {
					//		Unresolved external parameter entity
					mHand.skippedEntity("%" + str);
				}
			} else {
				//		Internal parameter entity
				if (flag == '-') {
					//		No surrounding spaces
					inp.chIdx = 1;
				} else {
					//		Insert surrounding spaces
					bappend(' ');	// tail space
					inp.chIdx = 0;
				}
				push(inp);
			}
		} else {
			//		Unknown parameter entity
			mHand.skippedEntity("%" + str);
		}
	}

	/**
	 * Recognizes and handles a namespace declaration.
	 *
	 * This method identifies a type of namespace declaration if any and 
	 * puts new mapping on top of prefix stack.
	 *
	 * @param name The attribute qualified name (<code>name.value</code> is a 
	 *	<code>String</code> object which represents the attribute prefix).
	 * @param value The attribute value.
	 * @return <code>true</code> if a namespace declaration is recognized.
	 */
	private boolean isdecl(Pair name, String value)
	{
		if (name.chars[0] == 0) {
			if ("xmlns".equals(name.name) == true) {
				//		New default namespace declaration
				mPref = pair(mPref);
				mPref.value = value;
				mPref.name  = "";
				mPref.chars = NONS;
				return true;
			}
		} else {
			if (name.eqpref(XMLNS) == true) {
				//		New prefix declaration
				int len = name.name.length();
				mPref = pair(mPref);
				mPref.value    = value;
				mPref.name     = name.name;
				mPref.chars    = new char[len + 1];
				mPref.chars[0] = (char)(len + 1);
				name.name.getChars(0, len, mPref.chars, 1);
				return true;
			}
		}
		return false;
	}

	/**
	 * Resolves a prefix.
	 *
	 * @return The namespace assigned to the prefix.
	 * @exception SAXException When mapping for specified prefix is not found.
	 */
	private String rslv(char[] qname)
		throws SAXException
	{
		for (Pair pref = mPref; pref != null; pref = pref.next) {
			if (pref.eqpref(qname) == true)
				return pref.value;
		}
		panic(FAULT);
		return null;
	}

	/**
	 * Skips xml white space characters.
	 *
	 * This method skips white space characters (' ', '\t', '\n', '\r') and 
	 * looks ahead not white space character. 
	 *
	 * @return The first not white space look ahead character.
	 * @exception SAXException When End Of Stream character typed.
	 * @exception IOException 
	 */
	private char wsskip()
		throws SAXException, IOException
	{
		char	ch;
		while (true) {
			ch	= next();
			switch (ch) {
			case ' ':
			case '\t':
			case '\r':
			case '\n':
				continue;

			case EOS:
				panic(FAULT);

			default:
				back();
				return ch;
			}
		}
	}

	/**
	 * Notifies the handler about fatal parsing error.
	 *
	 * @param msg The problem description message.
	 */
	private void panic(String msg)
		throws SAXException
	{
		mHand.fatalError(new SAXParseException(msg, this));
	}

	/**
	 * Reads a qualified xml name. 
	 *
	 * This is low level routine which leaves a qName in the buffer.
	 * The characters of a qualified name is an array of characters. The 
	 * first (chars[0]) character is the index of the colon character which 
	 * separates the prefix from the local name. If the index is zero, the 
	 * name does not contain separator or the parser works in the namespace 
	 * unaware mode. The length of qualified name is the length of the array 
	 * minus one. 
	 *
	 * @param ns The true value turns namespace conformance on.
	 * @exception SAXException  When incorrect character appear in the name.
	 * @exception IOException 
	 */
	private void bname(boolean ns)
		throws SAXException, IOException
	{
		char	ch;
		char	pos	= (char)(mBuffIdx + 1);
		char	idx	= pos;
		short	st	= (short)((ns == true)? 0: 2);
		bappend((char)0);	// default offset to the colon char
		while (st >= 0) {
			ch	= next();
			switch (st) {
			case 0:		// read the first char of the prefix
			case 2:		// read the first char of the suffix
				switch (chtyp(ch)) {
				case 'a':
				case 'A':
				case '_':
				case 'X':
					bappend(ch);
					st++;		// (st == 0)? 1: 3;
					break;

				case ':':
					back();
					st++;		// (st == 0)? 1: 3;
					break;

				default:
					panic(FAULT);
				}
				break;

			case 1:		// read the prefix
			case 3:		// read the suffix
				switch (chtyp(ch)) {
				case 'a':
				case 'A':
				case 'd':
				case '.':
				case '-':
				case '_':
				case 'X':
					bappend(ch);
					break;

				case ':':
					bappend(ch);
					if (ns == true) {
						if (idx != pos)
							panic(FAULT);	// it must be only one colon
						idx = (char)mBuffIdx;
						if (st == 1)
							st = 2;
					}
					break;

				default:
					back();
					mBuff[pos] = idx;
					return;
				}
				break;

			default:
				panic(FAULT);
			}
		}
	}

	/**
	 * Reads a nmtoken. 
	 *
	 * This is low level routine which leaves a nmtoken in the buffer.
	 *
	 * @exception SAXException  When incorrect character appear in the name.
	 * @exception IOException 
	 */
	private void bntok()
		throws SAXException, IOException
	{
		char	ch;
		mBuffIdx = -1;
		bappend((char)0);	// default offset to the colon char
		while (true) {
			ch	= next();
			switch (chtyp(ch)) {
			case 'a':
			case 'A':
			case 'd':
			case '.':
			case ':':
			case '-':
			case '_':
			case 'X':
				bappend(ch);
				break;

			default:
				back();
				return;
			}
		}
	}

	/**
	 * Recognizes a keyword. 
	 *
	 * This is low level routine which recognizes one of keywords in the buffer.
	 * Keyword     Id
	 *  ID       - i
	 *  IDREF    - r
	 *  IDREFS   - R
	 *  ENTITY   - n
	 *  ENTITIES - N
	 *  NMTOKEN  - t
	 *  NMTOKENS - T
	 *  ELEMENT  - e
	 *  ATTLIST  - a
	 *  NOTATION - o
	 *  CDATA    - c
	 *  REQUIRED - Q
	 *  IMPLIED  - I
	 *  FIXED    - F
	 *
	 * @return an id of a keyword or '?'.
	 * @exception SAXException  When incorrect character appear in the name.
	 * @exception IOException 
	 */
	private char bkeyword()
		throws SAXException, IOException
	{
		String str = new String(mBuff, 1, mBuffIdx);
		switch (str.length()) {
		case 2:	// ID
			return ("ID".equals(str) == true)? 'i': '?';

		case 5:	// IDREF, CDATA, FIXED
			switch (mBuff[1]) {
			case 'I':
				return ("IDREF".equals(str) == true)? 'r': '?';
			case 'C':
				return ("CDATA".equals(str) == true)? 'c': '?';
			case 'F':
				return ("FIXED".equals(str) == true)? 'F': '?';
			default:
				break;
			}
			break;

		case 6:	// IDREFS, ENTITY
			switch (mBuff[1]) {
			case 'I':
				return ("IDREFS".equals(str) == true)? 'R': '?';
			case 'E':
				return ("ENTITY".equals(str) == true)? 'n': '?';
			default:
				break;
			}
			break;

		case 7:	// NMTOKEN, IMPLIED, ATTLIST, ELEMENT
			switch (mBuff[1]) {
			case 'I':
				return ("IMPLIED".equals(str) == true)? 'I': '?';
			case 'N':
				return ("NMTOKEN".equals(str) == true)? 't': '?';
			case 'A':
				return ("ATTLIST".equals(str) == true)? 'a': '?';
			case 'E':
				return ("ELEMENT".equals(str) == true)? 'e': '?';
			default:
				break;
			}
			break;

		case 8:	// ENTITIES, NMTOKENS, NOTATION, REQUIRED
			switch (mBuff[2]) {
			case 'N':
				return ("ENTITIES".equals(str) == true)? 'N': '?';
			case 'M':
				return ("NMTOKENS".equals(str) == true)? 'T': '?';
			case 'O':
				return ("NOTATION".equals(str) == true)? 'o': '?';
			case 'E':
				return ("REQUIRED".equals(str) == true)? 'Q': '?';
			default:
				break;
			}
			break;

		default:
			break;
		}
		return '?';
	}

	/**
	 * Reads a single or double quotted string in to the buffer.
	 *
	 * This method resolves entities inside a string unless the parser 
	 * parses DTD.
	 *
	 * @param flag 'c' - CDATA, 'i' - non CDATA, ' ' or '-' - no normalization.
	 * @exception SAXException 
	 * @exception IOException 
	 */
	private void bqstr(char flag)
		throws SAXException, IOException
	{
		Input inp = mInp;	// remember the original input
		mBuffIdx  = -1;
		bappend((char)0);	// default offset to the colon char
		char ch;
		for (short st = 0; st >= 0;) {
			ch = next();
			switch (st) {
			case 0:		// read a single or double quote
				switch (ch) {
				case ' ':
				case '\n':
				case '\r':
				case '\t':
					break;

				case '\'':
					st = 2;	// read a single quoted string
					break;

				case '\"':
					st = 3;	// read a double quoted string
					break;

				default:
					panic(FAULT);
					break;
				}
				break;

			case 2:		// read a single quoted string
			case 3:		// read a double quoted string
				switch (ch) {
				case '\'':
					if ((st == 2) && (mInp == inp))
						st = -1;
					else
						bappend(ch);
					break;

				case '\"':
					if ((st == 3) && (mInp == inp))
						st = -1;
					else
						bappend(ch);
					break;

				case '&':
					ent(' ');
					break;

				case '%':
					pent(flag);
					break;

				case '\r':		// EOL processing [#2.11]
					if (flag != ' ') {
						if (next() != '\n')
							back();
						ch = '\n';
					}
				default:
					//		This implements attribute value normalization as 
					//		described in the XML specification [#3.3.3].
					switch (flag) {
					case 'i':	// non CDATA normalization
						switch (ch) {
						case ' ':
						case '\n':
						case '\t':
							if ((mBuffIdx > 0) && (mBuff[mBuffIdx] != ' '))
								bappend(' ');
							break;

						default:
							bappend(ch);
							break;
						}
						break;

					case 'c':	// CDATA normalization
						switch (ch) {
						case '\n':
						case '\t':
							bappend(' ');
							break;

						default:
							bappend(ch);
							break;
						}
						break;

					default:	// no normalization
						bappend(ch);
						break;
					}
					break;
				}
				break;

			default:
				panic(FAULT);
			}
		}
		//		There is maximum one space at the end of the string in
		//		i-mode (non CDATA normalization) and it has to be removed.
		if ((flag == 'i') && (mBuff[mBuffIdx] == ' '))
			mBuffIdx -= 1;
	}

	/**
	 * Reports characters and empties the parser's buffer.
	 */
	private void bflash()
		throws SAXException
	{
		if (mBuffIdx >= 0) {
			//		Textual data has been read
			mHand.characters(mBuff, 0, (mBuffIdx + 1));
			mBuffIdx = -1;
		}
	}

	/**
	 * Appends a character to parser's buffer.
	 *
	 * @param ch The character to append to the buffer.
	 */
	private void bappend(char ch)
	{
		try {
			mBuffIdx++;
			mBuff[mBuffIdx] = ch;
		} catch (Exception exp) {
			//		Double the buffer size
			char buff[] = new char[mBuff.length << 1];
			System.arraycopy(mBuff, 0, buff, 0, mBuff.length);
			mBuff			= buff;
			mBuff[mBuffIdx]	= ch;
		}
	}

	/**
	 * Recognizes the built-in entities <i>lt</i>, <i>gt</i>, <i>amp</i>, 
	 * <i>apos</i>, <i>quot</i>. 
	 * The initial state is 0x100. Any state belowe 0x100 is a built-in 
	 * entity replacement character. 
	 *
	 * @param ch the next character of an entity name.
	 */
	private void eappend(char ch)
	{
		switch (mESt) {
		case 0x100:	// "l" or "g" or "a" or "q"
			switch (ch) {
			case 'l':	mESt	= 0x101; break;
			case 'g':	mESt	= 0x102; break;
			case 'a':	mESt	= 0x103; break;
			case 'q':	mESt	= 0x107; break;
			default:	mESt	= 0x200; break;
			}
			break;
		case 0x101:	// "lt"
			mESt = (ch == 't')? '<': (char)0x200;
			break;
		case 0x102:	// "gt"
			mESt = (ch == 't')? '>': (char)0x200;
			break;
		case 0x103:	// "am" or "ap"
			switch (ch) {
			case 'm':	mESt	= 0x104; break;
			case 'p':	mESt	= 0x105; break;
			default:	mESt	= 0x200; break;
			}
			break;
		case 0x104:	// "amp"
			mESt = (ch == 'p')? '&': (char)0x200;
			break;
		case 0x105:	// "apo"
			mESt = (ch == 'o')? (char)0x106: (char)0x200;
			break;
		case 0x106:	// "apos"
			mESt = (ch == 's')? '\'': (char)0x200;
			break;
		case 0x107:	// "qu"
			mESt = (ch == 'u')? (char)0x108: (char)0x200;
			break;
		case 0x108:	// "quo"
			mESt = (ch == 'o')? (char)0x109: (char)0x200;
			break;
		case 0x109:	// "quot"
			mESt = (ch == 't')? '\"': (char)0x200;
			break;
		case '<':	// "lt"
		case '>':	// "gt"
		case '&':	// "amp"
		case '\'':	// "apos"
		case '\"':	// "quot"
			mESt	= 0x200;
		default:
			break;
		}
	}

	/**
	 * Sets up a new input source on the top of the input stack.
	 * Noet, the first byte returned by the entity's byte stream has to be the 
	 * first byte in the entity. However, the parser does not expect the byte 
	 * order mask in both cases when encoding is provided by the input source.
	 *
	 * @param is A new input source to set up.
	 * @exception IOException If any IO errors occur.
	 * @exception SAXException If the input source cannot be read.
	 */
	private void setinp(InputSource is)
		throws SAXException, IOException
	{
		Reader reader = null;
		mChIdx   = 0;
		mChLen   = 0;
		mChars   = mInp.chars;
		mInp.src = null;
		if (is.getCharacterStream() != null) {
			//		Ignore encoding in the xml text decl. 
			reader = is.getCharacterStream();
			xml(reader);
		} else if (is.getByteStream() != null) {
			if (is.getEncoding() != null) {
				//		Ignore encoding in the xml text decl.
				String encoding = is.getEncoding().toUpperCase();
				if (encoding.equals("UTF-16"))
					reader = bom(is.getByteStream(), 'U');	// UTF-16 [#4.3.3]
				else
					reader = enc(encoding, is.getByteStream());
				xml(reader);
			} else {
				//		Get encoding from BOM or the xml text decl.
				reader = bom(is.getByteStream(), ' ');
				if (reader == null) {
					//		Encoding is defined by the xml text decl.
					reader = enc("UTF-8", is.getByteStream());
					reader = enc(xml(reader), is.getByteStream());
				} else {
					//		Encoding is defined by the BOM.
					xml(reader);
				}
			}
		} else {
			//		There is no support for public/system identifiers.
			panic(FAULT);
		}
		mInp.src   = reader;
		mInp.pubid = is.getPublicId();
		mInp.sysid = is.getSystemId();
	}

	/**
	 * Determines the entity encoding.
	 *
	 * This method gets encoding from Byte Order Mask [#4.3.3] if any. 
	 * Note, the first byte returned by the entity's byte stream has 
	 * to be the first byte in the entity. Also, there is no support 
	 * for UCS-4.
	 *
	 * @param is A byte stream of the entity.
	 * @param hint An encoding hint, character U means UTF-16.
	 * @return a reader constructed from the BOM or UTF-8 by default.
	 * @exception SAXException 
	 * @exception IOException 
	 */
	private Reader bom(InputStream is, char hint)
		throws SAXException, IOException
	{
		int val = is.read();
		switch (val) {
		case 0xef:		// UTF-8
			if (hint == 'U')	// must be UTF-16
				panic(FAULT);
			if (is.read() != 0xbb) 
				panic(FAULT);
			if (is.read() != 0xbf) 
				panic(FAULT);
			return new ReaderUTF8(is);

		case 0xfe:		// UTF-16, big-endian
			if (is.read() != 0xff) 
				panic(FAULT);
			return new ReaderUTF16(is, 'b');

		case 0xff:		// UTF-16, little-endian
			if (is.read() != 0xfe) 
				panic(FAULT);
			return new ReaderUTF16(is, 'l');

		case -1:
			mChars[mChIdx++] = EOS;
			return new ReaderUTF8(is);

		default:
			if (hint == 'U')	// must be UTF-16
				panic(FAULT);
			//		Read the rest of UTF-8 character
			switch (val & 0xf0) {
			case 0xc0:
			case 0xd0:
				mChars[mChIdx++] = (char)(((val & 0x1f) << 6) | (is.read() & 0x3f));
				break;

			case 0xe0:
				mChars[mChIdx++] = (char)(((val & 0x0f) << 12) | 
					((is.read() & 0x3f) << 6) | (is.read() & 0x3f));
				break;

			case 0xf0:	// UCS-4 character
				throw new UnsupportedEncodingException();

			default:
				mChars[mChIdx++] = (char)val;
				break;
			}
			return null;
		}
	}

	/**
	 * Parses the xml text declaration.
	 *
	 * This method gets encoding from the xml text declaration [#4.3.1] if any. 
	 * The method assumes the buffer (mChars) is big enough to accomodate whole 
	 * xml text declaration.
	 *
	 * @param reader is entity reader.
	 * @return The xml text declaration encoding or default UTF-8 encoding.
	 * @exception SAXException 
	 * @exception IOException 
	 */
	private String xml(Reader reader)
		throws SAXException, IOException
	{
		String	str	= null;
		String	enc	= "UTF-8";
		char	ch;
		int		val;
		short	st;
		//		Read the xml text declaration into the buffer
		if (mChIdx != 0) {
			//		The bom method have read ONE char into the buffer. 
			st = (short)((mChars[0] == '<')? 1: -1);
		} else {
			st = 0;
		}
		while (st >= 0) {
			ch = ((val = reader.read()) >= 0)? (char)val: EOS;
			mChars[mChIdx++] = ch;
			switch (st) {
			case 0:		// read '<' of xml declaration
				switch (ch) {
				case '<':
					st = 1;
					break;

				case 0xfeff:	// the byte order mask
					ch = ((val = reader.read()) >= 0)? (char)val: EOS;
					mChars[mChIdx - 1] = ch;
					st = (short)((ch == '<')? 1: -1);
					break;

				default:
					st = -1;
					break;

				}
				break;

			case 1:		// read '?' of xml declaration
				st = (short)((ch == '?')? 2: -1);
				break;

			case 2:		// read 'x' of xml declaration
				st = (short)(((ch == 'x') || (ch == 'X'))? 3: -1);
				break;

			case 3:		// read 'm' of xml declaration
				st = (short)(((ch == 'm') || (ch == 'M'))? 4: -1);
				break;

			case 4:		// read 'l' of xml declaration
				st = (short)(((ch == 'l') || (ch == 'L'))? 5: -1);
				break;

			case 5:		// read white space after 'xml'
				switch (ch) {
				case ' ':
				case '\t':
				case '\r':
				case '\n':
					st = 6;
					break;

				default:
					st = -1;
					break;
				}
				break;

			case 6:		// read content of xml declaration
				if (ch == '?')
					st = 7;
				break;

			case 7:		// read '>' after '?' of xml declaration
				st = (short)((ch != '>')? 6: -2);
				break;

			default:
				panic(FAULT);
				break;
			}
		}
		mChLen = mChIdx;
		mChIdx = 0;
		//		If there is no xml text declaration, the encoding is default.
		if (st == -1) {
			return enc;
		}
		mChIdx = 5;		// the first white space after "<?xml"
		//		Parse the xml text declaration
		for (st = 0; st >= 0;) {
			ch = next();
			switch (st) {
			case 0:		// skip spaces after the xml declaration name
				if (chtyp(ch) != ' ') {
					back();
					st = 1;
				}
				break;

			case 1:		// read xml declaration version
			case 2:		// read xml declaration encoding or standalone
			case 3:		// read xml declaration standalone
				switch (chtyp(ch)) {
				case 'a':
				case 'A':
				case '_':
					back();
					str	= name(false).toLowerCase();
					if ("version".equals(str) == true) {
						if (st != 1)
							panic(FAULT);
						if ("1.0".equals(eqstr('=')) != true)
							panic(FAULT);
						st = 2;
					} else if ("encoding".equals(str) == true) {
						if (st != 2)
							panic(FAULT);
						enc = eqstr('=').toUpperCase();
						st  = 3;
					} else if ("standalone".equals(str) == true) {
						if (st == 1)
							panic(FAULT);
						str = eqstr('=');
						// BUG: the value has to be analized by the parser [#5.1]
						st  = 4;
					} else {
						panic(FAULT);
					}
					break;

				case ' ':
					break;

				case '?':
					if (st == 1)
						panic(FAULT);
					back();
					st = 4;
					break;

				default:
					panic(FAULT);
				}
				break;

			case 4:		// end of xml declaration
				switch (chtyp(ch)) {
				case '?':
					if (next() != '>')
						panic(FAULT);
					if (mSt == 0)		// the begining of the document
						mSt	= 1;		// misc before DTD
					st = -1;
					break;

				case ' ':
					break;

				default:
					panic(FAULT);
				}
				break;

			default:
				panic(FAULT);
			}
		}
		return enc;
	}

	/**
	 * Sets up the document reader.
	 *
	 * @param name an encoding name.
	 * @param is the document byte input stream.
	 * @return a reader constructed from encoding name and input stream.
	 * @exception UnsupportedEncodingException 
	 */
	private Reader enc(String name, InputStream is)
		throws java.io.UnsupportedEncodingException
	{
		//		DO NOT CLOSE current reader if any! 
		if (name.equals("UTF-8"))
			return new ReaderUTF8(is);
		else if (name.equals("UTF-16LE"))
			return new ReaderUTF16(is, 'l');
		else if (name.equals("UTF-16BE"))
			return new ReaderUTF16(is, 'b');
		else
			return new InputStreamReader(is, name);
	}

	/**
	 * Sets up current input on the top of the input stack.
	 *
	 * @param inp A new input to set up.
	 */
	private void push(Input inp)
	{
		mInp.chLen	= mChLen;
		mInp.chIdx	= mChIdx;
		inp.next	= mInp;
		mInp		= inp;
		mChars		= inp.chars;
		mChLen		= inp.chLen;
		mChIdx		= inp.chIdx;
	}

	/**
	 * Restores previous input on the top of the input stack.
	 */
	private void pop()
	{
		if (mInp.src != null) {
			try { mInp.src.close(); } catch (IOException ioe) {}
			mInp.src = null;
		}
		mInp	= mInp.next;
		if (mInp != null) {
			mChars	= mInp.chars;
			mChLen	= mInp.chLen;
			mChIdx	= mInp.chIdx;
		} else {
			mChars	= null;
			mChLen	= 0;
			mChIdx	= 0;
		}
	}

	/**
	 * Maps a character to it's type.
	 *
	 * Possible character type values are:<br /> 
	 * - ' ' for any kind of white space character;<br /> 
	 * - 'a' for any lower case alphabetical character value;<br /> 
	 * - 'A' for any upper case alphabetical character value;<br /> 
	 * - 'd' for any decimal digit character value;<br /> 
	 * - 'z' for any character less then ' ' except 
	 * '\t', '\n', '\r';<br /> 
	 * - 'X' for any not ASCII character.<br /> 
	 * An ASCII (7 bit) character which does not fall in any category listed 
	 * above is mapped to it self. 
	 *
	 * @param ch The character to map.
	 * @return The type of character.
	 * @exception SAXException When End Of Stream character typed.
	 */
	private char chtyp(char ch)
		throws SAXException
	{
		if (ch < 0x80)
			return (char)asctyp[ch];
		if (ch == EOS)
			panic(FAULT);
		return 'X';
	}

	/**
	 * Retrives the next character in the document.
	 *
	 * @return The next character in the document.
	 */
	private char next()
		throws java.io.IOException
	{
		if (mChIdx >= mChLen) {
			if (mInp.src == null) {
				pop();		// remove internal entity
				return next();
			}
			//		Read new portion of the document characters
			int Num = mInp.src.read(mChars, 0, mChars.length);
			if (Num < 0) {
				if (mInp != mDoc) {
					pop();	// restore the previous input
					return next();
				} else {
					mChars[0] = EOS;
					mChLen    = 1;
				}
			}
			else
				mChLen = (char)Num;
			mChIdx = 0;
		}
		return mChars[mChIdx++];
	}

	/**
	 * Puts back the last read character.
	 *
	 * This method <strong>MUST NOT</strong> be called more then once after 
	 * each call of {@link #next next} method.
	 */
	private void back()
		throws SAXException
	{
		if(mChIdx <= 0)
			panic(FAULT);
		mChIdx--;
	}

	/**
	 * Finds a pair in the pair chain by a qualified name.
	 *
	 * @param chain The first element of the chain of pairs.
	 * @param qname The qualified name.
	 * @return A pair with the specified qualified name or null.
	 */
	private Pair find(Pair chain, char[] qname)
	{
		for (Pair pair = chain; pair != null; pair = pair.next) {
			if (pair.eqname(qname) == true)
				return pair;
		}
		return null;
	}

	/**
	 * Provedes an instance of a pair.
	 *
	 * @param next The reference to a next pair.
	 * @return An instance of a pair.
	 */
	private Pair pair(Pair next)
	{
		Pair pair;

		if (mDltd != null) {
			pair  = mDltd;
			mDltd = pair.next;
		} else {
			pair  = new Pair();
		}
		pair.next = next;

		return pair;
	}

	/**
	 * Deletes an instance of a pair.
	 *
	 * @param pair The pair to delete.
	 * @return A reference to the next pair in a chain.
	 */
	private Pair del(Pair pair)
	{
		Pair next = pair.next;

		pair.name  = null;
		pair.value = null;
		pair.chars = null;
		pair.list  = null;
		pair.next  = mDltd;
		mDltd      = pair;

		return next;
	}
}
