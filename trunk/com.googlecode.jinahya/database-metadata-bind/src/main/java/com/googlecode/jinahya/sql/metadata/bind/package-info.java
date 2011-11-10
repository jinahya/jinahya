

/**
 * 
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlSchema(xmlns = {
    @XmlNs(prefix = "",
           namespaceURI = DatabaseMetadataBindConstants.TARGET_NAMESPACE),
    @XmlNs(prefix = "xs", namespaceURI = "http://www.w3.org/2001/XMLSchema"),
    @XmlNs(prefix = "xsi",
           namespaceURI = "http://www.w3.org/2001/XMLSchema-instance")},
           namespace = "http://www.gnifrix.com/teledit/persistence",
           elementFormDefault = XmlNsForm.QUALIFIED,
           attributeFormDefault = XmlNsForm.UNQUALIFIED)
package com.googlecode.jinahya.sql.metadata.bind;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;