package com.worldvision.vehicletracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import io.paperdb.Paper;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        Paper.init(getApplicationContext());
    }
    public void onBtnNewClick(View v){
        Intent intentNewEntry = new Intent(MainActivity.this, NewEntryActivity.class);
        startActivity(intentNewEntry);
    }
    public void onBtnViewEntriesClick(View v){
        Intent intentViewEntries = new Intent(MainActivity.this, ViewEntriesActivity.class);
        startActivity(intentViewEntries);
    }
    public void onBtnExportClick(View v){
        Intent intentViewEntries = new Intent(MainActivity.this, ExportActivity.class);
        startActivity(intentViewEntries);
    }
}
