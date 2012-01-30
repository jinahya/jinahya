package org.davic.media;

/**
 * Defines a JMF <code>MediaLocator</code> which can easily be created from
 * an instance of <code>org.davic.net.Locator</code>.
 */

public class MediaLocator extends javax.media.MediaLocator
{
	/**
	 * Create a MediaLocator from a DAVIC locator
	 * @param locator the DAVIC locator
 	 */
	public MediaLocator(org.davic.net.Locator locator)
	{
		super(locator.toString());
	}
}

