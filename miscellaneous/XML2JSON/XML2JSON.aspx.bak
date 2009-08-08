<%@ Page Language="C#" AutoEventWireUp="true" CodeFile="XML2JSON.aspx.cs" Inherits="XML2JSON" %>

<%@ Import Namespace="System.IO" %>


private static string XmlToJSON(XmlDocument xmlDoc) {
    StringBuilder sbJSON = new StringBuilder();
    sbJSON.Append("{ ");
    XmlToJSONnode(sbJSON, xmlDoc.DocumentElement, true);

    sbJSON.Append("}");
    return sbJSON.ToString();
}


private static void XmlToJSONnode(StringBuilder sbJSON, XmlElement node,
                                  bool showNodeName) {
    if (showNodeName) {
        sbJSON.Append("\"" + SafeJSON(node.Name) + "\": ");
    }
    sbJSON.Append("{");

    SortedList childNodeNames = new SortedList();

    if(node.Attributes != null) {
        foreach (XmlAttribute attr in node.Attributes) {
            StoreChildNode(childNodeNames,attr.Name,attr.InnerText);
        }
    }

    foreach (XmlNode cnode in node.ChildNodes) {
        if (cnode is XmlText) {
            StoreChildNode(childNodeNames, "value", cnode.InnerText);
        } else if (cnode is XmlElement) {
            StoreChildNode(childNodeNames, cnode.Name, cnode);
        }
    }
    
    foreach (string childname in childNodeNames.Keys) {
        ArrayList alChild = (ArrayList)childNodeNames[childname];
        if (alChild.Count == 1) {
            OutputNode(childname, alChild[0], sbJSON, true);
        } else {
            sbJSON.Append(" \"" + SafeJSON(childname) + "\": [ ");
            foreach (object Child in alChild) {
                OutputNode(childname, Child, sbJSON, false);
            }
            sbJSON.Remove(sbJSON.Length - 2, 2);
            sbJSON.Append(" ], ");
        }
    }
    sbJSON.Remove(sbJSON.Length - 2, 2);
    sbJSON.Append(" }");
}


private static void StoreChildNode(SortedList childNodeNames, string nodeName,
                                   object nodeValue) {
    if (nodeValue is XmlElement) {
        XmlNode cnode = (XmlNode)nodeValue;
        if (cnode.Attributes.Count == 0) {
            XmlNodeList children = cnode.ChildNodes;
            if(children.Count==0) {
                nodeValue = null;
            } else if (children.Count == 1 && (children[0] is XmlText)) {
                nodeValue = ((XmlText)(children[0])).InnerText;
            }
        }
    }
    object oValuesAL = childNodeNames[nodeName];
    ArrayList ValuesAL;
    if (oValuesAL == null) {
        ValuesAL = new ArrayList();
        childNodeNames[nodeName] = ValuesAL;
    } else {
        ValuesAL = (ArrayList)oValuesAL;
    }
    ValuesAL.Add(nodeValue);
}


private static void OutputNode(string childname, object alChild,
                               StringBuilder sbJSON, bool showNodeName) {
    if (alChild == null) {
        if (showNodeName) {
            sbJSON.Append("\"" + SafeJSON(childname) + "\": ");
        }
        sbJSON.Append("null");
    } else if (alChild is string) {
        if (showNodeName) {
            sbJSON.Append("\"" + SafeJSON(childname) + "\": ");
        }
        string sChild = (string)alChild;
        sChild = sChild.Trim();
        sbJSON.Append("\"" + SafeJSON(sChild) + "\"");
    } else {
        XmlToJSONnode(sbJSON, (XmlElement)alChild, showNodeName);
    }
    sbJSON.Append(", ");
}


private static string SafeJSON(string sIn) {
    StringBuilder sbOut = new StringBuilder(sIn.Length);
    foreach (char ch in sIn) {
        if (Char.IsControl(ch) || ch == '\'') {
            int ich = (int)ch;
            sbOut.Append(@"\u" + ich.ToString("x4"));
            continue;
        } else if (ch == '\"' || ch == '\\' || ch == '/') {
            sbOut.Append('\\');
        }
        sbOut.Append(ch);
    }
    return sbOut.ToString();
}