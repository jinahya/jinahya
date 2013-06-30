package org.ocap.dvr;

import org.ocap.shared.dvr.RecordedService;

/**
 * This interface represents a RecordedService in OCAP. The object returned when
 * an applications calls the getService method on a RecordingRequest will be an instance of
 * this interface.
 */
public interface OcapRecordedService extends RecordedService
{
    /**
     * Get the bit-rate used for encoding and storage of this recorded service.
     *
     * @return Bit-rate in bytes per second.
     */
    public long getRecordedBitRate();

    /**
     * Gets the size of the recording in bytes.
     *
     * @return Space occupied by the recording in bytes.
     */
    public long getRecordedSize();

    /**
     * Determines if the recording can be decrypted by the implementation
     * on the current network.
     *
     * @return True if the recording can be decrypted, otherwise returns false.
     **/
    public boolean isDecryptable();

    /**
     * Determines if the recording has a format which can be decoded
     * for presentation by the implementation, e.g., the bit rate,
     * resolution, and encoding are supported.
     *
     * @return True if the recording can be decoded, otherwise returns false.
     **/
    public boolean isDecodable();
}
