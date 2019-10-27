package com.example.mcumoviescatalogue;

import android.os.Parcel;
import android.os.Parcelable;

public class TvShow implements Parcelable {
    private int imgTvShowPoster;
    private String tvShowTitle;
    private String tvShowReleaseDate;
    private String tvShowDescription;
    private String tvShowSynopsis;


    public int getImgTvShowPoster() {
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
