

/**
 * 
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlSchema(xmlns = {
    @XmlNs(prefix = "",
           namespaceURI = DatabaseMetadataBindConstants.TARGET_NAMESPACE),
    @XmlNs(prefix = "xs", namespaceURI = XMLConstants.W3C_XML_SCHEMA_NS_URI),
    @XmlNs(prefix = "xsi",
           namespaceURI = XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI)},
           namespace = DatabaseMetadataBindConstants.TARGET_NAMESPACE,
           elementFormDefault = XmlNsForm.QUALIFIED,
           attributeFormDefault = XmlNsForm.UNQUALIFIED)
package com.googlecode.jinahya.sql.metadata.bind;


import javax.xml.XMLConstants;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;