package com.example.mcumoviescatalogue.ui.main;

import android.content.Intent;
import android.content.res.TypedArray;
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

import com.example.mcumoviescatalogue.DetailMovieActivity;
import com.example.mcumoviescatalogue.Movie;
import com.example.mcumoviescatalogue.MovieAdapter;
import com.example.mcumoviescatalogue.MoviesViewModel;
import com.example.mcumoviescatalogue.R;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieFragment extends Fragment{

    private MovieAdapter adapter;

    private MoviesViewModel moviesViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        adapter = new MovieAdapter(getActivity());
        RecyclerView recyclerView = view.findViewById(R.id.lv_list1);

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
                }
            }
        });

        moviesViewModel.setListMovieItems();



        return view;
    }



    private void showSelectedMovie(Movie movie) {

        Intent detailMovieIntent = new Intent(getActivity(), DetailMovieActivity.class);
        detailMovieIntent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie);
        startActivity(detailMovieIntent);

    }
}