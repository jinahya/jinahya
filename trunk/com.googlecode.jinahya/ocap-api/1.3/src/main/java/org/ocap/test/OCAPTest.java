/*
 * "Stub" definition of OCAPTest.
 *
 */

package org.ocap.test;

import java.io.IOException;


/**
 * The purpose of this class is to provide a very simple communication 
 * channel between the OCAP implementation under test (IUT) and the test server.
 * The functionality of this class is intentionally limited to 
 * sending/receiving raw messages via TCP/IP or UDP/IP protocol. 
 * This approach decouples the lower 
 * communications channel from the higher messaging protocol used to exchange messages 
 * between the test client and the test server.  As a result, the implementation will 
 * be isolated from any future messaging protocol changes which may be required 
 * for future testing needs.
 * <br>
 * This class does not provide a means for concurrently executing test applications
 * to negotiate reservation of the interaction channel used to exchange message with 
 * the test environment host.  Test application authors should ensure that 
 * concurrently executing test applications correctly interoperate in their use of
 * this channel.
 * <br>
 *
 * The implementation of this class MUST use TCP/IP or UDP/IP protocol to establish a 
 * socket connection to the test server via the RF connector labeled 'Cable In'
 * on the OCAP Host Device. See the {@link OCAPTest#getProtocol} method. 
 *
 * @author Piotr Czapla
 * @author Shigeaki Watanabe
 */
public class OCAPTest
{

    /**
     * Message termination byte. 
     * Used to identify the end of a sequence of message bytes
     * between ATE and a test application. 
     * For the {@link OCAPTest#send} and {@link OCAPTest#receive} methods the 
     * specified rawMessage and the returned bytes shall not contain 
     * MESSAGE_TERMINATION_BYTE except indicating message termination. 
     * For the {@link OCAPTest#sendUDP} and {@link OCAPTest#receiveUDP} methods 
     * the specified rawMessage and the returned bytes may contain 
     * MESSAGE_TERMINATION_BYTE to process acknowledge protocol. 
     */
    public final static byte MESSAGE_TERMINATION_BYTE = '\0';

    /**
     * Maximum length of messages exchanged between 
     * ATE and a test application. 
     */
    public final static int MAX_MESSAGE_LENGTH = 1500;

    /**
     * Indicates that an OCAPTest instance is configured to use UDP/IP protocol. 
     */
    public static final int UDP = 0;

    /**
     * Indicates that an OCAPTest instance is configured to use TCP/IP protocol. 
     */
    public static final int TCP = 1;

    
    /**
     * Send a specified byte array to ATE via TCP/IP protocol. 
     * The message MUST NOT be altered before or while sending. 
     * <br>
     * A test application specifies a byte array that is less than 
     * MAX_MESSAGE_LENGTH to the rawMessage parameter. The byte array shall not 
     * contain MESSAGE_TERMINATION_BYTE. 
     * This method SHALL NOT make any assumptions as to the format of the 
     * content of the rawMessage other than it can’t contain a byte with the value 
     * MESSAGE_TERMINATION_BYTE except message termination. After the 
     * specified rawMessage are sent, 
     * <code>MESSAGE_TERMINATION_BYTE</code> will be sent by this method, indicating to
     * the ATE that the rawMessage is complete, 
     * and ready for parsing.
     * <br>
     * In case of buffered connections, the buffer MUST be flushed upon 
     * exiting this method.
     *
     * @param rawMessage  a byte array of the raw message to be sent to 
     *        ATE via TCP/IP protocol. 
     *
     * @throws IllegalArgumentException  If rawMessage contains a byte with the 
     *        value MESSAGE_TERMINATION_BYTE. 
     * 
     * @throws IOException If there is any problem with I/O operations or an 
     *        interaction channel has not been initialized. 
     */
    public static void send(byte[]rawMessage)
        throws IOException
    {
    }


    /**
     * Receive a byte array from ATE via TCP/IP.
     * 
     * <br> 
     * The message MUST NOT be altered during or after reception. 
     * <br>
     * This is a blocking method which waits for an entire of original message 
     * from ATE.  
     * The implementation should accumulate bytes from the test server until 
     * the <code>MESSAGE_TERMINATION_BYTE</code> is received.  The all received bytes
     * MUST then be returned to the caller as a <code>byte[]</code>.  The 
     * termination character is not to be included in the returned byte array. 
     * There should be no other assumptions as to the format or content of the 
     * message bytes.
     * <br>
     * The maximum number of returned byte array  is 
     * <code>MAX_MESSAGE_LENGTH</code>.
     * Any bytes received beyond MAX_MESSAGE_LENGTH should be discarded.  In any
     * event, this method must not return until a 
     * <code>MESSAGE_TERMINATION_BYTE</code> has been encountered.
     *
     * @return  a byte array coming from ATE via TCP/IP protocol.      
     *         The termination character is not included.
     *
     * @throws IOException If there is any problem with I/O operations or an 
     *         interaction channel has not been initialized. 
     */
    public static byte[] receive()
        throws IOException
    {
            return null;
    }


    /**
     * Returns a current protocol that is used by an OCAPTest instance. 
     * The current protocol shall matches with a protocol specified by a 
     * ppp value of an “ate:a.b.c.d:xxxx:ppp” message from ATE. 
     * 
     * @return a current protocol that is used by an OCAPTest instance. 
     *         Either {@link OCAPTest#UDP} or {@link OCAPTest#TCP} constant. 
     */
    public static int getProtocol() {
        return 0;
    }


    /**
     * Receive a message from ATE via UDP/IP. The ATE’s IP address is specified 
     * by an “ate:a.b.c.d:xxxx:ppp” message from ATE. This method simply returns 
     * a payload bytes in a UDP packet without modification, i.e., the OCAPTest 
     * doesn’t concatenate a sequence of messages. It is responsibility of a 
     * caller of this method to concatenate received byte arrays into an 
     * original message according to ATE acknowledge protocol.  
     * This method blocks the thread of a caller until receiving a UDP packet. 
     * 
     * @return byte  a payload bytes in a UDP packet. The byte length must be 
     *         less than a max length limited by the interaction channel and 
     *         it is responsibility of a caller of this method to do so. 
     * 
     * @throws IOException If there is any problem with I/O operations or an 
     *         interaction channel has not been initialized. 
     */
    public static byte[] receiveUDP() throws java.io.IOException {
        return null;
    }


    /**
     * Send rawMessage to ATE via UDP/IP. The ATE’s IP address is specified by 
     * an “ate:a.b.c.d:xxxx:ppp” message from ATE. This method shall not 
     * divide the specified rawMessage into some UDP packets. The specified 
     * rawMessage shall be sent in a single UDP packet. 
     * 
     * @param rawMessage byte data to be sent. The byte length must be 
     *         less than a max length limited by the interaction channel and 
     *         it is responsibility of a caller of this method to do so. 
     * 
     * @throws IOException If there is any problem with I/O operations or an
     *         interaction channel has not been initialized. 
     */
    public static void sendUDP(byte[] rawMessage) throws java.io.IOException {
    }


}
