
package org.ocap.hardware.device;

/**
 * Thrown when an application attempts to query/set/get a feature
 * not supported on the device.
 */
public class FeatureNotSupportedException extends java.lang.Exception
{
   /**
    * Creates an FeatureNotSupportedException object. See the class
    * description for details of constructor parameters and default
    * values.  
    */
   public FeatureNotSupportedException()
   {	
     throw new RuntimeException("Not implemented");
   }

   /**
    * Creates an FeatureNotSupportedException object with a specified
    * reason string.
    *
    * @param message the reason why the exception was raised 
    */
   public FeatureNotSupportedException(String message)
    {
     throw new RuntimeException("Not implemented");
   }
}




