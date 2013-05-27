

package com.googlecode.jinahya.test;


import java.io.*;
import java.util.regex.*;


public class EmailAddressRegex {


    private static void check(final Pattern pattern, final String name)
        throws IOException {

        System.out.println("----------------------------------------"
                           + "----------------------------------------");
        System.out.println(name);

        final InputStream addresses =
            EmailAddressRegex.class.getResourceAsStream(name);
        assert addresses != null;

        final BufferedReader reader =
            new BufferedReader(new InputStreamReader(addresses, "UTF-8"));
        float count = .0f;
        float match = .0f;
        long elapsed = 0L;
        for (String address; (address = reader.readLine()) != null;) {
            if (address.startsWith("#") || address.isEmpty()) {
                continue;
            }
            final long start = System.nanoTime();
            final Matcher matcher = pattern.matcher(address);
            final boolean matches = matcher.matches();
            elapsed += (System.nanoTime() - start);
            System.out.printf("%74s %5b\n", address, matches);
            count++;
            if (matches) {
                match++;
            }
        }
        System.out.printf("matches: %s\n",
                          (count > 0 ? ((match / count) * 100.f + "%") : "--"));
        System.out.printf("elapsed: %d ns\n", elapsed);
    }


    public static void main(final String[] args) throws IOException {

        final InputStream expressions =
            EmailAddressRegex.class.getResourceAsStream("/expressions");
        assert expressions != null;

        final BufferedReader reader =
            new BufferedReader(new InputStreamReader(expressions, "UTF-8"));
        for (String regex; (regex = reader.readLine()) != null;) {
            if (regex.startsWith("#") || regex.isEmpty()) {
                continue;
            }
            System.out.println("========================================"
                               + "========================================");
            for (int i = 0; i < regex.length(); i++) {
                if (i > 0 && i % 80 == 0) {
                    System.out.println();
                }
                System.out.print(regex.charAt(i));
            }
            System.out.println();
            final Pattern pattern;
            try {
                pattern = Pattern.compile(regex);
            } catch (PatternSyntaxException pse) {
                pse.printStackTrace(System.err);
                continue;
            }
            check(pattern, "/valid");
            check(pattern, "/invalid");
            check(pattern, "/unknown");
        }
    }


}