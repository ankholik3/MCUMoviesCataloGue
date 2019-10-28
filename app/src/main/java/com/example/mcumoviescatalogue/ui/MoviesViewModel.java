package com.example.mcumoviescatalogue.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mcumoviescatalogue.model.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MoviesViewModel extends ViewModel {
    private static final String API_KEY = "f8febe57cc9ed470b0ee502bfb4fdcc8";
    private MutableLiveData<ArrayList<Movie>> listMovieItems = new MutableLiveData<>();

    public MutableLiveData<ArrayList<Movie>> getListMovieItems() {
        return listMovieItems;
    }

    public void setListMovieItems( ) {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Movie> listItems = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/discover/movie?api_key="+API_KEY+"&language=en-US";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    Log.d("result", result+" "+ headers.toString());
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject movie = list.getJSONObject(i);
                        Movie movieItems = new Movie();
                        movieItems.setId(movie.getString("id"));
                        movieItems.setName(movie.getString("title"));
                        movieItems.setDescriptionFromAPI(movie.getString("overview"));
                        movieItems.setRelease_date(movie.getString("release_date"));
                        movieItems.setPoster_path(movie.getString("poster_path"));
                        listItems.add(movieItems);
                    }
                    listMovieItems.postValue(listItems);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }
}
