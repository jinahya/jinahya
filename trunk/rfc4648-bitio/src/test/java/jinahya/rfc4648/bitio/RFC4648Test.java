package jinahya.io.bitio;


import java.io.*;
import java.security.*;
import java.util.*;

import org.testng.*;
import org.testng.annotations.*;

import jinahya.rfc4648.*;
import jinahya.rfc4648.bitio.*;


public class RFC4648Test {


    @BeforeClass
    public void create() throws Exception {
        original = File.createTempFile("prefix", "suffix");
        Assert.assertEquals(original.canWrite(), true);
        System.out.println("original: " + original.getPath());

        encoded = File.createTempFile("prefix", "suffix");
        Assert.assertEquals(encoded.canWrite(), true);
        System.out.println("encoded: " + encoded.getPath());

        decoded = File.createTempFile("prefix", "suffix");
        Assert.assertEquals(decoded.canWrite(), true);
        System.out.println("decoded: " + decoded.getPath());
    }


    private void encode(String alphabet) throws IOException {
        final Writer output = new FileWriter(encoded);
        try {
            InputStream input = new FileInputStream(original);
            try {
                new RFC4648Encoder
                    (alphabet,
                     new EncodingOutputHandler() {
                         public void encoded(char encoded) throws IOException {
                             output.write(encoded);
                         }
                     },
                     input).encodeFinal();
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
                    (alphabet,
                     new DecodingOutputHandler() {
                         public void decoded(byte decoded) throws IOException {
                             output.write(decoded);
                         }
                     },
                     input).decodeFinal();
                output.flush();
            } finally {
                input.close();
            }
        } finally {
            output.close();
        }
    }


    @Test
    public void test() throws Exception {
        String[] alphabet = new String[] { RFC4648Constants.base64,
                                           RFC4648Constants.base64url,
                                           RFC4648Constants.base32,
                                           RFC4648Constants.base32hex,
                                           RFC4648Constants.base16 };
        for (int j = 0; j < alphabet.length; j++) {
            for (int i = 0; i < 1024; i++) {
                reset(original);
                encode(alphabet[j]);
                decode(alphabet[j]);
                Assert.assertEquals(digest(original), digest(decoded));
            }
        }
    }


    @AfterClass
    public void delete() throws Exception {
        original.delete();
        encoded.delete();
        decoded.delete();
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