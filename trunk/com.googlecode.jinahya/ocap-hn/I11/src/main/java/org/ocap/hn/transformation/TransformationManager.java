package org.ocap.hn.transformation;

import org.ocap.hn.content.ContentItem;

/**
 * This class is a singleton manager that can be used to get transformation
 * capabilities of the Host device and to manage the content item transformation
 * configuration. Transformation capabilities indicate the transformations from
 * input content format to output content format that the device supports.
 */
public abstract class TransformationManager
{
    /**
     * Constructor protected from erroneous application access.
     */
    protected TransformationManager()
    {
    }

    /**
     * Gets an instance of the <code>TransformationManager</code>.
     * 
     * @throws SecurityException if the calling application has not been granted
     *      HomeNetPermission("contentmanagement").
     */
    public static TransformationManager getInstance()
    {
        return null;
    }
    
    /**
     * Adds a <code>TransformationListener</code> to receive callbacks from the
     * <code>TransformationManager</code>. The <code>TransformationListener</code>
     * will be notified whenever transformations are applied for the
     * <code>ContentItem</code>. Subsequent calls to register the same 
     * listener will be ignored.
     * 
     * @param listener The listener that will receive the callbacks
     *
     * @throws IllegalArgumentException if the listener parameter is null.
     *
     */
    public abstract void addTransformationListener(TransformationListener listener);
    
    /**
     * Removes the specified TransformationListener. If the listener specified is not 
     * registered or is null, then this method has no effect
     * 
     * @param listener The listener to remove
     */
    public abstract void removeTransformationListener(TransformationListener listener);

    /**
     * Gets all of the transformation permutations the Host device supports.
     * See [OC-BUNDLE] for additional mapping of this method.
     * 
     * @return Device supported transformations.  If the device does not support
     *      transformations an empty array is returned.
     */
    public abstract Transformation [] getSupportedTransformations();
        
    /**
     * Sets the default transformations. Default transformations will be applied
     * to newly-created ContentItems only and certain calls to 
     * <code>TransformationManager</code>. A call to setDefaultTransformation 
     * over-rides any previously-set default transformations and passing an
     * empty array disables any previously-set default transformations. 
     * See [OC-BUNDLE] for additional mapping of this method.
     * 
     * @param transformations The new default transformations.
     * 
     * @throws IllegalArgumentException if the transformations parameter is null.
     * 
     * @returns The default transformations.
     */
    public abstract Transformation [] setDefaultTransformations(Transformation [] transformations);

    /**
     * Returns the currently-set default transformation.
     * 
     * @returns The default transformations.
     */
    public abstract Transformation [] getDefaultTransformations();
    
    /**
     * Returns the applied transformations for the content item.
     * 
     * @param item The content item.
     * 
     * @throws IllegalArgumentException if the item parameter is null.
     *
     * @returns The applied transformations
     */
    public abstract Transformation [] getTransformations(ContentItem item);
        
    /**
     * Applies the transformations to all existing local content items that
     * represent network operator content. A call to this method will remove
     * any existing transformations before setting the transformations. 
     * See [OC-BUNDLE] for additional mapping of this method.
     * 
     * @param transformations The array of transformations to be applied.
     *
     * @throws IllegalArgumentException if the transformations parameter is null.
     *
     */
    public abstract void setTransformations(Transformation [] transformations);
    
    /**
     * Applies the default transformations for a set of content items. Configures 
     * metadata indicating content transformation support for each 
     * <code>ContentItem</code> in the items array parameter. If a content item
     * in the array parameter is not local or does not represent MSO local 
     * content it is skipped without change or notification. A call to this
     * method will remove any existing transformations before setting the 
     * transformations. 
     * See [OC-BUNDLE] for additional mapping of this method.
     * 
     * @param items The array of content items the transformation metadata 
     *              will be configured in.
     * 
     * @throws IllegalArgumentException if the parameter is null or empty.
     */
    public abstract void setTransformations(ContentItem [] items);  
    
    /**
     * Applies specific transformations for a set of content items. Configures 
     * metadata indicating content transformation support that matches any
     * of the transformations in the transformations array parameter for each
     * <code>ContentItem</code> in the items array parameter. If a content item 
     * in the array parameter is not local or does not represent MSO local 
     * content it is skipped without change or notification. A call to this 
     * method will remove any existing transformations before setting the 
     * transformations.
     * See [OC-BUNDLE] for additional mapping of this method.
     * 
     * @param items The array of content items the transformation metadata will
     *              be configured in.
     * @param transformations The array of transformations to apply.
     * 
     * @throws IllegalArgumentException if items parameter is null or empty or 
     * the transformations parameter is null.
     */
    public abstract void setTransformations(ContentItem [] items,    
                                            Transformation [] transformations);
}
