package org.dvb.media;

/**
  * This class represents a permission to access the drip feed mode.
  * 
  */

public class DripFeedPermission extends java.security.BasicPermission
{
  /**
    * Create a new DripFeedPermission. 
    * @param name the name string is currently unused and should be empty
    */
  public DripFeedPermission(String name) {super(name);}
  
  /**
    * Create a new DripFeedPermission. This constructor is used by the policy class 
    * to instantiate new permission objects.
    *
    * @param name The name string is currently unused and should be empty
    * @param actions The actions string is currently unused 
    * and should be null. 
    */
  public DripFeedPermission(String name, String actions) {super(name,actions);}

  /**
   * Checks if the specified permission is "implied" by this object.
   * <p>
   * Since name and actions are not used, the only check needed is
   * whether p is also a DripFeedPermission.
   * @param p the permission to check against.
   * @return true if the passed permission is equal to or implied by
   * this permission, false otherwise.
   */
   public boolean implies (java.security.Permission p) {
     return false;
   }

}


