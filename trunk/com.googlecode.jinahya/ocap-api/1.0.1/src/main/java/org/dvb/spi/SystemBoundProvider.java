

package org.dvb.spi;

/**
  * Interface for providers that are registered from one Xlet,
  * but provide system-wide services.  Such providers shall be used by
  * the system in such a way that there is no sharing of mutable object
  * instances between the xlet that installs the provider, and any xlet
  * that uses the services.
  *
  * @see XletBoundProvider
  * @since MHP 1.1.3
  **/

public interface SystemBoundProvider extends Provider {

}

