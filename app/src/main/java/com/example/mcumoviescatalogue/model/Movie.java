package com.example.mcumoviescatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private String id;
    private float voteavg;
    private String name;
    private String descriptionFromAPI;
    private String poster_path;
    private String banner_path;
    private String release_date;
    private String language;

    public Movie(String id, float voteavg, String name, String descriptionFromAPI, String poster_path, String banner_path, String release_date, String language) {
        this.id = id;
        this.voteavg = voteavg;
        this.name = name;
        this.descriptionFromAPI = descriptionFromAPI;
        this.poster_path = poster_path;
        this.banner_path = banner_path;
        this.release_date = release_date;
        this.language = language;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptionFromAPI() {
        return descriptionFromAPI;
    }

    public void setDescriptionFromAPI(String descriptionFromAPI) {
        this.descriptionFromAPI = descriptionFromAPI;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public float getVoteavg() {
        return voteavg;
    }

    public void setVoteavg(float voteavg) {
        this.voteavg = voteavg;
    }

    public String getBanner_path() {
        return banner_path;
    }

    public void setBanner_path(String banner_path) {
        this.banner_path = banner_path;
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
        parcel.writeString(descriptionFromAPI);
        parcel.writeString(poster_path);
        parcel.writeString(release_date);
    }

    protected Movie(Parcel in) {
        id = in.readString();
        name = in.readString();
        descriptionFromAPI = in.readString();
        poster_path = in.readString();
        release_date = in.readString();
    }


    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public Movie() {

    }

}
