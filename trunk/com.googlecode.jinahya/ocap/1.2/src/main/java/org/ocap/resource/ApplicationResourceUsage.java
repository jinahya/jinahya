package org.ocap.resource;

import org.ocap.resource.ResourceUsage;

/**
 * This interface represents a ResourceUsage corresponding to a resource
 * explicitly reserved by an application by successfully calling one of the
 * following OCAP calls:
 * org.davic.mpeg.sections.SectionFilterGroup.attach(TransportStream, ResourceClient, Object)
 * org.davic.net.tuning.NetworkInterfaceController.reserve(NetworkInterface, Object)
 * org.davic.net.tuning.NetworkInterfaceController.reserveFor(Locator, Object)
 * org.havi.ui.HBackgroundDevice.reserveDevice(ResourceClient)
 * org.havi.ui.HGraphicsDevice.reserveDevice(ResourceClient)
 * org.havi.ui.HVideoDevice.reserveDevice(ResourceClient)
 * org.ocap.media.VBIFilterGroup.attach(ServiceContext serviceContext, ResourceClient client, Object requestData)
 *
 * An object implementing this interface should be used by the implementation
 * to represent the ResourceUsage corresponding to a reserved resource when
 * the ResourceContentionHandler.resolveResourceContention() method is invoked.
 */
public interface ApplicationResourceUsage extends ResourceUsage
{
}
