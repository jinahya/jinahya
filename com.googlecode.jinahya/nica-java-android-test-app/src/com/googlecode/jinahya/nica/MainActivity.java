

package com.googlecode.jinahya.nica;


import android.app.Activity;
import android.os.Bundle;
import android.util.AndroidRuntimeException;
import android.util.Log;
import com.googlecode.jinahya.nica.client.AndroidNicaBuilderFactory;
import com.googlecode.jinahya.nica.client.NicaBuilderFactory;
import com.googlecode.jinahya.nica.client.NicaClientException;
import java.net.URL;
import java.net.URLConnection;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpGet;


public class MainActivity extends Activity {


    private static final String TAG = "NICA";


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        final NicaBuilderFactory factory;
        try {
            factory = AndroidNicaBuilderFactory.newInstance(this);
        } catch (NicaClientException nce) {
            throw new AndroidRuntimeException(nce);
        }

        Log.i(TAG, "factory: " + factory);

        try {
            final URLConnection connection =
                new URL("http://www.daum.net").openConnection();
            factory.newNicaBuilder().build(connection);

            for (Header header : Header.values()) {
                Log.i(TAG, header.getName() + ": "
                           + connection.getRequestProperty(header.getName()));
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }

        try {
            final HttpRequest request = new HttpGet("http://www.daum.net");
            factory.newNicaBuilder().build(request);

            for (Header header : Header.values()) {
                try {
                    Log.i(TAG, header.getName() + ": "
                               + request.getFirstHeader(header.getName())
                        .getValue());
                } catch (NullPointerException npe) {
                }
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }

    }


}
