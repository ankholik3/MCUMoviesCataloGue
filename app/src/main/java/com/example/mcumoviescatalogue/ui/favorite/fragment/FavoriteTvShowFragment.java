package com.example.mcumoviescatalogue.ui.favorite.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mcumoviescatalogue.R;
import com.example.mcumoviescatalogue.model.TvShow;
import com.example.mcumoviescatalogue.ui.TvShowAdapter;
import com.example.mcumoviescatalogue.ui.TvViewModel;
import com.example.mcumoviescatalogue.ui.detail.DetailTvShowActivity;

import java.util.ArrayList;

public class FavoriteTvShowFragment extends Fragment {
    private TvShowAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main2, container, false);
        adapter = new TvShowAdapter(getActivity());
        RecyclerView recyclerView = view.findViewById(R.id.lv_list2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickCallback(new TvShowAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(TvShow tvShows) {
                showSelectedTvShow(tvShows);
            }
        });

        return view;
    }


    private void showSelectedTvShow (TvShow tvShow) {
        Intent detailTvShowIntent = new Intent(getActivity(), DetailTvShowActivity.class);
        detailTvShowIntent.putExtra(DetailTvShowActivity.EXTRA_TV_SHOW, tvShow);
        startActivity(detailTvShowIntent);
    }

}
