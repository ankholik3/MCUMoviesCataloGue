package com.example.mcumoviescatalogue.ui.detail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mcumoviescatalogue.R;
import com.example.mcumoviescatalogue.TvShow;

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
        tvTvTitle = findViewById(R.id.txt_movie_title_detail);
        rate = findViewById(R.id.rb_score);
        //tvDescription = findViewById(R.id.txt_description_detail);
        tvTvReleaseDate = findViewById(R.id.txt_release_date_detail);
        tvTvSynopsis = findViewById(R.id.txt_synopsis_detail);
        ivTvPoster = findViewById(R.id.img_poster_detail);
        ivTvBanner = findViewById(R.id.vv_trailer);

        loadingDialog = new ProgressDialog(DetailTvShowActivity.this);
        loadingDialog.setMessage("Loading...");
        loadingDialog.show();

        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(DetailViewModel.class);

        TvShow tvShow = getIntent().getParcelableExtra(EXTRA_TV_SHOW);
        viewModel.getDetailTv(Integer.parseInt(tvShow.getId())).observe(this, new Observer<TvShow>() {
            @Override
            public void onChanged(TvShow detailTv) {
                if (detailTv != null) {
                    tvTvTitle.setText(detailTv.getName());
                    tvTvSynopsis.setText(detailTv.getOverview());
                    tvTvReleaseDate.setText(detailTv.getFirst_air_date());

                    score = (detailTv.getVoteavg()*10/20);
                    rate.setRating(score);

                    Glide.with(getApplicationContext())
                            .load("https://image.tmdb.org/t/p/w500"+detailTv.getPoster_path())
                            .apply(new RequestOptions().placeholder(R.drawable.img_placeholder).error(R.drawable.img_placeholder))
                            .into(ivTvPoster);
                    Glide.with(getApplicationContext())
                            .load("https://image.tmdb.org/t/p/w500"+detailTv.getImgTvShowBanner())
                            .apply(new RequestOptions().placeholder(R.drawable.img_placeholder).error(R.drawable.img_placeholder))
                            .into(ivTvBanner);
                    loadingDialog.dismiss();
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
