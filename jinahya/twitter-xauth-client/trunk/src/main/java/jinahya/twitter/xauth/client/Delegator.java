

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


    /*
     * 
     * @param connector
     * @param method
     * @throws IOException 
    void setRequestMethod(T connection, String method) throws IOException;
     */


    /*
     * 
     * @param connector
     * @param key
     * @param value
     * @throws IOException 
    void setRequestProperty(T connection, String key, String value)
        throws IOException;
     */


    /**
     * 
     * @param connection
     * @param method
     * @param authorization
     * @param output
     * @throws IOException 
     */
    /**
     * 
     */
    void connect(final T connection, String method, String uri, String authorization, boolean output) throws IOException;


    /**
     * 
     */
    OutputStream openOutputStream(T connection) throws IOException;


    void closeOutputStream(T connection, OutputStream outputStream) throws IOException;


    /**
     * 
     */
    InputStream openInputStream(T connection) throws IOException;


    void closeInputStream(T connection, InputStream inputStream) throws IOException;


    /**
     * 
     * @param connection 
     */
    void close(T connection) throws IOException;
}
