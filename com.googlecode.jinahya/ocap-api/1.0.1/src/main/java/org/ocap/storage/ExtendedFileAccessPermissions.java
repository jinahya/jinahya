package org.ocap.storage;

import org.dvb.io.persistent.FileAccessPermissions;

/**
 * This class extends {@link FileAccessPermissions} to let granting applications provide read
 * and write file access to applications that have an organization identifier different from
 * a granting application.
 */
public class ExtendedFileAccessPermissions extends FileAccessPermissions
{
    /**
     * This constructor encodes application, application organization, and world file
     * access permissions as a set of booleans, and other organizations file access
     * permissions as arrays of granted organization identifiers.
     *
     * @param readWorldAccessRight read access for all applications
     * @param writeWorldAccessRight write access for all applications
     * @param readOrganisationAccessRight read access for applications with the same
     *      organization as the granting application.
     * @param writeOrganisationAccessRight write access for applications with the same
     *      organization as the granting application.
     * @param readApplicationAccessRight read access for the owner.
     * @param writeApplicationAccessRight write access for the owner.
     * @param otherOrganisationsReadAccessRights array of other organization identifiers with
     *      read access.  Applications with an organization identifier matching one of these
     *      organization identifiers will be given read access.
     * @param otherOrganisationsWriteAccessRights array of other organisation identifiers with
     *      write access.  Applications with an organization identifier matching one of these
     *      organization identifiers will be given write access.

     */
    public ExtendedFileAccessPermissions(
        boolean readWorldAccessRight, boolean writeWorldAccessRight,
        boolean readOrganisationAccessRight, boolean writeOrganisationAccessRight,
        boolean readApplicationAccessRight, boolean writeApplicationAccessRight,
        int [] otherOrganisationsReadAccessRights, int [] otherOrganisationsWriteAccessRights)
    {
        super(readWorldAccessRight,
              writeWorldAccessRight,
              readOrganisationAccessRight,
              writeOrganisationAccessRight,
              readApplicationAccessRight,
              writeApplicationAccessRight);
    }

    /**
     * This method allows modification of the permissions on this instance of
     * the ExtendedFileAccessPermission class.
     *
     * @param readWorldAccessRight read access for all applications
     * @param writeWorldAccessRight write access for all applications
     * @param readOrganisationAccessRight read access for organisation
     * @param writeOrganisationAccessRight write access for organisation
     * @param readApplicationAccessRight read access for the owner
     * @param writeApplicationAccessRight write access for the owner
     * @param otherOrganisationsReadAccessRights array of other organisation identifiers with
     *      read access.  Applications with an organization identifier matching one of these
     *      organization identifiers will be given read access.
     * @param otherOrganisationsWriteAcessRights array of other organisation identifiers with
     *      write access.  Applications with an organization identifier matching one of these
     *      organization identifiers will be given write access.
     */
    public void setPermissions(
        boolean readWorldAccessRight, boolean writeWorldAccessRight,
        boolean readOrganisationAccessRight, boolean writeOrganisationAccessRight,
        boolean readApplicationAccessRight, boolean writeApplicationAccessRight,
        int [] otherOrganisationsReadAccessRights, int [] otherOrganisationsWriteAcessRights)
    {
    }

    /**
     * Gets the array of organization identifiers with read permission.
     *
     * @return Array of organization identifiers with read permission.
     */
    public int [] getReadAccessOrganizationIds()
    {
        return null;
    }

    /**
     * Gets the array of organization identifiers with write permission.
     *
     * @return Array of organization identifiers with write permission.
     */
    public int [] getWriteAccessOrganizationIds()
    {
        return null;
    }
}
