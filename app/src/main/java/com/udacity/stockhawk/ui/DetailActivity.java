package com.udacity.stockhawk.ui;

import android.os.Bundle;
import android.app.Activity;

import com.udacity.stockhawk.R;

public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
