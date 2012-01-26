package org.ocap.dvr;

import javax.tv.service.selection.ServiceContextEvent;
import javax.tv.service.selection.ServiceContext;

/**
 * The parent class for TimeShiftBuffer events.
 */
public class TimeShiftEvent extends javax.tv.service.selection.ServiceContextEvent
{
    /**
     * A time-shift buffer or recording was found for attachment to the
     * <code>ServiceContext</code>.
     */
    public final static int TIME_SHIFT_BUFFER_FOUND = 1;

    /**
     * A time-shift buffer or recording was not found for attachment to the
     * <code>ServiceContext</code>
     */
    public final static int NO_TIME_SHIFT_BUFFER = 2;
    
    /**
     * The implementation was forced to change time-shift properties due to
     * signaling.
     */
    public final static int TIME_SHIFT_PROPERTIES_CHANGED = 3;


   /**
    * Constructor for this event.
    *
    * @param source The object associated with this event.
    * @param reason The reason code for this event.  See constants in this class
    *       for possible values.
    * 
    * @throws IllegalArgumentException if the reason code is not a value matching
    *       one of the possible constants.
    */
   public TimeShiftEvent(ServiceContext source, int reason)
   {
       super(source);
   }

   /**
    * Gets the reason for this event.
    * 
    * @return The reason code for this event.  See constants in this class for
    *       possible return values; see constants in this class.
    */
   public int getReason()
   {
       return NO_TIME_SHIFT_BUFFER;
   }

   /**
    * Reports the <code>ServiceContext</code> that generated the event.
    *
    * @return The <code>ServiceContext</code> that generated the event.
    */
   public ServiceContext getServiceContext()
   {
       return (ServiceContext)getSource();
   }

}


