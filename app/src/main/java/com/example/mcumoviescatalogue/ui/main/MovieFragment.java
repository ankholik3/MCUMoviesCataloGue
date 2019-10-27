package com.example.mcumoviescatalogue.ui.main;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mcumoviescatalogue.DetailMovieActivity;
import com.example.mcumoviescatalogue.MainActivity;
import com.example.mcumoviescatalogue.Movie;
import com.example.mcumoviescatalogue.MovieAdapter;
import com.example.mcumoviescatalogue.R;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieFragment extends Fragment{
    private String[] dataMovieTitle;
    private String[] dataDescription;
    private String[] dataSynopsis;
    private String[] dataRelease;
    private TypedArray dataPoster;
    private MovieAdapter adapter;
    private ArrayList<Movie> movies;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        adapter = new MovieAdapter(getActivity());
        RecyclerView recyclerView = view.findViewById(R.id.lv_list1);
        adapter.setMovies(movies);
        adapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        prepare();
        addItem();

        adapter.setOnItemClickCallback(new MovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Movie movies) {
                showSelectedMovie(movies);
            }
        });
        /*
        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(MainActivity.this, movies.get(i).getMovieTitle(), Toast.LENGTH_SHORT).show();
                Movie moviee = new Movie();
                moviee.setMovieTitle(movies.get(i).getMovieTitle());
                moviee.setDescription(movies.get(i).getDescription());
                moviee.setReleaseDate(dataRelease[i]);
                moviee.setSynopsis(dataSynopsis[i]);
                moviee.setImgPoster(movies.get(i).getImgPoster());


                Intent detailMovieIntent = new Intent(getActivity(), DetailMovieActivity.class);
                detailMovieIntent.putExtra(DetailMovieActivity.EXTRA_MOVIE, moviee);
                startActivity(detailMovieIntent);
            }
        });
*/

        return view;
    }

    private void addItem() {
        movies = new ArrayList<>();
        for (int i = 0; i < dataMovieTitle.length; i++) {
            Movie movie = new Movie();
            movie.setImgPoster(dataPoster.getResourceId(i, -1));
            movie.setMovieTitle(dataMovieTitle[i]);
            movie.setDescription(dataDescription[i]);
            movie.setReleaseDate(dataRelease[i]);
            movie.setSynopsis(dataSynopsis[i]);
            movies.add(movie);
        }
        adapter.setMovies(movies);
    }

    private void prepare() {
        dataMovieTitle = getResources().getStringArray(R.array.data_movie_title);
        dataDescription = getResources().getStringArray(R.array.data_description);
        dataPoster = getResources().obtainTypedArray(R.array.data_poster);
        dataSynopsis = getResources().getStringArray(R.array.data_synopsis);
        dataRelease = getResources().getStringArray(R.array.data_release_date);
    }

    private void showSelectedMovie(Movie movie) {
        //Toast.makeText(getActivity(), "Kamu memilih " + movie.getMovieTitle(), Toast.LENGTH_SHORT).show();
        Movie moviee = new Movie();
        moviee.setMovieTitle(movie.getMovieTitle());
        moviee.setDescription(movie.getDescription());
        moviee.setReleaseDate(movie.getReleaseDate());
        moviee.setSynopsis(movie.getSynopsis());
        moviee.setImgPoster(movie.getImgPoster());

        Intent detailMovieIntent = new Intent(getActivity(), DetailMovieActivity.class);
        detailMovieIntent.putExtra(DetailMovieActivity.EXTRA_MOVIE, moviee);
        startActivity(detailMovieIntent);

    }
}