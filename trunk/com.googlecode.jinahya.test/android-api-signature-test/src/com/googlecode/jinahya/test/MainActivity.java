

package com.googlecode.jinahya.test;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends Activity {


    private static final String TAG = "TEST";


    @Override
    public void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        Log.i(TAG, "onCreate(" + savedInstanceState + ")");
    }


}
