package com.example.priya.contactdemoproject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.priya.contactdemoproject.adapter.ViewPagerAdapter;
import com.example.priya.contactdemoproject.frontend.CallLogFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView ivDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;
    private FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView() {
        tabLayout=(TabLayout)findViewById(R.id.tab_layout);
        viewPager=(ViewPager)findViewById(R.id.view_pager);
        ivDrawer=(ImageView)findViewById(R.id.iv_drawer);
        ivDrawer.setOnClickListener(this);
        setUpPageViewer(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(Color.WHITE,Color.WHITE);

    }
    private void setUpPageViewer(ViewPager viewPager) {
        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CallLogFragment(),"Missed Calls");
        adapter.addFragment(new NotificationFragment(),"Notification");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.iv_drawer){
        }

    }

}

