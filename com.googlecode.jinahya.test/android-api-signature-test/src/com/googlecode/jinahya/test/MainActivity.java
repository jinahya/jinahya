

package com.googlecode.jinahya.test;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import java.util.concurrent.ThreadLocalRandom;


public class MainActivity extends Activity {


    private static final String TAG = "TEST";


    @Override
    public void onCreate(final Bundle savedInstanceState) {

        Log.i(TAG, "onCreate(" + savedInstanceState + ")");

        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        //final boolean flag = ThreadLocalRandom.current().nextBoolean();
    }


}
