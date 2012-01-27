package org.dvb.si;

/**
 * This class contains SI related utility functions. 
 */

public class SIUtil
{
	// Constructor definition to indicate it is a non public constructor,
	// not part of the API definition, required for javadoc formatting.
	SIUtil() {}
	
	/**
	 * This method converts a text string that is coded according
	 * to annex A of the DVB-SI specification (EN 300 468) to a
	 * Java String object.
	 * <p>
	 * The text that must be converted is contained in 'dvbSIText'
	 * from index 'offset' to index 'offset+length-1' (inclusive).
	 * <p>
	 * If the text that must be converted is not validly coded
	 * according to annex A of the DVB-SI specification, then
	 * the result is undefined.
	 * 
	 * @param dvbSIText          The byte array that contains the string
	 *                           that must be converted.
	 * @param offset             The offset indicates the start of the
	 *                           DVB-SI text in dvbSIText.
	 * @param length             Length of the DVB-SI text in bytes.
	 * @param emphasizedPartOnly If emphasizedPartOnly is true, then only
	 *                           the text that is marked as emphasized
	 *                           (using the character emphasis on [0x86]
	 *                           and character emphasis off [0x87] control
	 *                           codes) will be returned. Otherwise,
	 *                           the character emphasis codes will be ignored,
	 *                           and all of the converted text will be
	 *                           returned.
	 * 
	 * @return                   The converted text.
	 * 
	 * @exception SIIllegalArgumentException  thrown if offset and/or
	 *                offset+length-1 is not a valid index in dvbSIText.
	 */
	public static String convertSIStringToJavaString(
		byte[] dvbSIText, int offset, int length,
		boolean emphasizedPartOnly)
		throws SIIllegalArgumentException
	{
		return null;	
	}
}

