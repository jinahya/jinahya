/*
 * VBIFilterGroup.java
 *
 * Created on September 18, 2004, 1:36 PM
 */

package org.ocap.media;

import javax.tv.service.selection.*;
import org.davic.resources.*;

/**
 * <p>
 * This class represents a group of VBI data filters. The VBI filters in 
 * a group are attached to a ServiceContext. Current video played back 
 * on the ServiceContext is the target of filtering. If the current video is 
 * analog, the filters retrieve specific data in a specified VBI line of 
 * the video. The data format is defined in each VBI data specification. 
 * If the current video is MPEG video, the filters retrieve specific data 
 * in the user_data found in the MPEG picture headers of the video. Data 
 * format in user_data is defined in ANSI/SCTE 20 and ANSI/SCTE 21. 
 * </p><p>
 * When a new VBIFilterGroup is constructed, an application specifies the 
 * total number of VBIFilters held by it. The application can create a 
 * specified number of new VBIFilter instances via a 
 * {@link VBIFilterGroup#newVBIFilter} method. VBI lines and data format for 
 * filtering are specified in this method. After configuring all 
 * filters, the VBIFilterGroup is attached to a specific ServiceContext. 
 * Current video on the ServiceContext is the target of filtering. 
 * If the startFiltering() method of a VBI filter is called, the filter 
 * starts filtering immediately. 
 * The application can retrieve VBI data via a {@link VBIFilter#getVBIData} 
 * method. The host may be able to filter VBI data by using separated fields
 * (specify either field 1 or field 2 and retrieve data in only the specified 
 * field) or by using mixed fields (retrieve data in both field 1 and 2 in 
 * arrival order). 
 * </p><p>
 * The data unit is defined as follows. 
 * </p><p>
 * <b>XDS (VBI_DATA_FORMAT_XDS)</b><br>
 * XDS packets in line 21 field 2 is defined in EIA-608-B. 
 * It has a start/continue/end code and is interleaved with CC3, CC4, T3 and 
 * T4 service. One XDS packet is a single data unit of this data format. 
 * </p><p>
 * <b>Text service (VBI_DATA_FORMAT_T1/T2/T3/T4)</b><br>
 * Text service data in line 21 field 1 or 2 is defined in EIA-608-B. 
 * It has start/end codes and is interleaved with the other closed captioning 
 * service and Text service according to Section 7 of EIA-608-B. 
 * A single data unit of Text service is a character sequence between a text 
 * mode in code (RTD or TR) and a text mode out code (EOC, RCL, RDC, RU2, 
 * RU3 or RU4). 
 * Note that Text service data unit returned from the VBIFilter doesn't contain 
 * these text mode in/out codes, but contains another control code like EDM etc. 
 * </p><p>
 * <b>Generic EIA-608-B service (VBI_DATA_FORMAT_EIA608B)</b><br>
 * Generic EIA-608-B service represents a character stream that consists of 
 * Character One and Character Two in a field of line 21. 
 * Character One and Character Two are defined in Figure 1 of EIA-608-B. 
 * Note that the VBIFilter only supports separated field filtering since 
 * Character One and Character Two make a separate data stream in each field. 
 * A single data unit of this format is a set of Character One and Character 
 * Two in one VBI line and field (i.e., only two characters). 
 * </p><p>
 * <b>NABTS (VBI_DATA_FORMAT_NABTS)</b><br>
 * NABTS is defined in EIA-516. It is a 33 byte fixed length packet and 
 * consists of 5 byte prefix and 28 byte data block. 
 * Note that the filter retrieves only the "Data packet" part. I.e., one 
 * NABTS packet is a single data unit. 
 * The VBIFilter retrieves multiple NABTS packets in arrival order for all 
 * specified lines and fields. 
 * </p><p>
 * <b>AMOL I/II (VBI_DATA_FORMAT_AMOL_I/II)</b><br>
 * AMOL I and II is defined in ACN 403-1218-024. 
 * It specifies header bits and the number of bits in a VBI line. 
 * The bits in one VBI line/field is a single data unit for this data format, 
 * i.e., the header bits are included in the data unit. 
 * </p><p>
 * <b>UNKNOWN (VBI_DATA_FORMAT_UNKNOWN) </b><br>
 * UNKNOWN represents a VBI waveform that is unknown. To retrieve UNKNOWN 
 * data unit, the OCAP implementation needs to synchronize to the VBI's bit 
 * rate automatically. Filtering success is not guaranteed even if 
 * the OCAP implementation supports the UNKNOWN format, since an analog waveform 
 * is not standardized. 
 * The bits in one VBI line and field is a data unit for UNKNOWN format. 
 * </p><p>
 * Support of the other VBI lines is optional. And support of the ANSI/SCTE 
 * 20 and ANSI/SCTE 21 is also optional. An application can check the supported 
 * filtering lines, data format and filtering techniques (field separated or 
 * field mixed) via capability methods. 
 * </p>
 *
 * @author  Shigeaki Watanabe (Panasonic)
 */
public class VBIFilterGroup implements org.davic.resources.ResourceProxy,
                                    org.davic.resources.ResourceServer {
    /**
     * Represents a XDS data format of line 21 field 2. 
     */
    public static final int VBI_DATA_FORMAT_XDS = 1;

    /**
     * Represents a Text service T1 format of line 21 field 1. 
     */
    public static final int VBI_DATA_FORMAT_T1 = 2;

    /**
     * Represents a Text service T2 format of line 21 field 1. 
     */
    public static final int VBI_DATA_FORMAT_T2 = 3;

    /**
     * Represents a Text service T3 format of line 21 field 2. 
     */
    public static final int VBI_DATA_FORMAT_T3 = 4;

    /**
     * Represents a Text service T4 format of line 21 field 2. 
     */
    public static final int VBI_DATA_FORMAT_T4 = 5;

    /**
     * Represents a EIA-608-B generic Character One and Character Two format 
     * of line 21 field 1 or 2. 
     */
    public static final int VBI_DATA_FORMAT_EIA608B = 6;

    /**
     * Represents an NABTS data format defined by EIA-516. 
     */
    public static final int VBI_DATA_FORMAT_NABTS = 7;

    /**
     * Represents an AMOL I data format defined by ACN 403-1218-024. 
     */
    public static final int VBI_DATA_FORMAT_AMOL_I = 8;

    /**
     * Represents an AMOL II data format defined by ACN 403-1218-024. 
     */
    public static final int VBI_DATA_FORMAT_AMOL_II = 9;

    /**
     * Represents an unknown. I.e., all data bits in the VBI line are 
     * retrieved. 
     */
    public static final int VBI_DATA_FORMAT_UNKNOWN = 10;

    /**
     * Represents filtering of VBI data in field 1 only. 
     */
    public static final int FIELD_1 = 1;

    /**
     * Represents filtering of VBI data in field 2 only. 
     */
    public static final int FIELD_2 = 2;

    /**
     * Represents filtering of VBI data in both field 1 and field 2 in 
     * arrival order. 
     */
    public static final int FIELD_MIXED = 3;



    /**
     * Creates a VBIFilterGroup instance that includes the specified number 
     * of VBI filters. The VBI filters are reserved when the 
     * {@link VBIFilterGroup#attach} method is called. 
     *
     * @param numberOfFilters  the number of requested VBI filters held by 
     *             a new VBIFilterGroup instance. 
     *
     * @throws SecurityException  if the caller doesn't have 
     *             MonitorAppPermission("vbifiltering"). 
     */
    public VBIFilterGroup(int numberOfFilters){
    }


    /**
     * Create a new VBIFilter instance within a VBIFilterGroup instance. 
     * The VBIFilter filters the specified VBI line/field and retrieves 
     * data units of the specified format. 
     * The filter has an internal buffer of the specified size to store the 
     * filtered data units. If the filtered data size exceeds the buffer size, 
     * the oversized part of data will be lost. 
     *
     * @return  a new VBIFilter instance. Total number of filters 
     *            created by a VBIFilterGroup shall not exceed the 
     *            number of filters specified by a constructor. 
     *
     * @param lineNumber  an array of VBI line numbers that will be filtered 
     *            by a returned SimpleVBIFilter. 
     *            If line 21 is specified, the filter also filters user_data 
     *            defined in ANSI/SCTE 20 and ANSI/SCTE 21, when a current 
     *            video is MPEG. Note that filtering of user_data is optional. 
     *
     * @param field  a field number that will be filtered by a returned 
     *            VBIFilter. One of the constants that has a FIELD_ prefix. 
     *            <br>
     *            If FIELD_MIXED is specified, the VBIFilter filters 
     *            both field 1 and 2 of the specified lineNumber lines. 
     *            Filtered data is concatenated in arrival order. <br>
     *            If FIELD_1 is specified, the VBIFilter filters only 
     *            field 1 of the specified lineNumber lines. <br>
     *            If FIELD_2 is specified, the VBIFilter filters only 
     *            field 2 of the specified lineNumber lines. <br>
     *
     * @param dataFormat  one of constants that has a VBI_DATA_FORMAT_ prefix 
     *            to specify a data format to be filtered. 
     *
     * @param unitLength  specify the number of bits (not bytes) of a single 
     *            data unit for the VBI_DATA_FORMAT_UNKNOWN format. 
     *            This value shall be ignored for the other well known 
     *            formats. 
     *
     * @param bufferSize  number of bytes to specify size of an internal 
     *            buffer used to hold a VBI data unit. 
     *
     * @throws IllegalStateException  if the total number of filters created 
     *            by this VBIFilterGroup exceeds the number specified by the 
     *            constructor. 
     *
     * @throws IllegalArgumentException  if this VBIFilterGroup can't create 
     *            a VBIFilter for specified parameters due to hardware 
     *            or software restrictions, or the lineNumber is not capable 
     *            line, or if the field is not defined in constants, 
     *            or the specified dataFormat is not defined in constants, 
     *            or if the bufferSize is 0. 
     *            For example, if this method is called with lineNumber=21, 
     *            field=FIELD_1 and dataFormat=VBI_DATA_FORMAT_XDS, this 
     *            exception is thrown and null returns. 
     */
    public VBIFilter newVBIFilter(int lineNumber[], int field, 
                    int dataFormat, int unitLength, int bufferSize) {
        return null;
    }

    /**
     * This method attempts to reserve all VBI filters held by this 
     * VBIFilterGroup, and attaches to a specified ServiceContext. After the 
     * method call, the filter starts filtering of VBI data units contained 
     * in current selected video on the ServiceContext responding to a 
     * {@link VBIFilter#startFiltering} call. 
     * If the startFiltering method has already been called, the filter 
     * starts filtering immediately. 
     * Note that the number of filters specified by a constructor shall be 
     * created by the newVBIFilter() method before calling this method. 
     * (I.e., VBI lines and a data format shall be specified before resource 
     * reservation.) 
     *
     * @param serviceContext  a ServiceContext that selects the video to be 
     *            filtered. It is not necessary that a specified 
     *            ServiceContext is in the <em>presenting</em> state when this
     *            method is called. 
     *
     * @param client  DAVIC ResourceClient to be used for resource management. 
     *
     * @param requestData  application specific data to be used for resource 
     *            management. Null is possible. 
     *
     * @throws IllegalArgumentException  if the serviceContext or the client 
     *            is null. Note that requestData may be null. 
     *
     * @throws IllegalStateException  if this method is called before creating 
     *            all filters in this VBIFilterGroup. 
     */
    public void attach(ServiceContext serviceContext, ResourceClient client, 
                    Object requestData) {
    }


    /**
     * Releases all filters in this VBIFilterGroup and terminate the connection 
     * to the ServiceContext. All filtering by filters in this group 
     * terminates immediately. 
     *
     * @throws IllegalStateException  if this method is called when the 
     *             filters have already been released and this method does 
     *             nothing. 
     */
    public void detach() {
    }


    /**
     * Returns a ServiceContext instance specified by a 
     * {@link VBIFilterGroup#attach} method. 
     *
     * @return a ServiceContext instance specified by a 
     *             {@link VBIFilterGroup#attach} method. 
     *             Null if no ServiceContext is attached at the moment. 
     */
    public ServiceContext getServiceContext() {
        return null;
    }


    /**
     * Returns true if field separated filtering of the specified VBI line 
     * numbers with the specified data format is supported by this 
     * VBIFilterGroup. 
     * At a minimum, either this method or the 
     * {@link VBIFilterGroup#getMixedFilteringCapability} method 
     * shall return true for the line 21 with CC1, CC2, CC3, CC4, T1, T2, T3, 
     * T4 and XDS format. 
     *
     * @return  true if the host supports field separated filtering of the 
     *             specified line numbers with the specified data format 
     *             (i.e., the newVBIFilter() method accepts FIELD_1 or 
     *             FIELD_2). 
     *             Otherwise returns false. 
     *
     * @param lineNumber  an array of line numbers used to determine 
     *             filtering capability. 
     *
     * @param dataFormat  a data format used to determine filtering 
     *             capability. 
     */
    public boolean getSeparatedFilteringCapability(int lineNumber[], 
                        int dataFormat) {
        return true;
    }


    /**
     * Returns true if field mixed filtering of the specified VBI line 
     * numbers with the specified data format is supported by this 
     * VBIFilterGroup. 
     * At a minimum, either this method or the 
     * {@link VBIFilterGroup#getSeparatedFilteringCapability} method 
     * shall return true for the line 21 with CC1, CC2, CC3, CC4, T1, 
     * T2, T3, T4 and XDS format.
     *
     * @return  true if the host supports field mixed filtering of the 
     *             specified line numbers with the specified data format 
     *             (i.e., the newVBIFilter() method accepts FIELD_MIXED). 
     *             Otherwise false returns. 
     *
     * @param lineNumber  a line number used to determine filtering 
     *             capability. 
     *
     * @param dataFormat  a data format used to determine filtering 
     *             capability. 
     */
    public boolean getMixedFilteringCapability(int lineNumber[], 
                         int dataFormat) {
        return true;
    }


    /**
     * Indicates if the host supports line 21 VBI data retrieval from 
     * user_data in MPEG picture headers as defined in ANSI/SCTE 20. 
     * See also a {@link VBIFilterGroup#newVBIFilter} method. 
     *
     * @return  true if the host can retrieve line 21 data from user_data 
     *             in MPEG picture headers as defined in ANSI/SCTE 20. 
     */
    public boolean getSCTE20Capability() {
        return true;
    }


    /**
     * Indicates if the host supports line 21 VBI data retrieval from 
     * user_data in MPEG picture headers as defined in ANSI/SCTE 21. 
     * See also a {@link VBIFilterGroup#newVBIFilter} method. 
     *
     * @return  true if the host can retrieve line 21 data from user_data 
     *             in MPEG picture headers as defined in ANSI/SCTE 21. 
     */
   public boolean getSCTE21Capability() {
        return true;
    }


    /**
     * Returns the ResourceClient object specified in the attach() method. 
     *
     * @return  the ResourceClient object specified in the last call of the 
     *             {@link VBIFilterGroup#attach} method. 
     *             If the attach() method has never been called or the 
     *             {@link VBIFilterGroup#detach} method is called to cancel 
     *             an attach call, this method returns null. 
     */
    public ResourceClient getClient(){
        return null;
    }


    /**
     * Add a listener object to be notified of changes in the status of 
     * resources related to VBI filters. 
     *
     * @param listener  an object implementing the ResourceStatusListener 
     *             interface. 
     *             Multiple calls with a same listener instance is simply 
     *             ignored with throwing no exception. 
     *
     * @throws IllegalArgumentException  if the listener is null. 
     */
    public void addResourceStatusEventListener(
                            ResourceStatusListener listener) {
    }


    /**
     * Remove a specified listener object from this VBIFilterGroup. 
     *
     * @param listener  an object implementing the ResourceStatusListener 
     *             interface. 
     *             This method does nothing if the specified ccListener is 
     *             null, not previously added or already removed. 
     *
     * @throws IllegalArgumentException  if the listener is null. 
     */
    public void removeResourceStatusEventListener(
                            ResourceStatusListener listener) {
    }

}

