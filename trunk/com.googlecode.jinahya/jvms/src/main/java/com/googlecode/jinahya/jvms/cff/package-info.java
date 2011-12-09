

@XmlAccessorType(XmlAccessType.NONE)
@XmlSchema(namespace = CLASS_FILE_FORMAT_NAMESPACE_URI,
           elementFormDefault = XmlNsForm.QUALIFIED,
           attributeFormDefault = XmlNsForm.UNQUALIFIED,
           xmlns = {
    @XmlNs(prefix = DEFAULT_NS_PREFIX,
           namespaceURI = CLASS_FILE_FORMAT_NAMESPACE_URI),
    @XmlNs(prefix = W3C_XML_SCHEMA_NS_PREIX,
           namespaceURI = W3C_XML_SCHEMA_NS_URI),
    @XmlNs(prefix = W3C_XML_SCHEMA_INSTANCE_NS_PREIX,
           namespaceURI = W3C_XML_SCHEMA_INSTANCE_NS_URI)})
package com.googlecode.jinahya.jvms.cff;


import static com.googlecode.jinahya.jvms.cff.CFFXMLConstants.*;
import static javax.xml.XMLConstants.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;
