package com.example.mcumoviescatalogue.ui.detail;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mcumoviescatalogue.R;
import com.example.mcumoviescatalogue.database.MovieHelper;
import com.example.mcumoviescatalogue.model.TvShow;

import static com.example.mcumoviescatalogue.database.DatabaseContract.MovieColumns.BANNERPATH;
import static com.example.mcumoviescatalogue.database.DatabaseContract.MovieColumns.CATEGORY;
import static com.example.mcumoviescatalogue.database.DatabaseContract.MovieColumns.ID;
import static com.example.mcumoviescatalogue.database.DatabaseContract.MovieColumns.LANGUAGE;
import static com.example.mcumoviescatalogue.database.DatabaseContract.MovieColumns.OVERVIEW;
import static com.example.mcumoviescatalogue.database.DatabaseContract.MovieColumns.POSTERPATH;
import static com.example.mcumoviescatalogue.database.DatabaseContract.MovieColumns.RELEASEDATE;
import static com.example.mcumoviescatalogue.database.DatabaseContract.MovieColumns.TITLE;
import static com.example.mcumoviescatalogue.database.DatabaseContract.MovieColumns.VOTEAVG;

public class DetailTvShowActivity extends AppCompatActivity {
    public static final String EXTRA_TV_SHOW = "extra_tv_show";
    Float score;
    RatingBar rate;
    TextView tvTvTitle;
    TextView tvTvDescription;
    TextView tvTvSynopsis;
    ImageView ivTvPoster;
    ImageView ivTvBanner;
    TextView tvTvReleaseDate;
    private DetailViewModel viewModel;
    ProgressDialog loadingDialog;
    private MovieHelper movieHelper;
    private TvShow tvShowData;
    private Drawable star;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv_show);
        tvTvTitle = findViewById(R.id.txt_movie_title_detail);
        rate = findViewById(R.id.rb_score);
        //tvDescription = findViewById(R.id.txt_description_detail);
        tvTvReleaseDate = findViewById(R.id.txt_release_date_detail);
        tvTvSynopsis = findViewById(R.id.txt_synopsis_detail);
        ivTvPoster = findViewById(R.id.img_poster_detail);
        ivTvBanner = findViewById(R.id.vv_trailer);

        loadingDialog = new ProgressDialog(DetailTvShowActivity.this);
        loadingDialog.setMessage(getResources().getString(R.string.loading));
        loadingDialog.show();

        movieHelper = MovieHelper.getInstance(getApplicationContext());
        movieHelper.open();

        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(DetailViewModel.class);

        final TvShow tvShow = getIntent().getParcelableExtra(EXTRA_TV_SHOW);
        tvShowData = tvShow;
        viewModel.getDetailTv(Integer.parseInt(tvShow.getId())).observe(this, new Observer<TvShow>() {
            @Override
            public void onChanged(TvShow detailTv) {
                if (detailTv != null) {
                    tvTvTitle.setText(detailTv.getName());
                    tvTvSynopsis.setText(detailTv.getOverview());
                    tvTvReleaseDate.setText(detailTv.getFirst_air_date());

                    score = (detailTv.getVoteavg() * 10 / 20);
                    rate.setRating(score);

                    Glide.with(getApplicationContext())
                            .load("https://image.tmdb.org/t/p/w500" + detailTv.getPoster_path())
                            .apply(new RequestOptions().placeholder(R.drawable.img_placeholder).error(R.drawable.img_placeholder))
                            .into(ivTvPoster);
                    Glide.with(getApplicationContext())
                            .load("https://image.tmdb.org/t/p/w500" + detailTv.getImgTvShowBanner())
                            .apply(new RequestOptions().placeholder(R.drawable.img_placeholder).error(R.drawable.img_placeholder))
                            .into(ivTvBanner);
                    loadingDialog.dismiss();
                }
            }
        });
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        menu.getItem(0).setVisible(false);
        menu.getItem(2).setVisible(false);
        star = menu.getItem(1).getIcon();
        star.mutate();


        Cursor dataCursor = movieHelper.queryById(tvShowData.getId());
        if (dataCursor.getCount() > 0) {
            star.setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);
        } else {
            star.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
        }

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        } else if (item.getItemId() == R.id.action_favorite) {
            Cursor dataCursor = movieHelper.queryById(tvShowData.getId());
            //Log.d("DetailTvShowActivity", dataCursor.toString());
            if (dataCursor.getCount() == 0) {
                insertFavorite();
            } else {
                deleteFavorite();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void insertFavorite() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, tvShowData.getId());
        contentValues.put(TITLE, tvShowData.getName());
        contentValues.put(VOTEAVG, tvShowData.getVoteavg());
        contentValues.put(OVERVIEW, tvShowData.getOverview());
        contentValues.put(RELEASEDATE, tvShowData.getFirst_air_date());
        contentValues.put(POSTERPATH, tvShowData.getPoster_path());
        contentValues.put(BANNERPATH, tvShowData.getImgTvShowBanner());
        contentValues.put(LANGUAGE, tvShowData.getLanguage());
        contentValues.put(CATEGORY, "tvshow");
        movieHelper.insert(contentValues);

        star.setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);
    }

    private void deleteFavorite() {
        movieHelper.deleteById(tvShowData.getId());
        star.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        movieHelper.close();
    }
}
