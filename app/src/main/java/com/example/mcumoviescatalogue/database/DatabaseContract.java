package com.example.mcumoviescatalogue.database;

import android.provider.BaseColumns;

public class DatabaseContract {
    static String TABLE_NAME = "movie";

    public static final class MovieColumns implements BaseColumns {
        public static final String ID = "id";
        public static final String VOTEAVG = "voteavg";
        public static final String TITLE = "name";
        public static final String OVERVIEW = "overview";
        public static final String RELEASEDATE = "releasedate";
        public static final String POSTERPATH = "posterpath";
        public static final String BANNERPATH = "bannerpath";
        public static final String LANGUAGE = "language";
        public static final String CATEGORY = "Category";


    }
}
