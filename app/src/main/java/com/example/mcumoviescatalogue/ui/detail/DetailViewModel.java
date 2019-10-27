package com.example.mcumoviescatalogue.ui.detail;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mcumoviescatalogue.Movie;
import com.example.mcumoviescatalogue.TvShow;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

public class DetailViewModel extends ViewModel {
    private static final String API_KEY = "f8febe57cc9ed470b0ee502bfb4fdcc8";
    MutableLiveData<Movie> detailItem = new MutableLiveData<>();
    MutableLiveData<TvShow> detailTv = new MutableLiveData<>();

    public LiveData<Movie> getDetailMovie(int id) {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://api.themoviedb.org/3/movie/" + id + "?api_key=" + API_KEY;

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                try {
                    Movie detailResponse;
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);

                    detailResponse = new Movie(
                            String.valueOf(responseObject.getInt("id")),
                            Float.valueOf(String.valueOf(responseObject.getDouble("vote_average"))),
                            responseObject.getString("original_title"),
                            responseObject.getString("overview"),
                            responseObject.getString("poster_path"),
                            responseObject.getString("backdrop_path"),
                            responseObject.getString("release_date"),
                            responseObject.getString("original_language"));

                    detailItem.postValue(detailResponse);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });

        return detailItem;
    }

    public LiveData<TvShow> getDetailTv(int id) {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://api.themoviedb.org/3/tv/" + id + "?api_key=" + API_KEY;

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                try {
                    TvShow detailResponse;
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);

                    detailResponse = new TvShow(
                            String.valueOf(responseObject.getInt("id")),
                            Float.valueOf(String.valueOf(responseObject.getDouble("vote_average"))),
                            responseObject.getString("original_name"),
                            responseObject.getString("overview"),
                            responseObject.getString("poster_path"),
                            responseObject.getString("first_air_date"),
                            responseObject.getString("backdrop_path"),
                            responseObject.getString("original_language"));

                    detailTv.postValue(detailResponse);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });

        return detailTv;
    }
}
