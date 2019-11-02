package com.example.mcumoviescatalogue.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mcumoviescatalogue.R;
import com.example.mcumoviescatalogue.model.Movie;
import com.example.mcumoviescatalogue.model.TvShow;
import com.example.mcumoviescatalogue.ui.TabMenuActivity;
import com.example.mcumoviescatalogue.ui.TvShowAdapter;
import com.example.mcumoviescatalogue.ui.TvViewModel;
import com.example.mcumoviescatalogue.ui.detail.DetailTvShowActivity;
import com.example.mcumoviescatalogue.ui.favorite.TabMenuFavorite;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class TvShowFragment extends Fragment {
    private TvShowAdapter adapter;
    private TvViewModel tvShowsViewModel;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main2, container, false);
        setHasOptionsMenu(true);
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

        /*tvShowsViewModel.getListTvItems().observe(this, new Observer<ArrayList<TvShow>>() {

            @Override
            public void onChanged(ArrayList<TvShow> tvShow) {
                if (tvShow != null) {
                    Log.d("get data", tvShow.toString());
                    adapter.setTvShows(tvShow);
                    adapter.notifyDataSetChanged();
                    showLoading(false);
                }
            }
        });*/

        tvShowsViewModel.getListTvItems().observe(this, getData);
        tvShowsViewModel.setListTvItems();
        showLoading(true);

        return view;
    }

    private Observer<ArrayList<TvShow>> getData = new Observer<ArrayList<TvShow>>() {
        @Override
        public void onChanged(ArrayList<TvShow> tvShowItems) {
            if (tvShowItems != null) {
                adapter.setTvShows(tvShowItems);
                adapter.notifyDataSetChanged();
                showLoading(false);
            }
            else{
                showLoading(false);
            }
        }
    };


    private void showSelectedTvShow(TvShow tvShow) {
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView sv = new SearchView(((TabMenuActivity) getActivity()).getSupportActionBar().getThemedContext());
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItem.SHOW_AS_ACTION_IF_ROOM);
        item.setActionView(sv);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tvShowsViewModel.setSearchTvShow(query);
                showLoading(true);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.equalsIgnoreCase("")){
                    tvShowsViewModel.setListTvItems();
                    showLoading(true);
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
            return true;
        } else if (item.getItemId() == R.id.action_favorite) {
            Intent mIntent = new Intent(getActivity(), TabMenuFavorite.class);
            startActivity(mIntent);
            return true;
        }
        return false;
    }

}