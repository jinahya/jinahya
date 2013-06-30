package org.ocap.dvr;

import java.util.EventListener;

/**
 * Listener for Recording Alerts.
 */
public interface RecordingAlertListener extends EventListener
{
    /**
     * Notifies the <code>RecordingAlertListener</code> that a scheduled
     * acitivity is about to happen.
     *
     * @param e The generated event.
     */
    public void recordingAlert(RecordingAlertEvent e);
}

