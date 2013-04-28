

   package org.dvb.dsmcc;

   import java.io.*;
   import org.davic.net.*;


/**
  * A ServiceXFRException is thrown when a DSMCC Object can not be loaded in the 
  * current ServiceDomain but is available in an alternate ServiceDomain 
  * (i.e. for an object Carousel, the IOR of the object or one of its
  *  parent directories contains a Lite Option Profile Body).
  * There is no implicit mounting by the implementation of the carousel 
  * that actually contain the object. This exception is not thrown if the 
  * Service Domain that actually contains the DSMCCObject is already mounted.
  *
  */

   public class ServiceXFRException extends DSMCCException {
   
   /**
   * Creates a ServiceXFRException object.
   *
   * @param aService Locator of the Service
   * @param carouselId Carousel Identifier
   * @param pathName pathName of the object in the alternate ServiceDomain
   */
      public ServiceXFRException(org.davic.net.Locator aService, int carouselId,
                        String pathName)
      {
      }
   
   /**
   * Creates a ServiceXFRException object.
   *
   * @param NSAPAddress The NSAP Address of a ServiceDomain as defined
   * in ISO/IEC 13818-6
   * @param pathName pathName of the object in the alternate ServiceDomain
   */
      public ServiceXFRException(byte[] NSAPAddress, String pathName)
      {
      }
   
   /**
   * This method is used to get the alternate ServiceDomain which contains
   * the object requested.
   * @return the address of an alternate ServiceDomain where the object can
   * be found.
   */
      public ServiceXFRReference getServiceXFR()
      { return null;
      }
   }

