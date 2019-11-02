package com.example.mcumoviescatalogue.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mcumoviescatalogue.BuildConfig;
import com.example.mcumoviescatalogue.model.TvShow;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TvViewModel extends ViewModel {
    private static final String API_KEY = BuildConfig.API_KEY;
    private MutableLiveData<ArrayList<TvShow>> listTvItems = new MutableLiveData<>();

    public MutableLiveData<ArrayList<TvShow>> getListTvItems() {
        return listTvItems;
    }

    public void setListTvItems() {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<TvShow> listItems = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/discover/tv?api_key=" + API_KEY + "&language=en-US";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    Log.d("result", result + " " + headers.toString());
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject tv = list.getJSONObject(i);
                        TvShow tvItems = new TvShow();
                        tvItems.setId(tv.getString("id"));
                        tvItems.setName(tv.getString("name"));
                        tvItems.setOverview(tv.getString("overview"));
                        tvItems.setFirst_air_date(tv.getString("first_air_date"));
                        tvItems.setPoster_path(tv.getString("poster_path"));
                        listItems.add(tvItems);
                    }
                    listTvItems.postValue(listItems);
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

    public MutableLiveData<ArrayList<TvShow>> getSearchTvShow(String query) {
        return listTvItems;
    }

    public void setSearchTvShow(String query) {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<TvShow> listItems = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/search/tv?api_key="+API_KEY+"&language=en-US&query="+query+"";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    Log.d("result", result + " " + headers.toString());
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject tv = list.getJSONObject(i);
                        TvShow tvItems = new TvShow();
                        tvItems.setId(tv.getString("id"));
                        tvItems.setName(tv.getString("name"));
                        tvItems.setOverview(tv.getString("overview"));
                        tvItems.setFirst_air_date(tv.getString("first_air_date"));
                        tvItems.setPoster_path(tv.getString("poster_path"));
                        listItems.add(tvItems);
                    }
                    listTvItems.postValue(listItems);
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
