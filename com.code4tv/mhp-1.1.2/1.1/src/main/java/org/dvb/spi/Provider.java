
package org.dvb.spi;

/**
  * Abstract interface for all DVB providers. 
  **/

public interface Provider {

    /** 
     * @return Descriptive name of this provider.  This can be used
     *       for debugging purposes, e.g. in a crash log.  The name 
     *	     shall be encoded as defined for the permission request
     *	     file in the main body of the present document.
     *       For example "0x0000000B.EMV_PK11.VISA_REVOLVER". 
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
     * list shall be at least one element long, and shall contain onlyl
     * valid SPI interfaces defined by the terminal specification.
     * Unknown interfaces (e.g. application-defined interfaces) shall
     * be rejected, as documented in ProviderRegistry.
     *
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

