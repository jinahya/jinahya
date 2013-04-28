package org.ocap.hn.transformation;

import org.ocap.hn.content.ContentFormat;

/**
 * This interface represents a transformation from an input content format
 * to an output content format. Instances implementing this interface are
 * created by the <code>TransformationManager</code>.
 */
public interface Transformation
{
    /**
     * Returns the input content format that can be transformed.
     * 
     * @return The input content format.
     */
    public ContentFormat getInputContentFormat();

    /**
     * Returns the output content format the input format can
     * be transformed into. For content formats that include video the method
     * SHALL return a <code>OutputVideoContentFormat</code> instance.
     * 
     * @return The output content format.
     */
    public ContentFormat getOutputContentFormat();
}
