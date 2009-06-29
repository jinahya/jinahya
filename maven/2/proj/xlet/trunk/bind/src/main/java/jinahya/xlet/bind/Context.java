package jinahya.xlet.bind;


import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Hashtable;
import java.util.Properties;


/**
 *
 *
 * @author <a href="jinahya@gmail.com">Jin Kwon</a>
 */
public class Context {


    private static final Hashtable properties = new Hashtable();


    private static String FILENAME = "bind.properties";

    private static String KEY_BASE_URL = "base_url";

    //private static String KEY_RETRY_COUNT = "retry_count";


    private static Properties getProperties(String packageName)
        throws IOException {

        synchronized (properties) {
            if (properties.containsKey(packageName)) {
                return (Properties) properties.get(packageName);
            }
            InputStream in = new FileInputStream
                (packageName.replace('.', '/') +
                (packageName.length() == 0 ? "" : "/") + FILENAME);
            try {
                Properties _properties = new Properties();
                _properties.load(in);
                properties.put(packageName, _properties);
                return getProperties(packageName);
            } finally {
                in.close();
            }
        }
    }


    private static String getProperty(String packageName, String key)
        throws IOException {

        String value = getProperties(packageName).getProperty(key);
        if (value == null) {
            throw new IOException("property not found: " + key);
        }
        return value;
    }


    private static void constructSpec(StringBuffer spec, Bind bind, String name)
        throws IOException {

        String className = bind.getClass().getName();
        int lastDotIndex = className.lastIndexOf('.');
        String packageName = "";
        if (lastDotIndex != -1) {
            packageName = className.substring(0, lastDotIndex);
            className = className.substring(lastDotIndex);
        }

        String baseUrl = getProperty(packageName, KEY_BASE_URL);

        spec.append(baseUrl + (baseUrl.endsWith("/") ? "" : "/") + className);
        if (name != null) {
            spec.append((name.startsWith("/") ? "" : "/") + name);
        }
    }


    public static Bind receiveBind(Bind bind) throws IOException {
        StringBuffer spec = new StringBuffer();
        constructSpec(spec, bind, null);
        appendQuery(spec, bind);

        URL url = new URL(spec.toString());

        for (int i = 0; i < 3; i++) {
            try {
                DataInputStream in = new DataInputStream
                    (new URL(spec.toString()).openStream());
                try {
                    bind.receive(in);
                    return bind;
                } finally {
                    in.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        throw new IOException
            ("failed to receive bind from " + url.toExternalForm());
    }


    public static byte[] receiveAttachement(Bind bind, String name)
        throws IOException {

        StringBuffer spec = new StringBuffer();
        constructSpec(spec, bind, name);
        appendQuery(spec, bind);

        URL url = new URL(spec.toString());
        byte[] buffer = new byte[8192];

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            for (int i = 0; i < 3; i++) {
                baos.reset();
                try {
                    InputStream in = url.openStream();
                    try {
                        for (int read = -1; (read = in.read(buffer)) != -1; ) {
                            baos.write(buffer, 0, read);
                        }
                        baos.flush();
                        return baos.toByteArray();
                    } finally {
                        in.close();
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            throw new IOException
                ("failed to receive attachement from " + url.toString());
        } finally {
            baos.close();
        }
    }


    private static void appendQuery(StringBuffer spec, Bind bind) {
        String[][] parameters = bind.getParameters();
        if (parameters.length > 0) {
            spec.append("?" + parameters[0][0] + "=" + parameters[0][1]);
        }
        for (int i = 1; i < parameters.length; i++) {
            spec.append("&" + parameters[i][0] + "=" + parameters[i][1]);
        }
    }
}