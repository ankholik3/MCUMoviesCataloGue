package com.example.mcumoviescatalogue;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private int imgPoster;
    private String movieTitle;
    private String releaseDate;
    private String description;
    private String synopsis;
    //FromAPI
    private int id;
    private String name;
    private String descriptionFromAPI;
    private String poster_path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getImgPoster() {
        return imgPoster;
    }

    public void setImgPoster(int imgPoster) {
        this.imgPoster = imgPoster;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(imgPoster);
        parcel.writeString(movieTitle);
        parcel.writeString(description);
        parcel.writeString(synopsis);
        parcel.writeString(releaseDate);
    }

    protected Movie(Parcel in) {
        imgPoster = in.readInt();
        movieTitle = in.readString();
        description = in.readString();
        synopsis = in.readString();
        releaseDate = in.readString();
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

    public Movie(){

    }

}
