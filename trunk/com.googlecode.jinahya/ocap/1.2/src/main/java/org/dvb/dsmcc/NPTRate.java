package org.dvb.dsmcc;

/**
 * Represents the rate at which an NPT time-base progresses. Rates are expressed as
 * the combination of a numerator and a denominator. Instances of
 * this class are constructed by the platform and returned to applications.
 * A rate of 1/1 shall indicate "the standard presentation rate" as defined in the NPT specification.
 * @since MHP 1.0.1
 */

public class NPTRate {
	/**
	 * Define a package scope constructor to stop javadoc adding a public one.
	 */
	NPTRate(){}

	/**
	 * Get the NPT rate's numerator. A value of zero indicates
	 * that the NPT is not progressing.
	 *
	 * @return the numerator
	 */
	public int getNumerator(){return 0;}
	
	/**
	 * Get the NPT rate's denominator.
	 * @return the denominator
	 */
	public int getDenominator() {return 0;}
}

