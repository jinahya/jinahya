/*
 * TokenInfo.java
 *
 * Created on 31 janvier 2005, 14:15
 */

package org.dvb.security.pkcs11;

/**
 * This interface is used to get information about a PKCS11 token.
 * The information returned here is the equivalent to the one returned by the PKCS11 function C_GetTokenInfo.
 * 
 */
public interface TokenInfo {
    
    /**
     * This method returns the label of the PKCS11 CK_TOKEN_INFO.
     */
    public String getLabel();
    
     /**
     * This method returns the manufacturerID of the PKCS11 CK_TOKEN_INFO.
     */
    public String getManufacturerID();
    
     /**
     * This method returns the model of the PKCS11 CK_TOKEN_INFO.
     */
    public String getModel();
    
     /**
     * This method returns the serial Number of the PKCS11 CK_TOKEN_INFO.
     */
    public String getSerialNumber();
    
     /**
     * This method returns the flags of the PKCS11 CK_TOKEN_INFO.
     */
    public int getFlags();
}


