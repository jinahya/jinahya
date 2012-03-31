package org.dvb.si;

/**
  * This interface represents information about transport streams that has been
  * retrieved from a BAT table. All descriptor accessing methods return
  * descriptors retrieved from a BAT table. 
  * Methods in SIBouquet for retrieving transport streams return
  * objects that implement this interface.
  */

public interface SITransportStreamBAT extends SITransportStream {

    /**
    * Get the identification of the bouquet this transport stream is part of.
    * @return The bouquet identification identifier.
    */
  public int getBouquetID();
}

