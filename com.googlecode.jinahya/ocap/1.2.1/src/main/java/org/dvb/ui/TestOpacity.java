package org.dvb.ui;

/**
 * Interface implemented by Components or Containers in order to allow the 
 * platform to query whether their paint method is fully opaque.
 */

public interface TestOpacity {
	/**
	 * Returns true if the entire area of the component as given by the
	 * getBounds method, is fully opaque. Hence its paint method (or
	 * surrogate methods) guarantees that all pixels are painted in an 
	 * opaque Color. <p>
	 * Classes implementing
	 * this interface shall return true from their implementation of this method if and
	 * only if their implementation can guarantee full opacity.
	 * The consequences of an invalid overridden value are implementation specific.
	 *
	 * @return true if all the pixels with the java.awt.Component#getBounds method are
	 * fully opaque, otherwise false.
	 */
	public boolean isOpaque();
}

