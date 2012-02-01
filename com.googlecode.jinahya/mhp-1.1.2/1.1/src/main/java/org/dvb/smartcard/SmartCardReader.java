package org.dvb.smartcard;

import javax.microedition.io.ConnectionNotFoundException;
import java.io.IOException;
import javax.microedition.apdu.APDUConnection;
/**
 *  Represents a physical smart card reader slot in the terminal.
 * @since MHP 1.1.3
 */
public class SmartCardReader {

    /**
    * This constructor is provided for the use of implementations and specifications
    * which extend this specification. Applications shall not define sub-classes of
    * this class. Implementations are not required to behave correctly if any such
    * application defined sub-classes are used.
    */

  protected SmartCardReader() {
  }

  /**
   * Each single reader is identified on the terminal with an increasing identifier.
   * Default identifier for single slot terminals is zero.
   *
   * @return int id associated to the reader
   */
  public int getSlotId() {
    return 0;
  }

  /**
   * Retrieves current status of the smart card reader.
   * The constant values are the ones defined in SmartCardReaderEvent.
   *
   * @return int current status of the reader
   */
  public int getStatus() {
    return 0;
  }

  /**
   * Allows applications to query if a smart card is inserted in the reader.
   * True is returned also if no ATR is correctly retrieved (i.e. smart card inserted
   * upside-down).
   *
   * @return boolean true if smart card is inserted in given slot
   */
  public boolean isSmartCardInserted() {
    return false;
  }

  /**
   * Allows the specified listener to received notifications about changes in the
   * status of the smart card reader.
   *
   * @param listener the SmartCardReaderListener that will receive the notifications
   */
  public void addSmartCardReaderListener(SmartCardReaderListener listener) {
    return;
  }

  /**
  * Removes specified listener: the notifications about changes in the status of the
  * smart card reader will no longer be forwarded to the given listener.
  *
  * @param listener the SmartCardReaderListener that was receiving the notifications.
  */
  public void removeSmartCardReaderListener(SmartCardReaderListener listener) {
    return;
  }

/**
 * Open connection without selecting an application, and managing channels
 *
 * @return the APDU connection established with the smart card in the reader
 * @throws IOException if a connection is already opened and/or cannot be established
 * @throws ConnectionNotFoundException if opening a connection is not admitted (i.e. for CA cards
 */

 public APDUConnection openDefaultConnection() throws IOException, ConnectionNotFoundException {
   return null;
 }



}

