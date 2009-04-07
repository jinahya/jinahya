package jinahya.qtff;


import java.io.DataInput;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collection;


/**
 *
 *
 * @author Jin Kwon
 */
public class Microscope {


    private static final int _ROOT_SPECTRUM_TYPE = 1380929364; // "ROOT"


    public Microscope(RandomAccessFile file) {
        super();

        this.file = file;
    }


    private RandomAccessFile file;


    /**
     * Returns a virtual root spectrum.
     *
     * @return a virual root spectrum
     * @throws IOException if an I/O error occurs
     */
    private Spectrum ROOT() throws IOException {
        if (root == null) {
            long offset = -8L;
            long length = file.length() + 8L;
            if (length >= 4294967296L) { // 2^32
                offset -= 8L;
                length += 8L;
            }
            Spectrum root = new Spectrum();
            root.setParent(null);
            root.setOffset(offset);
            root.setLength(length);
            root.setType(_ROOT_SPECTRUM_TYPE);
        }
        return root;
    }


    private transient Spectrum root;



    /**
     *
     *
     * @param parent
     * @param spectrums
     * @return this microscope
     * @throws IOException if an I/O error occurs
     */
    public Microscope getChildren(Spectrum parent,
                                  Collection<Spectrum> spectrums)
        throws IOException {

        if (parent == null) {
            return getChildren(ROOT(), spectrums);
        }

        long childOffset = parent.getDataOffset();
        while ((childOffset + 8L) <= parent.getLimit()) {
            Spectrum child = read(childOffset);

            if (child == null) {
                break;
            }

            if (child.getLimit() > parent.getLimit()) {
                break;
            }

            if (parent.getType() != _ROOT_SPECTRUM_TYPE) {
                child.setParent(parent);
            }

            spectrums.add(child);

            childOffset = child.getLimit();
        }

        return this;
    }


    /**
     *
     *
     * @param offset
     * @return parsed spectrum of null
     * @throws IOException if an I/O error occurs
     */
    private Spectrum read(long offset) throws IOException {
        file.seek(offset);

        long length = (file.readInt() & 0xFFFFFFFFL);
        if (length < 8L) {
            return null;
        }
        int type = file.readInt();
        if (length == 1L) { // extended size
            length = file.readLong();
            if (length < 16L) {
                return null;
            }
        }

        Spectrum spectrum = new Spectrum();
        spectrum.setOffset(offset);
        spectrum.setLength(length);
        spectrum.setType(type);
        return spectrum;
    }



    /**
     *
     *
     * @param parent
     * @param spectrums
     * @param types
     * @throws IOException if an I/O error occurs
     */
    public void search(Spectrum parent, Collection<Spectrum> spectrums,
                       String... types)
        throws IOException {

        if (parent == null) {
            search(ROOT(), spectrums, types);
            return;
        }

        
    }

}