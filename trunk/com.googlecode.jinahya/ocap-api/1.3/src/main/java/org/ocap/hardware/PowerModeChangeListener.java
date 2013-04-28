package org.ocap.hardware;

import java.util.EventListener;

/**
 * The callback interface to be implemented by classes that wish to receive
 * notification when the power mode of the Host Devices changes (for example
 * when the user presses the Power button).
 */
public interface PowerModeChangeListener extends EventListener
{
    
    
   /**
    * Called when the power mode changes (for example from full to low power). 
    *
    *@see Host#FULL_POWER
    *@see Host#LOW_POWER
    */
    void powerModeChanged(int newPowerMode);
    
}
