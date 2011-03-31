

package jinahya.twitter.xauth.client;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * 
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @param <T> connection type
 */
public interface Delegator<T> {


    /**
     * 
     * @param spec
     * @return
     * @throws Exception 
     */
    T open(String spec) throws Exception;


    /**
     * 
     * @param connector
     * @param method
     * @throws IOException 
     */
    void setRequestMethod(T connection, String method) throws IOException;


    /**
     * 
     * @param connector
     * @param key
     * @param value
     * @throws IOException 
     */
    void setRequestProperty(T connection, String key, String value)
        throws IOException;


    /**
     * 
     * @param connector
     * @throws IOException 
     */
    void connect(final T connection) throws IOException;


    /**
     * 
     */
    OutputStream getOutputStream (T connection) throws IOException;


    /**
     * 
     */
    InputStream getInputStream (T connection) throws IOException;


    /**
     * 
     * @param connection 
     */
    void close(T connection) throws IOException;
}
