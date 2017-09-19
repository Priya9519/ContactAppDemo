package com.example.priya.contactdemoproject.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.example.priya.contactdemoproject.R;
import com.example.priya.contactdemoproject.adapter.DrawerAdapter;
import com.example.priya.contactdemoproject.pojo.DrawerModel;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    private ArrayList<DrawerModel>dataList=new ArrayList<>();
    private DrawerAdapter mAdapter;
    private RecyclerView recyclerView;
    private DrawerLayout drawer;
    private ImageView ivDrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dataList.add(new DrawerModel("Google Map",R.mipmap.google_map));
        dataList.add(new DrawerModel("Auto-Reply",R.mipmap.auto_reply));
        dataList.add(new DrawerModel("Timer",R.mipmap.timer));
        dataList.add(new DrawerModel("VIP Caller",R.mipmap.call));
        dataList.add(new DrawerModel("Manual\nBlocking",R.mipmap.blocking));
        dataList.add(new DrawerModel("Formate",R.mipmap.format));
        dataList.add(new DrawerModel("Terms of Use",R.mipmap.tnc));

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        setRecyclerView(dataList);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        ivDrawer=(ImageView)findViewById(R.id.ivDrawer);
        ivDrawer.setOnClickListener(this);
//        ivDrawer.setTag("menu_icon");

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HomeFragment fragment = new HomeFragment();
        fragmentTransaction.add(R.id.content_frame, fragment);
        fragmentTransaction.commit();

      /*  ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();*/
    }
    /*public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
*/
   /* @Override
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
*/
   /* @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/
    public void setRecyclerView(ArrayList<DrawerModel> dataList) {
        mAdapter=new DrawerAdapter(dataList,this);
        LinearLayoutManager layoutManager = new  LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        if(ivDrawer.getTag() != null && ivDrawer.getTag().toString().equals("menu_icon")){
            ivDrawer.setImageDrawable(ContextCompat.getDrawable(this,R.mipmap.back_arrow));
            ivDrawer.setTag("back_arrow");
            drawer.openDrawer(drawer);
            Log.e("msg","clicke");

        }
        else if(ivDrawer.getTag() != null && ivDrawer.getTag().toString().equals("back_arrow")){
            ivDrawer.setImageDrawable(ContextCompat.getDrawable(this,R.mipmap.icon_menu));
            drawer.closeDrawer(drawer);
            ivDrawer.setTag("menu_icon");

        }else if (v.getId() == R.id.ivDrawer){
            if (drawer != null){
                if (drawer.isDrawerOpen(drawer))
                    drawer.closeDrawer(Gravity.LEFT);
                else
                    drawer.openDrawer(Gravity.RIGHT);
            }
        }

    }
}


