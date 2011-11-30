

@XmlAccessorType(XmlAccessType.NONE)
@XmlSchema(xmlns = {
    @XmlNs(prefix = "", namespaceURI = Constants.TARGET_NAMESPACE),
    @XmlNs(prefix = "xs", namespaceURI = W3C_XML_SCHEMA_NS_URI),
    @XmlNs(prefix = "xsi", namespaceURI = W3C_XML_SCHEMA_INSTANCE_NS_URI)},
           namespace = Constants.TARGET_NAMESPACE,
           elementFormDefault = XmlNsForm.QUALIFIED,
           attributeFormDefault = XmlNsForm.UNQUALIFIED)
package com.googlecode.jinahya.jdbc.realm.persistence;


import com.googlecode.jinahya.jdbc.realm.persistence.Constants.*;

import static javax.xml.XMLConstants.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;
