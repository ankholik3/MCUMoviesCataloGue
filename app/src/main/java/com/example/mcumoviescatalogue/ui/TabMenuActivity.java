package com.example.mcumoviescatalogue.ui;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.mcumoviescatalogue.R;
import com.example.mcumoviescatalogue.ui.favorite.TabMenuFavorite;
import com.example.mcumoviescatalogue.ui.main.MovieFragment;
import com.example.mcumoviescatalogue.ui.main.SectionsPagerAdapter;
import com.example.mcumoviescatalogue.ui.main.TvShowFragment;
import com.google.android.material.tabs.TabLayout;

public class TabMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_menu);

        initViews();

    }

    private void initViews() {
        // setting toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // setting view pager
        ViewPager viewPager = findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        // setting tabLayout
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter mainFragmentPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mainFragmentPagerAdapter.addFragment(new MovieFragment(), getResources().getString(R.string.tab_text_1));
        //mainFragmentPagerAdapter.addFragment(new PlaceholderFragment(), "Tes1");
        mainFragmentPagerAdapter.addFragment(new TvShowFragment(), getResources().getString(R.string.tab_text_2));
        viewPager.setAdapter(mainFragmentPagerAdapter);
    }




}