/*
 * Created on Feb 12, 2007
 */
package org.ocap.net;

import java.net.MalformedURLException;
import java.net.URL;

import org.davic.net.InvalidLocatorException;
import org.davic.net.Locator;

/**
 * A concrete implementation of <code>Locator</code> that encapsulates a URL into an object.
 * <p>
 * Instances of this class MAY be used to reference any resource that is referencable 
 * using an instance of <code>URL</code>.
 * 
 * @see URL
 * 
 * @author Aaron Kamienski
 */
public class URLLocator extends Locator
{
    /**
     * Construct an <code>URLLocator</code> encapsulating the given URL.
     * <p>
     * The accepted syntax SHALL be identical to that accepted by {@link URL#URL(String)}.
     *
     * @param url a URL string
     * 
     * @throws InvalidLocatorException if <code>URL(String)</code> would throw {@link MalformedURLException}
     */
    public URLLocator(String url) throws InvalidLocatorException
    {
        super(url);
    }
    
    /**
     * Construct an <code>URLLocator</code> encapsulating the given URL.
     * <p>
     * This SHALL be equivalent to <code>new URLLocator(url.toString())</code>.
     *
     * @param url the URL expressed as an instance of <code>URL</code>
     * 
     * @see #URLLocator(String)
     */
    public URLLocator(URL url)
    {
        super(url.toString());
    }
}
