package jinahya.bmv.bind;


import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import jinahya.bmv.PropertyChangeSupported;


/**
 *
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class Bind extends PropertyChangeSupported
    implements Serializable {


    private static final Inflater inflater = new Inflater();
    private static final Deflater deflater = new Deflater();
    private static final ByteArrayOutputStream baos =
        new ByteArrayOutputStream();


    public Bind() {
        super();
    }


    /**
     *
     * @param in
     * @return this object
     * @throws IOException if an I/O error occurs
     */
    public abstract Bind receive(DataInput in) throws IOException;


    /**
     *
     * @param out
     * @return this object
     * @throws IOException if an I/O error occurs
     */
    public abstract Bind send(DataOutput out) throws IOException;


    private byte[] readBytes(DataInput in) throws IOException {
        byte[] v = new byte[in.readInt()];
        in.readFully(v);
        return v;
    }


    protected byte[] receiveBytes(DataInput in, boolean nullable)
        throws IOException {

        if (in.readBoolean()) { // null flag
            if (nullable) {
                return null;
            } else {
                throw new NullPointerException("null");
            }
        }
        if (in.readBoolean()) { // compression flag
            synchronized (inflater) {
                inflater.reset();
                inflater.setInput(readBytes(in));
                baos.reset();
                byte[] buffer = new byte[1024];
                while (!inflater.finished()) {
                    try {
                        baos.write(buffer, 0, inflater.inflate(buffer));
                    } catch (DataFormatException dfe) {
                        throw new IOException(dfe.getMessage());
                    }
                }
                baos.flush();
                return baos.toByteArray();
            }
        } else {
            return readBytes(in);
        }
    }


    private void writeBytes(DataOutput out, byte[] v) throws IOException {
        out.writeInt(v.length);
        out.write(v);
    }


    protected final void sendBytes(DataOutput out, byte[] v, boolean nullable,
                                   boolean deflate)
        throws IOException {

        if (!nullable && v == null) {
            throw new NullPointerException("null");
        }
        out.writeBoolean(v == null); // null flag
        if (v == null) {
            return;
        }
        out.writeBoolean(deflate); // compression flag
        if (deflate) {
            synchronized (deflater) {
                deflater.reset();
                deflater.setInput(v);
                deflater.finish();
                baos.reset();
                byte[] buffer = new byte[1024];
                while (!deflater.finished()) {
                    baos.write(buffer, 0, deflater.deflate(buffer));
                }
                baos.flush();
                writeBytes(out, baos.toByteArray());
            }
        } else {
            writeBytes(out, v);
        }
    }


    protected final String receiveString(DataInput in, boolean nullable)
        throws IOException {

        byte[] v = receiveBytes(in, nullable);
        if (v == null) {
            return null;
        }
        return new String(v, "UTF-8");
    }


    protected final void sendString(DataOutput out, String v, boolean nullable,
                                    boolean deflate)
            throws IOException {

        sendBytes(out, v == null ? null : v.getBytes("UTF-8"), nullable,
                  deflate);
    }
}