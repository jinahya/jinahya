package org.dvb.test;

import java.io.IOException;

/** 

The DVBTest class allows test applications to log messages during their 
execution and to indicate their termination condition in a platform independent manner.

<p>

A number of constants are defined in the DVBTest class and are reserved as follows

<ul>

<li>

Zero and negative values defined within the class are reserved by DVB. 

<li> 

Positive return values are available for test application specific return values, which 
must be defined within the procedure for executing the test application as to their precise
meaning as regards conformance.

</ul>

*/ 
public class DVBTest 
{
/**
There is no public constructor
*/
private DVBTest()
{ 
}

/**
The application executed and terminated successfully and has therefore operated in a conformant manner.
*/
    public final static int PASS = 0x00; 

/**
The application executed and terminated unsuccessfully and has therefore operated in a non-conformant manner.
*/
    public final static int FAIL = -0x01; 

/**
The platform does not contain the option under test and therefore the test is inapplicable, the test result should not be 
considered when determining the status of the platform's conformance. 
*/
    public final static int OPTION_UNSUPPORTED = -0x02; 

/**
The application is unable to determine whether it has operated conformantly and therefore requires some human 
intervention to determine whether conformance has been achieved. Until the application has been checked the result of 
the application should be considered as non-conformant.

<p>

It is envisaged that tests returning this value may be those requiring evaluation of presented content, such as graphics, 
etc. Such presentation may require (subjective) human evaluation. 

*/
 public final static int HUMAN_INTERVENTION = -0x03; 

/**
A setup stage necessary to execute the application failed, and hence the result of
the application is unknown and therefore should be considered to have operated in a 
non-conformant manner.
*/
    public final static int UNRESOLVED = -0x04; 

/**
The application ran successfully, but the particular test was unable to execute.
Hence the result of is unknown, and may require human evaluation to determine conformance.

<p>

For example, an out of disk space test may not execute within a fixed number of iterations
(within a practical amount of time) for devices with large capacity storage, etc.
*/
    public final static int UNTESTED = -0x05; 

/**
This synchronous, blocking, method logs a result (intermediate result) of a test application using write-only access. The 
method takes both an identifier string, e.g.&nbsp; "Test number 1" and a message to output, e.g.&nbsp "Now invoking 
the xletPause method...". The application is not required to open a file or 
network connection, per se, and the log() method is always available for writing (in principle).

<p>

The precise format of the logged message is left deliberately unspecified, implementers may choose to output 
compressed messages, XML documents, or other formats of their choice (obviously provided that the original 
information can be recovered). It is an implementation option to include additional information with each logging 
message, e.g. including:

<ul>

<li>	version of the specification being implemented

<li>	compiler version and options.

<li>	build-version

<li>	timestamp

<li>	date

<li>	debug info

</ul>

Messages sent using this method should &quot;atomic&quot;, i.e. that they are not interleaved with other messages
sent using the methods defined in the DVBTest class.

<p>

<b> 
Implementation
</b>
<p>
The precise mechanism(s) by which the this method may be implemented are 
intentionally unspecified, implementation options might include:
<ul>
<li> logging the message to a local file system.
<li> logging the message to a mounted remote file system.
<li> logging the message to a RAM disk, etc.
<li> logging the message via an RS-232 (or other serial) connection.
<li> logging the message to a remote host via some IP / UDP based mechanism, 
e.g. 
using a socket-based connection.
</ul>
Note that the implementation of the log method may use the same or a different 
mechanism to that used by the terminate method.
<p>
The log method does not require any explicit initialisation on the part of the 
application 
under test. For example if messages are being stored to a file system, then the 
application 
is not required to mount / open any storage file. Similarly, if the messages are 
being 
logged via a network connection, then the application is not required to open a 
connection to the storage host, etc. In principle, the mechanism should always 
be 
available to accept messages.
<p>
If this method is implemented on top of some  buffering mechanism, it is 
strongly recommended that the 
buffer be flushed for each occurrence of a message being logged.

<p>
<b>
Security and implementation options
</b>
<p>
There is no Java security mechanism that is used to secure the log method.
<p>
Note that even if the log method is based on a particular implementation option, 
it shall be able to operate in spite of that particular implementation option 
itself being subject to security checks. For example, a log method implemented 
using the java.net.Socket class shall always be able to log a message from a 
test-application, even if the test-application is unable to directly access the 
java.net.Socket class due to security restrictions, etc.
<p>
It is an allowed implementation option to require that the test-client be put 
into some particular "test-mode" before any test-results are logged. This 
mechanism is required to reduce any inadvertent interaction due to downloaded 
applications accessing the test methods.

<p>
<b>
Authoring guidelines
</b>
<p>
The log method is not intended to be accessed by downloaded applications 
directly, 
it is purely intended for the use of conformance test applications. Authors of 
downloaded 
applications should not call this method, since there may be interactions 
between this 
method and normal in-field operation of the test-client (MHP platform). 
<p>
It is an allowed implementation option to require that the test-client be put 
into some 
particular "test-mode" before any test-results are logged. This mechanism is 
required to 
reduce any inadvertent interaction due to downloaded applications accessing the 
test 
methods.
<p>
It is an allowed implementation option to have a number of "test-modes" that are 
appropriate to different elements being conformance tested, for example, it is a 
valid 
implementation for a test-client to have a test-mode where results are stored 
via a serial 
port, and a separate test-mode where results are stored via a RAM disk. It is 
allowable for 
a conformance test to be performed with the test-client in some specific test-
mode, e.g. a 
java.net test (using a serial modem) might have its test results logged to a RAM 
disk, to 
avoid interaction between test-log messages and the serial protocol. 
<p>
The mechanism by which a test-client is put into a given test mode is 
intentionally left 
unspecified.
<p>
<b>
Relationship to java.io
</b>
<p>
It is an implementation option to map the implementation of this method onto 
corresponding write 
method(s) of appropriate java.io classes. These  classes may in turn be 
obtained, e.g. from java.net Socket 
classes, etc. 

@param id a string identifying the application (thread) that is logging the test result.

@param message the message that the application wishes to be logged.

@throws IOException if there is any problem in providing synchronous logging to an application.
This IOException may be due to failure to write to a file system, inability 
to access a remote socket, etc. the precise causes are deliberately unspecified and are implementation dependent.

*/
public static void log(String id, String message) throws IOException
{
}

/**

This method has the same behaviour, implementation options and restrictions as log(String, String) - except that. 
it allows an integer value to be logged, rather than a String, which may prove a useful option for automating tests.

@param id a string identifying the application (thread) that is logging the test result.

@param no the integer value that the application wishes to be logged.

@throws IOException under the same conditions as log(String, String).

*/
public static void log(String id, int no) throws IOException
{
}

/** 
This synchronous, blocking, method logs the termination condition of a test 
application using write-only access. The method takes both an identifier string, e.g. "Test number 1" and a integer 
value to output, e.g. org.dvb.test.DVBTest.PASS. In addition to logging the termination condition of 
the test, invoking this method also indicates that the test application has terminated its operation. Note that 
termination of operation does not necessarily correspond to the application being in any particular lifecycle state (as 
defined in the "Application Model" clause of the MHP specification). The application is not required to open a file or 
network connection, per se, and the terminate() method is always available for writing (in principle).

<p>

The precise format of the termination message is left deliberately unspecified, implementers may choose to output 
compressed messages, XML documents, or other formats of their choice (obviously provided that the original 
information can be recovered). It is an implementation option to include additional information with each termination 
message, e.g. including:

<ul>
<li>	version of the specification being implemented
<li>	compiler version and options.
<li>	build-version
<li>	timestamp
<li>	date
<li>	debug info
</ul>

<p>

On test-clients whose implementation of the terminate() method supports external 
communication to its test-server, implementations of this method may optionally indicate to the test-server that 
the test-client can be reset by its test-server so that another test may be initiated.  The precise mechanism by which 
this communication takes place is not specified it may be via a IP / socket, serial port, etc.

<p>

In the case of an test-client that does not support communication to its test-server, or in the case of an unsuccessful 
(hanging) test, or inability of this method to return (without throwing an exception) the test-server must be prepared to 
"time out" the application running on the test-client and then reset the test-client.

<p>

<b> 
Implementation
</b>
<p>
The precise mechanism(s) by which the this method may be implemented are 
intentionally unspecified, implementation options might include:
<ul>
<li> storing the termination condition to a local file system.
<li> storing the termination condition to a mounted remote file system.
<li> storing the termination condition to a RAM disk, etc.
<li> storing the termination condition via an RS-232 (or other serial) 
connection.
<li> storing the termination condition to a remote host via some IP / UDP based 
mechanism, e.g. using a socket-based connection.
</ul>
Messages sent using this method should &quot;atomic&quot;, i.e. that they are not interleaved with other messages
sent using the methods defined in the DVBTest class.
<p>
Note that the implementation of the terminate method may use the same or a 
different mechanism to that used by the log method.
<p>
The terminate method does not require any explicit initialisation on the part of 
the 
application under test. For example if termination conditions are being stored 
to 
a 
file system, then the application is not required to mount / open any storage 
file. Similarly, 
if the results are being logged via a network connection, then the application 
is not 
required to open a connection to the storage host, etc. In principle, the 
mechanism should 
always be available to accept termination messages.
<p>
If this method is implemented on top of some  buffering mechanism, it is 
strongly recommended that the 
buffer be flushed for each occurrence of a message being logged.

<p>
<b>
Security and implementation options
</b>
<p>
There is no Java security mechanism that is used to secure the terminate method.
<p>
Note that even if the terminate methods is based on a particular implementation 
option, it shall be able to operate in spite of that particular implementation 
option itself being subject to security checks. For example, a terminate method 
implemented using the java.net.Socket class shall always be able to log the 
termination condition of a test-application, even if the test-application is 
unable to directly access the java.net.Socket class due to security 
restrictions, etc.
<p>
It is an allowed implementation option to require that the test-client be put 
into some particular "test-mode" before any test-results are logged. This 
mechanism is required to reduce any inadvertent interaction due to downloaded 
applications accessing the test methods.

<p>
<b>
Authoring guidelines
</b>
<p>
The terminate method is not intended to be accessed by downloaded applications 
directly, 
it is purely intended for the use of conformance test applications. Authors of 
downloaded 
applications should not call this method, since there may be interactions 
between this 
method and normal in-field operation of the test-client (MHP platform). 
<p>
It is an allowed implementation option to require that the test-client be put 
into some 
particular "test-mode" before any test-results are logged. This mechanism is 
required to 
reduce any inadvertent interaction due to downloaded applications accessing the 
test 
methods.
<p>
It is an allowed implementation option to have a number of "test-modes" that are 
appropriate to different elements being conformance tested, for example, it is a 
valid 
implementation for a test-client to have a test-mode where results are stored 
via a serial 
port, and a separate test-mode where results are stored via a RAM disk. It is 
allowable for 
a conformance test to be performed with the test-client in some specific test-
mode, e.g. a 
java.net test (using a serial modem) might have its test results logged to a RAM 
disk, to 
avoid interaction between test-log messages and the serial protocol. 
<p>
The mechanism by which a test-client is put into a given test mode is 
intentionally left 
unspecified.
<p>
<b>
Relationship to java.io
</b>
<p>
It is an implementation option to map the implementation of this method onto 
corresponding write 
method(s) of appropriate java.io classes. These  classes may in turn be 
obtained, e.g. from java.net Socket 
classes, etc. 

@param id a string identifying the application (thread) that is terminating the test.

@param terminationCondition the termination condition of the test application.

@throws IOException thrown if there is any problem in terminating an application.
This may be due to failure to write to a file system, inability 
to access a remote socket, etc. the precise causes are deliberately unspecified.

*/ 

    public static void terminate(String id, int terminationCondition) throws 
java.io.IOException
	{
		System.out.println(id);
		switch(terminationCondition)
		{
			case PASS:
			System.out.println("PASSED");
			break;
			case FAIL:
			System.out.println("FAILED");
			break;
			default:
			System.out.println("UNKNOWN EXIT CONDITION(" + terminationCondition + ")");
			break;
		}
	}

/**
This is a method is used to &quot;approximately&quot; synchronise a test-client and test-server, the method blocks
until the test-server positively or negatively acknowledges the particular message. The intended use of this method is to remove critical timing issues from 
conformance tests, e.g. a conformance test to ensure that an Xlet responds to a change in broadcast 
signalling must first ensure that the Xlet is in a state where it is able to respond to such signalling --
since the time taken for an Xlet to achieve such a state is reliant on aspects outside of the scope of the
conformance test itself (delivery bit rate, hardware and CPU capabilities of the test-client, etc.).

<p>

Messages sent using this method should &quot;atomic&quot;, i.e. that they are not interleaved with other messages
sent using the methods defined in the DVBTest class.

<p>

<b> 
Implementation
</b>
<p>
The precise mechanism(s) by which the this method may be implemented are 
intentionally unspecified.

Implementation options for sending the prompt might include:
<ul>
<li> logging the controlCode via an RS-232 (or other serial) connection.
<li> logging the controlCode to a remote host via some IP / UDP based mechanism, e.g. using a socket-based connection.
<li> displaying the message on-screen for a (human) test operator, e.g. for systems not implementing a return channel capability.
</ul>
Implementation options for receiving the acknowledgement might include:
<ul>
<li> acknowledgement via an RS-232 (or other serial) connection.
<li> acknowledgement from a remote host via some IP / UDP based mechanism, e.g. using a socket-based connection.
<li> a (human) test operator manually acknowledging the message, e.g. for systems not implementing a return channel capability.
</ul>

@param id a string identifying the application (thread) that is sending the prompt.

@param controlCode an integer value (unique within a given Xlet) intended for use by some 
automated test process (corresponding to the readable message). 

@param message a message (unique within a given Xlet) intended to be readable by a (human) test 
operator (corresponding to the automated controlCode).

@throws IOException If there is any problem in receiving a positive acknowledgement from the test-server, then 
an this shall be thrown. This may be due to a negative acknowledgement
from the test-server, or due to other communication based causes -- which are deliberately left unspecified.

*/
    public static void prompt(String id, int controlCode, String message) throws IOException
	{
	}

}



