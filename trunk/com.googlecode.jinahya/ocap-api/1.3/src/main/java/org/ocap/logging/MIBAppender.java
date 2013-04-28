package org.ocap.logging;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;

/**
 * The <i>MIBAppender</i> appends events to a circular queue which is made
 * available at a given OID.  The [MIB-HOST] specification defines a table
 * specifically for this purpose, for which an application can use the OID
 * of the ocStbHostSystemLoggingTable.
 *
 */
public class MIBAppender extends AppenderSkeleton
{

    /**
     * Closes the MIB appender.
     */
    public void close()
    {
    }

    /**
     * Appends a logging event to the MIB Appender.  If the OID set for this
     * MIB Appender is not an OID to a known circular logging table this method
     * does nothing successfully.
     */
    public void append(LoggingEvent event)
    {
    }

    /**
     * Sets the OID that this MIBAppender should be attached to. This is a
     * required value for the <i>MIBAppender</i> and there is no default.
     *
     * @param  oid The OID for the MIB node where the queue of logger messages
     *      will be accessible.
     *
     */
    public void setOid(String oid)
    {
    }

    public String getOid()
    {
        return null;
    }

    /**
     * Set the size of the circular queue in number of messages. (Default: 10)
     *
     * @param  size  the size to make the circular queue
     */
    public void setSize(int size)
    {
    }

    public int getSize()
    {
        return 0;
    }

    /**
     * Set the minimum priority level that this Appender will actually execute
     * for. Although a Logger may be configured to send a message based upon its
     * current level, this level will further restrict the verbosity of this
     * specific Appender. By default, this is set to {@link Level#ALL#toString()}.
     *
     * @param levelMin The minimum priority level that this appender will
     *      actually log.
     */
    public void setLevelMin(Level levelMin)
    {
    }

    public Level getLevelMin()
    {
        return null;
    }

    /**
     * Accessor reporting if MIBAppender requires a layout
     * @return true
     */
    public boolean requiresLayout()
    {
        return false;
    }
}
