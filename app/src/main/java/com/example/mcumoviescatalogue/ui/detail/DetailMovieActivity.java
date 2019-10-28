package com.example.mcumoviescatalogue.ui.detail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.example.mcumoviescatalogue.database.DatabaseContract.*;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mcumoviescatalogue.database.MovieHelper;
import com.example.mcumoviescatalogue.model.Movie;
import com.example.mcumoviescatalogue.R;
import com.example.mcumoviescatalogue.ui.favorite.TabMenuFavorite;

import static com.example.mcumoviescatalogue.database.DatabaseContract.MovieColumns.BANNERPATH;
import static com.example.mcumoviescatalogue.database.DatabaseContract.MovieColumns.CATEGORY;
import static com.example.mcumoviescatalogue.database.DatabaseContract.MovieColumns.ID;
import static com.example.mcumoviescatalogue.database.DatabaseContract.MovieColumns.LANGUAGE;
import static com.example.mcumoviescatalogue.database.DatabaseContract.MovieColumns.OVERVIEW;
import static com.example.mcumoviescatalogue.database.DatabaseContract.MovieColumns.POSTERPATH;
import static com.example.mcumoviescatalogue.database.DatabaseContract.MovieColumns.RELEASEDATE;
import static com.example.mcumoviescatalogue.database.DatabaseContract.MovieColumns.TITLE;
import static com.example.mcumoviescatalogue.database.DatabaseContract.MovieColumns.VOTEAVG;

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
    private MovieHelper movieHelper;
    private Movie movieData;
    private Drawable star;


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

        movieHelper = MovieHelper.getInstance(getApplicationContext());
        movieHelper.open();

        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(DetailViewModel.class);

        final Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        movieData = movie;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        star = menu.getItem(0).getIcon();
        star.mutate();


        Cursor dataCursor = movieHelper.queryById(movieData.getId());
        if(dataCursor.getCount()>0){
            star.setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);
        } else {
            star.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        }

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        } else if (item.getItemId() == R.id.action_favorite) {
            Cursor dataCursor = movieHelper.queryById(movieData.getId());
            Log.d("DetailMovieActivity",dataCursor.toString());
            if(dataCursor.getCount()==0){
                insertFavorite();
            } else {
                deleteFavorite();
            }

        }
        return super.onOptionsItemSelected(item);
    }

    private void insertFavorite(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, movieData.getId());
        contentValues.put(TITLE, movieData.getName());
        contentValues.put(VOTEAVG, movieData.getVoteavg());
        contentValues.put(OVERVIEW, movieData.getDescriptionFromAPI());
        contentValues.put(RELEASEDATE, movieData.getRelease_date());
        contentValues.put(POSTERPATH, movieData.getPoster_path());
        contentValues.put(BANNERPATH, movieData.getBanner_path());
        contentValues.put(LANGUAGE, movieData.getLanguage());
        contentValues.put(CATEGORY, "movie");
        movieHelper.insert(contentValues);

        star.setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);
    }

    private void deleteFavorite(){
        movieHelper.deleteById(movieData.getId());
        star.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        movieHelper.close();
    }
}
