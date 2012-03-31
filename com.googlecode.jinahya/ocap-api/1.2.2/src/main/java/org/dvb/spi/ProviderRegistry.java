

package org.dvb.spi;

/**
  * Registry of providers.
  * @since MHP 1.1.3
  **/

public class ProviderRegistry {

    /**
     * This constructor is provided for use by implementations and by other
     * specifications that extend this class.   It is not to be used by
     * normal applications.
     **/
    protected ProviderRegistry() {
    }

	/**	
	 * Return the singleton provider registry as seen by the calling application.
	 * @return the provider registry
	 */
    public static ProviderRegistry getInstance() {
	return null;
    }

    /**
     * Registers a provider.  Note that providers might be installed
     * "automatically" by the terminal, e.g. due to signalling.
     * @param p the provider to register
     * @throws IllegalArgumentException if Provider does not export a
     *		valid set of services as determined by 
     *		Provider.getServiceProviderInterfaces(), or if
     *		the provider does not have have a non-null Xlet context.
     * @throws ProviderFailedInstallationException if the organisation_id 
     * 	      in the name of the provider does not match the 
     *	      organisation_id in a certificate which can authenticate 
     *	      the provider class.
     * @throws SecurityException if the caller, for all of the SPIs
     * implemented by the provider, does not have a ProviderPermission
     * whose name is the fully qualified name of the class returned
     * by Provider getServiceProviderInterfaces  and whose action
     * is "xlet".
     *
     * 	@see org.dvb.spi.Provider#getServiceProviderInterfaces()
     * 	@see org.dvb.spi.XletBoundProvider#getBoundXletContext()
     * 	@see org.dvb.spi.XletBoundProvider#getBoundPBPXletContext()
     **/
    public void registerXletBound(XletBoundProvider p) 
    	throws ProviderFailedInstallationException
    {
    }

    /**
     * Registers a provider.  Note that providers might be installed
     * "automatically" by the terminal, e.g. due to signalling.
     * @param p the provider to register
     * @throws IllegalArgumentException if Provider does not expoort a
     *		valid set of services as determined by 
     *		Provider.getServiceProviderInterfaces()
     * @throws ProviderFailedInstallationException if the organisation_id 
     * 	      in the name of the provider does not match the 
     *	      organisation_id in a certificate which can authenticate 
     *	      the provider class.
     *
     * @throws SecurityException if the caller, for all of the SPIs
     * implemented by the provider, does not have a ProviderPermission
     * whose name is the fully qualified name of the class returned
     * by Provider getServiceProviderInterfaces  and whose action
     * is "system".
     *
     * 	@see org.dvb.spi.Provider#getServiceProviderInterfaces()
     **/
    public void registerSystemBound(SystemBoundProvider p) 
        throws ProviderFailedInstallationException
    {
    }


    /**
     * Unregister a provider.  Xlets that "manually" register a provider
     * using one of the register methods of this class shall unregister
     * that provider before returning from a successful destroyXlet
     * call.
     * @param p the provider to unregister
     * @see javax.tv.xlet.Xlet#destroyXlet(boolean)
     **/
    public void unregister(Provider p) {
    }

    /**
     * Return the names of all installed providers.
     * These are the names returned by the getName methods on those
     * Providers.
     * Provider names shall be encoded as defined for permission request
     * file in the main body of the present document.
     * For example "0x0000000B.EMV_PK11.VISA_REVOLVER".
     *
     * @see org.dvb.spi.Provider#getName()
     *
     * @return the names of all installed providers
     */
    public String[] getInstalledProviders(){return null;}

    /**
     * Return the version of an installed provider.
     *
     * @see org.dvb.spi.Provider#getVersion()
     *
     * @param provider the name of a provider as returned by the method
     * getInstalledProviders
     * @return the version of the specified provider
     * @throws IllegalArgumentException if the provider name is not one 
     *	       of those installed, i.e. is not one returned from a call 
     *	       to getInstalledProviders
     **/
    public String getProviderVersion(String provider){return null;}

}

