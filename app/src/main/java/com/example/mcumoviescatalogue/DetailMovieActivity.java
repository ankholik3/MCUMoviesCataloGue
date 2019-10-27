package com.example.mcumoviescatalogue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailMovieActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";
    TextView tvMovieTitle;
    TextView tvDescription;
    TextView tvSynopsis;
    ImageView ivPoster;
    TextView tvReleaseDate;

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
        setContentView(R.layout.activity_detail_movie);
        tvMovieTitle = findViewById(R.id.txt_movie_title_detail);
        tvDescription = findViewById(R.id.txt_description_detail);
        tvReleaseDate = findViewById(R.id.txt_release_date_detail);
        tvSynopsis = findViewById(R.id.txt_synopsis_detail);
        ivPoster = findViewById(R.id.img_poster_detail);

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        tvMovieTitle.setText(movie.getMovieTitle());
        tvDescription.setText(movie.getDescription());
        tvSynopsis.setText(movie.getSynopsis());
        tvReleaseDate.setText(movie.getReleaseDate());
        ivPoster.setImageResource(movie.getImgPoster());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }


}
