
package org.dvb.security.provider;

import java.security.Provider;

/**
 * This class gives access to those cryptographic service providers visible to an application. 
 * The cryptographic service providers visible to an application shall include the following;<ul>
 * <li>The installed providers as returned by java.security.Security.getProviders
 * <li>Xlet bound providers which are bound to the calling Xlet
 * </ul>
 * Where providers of the same name are visible to an application through both the
 * above mechanisms, the Xlet bound provider shall always be used by this class,
 * even if the installed provider has a higher version number. Installed providers
 * can always be accessed through java.security.Security.getProviders.
 */
public class ProviderManager {
    
    /**
     * Creates a new instance of ProviderManager.
     * This constructor is provided for the use of implementations and specifications which extend 
     * this specification. Applications shall not define sub-classes of this class. Implementations 
     * are not required to behave correctly if any such application defined sub-classes are used.
     */
    protected ProviderManager() {
    }
    
    /**
     * Gets the singleton instance of the ProviderManager
     * @return the ProviderManager.
     */
    public static ProviderManager getInstance()
    {
        return(null);
    }

	/**
	 * Returns a visible provider with the specified name.
	 * If no provider with that name is visible to the calling
	 * application then null shall be returned.
	 *
	 * @param name the name of the provider
	 * @return a provider or null
	 */
	public Provider getProvider(String name) {return null;}

	/**
	 * Returns an array containing all the providers visible to
	 * the calling application. 
	 * @return an array of providers
	 */
	public Provider[] getProviders() { return null; }    
}


