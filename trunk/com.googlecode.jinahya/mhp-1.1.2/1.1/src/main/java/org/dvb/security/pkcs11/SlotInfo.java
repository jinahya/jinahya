/*
 * SlotInfo.java
 *
 * Created on 31 janvier 2005, 14:14
 */

package org.dvb.security.pkcs11;

/**
 * This interface is used to retrieve information about a PKCS11 slot.
 * The information returned here is the equivalent to the one returned by the PKCS11 function C_GetSlotInfo.
 * 
 */
public interface SlotInfo {
    
        /**
         * This method returns the slot identifier associated this slot information.
         */
        public int getSlotID();
        
        /**
         * This method returns the slotDescription of the PKCS11 CK_SLOT_INFO.
         */
        public String getSlotDescription();
        
        /**
         * This method returns the ManufacturerID of the PKCS11 CK_SLOT_INFO.
         */
        public String getManufacturerID();
        
        /**
         * This method returns the flags of the PKCS11 CK_SLOT_INFO.
         */
        public int getFlags();
}


