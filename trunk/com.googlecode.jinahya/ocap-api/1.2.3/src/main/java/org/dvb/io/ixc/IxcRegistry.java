package org.dvb.io.ixc;

import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;

/** 
 * This is the bootstrap mechanism for obtaining references to
 * remote objects residing in other Xlets executing on the same
 * MHP terminal, using a URL-like syntax. The identification of 
 * a remote
 * object is given using a syntax indicating the organisation
 * ID and application ID:
 *
 * <br>  /organisation_id/application_id/name
 * <br>  organisation_id = the organisation ID of the Xlet,
 *                     as signalled in the application_identifier
 *                     record, defined in the MHP specification.
 * <br>  application_id = the application ID of the Xlet, as
 *                     signalled in the application_identifier
 *                     record, defined in the MHP specification.
 * <br>  name           = the name under which the remote object was
 *                        exported.
 *
 * <p>
 * The organisation ID and the application ID shall each be encoded as a
 * hexadecimal string, as would be accepted by
 * java.lang.Integer.parseInt(String s, 16).
 * <p>
 * When RMI is used to communicate over a network, stubs generated by a
 * tool like rmic are often required. This is not necessary for inter-xlet
 * communication initiated with IxcRegistry. If such stubs are present,
 * they shall be ignored.
 * <p>
 * Similarly, network RMI objects often extend the class 
 * server.RemoteObject, in order to get appropriate implementations
 * for Object.hashCode(), Object.equals(), and Object.toString(). Overriding
 * Object's implementation of these methods in this way is not necessary for 
 * inter-xlet communication initiated with IxcRegistry, although it is not
 * harmful. Note that the class server.RemoteObject is not required
 * in all MHP profiles.
 **/

public class IxcRegistry {

    //
    // Disallow anyone from creating an instance of IxcRegistry
    //
    private IxcRegistry() { }
	

    /**
     * Returns a remote object previously exported by an Xlet that
     * has not been destroyed.
     * The identification of a remote
     * object is given using a syntax indicating the organisation
     * ID and application ID:
     *
     * <br>  /organisation_id/application_id/name
     * <br>  organisation_id = the organisation ID of the Xlet,
     *                     as signalled in the application_identifier
     *                     record.
     * <br>  application_id = the application ID of the Xlet, as
     *                     signalled in the application_identifier
     *                     record.
     * <br>  name           = the name under which the remote object was
     *                        exported.
     *
     * <p>
     * The organisation ID and the application ID shall each be encoded as a
     * hexadecimal string, as would be accepted by
     * java.lang.Integer.parseInt(String s, 16). If the caller is not
     * authorized to import a given object due to the security policy, then
     * this API will behave as though the object had not been exported, that
     * is, a NotBoundException shall be thrown.
     *
     * @param xc    The context of the current Xlet (that is, the Xlet
     *		    importing the object).
     * @param path   A file pathname-like string identifying the Xlet and the 
     *               name of the object to be imported.
     *
     * @return 	   A remote object
     *
     * @exception NotBoundException    
     *			If the path is not currently bound.
     * @exception RemoteException
     *			If a remote stub class cannot be generated for
     *			the object being imported.
     * @exception java.lang.IllegalArgumentException
     *			If the path is not formatted in the syntax given
     *                  above.
     * @exception NullPointerException   if path is null
     **/
    public static Remote lookup(javax.tv.xlet.XletContext xc, String path)
        throws NotBoundException, RemoteException
    {
        return null;
    }
    
    /**
     * Exports an object under a given name in the namespace of
     * an Xlet. The name can be any valid non-null String. No
     * hierarchical namespace exists, e.g. the names "foo" and "bar/../foo"
     * are distinct. If the exporting xlet has been destroyed, this method
     * may fail silently.

     * @param xc    The context of the Xlet exporting the object.
     * @param name  The name identifying the object.
     * @param obj   The object being exported
     * 
     * @exception AlreadyBoundException
     *			if this Xlet has previously exported an object
     *			under the given name.
     *
     * @exception NullPointerException   if xc, name or obj is null
     **/
    public static void bind(javax.tv.xlet.XletContext xc,
    			    String name, Remote obj) 
	    throws AlreadyBoundException {
    }
    
    /**
     * Unbind the name.
     *
     * @param xc    The context of the Xlet that exported the object to
     *		    be unbound.
     * @param name  The name identifying the object.
     * 
     * @exception NotBoundException
     *			if this is not currently any object exported by
     *			this Xlet under the given name.
     *
     * @exception NullPointerException   if xc or name is null
     **/
    public static void unbind(javax.tv.xlet.XletContext xc,
    			    String name)
		throws NotBoundException {
    }

    /**
     * Rebind the name to a new object in the context of an Xlet;
     * replaces any existing binding.
     * The name can be any valid non-null String. No
     * hierarchical namespace exists, e.g. the names "foo" and "bar/../foo"
     * are distinct. If the exporting xlet has been destroyed, this method
     * may fail silently.

     *
     * @param xc    The context of the Xlet that exported the object.
     * @param name  The name identifying the object.
     * @param obj   The object being exported
     * 
     * @exception NullPointerException   if xc, name or obj is null
     **/
    public static void rebind(javax.tv.xlet.XletContext xc,
    			      String name, Remote obj)  {
    }

    /**
     * Returns an array of string path objects available in the
     * registry. The array contains a snapshot of the names present
     * in the registry that the current Xlet would be allowed to import using
     * IxcRegistry.lookup.
     *
     * @param xc    The context of the current Xlet.
     *
     * @return A non-null array of strings containing a snapshot of the 
     *		path names of all objects available to the caller in 
     *		this registry.
     *
     * @see IxcRegistry#lookup(javax.tv.xlet.XletContext,String)
     **/
    public static String[] list(javax.tv.xlet.XletContext xc) { return null;
    }
    

}

