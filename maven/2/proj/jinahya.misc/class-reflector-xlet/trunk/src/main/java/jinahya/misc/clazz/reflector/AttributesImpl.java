package jinahya.misc.clazz.reflector;


import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
class AttributesImpl implements Attributes {


    public AttributesImpl() {
        super();

        attributes = new ArrayList<String>();
    }


    @Override
    public String getValue(final int index) {

        if (index >= attributes.size()) {
            return null;
        }

        final String attribute = attributes.get(index);
        return attribute.substring(attribute.indexOf('=') + 1);
    }


    @Override
    public String getValue(final String qName) {
        return null;
    }


    @Override
    public String getValue(final String uri, final String localName) {
        final String key = key(uri, localName);

        for (String attribute : attributes) {
            if (attribute.startsWith(key)) {
                return attribute.substring(attribute.indexOf('=') + 1);
            }
        }

        return null;
    }


    public void addAttribute(final String uri, final String localName,
                             final String qName, final String type,
                             final String value) {

        attributes.add(key(uri, localName) + "=" + value);
    }


    public void add(final String localName, final String value) {
        addAttribute("", localName, "", "CDATA", value);
    }


    @Override
    public String getType(final int index) {
        return "CDATA";
    }


    @Override
    public String getType(final String qName) {
        return "CDATA";
    }


    @Override
    public String getType(final String uri, final String localName) {
        return "CDATA";
    }


    @Override
    public int getIndex(final String qName) {
        return -1;
    }


    @Override
    public int getIndex(final String uri, final String localName) {

        final String key = key(uri, localName);

        for (int i = 0; i < attributes.size(); i++) {
            if (attributes.get(i).startsWith(key)) {
                return i;
            }
        }

        return -1;
    }


    @Override
    public String getQName(final int index) {

        if (index >= attributes.size()) {
            return null;
        }

        return null;
    }


    @Override
    public String getLocalName(final int index) {

        if (index >= attributes.size()) {
            return null;
        }

        final String attribute = attributes.get(index);
        return attribute.substring(attribute.indexOf('}') + 1, attribute.indexOf('='));
    }


    @Override
    public String getURI(final int index) {

        if (index >= attributes.size()) {
            return null;
        }

        final String attribute = attributes.get(index);
        return attribute.substring(1, attributes.indexOf('}'));
    }


    @Override
    public int getLength() {
        return attributes.size();
    }


    private String key(final String uri, final String localName) {
        return "{" + uri + "}" + localName;
    }


    public void clear() {
        attributes.clear();
    }


    private List<String> attributes;
}
