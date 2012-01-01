

@XmlAccessorType(XmlAccessType.NONE)
@XmlSchema(namespace = MetadataXMLConstants.METADATA_NS_URI,
           elementFormDefault = XmlNsForm.QUALIFIED,
           attributeFormDefault = XmlNsForm.UNQUALIFIED,
           xmlns = {@XmlNs(prefix = "", namespaceURI = METADATA_NS_URI),
                    @XmlNs(prefix = "xs", namespaceURI = W3C_XML_SCHEMA_NS_URI),
                    @XmlNs(prefix = "xsi",
                           namespaceURI = W3C_XML_SCHEMA_INSTANCE_NS_URI)})
package com.googlecode.jinahya.sql.metadata;


import static com.googlecode.jinahya.sql.metadata.MetadataXMLConstants.*;

import static javax.xml.XMLConstants.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;
