package com.example.mcumoviescatalogue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailTvShowActivity extends AppCompatActivity {
    public static final String EXTRA_TV_SHOW = "extra_tv_show";
    TextView tvTvTitle;
    TextView tvTvDescription;
    TextView tvTvSynopsis;
    ImageView ivTvPoster;
    TextView tvTvReleaseDate;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv_show);
        tvTvTitle = findViewById(R.id.txt_tv_title_detail);
        tvTvDescription = findViewById(R.id.txt_tv_description_detail);
        tvTvReleaseDate = findViewById(R.id.txt_tv_release_date_detail);
        tvTvSynopsis = findViewById(R.id.txt_tv_synopsis_detail);
        ivTvPoster = findViewById(R.id.img_tv_poster_detail);

        TvShow tvShow = getIntent().getParcelableExtra(EXTRA_TV_SHOW);
        tvTvTitle.setText(tvShow.getTvShowTitle());
        tvTvDescription.setText(tvShow.getTvShowDescription());
        tvTvSynopsis.setText(tvShow.getTvShowSynopsis());
        tvTvReleaseDate.setText(tvShow.getTvShowReleaseDate());
        ivTvPoster.setImageResource(tvShow.getImgTvShowPoster());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
