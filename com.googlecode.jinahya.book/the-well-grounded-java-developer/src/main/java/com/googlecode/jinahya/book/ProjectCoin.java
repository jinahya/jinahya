

package com.googlecode.jinahya.book;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class ProjectCoin {


    private static void throwIoOrSql() throws IOException, SQLException {
        // empty
    }


    private static void rethrowIoOrSql() throws IOException, SQLException {
        try {
            throwIoOrSql();
        } catch (Exception e) {
            throw e; // won't compile with pre Java 7
        }
    }


    /**
     * Returns a collection of given {@code elements}.
     *
     * @param elements elements to be collected
     *
     * @return a collection containing given {@code elements}.
     *
     * @see <a href="http://goo.gl/WKdkX">Improved Compiler Warnings and Errors
     * When Using Non-Reifiable Formal Parameters with Varargs Methods</a>
     */
    @SafeVarargs // possible heap pollution from parameterized vararg type
    private static <T> Collection<T> collect(final T... elements) {

        final Collection<T> collection = new ArrayList<>(elements.length);

        for (T element : elements) {
            collection.add(element);
        }

        return collection;

        //return Arrays.asList(elements);
    }


    public static void main(final String[] args)
        throws IOException, SQLException {


        // --------------------------------------------------- STRINGS-IN-SWITCH
        final String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH)
            .format(Calendar.getInstance().getTime());
        System.out.println("Today in English: " + dayOfWeek);
        System.out.print("Today in Korean: ");
        switch (dayOfWeek) {
            case "Sunday":
                System.out.println("일요일");
                break;
            case "Monday":
                System.out.println("월요일");
                break;
            case "Tuesday":
                System.out.println("화요일");
                break;
            case "Wednesday":
                System.out.println("수요일");
                break;
            case "Thursday":
                System.out.println("목요일");
                break;
            case "Friday":
                System.out.println("금요일");
                break;
            default:
                assert dayOfWeek.equals("Saturday");
                System.out.println("토요일");
                break;
        }



        // ---------------------------------------------- BINARY_INTEGER_LITERAL

        final int b1 = 0b00001111;
        final int h1 = 0x0F;
        assert b1 == h1;

        final int b2 = 0b11001010_11111110_10111010_10111110;
        final int h2 = 0xCA_FE_BA_BE;
        assert b2 == h2;

        //final int b1 = 0b_1; // won't compile
        //final int b2 = 0b1_; // won't compile



        // --------------------------------------------------------- MULTI-CATCH
        try {
            throwIoOrSql();
        } catch (IOException ioe) {
            // log(ioe);
            ioe = null; // ok
        } catch (SQLException sqle) {
            // log(sqle);
            sqle = null; // ok
        }

        try {
            throwIoOrSql();
        } catch (IOException | SQLException e) {
            // log(e);
            // e = null; // won't compile; e is implicitly final
            // smaller bytecode size!!!
        }


        // --------------------------------------- IMPROVED TYPE CHECKED RETHROW

        rethrowIoOrSql(); // see this method



        // -------------------------------------------------- TRY-WITH-RESOURCES

        final File source = new File("pom.xml");
        final File target = new File("target" + File.separator + "pom.xml");

        // traditional I/O
        final InputStream input1 = new ExtendedFileInputStream(source);
        try {
            final OutputStream output1 = new ExtendedFileOutputStream(target);
            try {
                final byte[] buffer = new byte[8192];
                for (int read; (read = input1.read(buffer)) != -1;) {
                    output1.write(buffer, 0, read);
                }
                output1.flush();
            } finally {
                output1.close();
            }
        } finally {
            input1.close();
        }

        assert AutoCloseable.class.isAssignableFrom(InputStream.class);

        // try-with-resources
        try (InputStream input2 = new ExtendedFileInputStream(source);
             OutputStream output2 = new ExtendedFileOutputStream(target)) {
            final byte[] buffer = new byte[8192];
            for (int read; (read = input2.read(buffer)) != -1;) {
                output2.write(buffer, 0, read);
            }
            output2.flush();
        }

        // watch out!!!
        final OutputStream notGonnaBeClosed =
            new ExtendedFileOutputStream(target);
        try {
            try (final OutputStream errorOnClosing =
                new ExtendedOutputStream(notGonnaBeClosed, true)) {
            }
        } catch (IOException ieo) {
            // exception from outer close()
            // inner close() is not gonna be closed
        }



        // ------------------------------------------------------ DIAMOND SYNTAX

        // really verbose
        final Map<Integer, Map<String, String>> map1 =
            new HashMap<Integer, Map<String, String>>();

        // using a new form of type of inference
        final Map<Integer, Map<String, String>> map2 = new HashMap<>();



        // -------------------------------- SIMPLIFIED VARARGS METHOD INVOCATION

        // won't compile; 'generic array creation'
        // final List<String>[] list1 = new ArrayList<>[3];

        // somewhat silly
        @SuppressWarnings("unchecked")
        final List<String>[] list2 = new ArrayList[3];

        final Collection<Integer> integers = collect(1, 2);
        final Collection<String> strings = collect("a", "b");
        final Collection<?> mixed = collect(1, "a");

        // type-erasure; compiler removes information related to type parameters
        // and type arguments
        try {
            final Method collect =
                ProjectCoin.class.getDeclaredMethod("collect", Object[].class);
            assert collect.isVarArgs();
            try {
                final List<?> collected = (List) collect.invoke(
                    null, new Object[]{new Object[]{Integer.valueOf(1), "a"}});
                assert collected.get(0).equals(Integer.valueOf(1));
                assert collected.get(1).equals("a");
            } catch (IllegalAccessException | InvocationTargetException e) {
                // multi-catch, huh?
            }
        } catch (NoSuchMethodException nsme) {
            // different depth
        }
    }


    private ProjectCoin() {
        super();
    }


}

