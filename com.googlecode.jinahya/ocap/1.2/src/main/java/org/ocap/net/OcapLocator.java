/*
 * OcapLocator.java
 *
 * Modified on September 18, 2004, 11:58 AM
 */

package org.ocap.net;

import org.davic.net.InvalidLocatorException;

/**
 * <P>
 * This class encapsulates an OCAP URL into an object.
 * This class provides access to locations of various types of items
 * in the transport stream.
 * </P><P>
 * The javax.tv.locator.Locator.toExternalForm() method returns an OCAP
 * URL string that is used to create an OcapLocator instance, in
 * canonical form.  If an OCAP locator is in canonical form, the following
 * MUST hold:
 * <UL>
 * <LI>no character is escaped if it is possible to represent it without
 * escaping according to the OCAP URL BNF.  (E.g. "%41" is changed to
 * "A").</LI>
 * <LI>hex numbers do not have leading zeros, except for the number zero
 * itself, which is represented as "0x0".  (E.g. "0x01" is changed to
 * "0x1").</LI>
 * <LI>all instances of ISO_639_language_code must be lowercase.  (E.g.
 * "SPA" is changed to "spa").</LI>
 * </UL>
 * No other change is performed to convert an OCAP locator to its canonical
 * form.
 * </P><P>
 * All methods defined in this class that return Strings, except
 * for toExternalForm(), return the String in Unicode format.
 * I.e. They MUST un-escape characters in the corresponding portion
 * of the URL that are escaped with the %nn syntax (where that syntax
 * is permitted by the OCAP URL BNF), and they MUST UTF-8 decode the
 * string.
 * </P><P>
 * All constructors defined in this class that take String parameters,
 * except for the OcapLocator(String url) constructor, require the String
 * in Unicode format.  I.e. Where permitted by the OCAP URL BNF they MUST
 * UTF-8 encode the string and they MUST escape (using the %nn syntax)
 * any characters that require escaping.  They MUST NOT escape any
 * character that can be represented without escaping.
 * </P>
 *
 * @see org.davic.net.Locator
 * @see org.dvb.application.AppAttributes#getServiceLocator
 */

public class OcapLocator extends org.davic.net.Locator
{
    /**
     * <P>
     * A constructor of this class corresponding to the OCAP URL form
     * "ocap://source_id".
     * </P><P>
     * Note that the OcapLocator does not automatically transform this
     * OCAP URL BNF form to any other form, even if the getFrequency() etc.
     * is called.
     * </P>
     *
     * @param sourceID a source_id value for the OCAP URL.
     *
     * @throws InvalidLocatorException if the sourceID to construct the
     *         locator doesn't specify a valid OCAP URL (e.g. a value is
     *         out of range).
     */
    public OcapLocator(int sourceID) throws InvalidLocatorException{
       super("") ;
    }

    /**
     * <P>
     * A constructor of this class corresponding to the OCAP URL form
     * "ocap://(oobfdc|f=frequency).program_number[.m=modulation_format]".
     * </P><P>
     * Note that the OcapLocator does not automatically transform this
     * OCAP URL BNF form to any other form, even if the getSourceId() etc.
     * is called.
     * </P>
     *
     * @param frequency a frequency value for the OCAP URL in hertz. If
     * the value is -1 then "oobfdc" is used instead of the frequency
     * term and the modulationFormat parameter is ignored.
     *
     * @param programNumber a program_number value for the OCAP URL
     *
     * @param modulationFormat a value representing a modulation_format as 
     *         specified in SCTE 65. If the value is 0xFF the modulation_format 
     *         is treated as NTSC analog and the programNumber parameter is 
     *         ignored. If the value is -1 the modulation_format is not 
     *         specified and the modulation_format term will not be included 
     *         in the locator constructed.
     *
     * @throws InvalidLocatorException if the parameters to construct the
     *         locator don't specify a valid OCAP URL (e.g. a value is
     *         out of range).
     */
    public OcapLocator (int frequency, int programNumber, int modulationFormat)
            throws InvalidLocatorException{
        super("") ;
     }
     
    /**
     * <P>
     * A constructor of this class corresponding to the OCAP URL form
     * "ocap://f=frequency[.m=modulation_format]".
     * </P><P>
     * Note that the OcapLocator does not automatically transform this
     * OCAP URL BNF form to any other form, even if the getSourceId() etc.
     * is called.
     * </P>
     *
     * @param frequency a frequency value for the OCAP URL in hertz.
     *
     * @param modulationFormat a value representing a modulation_format as 
     *         specified in SCTE 65. If the value is 0xFF the modulation_format is 
     *         treated as NTSC analog. If the value is -1 the modulation_format 
     *         is not specified and the modulation_format term will not be 
     *         included in the locator constructed.
     *
     * @throws InvalidLocatorException if the parameters to construct the
     *         locator don't specify a valid OCAP URL (e.g. a value is
     *         out of range).
     */
    public OcapLocator (int frequency, int modulationFormat)
            throws InvalidLocatorException{
        super("") ;
     }

    /**
     * <P>
     * A constructor of this class for any form of OCAP URL.
     * </P><P>
     * Note that the OcapLocator does not automatically transform the
     * specified url string to any other form, even if any get methods
     * for the value that is not included in the url string are called.
     * </P>
     *
     * @param url a string expression that represents the OCAP URL.
     *
     * @throws InvalidLocatorException if the url to construct the
     *         locator doesn't specify a valid OCAP URL (e.g. a value
     *         is out of range).
     */
    public OcapLocator(String url) throws InvalidLocatorException{
        super("") ;
    }

    /**
     * <P>
     * A constructor of this class corresponding to the OCAP URL form
     * "ocap://source_id[.stream_type[,ISO_639_language_code]{&
     * stream_type[,ISO_639_language_code]}][;event_id]{/path_segments}".
     * Some of the parameters can be omitted according to the OCAP URL BNF
     * definition.
     * </P><P>
     * Note that the OcapLocator does not automatically transform this
     * OCAP URL BNF form to any other form, even if the getFrequency() etc.
     * is called.
     * </P>
     *
     * @param sourceID a source_id value for the OCAP URL.
     *
     * @param streamType a stream_type value for the OCAP URL.
     *         A combination of the streamType[n] and the ISO639LanguageCode[n]
     *         makes a program_element. The streamType shall be a zero
     *         length array, if it is omitted in the OCAP URL.
     *
     * @param ISO639LanguageCode an ISO_639_language_code value for the
     *         OCAP URL.
     *         A combination of the streamType[n] and the ISO639LanguageCode[n]
     *         makes a program_element. The ISO639LanguageCode shall be
     *         a zero length array, if it is omitted in the OCAP URL.
     *         If ISO639LanguageCode is not a zero-length array, it shall
     *         be an array with the same length as streamType.  If
     *         ISO639LanguageCode[n] is null, then the language code for
     *         streamType[n] is omitted in the OCAP URL.
     *
     * @param eventID an event_id value for the OCAP URL.
     *         The event_id shall be -1, if it is omitted in the OCAP URL.
     *
     * @param pathSegments a path_segments value for the OCAP URL.
     *         The pathSegments shall be null, if it is omitted in the
     *         OCAP URL.
     *
     * @throws InvalidLocatorException if the parameters to construct the
     *         locator don't specify a valid OCAP URL (e.g. a value is
     *         out of range).
     */
    public OcapLocator(int sourceID, short streamType[],
                        String ISO639LanguageCode[], int eventID,
                        String pathSegments)
            throws InvalidLocatorException{
       super("") ;
    }


    /**
     * <P>
     * A constructor of this class corresponding to the OCAP URL form
     * "ocap://source_id[.stream_type[,index]{&stream_type[,index]}]
     * [;event_id]{/path_segments}".
     * Some of parameters can be omitted according to the OCAP URL BNF
     * definition.
     * </P><P>
     * Note that the OcapLocator does not automatically transform this
     * OCAP URL BNF form to any other form, even if the getFrequency() etc.
     * is called.
     * </P>
     *
     * @param sourceID a source_id value for the OCAP URL.
     *
     * @param streamType a stream_type value for the OCAP URL.
     *         A combination of the streamType[n] and the index[n] makes
     *         a program_element. The streamType shall be a zero
     *         length array, if it is omitted in the OCAP URL.
     *
     * @param index an index value for the OCAP URL.
     *         A combination of the streamType[n] and the index[n] makes
     *         a program_element. The index shall be a zero length
     *         array, if it is omitted in the OCAP URL.
     *         If index is not a zero-length array, it shall be an array
     *         with the same length as streamType.  If index[n] is -1,
     *         then the index for streamType[n] is omitted in the OCAP URL.
     *
     * @param eventID an event_id value for the OCAP URL.
     *         The event_id shall be -1, if it is omitted in the OCAP URL.
     *
     * @param pathSegments a path_segments value for the OCAP URL.
     *         The pathSegments shall be null, if it is omitted in the
     *         OCAP URL.
     *
     * @throws InvalidLocatorException if the parameters to construct the
     *         locator don't specify a valid OCAP URL (e.g. a value is
     *         out of range).
     */
    public OcapLocator(int sourceID, short streamType[],
                        int index[], int eventID,
                        String pathSegments)
            throws InvalidLocatorException{
       super("") ;
    }


    /**
     * <P>
     * A constructor of this class corresponding to the OCAP URL form
     * "ocap://source_id[.+PID{&PID}][;event_id]{/path_segments}".
     * Some of parameters can be omitted according to the OCAP URL BNF
     * definition.
     * </P><P>
     * Note that the OcapLocator does not automatically transform this
     * OCAP URL BNF form to any other form, even if the getFrequency() etc.
     * is called.
     * </P>
     *
     * @param sourceID a source_id value for the OCAP URL.
     *
     * @param PID a PID value for the OCAP URL.
     *         The PID shall be a zero length array, if it is omitted
     *         in the OCAP URL.
     *
     * @param eventID an event_id value for the OCAP URL.
     *         The event_id shall be -1, if it is omitted in the OCAP URL.
     *
     * @param pathSegments a path_segments value for the OCAP URL.
     *         The pathSegments shall be null, if it is omitted in the
     *         OCAP URL.
     *
     * @throws InvalidLocatorException if the parameters to construct the
     *         locator don't specify a valid OCAP URL (e.g. a value is
     *         out of range).
     */
    public OcapLocator(int sourceID, int PID[], int eventID,
                        String pathSegments)
            throws InvalidLocatorException{
       super("") ;
    }


    /**
     * <P>
     * A constructor of this class corresponding to the OCAP URL form
     * "ocap://source_id[.$component_name{&component_name}]
     * [;event_id]{/path_segments}".
     * Some of parameters can be omitted according to the OCAP URL BNF
     * definition.
     * </P><P>
     * Note that the OcapLocator does not automatically transform this
     * OCAP URL BNF form to any other form, even if the getFrequency() etc.
     * is called.
     * </P>
     *
     * @param sourceID a source_id value for the OCAP URL.
     *
     * @param componentName a component_name value for the OCAP URL.
     *         The component_name shall be a zero length array, if it
     *         is omitted in the OCAP URL.
     *
     * @param eventID an event_id value for the OCAP URL.
     *         The event_id shall be -1, if it is omitted in the OCAP URL.
     *
     * @param pathSegments a path_segments value for the OCAP URL.
     *         The pathSegments shall be null, if it is omitted in the
     *         OCAP URL.
     *
     * @throws InvalidLocatorException if the parameters to construct the
     *         locator don't specify a valid OCAP URL (e.g. a value is
     *         out of range).
     */
    public OcapLocator(int sourceID, String componentName[], int eventID,
                        String pathSegments)
            throws InvalidLocatorException{
       super("") ;
    }

    /**
     * <P>
     * A constructor of this class corresponding to the OCAP URL form
     * "ocap://source_id[.@component_tag{&component_tag}][;event_id]{/path_segments}".
     * Some of parameters can be omitted according to the OCAP URL BNF
     * definition.
     * </P><P>
     * Note that the OcapLocator does not automatically transform this
     * OCAP URL BNF form to any other form, even if the getFrequency() etc.
     * is called.
     * </P><P>
     * <STRONG>WARNING:</STRONG> Note that the parameter order for this
     * constructor is different from other OcapLocator constructors - the
     * eventId is <EM>before</EM> the componentTags.  If you are an OCAP
     * application author and you get it wrong, your program will compile
     * and run but it will be calling the constructor that expects a list
     * of PIDs instead.
     * </P>
     *
     * @param sourceID a source_id value for the OCAP URL.
     *
     * @param eventID an event_id value for the OCAP URL.
     *         The event_id shall be -1, if it is omitted in the OCAP URL.
     *
     * @param componentTags a component_tag value for the OCAP URL.
     *         The component_tag shall be a zero length array, if it
     *         is omitted in the OCAP URL.
     *
     * @param pathSegments a path_segments value for the OCAP URL.
     *         The pathSegments shall be null, if it is omitted in the
     *         OCAP URL.
     *
     * @throws InvalidLocatorException if the parameters to construct the
     *         locator don't specify a valid OCAP URL (e.g. a value is
     *         out of range).
     */
    public OcapLocator(int sourceID, int eventID, int[] componentTags,
                        String pathSegments)
            throws InvalidLocatorException{
       super("") ;
    }

    /**
     * <P>
     * A constructor of this class corresponding to the OCAP URL form
     * "ocap://n=service_name[.stream_type[,ISO_639_language_code]{&
     * stream_type[,ISO_639_language_code]}][;event_id]{/path_segments}".
     * Some of parameters can be omitted according to the OCAP URL BNF
     * definition.
     * </P><P>
     * Note that the OcapLocator does not automatically transform this
     * OCAP URL BNF form to any other form, even if the getFrequency() etc.
     * is called.
     * </P>
     *
     * @param serviceName a service_name value for the OCAP URL.
     *
     * @param streamType a stream_type value for the OCAP URL.
     *         A combination of the streamType[n] and the ISO639LanguageCode[n]
     *         makes a program_element. The streamType shall be a zero
     *         length array, if it is omitted in the OCAP URL.
     *
     * @param ISO639LanguageCode an ISO_639_language_code value for the
     *         OCAP URL.
     *         A combination of the streamType[n] and the ISO639LanguageCode[n]
     *         makes a program_element. The ISO639LanguageCode shall be
     *         a zero length array, if it is omitted in the OCAP URL.
     *
     * @param eventID an event_id value for the OCAP URL.
     *         The event_id shall be -1, if it is omitted in the OCAP URL.
     *
     * @param pathSegments a path_segments value for the OCAP URL.
     *         The pathSegments shall be null, if it is omitted in the
     *         OCAP URL.
     *
     * @throws InvalidLocatorException if the parameters to construct the
     *         locator don't specify a valid OCAP URL (e.g. a value is
     *         out of range).
     */
    public OcapLocator(String serviceName, short streamType[],
                        String ISO639LanguageCode[], int eventID,
                        String pathSegments)
            throws InvalidLocatorException{
       super("") ;
    }


    /**
     * <P>
     * A constructor of this class corresponding to the OCAP URL form
     * "ocap://n=service_name[.stream_type[,index]{&stream_type[,index]}]
     * [;event_id]{/path_segments}".
     * Some of parameters can be omitted according to the OCAP URL BNF
     * definition.
     * </P><P>
     * Note that the OcapLocator does not automatically transform this
     * OCAP URL BNF form to any other form, even if the getFrequency() etc.
     * is called.
     * </P>
     *
     * @param serviceName a service_name value for the OCAP URL.
     *
     * @param streamType a stream_type value for the OCAP URL.
     *         A combination of the streamType[n] and the index[n] makes
     *         a program_element. The streamType shall be a zero
     *         length array, if it is omitted in the OCAP URL.
     *
     * @param index an index value for the OCAP URL.
     *         A combination of the streamType[n] and the index[n] makes
     *         a program_element. The index shall be a zero length
     *         array, if it is omitted in the OCAP URL.
     *
     * @param eventID an event_id value for the OCAP URL.
     *         The event_id shall be -1, if it is omitted in the OCAP URL.
     *
     * @param pathSegments a path_segments value for the OCAP URL.
     *         The pathSegments shall be null, if it is omitted in the
     *         OCAP URL.
     *
     * @throws InvalidLocatorException if the parameters to construct the
     *         locator don't specify a valid OCAP URL (e.g. a value is
     *         out of range).
     */
    public OcapLocator(String serviceName, short streamType[],
                        int index[], int eventID,
                        String pathSegments)
            throws InvalidLocatorException{
       super("") ;
    }


    /**
     * <P>
     * A constructor of this class corresponding to the OCAP URL form
     * "ocap://n=service_name[.+PID{&PID}][;event_id]{/path_segments}".
     * Some of parameters can be omitted according to the OCAP URL BNF
     * definition.
     * </P><P>
     * Note that the OcapLocator does not automatically transform this
     * OCAP URL BNF form to any other form, even if the getFrequency() etc.
     * is called.
     * </P>
     *
     * @param serviceName a service_name value for the OCAP URL.
     *
     * @param PID a PID value for the OCAP URL.
     *         The PID shall be a zero length array, if it is omitted
     *         in the OCAP URL.
     *
     * @param eventID an event_id value for the OCAP URL.
     *         The event_id shall be -1, if it is omitted in the OCAP URL.
     *
     * @param pathSegments a path_segments value for the OCAP URL.
     *         The pathSegments shall be null, if it is omitted in the
     *         OCAP URL.
     *
     * @throws InvalidLocatorException if the parameters to construct the
     *         locator don't specify a valid OCAP URL (e.g. a value is
     *         out of range).
     */
    public OcapLocator(String serviceName, int PID[], int eventID,
                        String pathSegments)
            throws InvalidLocatorException{
       super("") ;
    }


    /**
     * <P>
     * A constructor of this class corresponding to the OCAP URL form
     * "ocap://n=service_name[.$component_name{&component_name}]
     * [;event_id]{/path_segments}".
     * Some of parameters can be omitted according to the OCAP URL BNF
     * definition.
     * </P><P>
     * Note that the OcapLocator does not automatically transform this
     * OCAP URL BNF form to any other form, even if the getFrequency() etc.
     * is called.
     * </P>
     *
     * @param serviceName a service_name value for the OCAP URL.
     *
     * @param componentName a component_name value for the OCAP URL.
     *         The component_name shall be a zero length array, if it
     *         is omitted in the OCAP URL.
     *
     * @param eventID an event_id value for the OCAP URL.
     *         The event_id shall be -1, if it is omitted in the OCAP URL.
     *
     * @param pathSegments a path_segments value for the OCAP URL.
     *         The pathSegments shall be null, if it is omitted in the
     *         OCAP URL.
     *
     * @throws InvalidLocatorException if the parameters to construct the
     *         locator don't specify a valid OCAP URL (e.g. a value is
     *         out of range).
     */
    public OcapLocator(String serviceName, String componentName[], int eventID,
                        String pathSegments)
            throws InvalidLocatorException{
       super("") ;
    }

    /**
     * <P>
     * A constructor of this class corresponding to the OCAP URL form
     * "ocap://n=service_name[.@component_tag{&component_tag}][;event_id]{/path_segments}".
     * Some of parameters can be omitted according to the OCAP URL BNF
     * definition.
     * </P><P>
     * Note that the OcapLocator does not automatically transform this
     * OCAP URL BNF form to any other form, even if the getFrequency() etc.
     * is called.
     * </P><P>
     * <STRONG>WARNING:</STRONG> Note that the parameter order for this
     * constructor is different from other OcapLocator constructors - the
     * eventId is <EM>before</EM> the componentTags.  If you are an OCAP
     * application author and you get it wrong, your program will compile
     * and run but it will be calling the constructor that expects a list
     * of PIDs instead.
     * </P>
     *
     * @param serviceName a service_name value for the OCAP URL.
     *
     * @param eventID an event_id value for the OCAP URL.
     *         The event_id shall be -1, if it is omitted in the OCAP URL.
     *
     * @param componentTags a component_tag value for the OCAP URL.
     *         The component_tag shall be a zero length array, if it
     *         is omitted in the OCAP URL.
     *
     * @param pathSegments a path_segments value for the OCAP URL.
     *         The pathSegments shall be null, if it is omitted in the
     *         OCAP URL.
     *
     * @throws InvalidLocatorException if the parameters to construct the
     *         locator don't specify a valid OCAP URL (e.g. a value is
     *         out of range).
     */
    public OcapLocator(String serviceName, int eventID, int[] componentTags,
                        String pathSegments)
            throws InvalidLocatorException{
       super("") ;
    }


    /**
     * <P>
     * A constructor of this class corresponding to the OCAP URL form
     * "ocap://(oobfdc|f=frequency).program_number[.m=modulation_format]
     * [.stream_type[,ISO_639_language_code]
     * {&stream_type[,ISO_639_language_code]}][;event_id]{/path_segments}".
     * Some of parameters can be omitted according to the OCAP URL BNF
     * definition.
     * </P><P>
     * Note that the OcapLocator does not automatically transform this
     * OCAP URL BNF form to any other form, even if the getFrequency() etc.
     * is called.
     * </P>
     *
     * @param frequency a frequency value for the OCAP URL in hertz. If
     * the value is -1 then "oobfdc" is used instead of the frequency
     * term and the modulationFormat parameter is ignored.
     *
     * @param programNumber a program_number value for the OCAP URL
     *
     * @param modulationFormat a value representing a modulation_format as 
     *         specified in SCTE 65. If the value is 0xFF the modulation_format
     *         is treated as NTSC analog and the programNumber parameter is 
     *         ignored. If the value is -1 the modulation_format is not 
     *         specified and the modulation_format term will not be included in 
     *         the locator constructed.
     *
     * @param streamType a stream_type value for the OCAP URL.
     *         A combination of the streamType[n] and the ISO639LanguageCode[n]
     *         makes a program_element. The streamType shall be a zero
     *         length array, if it is omitted in the OCAP URL.
     *
     * @param ISO639LanguageCode an ISO_639_language_code value for the
     *         OCAP URL.
     *         A combination of the streamType[n] and the ISO639LanguageCode[n]
     *         makes a program_element. The ISO639LanguageCode shall be
     *         a zero length array, if it is omitted in the OCAP URL.
     *         If ISO639LanguageCode is not a zero-length array, it shall
     *         be an array with the same length as streamType.  If
     *         ISO639LanguageCode[n] is null, then the language code for
     *         streamType[n] is omitted in the OCAP URL.
     *
     * @param eventID an event_id value for the OCAP URL.
     *         The event_id shall be -1, if it is omitted in the OCAP URL.
     *
     * @param pathSegments a path_segments value for the OCAP URL.
     *         The pathSegments shall be null, if it is omitted in the
     *         OCAP URL.
     *
     * @throws InvalidLocatorException if the parameters to construct the
     *         locator don't specify a valid OCAP URL (e.g. a value is
     *         out of range).
     */
    public OcapLocator(int frequency, int programNumber, int modulationFormat,
                       short streamType[], String ISO639LanguageCode[], int eventID,
                        String pathSegments)
            throws InvalidLocatorException{
       super("") ;
    }


    /**
     * <P>
     * A constructor of this class corresponding to the OCAP URL form
     * "ocap://(oobfdc|f=frequency).program_number.[m=modulation_format]
     * [.stream_type[,index]{&stream_type[,index]}]
     * [;event_id]{/path_segments}".
     * Some of parameters can be omitted according to the OCAP URL BNF
     * definition.
     * </P><P>
     * Note that the OcapLocator does not automatically transform this
     * OCAP URL BNF form to any other form, even if the getFrequency() etc.
     * is called.
     * </P>
     *
     * @param frequency a frequency value for the OCAP URL in hertz. If
     * the value is -1 then "oobfdc" is used instead of the frequency
     * term and the modulationFormat parameter is ignored.
     *
     * @param programNumber a program_number value for the OCAP URL
     *
     * @param modulationFormat a value representing a modulation_format as 
     *         specified in SCTE 65. If the value is 0xFF the modulation_format
     *         is treated as NTSC analog and the programNumber parameter is 
     *         ignored. If the value is -1 the modulation_format is not 
     *         specified and the modulation_format term will not be included in 
     *         the locator constructed.
     *
     * @param streamType a stream_type value for the OCAP URL.
     *         A combination of the streamType[n] and the index[n] makes
     *         a program_element. The streamType shall be a zero
     *         length array, if it is omitted in the OCAP URL.
     *
     * @param index an index value for the OCAP URL.
     *         A combination of the streamType[n] and the index[n] makes
     *         a program_element. The index shall be a zero length
     *         array, if it is omitted in the OCAP URL.
     *         If index is not a zero-length array, it shall be an array
     *         with the same length as streamType.  If index[n] is -1,
     *         then the index for streamType[n] is omitted in the OCAP URL.
     *
     * @param eventID an event_id value for the OCAP URL.
     *         The event_id shall be -1, if it is omitted in the OCAP URL.
     *
     * @param pathSegments a path_segments value for the OCAP URL.
     *         The pathSegments shall be null, if it is omitted in the
     *         OCAP URL.
     *
     * @throws InvalidLocatorException if the parameters to construct the
     *         locator don't specify a valid OCAP URL (e.g. a value is
     *         out of range).
     */
    public OcapLocator(int frequency, int programNumber, int modulationFormat,
                       short streamType[], int index[], int eventID, String pathSegments)
            throws InvalidLocatorException{
       super("") ;
    }



    /**
     * <P>
     * A constructor of this class corresponding to the OCAP URL form
     * "ocap://(oobfdc|f=frequency).program_number[.m=modulation_format]
     * [.+PID{&PID}][;event_id]{/path_segments}".
     * Some of parameters can be omitted according to the OCAP URL BNF
     * definition.
     * </P><P>
     * Note that the OcapLocator does not automatically transform this
     * OCAP URL BNF form to any other form, even if the getFrequency() etc.
     * is called.
     * </P>
     *
     * @param frequency a frequency value for the OCAP URL in hertz. If
     * the value is -1 then "oobfdc" is used instead of the frequency
     * term and the modulationFormat parameter is ignored.
     *
     * @param programNumber a program_number value for the OCAP URL
     *
     * @param modulationFormat a value representing a modulation_format as 
     *         specified in SCTE 65. If the value is 0xFF the modulation_format
     *         is treated as NTSC analog and the programNumber parameter is 
     *         ignored. If the value is -1 the modulation_format is not 
     *         specified and the modulation_format term will not be included in 
     *         the locator constructed.
     *
     * @param PID a PID value for the OCAP URL.
     *         The PID shall be a zero length array, if it is omitted
     *         in the OCAP URL.
     *
     * @param eventID an event_id value for the OCAP URL.
     *         The event_id shall be -1, if it is omitted in the OCAP URL.
     *
     * @param pathSegments a path_segments value for the OCAP URL.
     *         The pathSegments shall be null, if it is omitted in the
     *         OCAP URL.
     *
     * @throws InvalidLocatorException if the parameters to construct the
     *         locator don't specify a valid OCAP URL (e.g. a value is
     *         out of range).
     */
    public OcapLocator(int frequency, int programNumber, int modulationFormat,
                       int PID[], int eventID, String pathSegments)
            throws InvalidLocatorException{
       super("") ;
    }


    /**
     * <P>
     * A constructor of this class corresponding to the OCAP URL form
     * "ocap://(oobfdc|f=frequency).program_number[.m=modulation_format]
     * [.$component_name{&component_name}]
     * [;event_id]{/path_segments}".
     * Some of parameters can be omitted according to the OCAP URL BNF
     * definition.
     * </P><P>
     * Note that the OcapLocator does not automatically transform this
     * OCAP URL BNF form to any other form, even if the getFrequency() etc.
     * is called.
     * </P>
     *
     * @param frequency a frequency value for the OCAP URL in hertz. If
     * the value is -1 then "oobfdc" is used instead of the frequency
     * term and the modulationFormat parameter is ignored.
     *
     * @param programNumber a program_number value for the OCAP URL
     *
     * @param modulationFormat a value representing a modulation_format as 
     *         specified in SCTE 65. If the value is 0xFF the modulation_format
     *         is treated as NTSC analog and the programNumber parameter is 
     *         ignored. If the value is -1 the modulation_format is not 
     *         specified and the modulation_format term will not be included in 
     *         the locator constructed.
     *
     * @param componentName a component_name value for the OCAP URL.
     *         The component_name shall be a zero length array, if it
     *         is omitted in the OCAP URL.
     *
     * @param eventID an event_id value for the OCAP URL.
     *         The event_id shall be -1, if it is omitted in the OCAP URL.
     *
     * @param pathSegments a path_segments value for the OCAP URL.
     *         The pathSegments shall be null, if it is omitted in the
     *         OCAP URL.
     *
     * @throws InvalidLocatorException if the parameters to construct the
     *         locator don't specify a valid OCAP URL (e.g. a value is
     *         out of range).
     */
    public OcapLocator(int frequency, int programNumber, int modulationFormat,
                       String componentName[], int eventID, String pathSegments)
            throws InvalidLocatorException{
       super("") ;
    }



    /**
     * <P>
     * A constructor of this class corresponding to the OCAP URL form
     * "ocap://(oobfdc|f=frequency).program_number[.m=modulation_format]
     * [.@component_tag{&component_tag}][;event_id]{/path_segments}".
     * Some of parameters can be omitted according to the OCAP URL BNF
     * definition.
     * </P><P>
     * Note that the OcapLocator does not automatically transform this
     * OCAP URL BNF form to any other form, even if the getFrequency() etc.
     * is called.
     * </P><P>
     * <STRONG>WARNING:</STRONG> Note that the parameter order for this
     * constructor is different from other OcapLocator constructors - the
     * eventId is <EM>before</EM> the componentTags.  If you are an OCAP
     * application author and you get it wrong, your program will compile
     * and run but it will be calling the constructor that expects a list
     * of PIDs instead.
     * </P>
     *
     * @param frequency a frequency value for the OCAP URL in hertz. If
     * the value is -1 then "oobfdc" is used instead of the frequency
     * term and the modulationFormat parameter is ignored.
     *
     * @param programNumber a program_number value for the OCAP URL
     *
     * @param modulationFormat a value representing a modulation_format as 
     *         specified in SCTE 65. If the value is 0xFF the modulation_format
     *         is treated as NTSC analog and the programNumber parameter is 
     *         ignored. If the value is -1 the modulation_format is not 
     *         specified and the modulation_format term will not be included in 
     *         the locator constructed.
     *
     * @param eventID an event_id value for the OCAP URL.
     *         The event_id shall be -1, if it is omitted in the OCAP URL.
     *
     * @param componentTags a component_tag value for the OCAP URL.
     *         The component_tag shall be a zero length array, if it
     *         is omitted in the OCAP URL.
     *
     * @param pathSegments a path_segments value for the OCAP URL.
     *         The pathSegments shall be null, if it is omitted in the
     *         OCAP URL.
     *
     * @throws InvalidLocatorException if the parameters to construct the
     *         locator don't specify a valid OCAP URL (e.g. a value is
     *         out of range).
     */
    public OcapLocator(int frequency, int programNumber, int modulationFormat,
                       int eventID, int[] componentTags, String pathSegments)
            throws InvalidLocatorException{
       super("") ;
    }


    /**
     * This method returns a source_id value of the OCAP URL represented
     * by this OcapLocator instance.
     *
     * @return a source_id value of the OCAP URL represented by this
     *        OcapLocator instance.
     *        If the OCAP URL that is specified to construct an OcapLocator
     *        instance doesn't include it, -1 returns.
     *
     */
    public int getSourceID(){
        return -1;
    }


    /**
     * This method returns a service_name value of the OCAP URL represented
     * by this OcapLocator instance.
     *
     * @return a service_name value of the OCAP URL represented by this
     *        OcapLocator instance.
     *        If the OCAP URL that is specified to construct an OcapLocator
     *        instance doesn't include it, null returns.
     */
    public String getServiceName(){
        return null;
    }


    /**
     * This method returns a frequency value, in hertz, of the OCAP URL
     * represented by this OcapLocator instance.
     *
     * @return a frequency value, in hertz, of the OCAP URL represented
     *        by this OcapLocator instance.
     *        If the OCAP URL that is specified to construct an OcapLocator
     *        instance doesn't include it or the locator is OOB, -1 is
     *        returned. If the getProgramNumber method returns a value
     *        other than -1, the locator is OOB.
     */
    public int getFrequency(){
        return -1;
    }


    /**
     * This method returns a value representing a modulation_format as specified in SCTE 65.
     * A modulation_format value of 0xFF indicates an NTSC analog video format.
     *
     * @return a value representing the modulation format.  If the OCAP URL that is
     *      specified to construct an OcapLocator instance doesn't include it or -1
     *      was passed in as the modulation format, -1 is returned. When the locator contains
     *      a frequency term and this method returns a -1, a default modulation format
     *      value of QAM256 is implied.
     */
    public int getModulationFormat(){
        return -1;
    }


    /**
     * This method returns a program_number value of the OCAP URL represented
     * by this OcapLocator instance.
     *
     * @return a program_number value of the OCAP URL represented by this
     *        OcapLocator instance.
     *        If the OCAP URL that is specified to construct an OcapLocator
     *        instance doesn't include it, -1 returns.
     */
    public int getProgramNumber(){
        return -1;
    }


    /**
     * This method returns a stream_type value of the OCAP URL represented
     * by this OcapLocator instance.
     *
     * @return a stream_type value of the OCAP URL represented by this
     *        OcapLocator instance. The order of stream_types is same as
     *        specified in the constructor.
     *        If the OCAP URL that is specified to construct an OcapLocator
     *        instance doesn't include it, a zero length array returns.
     */
    public short[] getStreamTypes(){
        return null;
    }


    /**
     * This method returns an ISO_639_language_code value of the OCAP URL
     * represented by this OcapLocator instance.
     *
     * @return an ISO_639_language_code value of the OCAP URL represented
     *        by this OcapLocator instance.
     *        The order of ISO_639_language_code is same as specified
     *        in the constructor.
     *        If the OCAP URL that is specified to construct an OcapLocator
     *        instance doesn't include any language codes, a zero length
     *        array returns.
     *        If the OCAP URL that is specified to construct an OcapLocator
     *        instance includes any language codes, an array is returned
     *        that is the same length as that returned by getStreamTypes().
     *        Some of the elements in this array may be null, if no
     *        language was specified for the corresponding stream_type.
     */
    public String[] getLanguageCodes(){
        return null;
    }


    /**
     * This method returns an index value of the OCAP URL
     * represented by this OcapLocator instance.
     *
     * @return an index value of the OCAP URL represented
     *         by this OcapLocator instance.
     *         The order of index is same as specified in the
     *         constructor.
     *         If the OCAP URL that is specified to construct an OcapLocator
     *         instance doesn't include any indexes, a zero length
     *        array returns.
     *        If the OCAP URL that is specified to construct an OcapLocator
     *        instance includes any indexes, an array is returned
     *        that is the same length as that returned by getStreamTypes().
     *        Some of the elements in this array may be -1, if no
     *        index was specified for the corresponding stream_type.
     */
    public int[] getIndexes(){
        return null;
    }


    /**
     * This method returns an event_id value of the OCAP URL represented
     * by this OcapLocator instance.
     *
     * @return an event_id value of the OCAP URL represented by this
     *        OcapLocator instance.
     *        If the OCAP URL that is specified to construct an OcapLocator
     *        instance doesn't include it, -1 returns.
     */
    public int getEventId(){
        return -1;
    }


    /**
     * This method returns a PID value of the OCAP URL represented
     * by this OcapLocator instance.
     *
     * @return a PID value of the OCAP URL represented by this
     *        OcapLocator instance.
     *        The order of PID is same as specified in the constructor.
     *        If the OCAP URL that is specified to construct an OcapLocator
     *        instance doesn't include it, a zero length array returns.
     */
    public int[] getPIDs(){
        return null;
    }


    /**
     * This method returns a component_name value of the OCAP URL
     * represented by this OcapLocator instance.
     *
     * @return a component_name value of the OCAP URL represented
     *        by this OcapLocator instance.
     *        The order of component_name is same as specified in the
     *        constructor.
     *        If the OCAP URL that is specified to construct an OcapLocator
     *        instance doesn't include it, a zero length array returns.
     */
    public String[] getComponentNames(){
        return null;
    }


    /**
     * This method returns a component_tag value of the OCAP URL
     * represented by this OcapLocator instance.
     *
     * @return a component_tag value of the OCAP URL represented
     *        by this OcapLocator instance.
     *        The order of component_tags is same as specified in the
     *        constructor.
     *        If the OCAP URL that is specified to construct an OcapLocator
     *        instance doesn't include it, a zero length array returns.
     */
    public int[] getComponentTags(){
        return null;
    }


    /**
     * This method returns a path_segments string of the OCAP URL
     * represented by this OcapLocator instance.
     *
     * @return a path_segments string of the OCAP URL represented
     *        by this OcapLocator instance.
     *        If the OCAP URL that is specified to construct an OcapLocator
     *        instance doesn't include it, null returns.
     */
    public String getPathSegments(){
        return null;
    }
}

