/*
 * ProgramMapTableManager.java
 */
package org.ocap.si;

/**
 * The Program Map Table (PMT) manager is used to discover and listen 
 * for MPEG-2 PMTs.
 * To retrieve the PMT, an application add the TableChangeListener to 
 * the ProgramMapTableManager via the addInBandChangeListener() or the 
 * addOutOfBandChangeListener(), and call the retrieveInBand() or the 
 * retrieveOutOfBand(). If PMT has changed, ProgramMapTableManager call 
 * TableChangeListener.NotifyChange() to notify it. 
 * The application must get updated ProgramMapTable object via the 
 * SIChangeEvent.getSIElement() to keep the PMT table fresh when the 
 * PMT change is notified, i.e., ProgramMapTable object is not updated 
 * automatically.
 */
public abstract class ProgramMapTableManager
{
    /**
     * For Singleton behavior
     */
    protected ProgramMapTableManager ()
    {
    }
    
    /**
     * Get an instance of the Program Map Table Manager.
     *
     * @return The ProgramMapTableManager instance.
     */
    public static ProgramMapTableManager getInstance()
    {
        /*
         * Actual implementation is vendor dependent.
         */
        return null;
    }


    /** 
     * Add a TableChangeListener object that will be notified when 
     * the inband PMT changes. 
     * If the specified TableChangeListener object is already added, 
     * no action is performed.
     * <tt>javax.tv.service.SIRequestFailureType.DATA_UNAVAILABLE</tt>
     * is returned via <tt>SIRequestor.notifyFailure()</tt>
     * if the locator refers to an analog service.
     *
     * @param listener  A TableChangeListener object to be notified 
     *                  when the inband PMT changes.
     *
     * @param locator    A locator to specify a virtual channel carrying 
     *                   the inband PMTs.  Should correspond to one of the
     *                   following OCAP URL forms:
     *                   ocap://source_id, ocap://n=service_name, ocap://f=frequency.program_number
     *
     * @throws IllegalArgumentException
     *     This exception is thrown when the <code>locator</code> parameter is
     *     not in the form of a valid OCAP URL as specified by this method.
     */
    public abstract void addInBandChangeListener(
                                TableChangeListener listener, 
                                javax.tv.locator.Locator locator);


    /** 
     * Add a TableChangeListener object that will be notified when 
     * the out-of-band PMT changes. 
     * If the specified TableChangeListener object is already added, 
     * no action is performed.
     *
     * @param listener  A TableChangeListener object to be notified 
     *                  when the out-of-band PMT changes.
     *
     * @param programNumber  A program number of the PMT from the 
     *                  corresponding PAT. 
     */
    public abstract void addOutOfBandChangeListener(
                                TableChangeListener listener, 
                                int programNumber);


    /** 
     * Remove the TableChangedListener object for the inband PMT.
     *
     * @param listener  The TableChangeListener object to be removed. 
     */
    public abstract void removeInBandChangeListener(
                                TableChangeListener listener);


    /** 
     * Remove the TableChangedListener object for the OOB PMT.
     *
     * @param listener  The TableChangeListener object to be removed. 
     */
    public abstract void removeOutOfBandChangeListener(
                                TableChangeListener listener);


    /** 
     * <P>
     * Retrieve a PMT from the in-band channel (transport stream)
     * identified by the Locator parameter.
     * <P></P>
     * The OCAP implementation does not automatically tune to the
     * transport stream specified by the Locator.
     * Hence, the calling application must tune to the corresponding
     * transport stream before calling this method.
     * <P></P>
     * The attempt to retrieve a PMT stops silently and permanently
     * when the network interface starts tuning to another transport
     * stream.  In this case, the registered
     * <tt>SIRequestor.notifyFailure()</tt>
     * method is invoked with a failure type of
     * <tt>javax.tv.service.SIRequestFailureType.DATA_UNAVAILABLE</tt>.
     * </P><P>
     * It is not guaranteed that the transport stream specified 
     * by the Locator is still tuned when the method of the SIRequestor 
     * is called back.
     * <P></P>
     * Note:  If an application has added a listener via the
     *  <tt>addInBandChangeListener()</tt> method,
     * the <tt>TableChangeListener.notifyChange()</tt> method
     * is called when the specified PMT is updated.  In this case,
     * the PMT returned to the <tt>SIRequestor</tt> registered with
     * this method may have expired.
     * </P>
     * <tt>javax.tv.service.SIRequestFailureType.DATA_UNAVAILABLE</tt>
     * is returned via <tt>SIRequestor.notifyFailure()</tt>
     * if the locator refers to an analog service.
     *
     * @param requestor  The SIRequestor object to be called back with 
     *                   the retrieval result, when the PMT is discovered.
     *
     * @param locator    A locator to specify a virtual channel carrying
     *                   the inband PMTs.   Should correspond to one of
     *                   the following OCAP URL forms:
     *                   ocap://source_id, ocap://n=service_name, ocap://f=frequency.program_number
     *
     * @return           The SIRequest object that identifies this 
     *                   asynchronous retrieval request and allows the 
     *                   request to be cancelled.
     *                   
     * @throws IllegalArgumentException
     *     This exception is thrown when the <code>locator</code> parameter is
     *     not in the form of a valid OCAP URL as specified by this method.
     */
    public abstract javax.tv.service.SIRequest retrieveInBand(
                            javax.tv.service.SIRequestor requestor,
                            javax.tv.locator.Locator locator);


    /** 
     * Retrieve the PMT from the out-of-band channel.  If there is 
     * no OOB PMT the SIRequestor.notifyFailure method will be 
     * called with a failure type of 
     * javax.tv.service.SIRequestFailureType.DATA_UNAVAILABLE.
     *
     * @param requestor  The SIRequestor object to be called back with 
     *                   the retrieval result.
     *
     * @param programNumber  A program number of the PMT from the 
     *                   corresponding PAT. 
     * 
     * @return           The SIRequest object that identifies this 
     *                   asynchronous retrieval request and allows the 
     *                   request to be cancelled.
     */
    public abstract javax.tv.service.SIRequest retrieveOutOfBand(
                            javax.tv.service.SIRequestor requestor, 
                            int programNumber);
}
