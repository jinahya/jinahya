
package com.sun.ukit.jaxp;

import java.io.Reader;

/**
 * A parsed entity input state.
 *
 * This class represents a parsed entity input state. The parser uses 
 * an instance of this class to manage input.
 */

/* package */ class Input
{
	/** The entity public identifier or null. */
	public String  pubid;

	/** The entity systen identifier or null. */
	public String  sysid;

	/** The entity reader. */
	public Reader  src;

	/** The character buffer. */
	public char[]  chars;

	/** The length of the character buffer. */
	public char    chLen;

	/** The index of the next character to read. */
	public char    chIdx;

	/** The next input in a chain. */
	public Input   next;

	/**
	 * Constructor.
	 *
	 * @param buffsize The input buffer size.
	 */
	public Input(short buffsize)
	{
		chars = new char[buffsize];
		chLen = (char)chars.length;
	}

	/**
	 * Constructor.
	 *
	 * @param buff The input buffer.
	 */
	public Input(char[] buff)
	{
		chars = buff;
		chLen = (char)chars.length;
	}

	/**
	 * Constructor.
	 */
	public Input()
	{
	}
}
