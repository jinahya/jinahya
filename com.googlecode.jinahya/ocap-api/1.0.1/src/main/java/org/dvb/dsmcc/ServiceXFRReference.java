


   package org.dvb.dsmcc;

   import java.io.*;
   import org.davic.net.*;


/**
  * A ServiceXFRReference object is used when a DSMCC Object can not
  * be loaded in the current ServiceDomain but is available
  * in an alternate ServiceDomain.
  * Instances of this class are just containers. The parameters passed are merely 
  * stored and returned by the access methods. It is the responsibility of the 
  * platform when generating instances to use correct values.
  *
  */

   public class ServiceXFRReference  {
   
   
   /**
   * Creates a ServiceXFRReference object.
   *
   * @param serviceLocator Locator of the Service
   * @param carouselId Carousel Identifier
   * @param pathName pathName of the object in the alternate ServiceDomain
   */
      public ServiceXFRReference(org.davic.net.Locator serviceLocator, 
                        int carouselId, String pathName) {
      }
   
   /**
   * Creates a ServiceXFRReference object.
   *
   * @param nsapAddress The NSAP Address of a ServiceDomain as defined
   * in ISO/IEC 13818-6
   * @param pathName pathName of the object in the alternate ServiceDomain
   */
      public ServiceXFRReference(byte[] nsapAddress, String pathName)
      {
      }
   
   /**
   * This method returns the Locator of the Service for an Object Carousel.
   *
   * @return the Locator of the Service for an Object Carousel.
   * This method returns null, if the ServiceDomain is not associated with 
   * an Object Carousel. In this case the NSAP address must be used instead.
   */  
      public org.davic.net.Locator getLocator()
      {return null;
      }
   
   /**
    * This method returns the carousel identifier. If the object was constructed
    * using the constructor which includes a carousel ID or if it was
    * constructed using the constructor which includes an NSAP address and that 
    * NSAP address contains a carouselID then this method shall return that
    * carousel ID otherwise this method shall return -1.
    *
    * @return the carousel identifier or -1.
    */  
      public int getCarouselId()
      { return 0;
      }
   
   /**
   * This method returns the pathname of the object in the alternate ServiceDomain.
   *
   * @return the pathname of the object in the alternate ServiceDomain.
   */  
      public String getPathName()
      {return null;
      }
   
   /**
   * This method returns the NSAP Address of a ServiceDomain as defined
   * in ISO/IEC 13818-6. If the object was constructed using an NSAP address
   * then this method shall return the NSAP address passed into the
   * constructor. If the object was constructed with a locator and a carouselID 
   * then this method shall return an NSAP address derived from this information 
   * when locator is an instance of org.davic.net.dvb.DVBLocator. Otherwise 
   * this method shall return null
   * @return the NSAP Address of a ServiceDomain as defined in ISO/IEC 13818-6 or null
   */ 
      public byte[] getNSAPAddress()
      {return null;
      }
   
   }

