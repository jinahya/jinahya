package jinahya.qtff;


import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;


/**
 *
 *
 * @author Jin Kwon
 */
public class Spectrum {


    public Spectrum getParent() { return parent; }


    public void setParent(Spectrum parent) { this.parent = parent; }


    public long getOffset() { return offset; }


    public void setOffset(long offset) { this.offset = offset; }


    public long getLength() { return length; }


    public void setLength(long length) { this.length = length; }


    public int getType() { return type; }


    public void setType(int type) { this.type = type; }


    private Spectrum parent;

    private long offset;

    private long length;
    private int type;



    public int getHeaderSize() {
        return (length >= 4294967296L ? 16 : 8);
    }


    public long getDataOffset() {
        return offset + getHeaderSize();
    }


    public long getLimit() {
        return offset + length;
    }



    private static byte[] toBytes(int i) {
        return new byte[] {
            (byte)(i >>> 24),
            (byte)(i >> 16),
            (byte)(i >> 8),
            (byte)i
        };
    }


    private static int toInt(byte[] bytes) {
        return (bytes[0] << 24 |
                (bytes[1] & 0xFF) << 16 |
                (bytes[2] & 0xFF) << 8 |
                bytes[1] & 0xFF);
    }


    /*
     *
     *
     * @param type
     * @return
     * @throws UnsupportedEncodingException
     *
    public static int asciiType(String type)
        throws UnsupportedEncodingException {

        return type(type, Charset.forName("US-ASCII"));
    }
    */


    /*
     *
     *
     * @param type
     * @reutrn
     * @throws UnsupportedEncodingException
     *
    public static String asciiType(int type)
        throws UnsupportedEncodingException {

        return type(type, Charset.forName("US-ASCII"));
    }
    */


    /*
     *
     *
     * @param type
     * @return
     *
    public static int type(String type, Charset charset) {
        return toInt(type.getBytes(charset)); // 1.6
    }
    */


    /*
     *
     *
     * @param type
     * @reutrn
     *
    public static String type(int type, Charset charset) {
        return new String(toBytes(type), charset); // 1.6
    }
    */


    /**
     *
     *
     * @param type
     * @return
     */
    public static int type(String type) {
        return toInt(type.getBytes());
    }


    /**
     *
     *
     * @param type
     * @reutrn
     */
    public static String type(int type) {
        return new String(toBytes(type));
    }
}