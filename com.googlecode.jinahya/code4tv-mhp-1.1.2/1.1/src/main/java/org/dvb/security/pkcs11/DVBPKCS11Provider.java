/*
 * DVBPKCS11Provider.java
 *
 * Created on 31 janvier 2005, 14:09
 */

package org.dvb.security.pkcs11;

import org.dvb.security.*;
import org.dvb.auth.callback.*;

import java.io.IOException;

/**
 * This class implements a PKCS11 security provider. It can be used in 
 * the following security related packages: <ul>
 * <li>In java.security for MessageDigest, SecureRandom, Signature, KeyPairGenerator and KeyStore related classes.
 * <li>In javax.crypto (JCE) for encryption and decryption.
 * </ul>
 * Providers have a slot identifier associated with them identifying each smart card reader slot. 
 * These are numbered starting from zero. For details, see the PKCS 11 specification.
 */
public abstract class DVBPKCS11Provider extends AuthProvider {
    
    /** Creates a new instance of DVBPKCS11Provider 
    * @throws SecurityException when called with an appID not allowed by the DVBPKCS11 implementation
    */
    protected DVBPKCS11Provider(String name, double version, String info, org.dvb.application.AppID appID) throws SecurityException
    {
        super(name, version, info);
    }
    
    /** This method returns the PKCS11 slot identifier currently associated with this provider.
     * By default the slot identifier is defined by the property "dvb.security.pkcs11.defaultSlotId" 
     * It can be changed by calling setSlotId.
     * @return a slot identifier.
     */
    public abstract int getSlotId();
    
    /**
     * This method can be used to change the slot identifier used by the provider. 
     * The slot can only be changed when the provider is not logged into the token.
     * @param slotId a slot identifier.
     * @exception IOException this exception is thrown if this method is called when the provider is 
     * logged into the token.
     * @throws IllegalArgumentException if the slot does not exist
     */
    public abstract void setSlotId(int slotId) throws IOException, IllegalArgumentException;
                 
    /** This method is used to get the list of PKCS11 Slot available for this provider. 
     * It is equivalent to the PKCS11 C_GetSlotList function to get the list of slot and C_GetSlotInfo to retreive
     * slot information.
     * @param tokenPresent boolean indicating if the returned list includes only the slots with a 
     * token present or all slots.
     * @return the list of slot.
     */
    public SlotInfo[] getSlotList(boolean tokenPresent) { return null ; }
    
    /**
     * This method is used to retreive information about a PKCS11 token in a given slot.
     * It is equivalent to the PKCS11 C_GetTokenInfo function to get this information.
     * @throws IllegalArgumentException if the slot does not exist or there is no token in the slot
     * @return a TokenInfo object which gives a subset of the PKCS11 CK_TOKEN_INFO.
     */
    public abstract TokenInfo getTokenInfo(int slotId) throws IllegalArgumentException;
    
     /**
      * This method is used to explicitly log into a PKCS11 token.
      * A call to this method will be equivalent to a call to the PKCS11 function C_Login. 
      * The PKCS11 user type will always be CKU_USER.
      * @param identity This parameter is not used. It is kept to be compatible with the J2SE 5.0 
      * @param handler This parameter is used to get the pin code needed to login. The callbackHandler
      * will get a PasswordCallback in which it should put the pin code.
      * This parameter may be null in which case the handler that was previously 
      * set by setCallbackHandler is used.
      * @exception LoginException This exception is under the conditions when C_Login would return an error return an error.
      * @throws NullPointerException if the CallbackHandler parameter is null and either no 
      * previous call to setCallbackHandler has occurred or the last call to that method set the handler to null.
      */
    public abstract void login(java.security.Principal identity, CallbackHandler handler) throws LoginException, NullPointerException;
        
    /**
     * This method is called to explicitly log out from a PKCS11 token.
     * A call to this method will be equivalent to a call to the PKCS11 funtion C_Logout.
     * @exception LoginException This exception is thrown under the conditions when C_Logout would return an error.
     */
    public abstract void logout() throws LoginException;
    
    
    /** 
     * This method is used to set a default callback handler for the provider.
     * @param handler a Callback handler that will be used to get the pin code when 
     * the login method is called with a null handler.
     */
    public abstract void setCallbackHandler(CallbackHandler handler);

}


