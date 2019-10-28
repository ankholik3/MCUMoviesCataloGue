package com.example.mcumoviescatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TvShow implements Parcelable {
    private String id;
    private float voteavg;
    private String name;
    private String overview;
    private String poster_path;
    private String first_air_date;
    private String imgTvShowBanner;
    private String language;

    public TvShow(String id, float voteavg, String name, String overview, String poster_path, String first_air_date, String imgTvShowBanner, String language) {
        this.id = id;
        this.voteavg = voteavg;
        this.name = name;
        this.overview = overview;
        this.poster_path = poster_path;
        this.first_air_date = first_air_date;
        this.imgTvShowBanner = imgTvShowBanner;
        this.language = language;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getVoteavg() {
        return voteavg;
    }

    public void setVoteavg(float voteavg) {
        this.voteavg = voteavg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getImgTvShowBanner() {
        return imgTvShowBanner;
    }

    public void setImgTvShowBanner(String imgTvShowBanner) {
        this.imgTvShowBanner = imgTvShowBanner;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(overview);
        parcel.writeString(poster_path);
        parcel.writeString(first_air_date);
    }

    protected TvShow(Parcel in) {
        id = in.readString();
        name = in.readString();
        overview = in.readString();
        poster_path = in.readString();
        first_air_date = in.readString();
    }

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel in) {
            return new TvShow(in);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };

    public TvShow(){

    }

}
