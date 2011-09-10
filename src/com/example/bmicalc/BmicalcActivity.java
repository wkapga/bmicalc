package com.example.bmicalc;

import android.app.Activity;
import android.os.Bundle;

public class BmicalcActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
         int  d = 1;
         
         d = d * 2;
        
        setContentView(R.layout.main);
        
    }
}