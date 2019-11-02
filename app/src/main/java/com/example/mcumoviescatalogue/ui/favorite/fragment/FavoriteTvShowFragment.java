package com.example.mcumoviescatalogue.ui.favorite.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mcumoviescatalogue.R;
import com.example.mcumoviescatalogue.database.MovieHelper;
import com.example.mcumoviescatalogue.model.TvShow;
import com.example.mcumoviescatalogue.ui.TvShowAdapter;
import com.example.mcumoviescatalogue.ui.detail.DetailTvShowActivity;

import java.util.ArrayList;

import static com.example.mcumoviescatalogue.database.DatabaseContract.MovieColumns.BANNERPATH;
import static com.example.mcumoviescatalogue.database.DatabaseContract.MovieColumns.CATEGORY;
import static com.example.mcumoviescatalogue.database.DatabaseContract.MovieColumns.ID;
import static com.example.mcumoviescatalogue.database.DatabaseContract.MovieColumns.LANGUAGE;
import static com.example.mcumoviescatalogue.database.DatabaseContract.MovieColumns.OVERVIEW;
import static com.example.mcumoviescatalogue.database.DatabaseContract.MovieColumns.POSTERPATH;
import static com.example.mcumoviescatalogue.database.DatabaseContract.MovieColumns.RELEASEDATE;
import static com.example.mcumoviescatalogue.database.DatabaseContract.MovieColumns.TITLE;
import static com.example.mcumoviescatalogue.database.DatabaseContract.MovieColumns.VOTEAVG;

public class FavoriteTvShowFragment extends Fragment {
    private TvShowAdapter adapter;
    private MovieHelper movieHelper;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main2, container, false);
        adapter = new TvShowAdapter(getActivity());
        RecyclerView recyclerView = view.findViewById(R.id.lv_list2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        movieHelper = MovieHelper.getInstance(getContext());
        movieHelper.open();


        adapter.setOnItemClickCallback(new TvShowAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(TvShow tvShows) {
                showSelectedTvShow(tvShows);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Cursor dataCursor = movieHelper.queryByCategory("tvshow");
        adapter.setTvShows(mapCursorToArrayList(dataCursor));
    }

    private ArrayList<TvShow> mapCursorToArrayList(Cursor tvshowCursor) {
        ArrayList<TvShow> tvshowsList = new ArrayList<>();

        while (tvshowCursor.moveToNext()) {
            String id = tvshowCursor.getString(tvshowCursor.getColumnIndexOrThrow(ID));
            String title = tvshowCursor.getString(tvshowCursor.getColumnIndexOrThrow(TITLE));
            float voteavg = tvshowCursor.getFloat(tvshowCursor.getColumnIndexOrThrow(VOTEAVG));
            String overview = tvshowCursor.getString(tvshowCursor.getColumnIndexOrThrow(OVERVIEW));
            String releasedate = tvshowCursor.getString(tvshowCursor.getColumnIndexOrThrow(RELEASEDATE));
            String posterpath = tvshowCursor.getString(tvshowCursor.getColumnIndexOrThrow(POSTERPATH));
            String bannerpath = tvshowCursor.getString(tvshowCursor.getColumnIndexOrThrow(BANNERPATH));
            String language = tvshowCursor.getString(tvshowCursor.getColumnIndexOrThrow(LANGUAGE));
            String category = tvshowCursor.getString(tvshowCursor.getColumnIndexOrThrow(CATEGORY));
            tvshowsList.add(new TvShow(id, voteavg, title, overview, posterpath, bannerpath, releasedate, language));
        }

        return tvshowsList;
    }


    private void showSelectedTvShow(TvShow tvShow) {
        Intent detailTvShowIntent = new Intent(getActivity(), DetailTvShowActivity.class);
        detailTvShowIntent.putExtra(DetailTvShowActivity.EXTRA_TV_SHOW, tvShow);
        startActivity(detailTvShowIntent);
    }

}
