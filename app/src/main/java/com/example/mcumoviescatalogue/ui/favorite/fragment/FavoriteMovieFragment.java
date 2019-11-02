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
import com.example.mcumoviescatalogue.model.Movie;
import com.example.mcumoviescatalogue.ui.MovieAdapter;
import com.example.mcumoviescatalogue.ui.detail.DetailMovieActivity;

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

public class FavoriteMovieFragment extends Fragment {

    private MovieAdapter adapter;
    private MovieHelper movieHelper;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        adapter = new MovieAdapter(getActivity());
        RecyclerView recyclerView = view.findViewById(R.id.lv_list1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        movieHelper = MovieHelper.getInstance(getContext());
        movieHelper.open();


        adapter.setOnItemClickCallback(new MovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Movie movies) {
                showSelectedMovie(movies);
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Cursor dataCursor = movieHelper.queryByCategory("movie");
        adapter.setMovies(mapCursorToArrayList(dataCursor));

    }

    private void showSelectedMovie(Movie movie) {

        Intent detailMovieIntent = new Intent(getActivity(), DetailMovieActivity.class);
        detailMovieIntent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie);
        startActivity(detailMovieIntent);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        movieHelper.close();
    }

    public static ArrayList<Movie> mapCursorToArrayList(Cursor MoviesCursor) {
        ArrayList<Movie> MoviesList = new ArrayList<>();

        while (MoviesCursor.moveToNext()) {
            String id = MoviesCursor.getString(MoviesCursor.getColumnIndexOrThrow(ID));
            String title = MoviesCursor.getString(MoviesCursor.getColumnIndexOrThrow(TITLE));
            float voteavg = MoviesCursor.getFloat(MoviesCursor.getColumnIndexOrThrow(VOTEAVG));
            String overview = MoviesCursor.getString(MoviesCursor.getColumnIndexOrThrow(OVERVIEW));
            String releasedate = MoviesCursor.getString(MoviesCursor.getColumnIndexOrThrow(RELEASEDATE));
            String posterpath = MoviesCursor.getString(MoviesCursor.getColumnIndexOrThrow(POSTERPATH));
            String bannerpath = MoviesCursor.getString(MoviesCursor.getColumnIndexOrThrow(BANNERPATH));
            String language = MoviesCursor.getString(MoviesCursor.getColumnIndexOrThrow(LANGUAGE));
            String category = MoviesCursor.getString(MoviesCursor.getColumnIndexOrThrow(CATEGORY));
            MoviesList.add(new Movie(id, voteavg, title, overview, posterpath, bannerpath, releasedate, language));
        }

        return MoviesList;
    }

}
