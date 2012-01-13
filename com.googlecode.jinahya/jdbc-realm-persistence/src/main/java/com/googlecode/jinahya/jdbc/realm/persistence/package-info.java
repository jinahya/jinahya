

@XmlAccessorType(XmlAccessType.NONE)
@XmlSchema(namespace = Constants.TARGET_NAMESPACE,
           elementFormDefault = XmlNsForm.QUALIFIED,
           attributeFormDefault = XmlNsForm.UNQUALIFIED,
           xmlns = {@XmlNs(prefix = "",
                           namespaceURI = Constants.TARGET_NAMESPACE),
                    @XmlNs(prefix = "xs", namespaceURI = W3C_XML_SCHEMA_NS_URI),
                    @XmlNs(prefix = "xsi",
                           namespaceURI = W3C_XML_SCHEMA_INSTANCE_NS_URI)})
package com.googlecode.jinahya.jdbc.realm.persistence;


import static javax.xml.XMLConstants.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;
