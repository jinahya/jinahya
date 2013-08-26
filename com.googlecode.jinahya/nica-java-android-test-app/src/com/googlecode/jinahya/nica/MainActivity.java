

package com.googlecode.jinahya.nica;


import android.app.Activity;
import android.os.Bundle;
import android.util.AndroidRuntimeException;
import android.util.Log;
import com.googlecode.jinahya.nica.client.AndroidNicaBuilderFactory;
import com.googlecode.jinahya.nica.client.NicaBuilderFactory;
import com.googlecode.jinahya.nica.client.NicaClientException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;


public class MainActivity extends Activity {


    private static final String TAG = "NICA-APP";


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
    }


    private void excute() {

        final NicaBuilderFactory factory;
        try {
            factory = AndroidNicaBuilderFactory.newInstance(this);
        } catch (NicaClientException nce) {
            throw new AndroidRuntimeException(nce);
        }

        Log.i(TAG, "factory: " + factory);


        final String base = "http://10.0.2.2:58080/nica-java-server-test";

        final String url = base + "/resources/test";

        try {
            final HttpURLConnection connection =
                (HttpURLConnection) new URL(url).openConnection();
            factory.newNicaBuilder().build(connection);

            for (Header header : Header.values()) {
                Log.i(TAG, header.getName() + ": "
                           + connection.getRequestProperty(header.getName()));
            }
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10000); // 10 secs
            Log.i(TAG, "connecting...");
            connection.connect();
            Log.i(TAG, "connected");
            final int responseCode = connection.getResponseCode();
            final String responseMessage = connection.getResponseMessage();
            Log.i(TAG, "response: " + responseCode + " " + responseMessage);
            if (responseCode == 200) {
                System.out.println("body: " + new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "UTF-8"))
                    .readLine());
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }

        try {
            final HttpRequest request = new HttpGet(url);
            factory.newNicaBuilder().build(request);

            for (Header header : Header.values()) {
                try {
                    Log.i(TAG, header.getName() + ": "
                               + request.getFirstHeader(header.getName())
                        .getValue());
                } catch (NullPointerException npe) {
                }
            }

            final HttpClient client = new DefaultHttpClient();
            Log.i(TAG, "executing...");
            final HttpResponse response =
                client.execute((HttpUriRequest) request);
            Log.i(TAG, "executed");
            final StatusLine statusLine = response.getStatusLine();
            final int statusCode = statusLine.getStatusCode();
            final String reasonPhrase = statusLine.getReasonPhrase();
            if (statusCode == 200) {
                Log.i(TAG, "response: " + statusCode + " " + reasonPhrase);
                System.out.println("body: " + new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent(),
                                          "UTF-8"))
                    .readLine());
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }


    @Override
    protected void onStart() {

        super.onStart();

        synchronized (this) {

            if (thread != null) {
                thread.interrupt();
                try {
                    thread.join();
                } catch (InterruptedException ie) {
                    ie.printStackTrace(System.err);
                }
            }

            thread = new Thread() {


                @Override
                public void run() {
                    while (!isInterrupted()) {
                        excute();
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException ie) {
                            break;
                        }
                    }
                }

            };

            thread.start();
        }
    }


    @Override
    protected void onStop() {

        synchronized (this) {
            if (thread != null) {
                thread.interrupt();
                try {
                    thread.join();
                } catch (InterruptedException ie) {
                    ie.printStackTrace(System.err);
                }
                thread = null;
            }
        }

        super.onStop();
    }


    private volatile Thread thread;


}
