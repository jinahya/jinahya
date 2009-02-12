package jinahya.io;


import java.io.*;
import java.util.*;
import org.testng.*;
import org.testng.annotations.*;


public class BitIOTest {


    @Test
    public void test() throws IOException {

        final PipedOutputStream pout = new PipedOutputStream();
        final PipedInputStream pin = new PipedInputStream(pout);

        BitOutput out = new BitOutputImpl(new BitOutputAdapter() {
                public void putOctet(int octet) throws IOException {
                    pout.write(octet);
                }
            }, true);

        BitInput in = new BitInputImpl(new BitInputAdapter() {
                public int getOctet() throws IOException {
                    return pin.read();
                }
            }, true);



        out.writeInt(32, -1);
        Assert.assertEquals(in.readInt(32), -1);


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
    }
}