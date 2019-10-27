package com.example.mcumoviescatalogue;

import android.os.Parcel;
import android.os.Parcelable;

public class TvShow implements Parcelable {
/*    private int imgTvShowPoster;
    private String tvShowTitle;
    private String tvShowReleaseDate;
    private String tvShowDescription;
    private String tvShowSynopsis;*/

    private String id;
    private String name;
    private String overview;
    private String poster_path;
    private String first_air_date;

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

    /*public int getImgTvShowPoster() {
        return imgTvShowPoster;
    }

    public void setImgTvShowPoster(int imgTvShowPoster) {
        this.imgTvShowPoster = imgTvShowPoster;
    }

    public String getTvShowTitle() {
        return tvShowTitle;
    }

    public void setTvShowTitle(String tvShowTitle) {
        this.tvShowTitle = tvShowTitle;
    }

    public String getTvShowReleaseDate() {
        return tvShowReleaseDate;
    }

    public void setTvShowReleaseDate(String tvShowReleaseDate) {
        this.tvShowReleaseDate = tvShowReleaseDate;
    }

    public String getTvShowDescription() {
        return tvShowDescription;
    }

    public void setTvShowDescription(String tvShowDescription) {
        this.tvShowDescription = tvShowDescription;
    }

    public String getTvShowSynopsis() {
        return tvShowSynopsis;
    }

    public void setTvShowSynopsis(String tvShowSynopsis) {
        this.tvShowSynopsis = tvShowSynopsis;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(imgTvShowPoster);
        parcel.writeString(tvShowTitle);
        parcel.writeString(tvShowDescription);
        parcel.writeString(tvShowSynopsis);
        parcel.writeString(tvShowReleaseDate);
    }

    protected TvShow(Parcel in) {
        imgTvShowPoster = in.readInt();
        tvShowTitle = in.readString();
        tvShowDescription = in.readString();
        tvShowSynopsis = in.readString();
        tvShowReleaseDate = in.readString();
    }*/

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
