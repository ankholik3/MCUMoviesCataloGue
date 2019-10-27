package com.example.mcumoviescatalogue;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.entity.mime.Header;

public class MoviesViewModel extends ViewModel {
    private static final String API_KEY = "f8febe57cc9ed470b0ee502bfb4fdcc8";
    private MutableLiveData<ArrayList<Movie>> listMovieItems = new MutableLiveData<>();

    public MutableLiveData<ArrayList<Movie>> getListMovieItems() {
        return listMovieItems;
    }

    public void setListMovieItems(final String movies ) {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Movie> listItems = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/discover/movie?api_key={THEMOVIEDB_API_KEY}&language=en-US";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("list");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject movie = list.getJSONObject(i);
                        Movie movieItems = new Movie();
                        movieItems.setId(movie.getInt("id"));
                        movieItems.setName(movie.getString("name"));
                        movieItems.setDescriptionFromAPI(movie.getJSONArray("weather").getJSONObject(0).getString("description"));
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
