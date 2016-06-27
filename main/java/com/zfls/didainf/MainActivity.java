package com.zfls.didainf;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;

    //tab
    private LinearLayout topical;
    private LinearLayout entertainment;
    private LinearLayout lettres;
    private LinearLayout funny;
    private LinearLayout jokes;
    private LinearLayout games;

    private TextView topical_t;
    private TextView entertainment_t;
    private TextView lettres_t;
    private TextView funny_t;
    private TextView jokes_t;
    private TextView games_t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initside();

        initView();

        initWork();

        setSelect(0);
    }

    private void initWork() {

        topical.setOnClickListener(this);
        entertainment.setOnClickListener(this);
        lettres.setOnClickListener(this);
        funny.setOnClickListener(this);
        jokes.setOnClickListener(this);
        games.setOnClickListener(this);

    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        topical = (LinearLayout) findViewById(R.id.topical);
        entertainment = (LinearLayout) findViewById(R.id.entertainment);
        lettres = (LinearLayout) findViewById(R.id.lettres);
        funny = (LinearLayout) findViewById(R.id.funny);
        jokes = (LinearLayout) findViewById(R.id.jokes);
        games = (LinearLayout) findViewById(R.id.games);

        topical_t = (TextView) findViewById(R.id.topical_text_bg);
        entertainment_t = (TextView) findViewById(R.id.entertainment_text_bg);
        lettres_t = (TextView) findViewById(R.id.lettres_text_bg);
        funny_t = (TextView) findViewById(R.id.funny_text_bg);
        jokes_t = (TextView) findViewById(R.id.jokes_text_bg);
        games_t = (TextView) findViewById(R.id.games_text_bg);

        mFragments = new ArrayList<Fragment>();
        Fragment mTab01 = new TopicalFragment();
        Fragment mTab02 = new EntertainmentFragment();
        Fragment mTab03 = new LetteresFragment();
        Fragment mTab04 = new FunnyFragment();
        Fragment mTab05 = new JokesFragment();
        Fragment mTab06 = new GamesFragment();
        mFragments.add(mTab01);
        mFragments.add(mTab02);
        mFragments.add(mTab03);
        mFragments.add(mTab04);
        mFragments.add(mTab05);
        mFragments.add(mTab06);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };
        mViewPager.setAdapter(mAdapter);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int currentItem = mViewPager.getCurrentItem();
                setTab(currentItem);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    //侧滑
    private void initside() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.topical:
                //热点
                setSelect(0);
                break;
            case R.id.entertainment:
                //娱乐
                setSelect(1);
                break;
            case R.id.lettres:
                //美文
                setSelect(2);
                break;
            case R.id.funny:
                //搞笑
                setSelect(3);
                break;
            case R.id.jokes:
                //段子
                setSelect(4);
                break;
            case R.id.games:
                //游戏
                setSelect(5);
                break;

            default:
                break;
        }
    }

    private void setSelect(int i) {
        setTab(i);
        mViewPager.setCurrentItem(i);
    }


    private void setTab(int i) {

        changbg();
        switch (i){
            case 0:
                topical_t.setTextColor(ContextCompat.getColor(MainActivity.this , R.color.colorAccent));
                break;
            case 1:
                entertainment_t.setTextColor(ContextCompat.getColor(MainActivity.this , R.color.colorAccent));
                break;
            case 2:
                lettres_t.setTextColor(ContextCompat.getColor(MainActivity.this , R.color.colorAccent));
                break;
            case 3:
                funny_t.setTextColor(ContextCompat.getColor(MainActivity.this , R.color.colorAccent));
                break;
            case 4:
                jokes_t.setTextColor(ContextCompat.getColor(MainActivity.this , R.color.colorAccent));
                break;
            case 5:
                games_t.setTextColor(ContextCompat.getColor(MainActivity.this , R.color.colorAccent));
                break;
        }
    }

    private void changbg()
    {
        topical_t.setTextColor(ContextCompat.getColor(MainActivity.this , R.color.colorWrite));
        entertainment_t.setTextColor(ContextCompat.getColor(MainActivity.this , R.color.colorWrite));
        lettres_t.setTextColor(ContextCompat.getColor(MainActivity.this , R.color.colorWrite));
        funny_t.setTextColor(ContextCompat.getColor(MainActivity.this , R.color.colorWrite));
        jokes_t.setTextColor(ContextCompat.getColor(MainActivity.this , R.color.colorWrite));
        games_t.setTextColor(ContextCompat.getColor(MainActivity.this , R.color.colorWrite));
    }
}
