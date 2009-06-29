package jinahya.xlet.bind;


import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Hashtable;


/**
 *
 *
 * @author <a href="onacit@gmail.com">Jin Kwon</a>
 */
public abstract class Bind extends AbstractBind {


    public Bind() {
        super();
    }


    /**
     *
     * @param out
     * @return this object
     * @throws IOException if an I/O error occurs
     */
    public abstract Bind send(DataOutput out) throws IOException;


    /**
     *
     * @param in
     * @return this object
     * @throws IOException if an I/O error occurs
     */
    public abstract Bind receive(DataInput in) throws IOException;


    private final Hashtable reqParams = new Hashtable();
    private final Hashtable resParams = new Hashtable();
}