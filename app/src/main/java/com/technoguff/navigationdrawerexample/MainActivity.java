package com.technoguff.navigationdrawerexample;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.technoguff.navigationdrawerexample.adapters.DrawerAdapter;
import com.technoguff.navigationdrawerexample.models.DrawerItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView.LayoutManager layoutManager;
    private DrawerAdapter mDrawerAdapter;
    private RecyclerView mRecyclerView;
    private ArrayList<DrawerItem> mDrawerItemList;

    private DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar)findViewById(R.id.appBar);
        setSupportActionBar(mToolbar);



        //Dummy Data
        mDrawerItemList = new ArrayList<DrawerItem>();
        DrawerItem item = new DrawerItem();
        item.setIcon(R.drawable.inbox);
        item.setTitle("Inbox");
        mDrawerItemList.add(item);

        DrawerItem item2 = new DrawerItem();
        item2.setIcon(R.drawable.send);
        item2.setTitle("Send");
        mDrawerItemList.add(item2);



        mRecyclerView = (RecyclerView) findViewById(R.id.drawerRecyclerView);

        DrawerAdapter adapter = new DrawerAdapter(mDrawerItemList);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //TODO Add some action here
                //Executed when drawer closes

                Toast.makeText(MainActivity.this, "Closed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //TODO Add some action here
                //executes when drawer open
                Toast.makeText(MainActivity.this, "Opened", Toast.LENGTH_SHORT).show();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();


        adapter.setOnItemClickLister(new DrawerAdapter.OnItemSelecteListener() {
            @Override
            public void onItemSelected(View v, int position) {
                Toast.makeText(MainActivity.this, "You clicked at position: "+ position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
