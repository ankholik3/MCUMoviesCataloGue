package com.example.mcumoviescatalogue.ui;

import android.content.Intent;
import android.os.Bundle;

import com.example.mcumoviescatalogue.R;
import com.example.mcumoviescatalogue.ui.favorite.TabMenuFavorite;
import com.example.mcumoviescatalogue.ui.main.MovieFragment;
import com.example.mcumoviescatalogue.ui.main.TvShowFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.mcumoviescatalogue.ui.main.SectionsPagerAdapter;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }else if (item.getItemId() == R.id.action_favorite) {
            Intent mIntent = new Intent(this, TabMenuFavorite.class);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}