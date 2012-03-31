package org.dvb.smartcard;

/**
 * The smart card reader manager allow user to know the status of any slot available on the terminal. The manager can
 * dispatch the change of status in any reader to applications that registered themselves for monitoring it or can synchronously
 * return the current status of the reader.
 *
 * The SmartCardReaderManager is a singleton.
 * @since MHP 1.1.3
 */
public final class SmartCardReaderManager {

    /**
     * This constructor is provided for the use of implementations and specifications
     * which extend this specification. Applications shall not define sub-classes of
     * this class. Implementations are not required to behave correctly if any such
     * application defined sub-classes are used.
     */

  protected SmartCardReaderManager() {
  }

  /**
   * Used to get the unique istance of the smart card reader manager.
   *
   * @return SmartCardReaderManager the manager singleton instance
   */
  public static SmartCardReaderManager getInstance() {
    return null;
  }

  /**
   * Retrieves the number of smart card readers provided on the terminal.
   *
   * @return int number of card readers
   */
  public int getNumber() {
    return 0;
  }

  /**
   * Allows application to retrieve an array including all the smart card readers
   * provided on the terminal.
   *
   * In case no readers are provided, an array with length zero is returned.
   *
   * @return SmartCardReader[] array of smart card readers
   */

  public SmartCardReader[] getSmartCardReaders() {
    return null;
  }
}

