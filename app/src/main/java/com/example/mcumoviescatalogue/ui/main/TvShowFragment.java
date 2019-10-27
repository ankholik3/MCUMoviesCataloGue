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
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mcumoviescatalogue.ui.detail.DetailTvShowActivity;
import com.example.mcumoviescatalogue.Movie;
import com.example.mcumoviescatalogue.MovieAdapter;
import com.example.mcumoviescatalogue.MoviesViewModel;
import com.example.mcumoviescatalogue.R;
import com.example.mcumoviescatalogue.TvShow;
import com.example.mcumoviescatalogue.TvShowAdapter;
import com.example.mcumoviescatalogue.TvViewModel;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class TvShowFragment extends Fragment{
    private TvShowAdapter adapter;
    private TvViewModel tvShowsViewModel;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main2, container, false);
        adapter = new TvShowAdapter(getActivity());
        RecyclerView recyclerView = view.findViewById(R.id.lv_list2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        progressBar = view.findViewById(R.id.progressBar);

        tvShowsViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(TvViewModel.class);

        adapter.setOnItemClickCallback(new TvShowAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(TvShow tvShows) {
                showSelectedTvShow(tvShows);
            }
        });

        tvShowsViewModel.getListTvItems().observe(this, new Observer<ArrayList<TvShow>>() {

            @Override
            public void onChanged(ArrayList<TvShow> tvShow) {
                if (tvShow != null) {
                    Log.d("get data", tvShow.toString());
                    adapter.setTvShows(tvShow);
                    adapter.notifyDataSetChanged();
                    showLoading(false);
                }
            }
        });

        tvShowsViewModel.setListTvItems();
        showLoading(true);

        return view;
    }


    private void showSelectedTvShow (TvShow tvShow) {
        Intent detailTvShowIntent = new Intent(getActivity(), DetailTvShowActivity.class);
        detailTvShowIntent.putExtra(DetailTvShowActivity.EXTRA_TV_SHOW, tvShow);
        startActivity(detailTvShowIntent);
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}