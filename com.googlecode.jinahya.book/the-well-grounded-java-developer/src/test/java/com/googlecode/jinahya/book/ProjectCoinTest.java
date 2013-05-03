

package com.googlecode.jinahya.book;


import static com.googlecode.jinahya.book.ProjectCoin.*;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ProjectCoinTest {


    private static final Logger LOGGER =
        Logger.getLogger(ProjectCoinTest.class.getName());


    @Test
    public static void testDayOfWeek() throws ParseException {

        final Calendar calendar = Calendar.getInstance();

        final int expected = calendar.get(Calendar.DAY_OF_WEEK);

        System.out.printf("%1$20s %2$10s %3$s\n", "country", "language",
                          "today");
        for (Locale locale : Locale.getAvailableLocales()) {
            if (locale.getCountry().isEmpty()) {
                continue;
            }
            System.out.printf("%1$20s %2$10s ",
                              locale.getDisplayCountry(Locale.US),
                              locale.getDisplayLanguage(Locale.US));
            final String dayOfWeek = printDayOfWeek(locale, expected);
            System.out.print(dayOfWeek);
            final int actual = parseDayOfWeek(locale, dayOfWeek);
            Assert.assertEquals(actual, expected);
            System.out.println();
        }
    }


}

