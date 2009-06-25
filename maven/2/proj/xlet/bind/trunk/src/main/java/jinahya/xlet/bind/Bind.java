package jinahya.xlet.bind;


import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


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
    public abstract Object send(DataOutput out) throws IOException;


    /**
     *
     * @param in
     * @return this object
     * @throws IOException if an I/O error occurs
     */
    public abstract Object receive(DataInput in) throws IOException;
}