package com.example.mcumoviescatalogue.ui.favorite;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.mcumoviescatalogue.R;
import com.example.mcumoviescatalogue.ui.favorite.fragment.FavoriteMovieFragment;
import com.example.mcumoviescatalogue.ui.favorite.fragment.FavoriteTvShowFragment;
import com.example.mcumoviescatalogue.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class TabMenuFavorite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_tab_menu);
        // setting toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initViews();

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews() {

        // setting view pager
        ViewPager viewPager = findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        // setting tabLayout
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter mainFragmentPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mainFragmentPagerAdapter.addFragment(new FavoriteMovieFragment(), getResources().getString(R.string.tab_text_1));
        mainFragmentPagerAdapter.addFragment(new FavoriteTvShowFragment(), getResources().getString(R.string.tab_text_2));
        viewPager.setAdapter(mainFragmentPagerAdapter);
    }



}
