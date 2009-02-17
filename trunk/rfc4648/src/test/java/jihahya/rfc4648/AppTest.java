package jinahya.rfc4648;


import java.io.*;
import java.security.*;
import java.util.*;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super( testName );
    }


    protected void setUp() throws Exception {
        original = File.createTempFile("prefix", "suffix");
        //Assert.assertEquals(original.canWrite(), true);
        System.out.println("original: " + original.getPath());

        encoded = File.createTempFile("prefix", "suffix");
        //Assert.assertEquals(encoded.canWrite(), true);
        System.out.println("encoded: " + encoded.getPath());

        decoded = File.createTempFile("prefix", "suffix");
        //Assert.assertEquals(decoded.canWrite(), true);
        System.out.println("decoded: " + decoded.getPath());
    }


    protected void tearDown() throws Exception {
        original.delete();
        encoded.delete();
        decoded.delete();
    }


    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }


    public void testApp() {
        String[] alphabet = new String[] { RFC4648Constants.base64,
                                           RFC4648Constants.base64url,
                                           RFC4648Constants.base32,
                                           RFC4648Constants.base32hex,
                                           RFC4648Constants.base16 };
        for (int j = 0; j < alphabet.length; j++) {
            for (int i = 0; i < 1024; i++) {
                try {
                    reset(original);
                    encode(alphabet[j]);
                    decode(alphabet[j]);
                    assertEquals(digest(original), digest(decoded));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }


    private void encode(String alphabet) throws IOException {
        final Writer output = new FileWriter(encoded);
        try {
            InputStream input = new FileInputStream(original);
            try {
                new RFC4648Encoder
                    (alphabet, input,
                     new EncodingOutputHandler() {
                         public void encoded(char encoded) throws IOException {
                             output.write(encoded);
                         }
                     }).encode();
                output.flush();
            } finally {
                input.close();
            }
        } finally {
            output.close();
        }
    }

    private void decode(String alphabet) throws IOException {
        final OutputStream output = new FileOutputStream(decoded);
        try {
            Reader input = new FileReader(encoded);
            try {
                new RFC4648Decoder
                    (alphabet, input,
                     new DecodingOutputHandler() {
                         public void decoded(int decoded) throws IOException {
                             output.write(decoded);
                         }
                     }).decode();
                output.flush();
            } finally {
                input.close();
            }
        } finally {
            output.close();
        }
    }

    private byte[] digest(File file) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        try {
            byte[] buffer = new byte[8192];
            for (int r = -1; (r = in.read(buffer)) != -1; ) {
                md.update(buffer, 0, r);
            }
            return md.digest();
        } finally {
            in.close();
        }
    }

    private void reset(File file) throws Exception {
        OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        try {
            int count = (int)(Math.random() * 100);
            byte[] buffer = new byte[8192];
            //byte[] buffer = new byte[1];

            Random random = new Random();
            for (int i = 0; i < count; i++) {
                random.nextBytes(buffer);
                out.write(buffer);
            }
            out.flush();
        } finally {
            out.close();
        }
    }


    private File original;
    private File encoded;
    private File decoded;
}