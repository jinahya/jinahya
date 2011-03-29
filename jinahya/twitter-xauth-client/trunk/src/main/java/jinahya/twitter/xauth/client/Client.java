

package jinahya.twitter.xauth.client;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * 
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public abstract class Client {


    private static final String ACCESS_TOKEN_URL =
        "https://api.twitter.com/oauth/access_token";


    protected static final String ALGORITHM = "HmacSHA1";


    protected static String percent(final String input)
        throws UnsupportedEncodingException {

        if (input == null) {
            throw new IllegalArgumentException("null input");
        }

        final StringBuffer buffer = new StringBuffer();

        final byte[] bytes = input.getBytes("UTF-8");
        for (int i = 0; i < bytes.length; i++) {
            final char ch = (char) (bytes[i] & 0xFF);
            if ((ch >= 0x30 && ch <= 0x39)
                || (ch >= 0x41 && ch <= 0x5A)
                || (ch >= 0x61 && ch <= 0x7A)
                || ch == 0x2D || ch == 0x2E || ch == 0x5F || ch == 0x7E) {
                buffer.append(ch);
            } else {
                buffer.append('%');
                if (ch <= 0x0F) {
                    buffer.append('0');
                }
                buffer.append(Integer.toHexString(ch).toUpperCase());
            }
        }

        return buffer.toString();
    }


    /** The Base 16 Alphabet. */
    private static final char[] BASE16_ALPHABET = {
        '0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };


    /**
     * Encodes given <code>input</code> into a base16(hex).
     *
     * @param input octet input
     * @return a base 16 encoded string
     */
    protected static String base16(final byte[] input) {

        if (input == null) {
            throw new IllegalArgumentException("null input");
        }

        final char[] output = new char[input.length * 2];

        int octet;
        for (int i = 0; i < input.length; i++) {
            octet = input[i] & 0xFF;
            output[i * 2 + 1] = BASE16_ALPHABET[octet & 0xF];
            output[i * 2] = BASE16_ALPHABET[octet >> 4];
        }

        return new String(output);
    }


    /** The Base 64 Alphabet. */
    private static final char[] BASE64_ALPHABET = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
        'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
        'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
        'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
        'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
        'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
        'w', 'x', 'y', 'z', '0', '1', '2', '3',
        '4', '5', '6', '7', '8', '9', '+', '/'
    };


    /**
     * Encodes given byte array into a BASE64 string.
     *
     * @param input octet input
     * @return a BASE64 string
     */
    protected static String base64(final byte[] input) {

        if (input == null) {
            throw new IllegalArgumentException("null input");
        }

        int count = input.length / 3;
        if (input.length % 3 > 0) {
            count++;
        }

        final char[] output = new char[count * 4];
        int index = 0;

        int pad = 0;
        int word;
        for (int i = 0; i < input.length; i += 3) {
            word = 0x00;
            for (int j = 0; j < 3; j++) {
                if ((i + j) < input.length) {
                    word <<= 8;
                    word |= (input[i + j] & 0xFF);
                } else {
                    switch (j) {
                        case 1:
                            pad = 2;
                            word <<= 4;
                            break;
                        case 2:
                            pad = 1;
                            word <<= 2;
                            break;
                        default:
                            break;
                    }
                    break;
                }
            }
            for (int j = 0; j < pad; j++) {
                output[index + 3 - j] = '=';
            }
            for (int j = 3 - pad; j >= 0; j--) {
                output[index + j] = BASE64_ALPHABET[word & 0x3F];
                word >>= 6;
            }
            if (pad != 0) {
                break;
            }
            index += 4;
        }

        return new String(output);
    }


    /**
     * Sorts given list which contains key-value sequence.
     *
     * @param <T> type
     * @param list  list
     */
    protected static <T extends Comparable<T>> void sort(final List<T> list) {

        boolean swapped;

        // ------------------------------------------------------------ BY VALUE
        for (int i = list.size() - 1; i >= 3;) {
            final T sourceValue = list.get(i);
            swapped = false;
            for (int j = 1; j <= i - 2; j += 2) {
                final T targetValue = list.get(j);
                if (targetValue.compareTo(sourceValue) > 0) {
                    Collections.swap(list, i - 1, j - 1); // key
                    Collections.swap(list, i, j); // value
                    swapped = true;
                    break;
                }
            }
            if (!swapped) {
                i -= 2;
            }
        }

        // -------------------------------------------------------------- BY KEY
        for (int i = list.size() - 2; i >= 2;) {
            final T sourceKey = list.get(i);
            swapped = false;
            for (int j = 0; j <= i - 2; j += 2) {
                final T targetKey = list.get(j);
                if (targetKey.compareTo(sourceKey) > 0) {
                    Collections.swap(list, i, j); // key
                    Collections.swap(list, i + 1, j + 1); // value
                    swapped = true;
                    break;
                }
            }
            if (!swapped) {
                i -= 2;
            }
        }
    }


    /**
     * Tests.
     *
     * @param <T> type
     * @param type client type
     * @param args consumer_key, consumer_secret, username, password, method,
     *             url, authorize
     * @throws Exception if an error occurs.
     */
    protected static <T extends Client> void main(final Class<T> type,
                                                  final String[] args)
        throws Exception {

        final Constructor<T> constructor = type.getConstructor(
            new Class<?>[]{String.class, String.class});

        final String consumerKey = args[0];
        final String consumerSecret = args[1];
        final T instance = constructor.newInstance(consumerKey, consumerSecret);

        final String username = args[2];
        final String password = args[3];
        instance.signIn(username, password);

        final String method = args[4];
        final String url = args[5];
        final boolean authorize = Boolean.parseBoolean(args[6]);
        final List<String> parameters = Collections.emptyList();
        final InputStream stream =
            instance.request(method, url, parameters, authorize);
        try {
            final BufferedReader reader =
                new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            try {
                for (String line = null; (line = reader.readLine()) != null;) {
                    System.out.println(line);
                }
            } finally {
                reader.close();
            }
        } finally {
            stream.close();
        }

        instance.signOut();
    }


    /**
     * Creates a new instance.
     *
     * @param consumerKey consumer key
     * @param consumerSecret consumer secret
     */
    public Client(final String consumerKey, final String consumerSecret) {
        super();

        if (consumerKey == null) {
            throw new IllegalArgumentException("null consumerKey");
        }

        if (consumerSecret == null) {
            throw new IllegalArgumentException("null consumerSecret");
        }

        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
    }


    /**
     * Sign in.
     *
     * @param username username
     * @param password password
     * @throws Exception if an error occurs.
     */
    public void signIn(final String username, final String password)
        throws Exception {

        if (username == null) {
            throw new IllegalArgumentException("null username");
        }

        if (password == null) {
            throw new IllegalArgumentException("null password");
        }

        signOut();

        final List<String> parameters = new LinkedList<String>();

        // ----------------------------------------------------- x_auth_username
        parameters.add("x_auth_username");
        parameters.add(username);

        // ----------------------------------------------------- x_auth_password
        parameters.add("x_auth_password");
        parameters.add(password);

        // --------------------------------------------------------- x_auth_mode
        parameters.add("x_auth_mode");
        parameters.add("client_auth");

        final InputStream stream = request(
            "POST", ACCESS_TOKEN_URL, parameters, true, "", "");
        try {
            final InputStreamReader reader =
                new InputStreamReader(stream, "UTF-8");
            try {
                final StringBuffer buffer = new StringBuffer();
                String key = null;
                String value = null;
                for (int c = 0; (c = reader.read()) != -1;) {
                    switch (c) {
                        case '=':
                            key = buffer.toString();
                            buffer.delete(0, buffer.length());
                            break;
                        case '&':
                            value = buffer.toString();
                            buffer.delete(0, buffer.length());
                            responses.put(key, value);

                            break;
                        default:
                            buffer.append((char) c);
                            break;
                    }
                }
            } finally {
                reader.close();
            }
        } finally {
            stream.close();
        }
    }


    /**
     * 
     * @param method request method
     * @param url normalized resource url
     * @param parameters
     * @param authorize flag for authentication requirement
     * @return resource stream
     * @throws Exception if an error occurs.
     */
    public InputStream request(final String method, final String url,
                               final Map<String, String> parameters,
                               final boolean authorize)
        throws Exception {

        if (method == null) {
            throw new IllegalArgumentException("null method");
        }

        if (url == null) {
            throw new IllegalArgumentException("null url");
        }

        if (parameters == null) {
            throw new IllegalArgumentException("null parameters");
        }

        if (authorize && !isSignedIn()) {
            throw new IllegalStateException("not signed in");
        }

        final List<String> list = new LinkedList<String>();
        for (Entry<String, String> entry : parameters.entrySet()) {
            list.add(entry.getKey());
            list.add(entry.getValue());
        }

        return request(method, url, list, authorize);
    }


    /**
     * 
     * @param method request method
     * @param url normalized resource url
     * @param parameters parameters
     * @param authorize flag for authentication requirement
     * @return resource stream
     * @throws Exception if an error occurs
     */
    public InputStream request(final String method, final String url,
                               final List<String> parameters,
                               final boolean authorize)
        throws Exception {

        if (method == null) {
            throw new IllegalArgumentException("null method");
        }

        if (url == null) {
            throw new IllegalArgumentException("null url");
        }

        if (parameters == null) {
            throw new IllegalArgumentException("null parameters");
        }

        if (authorize && !isSignedIn()) {
            throw new IllegalStateException("not signed in");
        }

        String token = "";
        String tokenSecret = "";
        if (authorize) {
            token = responses.get("oauth_token");
            tokenSecret = responses.get("oauth_token_secret");
        }

        return request(method, url, parameters, authorize, token,
                       tokenSecret);
    }


    /**
     * 
     * @param method HTTP method
     * @param url nomalized request URL
     * @param parameters parameters
     * @param authorize flag authenticate requirement
     * @param token token
     * @param tokenSecret token secret
     * @return resource stream
     * @throws Exception if an error occurs
     */
    private InputStream request(final String method, final String url,
                                final List<String> parameters,
                                final boolean authorize, final String token,
                                final String tokenSecret)
        throws Exception {

        if (method == null) {
            throw new IllegalArgumentException("null method");
        }

        if (url == null) {
            throw new IllegalArgumentException("null url");
        }

        if (parameters == null) {
            throw new IllegalArgumentException("null parameters");
        }

        String authorization = null;
        if (authorize) {
            authorization = authorize(method, url, parameters, token,
                                      tokenSecret);
        }

        final StringBuffer buffer = new StringBuffer();
        final Iterator<String> iterator = parameters.iterator();
        if (iterator.hasNext()) {
            buffer.append(URLEncoder.encode(iterator.next(), "UTF-8")).
                append("=").
                append(URLEncoder.encode(iterator.next(), "UTF-8"));
        }
        while (iterator.hasNext()) {
            buffer.append("&").
                append(URLEncoder.encode(iterator.next(), "UTF-8")).
                append("=").
                append(URLEncoder.encode(iterator.next(), "UTF-8"));
        }

        final boolean output = method.equals("POST");

        final String spec = url + (output ? "" : ("?" + buffer.toString()));

        final HttpURLConnection connection =
            (HttpURLConnection) new URL(spec).openConnection();

        connection.setRequestMethod(method);
        if (authorization != null) {
            connection.setRequestProperty("Authorization", authorization);
        }

        if (output) {
            connection.setDoOutput(true);
            final OutputStream out = connection.getOutputStream();
            try {
                out.write(buffer.toString().getBytes());
                out.flush();
            } finally {
                out.close();
            }
        }

        return connection.getInputStream();
    }


    public void signOut() {
        responses.clear();
    }


    public boolean isSignedIn() {
        return !responses.isEmpty();
    }


    /**
     * 
     * @param method request method
     * @param url normalized resource url
     * @param parameters parameters
     * @param token access token
     * @param tokenSecret token secret
     * @return OAuth Authorization header value
     * @throws Exception if an error occurs
     */
    protected String authorize(final String method, final String url,
                               final List<String> parameters,
                               final String token, final String tokenSecret)
        throws Exception {

        if (method == null) {
            throw new IllegalArgumentException("null method");
        }

        if (url == null) {
            throw new IllegalArgumentException("null url");
        }

        if (parameters == null) {
            throw new IllegalArgumentException("null parameters");
        }

        final List<String> list = new LinkedList<String>(parameters);

        // -------------------------------------------------- oauth_consumer_key
        list.add("oauth_consumer_key");
        list.add(consumerKey);

        // --------------------------------------------------------- oauth_token
        list.add("oauth_token");
        list.add(token);

        // ----------------------------------------------------- oauth_timestamp
        long timestamp = System.currentTimeMillis();
        if (list.contains("oauth_timestamp")) {
            final int index = parameters.indexOf("oauth_timestamp");
            parameters.remove(index + 1);
            parameters.remove(index);
        } else {
            list.add("oauth_timestamp");
            list.add(Long.toString(timestamp / 1000L));
        }

        // --------------------------------------------------------- oauth_nonce
        final byte[] nonce = new byte[16];
        for (int i = 7; i >= 0; i--) {
            nonce[i] = (byte) (timestamp & 0xFFL);
            timestamp >>>= 8;
        }
        long random = Double.doubleToRawLongBits(Math.random());
        for (int i = 15; i >= 8; i--) {
            nonce[i] = (byte) (random & 0xFFL);
            random >>>= 8;
        }
        if (list.contains("oauth_nonce")) {
            final int index = parameters.indexOf("oauth_nonce");
            parameters.remove(index + 1);
            parameters.remove(index);
        } else {
            list.add("oauth_nonce");
            list.add(base16(nonce));
        }

        // ---------------------------------------------- oauth_signature_method
        list.add("oauth_signature_method");
        list.add("HMAC-SHA1");

        // ------------------------------------------------------- oauth_version
        list.add("oauth_version");
        list.add("1.0");

        // ----------------------------------------------------- oauth_signature
        final String signature = sign(method, url, list, tokenSecret);
        list.add("oauth_signature");
        list.add(signature);

        final StringBuffer buffer =
            new StringBuffer("OAuth realm=\"" + url + "\"");
        for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
            final String key = iterator.next();
            final String value = iterator.next();
            if (parameters.contains(key)) {
                continue;
            }
            buffer.append(", ").
                append(percent(key)).
                append("=\"").
                append(percent(value)).
                append("\"");
        }
        return buffer.toString();
    }


    /**
     * 
     * @param method HTTP method
     * @param url normalized request URL
     * @param parameters OAuth parameters
     * @param tokenSecret token secret
     * @return signature
     * @throws Exception if an error occurs
     */
    protected String sign(final String method, final String url,
                          final List<String> parameters,
                          final String tokenSecret)
        throws Exception {

        final List<String> list = new LinkedList<String>(parameters);

        // ------------------------------------------------------ PERCENT ENCODE
        for (int i = 0; i < list.size(); i++) {
            list.add(i, percent(list.remove(i)));
        }

        // ---------------------------------------------------------------- SORT
        sort(list);

        // --------------------------------------------------------- CONCATENATE
        final StringBuffer buffer = new StringBuffer();
        final Iterator<String> iterator = list.iterator();
        if (iterator.hasNext()) {
            buffer.append(iterator.next()).append("=").append(iterator.next());
            while (iterator.hasNext()) {
                buffer.append("&").append(iterator.next()).append("=").
                    append(iterator.next());
            }
        }

        // ----------------------------------------------- SIGNATURE BASE STRING
        final String input = percent(method) + "&" + percent(url) + "&"
                             + percent(buffer.toString());

        // ----------------------------------------------------------------- KEY
        final String key = percent(consumerSecret) + "&" + percent(tokenSecret);

        // ----------------------------------------------------------- SIGNATURE
        return base64(HmacSHA1(key.getBytes(), input.getBytes()));
    }


    protected abstract byte[] HmacSHA1(final byte[] key, final byte[] input)
        throws Exception;


    private final String consumerKey;


    private final String consumerSecret;


    private final Map<String, String> responses =
        Collections.synchronizedMap(new HashMap<String, String>());
}
