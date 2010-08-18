package jinahya.misc.clazz.reflector;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Comparator;

import org.xml.sax.SAXException;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class Reflector {


    /**
     *
     * @param out
     * @throws IOException
     */
    public Reflector(final OutputStream out) throws IOException {
        this(new OutputStreamWriter(out, "UTF-8"));
    }


    /**
     *
     * @param out
     */
    public Reflector(final Writer out) {
        super();

        handler =new DefaultHandlerExt(out);
    }


    /**
     *
     * @throws SAXException
     */
    public void startReflect() throws SAXException {
        handler.startDocument();
        handler.startElement("", "machine", "", attributes);
    }


    /**
     *
     * @throws SAXException
     */
    public void endReflect() throws SAXException {
        handler.endElement("", "machine", "");
        handler.endDocument();
    }


    /**
     *
     * @param in
     * @param name
     * @throws SAXException
     * @throws IOException
     */
    public void reflectSpec(final BufferedReader in, final String name)
        throws SAXException, IOException {

        attributes.clear();

        attributes.add("name", name);

        handler.startElement("", "spec", "", attributes);


        int total = 0;
        int found = 0;

        for (String line = null; (line = in.readLine()) != null;) {
            if (line.trim().length() == 0 || line.trim().startsWith("#")) {
                continue;
            }
            if (reflectClass(line.trim())) {
                found++;
            }
            total++;
        }

        attributes.clear();
        attributes.add("name", name);
        attributes.add("total", Integer.toString(total));
        attributes.add("found", Integer.toString(found));
        attributes.add("compatibility",
                       Float.toString(((float) found) / ((float) total)));
        handler.startElement("", "result", "", attributes, true);
        //handler.endElement("", "result", "");

        handler.endElement("", "spec", "");
    }


    private boolean reflectClass(final String className) throws SAXException {

        attributes.clear();

        try {
            final Class cls = Class.forName(className);

            reflectClass(cls);

            return true;

        } catch (ClassNotFoundException cnfe) {

            attributes.add("name", className);
            attributes.add("found", "false");
            handler.startElement("", "class", "", attributes, true);
            //handler.endElement("", "class", "");
            return false;
        }
    }


    private void reflectClass(final Class cls) throws SAXException {

        attributes.clear();

        attributes.add("modifiers", Modifier.toString(cls.getModifiers()));
        attributes.add("name", cls.getName());

        final Class superClass = cls.getSuperclass();
        if (superClass != null) {
            attributes.add("superClass", superClass.getName());
        }

        checkClasses("interfaces", cls.getInterfaces());

        checkBoolean(Class.class, cls, "isArray");
        checkBoolean(Class.class, cls, "isEnum");
        checkBoolean(Class.class, cls, "isInterface");
        checkBoolean(Class.class, cls, "isLocalClass");
        checkBoolean(Class.class, cls, "isMemberClass");
        checkBoolean(Class.class, cls, "isPrimitive");
        checkBoolean(Class.class, cls, "isSynthetic");

        handler.startElement("", "class", "", attributes);

        final Constructor[] constructors = cls.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            reflectConstructor(cls, constructor);
        }

        final Method[] methods = cls.getDeclaredMethods();
        Arrays.sort(methods, new Comparator<Method>(){
            @Override
            public int compare(Method o1, Method o2) {
                return o1.getName().compareTo(o2.getName());
            }
            @Override
            public boolean equals(final Object obj) {
                return false;
            }
        });
        for (Method method : methods) {
            reflectMethod(cls, method);
        }

        final Field[] fields = cls.getDeclaredFields();
        Arrays.sort(fields, new Comparator<Field>() {
            @Override
            public int compare(Field o1, Field o2) {
                return o1.getName().compareTo(o2.getName());
            }
            @Override
            public boolean equals(final Object obj) {
                return false;
            }
        });
        for (Field field : fields) {
            reflectField(cls, field);
        }

        final Class[] members = cls.getClasses();
        if (members.length > 0) {
            attributes.clear();
            handler.startElement("", "members", "", attributes);
            for (int i = 0; i < members.length; i++) {
                reflectClass(members[i]);
            }
            handler.endElement("", "members", "");
        }

        handler.endElement("", "class", "");
    }


    private void reflectConstructor(final Class cls, final Constructor c)
        throws SAXException {

        attributes.clear();

        attributes.add("modifiers", Modifier.toString(c.getModifiers()));

        checkClasses("parameters", c.getParameterTypes());
        checkClasses("exceptions", c.getExceptionTypes());

        if (c.getDeclaringClass().equals(cls)) {
            //attributes.add("declared", "true");
        }

        handler.startElement("", "constructor", "", attributes, true);
        //handler.endElement("", "constructor", "");
    }


    private void reflectMethod(final Class cls, final Method m)
        throws SAXException {

        attributes.clear();

        attributes.add("modifiers", Modifier.toString(m.getModifiers()));
        attributes.add("return", m.getReturnType().getName());
        attributes.add("name", m.getName());

        checkClasses("parameters", m.getParameterTypes());

        checkClasses("exceptions", m.getExceptionTypes());

        if (m.getDeclaringClass().equals(cls)) {
            //attributes.add("declared", "true");
        }

        checkBoolean(Method.class, m, "isBridge");
        checkBoolean(Method.class, m, "isSynthetic");
        checkBoolean(Method.class, m, "isVarArgs");

        handler.startElement("", "method", "", attributes, true);
        //handler.endElement("", "method", "");
    }


    private void reflectField(final Class cls, final Field f)
        throws SAXException {

        attributes.clear();

        attributes.add(
            "modifiers", Modifier.toString(f.getModifiers()));
        attributes.add("type", f.getType().getName());
        attributes.add("name", f.getName());

        if (f.getDeclaringClass().equals(cls)) {
            //attributes.add("declared", "true");
        }

        checkBoolean(Field.class, f, "isEnumConstant");
        checkBoolean(Field.class, f, "isSynthetic");

        handler.startElement("", "field", "", attributes, true);
        //handler.endElement("", "field", "");
    }


    private void checkClasses(final String name, final Class[] classes) {

        buffer.delete(0, buffer.length());

        if (classes.length > 0) {
            buffer.append(classes[0].getName());
        }

        for (int i = 1; i < classes.length; i++) {
            buffer.append(", ");
            buffer.append(classes[i].getName());
        }

        attributes.add(name, buffer.toString());
    }


    private void checkBoolean(final Class c, final Object o, String name) {

        try {
            Method isArray = c.getMethod(name, new Class[0]);
            try {
                if ((Boolean) isArray.invoke(o, new Object[0])) {
                    attributes.add(name, "true");
                }
            } catch (InvocationTargetException ite) {
                //ite.printStackTrace();
            } catch (IllegalAccessException iae) {
                //iae.printStackTrace();
            }
        } catch (NoSuchMethodException nsme) {
            //nsme.printStackTrace();
        }
    }


    private DefaultHandlerExt handler;

    private transient AttributesImpl attributes = new AttributesImpl();
    private transient StringBuffer buffer = new StringBuffer();
}
