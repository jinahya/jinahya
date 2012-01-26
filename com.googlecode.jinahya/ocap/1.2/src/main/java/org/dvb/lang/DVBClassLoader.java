package org.dvb.lang;

import java.net.URL;

/**
 * This class loader is used to load classes and resources from
 * a search path of URLs referring to locations where Java
 * class files may be stored. <p>
 * 
 * The classes that are loaded are by default only allowed to load
 * code through the parent classloader, or from the URLs specified
 * when the DVBClassLoader was created.
 */


public abstract class DVBClassLoader extends java.security.SecureClassLoader {

    /**
     * Constructs a new DVBClassLoader for the given URLs. The
     * URLs will be searched in the order specified for classes
     * and resources. 
     * <p>
     * If there is a security manager, this method first calls the
     * security manager's <code>checkCreateClassLoader</code>
     * method to ensure creation of a class loader is allowed.
     *
     * @param urls the URLs from which to load classes and resources
     * @throws SecurityException if a security manager exists and its
     *                  <code>checkCreateClassLoader</code> method
     *                  doesn't allow creation of a class loader.
     * @see  SecurityManager#checkCreateClassLoader
     */
    public DVBClassLoader(URL[] urls){ }

    /**
     * Constructs a new DVBClassLoader for the given URLs. The
     * URLs will be searched in the order specified for classes
     * and resources.
     * <p>
     * If there is a security manager, this method first calls the
     * security manager's <code>checkCreateClassLoader</code>
     * method to ensure creation of a class loader is allowed.
     *
     * @param urls the URLs from which to load classes and resources
     * @param parent the parent classloader for delegation
     * @throws SecurityException if a security manager exists and its
     *                  <code>checkCreateClassLoader</code> method
     *                  doesn't allow creation of a class loader.
     * @see  SecurityManager#checkCreateClassLoader
     */
    public DVBClassLoader(URL[] urls, ClassLoader parent){ 
    }

    /**
     * Finds and loads the class with the specified name from the URL 
     * search path. Any URLs are searched 
     * until the class is found.
     * @throws ClassNotFoundException if the named class could not be
     * found.
     * @param name the name of the class.
     * @return the resulting class.
     */
    public Class findClass(String name) throws ClassNotFoundException {
	return null;
    }

    /**
     * Creates a new instance of DVBClassLoader for the specified URLs.
     * If a security manager is installed, the <code>loadClass</code>
     * method of the DVBClassLoader returned by this method will invoke
     * the <code>SecurityManager.checkPackageAccess</code> method before
     * loading the class.
     *
     * @param urls the URLs to search for classes and resources.
     * @return the resulting class loader
     */
    public static DVBClassLoader newInstance(URL[] urls){
	return null;
    }
    
    /**
     * Creates a new instance of DVBClassLoader for the specified URLs.
     * If a security manager is installed, the <code>loadClass</code>
     * method of the DVBClassLoader returned by this method will invoke
     * the <code>SecurityManager.checkPackageAccess</code> method before
     * loading the class.
     * 
     * @param urls the URLs to search for classes and resources.
     * @param parent the parent class loader for delegation.
     * @return the resulting class loader
     */
    public static DVBClassLoader newInstance(URL[] urls,
                                             ClassLoader parent){
	return null;

    }
   
   }
