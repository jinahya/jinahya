/*
 * ErrorEvent.java
 * Created on February 20, 2004, 1:56 PM
 */

package org.ocap.system.event;

/**
 * This class represents an event returned by the system when an uncaught exception or
 * implementation error is encountered, or by an application that wishes to log an error or
 * an informational event.  Error event type codes are defined in this class.
 * Applications may use the error type codes in this class or proprietary class
 * codes that are understood by the network.
 * <P>
 * The class takes a {@link java.lang.Throwable} object parameter in one constructor, but the
 * Throwable object cannot be returned from this class due to implementation and security
 * issues.  The Throwable object attributes (i.e., message and stacktrace) can be retrieved
 * from this class by calling corresponding get methods, which in-turn call the Throwable
 * object get methods.  However, the implementation MUST NOT allow the Throwable object get
 * methods to block indefinitely when called and MUST NOT wait longer than 30 seconds for them
 * to return.
 * </P>
 */
public class ErrorEvent extends SystemEvent
{
    /* Error Event type codes */

    //**** Application information event codes ****
    /**
     * Application informational event that doesn't fit into any other given category.
     */
    public final static int APP_INFO_GENERAL_EVENT = BEGIN_APP_INFO_RESERVED_EVENT_TYPES;

    //**** Application recoverable error codes ****
    /**
     * Application recoverable error that doesn't fit into any other given category.
     */
    public final static int APP_REC_GENERAL_ERROR = BEGIN_APP_REC_ERROR_RESERVED_EVENT_TYPES;

    /**
     * Application recoverable error - a Java Throwable caught by an exception, but that can
     * be recovered from by the application, or a Throwable that was created by an
     * application due to detection of a recoverable event.
     */
    public final static int APP_REC_JAVA_THROWABLE = BEGIN_APP_REC_ERROR_RESERVED_EVENT_TYPES + 1;

    //**** Application catastrophic error codes ****
    /**
     * Application catastrophic error that doesn't fit into any other given category.
     */
    public final static int APP_CAT_GENERAL_ERROR = BEGIN_APP_CAT_ERROR_RESERVED_EVENT_TYPES;

    //**** System information event codes ****
    /**
     * System informational event that doesn't fit into any other given category.
     */
    public final static int SYS_INFO_GENERAL_EVENT = BEGIN_SYS_INFO_RESERVED_EVENT_TYPES;

    //**** System recoverable error codes ****
    /**
     * System error that doesn't fit into any other given category.
     */
    public final static int SYS_REC_GENERAL_ERROR = BEGIN_SYS_REC_ERROR_RESERVED_EVENT_TYPES;

    //**** System catastrophic error codes ****
    /**
     * System catastrophic error that doesn't fit into any other given category.
     */
    public final static int SYS_CAT_GENERAL_ERROR = BEGIN_SYS_CAT_ERROR_RESERVED_EVENT_TYPES;

    /**
     * Java Throwable thrown by a call made by an application but not caught by the application.
     * This event is generated by the implementation, but indicates that an application cannot
     * continue normal operations.
     */
    public final static int SYS_CAT_JAVA_THROWABLE = BEGIN_SYS_CAT_ERROR_RESERVED_EVENT_TYPES + 1;


    /**
     * Class constructor specifying the event type code and readable message.
     *
     * @param typeCode - Unique error type code.
     *
     * @param message - Readable error message.
     *
     * @throws IllegalArgumentException when called by an application and the typeCode
     *      is not in one of the following ranges:
     *      {@link #BEGIN_APP_INFO_EVENT_TYPES} to {@link #END_APP_INFO_EVENT_TYPES}, or
     *      {@link #BEGIN_APP_REC_ERROR_EVENT_TYPES} to {@link #END_APP_REC_ERROR_EVENT_TYPES}, or
     *      {@link #BEGIN_APP_CAT_ERROR_EVENT_TYPES} to {@link #END_APP_CAT_ERROR_EVENT_TYPES}.
     */
    public ErrorEvent(int typeCode, String message)

    {
        super(typeCode, message);
    }

    /**
     * Class constructor specifying the event type code, and throwable condition.  The message
     * is derived from the Throwable object.  The
     *
     * @param typeCode - The unique error type code.
     * @param throwable - A throwable object that was generated by the implementation or
     *      an application in response to an informational or error event, or by the
     *      implementation when a call made by an application throws an exception that
     *      isn't caught by the application.
     *
     * @throws IllegalArgumentException when called by an application and the typeCode
     *      is not in one of the following ranges:
     *      {@link #BEGIN_APP_INFO_EVENT_TYPES} to {@link #END_APP_INFO_EVENT_TYPES}, or
     *      {@link #BEGIN_APP_REC_ERROR_EVENT_TYPES} to {@link #END_APP_REC_ERROR_EVENT_TYPES}, or
     *      {@link #BEGIN_APP_CAT_ERROR_EVENT_TYPES} to {@link #END_APP_CAT_ERROR_EVENT_TYPES}.
     */
    public ErrorEvent(int typeCode, Throwable throwable)

    {
        super(typeCode);
    }

    /**
     * This constructor is provided for internal use by OCAP implementations;
     * applications SHOULD NOT call it.
     *
     * @param typeCode - The unique error type code.
     * @param message - Readable message specific to the event generator.
     * @param stacktrace - Stacktrace taken from a Throwable object or null if no Throwable
     *      used.
     * @param throwableClasses - The class hierarchy list from a Throwable object or null if
     *      no Throwable used.
     * @param date - Event date in milli-seconds from midnight January 1, 1970 GMT.
     * @param appId - The Id of the application logging the event.
     *
     * @throws SecurityException if this constructor is called by any application.
     */
    public ErrorEvent(int typeCode,
                      String message,
                      String stacktrace,
                      String [] throwableClasses,
                      long date,
                      org.dvb.application.AppID appId){
        super(typeCode, message, date, appId);
    }

    /**
     * Gets the stack trace from the Throwable object if a Throwable object was passed to the
     * appropriate constructor.
     *
     * @return The stack trace from the Throwable object, or null if no Throwable object
     *      is available, or if the message cannot be extracted from the Throwable object
     *      (perhaps Throwable.printStackTrace() threw an exception or blocked).
     */
    public String getStackTrace()
    {
        return null;
    }

    /**
     * Gets the readable message String that was passed to a constructor explicitly or within
     * a Throwable object.
     *
     * @return The readable message, if the message cannot be extracted from the Throwable object
     *      (perhaps Throwable.getMessage() threw an exception or blocked).
     */
    public String getMessage()
    {
        return null;
    }

    /**
     * Gets the class hierarchy from the Throwable object that was passed to the corresponding
     * constructor.  Each String in the array will be a fully qualified class name.  The first
     * will be the full class name (with package name) of the Throwable object passed to this class.
     * Each subsequent String shall contain the name of a super class up to but not
     * including java.lang.Object.
     *
     * @return The stack trace from the Throwable object, or null if no Throwable object is
     *      available.
     */
    public String [] getThrowableClasses()
    {
        return null;
    }
}
