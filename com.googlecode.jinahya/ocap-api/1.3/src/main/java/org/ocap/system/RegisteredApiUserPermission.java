package org.ocap.system;

/**
 * The RegisteredApiUserPermission class represents permission for an
 * application to use a specific registered API.  When granted one of
 * these permissions is created for each registered API the application
 * is given access to based on a "registeredapi.user" entry in the 
 * application's PRF and where the name matches an
 * ocap_j_registered_api_descriptor.
 */
public final class RegisteredApiUserPermission extends java.security.BasicPermission
{

    /**
     * Creates a new RegisteredApiUserPermission with the specified name.
     *
     * @param name The name of the registered API.
     */
    public RegisteredApiUserPermission (String name)
    {
        super("registeredapi.user." + name);
    }

    /**
     * Creates a new RegisteredApiUserPermission with the specified name.
     *
     * @param name The name of the registered API.
     * @param actions This parameter is ignored.
     */
    public RegisteredApiUserPermission (String name, String actions)
    {
        this(name);
    }
}
