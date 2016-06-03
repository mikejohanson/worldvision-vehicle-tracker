package com.worldvision.vehicletracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import io.paperdb.Paper;

public class ViewEntriesActivity extends AppCompatActivity {
    private ArrayList<VehicleEntry> mList;
    private ArrayAdapter<VehicleEntry> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_entries);

        //getSupportActionBar().show();
        //setActionBar((Toolbar)findViewById(R.id.));
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        final ListView listview = (ListView) findViewById(R.id.lvEntries);
        mList =  Paper.book().read("entries");
        if(mList == null){
            mList = new ArrayList<VehicleEntry>();
        }
        // 5
        mAdapter = new VehicleEntryAdapter(this, mList);
        listview.setAdapter(mAdapter);
        registerForContextMenu(listview);
        // 6
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentViewEntries = new Intent(ViewEntriesActivity.this, NewEntryActivity.class);
                VehicleEntry ve = mList.get(i);
                intentViewEntries.putExtra("vehicleEntry",ve);
                startActivityForResult(intentViewEntries,1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            // 2 - Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // 3 - The user entered a new or modified an existing task. reload the list.
                mList =  Paper.book().read("entries");
                // 4
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //if (v.getId()==R.id.list_view) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_view_entries, menu);
        //}
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case R.id.delete:
                mList.remove(info.position);
                Paper.book().write("entries",mList);
                mAdapter.notifyDataSetChanged();
                // add stuff here
                return true;
//            case R.id.edit:
//                // edit stuff here
//                return true;
//            case R.id.delete:
//                // remove stuff here
//                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
