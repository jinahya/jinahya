/*
 *  Copyright 2009 onacit.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package jinahya.beans;


import java.beans.PropertyChangeSupport;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class ExtendedPropertyChangeSupport extends PropertyChangeSupport {


    private static final long serialVersionUID = -3746112795519529165L;


    /**
     *
     * @param sourceBean
     */
    public ExtendedPropertyChangeSupport(Object sourceBean) {
        super(sourceBean);
    }


    /**
     *
     * @param propertyName
     * @param oldValue
     * @param newValue
     */
    public void firePropertyChange(final String propertyName,
                                   final boolean oldValue,
                                   final boolean newValue) {

        firePropertyChange(propertyName, oldValue ?
                           Boolean.TRUE : Boolean.FALSE,
                           newValue ? Boolean.TRUE : Boolean.FALSE);
    }


    /**
     *
     * @param propertyName
     * @param oldValue
     * @param newValue
     */
    public void firePropertyChange(final String propertyName,
                                   final byte oldValue,
                                   final byte newValue) {

        firePropertyChange(propertyName, new Byte(oldValue),
                           new Byte(newValue));
    }


    /**
     *
     * @param propertyName
     * @param oldValue
     * @param newValue
     */
    public void firePropertyChange(final String propertyName,
                                   final char oldValue,
                                   final char newValue) {

        firePropertyChange(propertyName, new Character(oldValue),
                           new Character(newValue));
    }


    /**
     *
     * @param propertyName
     * @param oldValue
     * @param newValue
     */
    public void firePropertyChange(final String propertyName,
                                   final short oldValue,
                                   final short newValue) {

        firePropertyChange(propertyName, new Short(oldValue),
                           new Short(newValue));
    }


    /**
     *
     * @param propertyName
     * @param oldValue
     * @param newValue
     */
    public void firePropertyChange(final String propertyName,
                                   final int oldValue,
                                   final int newValue) {

        firePropertyChange(propertyName, new Integer(oldValue),
                           new Integer(newValue));
    }


    /**
     *
     * @param propertyName
     * @param oldValue
     * @param newValue
     */
    public void firePropertyChange(final String propertyName,
                                   final long oldValue,
                                   final long newValue) {

        firePropertyChange(propertyName, new Long(oldValue),
                           new Long(newValue));
    }


    /**
     * 
     * @param propertyName
     * @param oldValue
     * @param newValue
     */
    public void firePropertyChange(final String propertyName,
                                   final float oldValue,
                                   final float newValue) {

        firePropertyChange(propertyName, new Float(oldValue),
                           new Float(newValue));
    }


    /**
     *
     * @param propertyName
     * @param oldValue
     * @param newValue
     */
    public void firePropertyChange(final String propertyName,
                                   final double oldValue,
                                   final double newValue) {

        firePropertyChange(propertyName, new Double(oldValue),
                           new Double(newValue));
    }

}
