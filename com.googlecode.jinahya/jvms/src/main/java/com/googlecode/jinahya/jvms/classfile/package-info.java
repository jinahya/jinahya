

@XmlAccessorType(XmlAccessType.NONE)
@XmlSchema(namespace = CLASSFILE_NS_URI,
           elementFormDefault = XmlNsForm.QUALIFIED,
           attributeFormDefault = XmlNsForm.UNQUALIFIED,
           xmlns = {@XmlNs(prefix = DEFAULT_NS_PREFIX,
                           namespaceURI = CLASSFILE_NS_URI),
                    @XmlNs(prefix = CLASSFILE_ATTRIBUTE_NS_PREFIX,
                           namespaceURI = CLASSFILE_ATTRIBUTE_NS_URI),
                    @XmlNs(prefix = CLASSFILE_ATTRIBUTE_STACKMAP_NS_PREFIX,
                           namespaceURI = CLASSFILE_ATTRIBUTE_STACKMAP_NS_URI),
                    @XmlNs(prefix = CLASSFILE_CONSTANT_NS_PREFIX,
                           namespaceURI = CLASSFILE_CONSTANT_NS_URI),
                    @XmlNs(prefix = "xs", namespaceURI = W3C_XML_SCHEMA_NS_URI),
                    @XmlNs(prefix = "xsi",
                           namespaceURI = W3C_XML_SCHEMA_INSTANCE_NS_URI)})
package com.googlecode.jinahya.jvms.classfile;


import static com.googlecode.jinahya.jvms.classfile.ClassFileXMLConstant.*;
import static javax.xml.XMLConstants.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;
