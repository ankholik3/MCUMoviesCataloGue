package com.example.mcumoviescatalogue;

import android.os.Parcel;
import android.os.Parcelable;

public class TvShow implements Parcelable {
    private String id;
    private float voteavg;
    private String imgTvShowPoster;
    private String imgTvShowBanner;
    private String tvShowTitle;
    private String tvShowReleaseDate;
    private String tvShowDescription;
    private String tvShowSynopsis;
    private String language;

    public TvShow(String id, float voteavg, String imgTvShowPoster, String imgTvShowBanner, String tvShowTitle, String tvShowReleaseDate, String tvShowSynopsis, String language) {
        this.id = id;
        this.voteavg = voteavg;
        this.imgTvShowPoster = imgTvShowPoster;
        this.imgTvShowBanner = imgTvShowBanner;
        this.tvShowTitle = tvShowTitle;
        this.tvShowReleaseDate = tvShowReleaseDate;
        this.tvShowSynopsis = tvShowSynopsis;
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

    public String getImgTvShowPoster() {
        return imgTvShowPoster;
    }

    public void setImgTvShowPoster(String imgTvShowPoster) {
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
        parcel.writeString(imgTvShowPoster);
        parcel.writeString(tvShowTitle);
        parcel.writeString(tvShowDescription);
        parcel.writeString(tvShowSynopsis);
        parcel.writeString(tvShowReleaseDate);
    }

    protected TvShow(Parcel in) {
        imgTvShowPoster = in.readString();
        tvShowTitle = in.readString();
        tvShowDescription = in.readString();
        tvShowSynopsis = in.readString();
        tvShowReleaseDate = in.readString();
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
