

package com.googlecode.jinahya.book;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
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


    private static void rethrowIoOrSql1() throws IOException, SQLException {
        try {
            throwIoOrSql(null); // throws IOException, SQLException
        } catch (IOException | SQLException e) {
            throw e;
        }
    }


    private static void rethrowIoOrSql2() throws IOException, SQLException {
        try {
            throwIoOrSql(null); // throws IOException, SQLException
        } catch (Exception e) {
            throw e;
        }
    }


    private static void copy1(final File source, final File target)
        throws IOException {

        final InputStream input = new FileInputStream(source);
        try {
            final OutputStream output = new FileOutputStream(target);
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


    private static void copy2(final File source, final File target)
        throws IOException {

        try (InputStream input = new FileInputStream(source);
             OutputStream output = new FileOutputStream(target)) {
            final byte[] buffer = new byte[8192];
            for (int read; (read = input.read(buffer)) != -1;) {
                output.write(buffer, 0, read);
            }
            output.flush();
        }
    }


    public static void main(final String[] args) {


        // Strings in switch
        final String osName = System.getProperty("os.name");
        switch (osName) {
            default:
                if (osName.contains("win")) {
                    System.out.print("Hell No! ");
                }
                System.out.println("os.name: " + osName);
                break;
        }

        // BinaryIntegerLiteral
        final int b = 0b11001010_11111110_10111110_10111110;
        final int h = 0xCA_FE_BE_BE;
        assert b == h;

        //final int b1 = 0b_1; // won't compile
        //final int b2 = 0b1_; // won't compile


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
            // smaller bytecode size!!
        }


        // -------------------------------------------------- try-with-resources
        {
            final URL url = new URL("http://www.daum.net/index.html");

        }
    }


    private ProjectCoin() {
        super();
    }


}

