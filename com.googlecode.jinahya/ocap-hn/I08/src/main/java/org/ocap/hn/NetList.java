package org.ocap.hn;

/**
 * NetList
 *
 * @author			Luyang Li (lly@research.panasonic.com)
 * @version			1.0
 *
 */
 
/**
 * A list comprising of homenetwork elements such as Device or NetModule. 
 * The application may retrieve such a list from <code>NetManager</code>, 
 * <code>getNetModules</code>  * or <code> getDevices</code>.  The application
 * may refine the list by applying a {@link PropertyFilter}.
 */ 
public interface NetList {
	/**
	 * Indicates whether an element is included in this NetList.
	 * @param	element
	 *			the element to check whether it is in the list
	 * @return	true if the element is in the list; otherwise false.
	 */
	public boolean contains(Object element);
	
	/**
	 * Returns the element indexed by a number.
	 * @param	index
	 *			specified index of the element
	 * @return	element indexed by the number
	 */
	public Object getElement(int index);
	
	/**
	 * Returns all elements in this NetList in <code>Enumeration</code>.
	 * In Homenetwork, NetList can be used to retrieve  a list of Devices
	 * or a list of NetModules. In either case, a corresponding type of object
	 * is returned.
	 * @return	An enumeration of Device or NetModule elements  
	 */
	public java.util.Enumeration getElements();
	
	/**
	 * Applies a new <code>PropertyFilter</code> to this element list and 
	 * returns a new list.
	 * @param	filter 		new filter
	 * @return	new element list generated by new filter
	 * @see		PropertyFilter
	 */
	public NetList filterElement(PropertyFilter filter);
	
	/**
	 * Returns the index of an element in this element list.
	 * @param	element to be checked
	 * @return	index of an element in this list. If there is no such element in
	 * 			this list, returns -1.
	 */
	public int indexOf(Object element);
	
	/**
	 * Returns the size of this list.
	 * @return	size of the element list
	 */
	public int size();
}