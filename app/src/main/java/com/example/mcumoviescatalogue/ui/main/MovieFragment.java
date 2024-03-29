package com.example.mcumoviescatalogue.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mcumoviescatalogue.ui.detail.DetailMovieActivity;
import com.example.mcumoviescatalogue.model.Movie;
import com.example.mcumoviescatalogue.ui.MovieAdapter;
import com.example.mcumoviescatalogue.ui.MoviesViewModel;
import com.example.mcumoviescatalogue.R;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieFragment extends Fragment{

    private MovieAdapter adapter;
    private ProgressBar progressBar;
    private MoviesViewModel moviesViewModel;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        adapter = new MovieAdapter(getActivity());
        RecyclerView recyclerView = view.findViewById(R.id.lv_list1);
        progressBar = view.findViewById(R.id.progressBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        moviesViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MoviesViewModel.class);


        adapter.setOnItemClickCallback(new MovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Movie movies) {
                showSelectedMovie(movies);
            }
        });



        moviesViewModel.getListMovieItems().observe(this, new Observer<ArrayList<Movie>>() {

            @Override
            public void onChanged(ArrayList<Movie> movie) {
                if (movie != null) {
                    Log.d("get data", movie.toString());
                    adapter.setMovies(movie);
                    adapter.notifyDataSetChanged();
                    showLoading(false);
                }
            }
        });

        moviesViewModel.setListMovieItems();
        showLoading(true);


        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }



    private void showSelectedMovie(Movie movie) {

        Intent detailMovieIntent = new Intent(getActivity(), DetailMovieActivity.class);
        detailMovieIntent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie);
        startActivity(detailMovieIntent);

    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}