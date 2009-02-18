package jinahya.bitio;


import java.io.*;
import java.util.*;
import org.testng.*;
import org.testng.annotations.*;


public class BitIOTest {


    @Test
    public void test() throws IOException {

        final PipedOutputStream pout = new PipedOutputStream();
        final PipedInputStream pin = new PipedInputStream(pout);

        BitOutput out = new BitOutputImpl(new ByteOutput() {
                public void writeByte(int b) throws IOException {
                    pout.write(b);
                }
            });

        BitInput in = new BitInputImpl(new ByteInput() {
                public int readByte() throws IOException {
                    return pin.read();
                }
            });



        Random random = new Random();


        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(pout));

        for (int i = 0; i < 1048576; i++) {
            byte expected = new Integer((int)(Math.random() * 256) - 128).byteValue();
            dos.writeByte(expected);
            dos.flush();
            Assert.assertEquals(in.readInt(8), expected);
        }
        System.out.println("read byte done");

        for (int i = 0; i < 1048576; i++) {
            short expected = new Integer((int)(Math.random() * 65536) - 32768).shortValue();;
            dos.writeShort(expected);
            dos.flush();
            Assert.assertEquals(in.readInt(16), expected);
        }
        System.out.println("read short done");

        for (int i = 0; i < 1048576; i++) {
            int expected = random.nextInt();
            dos.writeInt(expected);
            dos.flush();
            Assert.assertEquals(in.readInt(32), expected);
        }
        System.out.println("read int done");

        for (int i = 0; i < 1048576; i++) {
            long expected = random.nextLong();
            dos.writeLong(expected);
            dos.flush();
            Assert.assertEquals(in.readLong(64), expected);
        }
        System.out.println("read long done");
        

        DataInputStream dis = new DataInputStream(new BufferedInputStream(pin));

        for (int i = 0; i < 1048576; i++) {
            byte expected = new Integer((int)(Math.random() * 256) - 128).byteValue();
            out.writeInt(8, (int)expected);
            pout.flush();
            Assert.assertEquals(dis.readByte(), expected);
        }
        System.out.println("write byte done");

        for (int i = 0; i < 1048576; i++) {
            short expected = new Integer((int)(Math.random() * 65536) - 32768).shortValue();;
            out.writeInt(16, (int)expected);
            pout.flush();
            Assert.assertEquals(dis.readShort(), expected);
        }
        System.out.println("write short done");

        for (int i = 0; i < 1048576; i++) {
            int expected = random.nextInt();
            out.writeInt(32, expected);
            pout.flush();
            Assert.assertEquals(dis.readInt(), expected);
        }
        System.out.println("write int done");

        for (int i = 0; i < 1048576; i++) {
            long expected = random.nextLong();
            out.writeLong(64, expected);
            pout.flush();
            Assert.assertEquals(dis.readLong(), expected);
        }
        System.out.println("write long done");


        for (int i = 0; i < 1048576; i++) {
            int expected = random.nextInt();
            out.writeInt(32, expected);
            pout.flush();
            Assert.assertEquals(in.readInt(32), expected);
        }
        System.out.println("signed 32bit int");


        for (int i = 0; i < 1048576; i++) {
            int length = (int)(Math.random() * 30 + 2); // 2 ~ 31
            int expected = (int)(Math.random() * Math.pow(2, length -1 ));
            if ((int)(Math.random() * 2) == 0) {
                expected = 0 - expected;
            }
            out.writeInt(length, expected);
            int pad = 8 - length % 8;
            if (pad > 0) { out.writeUnsignedInt(pad, 0); }
            pout.flush();

            Assert.assertEquals(in.readInt(length), expected);
            if (pad > 0) { in.readUnsignedInt(pad); }
        }
        System.out.println("signed int");

        for (int i = 0; i < 1048576; i++) {
            int length = (int)(Math.random() * 31 + 1); // 1 ~ 31
            int expected = (int)(Math.random() * Math.pow(2, length -1 ));
            out.writeUnsignedInt(length, expected);
            int pad = 8 - length % 8;
            if (pad > 0) { out.writeUnsignedInt(pad, 0); }
            pout.flush();

            Assert.assertEquals(in.readUnsignedInt(length), expected);
            if (pad > 0) {  in.readUnsignedInt(pad); }
        }
        System.out.println("unsigned int");



        for (int i = 0; i < 1048576; i++) {
            long expected = random.nextLong();
            out.writeLong(64, expected);
            pout.flush();
            Assert.assertEquals(in.readLong(64), expected);
        }
        System.out.println("signed 64bit long");

        for (int i = 0; i < 1048576; i++) {
            int length = (int)(Math.random() * 62 + 2); // 2 ~ 63
            long expected = (long)(Math.random() * Math.pow(2, length -1 ));
            if ((int)(Math.random() * 2) == 0) {
                expected = 0L - expected;
            }
            out.writeLong(length, expected);
            int pad = 8 - length % 8;
            if (pad > 0) { out.writeUnsignedInt(pad, 0); }
            pout.flush();

            Assert.assertEquals(in.readLong(length), expected);
            if (pad > 0) { in.readUnsignedInt(pad); }
        }
        System.out.println("signed long");

        for (int i = 0; i < 1048576; i++) {
            int length = (int)(Math.random() * 31 + 1); // 1 ~ 31
            long expected = (long)(Math.random() * Math.pow(2, length -1 ));
            out.writeUnsignedLong(length, expected);
            int pad = 8 - length % 8;
            if (pad > 0) { out.writeUnsignedInt(pad, 0); }
            pout.flush();

            Assert.assertEquals(in.readUnsignedLong(length), expected);
            if (pad > 0) { in.readUnsignedInt(pad); }
        }
        System.out.println("unsigned long");
    }
}