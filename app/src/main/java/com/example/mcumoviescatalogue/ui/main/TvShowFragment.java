package com.example.mcumoviescatalogue.ui.main;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mcumoviescatalogue.DetailMovieActivity;
import com.example.mcumoviescatalogue.DetailTvShowActivity;
import com.example.mcumoviescatalogue.Movie;
import com.example.mcumoviescatalogue.MovieAdapter;
import com.example.mcumoviescatalogue.R;
import com.example.mcumoviescatalogue.TvShow;
import com.example.mcumoviescatalogue.TvShowAdapter;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class TvShowFragment extends Fragment{
    private String[] dataTvShowTitle;
    private String[] dataTvShowDescription;
    private String[] dataTvShowSynopsis;
    private String[] dataTvShowRelease;
    private TypedArray dataTvShowPoster;
    private TvShowAdapter adapter;
    private ArrayList<TvShow> tvShows;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main2, container, false);
        adapter = new TvShowAdapter(getActivity());
        RecyclerView recyclerView = view.findViewById(R.id.lv_list2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        prepare();
        addItem();

        adapter.setOnItemClickCallback(new TvShowAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(TvShow tvShows) {
                showSelectedTvShow(tvShows);
            }
        });
/*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(MainActivity.this, movies.get(i).getMovieTitle(), Toast.LENGTH_SHORT).show();
                Movie tvshow = new Movie();
                tvshow.setMovieTitle(tvShows.get(i).getMovieTitle());
                tvshow.setDescription(tvShows.get(i).getDescription());
                tvshow.setReleaseDate(dataTvShowRelease[i]);
                tvshow.setSynopsis(dataTvShowSynopsis[i]);
                tvshow.setImgPoster(tvShows.get(i).getImgPoster());


                Intent detailMovieIntent = new Intent(getActivity(), DetailMovieActivity.class);
                detailMovieIntent.putExtra(DetailMovieActivity.EXTRA_MOVIE, tvshow);
                startActivity(detailMovieIntent);
            }
        });
*/

        return view;
    }

    private void addItem() {
        tvShows = new ArrayList<>();
        for (int i = 0; i < dataTvShowTitle.length; i++) {
            TvShow tvShow = new TvShow();
            tvShow.setImgTvShowPoster(dataTvShowPoster.getResourceId(i, -1));
            tvShow.setTvShowTitle(dataTvShowTitle[i]);
            tvShow.setTvShowDescription(dataTvShowDescription[i]);
            tvShow.setTvShowReleaseDate(dataTvShowRelease[i]);
            tvShow.setTvShowSynopsis(dataTvShowSynopsis[i]);
            tvShows.add(tvShow);
        }
        Log.d("Data", "hasil "+tvShows.size());
        adapter.setTvShows(tvShows);
        adapter.notifyDataSetChanged();
    }

    private void prepare() {
        /*dataTvShowTitle = getResources().getStringArray(R.array.data_movie_title);
        dataTvShowDescription = getResources().getStringArray(R.array.data_description);
        dataTvShowPoster = getResources().obtainTypedArray(R.array.data_poster);
        dataTvShowSynopsis = getResources().getStringArray(R.array.data_synopsis);
        dataTvShowRelease = getResources().getStringArray(R.array.data_release_date);
    */
        dataTvShowTitle = getResources().getStringArray(R.array.data_tv_show_title);
        dataTvShowDescription = getResources().getStringArray(R.array.data_description_tv_show);
        dataTvShowPoster = getResources().obtainTypedArray(R.array.data_poster_tv_show);
        dataTvShowSynopsis = getResources().getStringArray(R.array.data_synopsis_tv_show);
        dataTvShowRelease = getResources().getStringArray(R.array.data_release_date_tv_show);
    }

    private void showSelectedTvShow (TvShow tvShow) {
        //Toast.makeText(getActivity(), "Kamu memilih " + movie.getMovieTitle(), Toast.LENGTH_SHORT).show();
        TvShow tvShowe = new TvShow();
        tvShowe.setTvShowTitle(tvShow.getTvShowTitle());
        tvShowe.setTvShowDescription(tvShow.getTvShowDescription());
        tvShowe.setTvShowReleaseDate(tvShow.getTvShowReleaseDate());
        tvShowe.setTvShowSynopsis(tvShow.getTvShowSynopsis());
        tvShowe.setImgTvShowPoster(tvShow.getImgTvShowPoster());

        Intent detailTvShowIntent = new Intent(getActivity(), DetailTvShowActivity.class);
        detailTvShowIntent.putExtra(DetailTvShowActivity.EXTRA_TV_SHOW, tvShowe);
        startActivity(detailTvShowIntent);
    }
}