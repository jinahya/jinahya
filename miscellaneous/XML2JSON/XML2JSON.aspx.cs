using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Xml;


public partial class XML2JSON : System.Web.UI.Page {

    private static void element2json(XmlElement element, TextWriter writer) {
        writer.Write("{");
        Dictionary<String, Object> dictionary =
            new Dictionary<String, Object>();
         
        for (IEnumerator e = element.Attributes.GetEnumerator();
             e.MoveNext();) {
            XmlAttribute attribute = (XmlAttribute)e.Current;
            dictionary.Add("@" + attribute.Name, attribute.Value);
        }
         
        foreach (XmlNode childNode in element.ChildNodes) {
            Object list;
            switch (childNode.NodeType) {
            case XmlNodeType.None:
                break;
            case XmlNodeType.Element:
                if (!dictionary.TryGetValue(childNode.Name, out list)) {
                    list = new LinkedList<XmlNode>();
                    dictionary.Add(childNode.Name, list);
                }
                ((LinkedList<XmlNode>)list).AddLast(childNode);
                break;
            case XmlNodeType.Attribute:
                break;
            case XmlNodeType.Text:
            case XmlNodeType.CDATA:
                if (!dictionary.TryGetValue("text()", out list)) {
                    list = new LinkedList<XmlNode>();
                    dictionary.Add("text()", list);
                }
                ((LinkedList<XmlNode>)list).AddLast(childNode);
                break;
            case XmlNodeType.EntityReference:
                break;
            case XmlNodeType.Entity:
                break;
            case XmlNodeType.ProcessingInstruction:
                break;
            case XmlNodeType.Comment:
                break;
            case XmlNodeType.Document:
                break;
            case XmlNodeType.DocumentType:
                break;
            case XmlNodeType.DocumentFragment:
                break;
            case XmlNodeType.Notation:
                break;
            case XmlNodeType.Whitespace:
                break;
            case XmlNodeType.SignificantWhitespace:
                break;
            case XmlNodeType.EndElement:
                break;
            case XmlNodeType.EndEntity:
                break;
            case XmlNodeType.XmlDeclaration:
                break;
            default:
                break;
            }
        }

        bool hasPrevious = false;
        foreach (KeyValuePair<String, Object> pair in dictionary) {
            if (!pair.Key.StartsWith("@")) {
                continue;
            }
            if (hasPrevious) {
                writer.Write(", ");
            }
            writer.Write("\"{0}\": \"{1}\"", pair.Key, pair.Value);
            hasPrevious = true;
        }

        if (hasPrevious) {
            writer.Write(", ");
        }
        Object textNodeList;
        if (dictionary.TryGetValue("text()", out textNodeList)) {
            writer.Write("\"text()\": \"");
            foreach (XmlNode textNode in ((LinkedList<XmlNode>)textNodeList)) {
                if (textNode.NodeType == XmlNodeType.CDATA) {
                    StringBuilder sb = new StringBuilder();
                    foreach (char c in textNode.Value) {
                        switch (c) {
                        case '\"':
                            sb.Append("\\\"");
                            break;
                        case '\\':
                            sb.Append("\\\\");
                            break;
                        case '\b':
                            sb.Append("\\b");
                            break;
                        case '\f':
                            sb.Append("\\f");
                            break;
                        case '\n':
                            sb.Append("\\n");
                            break;
                        case '\r':
                            sb.Append("\\r");
                            break;
                        case '\t':
                            sb.Append("\\t");
                            break;
                        default:
                            int i = (int)c;
                            if (i < 32 || i > 127) {
                                sb.AppendFormat("\\u{0:X04}", i);
                            } else {
                                sb.Append(c);
                            }
                            break;
                        }
                    }
                    writer.Write(sb.ToString());
                } else {
                    writer.Write(textNode.Value);
                }
            }
            writer.Write("\"");
        } else {
            writer.Write("\"text()\": null");
        }
        hasPrevious = true;

        foreach (KeyValuePair<String, Object> pair in dictionary) {
            if (pair.Key.StartsWith("@") || pair.Key.Equals("text()")) {
                continue;
            }
            if (hasPrevious) {
                writer.Write(", ");
            }
            writer.Write("\"{0}\": [", pair.Key);
            hasPrevious = false;
            Object elementNodeList;
            dictionary.TryGetValue(pair.Key, out elementNodeList);
            foreach (XmlNode elementNode in
                     ((LinkedList<XmlNode>) elementNodeList)) {
                if (hasPrevious) {
                    writer.Write(", ");
                }
                element2json((XmlElement)elementNode, writer);
                hasPrevious = true;
            }
            writer.Write("]");
            hasPrevious = true;
        }

        writer.Write("}");

    }


    protected void Page_Load(object sender, EventArgs e) {
        string url = Request.Params.GetValues(0)[0];

        // Set the page's content type to JPEG files
        // and clear all response headers.
        Response.ContentType = "application/json";
        Response.Clear();
        
        // Buffer response so that page is sent
        // after processing is complete.
        Response.BufferOutput = true;

        XmlDocument xmlDocument = new XmlDocument();
        xmlDocument.Load(url);

        foreach (XmlNode childNode in document.ChildNodes) {
            if (childNode.NodeType != XmlNodeType.Element) {
                continue;
            }
            TextWriter writer = Response.Output;
            writer.Write("{");
            writer.Write("\"{0}\": ", childNode.Name);
            element2json((XmlElement) childNode, writer);
            writer.WriteLine("}");
            break;
        }

        // Send the output to the client.
        Response.Flush();
    }
}