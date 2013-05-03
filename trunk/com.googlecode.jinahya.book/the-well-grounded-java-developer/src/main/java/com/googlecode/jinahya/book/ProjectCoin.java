

package com.googlecode.jinahya.book;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;


/**
 *
 * @author onacit
 */
public class ProjectCoin {


    private static final Logger LOGGER =
        Logger.getLogger(ProjectCoin.class.getName());


    protected static final String DAY_OF_WEEK_PATTERN = "EEEE";


    public static String printDayOfWeek(final Locale locale,
                                        final int dayOfWeek) {

        switch (dayOfWeek) {
            default:
        }

        final DateFormat dateFormat =
            new SimpleDateFormat(DAY_OF_WEEK_PATTERN, locale);

        final Calendar calendar = Calendar.getInstance(locale);
        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);

        return dateFormat.format(calendar.getTime());
    }


    public static int parseDayOfWeek(final Locale locale,
                                     final String dayOfWeek)
        throws ParseException {

        switch (dayOfWeek) {
            default:
        }

        final DateFormat dateFormat = new SimpleDateFormat("EEEE", locale);

        final Date date = dateFormat.parse(dayOfWeek);

        final Calendar calendar = Calendar.getInstance(locale);
        calendar.setTime(date);

        return calendar.get(Calendar.DAY_OF_WEEK);
    }


    protected static int parseBinaryIntegerLiteral(
        String binaryIntegerLiteral) {

        if (binaryIntegerLiteral.startsWith("0b")) {
            binaryIntegerLiteral = binaryIntegerLiteral.substring(2);
        }

        return Integer.parseInt(binaryIntegerLiteral, 2);

    }


    private static void throwIoOrSql(final Boolean flag)
        throws IOException, SQLException {

        if (flag == null) {
            throwIoOrSql(ThreadLocalRandom.current().nextBoolean());
            return;
        }

        if (flag) {
            throw new IOException("IO");
        } else {
            throw new SQLException("SQL");
        }
    }


    /**
     * Copies bytes from {@code source} to {@code target}.
     *
     * @param source source file
     * @param target target file
     *
     * @throws IOException if an I/O error occurs
     */
    private static void copy1(final File source, final File target)
        throws IOException {

        System.out.println("copy1(" + source + ", " + target + ")");

        final InputStream input = new ExtendedFileInputStream(source);
        try {
            final OutputStream output = new ExtendedFileOutputStream(target);
            try {
                final byte[] buffer = new byte[8192];
                for (int read; (read = input.read(buffer)) != -1;) {
                    output.write(buffer, 0, read);
                }
                output.flush();
            } finally {
                output.close();
            }
        } finally {
            input.close();
        }
    }


    /**
     * Copies bytes from {@code source} to {@code target}.
     *
     * @param source source file
     * @param target target file
     *
     * @throws IOException if an I/O error occurs.
     */
    private static void copy2(final File source, final File target)
        throws IOException {

        System.out.println("copy2(" + source + ", " + target + ")");

        try (InputStream input = new ExtendedFileInputStream(source);
             OutputStream output = new ExtendedFileOutputStream(target)) {
            final byte[] buffer = new byte[8192];
            for (int read; (read = input.read(buffer)) != -1;) {
                output.write(buffer, 0, read);
            }
            output.flush();
        }
    }


    public static void main(final String[] args)
        throws IOException, SQLException {


        // --------------------------------------------------- strings-in-switch
        final String osName = System.getProperty("os.name");
        switch (osName) {
            default:
                System.out.println("os.name: " + osName);
                if (osName.contains("Windows")) {
                    System.out.println("Hell No! ");
                }
                break;
        }


        // ---------------------------------------------- binary-integer-literal
        final int b = 0b11001010_11111110_10111110_10111110;
        final int h = 0xCA_FE_BE_BE;
        assert b == h;

        //final int b1 = 0b_1; // won't compile
        //final int b2 = 0b1_; // won't compile


        // --------------------------------------------------------- multi-catch
        try {
            throwIoOrSql(null);
        } catch (IOException ioe) {
            // log(ioe);
        } catch (SQLException sqle) {
            // log(sqle);
        }

        try {
            throwIoOrSql(null);
        } catch (IOException | SQLException e) {
            // log(e);
            // e = null; // won't compile; e is implicitly final
            // smaller bytecode size!!!
        }


        // --------------------------------------- improved type checked rethrow
        try {
            throwIoOrSql(ThreadLocalRandom.current().nextBoolean());
        } catch (Exception e) {
            try {
                throw e; // NOTE that this(main) method doesn't throw Exception
            } catch (IOException | SQLException r) {
                // just re-cached for following statements.
            }
        }


        // -------------------------------------------------- try-with-resources
        assert AutoCloseable.class.isAssignableFrom(InputStream.class);

        copy1(new File("source.txt"), new File("target.txt"));

        copy2(new File("source.txt"), new File("target.txt"));

        // watch out!!!
        final OutputStream notGonnaBeClosed =
            new ExtendedFileOutputStream(new File("target.txt"));
        try {
            try (final OutputStream errorOnClosing =
                new ExtendedOutputStream(notGonnaBeClosed, true)) {
            }
        } catch (IOException ieo) {
            // exception from outer close()
            // inner close() is not gonna be closed
        }


        // ------------------------------------------------------- diamod-syntax
        final Map<Integer, Map<String, String>> map1 =
            new HashMap<Integer, Map<String, String>>();

        final Map<Integer, Map<String, String>> map2 =
            new HashMap<>();
    }


    private ProjectCoin() {
        super();
    }


}

