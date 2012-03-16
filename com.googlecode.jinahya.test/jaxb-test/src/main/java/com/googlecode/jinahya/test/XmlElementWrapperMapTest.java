

package com.googlecode.jinahya.test;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class XmlElementWrapperMapTest {


    static class Child {


        static Child newInstance(final int key) {
            final Child instance = new Child();
            instance.key = key;
            return instance;
        }


        @XmlElement
        int key;


    }


    static class Children2 {


        Collection<Child> getChildren() {
            if (child == null) {
                child = new ArrayList<Child>();
            }
            return child;
        }


        @XmlElement()
        Collection<Child> child;


    }


    static class Children3 {


        List<Child> getChildren() {
            if (children == null) {
                children = new ArrayList<Child>();
            }
            return children;
        }


        @XmlElement(name = "child")
        List<Child> children;


    }


    static class ChildrenAdapter1
        //        extends XmlAdapter<Collection<Child>, Map<Integer, Child>> {
        extends XmlAdapter<List<Child>, Map<Integer, Child>> {


//        @Override
//        public Collection<Child> marshal(final Map<Integer, Child> bound)
//            throws Exception {
//
//            if (bound == null) {
//                return null;
//            }
//
//            return new ArrayList<Child>(bound.values());
//        }
        @Override
        public List<Child> marshal(final Map<Integer, Child> bound)
            throws Exception {

            if (bound == null) {
                return null;
            }

            return new ArrayList<Child>(bound.values());
        }


//        @Override
//        public Map<Integer, Child> unmarshal(final Collection<Child> value)
//            throws Exception {
//
//            if (value == null) {
//                return null;
//            }
//
//            final Map<Integer, Child> bound =
//                new HashMap<Integer, Child>(value.size());
//            for (Child child : value) {
//                bound.put(child.key, child);
//            }
//
//            return bound;
//        }
        @Override
        public Map<Integer, Child> unmarshal(final List<Child> value)
            throws Exception {

            if (value == null) {
                return null;
            }

            final Map<Integer, Child> bound =
                new HashMap<Integer, Child>(value.size());
            for (Child child : value) {
                bound.put(child.key, child);
            }

            return bound;
        }


    }


    static class Children1Adapter2
        extends XmlAdapter<Children2, Map<Integer, Child>> {


        @Override
        public Children2 marshal(final Map<Integer, Child> bound)
            throws Exception {

            if (bound == null) {
                return null;
            }

            final Children2 value = new Children2();
            value.getChildren().addAll(bound.values());

            return value;
        }


        @Override
        public Map<Integer, Child> unmarshal(final Children2 value)
            throws Exception {

            if (value == null) {
                return null;
            }

            final Map<Integer, Child> bound =
                new HashMap<Integer, Child>(value.getChildren().size());
            for (Child child : value.getChildren()) {
                bound.put(child.key, child);
            }

            return bound;
        }


    }


    static class Children1Adapter3
        extends XmlAdapter<Children3, Map<Integer, Child>> {


        @Override
        public Children3 marshal(final Map<Integer, Child> bound)
            throws Exception {

            if (bound == null) {
                return null;
            }

            final Children3 value = new Children3();
            value.getChildren().addAll(bound.values());

            return value;
        }


        @Override
        public Map<Integer, Child> unmarshal(final Children3 value)
            throws Exception {

            if (value == null) {
                return null;
            }

            final Map<Integer, Child> bound =
                new HashMap<Integer, Child>(value.getChildren().size());
            for (Child child : value.getChildren()) {
                bound.put(child.key, child);
            }

            return bound;
        }


    }


    @XmlAccessorType(XmlAccessType.NONE)
    @XmlRootElement
    //@XmlType(propOrder = {"children1", "children2", "children3"})
    static class Parent {


        Map<Integer, Child> getChildren1() {
            if (children1 == null) {
                children1 = new HashMap<Integer, Child>();
            }
            return children1;
        }


        Map<Integer, Child> getChildren2() {
            if (children2 == null) {
                children2 = new HashMap<Integer, Child>();
            }
            return children2;
        }


        Map<Integer, Child> getChildren3() {
            if (children3 == null) {
                children3 = new HashMap<Integer, Child>();
            }
            return children3;
        }


        @XmlElement(name = "child")
        @XmlElementWrapper(required = true, nillable = true)
        Collection<Child> children0;


        //@XmlJavaTypeAdapter(ChildrenAdapter1.class)
        Map<Integer, Child> children1;


        //@XmlElement(required = true, nillable = true)
        //@XmlElementWrapper
        @XmlJavaTypeAdapter(Children1Adapter2.class)
        Map<Integer, Child> children2;


        //@XmlElementWrapper(required = true, nillable = true)
        @XmlJavaTypeAdapter(Children1Adapter3.class)
        Map<Integer, Child> children3;


    }


    public static void main(final String[] args)
        throws JAXBException, IOException {

        final Parent parent = new Parent();

        for (int i = 0; i < 3; i++) {
            parent.getChildren1().put(i, Child.newInstance(i));
        }

        for (int i = 0; i < 3; i++) {
            parent.getChildren2().put(i, Child.newInstance(i));
        }

        for (int i = 0; i < 3; i++) {
            parent.getChildren3().put(i, Child.newInstance(i));
        }

        final JAXBContext context = JAXBContext.newInstance(Parent.class);

        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        marshaller.marshal(parent, System.out);

        context.generateSchema(new SchemaOutputResolver() {


            @Override
            public Result createOutput(final String namespaceUri,
                                       final String suggestedFileName) {
                return new StreamResult(System.out) {


                    @Override
                    public String getSystemId() {
                        return suggestedFileName;

                    }


                };
            }


        });



    }


}

