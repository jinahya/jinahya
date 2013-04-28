package org.ocap.system;

import java.util.Hashtable;
import java.io.File;
import java.io.IOException;

/**
 * This class represents a manager for registered APIs that can be registered with an
 * implementation by a privileged application.
 */
public abstract class RegisteredApiManager
{
    /**
     * Protected constructor.
     */
    protected RegisteredApiManager()
    {
    }

    /**
     * Gets the singleton instance of the Registered API manager.
     *
     * @return The Registered API manager.
     */
    public static RegisteredApiManager getInstance()
    {
        return null;
    }

    /**
     * Registers an API with the implementation.
     * <p>
     * If the name and version number matches an API already registered, this function does
     * nothing (successfully). Matches for both name and version are based on exact
     * case sensitive comparisons.
     * <p>
     * If the name matches an API already registered,
     * and the version number is different, the implementation SHALL:
     * remove the existing API before installing the new API.  The removal SHALL obey
     * the semantics specified for the unregister() method.  If the installation fails then
     * the previously registered API SHALL NOT be removed.  The removal of the
     * previous API and installation of the new API SHALL be one atomic operation.
     * (Note: This implies that the terminal MUST download and authenticate all files required
     * for the new API, and only if this succeeds can it then remove the old API &
     * install the new API.  Application authors that do not need this behavior
     * should note that unregistering the old API before registering a new version
     * may reduce the memory usage of this operation and is strongly recommended).
     * <p>
     * Paths in the SCDF are relative to the directory containing the SCDF.
     * The priority field specified in the SCDF is ignored.
     *
     * @param name Name of the registered API.
     * @param version Version of the registered API.
     * @param scdf Path to the shared classes descriptor file.
     * @param storagePriority Storage priority of classes in the SCDF.
     *
     * @throws IllegalArgumentException if storagePriority is not a valid value as
     *      defined in chapter 12.
     * @throws IllegalStateException if the API to be updated is in use by any application.
     * @throws java.io.IOException if the SCDF or any file listed in it does not exist,
     *       cannot be loaded, or are not correctly signed.  Also thrown if the SCDF
     *       is not the correct format and cannot be parsed.
     * @throws SecurityException if the calling application does not have
     *      MonitorAppPermission("registeredapi.manager").
     */
    public abstract void register(String name,
                                  String version,
                                  File scdf,
                                  short storagePriority)
    throws IOException;

    /**
     * Unregisters an API from the implementation.  Removes all of the shared class files
     * associated with the registered API from persistent and volatile memory.  Removes the
     * registered API from the registered API list.
     *
     * @param name Name of the registered API to unregister.
     *
     * @throws IllegalArgumentException if no registered API with the name parameter has been
     *      registered.
     * @throws IllegalStateException if the API to be unregistered is in use by any application.
     * @throws SecurityException if the calling application does not have
     *      MonitorAppPermission("registeredapi.manager").
     */
    public abstract void unregister(String name);

    /**
     * Gets a list of registered APIs.
     *
     * Note that this is intended for use by applications that manage registered APIs.
     * Applications that use a registered API should call getUsedNames().
     *
     * @return An array of registered API names.
     * @throws SecurityException if the calling application does not have
     *      MonitorAppPermission("registeredapi.manager").
     */
    public abstract String[] getNames();

    /**
     * Gets the version of a registered API, or null if it is not registered.
     *
     * @param name the name of the registered API.
     * @return the version of the registered API, or null if it is not registered.
     * @throws SecurityException if the calling application does not have
     *      MonitorAppPermission("registeredapi.manager") or
     *      RegisteredApiUserPermission(name).
     */
    public abstract String getVersion(String name);

    /**
     * Gets a list of registered APIs that are in use by the caller.
     *
     * @return An array of registered API names that are in use by the caller.
     */
    public abstract String[] getUsedNames();

}
