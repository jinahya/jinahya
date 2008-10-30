package jinahya.iptv.bind;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 * @author Jin Kwon
 */
public interface Transferable {


    /**
     * @param in
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void receive(ObjectInputStream in)
        throws IOException, ClassNotFoundException;


    /**
     *
     * @param out
     * @throws IOException
     */
    public void send(ObjectOutputStream out) throws IOException;
}