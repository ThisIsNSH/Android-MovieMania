package com.travis.movie.activity;

/**
 * Created by LENOVO on 04-07-2018.
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import com.travis.movie.adapter.DrawerItemCustomAdapter;
import com.travis.movie.extra.DataModel;
import com.travis.movie.R;
import com.travis.movie.adapter.DrawerItemCustomAdapter;
import com.travis.movie.extra.DataModel;

public class Drawer extends AppCompatActivity {

    LinearLayout linearLayout;
    Toolbar toolbar;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        mTitle = mDrawerTitle = getTitle();
        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        linearLayout = findViewById(R.id.drawer_linear);
        setupToolbar();
        Log.w("before","Logcat save");
        // File logFile = new File( + "/log.txt" );
        String filePath = Environment.getExternalStorageDirectory() + "/logcat.txt";

        try {
            Process process = Runtime.getRuntime().exec("logcat -d");
            process = Runtime.getRuntime().exec( "logcat -f " + "/storage/emulated/0/"+"Logging.txt");
            Runtime.getRuntime().exec(new String[]{"logcat", "-f", filePath, "MyAppTAG:V", "*:S"});

        }catch(Exception e)
        {
            e.printStackTrace();
        }


        DataModel[] drawerItem = new DataModel[3];

        drawerItem[0] = new DataModel("BLALAL");
        drawerItem[1] = new DataModel("BLALALALA");
        drawerItem[2] = new DataModel("BLABLABLABLA");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

//        displayFragment(0);
        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    void setupDrawerToggle() {
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            selectItem(position);
        }

    }
    @Override
    public void onBackPressed(){
        //super.onBackPressed();
        //  Toast.makeText(this, "Press home button to quit", Toast.LENGTH_SHORT).show();
    }
}