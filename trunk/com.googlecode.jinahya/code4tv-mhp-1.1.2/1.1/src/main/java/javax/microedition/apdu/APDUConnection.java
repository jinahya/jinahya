package javax.microedition.apdu;

/**
 * 
 * This interface defines the APDU (Application Protocol Data Unit) connection. J2ME applications can use this
connection to communicate with applications on a smart card using APDU protocol. ISO 7816-4 defines the
APDU protocol as an application-level protocol between a smart card and an application on the device. There
are two types of APDU messages: command APDUs and response APDUs. Command APDUs are sent to the
smart card by J2ME applications. Response APDUs are the messages received from the smart cards. For more
information on APDU protocol see the ISO 7816-4 specifications.

Also J2ME applications can use getATR method in this interface to obtain information regarding an Answer
To Reset (ATR) returned by the smart card on card reset and use the enterPin method to ask the user to enter
PIN which is sent to the smart card for verification.

The methods of APDUConnection are not synchronized. The only method that can be called safely in another
thread is close. When close is invoked on a connection that is executing in another thread, any pending I/O
method MUST throw an InterruptedIOException. If an application terminates without calling close on the open
connection, the implementation SHOULD perform the close operation automatically in order to recover
resources, such as the logical channel.

Creating an APDU Connection
An APDU connection is created by passing a generic connection URI string with a card application identifier
(AID) and optionally the slot in which the card is inserted, to the Connector.open method. For example, the
connection string:
apdu:0;target=A0.0.0.67.4.7.1F.3.2C.3

indicates that the connection is to be established with a target application having AID A0.0.0.67.4.7.1F.3.2C.3,
which resides in the smart card inserted in the default slot; that is, slot number 0. If the slot number is not
specified, then the default slot is assumed.

Each APDU connection has a logical channel reserved exclusively for it. That is, the channel is dedicated to the
J2ME application and the selected smart card application until the connection is closed. A smart card supporting
logical channels allows the host device to open multiple logical channels to communicate with on-card
applications. Logical channels other than the basic channel may be closed when the connection is closed. Basic
channel or channel 0 has to remain open while the card is powered on.

Since the basic channel or channel 0 cannot be closed, the terminal should maintain its availability status. When
a J2ME application asks for a new connection, the implementation would find out if channel 0 is in use by any
application (native or J2ME application). If channel 0 is not in use, the implementation would acquire the
channel 0 for communicating with the card application by setting the state of channel 0 to “IN USE”. It would
then select the desired application on channel 0. If the selection is successful, the newly created connection
object would be returned to the J2ME application which can then use it to communicate with the card
application. If the card application selection fails or the J2ME application calls close on the connection
object, the availability state of the basic channel will be set back to “AVAILABLE” meaning that the basic
channel is available for use.

When a J2ME application requests a connection to the card and channel 0 is in use by some other application
(native or J2ME application), the implementation sends a MANAGE CHANNEL command to the card requesting
a logical channel for the new connection. If there is a logical channel available, the card returns with the logical
channel number which will be used by the new connection. The implementation would select the desired
application on the newly allocated logical channel. If the selection is successful, the implementation returns the
newly created connection object to the J2ME application which can then use it for communicating with the card
application. If application selection fails or the J2ME application calls close() method to close the
connection with the card application, the implementation will send a MANAGE CHANNEL command to the card
to close the channel. The channel will then be available for use by other applications.
In the case when there are no logical channels available or the card does not support logical channels, the card
will return an error. An IOException will be thrown and no connection object will be returned to the J2ME
application.

Communicating With Smart Card Applications
Once an APDU connection is created, the J2ME application can use the exchangeAPDU method to send
command APDUs and receive response APDUs to and from the card. J2ME applications cannot use
exchangeAPDU to send card application selection APDUs or channel management APDUs. Card application
selection is allowed only by calling Connector.open method with the URI string described above and
logical channels management is defined by API functionality.

There may be several APDU connections open at the same time using different logical channels with the same
card. However, since APDU protocol is synchronous, there can be no interleaving of command and their
response APDUs across logical channels. Between the receipt of the command APDU and the sending of the
response APDU to that command, only one logical channel is active. For T=0 protocol, for case 4 and case 2
command APDUs the card may respond with ’61 XX’ or ’6C XX’. These special cases MUST be handled by
the implementation in the following manner:
• ’61 XX’: The implementation MUST send GET RESPONSE to the card to get the response data before any
other command is sent.
• ’6C XX’: The implementation MUST resend the command after setting Le equal to XX received from the
card before any other command is sent.

In both the cases discussed above, the implementation MUST make sure that between sending the command
APDU, receiving status word ’61 XX’ or ’6C XX’, and sending GET RESPONSE or resending the command
APDU with Le set to XX respectively, there MUST not be any other APDU exchange on any logical channel
with the card. In case the status word ’61 XX’ is received multiple times successively from the card, the
implementation must accumulate all the response data received from the card before returning it to the J2ME
application. J2ME applications MUST remain oblivious of the exchanges mentioned above and should only get
the response received as a result of the above operations.

Communicating With Multiple Smart Card Applications
A J2ME application may connect and communicate with multiple smart card applications interchangeably or
have multiple connections with the same card application, if the card application is multi-selectable. To achieve
this the J2ME application can repeat the procedure mentioned above to create corresponding connection
objects.

Closing an APDUConnection
J2ME application can call javax.microedition.io.Connection.close() on an APDU connection
to close the connection. If the connection was established on channel other than channel 0, this action closes the
channel consequently deselecting the card application and making the channel available for use by other
applications. In case of channel 0, the channel is marked as available for use by other applications. The
application selected on channel 0 is not deselected at the time the connection is closed but remains selected until
a new connection is established on channel 0.

Exceptions that can be Thrown During Connection Establishment

A ConnectionNotFoundException is thrown in any of the following situations:
• if the card slot does not exist
• if the card is not inserted or powered on
• if the card application selection fails because the card application with the specified application identifier
does not exist or because the card application refused selection

If the card application selection fails because the J2ME application is not allowed to access the application with
the specified application identifier a SecurityException is thrown.

In cases such as when there is no logical channel available to establish a connection, an IOException will be
thrown and a connection object will not be returned to the J2ME application.
If a card is removed after the connection is established and then re-inserted, the J2ME application will have to
re-establish the connection and get a new connection object. Any attempts to exchange APDUs using the
connection object created before removal of the card will result in InterruptedIOException being
thrown.

BNF Format for Connector.open() string
The URI MUST conform to the BNF syntax specified below. If the URI does not conform to this syntax, an
IllegalArgumentException is thrown.

Smart Card Slot Discovery Mechanism
J2ME devices may support a variable number of security elements (usually smart card slots). Some security
elements are permanently attached to the device (e.g. a soldered chip), others are removable. The removable
security elements may be cold-swappable, requiring the battery to be removed before the security element can
be exchanged (e.g. a MiniSIM slot hidden behind the battery). Other removable security elements can be
inserted and removed while the system is running. (e.g. a hot-swappable smart card reader).
A system property is defined to indicate the names of the smart card slots. The property can be queried through
the System.getProperty() method using the key microedition.smartcardslots. The value returned is a
<APDU_connection_string> ::= “apdu:”<targetAddress>
<targetAddress> ::= [slot];target
<slot> ::= smart card slot number. (optional. Hexadecimal
number identifying the smart card slot. Default slot
assumed if left empty)
<target> ::= “target=”<AID>|“SAT”
<AID> ::= < 5 - 16 bytes >

An AID (Application Identifier) uniquely identifies a
smart card application. It is represented by 5 to 16
hexadecimal bytes where each byte value is seperated
by a “.”. comma-separated list of the smart card slots which can be used in the Connector.open() string to identify
the specific smart card slot.

If the platform includes a (U)SIM card, it MUST be in slot 0.
The logical slot names include the slot number and a descriptor indicating the type of the slot. For coldswappable
slots the letter ’C’ is appended to the slot number. For hot-swappable slots the letter ’H’ is appended
to the slot number. The slot descriptors (the letter ’C’ and ’H’ appended to the slot number) cannot be passed as
part of the URI to open a connection to the smart card application. The J2ME application MUST remove the
descriptor from the logical slot name and only use the slot number in URI to identify the specific smart card
slot.

A typical configuration for a cold-swappable SIM card and a hot-swappable removable card would be:
microedition.smartcardslots: 0C,1H

Support for (U)SIM Application Toolkit
If an implementation allows J2ME applications to communicate with (U)SAT, support for communication with
(U)SAT should be implemented as specified below.
The APDUConnection can be used to communicate with (U)SAT applications on channel 0. The following
sections describe the constraints and methods in supporting communicating with (U)SAT applications.
Technical Constraints
• The operator domain has full and exclusive access to this connection.
• Only ENVELOPE APDUs may be sent. For all other APDUs IllegalArgumentException is
thrown.
• The class byte MUST be set by the implementation which will be either A0 or 80 depending on whether the
phone is running GSM or UMTS. The class byte supplied by the J2ME application will be ignored.
• In the case when (U)SIM responds with status word ’9E XX’ or ’9F XX’, the behavior of
APDUConnection would be the same as when status word ’61 XX’ is received from the card.
• In the case when (U)SIM responds with status word ’62 XX’ or ’63 XX’ the implementation MUST send
GET RESPONSE to the card with Le set to ’00’ before any other command is sent. The implementation
MUST make sure that between sending the ENVELOPE APDU, receiving status word ’62 XX’ or ’63 XX’,
and sending GET RESPONSE APDU with Le set to ’00’, there MUST not be any other APDU exchange
on any logical channel with the card.
• When the J2ME application sends an ENVELOPE APDU to the (U)SIM, the native application may be
performing a proactive session. In this case the (U)SIM MUST manage the synchronization issue. The
(U)SIM may respond with status word ’93 00’ (SIM Application Toolkit is busy) when the (U)SIM is
performing another proactive session.
Open Connection with (U)SIM, invoke, Close Connection
To communicate using (U)SAT commands, a J2ME application performs these steps:
• The J2ME application establishes a connection by using the Connector.open method. To open a
connection with a smart card using (U)SAT commands, the URI string passed to the Connector.open
method MUST be the following:
“apdu:<slot>;target=SAT”
Where “apdu” is the protocol and slot is the card slot having the (U)SIM. The value “SAT” for parameter
“target” indicates that the connection is to be established with (U)SAT.

The implementation MUST use logical channel 0 even if this channel is occupied by other applications, and
MUST not send a MANAGE CHANNEL or a SELECT by DF NAME command to the (U)SIM.
• The J2ME application invokes the exchangeAPDU method on javax.microedition.apdu.
APDUConnection interface to send the ENVELOPE commands. The J2ME application MUST not
initiate a proactive session since if a proactive session is started, it could cause synchronization problems
with other entities talking to the (U)SAT, such as other J2ME applications or native applications. If a
proactive session is started by the (U)SAT application in response to an envelope sent by the J2ME
application, it is the responsibility of the J2ME application to deal with it accordingly.
The implementation MUST check the INS byte of the APDU sent by the J2ME application. If the APDU is
not an ENVELOPE command, the implementation throws an IllegalArgumentException. The
implementation MUST set the CLA byte corresponding to whether the phone is running GSM or UMTS.
• To close the connection, the J2ME application invokes the close method on javax.microedition.apdu.
APDUConnection. The implementation MUST not send a CLOSE CHANNEL to the (U)SIM.

APDUConnection Example
The following example shows how an APDUConnection can be used to access a smart card application.
APDUConnection acn = null;
try{
// Create an APDUConnection object
acn = (APDUConnection)
Connector.open(“apdu:0;target=A0.0.0.67.4.7.1F.3.2C.3”);
// Send a command APDU and receive response APDU
responseAPDU = acn.exchangeAPDU(commandAPDU);
...
} catch (IOException e) {
...
} finally {
...
if(acn != null) {
// Close connection
acn.close();
}
...
}
...
Note regarding PIN-related methods
A platform should implement the PIN entry UI in such a way that:
• the UI is distinguishable from a UI generated by external sources (for example J2ME applications)
• external sources are not able to retrieve or insert PIN data
• If the static access control mechanism is implemented, the PIN related methods MUST be implemented as
specified in the static access control mechanism. For further details see Appendix A (Recommended
Security Element Access Control)
 * 
 * 
 *
 */
public interface APDUConnection extends javax.microedition.io.Connection {

	/**
	 * Exchanges an APDU command with a smart card application. This method will
	 * put the channel number of the associated channel in the CLA byte of the
	 * command APDU. Communication with a smart card device is synchronous. This
	 * method blocks until the response has been received from the smart card
	 * application, or is interrupted. The interruption could be due to the card
	 * being removed from the slot or operation timeout.
	 * 
	 * Parameters:
	 * 
	 * @param commandAPDU -
	 *            a byte encoded command for the smart card application
	 * 
	 * @return a byte encoded response to the requested operation
	 * 
	 * 
	 * @throws java.lang.IllegalArgumentException   if: 
	 *             • commandAPDU parameter is null 
	 *             • commandAPDU contains a card application selection APDU 
	 *             • commandAPDU contains a MANAGE CHANNEL command APDU 
	 *             • if the channel associated with the connection object 
	 *             is non-zero and the CLA byte has a  value other than 0x0X, 
	 *             0x8X, 0x9X or 0xAX 
	 *             • commandAPDU  parameter contains a malformed APDU
	 *             
	 *  @throws java.io.InterruptedIOException - can be thrown in any of
	 *             these situations: 
	 *             • if this Connection object is closed during the exchange operation 
	 *             • if the card is removed after connection is established and 
	 *             then reinserted, and an APDU exchange is attempted without 
	 *             re-establishing the connection
	 * 
	 * @throws java.io.IOException -
	 *             is thrown if the operation was not successful because of
	 *             problems communicating with the card, or if the connection
	 *             was already closed
	 * 
	 * @throws java.lang.SecurityException -
	 *             if commandAPDU byte array contains an APDU that the J2ME
	 *             application is not allowed to send to the card application.
	 */
	public byte[] exchangeAPDU(byte[] commandAPDU) throws java.io.IOException;

	/**
	 * Returns the Answer To Reset (ATR) message sent by the smart card in
	 * response to the reset operation. ATR is received from the card when the
	 * card is powered up or when it is reset. The implementation MUST detect
	 * the presence of the card when this method is called and return the value
	 * of ATR if the card is present and null if the card has been removed.
	 * 
	 * @return the ATR message if the card is inserted, or null if the card has
	 *         been removed or the connection has been closed.
	 */
	public  byte[] getATR();

/**
 * This is a high-level method that lets the J2ME application ask for the user
 * PIN value for PIN verification purposes and send it to the card. A call to
 * enterPin method pops up a UI that requests the PIN from the user. The pinID
 * field indicates which PIN must be requested from the user. The user can
 * either cancel the request or continue. If the user enters the PIN and chooses
 * to continue the implementation is responsible for presenting the PIN value to
 * the card for verification. If padding is required for the PIN, the
 * implementation is responsible for providing appropriate padding.
 * 
 * Parameters:
 * 
 * @param pinID -
 *            the type of PIN the implementation is supposed to prompt the user
 *            to enter.
 * 
 * @return result of PIN verification which is the status word received from the
 *         smart card in the form of a byte array. This method would return null
 *         if the user cancels the request.
 * 
 * 
 * @throws java.io.IOException -
 *             is thrown if the PIN could not be communicated with the card
 *             because the connection was closed before this method was called
 *             or because of communication problems.
 * 
 * @throws java.io.InterruptedIOException -
 *             can be thrown in any of these situations: • if this Connection
 *             object is closed during the exchange operation • if the card is
 *             removed after connection is established and then reinserted, and
 *             PIN entry is attempted without re-establishing the connection
 * 
 * @throws java.lang.SecurityException -
 *             is thrown if the J2ME application does not have appropriate
 *             rights to ask for PIN verification.
 * 
 * @throws java.lang.UnsupportedOperationException -
 *             is thrown if the implementation does not support this method.
 */
	public  byte[] enterPin(int pinID) throws java.io.IOException;


	/**
	 * This is a high-level method that lets the J2ME application ask the user
	 * for PIN values for changing the PIN and sending these values to the card.
	 * A call to changePin method pops up a UI that requests the user for an old
	 * or existing PIN value and the new PIN value to change the value of the
	 * PIN. The pinID field indicates which PIN is to be changed. The user can
	 * either cancel the request or continue. If the user enters the PIN values
	 * and chooses to continue the implementation is responsible for presenting
	 * the PIN value to the card. If padding is required for the PIN, the
	 * implementation is responsible for providing appropriate padding.
	 * 
	 * Parameters: 
	 * 
	 * @param pinID - the type of PIN the implementation is supposed to
	 * prompt the user to change. 
	 * 
	 * @return result of changing the PIN value
	 * which is the status word received from the smart card in the form of a
	 * byte array. This method would return null if the user cancels the
	 * request. 
	 * 
	 * @throws java.io.IOException - is thrown if the PIN could not be
	 * communicated with the card because the connection was closed before this
	 * method was called or because of communication problems.
	 * 
	 * @throws java.io.InterruptedIOException - can be thrown in any of these
	 * situations: 
	 * 
	 * • if this Connection object is closed during the exchange
	 * operation 
	 * • if the card is removed after connection is established and
	 * then reinserted, and attempt is made to change PIN without
	 * re-establishing the connection 
	 * 
	 * @throws java.lang.SecurityException - is thrown if
	 * the J2ME application does not have appropriate rights to ask for changing
	 * the PIN value. 
	 * 
	 * @throws java.lang.UnsupportedOperationException - is thrown if the
	 * implementation does not support this method.
	 */
	public  byte[] changePin(int pinID) throws java.io.IOException;

/**
 * This is a high-level method that lets the J2ME application ask for the user
 * PIN value for the PIN that is to be disabled and send it to the card. A call
 * to disablePin method pops up a UI that requests the user to enter the value
 * for the PIN that is to be disabled. The pinID field indicates which PIN is to
 * be disabled. The user can either cancel the request or continue. If the user
 * enters the PIN and chooses to continue the implementation is responsible for
 * presenting the PIN value to the card to disable PIN. If padding is required
 * for the PIN, the implementation is responsible for providing appropriate
 * padding.
 * 
 * Parameters:
 * 
 * @param pinID -
 *            the type of PIN the implementation is required to prompt the user
 *            to enter.
 * 
 * @return result of disabling the PIN value which is the status word received
 *         from the smart card in the form of a byte array. This method would
 *         return null if the user cancels the request.
 * 
 * 
 * @throws java.io.IOException -
 *             is thrown if the PIN could not be communicated with the card
 *             because the connection was closed before this method was called
 *             or because of communication problems.
 * 
 * @throws java.io.InterruptedIOException -
 *             can be thrown in any of these situations: • if this Connection
 *             object is closed during the exchange operation • if the card is
 *             removed after connection is established and then reinserted, and
 *             attempt is made to disable PIN without re-establishing the
 *             connection
 * 
 * @throws java.lang.SecurityException -
 *             is thrown if the J2ME application does not have appropriate
 *             rights to ask for disabling the PIN.
 * 
 * @throws java.lang.UnsupportedOperationException -
 *             is thrown if the implementation does not support this method.
 */
	public  byte[] disablePin(int pinID) throws java.io.IOException;

/**
 * This is a high-level method that lets the J2ME application ask for the user
 * to enter the value for the PIN that is to be enabled and send it to the card.
 * A call to enablePin method pops up a UI that requests the user to enter the
 * value for the PIN that is to be enabled. The pinID field indicates which PIN
 * is to be enabled. The user can either cancel the request or continue. If the
 * user enters the PIN and chooses to continue the implementation is responsible
 * for presenting the PIN value to the card for enabling the PIN. If padding is
 * required for the PIN, the implementation is responsible for providing
 * appropriate padding.
 * 
 * Parameters:
 * 
 * @param pinID -
 *            the type of PIN the implementation is required to prompt the user
 *            to enter.
 * 
 * @return result of enabling the PIN value which is the status word received
 *         from the smart card in the form of a byte array. This method would
 *         return null if the user cancels the request.
 * 
 * 
 * @throws java.io.IOException -
 *             is thrown if the PIN could not be communicated with the card
 *             because the connection was closed before this method was called
 *             or because of communication problems.
 * 
 * @throws java.io.InterruptedIOException -
 *             can be thrown in any of these situations: • if this Connection
 *             object is closed during the exchange operation • if the card is
 *             removed after connection is established and then reinserted, and
 *             attempt is made to enable PIN without re-establishing the
 *             connection
 * 
 * @throws java.lang.SecurityException -
 *             is thrown if the J2ME application does not have appropriate
 *             rights to ask for enabling the PIN.
 * 
 * @throws java.lang.UnsupportedOperationException -
 *             is thrown if the implementation does not support this method.
 */
	public  byte[] enablePin(int pinID) throws java.io.IOException;

	/**
	 * This is a high-level method that lets the J2ME application ask the user
	 * to enter the value for an unblocking PIN, and the new value for the
	 * blocked PIN and send these to the card. A call to unblockPin method pops
	 * up a UI that requests the user to enter the value for the unblocking PIN
	 * and the new value for the blocked PIN. The unblockingPinID field
	 * indicates which unblocking PIN is to be used to unblock the blocked PIN
	 * which is indicated by the field blockedPinID. The user can either cancel
	 * the request or continue. If the user enters the PIN values and chooses to
	 * continue, the implementation is responsible for presenting the PIN values
	 * to the card for unblocking the blocked PIN. If padding is required for
	 * either of the PIN values, the implementation is responsible for providing
	 * appropriate padding.
	 * 
	 * Parameters:
	 * 
	 * @param blockedPinID -
	 *            the ID of PIN that is to be unblocked.
	 * @param unblockingPinID -
	 *            the ID of unblocking PIN.
	 * 
	 * @return result of unblocking the PIN value which is the status word
	 *         received from the smart card in the form of a byte array. This
	 *         method would return null if the user cancels the request.
	 * 
	 * 
	 * @throws java.io.IOException -
	 *             is thrown if the PIN could not be communicated with the card
	 *             because the connection was closed before this method was
	 *             called or because of communication problems.
	 * 
	 * @throws java.io.InterruptedIOException -
	 *             can be thrown in any of these situations: 
	 *             • if this Connection object is closed during the exchange operation 
	 *             • if the card is removed after connection is established and
	 *             then reinserted, and attempt is made to unblock PIN without
	 *             re-establishing the connection
	 * 
	 * @throws java.lang.SecurityException -
	 *             is thrown if the J2ME application does not have appropriate
	 *             rights to ask for unblocking the PIN.
	 * 
	 * @throwsjava.lang.UnsupportedOperationException - is thrown if the
	 *                                                implementation does not
	 *                                                support this method.
	 * 
	 */
	public  byte[] unblockPin(int blockedPinID, int unblockingPinID) throws java.io.IOException;

	
}
