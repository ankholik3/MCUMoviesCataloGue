package com.example.mcumoviescatalogue.ui.favorite;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.mcumoviescatalogue.R;
import com.example.mcumoviescatalogue.ui.TabMenuActivity;
import com.example.mcumoviescatalogue.ui.favorite.fragment.FavoriteMovieFragment;
import com.example.mcumoviescatalogue.ui.favorite.fragment.FavoriteTvShowFragment;
import com.example.mcumoviescatalogue.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class TabMenuFavorite extends AppCompatActivity {
    private Drawable star;

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


    /*@Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }*/

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
        mainFragmentPagerAdapter.addFragment(new FavoriteMovieFragment(), getResources().getString(R.string.tab_fav_1));
        mainFragmentPagerAdapter.addFragment(new FavoriteTvShowFragment(), getResources().getString(R.string.tab_fav_2));
        viewPager.setAdapter(mainFragmentPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        menu.getItem(0).setVisible(false);
        menu.getItem(2).setVisible(true);
        star = menu.getItem(1).getIcon();
        star.mutate();
        star.setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        } else if (item.getItemId() == R.id.action_favorite) {
            Intent mIntent = new Intent(this, TabMenuActivity.class);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }


}
