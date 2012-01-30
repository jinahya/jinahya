
package org.dvb.spi;

/**
  * Abstract interface for all DVB providers. 
  * @since MHP 1.1.3
  **/

public interface Provider {

    /** 
     * Returns the name of this provider. This can be used
     *       for debugging purposes, e.g. in a crash log.  The name 
     *	     shall be encoded as defined for the permission request
     *	     file in the main body of the present document.
     *       For example "0x0000000B.EMV_PK11.VISA_REVOLVER". 
     * @return the name
     **/
    public String getName();

    /**
     * Return the version of this provider.  The format of this string
     * is not specified.
     *
     * @return the version of this provider.
     **/
    public String getVersion();


    /**
     * Gives a list of the SPI's implemented by this provider.  The
     * list shall be at least one element long, and shall contain only
     * valid SPI interfaces defined by the terminal specification.
     * Unknown interfaces (e.g. application-defined interfaces) shall
     * be rejected, as documented in ProviderRegistry.
     * @return a list of SPIs
     * @see org.dvb.spi.ProviderRegistry#registerXletBound(org.dvb.spi.XletBoundProvider)
     * @see org.dvb.spi.ProviderRegistry#registerSystemBound(org.dvb.spi.SystemBoundProvider)
     **/
    public Class[] getServiceProviderInterfaces();

    /** 
     * Called by the system when this provider is registered.  
     **/
    public abstract void providerRegistered();

    /** 
     * Called by the system when this provider is unregistered. 
     **/
    public abstract void providerUnregistered();
}

