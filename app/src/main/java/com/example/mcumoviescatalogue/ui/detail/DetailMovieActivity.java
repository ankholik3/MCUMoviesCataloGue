package com.example.mcumoviescatalogue.ui.detail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mcumoviescatalogue.Movie;
import com.example.mcumoviescatalogue.R;

public class DetailMovieActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";
    Float score;
    RatingBar rate;
    TextView tvMovieTitle;
    TextView tvDescription;
    TextView tvSynopsis;
    ImageView ivPoster;
    ImageView ivBanner;
    TextView tvReleaseDate;
    private DetailViewModel viewModel;
    ProgressDialog loadingDialog;

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
        rate = findViewById(R.id.rb_score);
        //tvDescription = findViewById(R.id.txt_description_detail);
        tvReleaseDate = findViewById(R.id.txt_release_date_detail);
        tvSynopsis = findViewById(R.id.txt_synopsis_detail);
        ivPoster = findViewById(R.id.img_poster_detail);
        ivBanner = findViewById(R.id.vv_trailer);

        loadingDialog = new ProgressDialog(DetailMovieActivity.this);
        loadingDialog.setMessage("Loading...");
        loadingDialog.show();

        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(DetailViewModel.class);

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        viewModel.getDetailMovie(Integer.parseInt(movie.getId())).observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie detailMovie) {
                if (detailMovie != null) {
                    tvMovieTitle.setText(detailMovie.getName());
                    //tvDescription.setText(movie.getDescriptionFromAPI());
                    tvSynopsis.setText(detailMovie.getDescriptionFromAPI());
                    tvReleaseDate.setText(detailMovie.getRelease_date());

                    score = (detailMovie.getVoteavg()*10/20);
                    rate.setRating(score);

                    Glide.with(getApplicationContext())
                            .load("https://image.tmdb.org/t/p/w500"+detailMovie.getPoster_path())
                            .apply(new RequestOptions().placeholder(R.drawable.img_placeholder).error(R.drawable.img_placeholder))
                            .into(ivPoster);
                    Glide.with(getApplicationContext())
                            .load("https://image.tmdb.org/t/p/w500"+detailMovie.getBanner_path())
                            .apply(new RequestOptions().placeholder(R.drawable.img_placeholder).error(R.drawable.img_placeholder))
                            .into(ivBanner);
                    loadingDialog.dismiss();
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


}
