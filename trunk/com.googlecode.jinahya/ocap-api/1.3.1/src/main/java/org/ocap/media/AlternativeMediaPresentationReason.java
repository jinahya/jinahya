package org.ocap.media;

/**
 * This interface represents possible reasons that lead to alternative media
 * presentation. Registered {@link MediaAccessHandler} can define its own 
 * reason and pass them to the OCAP implementation through the 
 * MediaAccessHandler.checkMediaAccessAuthorization() method.
 */
public interface AlternativeMediaPresentationReason {

    /**
     * Marks the first bit for the range of alternative media 
     * presentation reasons.
     */
    public static final int REASON_FIRST = 0x01;

    /**
	 * Bit indicating that service components are ciphered and the user 
	 * has no entitlement to view all or part of them.
	 */
	public final static int NO_ENTITLEMENT = REASON_FIRST;
	
	/**
	 * Bit indicating that a user dialog for payment is necessary 
	 * before media presentation.  
	 */
	public final static int COMMERCIAL_DIALOG = 0x02;
	
	/**
	 * Reason indicating that media presentation is not authorized 
	 * regarding to the program rating.
	 */
	public final static int RATING_PROBLEM = 0x04;
	
	/**
	 * Bit indicating that media are ciphered and 
	 * the CA does not correspond to ciphering.
	 */
	public final static int CA_UNKNOWN = 0x08;
	
	/**
	 * Bit indicating that broadcast information is 
	 * inconsistent : for example PMT is missing.
	 */
	public final static int BROADCAST_INCONSISTENCY = 0x10;
	
	/**
	 * Bit indicating that hardware resource necessary 
	 * for presenting service components is not available.
	 */
	public final static int HARDWARE_RESOURCE_NOT_AVAILABLE = 0x20;

    /**
     * Marks the last bit for the range of alternative media 
     * presentation reasons.
     */
    public static final int REASON_LAST = HARDWARE_RESOURCE_NOT_AVAILABLE;

}