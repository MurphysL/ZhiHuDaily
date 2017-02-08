package com.murphysl.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.orhanobut.logger.Logger;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Logger.d("test" , "msg");
        Logger.i("test" , "123");
        Log.i(TAG, "onCreate: ");
    }
}
