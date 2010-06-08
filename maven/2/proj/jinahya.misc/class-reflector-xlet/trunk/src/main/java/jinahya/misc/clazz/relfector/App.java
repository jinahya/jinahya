package jinahya.misc.clazz.relfector;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;

import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class App implements Xlet {


    private static void print(final String string) {
        System.out.println("[REFLECT]" + string);
    }

    private static final StringBuffer BUFFER = new StringBuffer();


    @Override
    public void initXlet(final XletContext ctx)
        throws XletStateChangeException {

    }


    @Override
    public void startXlet() throws XletStateChangeException {
        print("<?xml version=\"1.0\"?>");
        print("<classes>");
        for (File file : new File("specs").listFiles(new FileFilter() {
            @Override
            public boolean accept(final File pathname) {
                return pathname.isFile();
            }
        })) {
            final String name = file.getName();
            print("<spec name=\"" + name + "\">");

            try {
                final BufferedReader br =
                    new BufferedReader(new FileReader(file));
                try {
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        line = line.trim();
                        if (line.length() == 0) {
                            continue;
                        }
                        if (line.startsWith("#")) {
                            continue;
                        }
                        reflect(line);
                    }
                } finally {
                    br.close();
                }
            } catch (IOException ioe) {
                //ioe.printStackTrace();
            }
            print("</spec>");
        }
        print("</classes>");
    }


    @Override
    public void pauseXlet() {
    }


    @Override
    public void destroyXlet(final boolean unconditional)
        throws XletStateChangeException {

    }


    private void reflect(final String className) {
        BUFFER.delete(0, BUFFER.length());

        try {
            final Class c = Class.forName(className);

            BUFFER.append("<class");
            BUFFER.append(" modifiers=\"" + Modifier.toString(c.getModifiers()) + "\"");
            BUFFER.append(" name=\"" + className + "\"");

            final Class superClass = c.getSuperclass();
            if (superClass != null) {
                BUFFER.append(" superClass=\"" + superClass.getName() + "\"");
            }

            final Class[] interfaces = c.getInterfaces();
            if (interfaces.length > 0) {
                BUFFER.append(" interfaces=\"" + interfaces[0].getName());
                for (int i = 1; i < interfaces.length; i++) {
                    BUFFER.append("," + interfaces[i].getName());
                }
                BUFFER.append("\"");
            }

            checkBoolean(Class.class, c, "isArray");
            checkBoolean(Class.class, c, "isEnum");
            checkBoolean(Class.class, c, "isInterface");
            checkBoolean(Class.class, c, "isLocalClass");
            checkBoolean(Class.class, c, "isMemberClass");
            checkBoolean(Class.class, c, "isPrimitive");
            checkBoolean(Class.class, c, "isSynthetic");

            BUFFER.append(">");

            print(BUFFER.toString());

            for (Constructor constructor : c.getConstructors()) {
                BUFFER.delete(0, BUFFER.length());
                BUFFER.append("<constructor");

                BUFFER.append(" modifiers=\"" + Modifier.toString(constructor.getModifiers()) + "\"");

                //BUFFER.append(" name=\"" + constructor.getName() + "\"");

                final Class[] parameterTypes = constructor.getParameterTypes();
                if (parameterTypes.length > 0) {
                    BUFFER.append(" parameters=\"");
                    BUFFER.append(parameterTypes[0].getName());
                    for (int i = 1; i < parameterTypes.length; i++) {
                        BUFFER.append(", " + parameterTypes[i].getName());
                    }
                    BUFFER.append("\"");
                }

                final Class[] exceptionTypes = constructor.getExceptionTypes();
                if (exceptionTypes.length > 0) {
                    BUFFER.append(" exceptions=\"");
                    BUFFER.append(exceptionTypes[0].getName());
                    for (int i = 1; i < exceptionTypes.length; i++) {
                        BUFFER.append(", " + exceptionTypes[i].getName());
                    }
                    BUFFER.append("\"");
                }

                if (constructor.getDeclaringClass().equals(c)) {
                    BUFFER.append(" declared=\"true\"");
                }

                BUFFER.append("/>");
                print(BUFFER.toString());
            }


            for (Method method : c.getMethods()) {
                BUFFER.delete(0, BUFFER.length());
                BUFFER.append("<method");

                BUFFER.append(" modifiers=\"" + Modifier.toString(method.getModifiers()) + "\"");
                BUFFER.append(" return=\"" + method.getReturnType().getName() + "\"");
                BUFFER.append(" name=\"" + method.getName() + "\"");


                final Class[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length > 0) {
                    BUFFER.append(" parameters=\"");
                    BUFFER.append(parameterTypes[0].getName());
                    for (int i = 1; i < parameterTypes.length; i++) {
                        BUFFER.append(", " + parameterTypes[i].getName());
                    }
                    BUFFER.append("\"");
                }

                final Class[] exceptionTypes = method.getExceptionTypes();
                if (exceptionTypes.length > 0) {
                    BUFFER.append(" exceptions=\"");
                    BUFFER.append(exceptionTypes[0].getName());
                    for (int i = 1; i < exceptionTypes.length; i++) {
                        BUFFER.append(", " + exceptionTypes[i].getName());
                    }
                    BUFFER.append("\"");
                }


                if (method.getDeclaringClass().equals(c)) {
                    BUFFER.append(" declared=\"true\"");
                }

                checkBoolean(Method.class, method, "isBridge");
                checkBoolean(Method.class, method, "isSynthetic");
                checkBoolean(Method.class, method, "isVarArgs");

                BUFFER.append("/>");
                print(BUFFER.toString());
            }


            for (Field field : c.getFields()) {
                BUFFER.delete(0, BUFFER.length());
                BUFFER.append("<field");
                BUFFER.append(" modifiers=\"" + Modifier.toString(field.getModifiers()) + "\"");
                BUFFER.append(" type=\"" + field.getType().getName() + "\"");
                BUFFER.append(" name=\"" + field.getName() + "\"");

                if (field.getDeclaringClass().equals(c)) {
                    BUFFER.append(" declared=\"true\"");
                }

                checkBoolean(Field.class, field, "isEnumConstant");
                checkBoolean(Field.class, field, "isSynthetic");

                BUFFER.append("/>");
                print(BUFFER.toString());
            }

            print("</class>");
        } catch (ClassNotFoundException cnfe) {
            print("<class name=\"" + className + "\" found=\"false\"/>");
        }
    }


    private void checkBoolean(final Class c, final Object o, String name) {
        try {
            Method isArray = c.getMethod(name, new Class[0]);
            try {
                if ((Boolean) isArray.invoke(o, new Object[0])) {
                    BUFFER.append(" " + name + "=\"true=\"");
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
}
