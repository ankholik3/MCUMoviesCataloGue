package com.example.mcumoviescatalogue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String[] dataMovieTitle;
    private String[] dataDescription;
    private String[] dataSynopsis;
    private String[] dataRelease;
    private TypedArray dataPoster;
    private MovieAdapter adapter;
    private ArrayList<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MovieAdapter(this);
        ListView listView = findViewById(R.id.lv_list);
        //listView.setAdapter(adapter);
        prepare();
        addItem();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(MainActivity.this, movies.get(i).getMovieTitle(), Toast.LENGTH_SHORT).show();
                Movie moviee = new Movie();
                moviee.setMovieTitle(movies.get(i).getMovieTitle());
                moviee.setDescription(movies.get(i).getDescription());
                moviee.setReleaseDate(dataRelease[i]);
                moviee.setSynopsis(dataSynopsis[i]);
                moviee.setImgPoster(movies.get(i).getImgPoster());


                Intent detailMovieIntent = new Intent(MainActivity.this, DetailMovieActivity.class);
                detailMovieIntent.putExtra(DetailMovieActivity.EXTRA_MOVIE, moviee);
                startActivity(detailMovieIntent);
            }
        });

    }

    private void addItem() {
        movies = new ArrayList<>();
        for (int i = 0; i < dataMovieTitle.length; i++) {
            Movie movie = new Movie();
            movie.setImgPoster(dataPoster.getResourceId(i, -1));
            movie.setMovieTitle(dataMovieTitle[i]);
            movie.setDescription(dataDescription[i]);
            //movie.setReleaseDate(dataRelease[i]);
           // movie.setSynopsis(dataSynopsis[i]);
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
}
